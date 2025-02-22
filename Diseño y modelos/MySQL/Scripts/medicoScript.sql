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
		ON m.idUsuario = u.idUsuario;
END $$
DELIMITER ;

-- Procedimiento para obtener Medico por ID
DELIMITER &&
CREATE PROCEDURE consultarHorariosMedicoPorID(
IN id_Medico int)

BEGIN 
SELECT h.diaSemana, h.horaEntrada, h.horaSalida
FROM horarios as h
WHERE h.idMedico=id_Medico;

END &&
DELIMITER ;