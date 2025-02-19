/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Paciente;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skyro
 */
public class PacienteDAO implements IPacienteDAO {
    private static final Logger logger = Logger.getLogger(Paciente.class.getName());
    
    /** Conexión a utilizar */
    IConexion conexion;
    
    /**
     * Constructor que inicializa todos los atributos de la clase. 
     * @param conexion Conexión a utlizar
     */
    public PacienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Registra un nuevo paciente.
     * @param paciente Objeto paciente a registrar.
     * @return True si se registró, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar registrar el paciente.
     */
    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        
        // Sentencia SQL
        String sentenciaSQL = "CALL registrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                CallableStatement cb = con.prepareCall(sentenciaSQL)) {
        
            // Se setean los datos personales del paciente
            cb.setString(1, paciente.getNombre());
            cb.setString(2, paciente.getApellidoPaterno());
            cb.setString(3, paciente.getApellidoMaterno());
            cb.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));
            cb.setString(5, paciente.getEmail());
            cb.setString(6, paciente.getTelefono());
            
            // Se setean los datos de dirección del paciente
            cb.setString(7, paciente.getDireccion().getCalle());
            cb.setString(8, paciente.getDireccion().getNumero());
            cb.setString(9, paciente.getDireccion().getColonia());
            cb.setString(10, paciente.getDireccion().getCodigoPostal());
                
            // Se setean los datos de perfil del paciente
            cb.setString(11, paciente.getUsuario().getContrasenia());
            
            // Se ejecuta el procedimiento
            cb.executeUpdate();
            
            // Si llega aquí, la inserción fue exitosa
            return true;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al registrar paciente", ex);
            throw new PersistenciaException("No se pudo registrar el paciente.");
        }
    }
}