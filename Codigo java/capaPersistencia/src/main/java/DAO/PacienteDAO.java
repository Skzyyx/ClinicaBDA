/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Paciente;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skyro
 */
public class PacienteDAO implements IPacienteDAO {
    
    IConexion conexion;

    public PacienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        
        // Sentencia SQL
        String sql = "CALL registrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            
        
            // Se setean los datos personales del paciente
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidoPaterno());
            ps.setString(3, paciente.getApellidoMaterno());
            ps.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));
            ps.setString(5, paciente.getEmail());
            ps.setString(6, paciente.getTelefono());
            
            // Se setean los datos de dirección del paciente
            ps.setString(7, paciente.getDireccion().getCalle());
            ps.setString(8, paciente.getDireccion().getNumero());
            ps.setString(9, paciente.getDireccion().getColonia());
            ps.setString(10, paciente.getDireccion().getCodigoPostal());
                
            // Se setean los datos de perfil del paciente
            ps.setString(11, paciente.getUsuario().getContrasenia());
            
            // Se ejecuta la sentencia SQL
            ps.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo registrar el paciente.");
        }
    }
}
