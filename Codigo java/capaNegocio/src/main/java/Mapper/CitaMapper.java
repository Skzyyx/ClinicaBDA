/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.CitaNuevoDTO;
import DTO.CitaViejoDTO;
import DTO.MedicoViejoDTO;
import DTO.PacienteNuevoDTO;
import DTO.PacienteViejoDTO;
import entidades.Cita;
import entidades.Medico;
import entidades.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class CitaMapper {

    // Instancia de los mappers para Paciente y Medico
    PacienteMapper mapperPaciente = new PacienteMapper();
    MedicoMapper mapperMedico = new MedicoMapper();

    /**
     * Convierte un CitaNuevoDTO a una entidad Cita.
     *
     * @param citaNueva DTO de tipo CitaNuevoDTO que contiene los datos de una
     * nueva cita.
     * @return Una entidad Cita que representa la cita nueva.
     */
    public Cita toEntity(CitaNuevoDTO citaNueva) {
        if (citaNueva == null) {
            return null;
        }

        // Mapeo de Paciente y Medico
        Paciente paciente = mapperPaciente.toEntity(citaNueva.getPaciente());
        Medico medico = mapperMedico.toEntity(citaNueva.getMedico());

        return new Cita(
                citaNueva.getFechaHoraInicio(),
                citaNueva.getEstado(),
                citaNueva.getFolio(),
                citaNueva.getTipo(),
                paciente,
                medico
        );
    }

    /**
     * Convierte un CitaViejoDTO a una entidad Cita.
     *
     * @param citaViejo DTO de tipo CitaViejoDTO que contiene los datos de una
     * cita ya existente.
     * @return Una entidad Cita que representa la cita vieja.
     */
    public Cita toEntity(CitaViejoDTO citaViejo) {
        if (citaViejo == null) {
            return null;
        }

        // Mapeo de Paciente y Medico
        Paciente paciente = mapperPaciente.toEntity(citaViejo.getPaciente());
        Medico medico = mapperMedico.toEntity(citaViejo.getMedico());

        return new Cita(
                Integer.parseInt(citaViejo.getIdCita()), // Conversión de String a Integer para el ID
                citaViejo.getFechaHoraInicio(),
                citaViejo.getEstado(),
                citaViejo.getFolio(),
                citaViejo.getTipo(),
                paciente,
                medico
        );
    }

    /**
     * Convierte una entidad Cita a un CitaViejoDTO.
     *
     * @param cita Entidad Cita que representa una cita.
     * @return Un DTO CitaViejoDTO que contiene los datos de la cita.
     */
    public CitaViejoDTO toViejoDTO(Cita cita) {
        if (cita == null) {
            return null;
        }

        // Mapeo de Paciente y Medico
        PacienteViejoDTO pacienteViejo = mapperPaciente.toViejoDTO(cita.getPaciente());
        MedicoViejoDTO medicoViejo = mapperMedico.toViejoDTO(cita.getMedico());

        return new CitaViejoDTO(
                String.valueOf(cita.getIdCita()), // Conversión de Integer a String para el ID
                cita.getFechaHoraInicio(),
                cita.getEstado(),
                cita.getFolio(),
                cita.getTipo(),
                pacienteViejo,
                medicoViejo
        );
    }

    /**
     * Convierte una entidad Cita a un CitaNuevoDTO.
     *
     * @param cita Entidad Cita que representa una cita.
     * @return Un DTO CitaNuevoDTO que contiene los datos de la nueva cita.
     */
    public CitaNuevoDTO toNuevoDTO(Cita cita) {
        if (cita == null) {
            return null;
        }

        // Mapeo de Paciente y Medico
        PacienteNuevoDTO pacienteNuevo = mapperPaciente.toNuevoDTO(cita.getPaciente());
        MedicoViejoDTO medicoNuevo = mapperMedico.toViejoDTO(cita.getMedico());

        return new CitaNuevoDTO(
                cita.getFechaHoraInicio(),
                cita.getEstado(),
                cita.getFolio(),
                cita.getTipo(),
                pacienteNuevo,
                medicoNuevo
        );
    }

    /**
     * Convierte una lista de entidades Cita a una lista de CitaViejoDTO.
     *
     * @param citas Lista de entidades Cita que representan citas.
     * @return Una lista de DTOs CitaViejoDTO que contienen los datos de las
     * citas.
     */
    public List<CitaViejoDTO> toViejoDTOList(List<Cita> citas) {
        List<CitaViejoDTO> citasViejoDTO = new ArrayList<>();

        // Convertir cada Cita a CitaViejoDTO
        for (Cita cita : citas) {
            citasViejoDTO.add(toViejoDTO(cita));
        }

        return citasViejoDTO;
    }
}
