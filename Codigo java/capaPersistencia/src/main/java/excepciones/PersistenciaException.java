/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 * Clase PersistenciaException.
 * Utilizar excepciones personalizadas para capa de persistencia
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @autor 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PersistenciaException extends Exception {
    
    /**
     * Constructor de la clase.
     * @param message mensaje personaizado
     */
    public PersistenciaException(String message) {
        super(message);
    }
    
    /**
     * Constructor de la clase.
     * @param message mensaje personalizado
     * @param cause causa de la excepción
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}