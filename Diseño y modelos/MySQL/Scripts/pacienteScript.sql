USE clinicabda;

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
-- Consulta los datos asociados a un paciente en específico
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

-- Procedimiento almacenado verPerfilPaciente
-- Consulta los datos de perfil de un paciente específico
DELIMITER $$
CREATE PROCEDURE verPerfilPaciente(
	IN id INT
)
BEGIN 
	IF NOT EXISTS (SELECT idPaciente FROM pacientes WHERE idPaciente = id) THEN
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	END IF;
    
    SELECT * FROM vistaPerfilPaciente
    WHERE idPaciente = id;
END $$
DELIMITER ;

-- Vista vistaPerfilPaciente
-- Tabla con todos los datos que se muestran en el perfil del paciente
CREATE OR REPLACE VIEW vistaPerfilPaciente AS
    SELECT
		p.idPaciente,
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
		ON d.idPaciente = p.idPaciente
	WHERE p.idPaciente = d.idPaciente;
    
DELIMITER $$

-- Procedimiento almacenado editarDatosPaciente
-- Actualiza los datos del paciente y su dirección
-- Solo es posible editar: 
	-- nombre
    -- apellidos
    -- fecha de nacimiento
    -- telefono
    -- direccion
DELIMITER $$
CREATE PROCEDURE editarDatosPaciente(
	IN id INT,
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
	IF NOT EXISTS (SELECT idPaciente FROM pacientes WHERE idPaciente = id) THEN
		-- Si no existe, lanza error
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "El paciente no existe";
	END IF;
	
    -- Actualizar los datos del paciente
	UPDATE pacientes 
	SET 
		nombre = nombrePaciente, 
		apellidoPaterno = apellidoPaternoPaciente, 
		apellidoMaterno = apellidoMaternoPaciente,
		fechaNacimiento = fechaNacimientoPaciente,
		telefono = telefonoPaciente
	WHERE idPaciente = id;
	
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
SELECT calcularEdad("2005-02-16");
SELECT * FROM vistaPerfilPaciente;
CALL consultarPacientePorId(23);
CALL verPerfilPaciente(10);