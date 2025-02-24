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
 *
 * @author Isabel
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

    @Override
    public String obtenerEstadoConsulta(int idCita) throws PersistenciaException {
       
        String sql = "SELECT obtenerEstadoConsulta(?)";
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareCall(sql)) {
        
            ps.setInt(1, idCita);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        }   catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener el estado de la consulta.");
        }
    }

    @Override
    public Consulta obtenerConsultaPorIdCita(int idCita) throws PersistenciaException {
        
        String sql = "CALL obtenerConsultaPorIdCita(?)";
        
        try (Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, idCita);
            
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                Direccion direccion = new Direccion(
                        rs.getInt("idDireccion"),
                        rs.getString("calle"),
                        rs.getString("numero"),
                        rs.getString("colonia"),
                        rs.getString("codigoPostal")
                );
                
                Paciente paciente  = new Paciente(
                        rs.getInt("idPaciente"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getDate("fechaNacimiento").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        null,
                        direccion
                );
                
                Cita cita = new Cita(
                        rs.getInt("idCita"),
                        rs.getTimestamp("fechaHoraInicio").toLocalDateTime(),
                        rs.getString("c.estado"),
                        rs.getString("folio"),
                        rs.getString("tipo"),
                        paciente,
                        null
                );
                
                return new Consulta(
                        rs.getInt("idConsulta"),
                        rs.getString("estado"), 
                        rs.getString("diagnostico"), 
                        rs.getString("tratamiento"), 
                        rs.getString("notas"), 
                        cita);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo obtener la consulta");
        }
    }
    
    
}
