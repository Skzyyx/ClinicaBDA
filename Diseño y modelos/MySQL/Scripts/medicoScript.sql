USE clinicabda;

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

-- Procedimiento para obtener todas las citas activas de un medico
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