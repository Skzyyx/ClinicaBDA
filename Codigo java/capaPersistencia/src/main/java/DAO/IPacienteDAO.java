/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entidades.Paciente;
import excepciones.PersistenciaException;

/**
 *
 * @author skyro
 */
public interface IPacienteDAO {
    
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException;
    
}
