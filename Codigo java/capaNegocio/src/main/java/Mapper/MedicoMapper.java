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
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class MedicoMapper {

    /**
     * Convierte una lista de entidades {@link Medico} a una lista de
     * {@link MedicoViejoDTO}.
     *
     * @param medicos Lista de médicos a convertir.
     * @return Lista de {@link MedicoViejoDTO} con los datos de los médicos.
     */
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
                    medico.getEstado(),
                    medico.getUsuario()
            ));
        }
        return medicosViejoDTO;
    }

    /**
     * Convierte un {@link MedicoViejoDTO} en una entidad {@link Medico}.
     *
     * @param medicoViejo DTO con los datos de un médico antiguo.
     * @return Instancia de {@link Medico} con los datos convertidos.
     */
    public Medico toEntity(MedicoViejoDTO medicoViejo) {
        if (medicoViejo == null) {
            return null;
        }

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

    /**
     * Convierte un {@link MedicoNuevoDTO} en una entidad {@link Medico}.
     *
     * @param medicoNuevo DTO con los datos de un médico nuevo.
     * @return Instancia de {@link Medico} con los datos convertidos.
     */
    public Medico toEntity(MedicoNuevoDTO medicoNuevo) {
        if (medicoNuevo == null) {
            return null;
        }

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

    /**
     * Convierte una entidad {@link Medico} en un {@link MedicoViejoDTO}.
     *
     * @param medico Entidad de tipo {@link Medico} a convertir.
     * @return Instancia de {@link MedicoViejoDTO} con los datos convertidos.
     */
    public MedicoViejoDTO toViejoDTO(Medico medico) {
        if (medico == null) {
            return null;
        }

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

    /**
     * Convierte una entidad {@link Medico} en un {@link MedicoNuevoDTO}.
     *
     * @param medico Entidad de tipo {@link Medico} a convertir.
     * @return Instancia de {@link MedicoNuevoDTO} con los datos convertidos.
     */
    public MedicoNuevoDTO toNuevoDTO(Medico medico) {
        if (medico == null) {
            return null;
        }

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
