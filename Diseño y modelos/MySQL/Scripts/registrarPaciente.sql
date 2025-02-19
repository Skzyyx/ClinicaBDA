USE clinicabda;

-- Procedimiento almacenado registrarUsuario
-- Agrega un nuevo usuario a la tabla Usuarios
-- Si el usuario ya estaba registrado, lanza error
DELIMITER $$
CREATE PROCEDURE registrarUsuario(
	IN usuarioNuevo VARCHAR(100),
    IN contraUsuario VARCHAR(50),
    IN rolUsuario ENUM('PACIENTE', 'MEDICO')
)
BEGIN
	IF EXISTS (SELECT * FROM usuarios WHERE usuario = usuarioNuevo) THEN
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El usuario ya se encuentra registrado.";
	END IF;
    
    INSERT INTO usuarios(usuario, contrasenia, rol) VALUES (usuarioNuevo, contraUsuario, rolUsuario);
END $$
DELIMITER ;

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
    IN contraUsuario VARCHAR(50)
)
BEGIN
    -- Manejador de errores. Por si algo falla en agregarUsuario, la transacción no queda abierta
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION 
    BEGIN
		-- Deshacer la transacción en caso de error
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Se ha producido un error en el procedimiento almacenado.";
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

-- Pruebas
CALL registrarPaciente(
	"Juan",
    "Perez",
    "Lopez",
    "2000-10-24",
    "example@gmail.com",
    "6444239710",
    "Tabasco",
    "1654",
    "Campestre",
    "85120",
    "segura123"
);