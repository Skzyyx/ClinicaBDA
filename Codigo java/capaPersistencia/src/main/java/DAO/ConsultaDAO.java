/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Cita;
import entidades.Consulta;
import entidades.Direccion;
import entidades.Paciente;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la interfaz IConsultaDAO para gestionar las consultas
 * médicas en la base de datos. Proporciona métodos para obtener el estado de
 * una consulta, recuperar información detallada de una consulta a partir del
 * identificador de la cita y actualizar los datos de una consulta existente.
 *
 * Esta clase interactúa con la base de datos mediante procedimientos
 * almacenados y consultas preparadas para garantizar eficiencia y seguridad en
 * la manipulación de datos.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class ConsultaDAO implements IConsultaDAO {

    /**
     * Conexión a la base de datos.
     */
    private final IConexion conexion;

    /**
     * Constructor de la clase ConsultaDAO.
     *
     * @param conexion Objeto que maneja la conexión a la base de datos.
     */
    public ConsultaDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Obtiene el estado de una consulta a partir del identificador de la cita.
     *
     * @param idCita Identificador único de la cita asociada a la consulta.
     * @return Estado de la consulta en forma de cadena, o null si no se
     * encuentra.
     * @throws PersistenciaException Si ocurre un error al obtener el estado de
     * la consulta.
     */
    @Override
    public String obtenerEstadoConsulta(int idCita) throws PersistenciaException {

        String sql = "SELECT obtenerEstadoConsulta(?)";

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareCall(sql)) {

            ps.setInt(1, idCita);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener el estado de la consulta.");
        }
    }

    /**
     * Obtiene la información completa de una consulta a partir del
     * identificador de la cita.
     *
     * Este método recupera datos de la consulta, así como la información del
     * paciente y su dirección asociada a partir de la cita correspondiente.
     *
     * @param idCita Identificador único de la cita.
     * @return Objeto Consulta con toda la información obtenida, o null si no se
     * encuentra.
     * @throws PersistenciaException Si ocurre un error al obtener la consulta.
     */
    @Override
    public Consulta obtenerConsultaPorIdCita(int idCita) throws PersistenciaException {

        String sql = "CALL obtenerConsultaPorIdCita(?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idCita);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                Direccion direccion = new Direccion(
                        rs.getInt("d.idDireccion"),
                        rs.getString("d.calle"),
                        rs.getString("d.numero"),
                        rs.getString("d.colonia"),
                        rs.getString("d.codigoPostal")
                );

                Paciente paciente = new Paciente(
                        rs.getInt("p.idPaciente"),
                        rs.getString("p.nombre"),
                        rs.getString("p.apellidoPaterno"),
                        rs.getString("p.apellidoMaterno"),
                        rs.getDate("p.fechaNacimiento").toLocalDate(),
                        rs.getString("p.email"),
                        rs.getString("p.telefono"),
                        null,
                        direccion
                );

                Cita cita = new Cita(
                        rs.getInt("c.idCita"),
                        rs.getTimestamp("c.fechaHoraInicio").toLocalDateTime(),
                        rs.getString("c.estado"),
                        rs.getString("c.folio"),
                        rs.getString("c.tipo"),
                        paciente,
                        null
                );

                return new Consulta(
                        rs.getInt("co.idConsulta"),
                        rs.getString("co.estado"),
                        rs.getString("co.diagnostico"),
                        rs.getString("co.tratamiento"),
                        rs.getString("co.notas"),
                        cita);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo obtener la consulta");
        }
    }

    /**
     * Edita los datos de una consulta existente en la base de datos.
     *
     * Este método actualiza el diagnóstico, tratamiento y notas de la consulta
     * especificada.
     *
     * @param consulta Objeto Consulta que contiene la información actualizada.
     * @return true si la actualización se realizó correctamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error al actualizar los datos
     * de la consulta.
     */
    @Override
    public boolean editarDatosConsulta(Consulta consulta) throws PersistenciaException {

        String sql = "CALL editarDatosConsulta(?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, consulta.getIdConsulta());
            cs.setString(2, consulta.getDiagnostico());
            cs.setString(3, consulta.getTratamiento());
            cs.setString(4, consulta.getNotas());

            return cs.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al actualizar los datos de la consulta");
        }
    }

}
