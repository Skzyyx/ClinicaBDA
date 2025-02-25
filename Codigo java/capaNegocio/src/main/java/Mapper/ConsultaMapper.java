/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.CitaNuevoDTO;
import DTO.CitaViejoDTO;
import DTO.ConsultaNuevoDTO;
import DTO.ConsultaViejoDTO;
import entidades.Cita;
import entidades.Consulta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isabel
 */
public class ConsultaMapper {

    // Instancia del CitaMapper para mapear las citas asociadas a las consultas
    CitaMapper mapperCita = new CitaMapper();

    /**
     * Convierte un ConsultaNuevoDTO a una entidad Consulta.
     *
     * @param consultaNuevo DTO de tipo ConsultaNuevoDTO que contiene los datos
     * de una nueva consulta.
     * @return Una entidad Consulta que representa la consulta nueva.
     */
    public Consulta toEntity(ConsultaNuevoDTO consultaNuevo) {
        if (consultaNuevo == null) {
            return null;
        }

        // Mapeo de la cita asociada
        Cita cita = mapperCita.toEntity(consultaNuevo.getCita());

        return new Consulta(
                consultaNuevo.getEstado(),
                consultaNuevo.getDiagnostico(),
                consultaNuevo.getTratamiento(),
                consultaNuevo.getNotas(),
                cita
        );
    }

    /**
     * Convierte un ConsultaViejoDTO a una entidad Consulta.
     *
     * @param consultaViejo DTO de tipo ConsultaViejoDTO que contiene los datos
     * de una consulta ya existente.
     * @return Una entidad Consulta que representa la consulta vieja.
     */
    public Consulta toEntity(ConsultaViejoDTO consultaViejo) {
        if (consultaViejo == null) {
            return null;
        }

        // Mapeo de la cita asociada
        Cita cita = mapperCita.toEntity(consultaViejo.getCita());

        return new Consulta(
                Integer.parseInt(consultaViejo.getIdConsulta()), // Conversión de String a Integer para el ID
                consultaViejo.getEstado(),
                consultaViejo.getDiagnostico(),
                consultaViejo.getTratamiento(),
                consultaViejo.getNotas(),
                cita
        );
    }

    /**
     * Convierte una entidad Consulta a un ConsultaNuevoDTO.
     *
     * @param consulta Entidad Consulta que representa una consulta.
     * @return Un DTO ConsultaNuevoDTO que contiene los datos de la nueva
     * consulta.
     */
    public ConsultaNuevoDTO toNuevoDTO(Consulta consulta) {
        if (consulta == null) {
            return null;
        }

        // Mapeo de la cita asociada
        CitaNuevoDTO citaNuevo = mapperCita.toNuevoDTO(consulta.getCita());

        return new ConsultaNuevoDTO(
                consulta.getEstado(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                consulta.getNotas(),
                citaNuevo
        );
    }

    /**
     * Convierte una entidad Consulta a un ConsultaViejoDTO.
     *
     * @param consulta Entidad Consulta que representa una consulta.
     * @return Un DTO ConsultaViejoDTO que contiene los datos de la consulta.
     */
    public ConsultaViejoDTO toViejoDTO(Consulta consulta) {
        if (consulta == null) {
            return null;
        }

        // Mapeo de la cita asociada
        CitaViejoDTO citaViejo = mapperCita.toViejoDTO(consulta.getCita());

        return new ConsultaViejoDTO(
                String.valueOf(consulta.getIdConsulta()), // Conversión de Integer a String para el ID
                consulta.getEstado(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                consulta.getNotas(),
                citaViejo
        );
    }

    /**
     * Convierte una lista de entidades Consulta a una lista de
     * ConsultaViejoDTO.
     *
     * @param consultas Lista de entidades Consulta que representan consultas.
     * @return Una lista de DTOs ConsultaViejoDTO que contienen los datos de las
     * consultas.
     */
    public List<ConsultaViejoDTO> toViejoDTOList(List<Consulta> consultas) {
        List<ConsultaViejoDTO> consultasViejoDTO = new ArrayList<>();

        // Convertir cada Consulta a ConsultaViejoDTO
        for (Consulta consulta : consultas) {
            consultasViejoDTO.add(toViejoDTO(consulta));
        }

        return consultasViejoDTO;
    }
}
