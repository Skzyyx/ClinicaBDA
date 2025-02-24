/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Cita;
import entidades.Consulta;
import entidades.Horario;
import entidades.Medico;
import excepciones.PersistenciaException;
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
     * Cambia el estado de un médico específico (da de baja o alta).
     * @param cedula Cédula profesional del médico.
     * @param nuevoEstado Nuevo estado para cambiar.
     * @return True si se cambió el estado, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar cambiar el estado.
     */
    public boolean cambiarEstadoMedico(String cedula, String nuevoEstado) throws PersistenciaException;
    
    /**
     * Obtiene los registros de todos los médicos.
     * @return Lista con los registros de los médicos encontrados.
     * @throws PersistenciaException Si hubo un error al consultar los médicos.
     */
    public List<Medico> obtenerMedicos() throws PersistenciaException;
    
    /**
     * Obtiene todas las especialidades de los médicos.
     * @return Lista con las especialidades encontradas.
     * @throws PersistenciaException Si hubo un error al consultar las especialidades.
     */
    public List<String> obtenerEspecialidades() throws PersistenciaException;
    
    /**
     * Obtiene el registro de un médico específico.
     * @param cedula Cédula profesional a consultar.
     * @return Registro del médico si se encontró, null en caso contrario.
     * @throws PersistenciaException Si hubo un error al consultar el médico.
     */
    public Medico obtenerMedico(String cedula) throws PersistenciaException;
    
    /**
     * Obtiene los horarios asociados a un médico específico.
     * @param id Id del médico a consultar.
     * @return Lista con los horarios del médico.
     * @throws PersistenciaException Si hubo un error al consultar los horarios.
     */
    public List<Horario> obtenerHorariosMedicoPorID (int id) throws PersistenciaException;
    
    /**
     * Cuenta la cantidad de médicos que se encuentren activos.
     * @return Cantidad de médicos con estado "ACTIVO".
     * @throws excepciones.PersistenciaException Si hubo un error al contar médicos.
     */
    public int contarMedicosActivos() throws PersistenciaException;
    
    /**
     * Verifica si un médico específico tiene citas activas.
     * @param cedula Cédula profesional del médico a consultar.
     * @return True si tiene citas activas, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al consultar.
     */
    public boolean tieneCitasActivas(String cedula) throws PersistenciaException;
    
    public List<Cita> obtenerCitasPorMedico(Medico medico) throws PersistenciaException;
    
    public List<Consulta> obtenerConsultasPorMedico(String cedula) throws PersistenciaException;
    
    public String obtenerPrimerMedicoDisponible() throws PersistenciaException;
}