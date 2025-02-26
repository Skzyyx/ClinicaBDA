CREATE DATABASE ClinicaBDA;
USE ClinicaBDA;

/*-------------------------------- CREACIÓN DE LAS TABLAS BASES --------------------------------*/
/*
	Usuarios(
		idUsuario :INT PRIMARY KEY AUTO_INCREMENT,
		usuario :VARCHAR(100) UNIQUE NOT NULL,
		contraseña :VARCHAR(70) NOT NULL,
		rol :ENUM(‘PACIENTE’, ‘MEDICO’) NOT NULL
	)
*/
CREATE TABLE usuarios(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(100) UNIQUE NOT NULL,
    contrasenia VARCHAR(70) NOT NULL,
    rol ENUM('PACIENTE', 'MEDICO') NOT NULL
);

/*
	Pacientes(
		idPaciente :INT PRIMARY KEY AUTO_INCREMENT,
		nombre :VARCHAR(50) NOT NULL,
		apellidoPaterno :VARCHAR(50) NOT NULL,
		apellidoMaterno :VARCHAR(50) NULL,
		fechaNacimiento :DATE NOT NULL,
		email :VARCHAR(100) UNIQUE NOT NULL,
		telefono :VARCHAR(10) UNIQUE NOT NULL,
		idUsuario :INT NOT NULL
	)
	 - idUsuario hace referencia a idUsuario en la tabla Usuarios
*/
CREATE TABLE pacientes(
	idPaciente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
	apellidoPaterno VARCHAR(50) NOT NULL,
	apellidoMaterno VARCHAR(50) NULL,
	fechaNacimiento DATE NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	telefono VARCHAR(10) UNIQUE NOT NULL,
	idUsuario INT UNIQUE NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);

/*
	Direcciones(
		idDireccion :INT PRIMARY KEY AUTO_INCREMENT,
		calle :VARCHAR(100) NOT NULL,
		numero :VARCHAR(5) NOT NULL,
		colonia :VARCHAR(100) NOT NULL,
		codigoPostal :VARCHAR(5) NOT NULL
		idPaciente :INT UNIQUE NOT NULL
	)
	 - idPaciente hace referencia a idPaciente la tabla Pacientes
*/
CREATE TABLE direcciones(
	idDireccion INT PRIMARY KEY AUTO_INCREMENT,
    calle VARCHAR(100) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	colonia VARCHAR(100) NOT NULL,
	codigoPostal VARCHAR(5) NOT NULL,
	idPaciente INT UNIQUE NOT NULL,
    FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente)
);

/*
	Medicos(
		idMedico :INT PRIMARY KEY AUTO_INCREMENT,
		nombre :VARCHAR(50) NOT NULL,
		apellidoPaterno :VARCHAR(50) NOT NULL,
		apellidoMaterno :VARCHAR(50) NULL,
		especialidad :VARCHAR(50) NOT NULL,
		cedula :VARCHAR(8) UNIQUE NOT NULL,
		estado :ENUM(‘ACTIVO’, ‘INACTIVO’) NOT NULL DEFAULT “ACTIVO”,
		idUsuario :INT NOT NULL
	)
	 - idUsuario hace referencia a idUsuario en la tabla Usuarios
*/
CREATE TABLE medicos(
	idMedico INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
	apellidoMaterno VARCHAR(50) NULL,
	especialidad VARCHAR(50) NOT NULL,
	cedula VARCHAR(8) UNIQUE NOT NULL,
	estado ENUM('ACTIVO', 'INACTIVO') NOT NULL DEFAULT 'ACTIVO',
	idUsuario INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);

/*
	Horarios(
		idHorario :INT PRIMARY KEY AUTO_INCREMENT,
		diaSemana :ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY, 'FRIDAY', 'SATURDAY', 'SUNDAY') NOT NULL,
		horaEntrada :TIME NOT NULL,
		horaSalida :TIME NOT NULL
		idMedico :INT NOT NULL
	)
	 - idMedico hace referencia a idMedico en la tabla Medicos
*/
CREATE TABLE horarios(
	idHorario INT PRIMARY KEY AUTO_INCREMENT,
    diaSemana ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') NOT NULL, 
    horaEntrada TIME NOT NULL,
    horaSalida TIME NOT NULL,
    idMedico INT NOT NULL,
    FOREIGN KEY (idMedico) REFERENCES medicos(idMedico)
);

