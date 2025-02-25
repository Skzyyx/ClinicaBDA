/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Cita;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase CitaDAO. Implementa la interfaz ICitaDAO y proporciona métodos para la
 * gestión de citas en la base de datos. Se encarga de registrar citas
 * programadas y de emergencia.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class CitaDAO implements ICitaDAO {

    /**
     * Conexión a la base de datos.
     */
    private final IConexion conexion;

    /**
     * Constructor de la clase CitaDAO.
     *
     * @param conexion Objeto que maneja la conexión a la base de datos.
     */
    public CitaDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Registra una cita programada en la base de datos.
     *
     * @param cita Objeto Cita que contiene la información de la cita a
     * registrar.
     * @return true si la cita se registra exitosamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error al registrar la cita en
     * la base de datos.
     */
    @Override
    public boolean registrarCitaProgramada(Cita cita) throws PersistenciaException {
        System.out.println(cita.getPaciente().getEmail());
        String sentenciaSQL = "CALL registrarCita(?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sentenciaSQL)) {

            cs.setTimestamp(1, Timestamp.valueOf(cita.getFechaHoraInicio()));
            cs.setString(2, cita.getFolio());
            cs.setString(3, "PROGRAMADA");
            cs.setString(4, cita.getPaciente().getEmail());
            cs.setInt(5, cita.getMedico().getIdMedico());

            cs.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex.getMessage());
        }
    }

    /**
     * Registra una cita de emergencia en la base de datos.
     *
     * @param cita Objeto Cita que contiene la información de la cita de
     * emergencia.
     * @return true si la cita se registra exitosamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error al registrar la cita en
     * la base de datos.
     */
    @Override
    public boolean registrarCitaEmergencia(Cita cita) throws PersistenciaException {
        String sentenciaSQL = "CALL registrarCita(?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sentenciaSQL)) {
            cs.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            cs.setString(2, cita.getFolio());
            cs.setString(3, "EMERGENCIA");
            cs.setString(4, cita.getPaciente().getEmail());
            cs.setInt(5, cita.getMedico().getIdMedico());

            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo registrar la cita de emergencia.");
        }
    }

    /**
     * Verifica si existe una cita en la base de datos para un médico en un
     * horario específico.
     *
     * @param fechaHoraInicio Timestamp que representa la fecha y hora de la
     * cita.
     * @param idMedico Identificador único del médico.
     * @return true si la cita existe, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error al verificar la
     * existencia de la cita.
     */
    @Override
    public boolean verificarCitaExiste(Timestamp fechaHoraInicio, int idMedico) throws PersistenciaException {

        String sentenciaSQL = "CALL verificarCitaExiste(?, ?, ?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sentenciaSQL)) {

            cs.setTimestamp(1, fechaHoraInicio);
            cs.setInt(2, idMedico);

            cs.registerOutParameter(3, Types.BOOLEAN);

            cs.execute();

            return cs.getBoolean(3);

        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error a obtener el resultado.");
        }
    }

    /**
     * Cancela una cita existente en la base de datos.
     *
     * @param cita Objeto Cita que contiene la información de la cita a
     * cancelar.
     * @return true si la cita se cancela exitosamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error al cancelar la cita en
     * la base de datos.
     */
    @Override
    public boolean cancelarCita(Cita cita) throws PersistenciaException {
        System.out.println(cita.toString());

        String sql = "CALL cancelarCita(?)";

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, cita.getIdCita());

            cs.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al cancelar la cita.");
        }
    }

    /**
     * Obtiene una cita de emergencia de la base de datos a partir de su folio.
     *
     * @param folio Folio único de la cita de emergencia.
     * @return Objeto Cita si la cita se encuentra en la base de datos, null en
     * caso contrario.
     * @throws PersistenciaException Si ocurre un error al obtener la cita de
     * emergencia.
     */
    @Override
    public Cita obtenerCitaEmergencia(String folio) throws PersistenciaException {
        String sentenciaSQL = "CALL obtenerCitaEmergencia(?)";
        Cita cita = null;

        try (Connection con = conexion.crearConexion(); CallableStatement cs = con.prepareCall(sentenciaSQL)) {

            cs.setString(1, folio);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                Medico medico = new Medico();
                medico.setNombre(rs.getString("nombre"));
                medico.setApellidoPaterno(rs.getString("apellidoPaterno"));
                medico.setApellidoMaterno(rs.getString("apellidoMaterno"));
                medico.setCedula(rs.getString("cedula"));

                cita = new Cita();
                cita.setFolio(rs.getString("folio"));
                cita.setFechaHoraInicio(rs.getTimestamp("fechaHoraInicio").toLocalDateTime());
                cita.setMedico(medico);
            }
            return cita;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo obtener la consulta");
        }
    }

}
