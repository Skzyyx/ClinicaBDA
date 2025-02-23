/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.MedicoNuevoDTO;
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
    
    public Medico toEntity(MedicoViejoDTO medicoViejo) {
        if (medicoViejo == null) return null;
        
        return new Medico(
                Integer.parseInt(medicoViejo.getIdMedico()),
                medicoViejo.getNombre(),
                medicoViejo.getApellidoPaterno(),
                medicoViejo.getApellidoMaterno(),
                medicoViejo.getEspecialidad(),
                medicoViejo.getCedula(),
                medicoViejo.getEstado(),
                medicoViejo.getUsuario()
        );
    }
    
    public Medico toEntity(MedicoNuevoDTO medicoNuevo) {
        if (medicoNuevo == null) return null;
        
        return new Medico(
                medicoNuevo.getNombre(),
                medicoNuevo.getApellidoPaterno(),
                medicoNuevo.getApellidoMaterno(),
                medicoNuevo.getEspecialidad(),
                medicoNuevo.getCedula(),
                medicoNuevo.getEstado(),
                medicoNuevo.getUsuario()
        );
    }
    
    public MedicoViejoDTO toViejoDTO(Medico medico) {
        if (medico == null) return null;
        
        return new MedicoViejoDTO(
                String.valueOf(medico.getIdMedico()),
                medico.getNombre(),
                medico.getApellidoPaterno(),
                medico.getApellidoMaterno(),
                medico.getEspecialidad(),
                medico.getCedula(),
                medico.getEstado(),
                medico.getUsuario()
        );
    }
    
    public MedicoNuevoDTO toNuevoDTO(Medico medico) {
        if (medico == null) return null;
        
        return new MedicoNuevoDTO(
                medico.getNombre(),
                medico.getApellidoPaterno(),
                medico.getApellidoMaterno(),
                medico.getEspecialidad(),
                medico.getCedula(),
                medico.getEstado(),
                medico.getUsuario()
        );
    }
}