/*
	Citas(
		idCita :INT PRIMARY KEY AUTO_INCREMENT,
		fechaHoraInicio :DATETIME NOT NULL,
		estado :ENUM('ACTIVA', 'TERMINADA', 'CANCELADA') NOT NULL DEFAULT 'ACTIVA',
        folio :VARCHAR(8) UNIQUE NULL,
        tipo :ENUM(“EMERGENCIA”, “PROGRAMADA”) NOT NULL,
		idPaciente :INT NOT NULL,
		idMedico : INT NOT NULL.
	)
	 - idPaciente hace referencia a idPaciente en la tabla Pacientes
	 - idMedico hace referencia a idMedico en la tabla Medicos
*/
CREATE TABLE citas(
	idCita INT PRIMARY KEY AUTO_INCREMENT,
    fechaHoraInicio DATETIME NOT NULL,
    estado ENUM('ACTIVA', 'TERMINADA', 'CANCELADA') NOT NULL DEFAULT 'ACTIVA',
    folio VARCHAR(8) UNIQUE NULL,
    tipo ENUM('EMERGENCIA', 'PROGRAMADA') NOT NULL,
	idPaciente INT NOT NULL,
	idMedico INT NOT NULL,
    FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente),
    FOREIGN KEY (idMedico) REFERENCES medicos(idMedico)
);

/*
	Consultas(
		idConsulta :INT PRIMARY KEY AUTO_INCREMENT,
		estado :ENUM('ASISTIO', 'NO ASISTIO', 'PENDIENTE') NOT NULL DEFAULT 'PENDIENTE',
		diagnostico :TEXT NULL,
		tratamiento  :TEXT NULL,
		notas :TEXT NULL,
        idCita :INT NULL
	)
    - idCita hace referencia a idCita de la tabla Citas
*/
CREATE TABLE consultas(
	idConsulta INT PRIMARY KEY AUTO_INCREMENT,
	estado ENUM('ASISTIO', 'NO ASISTIO', 'PENDIENTE') NOT NULL DEFAULT 'PENDIENTE',
	diagnostico TEXT NULL,
	tratamiento TEXT NULL,
	notas TEXT NULL, 
    idCita INT NULL,
    FOREIGN KEY (idCita) REFERENCES citas(idCita)
);

/*-------------------------------- CREACIÓN DE LA AUDITORIA (TABLAS Y TRIGGERS) --------------------------------*/
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

/*-------------------------------- USUARIO --------------------------------*/
-- Procedimiento almacenado registrarUsuario
-- Agrega un nuevo usuario a la tabla Usuarios
-- Si el usuario ya estaba registrado, lanza error
DELIMITER $$
CREATE PROCEDURE registrarUsuario(
	IN usuarioNuevo VARCHAR(100),
    IN contraUsuario VARCHAR(70),
    IN rolUsuario ENUM('PACIENTE', 'MEDICO')
)
BEGIN
	IF EXISTS (SELECT * FROM usuarios WHERE usuario = usuarioNuevo) THEN
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El usuario ya se encuentra registrado.";
	END IF;
    
    INSERT INTO usuarios(usuario, contrasenia, rol) VALUES (usuarioNuevo, contraUsuario, rolUsuario);
END $$
DELIMITER ;

