/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IPacienteDAO;
import DAO.PacienteDAO;
import DTO.PacienteNuevoDTO;
import Exception.NegocioException;
import Mapper.PacienteMapper;
import conexion.IConexion;
import entidades.Paciente;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Clase que valida los metodos de Paciente
 *
 * @author @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class PacienteBO {

    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());

    //DAO para manejar la persistencia de los pacientes
    private final IPacienteDAO pacienteDAO;

    // Creacion y uso del mapper/convertidor que se encargara de convertir los DTO a Entidades
    private final PacienteMapper mapper = new PacienteMapper();

    /**
     * Constructor de la clase PacienteBO
     *
     * @param conexion Instancia de Iconexion para la conexion a la base de
     * datos.
     */
    public PacienteBO(IConexion conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
    }

    //        Paciente paciente =new Paciente();
//        paciente=mapper.toEntity(pacienteNuevo);
    public boolean registrarPaciente(PacienteNuevoDTO pacienteNuevo) throws NegocioException {
        
        if (pacienteNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo");
        }
        // Validar que el rol( que sea o paciente o medico)
        if (!pacienteNuevo.getUsuario().getRol().equals("PACIENTE")) {
            throw new NegocioException("El rol no es corecto");
        }

        //Validar ninguno de los atributos esenciales sean nulo
        if (pacienteNuevo.getNombre() == null || pacienteNuevo.getApellidoPaterno() == null
                || pacienteNuevo.getFechaNacimiento() == null
                || pacienteNuevo.getEmail() == null || pacienteNuevo.getTelefono() == null
                || pacienteNuevo.getUsuario().getUsuario() == null || pacienteNuevo.getUsuario().getContrasenia() == null
                || pacienteNuevo.getDireccion().getCalle() == null || pacienteNuevo.getDireccion().getNumero() == null
                || pacienteNuevo.getDireccion().getColonia() == null || pacienteNuevo.getDireccion().getCodigoPostal() == null) {
            throw new NegocioException("Uno de los atributos del Paciente esta en nulo");
        }

//      
        //Valida que los espacios obligatorios hayan sido llenados y no queden como ""
        if (pacienteNuevo.getNombre().isEmpty() || pacienteNuevo.getApellidoPaterno().isEmpty()
                || pacienteNuevo.getEmail().isEmpty() || pacienteNuevo.getTelefono().isEmpty()
                || pacienteNuevo.getUsuario().getUsuario().isEmpty() || pacienteNuevo.getUsuario().getContrasenia().isEmpty()
                || pacienteNuevo.getDireccion().getCalle().isEmpty() || pacienteNuevo.getDireccion().getNumero().isEmpty()
                || pacienteNuevo.getDireccion().getColonia().isEmpty() || pacienteNuevo.getDireccion().getCodigoPostal().isEmpty()) {
            throw new NegocioException("Verifique que los campos obligatorios esten llenados");
        }

        //Validar que la fecha no sea despues de la fecha de hoy
        if (pacienteNuevo.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser despues de la fecha de hoy");
        }

        // Validar que el numero de telefono tenga los 10 digitos 
        if (pacienteNuevo.getTelefono().length() != 10) {
            throw new NegocioException("El numero de telefono no puede ser mayor o menor a 10 digitos");
        }

        //Validar que el correo haya sido ingresado correctamente
        if (!EmailValidator.getInstance().isValid(pacienteNuevo.getEmail())) {
            throw new NegocioException("El correo electrónico ingresado no es válido");
        }
        
         // Convertir el DTO a la entidad
        Paciente paciente = new Paciente();
        paciente= mapper.toEntity(pacienteNuevo);
        
        // Mandar la entidad al DAO en caso de que ocurra un error , lanzara una excepcion
        try {
            boolean pacienteRegistrado = pacienteDAO.registrarPaciente(paciente);
            
            return pacienteRegistrado;
            
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al registrar un Paciente " , e);
            throw new NegocioException ("Hubo un error al registrar el paciente ( registro no exitoso");
        }
       
        
        
        
        
        
        
    }

}
