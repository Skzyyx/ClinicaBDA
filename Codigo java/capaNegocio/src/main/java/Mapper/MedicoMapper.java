/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.MedicoViejoDTO;
import entidades.Medico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skyro
 */
public class MedicoMapper {
    
    public List<MedicoViejoDTO> toViejoDTOList(List<Medico> medicos) {
        List<MedicoViejoDTO> medicosViejoDTO = new ArrayList<>();
        
        for (Medico medico : medicos) {
            medicosViejoDTO.add(new MedicoViejoDTO( 
                    String.valueOf(medico.getIdMedico()),
                    medico.getNombre(), 
                    medico.getApellidoPaterno(), 
                    medico.getApellidoMaterno(), 
                    medico.getEspecialidad(),
                    medico.getCedula(),
                    medico.getEstado()));
        }
        return medicosViejoDTO;
    }
}
