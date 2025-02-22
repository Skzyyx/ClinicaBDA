/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.HorarioViejoDTO;
import entidades.Horario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skyro
 */
public class HorarioMapper {
    
    private MedicoMapper medicoMapper = new MedicoMapper();
    
    public List<HorarioViejoDTO> toViejoDTO(List<Horario> horarios) {
        List<HorarioViejoDTO> horariosViejoDTO = new ArrayList<>();
        
        for(Horario horario : horarios) {
            horariosViejoDTO.add(new HorarioViejoDTO(
            horario.getDiaSemana(),
            horario.getHoraEntrada(),
            horario.getHoraSalida(),
            medicoMapper.toViejoDTO(horario.getMedico())
            ));
        }
        return horariosViejoDTO;
    }
}
