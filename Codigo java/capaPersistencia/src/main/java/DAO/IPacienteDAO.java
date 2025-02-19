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
    
}