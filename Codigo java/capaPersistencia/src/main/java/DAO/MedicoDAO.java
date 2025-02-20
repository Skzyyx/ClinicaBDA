/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la interfaz IMedicoDAO.
 * Gestiona el alta y baja de médicos en la base de datos.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class MedicoDAO implements IMedicoDAO {
    
    private final IConexion conexion;

    /**
     * Constructor que recibe una conexión a la base de datos.
     * 
     * @param conexion Objeto de tipo IConexion para gestionar la conexión con la base de datos.
     */
    public MedicoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Da de alta a un médico en la base de datos.
     * 
     * @param medico Objeto Medico con los datos del médico a registrar.
     * @return true si la operación se realizó con éxito, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean darAltaMedico(Medico medico) throws PersistenciaException {
        
        // Sentencia SQL para llamar al procedimiento almacenado
        String sentenciaSQL = "CALL darAltaMedico(?)";
        
        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(sentenciaSQL)) {
            
            // Se establece el ID del médico a dar de alta
            cs.setInt(1, medico.getIdMedico());
            
            // Se ejecuta la consulta
            cs.executeUpdate();
            
            // Si llega aquí, el médico fue dado de alta con éxito.
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo dar de alta al médico.");
        }
    }

    /**
     * Da de baja a un médico en la base de datos.
     * 
     * @param medico Objeto Medico con los datos del médico a eliminar.
     * @return true si la operación se realizó con éxito, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean darBajaMedico(Medico medico) throws PersistenciaException {
        
        // Sentencia SQL para llamar al procedimiento almacenado
        String sentenciaSQL = "CALL darBajaMedico(?)";
        
        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(sentenciaSQL)) {
            
            // Se establece el ID del médico a dar de baja
            cs.setInt(1, medico.getIdMedico());
            
            // Se ejecuta la consulta
            cs.executeUpdate();
            
            // Si llega aquí, el médico fue dado de baja con éxito.
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo dar de baja al médico.");
        }
    }
}

