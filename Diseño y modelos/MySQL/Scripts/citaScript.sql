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

DELIMITER $$
CREATE PROCEDURE cancelarCita(
	IN id_cita INT
)
BEGIN
	UPDATE citas SET estado = 'CANCELADA' WHERE idCita = id_cita;
END$$
DELIMITER ;

CALL verificarCitaExiste('2025-02-22 17:30:00', 1, @existeCita);
SELECT @existeCita;