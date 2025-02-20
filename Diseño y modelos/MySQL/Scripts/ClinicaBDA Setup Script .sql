CREATE DATABASE ClinicaBDA;
USE ClinicaBDA;

/*
	Usuarios(
		idUsuario :INT PRIMARY KEY AUTO_INCREMENT,
		usuario :VARCHAR(100) UNIQUE NOT NULL,
		contraseña :VARCHAR(60) NOT NULL,
		rol :ENUM(‘PACIENTE’, ‘MEDICO’) NOT NULL
	)
*/
CREATE TABLE usuarios(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(100) UNIQUE NOT NULL,
    contrasenia VARCHAR(60) NOT NULL,
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