/*-------------------------------- PACIENTE --------------------------------*/
-- Procedimiento almacenado registrarPaciente
-- Agregar un paciente (como paciente y como usuario)
-- Si el paciente ya estaba registrado, lanza error
DELIMITER $$
CREATE PROCEDURE registrarPaciente(
	IN nombrePaciente VARCHAR(50),
    IN apellidoPaternoPaciente VARCHAR(50),
    IN apellidoMaternoPaciente VARCHAR(50),
    IN fechaNacimientoPaciente DATE,
    IN emailPaciente VARCHAR(100),
    IN telefonoPaciente VARCHAR(10),
    IN callePaciente VARCHAR(100),
    IN numeroCasa VARCHAR(5),
	IN coloniaPaciente VARCHAR(100),
    IN codigoPostalPaciente VARCHAR(5),
    IN contraUsuario VARCHAR(70)
)
BEGIN
    -- Manejador de errores. Por si algo falla en agregarUsuario, la transacción no queda abierta
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
				-- Guarda el ultimo mensaje de error originado
		GET DIAGNOSTICS CONDITION 1 @errorMensaje = MESSAGE_TEXT;
        
        -- Deshace la transacción en caso de error
        ROLLBACK;
        -- Lanza error
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = @errorMensaje;
    END;
	
    -- Empezar transacción
	START TRANSACTION;
	-- Verificar si es un paciente nuevo
	IF EXISTS (SELECT * FROM pacientes WHERE email = emailPaciente) THEN
		-- Si ya existe, lanza error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente ya se encuentra registrado";
	END IF;
    
    -- Insertar registro en tabla Usuarios (usando registrarUsuario)
    CALL registrarUsuario(emailPaciente, contraUsuario, 'PACIENTE');
        
	-- Insertar registro en tabla Pacientes
    INSERT INTO pacientes(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, email, telefono, idUsuario) 
		VALUES (nombrePaciente, apellidoPaternoPaciente, apellidoMaternoPaciente, fechaNacimientoPaciente, emailPaciente, telefonoPaciente, last_insert_id());
        
	-- Insertar registro en tabla Direcciones
    INSERT INTO direcciones(calle, numero, colonia, codigoPostal, idPaciente)
		VALUES (callePaciente, numeroCasa, coloniaPaciente, codigoPostalPaciente, last_insert_id());
        
    -- Si todo fue bien, confirmar los cambios
    COMMIT;
END $$
DELIMITER ;

-- Procedimiento almacenado consultarPacientePorId
-- Consulta los datos asociados a un paciente en específico usando su id
-- Tiene todos los atributos de la entidad (menos Usuario)
DELIMITER $$
CREATE PROCEDURE consultarPacientePorId(
	IN id INT
)
BEGIN
	IF NOT EXISTS (SELECT idPaciente FROM pacientes WHERE idPaciente = id) THEN
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	END IF;
    
    SELECT
		p.idPaciente,
        p.nombre,
        p.apellidoPaterno,
        p.apellidoMaterno,
        p.fechaNacimiento,
        p.email,
        p.telefono,
        u.idUsuario,
        u.usuario,
        u.contrasenia,
        u.rol,
        d.idDireccion,
        d.calle,
        d.numero,
        d.colonia,
        d.codigoPostal
	FROM pacientes AS p
    INNER JOIN usuarios AS u
		ON p.idUsuario = u.idUsuario
	INNER JOIN direcciones AS d
		ON d.idPaciente = p.idPaciente
	WHERE p.idPaciente = id;
END $$
DELIMITER ;

-- Procedimiento almacenado consultarPacientePorEmail
-- Consulta los datos asociados a un paciente en específico usando su email
-- Tiene todos los atributos de la entidad (menos Usuario)
DELIMITER $$
CREATE PROCEDURE consultarPacientePorEmail(
	IN emailPaciente VARCHAR(100)
)
BEGIN
	SELECT
		p.idPaciente,
        p.nombre,
        p.apellidoPaterno,
        p.apellidoMaterno,
        p.fechaNacimiento,
        p.email,
        p.telefono,
        u.idUsuario,
        u.usuario,
        u.contrasenia,
        u.rol,
        d.idDireccion,
        d.calle,
        d.numero,
        d.colonia,
        d.codigoPostal
	FROM pacientes AS p
    INNER JOIN usuarios AS u
		ON p.idUsuario = u.idUsuario
	INNER JOIN direcciones AS d
		ON d.idPaciente = p.idPaciente
	WHERE p.email = emailPaciente;
END $$
DELIMITER ;

-- Procedimiento almacenado verPerfilPaciente
-- Consulta los datos de perfil de un paciente específico
DELIMITER $$
CREATE PROCEDURE verPerfilPaciente(
	IN emailPaciente VARCHAR(100)
)
BEGIN 
	IF NOT EXISTS (SELECT email FROM pacientes WHERE email = emailPaciente) THEN
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	END IF;
    
    SELECT * FROM vistaPerfilPaciente
    WHERE email = emailPaciente;
END $$
DELIMITER ;

-- Función calcularEdad
-- Calcula la edad a partir de una fecha de nacimiento
DELIMITER $$
CREATE FUNCTION calcularEdad(fechaNacimiento DATE)
RETURNS INT 
DETERMINISTIC
BEGIN
    RETURN TIMESTAMPDIFF(YEAR, fechaNacimiento, curdate());
END $$
DELIMITER ;

