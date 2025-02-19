/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.PacienteNuevoDTO;
import DTO.PacienteViejoDTO;
import entidades.Paciente;

/**
 * Clase PacienteMapper.
 * Clase que permite mappear las entidades de la capa persistencia a entidades DTO.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PacienteMapper {
    
    /**
     * Convierte un objeto `PacienteCreateDTO` a una entidad `Paciente`.
     * 
     * Este método mapea los datos de un DTO de tipo `PacienteNuevoDTO` a una entidad `Paciente`.
     * Se utiliza cuando se desea crear un nuevo paciente en la base de datos, a partir de los datos proporcionados por el cliente.
     *
     * @param pacienteNuevo El DTO `PacienteNuevoDTO` con los datos del paciente a crear.
     * @return Una entidad `Paciente` que puede ser persistida en la base de datos, o null si el DTO es null.
     */
    public Paciente toEntity(PacienteNuevoDTO pacienteNuevo) {
        
        if (pacienteNuevo == null) return null;
        
        return new Paciente(
            pacienteNuevo.getNombre(),
            pacienteNuevo.getApellidoPaterno(),
            pacienteNuevo.getApellidoMaterno(),
            pacienteNuevo.getFechaNacimiento(),
            pacienteNuevo.getEmail(),
            pacienteNuevo.getTelefono(),
            pacienteNuevo.getUsuario(),
            pacienteNuevo.getDireccion()
        );
    }
    
    /**
     * Convierte una entidad `Paciente` a un DTO de tipo `PacienteNuevoDTO`.
     * 
     * Este método mapea los datos de la entidad `Paciente` a un DTO `PacienteNuevoDTO` que será utilizado
     * para la creación de un nuevo paciente en la base de datos.
     * 
     * @param paciente La entidad `Paciente` a convertir.
     * @return Un objeto `PacienteNuevoDTO` con los datos del paciente, o null si la entidad es null.
     */
    public PacienteNuevoDTO toNuevoDTO(Paciente paciente) {
        
        if (paciente == null) return null;
        
        return new PacienteNuevoDTO(
                paciente.getNombre(), 
                paciente.getApellidoPaterno(), 
                paciente.getApellidoMaterno(), 
                paciente.getFechaNacimiento(), 
                paciente.getEmail(), 
                paciente.getTelefono(), 
                paciente.getUsuario(), 
                paciente.getDireccion()
        );
    }
    
    /**
     * Convierte una entidad `Paciente` a un DTO de tipo `PacienteViejoDTO`.
     * 
     * Este método mapea los datos de la entidad `Paciente` a un DTO `PacienteViejoDTO`. 
     * Se utiliza principalmente para representar los datos del paciente en un formato adecuado 
     * para ser enviado a la capa de presentación o API, y puede incluir el ID del paciente.
     *
     * @param paciente La entidad `Paciente` a convertir.
     * @return Un objeto `PacienteViejoDTO` con los datos del paciente, o null si la entidad es null.
     */
    public PacienteViejoDTO toViejoDTO(Paciente paciente) {
        
        if (paciente == null) return null;
        
        return new PacienteViejoDTO(
                String.valueOf(paciente.getIdPaciente()), 
                paciente.getNombre(), 
                paciente.getApellidoPaterno(), 
                paciente.getApellidoMaterno(), 
                paciente.getFechaNacimiento(), 
                paciente.getEmail(), 
                paciente.getTelefono(), 
                paciente.getUsuario(), 
                paciente.getDireccion()
        );
    }
    
}
