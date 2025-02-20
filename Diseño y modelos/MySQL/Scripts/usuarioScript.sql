USE clinicabda;

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