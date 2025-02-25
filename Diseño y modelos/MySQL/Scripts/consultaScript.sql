USE clinicabda;

-- Procedimiento almacenado obtenerConsultasPaciente
-- Obtiene las consultas asociadas a un paciente específico
DELIMITER $$
CREATE PROCEDURE obtenerConsultasPaciente(
	IN emailPaciente VARCHAR(100)
)
BEGIN
	-- Variable para guardar el id
	DECLARE id INT;
    
    -- Si el paciente no existe en el sistema
    IF NOT EXISTS(SELECT email FROM pacientes WHERE email = emailPaciente) THEN
		-- Envía error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	-- Si sí existe
	ELSE
		-- Obtiene su id y lo guarda
		SET id = (SELECT idPaciente FROM pacientes WHERE email = emailPaciente);
	END IF;
    
    -- Busca las consultas de un paciente mediante su id
	SELECT 
		idConsulta,
        estadoConsulta,
        IFNULL(diagnostico, "N/A") AS diagnostico,
        IFNULL(tratamiento, "N/A") AS tratamiento,
        IFNULL(notas, "N/A") AS notas,
        idCita,
        fechaHoraInicio,
        estadoCita,
        IFNULL(folio, "Sin folio") AS folio,
        tipo,
        idPaciente,
		idMedico,
        cedula,
        especialidad
	FROM consultasCitas
    WHERE idPaciente = id
    ORDER BY fechaHoraInicio DESC;
END $$
DELIMITER ;

-- Procedimiento almacenado consultasCitas
-- Obtiene la información de consultas junto con sus citas asociadas
CREATE OR REPLACE VIEW consultasCitas AS
SELECT
	co.idConsulta,
    co.estado AS estadoConsulta,
    co.diagnostico, 
    co.tratamiento, 
    co.notas,
    co.idCita,
    c.fechaHoraInicio, 
    c.estado AS estadoCita,
    c.folio,
    c.tipo,
    c.idPaciente,
    c.idMedico,
	m.cedula,
    m.especialidad
FROM consultas AS co
LEFT JOIN citas AS c
	ON co.idCita = c.idCita
INNER JOIN medicos AS m
	ON c.idMedico = m.idMedico;

-- Función obtenerEstadoConsulta
-- Obtiene el estado de una consulta específica
DELIMITER $$
CREATE FUNCTION obtenerEstadoConsulta(id_cita INT)
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
	DECLARE estadoConsulta VARCHAR(50);
    
SELECT 
    estado
INTO estadoConsulta FROM
    consultas
WHERE
    idCita = id_cita;
    
    RETURN estadoConsulta;
END$$
DELIMITER ;

-- Procedimiento almacenado obtenerConsultaPorIdCita
-- Obtiene la consulta relacionada a una cita específica
DELIMITER $$
CREATE PROCEDURE obtenerConsultaPorIdCita(
IN id_cita INT
)
BEGIN
	SELECT *
    FROM consultas
    WHERE idCita = id_cita;
END$$
DELIMITER ;

-- Evento actualizarConsultasNoAsistidas
-- Cambia el estado de la cita y su consulta relacionada si ya se pasó el tiempo de tolerancia establecido
-- Se ejecuta cada minuto
DELIMITER $$
CREATE EVENT IF NOT EXISTS actualizarConsultasNoAsistidas
ON SCHEDULE EVERY 1 MINUTE -- Se ejecuta cada minuto
DO
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK; -- Si ocurre un error, deshacer cambios
    END;

    START TRANSACTION;

    -- Cambiar el estado de las citas de emergencia que no han sido atendidas en 10 minutos
    UPDATE citas
    SET estado = 'TERMINADA'
    WHERE tipo = 'EMERGENCIA'
        AND estado = 'ACTIVA'
        AND TIMESTAMPDIFF(MINUTE, fechaHoraInicio, NOW()) > 10;

    -- Cambiar el estado de las consultas relacionadas a esas citas a "NO ASISTIO"
    UPDATE consultas
    SET estado = 'NO ASISTIO'
    WHERE idCita IN (
        SELECT idCita FROM citas 
        WHERE tipo = 'EMERGENCIA'
            AND estado = 'TERMINADA'
            AND TIMESTAMPDIFF(MINUTE, fechaHoraInicio, NOW()) > 10
    );

    COMMIT; -- Si todo se ejecutó correctamente, guardar cambios
END $$
DELIMITER ;

-- Procedimiento almacenado editarDatosConsulta
-- Edita los datos de una consulta (descripciones) y cambia su estado y el de su cita relacionada.
DELIMITER $$
CREATE PROCEDURE editarDatosConsulta(
	IN id_consulta INT,
    IN diagnostico_consulta TEXT,
    IN tratamiento_consulta TEXT,
    IN notas_consulta TEXT
)
BEGIN
	DECLARE id_cita INT;
    
    -- Manejador de errores.
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
        -- Deshace la transacción en caso de error
        ROLLBACK;
        -- Lanza error
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Error al terminar cita";
    END;
    
    -- Usar transacción para registrar todo
	START TRANSACTION;
	
    -- Edita los datos de la consulta (descripciones y estado)
	UPDATE consultas 
    SET 
		diagnostico = diagnostico_consulta, 
        tratamiento = tratamiento_consulta, 
        notas = notas_consulta,
        estado = 'ASISTIO'
	WHERE idConsulta = id_consulta;
    
    -- Obtiene el id de la cita asociado a la consulta
    SELECT idCita
    INTO id_cita
    FROM consultas
    WHERE idConsulta = id_consulta;
    
    -- Edita el estado de la cita
    UPDATE citas
    SET estado = 'TERMINADA'
    WHERE idCita = id_cita;
    COMMIT;
END$$
DELIMITER ;