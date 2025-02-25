/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Cita;
import excepciones.PersistenciaException;
import java.sql.Timestamp;

/**
 * Interfaz ICitaDAO. Define los métodos para el manejo de citas en la base de
 * datos.
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
     * @return true si la cita se registra exitosamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean registrarCitaProgramada(Cita cita) throws PersistenciaException;

    /**
     * Registra una cita de emergencia en la base de datos.
     *
     * @param cita
     * @return true si la cita se registra exitosamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean registrarCitaEmergencia(Cita cita) throws PersistenciaException;

    /**
     * Verifica si existe una cita en la base de datos para un médico en un
     * horario específico.
     *
     * @param fechaHoraInicio Timestamp que representa la fecha y hora de la
     * cita.
     * @param idMedico Identificador único del médico.
     * @return true si la cita existe, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la verificación.
     */
    public boolean verificarCitaExiste(Timestamp fechaHoraInicio, int idMedico) throws PersistenciaException;

    /**
     * Cancela una cita existente en la base de datos.
     *
     * @param cita Objeto Cita que contiene la información de la cita a
     * cancelar.
     * @return true si la cita se cancela exitosamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean cancelarCita(Cita cita) throws PersistenciaException;

    /**
     * Obtiene una cita de emergencia de la base de datos a partir de su folio.
     *
     * @param folio Folio único de la cita de emergencia.
     * @return Objeto Cita si se encuentra la cita, o null en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public Cita obtenerCitaEmergencia(String folio) throws PersistenciaException;

}