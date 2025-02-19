/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import excepciones.PersistenciaException;
import java.sql.Connection;

/**
 * Interfaz IConexion.
 * Interfaz para implementar en clases de conexión
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public interface IConexion {
    
    /**
     * Método crearConexion(). Crea la conexión con la base de datos.
     * @return objeto Connection con la conexion creada
     * @throws PersistenciaException si no fue posible conectarse
     */
    public Connection crearConexion() throws PersistenciaException;
}