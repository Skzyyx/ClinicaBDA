USE clinicabda;
DELIMITER $$
CREATE PROCEDURE obtenerConsultasPaciente(
	IN emailPaciente VARCHAR(100)
)
BEGIN
	DECLARE id INT;
    
    IF NOT EXISTS(SELECT email FROM pacientes WHERE email = emailPaciente) THEN
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	ELSE
		SET id = (SELECT idPaciente FROM pacientes WHERE email = emailPaciente);
	END IF;
    
	SELECT 
		idConsulta,
        estadoConsulta,
        IFNULL("N/A", diagnostico) AS diagnostico,
        IFNULL("N/A", tratamiento) AS tratamiento,
        IFNULL("N/A", notas) AS notas,
        idCita,
        fechaHoraInicio,
        estadoCita,
        IFNULL("Sin folio", folio) AS folio,
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

DELIMITER $$
CREATE FUNCTION obtenerEstadoConsulta( id_cita INT)
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