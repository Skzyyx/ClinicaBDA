/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Medico;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz IMedicoDAO.
 * Define los métodos para el manejo de médicos en la base de datos.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public interface IMedicoDAO {
    
    /**
     * Registra un nuevo médico en la base de datos.
     * 
     * @param medico Objeto Medico con los datos del nuevo médico.
     * @return true si el médico se da de alta exitosamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean darAltaMedico(Medico medico) throws PersistenciaException;
    
    /**
     * Elimina un médico de la base de datos.
     * 
     * @param medico Objeto Medico con los datos del médico a dar de baja.
     * @return true si el médico se da de baja exitosamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean darBajaMedico(Medico medico) throws PersistenciaException;
    
    public List<Medico> obtenerMedicos() throws PersistenciaException;
    
    public List<String> obtenerEspecialidades() throws PersistenciaException;
    
    public Medico obtenerMedico(String cedula) throws PersistenciaException;
            
}

