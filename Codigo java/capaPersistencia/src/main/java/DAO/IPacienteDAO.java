/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entidades.Paciente;
import excepciones.PersistenciaException;

/**
 * Interfaz IPacienteDAO.
 * Interfaz para implementar en clases de DAO.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public interface IPacienteDAO {
    
    /**
     * Registra un nuevo paciente.
     * @param paciente Objeto paciente a registrar.
     * @return True si se registró, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar registrar el paciente.
     */
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException;
    
    /**
     * Consulta un paciente por su id.
     * @param id Id del paciente a consultar.
     * @return Paciente si se encontró, o null.
     * @throws PersistenciaException Si hubo un error al intentar consultar el paciente.
     */
    public Paciente consultarPacientePorId(int id) throws PersistenciaException;
    
    /**
     * Consulta un paciente por su id.
     * @param email Email del paciente a consultar.
     * @return Paciente si se encontró, o null.
     * @throws PersistenciaException Si hubo un error al intentar consultar el paciente.
     */
    public Paciente consultarPacientePorEmail(String email) throws PersistenciaException;
    
    /**
     * Edita los datos de un paciente.
     * Solo se permite editar:
     * 	-- nombre
     *  -- apellidos
     *  -- fecha de nacimiento
     *  -- telefono
     *  -- direccion
     * @param paciente Paciente para editar
     * @return True si se editó el registro, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar editar los datos.
     */
    public boolean editarDatosPaciente(Paciente paciente) throws PersistenciaException;
    
    /**
     * Obtiene los datos que se muestran en el perfil de un paciente.
     * @param id Id del paciente a consultar.
     * @return Objeto paciente con los datos de perfil.
     * @throws PersistenciaException Si hubo un error al consultar los datos.
     */
    public Paciente verPerfilPaciente(int id) throws PersistenciaException;
    
    /**
     * Busca si un teléfono ya está en la tabla pacientes.
     * @param telefono Teléfono a buscar.
     * @return True si el teléfono ya existe, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al consultar los datos.
     */
    public boolean existeTelefonoPaciente(String telefono) throws PersistenciaException;
}