-- Vista vistaPerfilPaciente
-- Tabla con todos los datos que se muestran en el perfil del paciente
CREATE OR REPLACE VIEW vistaPerfilPaciente AS
    SELECT
        p.nombre,
        p.apellidoPaterno,
        p.apellidoMaterno,
        p.fechaNacimiento,
        (SELECT calcularEdad(p.fechaNacimiento)) AS edad,
        p.email,
        p.telefono,
        d.calle,
        d.numero,
        d.colonia,
        d.codigoPostal
	FROM pacientes AS p
    INNER JOIN usuarios AS u
		ON p.idUsuario = u.idUsuario
	INNER JOIN direcciones AS d
		ON d.idPaciente = p.idPaciente;

-- Procedimiento almacenado editarDatosPaciente
-- Actualiza los datos del paciente y su dirección
-- Solo es posible editar: 
	-- nombre
    -- apellidos
    -- fecha de nacimiento
    -- telefono
    -- direccion
    -- contraseña
DELIMITER $$
CREATE PROCEDURE editarDatosPaciente(
	IN emailPaciente VARCHAR(100),
	IN nombrePaciente VARCHAR(50),
    IN apellidoPaternoPaciente VARCHAR(50),
    IN apellidoMaternoPaciente VARCHAR(50),
    IN fechaNacimientoPaciente DATE,
    IN telefonoPaciente VARCHAR(10),
    IN callePaciente VARCHAR(100),
    IN numeroCasa VARCHAR(5),
    IN coloniaPaciente VARCHAR(100),
    IN codigoPostalPaciente VARCHAR(5),
    IN contraseniaNueva VARCHAR(70)
)
BEGIN
	DECLARE id INT;
    
	-- Manejo de errores
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
		-- Guarda el ultimo mensaje de error originado
		GET DIAGNOSTICS CONDITION 1 @errorMensaje = MESSAGE_TEXT;
        
        -- Deshace la transacción en caso de error
        ROLLBACK;
        -- Lanza error
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = @errorMensaje;
    END;
    
    -- Empezar transacción
	START TRANSACTION;
    -- Verificar si el paciente existe
	IF NOT EXISTS (SELECT idPaciente FROM pacientes WHERE email = emailPaciente) THEN
		-- Si no existe, lanza error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	ELSE
		-- Si sí existe, obtiene su id
		SET id = (SELECT idPaciente FROM pacientes WHERE email = emailPaciente);
	END IF;
    
    -- Verificar si tiene dirección asociada
    IF NOT EXISTS (SELECT idPaciente FROM direcciones WHERE idPaciente = id) THEN
		-- Si no existe, lanza error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no cuenta con dirección asociada";
	END IF;
    
    -- Verificar si tiene registro como usuario
    IF NOT EXISTS (SELECT idUsuario FROM usuarios WHERE usuario = emailPaciente) THEN
		-- Si no existe, lanza error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no está registrado como usuario";
	END IF;
	
    -- Actualizar los datos del paciente
	UPDATE pacientes 
	SET 
		nombre = nombrePaciente, 
		apellidoPaterno = apellidoPaternoPaciente, 
		apellidoMaterno = apellidoMaternoPaciente,
		fechaNacimiento = fechaNacimientoPaciente,
		telefono = telefonoPaciente
	WHERE email = emailPaciente;
	
    -- Actualizar la dirección del paciente
	UPDATE direcciones
	SET
		calle = callePaciente,
		numero = numeroCasa,
		colonia = coloniaPaciente,
		codigoPostal = codigoPostalPaciente
	WHERE idPaciente = id;
    
    -- Actualizar la contraseña
    UPDATE usuarios
    SET
		contrasenia = contraseniaNueva
	WHERE usuario = emailPaciente;
    
    -- Confirmar con commit
	COMMIT;
END $$
DELIMITER ;

-- Procedimiento almacenado editarDatosPaciente
-- Actualiza los datos del paciente y su dirección, menos contraseña
-- Solo es posible editar: 
	-- nombre
    -- apellidos
    -- fecha de nacimiento
    -- telefono
    -- direccion
