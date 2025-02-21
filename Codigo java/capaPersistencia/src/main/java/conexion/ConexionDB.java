/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ConexionDB.
 * Crear y maneja la conexión con la base de datos
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class ConexionDB implements IConexion {
    
    /** URL de conexión a la base de datos MySQL. */
    final String URL = "jdbc:mysql://localhost:3306/clinicabda";
    
    /** Usuario para la autenticación en la base de datos. */
    final String USER = "root";
    
    /** Contraseña para la autenticación en la base de datos. */
    final String PASS = "94972Isl";
    
    /**
     * Método crearConexion().
     * Crea la conexión con la base de datos.
     * @return objeto Connection con la conexion creada.
     * @throws PersistenciaException si no se creó la conexión.
     */
    @Override
    public Connection crearConexion() throws PersistenciaException {
        try {
            //Crea la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            
            return conexion;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            //Si ocurrió un error en el proceso
            throw new PersistenciaException("Error al conectarse a la base de datos.");
        }
    } 
}