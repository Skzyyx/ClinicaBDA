/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.CitaNuevoDTO;
import DTO.CitaViejoDTO;
import DTO.MedicoNuevoDTO;
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
 * @author Isabel
 */
public class CitaMapper {
    PacienteMapper mapperPaciente = new PacienteMapper();
    MedicoMapper mapperMedico = new MedicoMapper();
    
    public Cita toEntity(CitaNuevoDTO citaNueva) {
        if (citaNueva == null) return null;
        
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
    
    public CitaViejoDTO toViejoDTO (Cita cita) {
        if (cita == null) return null;
        
        PacienteViejoDTO pacienteViejo = mapperPaciente.toViejoDTO(cita.getPaciente());
        MedicoViejoDTO medicoViejo = mapperMedico.toViejoDTO(cita.getMedico());
        
        return new CitaViejoDTO(
                cita.getIdCita(),
                cita.getFechaHoraInicio(),
                cita.getEstado(),
                cita.getFolio(),
                cita.getTipo(),
                pacienteViejo,
                medicoViejo
        );
    }
    
    public CitaNuevoDTO toNuevoDTO (Cita cita) {
        if (cita == null) return null;
        
        PacienteNuevoDTO pacienteNuevo = mapperPaciente.toNuevoDTO(cita.getPaciente());
        MedicoNuevoDTO medicoNuevo = mapperMedico.toNuevoDTO(cita.getMedico());
        
        return new CitaNuevoDTO(
                cita.getFechaHoraInicio(),
                cita.getEstado(),
                cita.getFolio(),
                cita.getTipo(),
                pacienteNuevo,
                medicoNuevo
        );
    }
    
    public List<CitaViejoDTO> toViejoDTOList (List<Cita> citas) {
        List<CitaViejoDTO> citasViejoDTO = new ArrayList<>();
        
        for (Cita cita : citas) {
            citasViejoDTO.add(toViejoDTO(cita));
        }
        
        return citasViejoDTO;
    }
}
