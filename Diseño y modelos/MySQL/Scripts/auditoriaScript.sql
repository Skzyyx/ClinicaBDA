USE clinicabda;

-- Tabla para registrar la auditoría de cuando se agenden citas
CREATE TABLE auditoriaCitaAgendada (
	idAuditoria INT PRIMARY KEY AUTO_INCREMENT,
    fechaHoraMovimiento DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    idCita INT NOT NULL,
    fechaHoraCita DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    tipoCita ENUM('EMERGENCIA', 'PROGRAMADA') NOT NULL,
    idPaciente INT NOT NULL,
    idMedico INT NOT NULL,
    FOREIGN KEY (idCita) REFERENCES citas(idCita),
    FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente),
    FOREIGN KEY (idMedico) REFERENCES medicos(idMedico)
);

-- Tabla para registrar la auditoría de cuando se inicie o no una consulta
CREATE TABLE auditoriaConsultaInicio (
	idAuditoria INT PRIMARY KEY AUTO_INCREMENT,
    fechaHoraMovimiento DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    idConsulta INT NOT NULL,
    estadoConsulta ENUM('ASISTIO', 'NO ASISTIO') NOT NULL,
	idCita INT NULL,
    idPaciente INT NOT NULL,
    idMedico INT NOT NULL,
    FOREIGN KEY (idConsulta) REFERENCES consultas(idConsulta),
    FOREIGN KEY (idCita) REFERENCES consultas(idCita),
    FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente),
    FOREIGN KEY (idMedico) REFERENCES medicos(idMedico)
);

-- Tabla para registrar la auditoría de cancelaciones de citas
CREATE TABLE auditoriaCitaCancelada (
	idAuditoria INT PRIMARY KEY AUTO_INCREMENT,
    fechaHoraMovimiento DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	idCita INT NOT NULL,
    estadoAnterior ENUM('ACTIVA', 'CANCELADA', 'TERMINADA') NOT NULL,
    idConsulta INT NOT NULL,
    idPaciente INT NOT NULL,
    idMedico INT NOT NULL,
    FOREIGN KEY (idConsulta) REFERENCES consultas(idConsulta),
    FOREIGN KEY (idCita) REFERENCES consultas(idCita),
    FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente),
    FOREIGN KEY (idMedico) REFERENCES medicos(idMedico)
);

-- Trigger audProgramaCita
-- Después de que entra un registro a la tabla Citas, se registra en la tabla de auditoria audProgramaCita
DELIMITER $$
CREATE TRIGGER audProgramaCita
AFTER INSERT ON citas
FOR EACH ROW
BEGIN
	-- Inserta el registro en la auditoría
	INSERT INTO auditoriaCitaAgendada(fechaHoraMovimiento, idCita, tipoCita, idPaciente, idMedico) 
    VALUES (NOW(), NEW.idCita, NEW.tipo, NEW.idPaciente, NEW.idMedico);
END $$
DELIMITER ;

-- Trigger audConsultaInicia
-- Después de que se inicia una consulta (estado anterior es PENDIENTE y nuevo es ASISTIO o NO ASISTIO), 
-- se registra en la tabla de auditoria audConsultaInicia
DELIMITER $$
CREATE TRIGGER audConsultaInicia
AFTER UPDATE ON consultas
FOR EACH ROW
BEGIN
	DECLARE idPacienteC INT;
    DECLARE idMedicoC INT;
    
    -- Si se inició la consulta
    IF (OLD.estado = "PENDIENTE" AND (NEW.estado = "ASISTIO" OR NEW.estado = "NO ASISTIO")) THEN
		-- Obtiene el idPaciente e idMedico asociados
		SELECT 
			p.idPaciente, 
			c.idMedico
		INTO 
			idPacienteC, 
			idMedicoC
		FROM pacientes AS p
		INNER JOIN citas AS c 
			ON p.idPaciente = c.idPaciente
		INNER JOIN consultas AS co 
			ON co.idCita = c.idCita
		WHERE NEW.idConsulta = co.idConsulta
		LIMIT 1;
		
        -- Inserta el registro en la auditoria
		INSERT INTO auditoriaConsultaInicio(fechaHoraMovimiento, idConsulta, estadoConsulta, idCita, idPaciente, idMedico)
		VALUES (NOW(), NEW.idConsulta, NEW.estado, NEW.idCita, idPacienteC, idMedicoC);
	END IF;
END $$
DELIMITER ;

-- Trigger audCitaCancelada
-- Después de que se cancela una cita, se registra en la tabla de auditoria audCitaCancelada
DELIMITER $$
CREATE TRIGGER audCitaCancelada
AFTER UPDATE ON citas
FOR EACH ROW
BEGIN
	DECLARE idPacienteC INT;
    DECLARE idMedicoC INT;
    DECLARE idConsultaC INT;
    
	IF (OLD.tipo = "PROGRAMADA" AND OLD.estado = "ACTIVA" AND NEW.estado = "CANCELADA") THEN
		SELECT c.idPaciente, c.idMedico, co.idConsulta
        INTO idPacienteC, idMedicoC, idConsultaC
        FROM citas AS c
        INNER JOIN consultas AS co
			ON c.idCita = co.idCita
		WHERE NEW.idCita = c.idCita;
        
        INSERT INTO auditoriaCitaCancelada(fechaHoraMovimiento, idCita, estadoAnterior, idConsulta, idPaciente, idMedico)
        VALUES (NOW(), NEW.idCita, OLD.estado, idConsultaC, idPacienteC, idMedicoC);
    END IF;
END $$
DELIMITER ;