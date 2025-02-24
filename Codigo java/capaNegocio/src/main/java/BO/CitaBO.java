/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.CitaDAO;
import DAO.ICitaDAO;
import DTO.CitaNuevoDTO;
import DTO.CitaViejoDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import conexion.IConexion;
import entidades.Cita;
import excepciones.PersistenciaException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skyro
 */
public class CitaBO {
    
    private ICitaDAO citaDAO;
    
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
            return citaDAO.registrarCitaProgramada(citaMapper.toEntity(citaNuevo));
        } catch (PersistenciaException ex) {
            Logger.getLogger(CitaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
    
    public String registrarCitaEmergencia(CitaNuevoDTO citaNuevo) throws NegocioException {
        if (citaNuevo == null) {
            throw new NegocioException("La cita no puede ser nula.");
        }
        
        try {
            String folio = generarFolio();
            citaNuevo.setFolio(folio);
            
            Cita cita = citaMapper.toEntity(citaNuevo);
            
            boolean registrada = citaDAO.registrarCitaEmergencia(cita);
            return (registrada) ? folio : null;
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(CitaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo agendar la cita de emergencia.");
        }
    }
    
    public boolean verificarCitaExiste(Timestamp fechaHoraInicio, String idMedico) throws NegocioException, PersistenciaException {
        if (fechaHoraInicio == null || idMedico == null) {
            throw new NegocioException("La cita no puede ser nula");
        }
        
            return citaDAO.verificarCitaExiste(fechaHoraInicio, Integer.parseInt(idMedico));
    }
    
    public boolean cancelarCita(CitaViejoDTO citaViejo) throws NegocioException {
        if (citaViejo == null) {
            throw new NegocioException("La cita no puede ser nula.");
        }
        
        if (citaViejo.getIdCita() == null) {
            throw new NegocioException("El id de la cita no puede ser nulo.");
        }
        try {
            return citaDAO.cancelarCita(citaMapper.toEntity(citaViejo));
        } catch (PersistenciaException ex) {
            Logger.getLogger(CitaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo cancelar la cita.");
        }
    }
    
    public CitaViejoDTO obtenerCitaEmergencia(String folio) throws NegocioException {
        if (folio == null) {
            throw new NegocioException("El folio no puede ser nulo.");
        }
        
        try {
            Cita cita = citaDAO.obtenerCitaEmergencia(folio);
            if (cita == null) {
                throw new NegocioException("No se encontró cita con folio " + folio + ".");
            }
            
            return citaMapper.toViejoDTO(cita);
        } catch (PersistenciaException ex) {
            Logger.getLogger(CitaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo cancelar la cita.");
        }
    }
    
    /**
     * Genera un folio aleatorio para una cita de emergencia.
     * @return Folio generado. 
     * @throws NegocioException Si hubo un error al generar el folio.
     */
    private String generarFolio() throws NegocioException {
        try {
            SecureRandom random = new SecureRandom();
            
            int folio = 10000000 + random.nextInt(90000000); // Número aleatorio de 8 dígitos
            return String.valueOf(folio);
        } catch (Exception ex) {
            Logger.getLogger(CitaBO.class.getName()).log(Level.SEVERE, "Error al generar el folio", ex);
            throw new NegocioException("Error al generar el folio de la cita.");
        }
    }
}
