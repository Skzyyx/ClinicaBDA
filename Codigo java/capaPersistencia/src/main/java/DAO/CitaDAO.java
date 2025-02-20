/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Cita;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase CitaDAO.
 * Implementa la interfaz ICitaDAO y proporciona métodos para la gestión de citas en la base de datos.
 * Se encarga de registrar citas programadas y de emergencia.
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
     * @param cita Objeto Cita que contiene la información de la cita a registrar.
     * @return true si la cita se registra exitosamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error al registrar la cita en la base de datos.
     */
    @Override
    public boolean registrarCitaProgramada(Cita cita) throws PersistenciaException {
        
        String sentenciaSQL = "CALL registrarCita(?, ?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion();
             CallableStatement cs = con.prepareCall(sentenciaSQL)) {
            
            cs.setTimestamp(1, Timestamp.valueOf(cita.getFechaHoraInicio()));
            cs.setString(2, cita.getFolio());
            cs.setString(3, "PROGRAMADA");
            cs.setInt(4, cita.getPaciente().getIdPaciente());
            cs.setInt(5, cita.getMedico().getIdMedico());
            
            cs.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo registrar la cita.");
        }
    }

    /**
     * Registra una cita de emergencia en la base de datos.
     * Actualmente no está implementado.
     * 
     * @param cita Objeto Cita que contiene la información de la cita de emergencia.
     * @return false ya que la funcionalidad no está soportada aún.
     * @throws PersistenciaException Si ocurre un error al intentar registrar la cita.
     */
    @Override
    public boolean registrarCitaEmergencia(Cita cita) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Método aún no implementado
    }
}
