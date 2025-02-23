/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.CitaDAO;
import DTO.CitaNuevoDTO;
import DTO.CitaViejoDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import conexion.IConexion;
import excepciones.PersistenciaException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skyro
 */
public class CitaBO {
    
    private CitaDAO citaDAO;
    
    private CitaMapper citaMapper = new CitaMapper();

    public CitaBO(IConexion conexion) {
        this.citaDAO = new CitaDAO(conexion);
    }
    
    public boolean registrarCitaProgramada(CitaNuevoDTO citaNuevo) throws NegocioException {
        if (citaNuevo.getFechaHoraInicio() == null) {
            throw new NegocioException("La fecha no puede ser nula.");
        }
        
        if (citaNuevo.getPaciente() == null) {
            throw new NegocioException("El paciente no puede ser nulo.");
        }
        
        if (citaNuevo.getMedico() == null) {
            throw new NegocioException("El médico no puede ser nulo.");
        }
        
        try {
            return citaDAO.registrarCitaEmergencia(citaMapper.toEntity(citaNuevo));
        } catch (PersistenciaException ex) {
            Logger.getLogger(CitaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Ocurrió un error inesperado. Lo sentimos.");
        }
    }
    
    public boolean verificarCitaExiste(Timestamp fechaHoraInicio, String idMedico) throws NegocioException, PersistenciaException {
        if (fechaHoraInicio == null || idMedico == null) {
            throw new NegocioException("La cita no puede ser nula");
        }
        
            return citaDAO.verificarCitaExiste(fechaHoraInicio, Integer.parseInt(idMedico));
    }
    
}
