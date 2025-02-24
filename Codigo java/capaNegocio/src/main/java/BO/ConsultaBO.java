/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ConsultaDAO;
import DAO.IConsultaDAO;
import DTO.ConsultaViejoDTO;
import Exception.NegocioException;
import Mapper.ConsultaMapper;
import conexion.IConexion;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Isabel
 */
public class ConsultaBO {
    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());
    
    private final IConsultaDAO consultaDAO;
    
    private final ConsultaMapper mapper = new ConsultaMapper();
    
    public ConsultaBO(IConexion conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
    }
    
    /**
     * Obtiene las especialidades de las consultas de un paciente en específico.
     * @param consultas Lista de consultas del paciente.
     * @return Lista con especialidades.
     * @throws NegocioException Si hubo un error en el proceso de filtrar.
     */
    public List<String> especialidadesConsultas (List<ConsultaViejoDTO> consultas) throws NegocioException {
        if (consultas == null) {
            throw new NegocioException("La lista de consultas no puede ser nula.");
        }

        // Usar un HashSet para evitar duplicados automáticamente
        Set<String> especialidadesSet = new HashSet<>();

        for (ConsultaViejoDTO consulta : consultas) {
            especialidadesSet.add(consulta.getCita().getMedico().getEspecialidad());
        }

        // Convertir el Set a una Lista y devolverlo
        return List.copyOf(especialidadesSet);
    }
    
    public List<String> nombresPacientesConsultas(List<ConsultaViejoDTO> consultas) throws NegocioException {
        if (consultas == null) {
            throw new NegocioException("La lista de consultas no puede ser nula.");
        }

        List<String> nombresLista = new ArrayList<>();

        for (ConsultaViejoDTO consulta : consultas) {
            String nombre = consulta.getCita().getPaciente().getNombre().trim();
            String apellidoPaterno = consulta.getCita().getPaciente().getApellidoPaterno().trim();
            String apellidoMaterno = consulta.getCita().getPaciente().getApellidoMaterno();

            // Si el apellido materno es null, lo convertimos en una cadena vacía para evitar "null"
            if (apellidoMaterno == null) {
                apellidoMaterno = "";
            }

            // Formar el nombre completo
            String nombreCompleto = (nombre + " " + apellidoPaterno + " " + apellidoMaterno);
            
            // Agregar a la lista solo si no está presente
            if (!nombresLista.contains(nombreCompleto)) {
                nombresLista.add(nombreCompleto);
            }
        }

        return nombresLista;
    }
  
    public List<ConsultaViejoDTO> filtrarConsultasPeriodo(List<ConsultaViejoDTO> consultas, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        if (consultas == null) {
            throw new NegocioException("La lista de consultas no puede ser nula.");
        }

        if (fechaInicio == null || fechaFin == null) {
            throw new NegocioException("Se requieren ambas fechas (inicio y fin).");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new NegocioException("El orden de las fechas no es correcto.");
        }

        // Filtrar por fecha en el rango usando stream
        return consultas.stream()
                .filter(consulta -> {
                    LocalDate fechaConsulta = consulta.getCita().getFechaHoraInicio().toLocalDate();
                    return !fechaConsulta.isBefore(fechaInicio) && !fechaConsulta.isAfter(fechaFin);
                })
                .collect(Collectors.toList());
    }
    
    public List<ConsultaViejoDTO> filtrarConsultasFecha(List<ConsultaViejoDTO> consultas, LocalDate fecha) throws NegocioException {
        if (consultas == null) {
            throw new NegocioException("La lista de consultas no puede ser nula.");
        }

        if (fecha == null) {
            throw new NegocioException("Se requieren una fechas válida.");
        }
        
        // Filtrar las consultas que coincidan con la fecha dada
        return consultas.stream()
                .filter(consulta -> consulta.getCita().getFechaHoraInicio().toLocalDate().equals(fecha))
                .collect(Collectors.toList());
    }

    public String obtenerEstadoConsulta(String idCita) throws NegocioException {
        if (idCita == null) {
            throw new NegocioException("El id no puede ser nulo");
        }
        
        try {
            return consultaDAO.obtenerEstadoConsulta(Integer.parseInt(idCita));
        } catch (PersistenciaException ex) {
            Logger.getLogger(ConsultaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ConsultaViejoDTO obtenerConsultaPorIdCita(String idCita) throws NegocioException {
        if (idCita == null) {
            throw new NegocioException("El id de la cita no puede ser nulo.");
        }
       
        try {
            return mapper.toViejoDTO(consultaDAO.obtenerConsultaPorIdCita(Integer.parseInt(idCita)));
        } catch (PersistenciaException ex) {
            Logger.getLogger(ConsultaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error inesperado al obtener la consulta.");
        }
    }
    
    public boolean editarDatosConsulta(ConsultaViejoDTO consultaViejo) throws NegocioException {
        if (consultaViejo.getIdConsulta().trim() == null) {
            throw new NegocioException("El id de la consulta no puede ser nulo.");
        }
        
        try {
            return consultaDAO.editarDatosConsulta(mapper.toEntity(consultaViejo));
        } catch (PersistenciaException ex) {
            Logger.getLogger(ConsultaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Ocurrio un error al editar los datos de la consulta.");
        }
    }
}
