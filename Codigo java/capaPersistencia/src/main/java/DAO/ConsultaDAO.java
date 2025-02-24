/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import excepciones.PersistenciaException;
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
    
    
}