DELIMITER $$
CREATE PROCEDURE editarDatosSinContraPaciente(
	IN emailPaciente VARCHAR(100),
	IN nombrePaciente VARCHAR(50),
    IN apellidoPaternoPaciente VARCHAR(50),
    IN apellidoMaternoPaciente VARCHAR(50),
    IN fechaNacimientoPaciente DATE,
    IN telefonoPaciente VARCHAR(10),
    IN callePaciente VARCHAR(100),
    IN numeroCasa VARCHAR(5),
    IN coloniaPaciente VARCHAR(100),
    IN codigoPostalPaciente VARCHAR(5)
)
BEGIN
	DECLARE id INT;
    
	-- Manejo de errores
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
		-- Guarda el ultimo mensaje de error originado
		GET DIAGNOSTICS CONDITION 1 @errorMensaje = MESSAGE_TEXT;
        
        -- Deshace la transacción en caso de error
        ROLLBACK;
        -- Lanza error
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = @errorMensaje;
    END;
    
    -- Empezar transacción
	START TRANSACTION;
    -- Verificar si el paciente existe
	IF NOT EXISTS (SELECT idPaciente FROM pacientes WHERE email = emailPaciente) THEN
		-- Si no existe, lanza error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	ELSE
		-- Si sí existe, obtiene su id
		SET id = (SELECT idPaciente FROM pacientes WHERE email = emailPaciente);
	END IF;
    
    -- Verificar si tiene dirección asociada
    IF NOT EXISTS (SELECT idPaciente FROM direcciones WHERE idPaciente = id) THEN
		-- Si no existe, lanza error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no cuenta con dirección asociada";
	END IF;
	
    -- Actualizar los datos del paciente
	UPDATE pacientes 
	SET 
		nombre = nombrePaciente, 
		apellidoPaterno = apellidoPaternoPaciente, 
		apellidoMaterno = apellidoMaternoPaciente,
		fechaNacimiento = fechaNacimientoPaciente,
		telefono = telefonoPaciente
	WHERE email = emailPaciente;
	
    -- Actualizar la dirección del paciente
	UPDATE direcciones
	SET
		calle = callePaciente,
		numero = numeroCasa,
		colonia = coloniaPaciente,
		codigoPostal = codigoPostalPaciente
	WHERE idPaciente = id;
    
    -- Confirmar con commit
	COMMIT;
END $$
DELIMITER ;

-- Procedimiento almacenado obtenerCitasActivasPaciente
-- Obtiene todas las citas del paciente que tienen como estado 'ACTIVA'
DELIMITER $$
CREATE PROCEDURE obtenerCitasActivasPaciente(
	IN emailPaciente VARCHAR(100))
BEGIN
	SELECT 
		c.idCita,
        c.fechaHoraInicio,
        c.estado AS estado_cita,
        c.folio,
        c.tipo,
        c.idPaciente,
        m.idMedico,
        m.nombre,
        m.apellidoPaterno,
        m.apellidoMaterno,
        m.especialidad,
        m.cedula,
        m.estado AS estado_medico,
        p.nombre,
        p.apellidoPaterno,
        p.apellidoMaterno,
        p.fechaNacimiento,
        p.email,
        p.telefono
    FROM citas AS c
	INNER JOIN medicos AS m
		ON c.idMedico = m.idMedico
	INNER JOIN pacientes AS p
		ON c.idPaciente = p.idPaciente
    WHERE emailPaciente = p.email
    AND c.estado = 'ACTIVA';
END $$
DELIMITER ;

/*-------------------------------- MEDICO --------------------------------*/
-- Procedimiento almacenado obtenerMedico
-- Obtiene la informacion de un medico (y usuario)
DELIMITER $$
CREATE PROCEDURE consultarMedico(
	IN cedulaMedico VARCHAR(8)
)
BEGIN 
	SELECT
		m.idMedico,
		m.nombre,
        m.apellidoPaterno,
        m.apellidoMaterno,
        m.especialidad,
        m.cedula,
        m.estado,
        u.idUsuario,
        u.usuario,
        u.contrasenia,
        u.rol
	FROM medicos AS m
    INNER JOIN usuarios AS u
		ON m.idUsuario = u.idUsuario
	WHERE u.usuario = cedulaMedico;
END $$
DELIMITER ;

-- Procedimiento consultarHorariosMedicoPorID
-- Consultar los horarios de un médico con un id específico
DELIMITER $$
CREATE PROCEDURE consultarHorariosMedicoPorID(
	IN id_Medico INT
)
BEGIN 
	SELECT 
		h.diaSemana, 
		h.horaEntrada, 
        h.horaSalida
	FROM horarios AS h
	WHERE h.idMedico = id_Medico;
