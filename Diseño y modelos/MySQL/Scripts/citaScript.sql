-- Procedimiento almacenado registrarCita
-- Agregar una cita y crea su consulta
-- Si el paciente ya tenia una cita programada en el mismo dia y hora, lanza error
DELIMITER $$
CREATE PROCEDURE registrarCita(
	IN fechaHoraInicioCita DATETIME,
    IN folioCita VARCHAR(8),
    IN tipoCita ENUM('EMERGENCIA', 'PROGRAMADA'),
    IN idPacienteCita INT,
    IN idMedicoCita INT
) 
BEGIN
	-- Manejador de errores. Por si ocurre un error, la transacción no queda abierta
    DECLARE errorMensaje TEXT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
	BEGIN
		GET DIAGNOSTICS CONDITION 1 errorMensaje = MESSAGE_TEXT;
		ROLLBACK;
		SET errorMensaje = LEFT(errorMensaje, 128);
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = errorMensaje;
	END;
    
    -- Empezar la transaccion
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
    
    -- Si no tiene citas en la misma hora, se crea la cita
    INSERT INTO citas(fechaHoraInicio, folio, tipo, idPaciente, idMedico) VALUES 
	(fechaHoraInicioCita, folioCita, tipoCita, idPacienteCita, idMedicoCita);
    
    -- Se crea la consulta
    INSERT INTO consultas(idCita) VALUES
		(LAST_INSERT_ID());
	
    -- Confirmar el commit
    COMMIT;
END$$
DELIMITER ;

SET @errorMensaje = "ASD";