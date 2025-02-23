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
    CitaMapper mapperCita = new CitaMapper();
    
    public Consulta toEntity(ConsultaNuevoDTO consultaNuevo){
        if (consultaNuevo == null) return null;
        
        Cita cita = mapperCita.toEntity(consultaNuevo.getCita());
        
        return new Consulta(
                consultaNuevo.getEstado(),
                consultaNuevo.getDiagnostico(),
                consultaNuevo.getTratamiento(),
                consultaNuevo.getNotas(),
                cita
        );
    }
    
    public ConsultaNuevoDTO toNuevoDTO (Consulta consulta) {
        if (consulta == null) return null;
        
        CitaNuevoDTO citaNuevo = mapperCita.toNuevoDTO(consulta.getCita());
        
        return new ConsultaNuevoDTO(
                consulta.getEstado(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                consulta.getNotas(),
                citaNuevo
        );
    }
    
    public ConsultaViejoDTO toViejoDTO(Consulta consulta) {
        if (consulta == null) return null;
        
        CitaViejoDTO citaViejo = mapperCita.toViejoDTO(consulta.getCita());
        
        return new ConsultaViejoDTO(
                consulta.getEstado(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                consulta.getNotas(),
                citaViejo
        );
    }
    
    public List<ConsultaViejoDTO> toViejoDTOList (List<Consulta> consultas) {
        List<ConsultaViejoDTO> consultasViejoDTO = new ArrayList<>();
        
        for (Consulta consulta : consultas) {
            consultasViejoDTO.add(toViejoDTO(consulta));
        }
        
        return consultasViejoDTO;
    }
}