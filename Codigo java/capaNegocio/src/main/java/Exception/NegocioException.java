/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 * Clase NegocioException.
 * Utilizar excepciones personalizadas para capa de negocio
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class NegocioException extends Exception {

    /**
     * Constructor de la clase.
     * @param message mensaje personaizado
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Constructor de la clase.
     * @param message mensaje personalizado
     * @param cause causa de la excepción
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
