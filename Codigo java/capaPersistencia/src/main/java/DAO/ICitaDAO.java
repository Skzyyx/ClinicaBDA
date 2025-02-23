/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Cita;
import excepciones.PersistenciaException;
import java.sql.Timestamp;

/**
 * Interfaz ICitaDAO.
 * Define los métodos para el manejo de citas en la base de datos.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public interface ICitaDAO {
    
    /**
     * Registra una cita programada en la base de datos.
     * 
     * @param cita Objeto Cita con los datos de la cita programada.
     * @return true si la cita se registra exitosamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean registrarCitaProgramada(Cita cita) throws PersistenciaException;
    
    /**
     * Registra una cita de emergencia en la base de datos.
     * 
     * @param cita Objeto Cita con los datos de la cita de emergencia.
     * @return true si la cita se registra exitosamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean registrarCitaEmergencia(Cita cita) throws PersistenciaException;
    
    public boolean verificarCitaExiste(Timestamp fechaHoraInicio, int idMedico) throws PersistenciaException;
    
    
    
}

