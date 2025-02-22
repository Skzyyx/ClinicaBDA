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
DELIMITER &&
CREATE PROCEDURE consultarHorariosMedicoPorID(
IN id_Medico int)

BEGIN 
SELECT h.diaSemana, h.horaEntrada, h.horaSalida
FROM horarios as h
WHERE h.idMedico=id_Medico;

END &&
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