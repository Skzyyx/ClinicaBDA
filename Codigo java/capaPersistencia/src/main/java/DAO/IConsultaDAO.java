/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Consulta;
import excepciones.PersistenciaException;

/**
 *
 * @author Isabel
 */
public interface IConsultaDAO {
    
    public String obtenerEstadoConsulta(int idCita) throws PersistenciaException;
    
    public Consulta obtenerConsultaPorIdCita(int idCita) throws PersistenciaException;
    
    public boolean editarDatosConsulta(Consulta conuslta) throws PersistenciaException;
}
