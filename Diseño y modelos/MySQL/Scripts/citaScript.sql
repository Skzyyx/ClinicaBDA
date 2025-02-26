-- Procedimiento almacenado registrarCita
-- Agregar una cita y crea su consulta
-- Si el paciente ya tenia una cita programada en el mismo dia y hora, lanza error
DELIMITER $$
CREATE PROCEDURE registrarCita(
    IN fechaHoraInicioCita DATETIME,
    IN folioCita VARCHAR(8),
    IN tipoCita ENUM('EMERGENCIA', 'PROGRAMADA'),
    IN usuarioPaciente VARCHAR(100),
    IN idMedicoCita INT
)
BEGIN
	DECLARE idPacienteCita INT;
    
    -- Manejador de errores. Por si ocurre un error, la transacción no queda abierta
    DECLARE errorMensaje TEXT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
        GET DIAGNOSTICS CONDITION 1 errorMensaje = MESSAGE_TEXT;
        ROLLBACK;
        SET errorMensaje = LEFT(errorMensaje, 128);
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = errorMensaje;
    END;
    
    -- Se obtiene el id del paciente mediante su correo.
    SELECT idPaciente
	INTO idPacienteCita
    FROM pacientes
    WHERE email =  usuarioPaciente;
    
    IF idPacienteCita IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se encontró paciente con ese email.';
	END IF;
    
    -- Empezar la transacción
    START TRANSACTION;
    
    -- Verificar si el paciente ya tiene una cita activa en la misma fecha y hora
    IF EXISTS (
        SELECT 1 FROM citas 
        WHERE idPaciente = idPacienteCita 
        AND fechaHoraInicio = fechaHoraInicioCita
        AND estado = 'ACTIVA'
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El paciente ya tiene una cita en esta fecha y hora.';
    END IF;
    
    -- Verificar si el médico ya tiene una cita programada con otro paciente a la misma hora
    IF EXISTS (
        SELECT 1 FROM citas 
        WHERE idMedico = idMedicoCita 
        AND fechaHoraInicio = fechaHoraInicioCita
        AND estado = 'ACTIVA'
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El médico ya tiene una cita programada en esta fecha y hora.';
    END IF;
    
    -- Si no hay conflictos, se crea la cita
    INSERT INTO citas(fechaHoraInicio, folio, tipo, idPaciente, idMedico) VALUES 
    (fechaHoraInicioCita, folioCita, tipoCita, idPacienteCita, idMedicoCita);
    
    -- Se crea la consulta
    INSERT INTO consultas(idCita) VALUES
        (LAST_INSERT_ID());
    
    -- Confirmar el commit
    COMMIT;
END$$
DELIMITER ;

-- Procedimiento verificarCitaExiste
-- Verifica si una cita existe
DELIMITER $$
CREATE PROCEDURE verificarCitaExiste(
	IN fechaHoraInicio_cita DATETIME,
    IN idMedico_cita INT,
    OUT existe BOOL
)
BEGIN
    IF EXISTS (
    SELECT * 
    FROM citas AS c
    WHERE c.fechaHoraInicio = fechaHoraInicio_cita
    AND c.idMedico = idMedico_cita) THEN
		SET existe = TRUE;
	ELSE 
		SET existe = FALSE;
	END IF;
END$$
DELIMITER ;

-- Procedimiento cancelarCita
-- Cambia el estado a 'CANCELADA' de una cita específica
DELIMITER $$
CREATE PROCEDURE cancelarCita(
	IN id_cita INT
)
BEGIN
	UPDATE citas SET estado = 'CANCELADA' WHERE idCita = id_cita;
END$$
DELIMITER ;

-- Trigger citaCancelada
-- Cambia el estado de la consulta a 'NO ASISTIO' cuando se cancela su cita relacionada
DELIMITER $$
CREATE TRIGGER citaCancelada 
AFTER UPDATE ON citas
FOR EACH ROW
BEGIN
	IF OLD.estado = "ACTIVA" AND NEW.estado = "CANCELADA" THEN 
		UPDATE consultas SET estado = "NO ASISTIO" 
        WHERE idCita = OLD.idCita;
	END IF;
END$$
DELIMITER ;

-- Procedimiento almacenado obtenerCitaEmergencia
-- Obtiene una cita de emergencia con un folio específico
DELIMITER $$
CREATE PROCEDURE obtenerCitaEmergencia(
	IN folioCita VARCHAR(8)
)
BEGIN
	IF folioCita IS NOT NULL AND NOT EXISTS (SELECT 1 FROM citas WHERE folio = folioCita) THEN
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "No existe cita con el folio ingresado";
	END IF;
    
	SELECT
		folio,
        fechaHoraInicio,
        nombre,
        apellidoPaterno,
        IFNULL(" ", apellidoMaterno) AS apellidoMaterno,
		cedula
	FROM citas AS c
    INNER JOIN medicos AS m
		ON c.idMedico = m.idMedico
	WHERE tipo = "EMERGENCIA"
    AND folio = folioCita;
END$$
DELIMITER ;

-- Evento actualizarCitasNoAsistidas
-- Cambia el estado de la cita y su consulta relacionada si ya se pasó el tiempo de tolerancia establecido
-- Se ejecuta cada minuto
DELIMITER $$
CREATE EVENT IF NOT EXISTS actualizarCitasNoAsistidas
ON SCHEDULE EVERY 1 MINUTE -- Se ejecuta cada minuto
DO
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK; -- Si ocurre un error, deshacer cambios
    END;

    START TRANSACTION;

    -- Cambiar el estado de las citas programadas que no han sido atendidas en 15 minutos
    UPDATE citas
    SET estado = 'TERMINADA'
    WHERE tipo = 'PROGRAMADA'
        AND estado = 'ACTIVA'
        AND TIMESTAMPDIFF(MINUTE, fechaHoraInicio, NOW()) > 15;

    -- Cambiar el estado de las consultas asociadas a esas citas a "NO ASISTIO"
    UPDATE consultas
    SET estado = 'NO ASISTIO'
    WHERE idCita IN (
        SELECT idCita FROM citas 
        WHERE tipo = 'PROGRAMADA'
            AND estado = 'TERMINADA'
            AND TIMESTAMPDIFF(MINUTE, fechaHoraInicio, NOW()) > 15
    );

    COMMIT; -- Si todo se ejecutó correctamente, guardar cambios
END $$

    