END $$
DELIMITER ;

-- Función contarMedicosActivos
-- Cuenta la cantidad de médicos activos
DELIMITER $$
CREATE FUNCTION contarMedicosActivos()
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE cantidadMedicosActivos INT;
    
    SELECT COUNT(*)
    INTO cantidadMedicosActivos
    FROM medicos
    WHERE estado = "ACTIVO";
    
    RETURN cantidadMedicosActivos;
END $$
DELIMITER ;

-- Vista vistaMedicosCitasActivas
-- Muestra los médicos que tienen citas activas y cuántas citas son
CREATE OR REPLACE VIEW vistaMedicosCitasActivas AS
SELECT 
	m.idMedico, 
    m.cedula,
    m.estado,
    COUNT(c.idCita) AS citasActivas
FROM medicos AS m
LEFT JOIN citas AS c
	ON m.idMedico = c.idMedico AND c.estado = "ACTIVA"
GROUP BY m.idMedico, m.cedula, m.estado;

-- Función citasActivasMedico
-- Obtiene la cantidad de citas activas de un médico específico
DELIMITER $$
CREATE FUNCTION citasActivasMedico(cedulaMedico VARCHAR(8))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE cantidadCitasActivas INT;
    
    SELECT 
		citasActivas
	INTO cantidadCitasActivas
	FROM vistaMedicosCitasActivas
    WHERE cedula = cedulaMedico;
    
    RETURN cantidadCitasActivas;
END $$
DELIMITER ;

