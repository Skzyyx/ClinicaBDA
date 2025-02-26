/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Consulta;
import excepciones.PersistenciaException;

/**
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public interface IConsultaDAO {
    
    /**
     * Obtiene el estado de una consulta a partir del identificador de la cita.
     * 
     * @param idCita Identificador único de la cita asociada a la consulta.
     * @return Estado de la consulta como cadena, o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public String obtenerEstadoConsulta(int idCita) throws PersistenciaException;
    
    /**
     * Obtiene la información completa de una consulta utilizando el identificador de la cita.
     * 
     * @param idCita Identificador único de la cita asociada a la consulta.
     * @return Objeto Consulta con la información recuperada, o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public Consulta obtenerConsultaPorIdCita(int idCita) throws PersistenciaException;
    
    /**
     * Actualiza los datos de una consulta existente en la base de datos.
     * 
     * @param consulta Objeto Consulta que contiene la información actualizada.
     * @return true si la actualización se realizó correctamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean editarDatosConsulta(Consulta conuslta) throws PersistenciaException;
}
