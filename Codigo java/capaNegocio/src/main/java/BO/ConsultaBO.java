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
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
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
    
    /**
     * Obtiene los nombres completos de los pacientes de una lista de consultas.
     * @param consultas Lista de consultas.
     * @return Lista con nombres únicos de pacientes.
     * @throws NegocioException Si la lista de consultas es nula.
     */
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
  
    /**
     * Filtra las consultas dentro de un período de fechas específico.
     * @param consultas Lista de consultas.
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin Fecha de fin.
     * @return Lista de consultas dentro del período.
     * @throws NegocioException Si la lista o las fechas son nulas o si la fechaInicio es posterior a fechaFin.
     */
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
    
    /**
     * Filtra las consultas por una fecha específica.
     * @param consultas Lista de consultas.
     * @param fecha Fecha de consulta.
     * @return Lista de consultas en la fecha indicada.
     * @throws NegocioException Si la lista o la fecha son nulas.
     */
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

    /**
     * Obtiene el estado de una consulta dado su ID de cita.
     * @param idCita ID de la cita.
     * @return Estado de la consulta.
     * @throws NegocioException Si el ID es nulo o si ocurre un error de persistencia.
     */
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
    
    /**
     * Obtiene una consulta por el ID de la cita.
     * @param idCita ID de la cita.
     * @return Consulta correspondiente al ID de la cita.
     * @throws NegocioException Si el ID es nulo o si ocurre un error de persistencia.
     */
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
    
    /**
     * Edita los datos de una consulta.
     * @param consultaViejo Objeto con los datos de la consulta.
     * @return True si la edición fue exitosa.
     * @throws NegocioException Si el ID de la consulta es nulo o si ocurre un error de persistencia.
     */
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