-- Procedimiento almacenado cambiarEstadoMedico
-- Cambia el estado de un médico (da de baja o alta)
DELIMITER $$
CREATE PROCEDURE cambiarEstadoMedico(
    IN cedulaMedico VARCHAR(8),
    IN nuevoEstado ENUM('ACTIVO', 'INACTIVO')
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
        GET DIAGNOSTICS CONDITION 1 @errorMensaje = MESSAGE_TEXT;
        ROLLBACK;
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = @errorMensaje;
    END;

    START TRANSACTION;
    
    -- Verificar si el médico existe antes de actualizar
    IF NOT EXISTS (SELECT 1 FROM medicos WHERE cedula = cedulaMedico) THEN
        SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El médico no existe";
    END IF;
    
    -- Actualizar el estado del médico
    UPDATE medicos SET estado = nuevoEstado WHERE cedula = cedulaMedico;
    
    COMMIT;
END $$
DELIMITER ;

-- Vista todasLasEspecialidades
-- Muestra todas las especialidades existentes de médicos
CREATE OR REPLACE VIEW todasLasEspecialidades AS 
SELECT DISTINCT
	especialidad
FROM medicos;

-- Vista todosLosMedicosConEspecialidad
-- Muestra todos los médicos que cuenten con especialidad
CREATE OR REPLACE VIEW todosLosMedicosConEspecialidad AS
SELECT 
	idMedico,
    nombre,
    apellidoPaterno,
    apellidoMaterno, 
    especialidad,
    cedula,
    estado,
    idUsuario
FROM medicos;

-- Procedimiento almacenado obtenerCitasPorMedico
-- Obtiene todas las citas activas de un médico
DELIMITER $$
CREATE PROCEDURE obtenerCitasPorMedico(
	IN cedulaMedico VARCHAR(8)
)
BEGIN
	SELECT *
    FROM consultas AS co
    INNER JOIN citas AS c ON co.idCita = c.idCita
    INNER JOIN medicos AS m ON c.idMedico = m.idMedico 
    INNER JOIN pacientes AS p ON p.idPaciente = c.idPaciente
    WHERE m.cedula = cedulaMedico
    AND c.estado = "ACTIVA";
END$$
DELIMITER ;

-- Procedimiento almacenado obtenerConsultasPorMedico
-- Obtiene todas las consultas asociadas a un médico
DELIMITER $$
CREATE PROCEDURE obtenerConsultasPorMedico(
	IN cedulaMedico VARCHAR(8)
)
BEGIN 
	SELECT 
		co.estado,
        IFNULL("N/A",co.diagnostico),
        IFNULL("N/A",co.tratamiento),
        c.fechaHoraInicio,
        IFNULL("N/A", c.folio),
        c.tipo,
        p.nombre,
        p.apellidoPaterno,
        IFNULL(" ",p.apellidoMaterno)
    FROM consultas AS co
    INNER JOIN citas AS c ON co.idCita = c.idCita
    INNER JOIN medicos AS m ON c.idMedico = m.idMedico 
    INNER JOIN pacientes AS p ON p.idPaciente = c.idPaciente
    WHERE m.cedula = cedulaMedico;
END$$
DELIMITER ;

-- Procedimiento almacenado primerMedicoDisponible
-- Obtiene a un médico activo disponible en la próxima media hora de hoy
-- Devuelve la cédula del médico seleccionado
DELIMITER $$
CREATE PROCEDURE primerMedicoDisponible(
	OUT cedulaMedicoDisponible VARCHAR(8))
BEGIN
	SELECT m.cedula
    INTO cedulaMedicoDisponible
	FROM horarios AS h
	INNER JOIN medicos AS m ON h.idMedico = m.idMedico
	WHERE
		h.diaSemana = DAYNAME(NOW())
		AND TIME(DATE_ADD(NOW(), INTERVAL 30 MINUTE)) BETWEEN h.horaEntrada AND h.horaSalida
		AND m.estado = 'ACTIVO'
		AND NOT EXISTS (
			SELECT 1
			FROM citas c
			WHERE c.idMedico = h.idMedico
			AND c.estado = 'ACTIVA'
			AND c.fechaHoraInicio <= DATE_ADD(NOW(), INTERVAL 30 MINUTE) 
			AND DATE_ADD(c.fechaHoraInicio, INTERVAL 30 MINUTE) >= NOW() -- Usar el cálculo de 30 minutos
		)
	ORDER BY RAND()
    LIMIT 1;
END $$
DELIMITER ;

/*-------------------------------- CITA --------------------------------*/
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
DELIMITER ;

/*-------------------------------- CONSULTA --------------------------------*/
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

/*-------------------------------- REGISTROS DE HORARIOS - USUARIOS - MEDICOS --------------------------------*/
-- Insertar usuarios (médicos)
INSERT INTO usuarios (usuario, contrasenia, rol) VALUES 
('11111111', 'medico111', 'MEDICO'),
('22222222', 'medico222', 'MEDICO'),
('33333333', 'medico333', 'MEDICO'),
('44444444', 'medico444', 'MEDICO'),
('55555555', 'medico555', 'MEDICO'),
('66666666', 'medico666', 'MEDICO'),
('77777777', 'medico777', 'MEDICO'),
('88888888', 'medico888', 'MEDICO'),
('99999999', 'medico999', 'MEDICO'),
('10101010', 'medico1010', 'MEDICO');

-- Insertar médicos
INSERT INTO medicos (nombre, apellidoPaterno, apellidoMaterno, especialidad, cedula, estado, idUsuario) VALUES 
('Ricardo', 'Castillo', 'Reyes', 'Cardiología', '11111111', 'ACTIVO', 1),
('Fernanda', 'Lozano', 'Garcia', 'Dermatología', '22222222', 'ACTIVO', 2),
('Alejandro', 'Ortega', 'Molina', 'Pediatría', '33333333', 'ACTIVO', 3),
('Mariana', 'Ruiz', 'Santos', 'Neurología', '44444444', 'ACTIVO', 4),
('Luis', 'Gomez', 'Navarro', 'Ginecología', '55555555', 'ACTIVO', 5),
('Gabriela', 'Vargas', 'Peña', 'Oftalmología', '66666666', 'ACTIVO', 6),
('Hugo', 'Ramirez', 'Diaz', 'Traumatología', '77777777', 'ACTIVO', 7),
('Diana', 'Hernandez', 'Fuentes', 'Psiquiatría', '88888888', 'ACTIVO', 8),
('Manuel', 'Salinas', 'Herrera', 'Ortopedia', '99999999', 'ACTIVO', 9),
('Clara', 'Jimenez', 'Mendez', 'Endocrinología', '10101010', 'ACTIVO', 10);

-- Insertar horarios para médicos
INSERT INTO horarios (diaSemana, horaEntrada, horaSalida, idMedico) VALUES
('MONDAY', '08:00:00', '14:00:00', 1),
('TUESDAY', '10:00:00', '16:00:00', 2),
('WEDNESDAY', '09:00:00', '15:00:00', 3),
('THURSDAY', '11:00:00', '17:00:00', 4),
('FRIDAY', '08:30:00', '14:30:00', 5),
('SATURDAY', '09:00:00', '13:00:00', 6);