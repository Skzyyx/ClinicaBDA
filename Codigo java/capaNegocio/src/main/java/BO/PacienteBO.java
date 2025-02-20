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
import at.favre.lib.crypto.bcrypt.BCrypt;
import conexion.IConexion;
import entidades.Paciente;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Clase PacienteBO.
 * Representa el BO para la clase Paciente
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class PacienteBO {

    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());

    // DAO para manejar la persistencia de los pacientes
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
    
    /**
     * Registra un paciente mediante la DAO.
     * @param pacienteNuevo Paciente a registrar
     * @return True si se registró, false en caso contrario.
     * @throws NegocioException
     * @throws PersistenciaException
     */
    public boolean registrarPaciente(PacienteNuevoDTO pacienteNuevo) throws NegocioException, PersistenciaException {
        // Validar el objeto
        if (pacienteNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo");
        }
        // Validar el rol
        if (!pacienteNuevo.getUsuario().getRol().equals("PACIENTE")) {
            throw new NegocioException("El rol no es correcto");
        }

        // Validar que ninguno de los atributos requeridos sea nulo
        if (pacienteNuevo.getNombre() == null || pacienteNuevo.getApellidoPaterno() == null
                || pacienteNuevo.getFechaNacimiento() == null
                || pacienteNuevo.getEmail() == null || pacienteNuevo.getTelefono() == null
                || pacienteNuevo.getUsuario().getUsuario() == null || pacienteNuevo.getUsuario().getContrasenia() == null
                || pacienteNuevo.getDireccion().getCalle() == null || pacienteNuevo.getDireccion().getNumero() == null
                || pacienteNuevo.getDireccion().getColonia() == null || pacienteNuevo.getDireccion().getCodigoPostal() == null) {
            throw new NegocioException("Ningún atributo requerido del paciente puede ser nulo");
        }

        // Validar que los espacios obligatorios hayan sido llenados y no queden como ""
        if (pacienteNuevo.getNombre().isEmpty() || pacienteNuevo.getApellidoPaterno().isEmpty()
                || pacienteNuevo.getEmail().isEmpty() || pacienteNuevo.getTelefono().isEmpty()
                || pacienteNuevo.getUsuario().getUsuario().isEmpty() || pacienteNuevo.getUsuario().getContrasenia().isEmpty()
                || pacienteNuevo.getDireccion().getCalle().isEmpty() || pacienteNuevo.getDireccion().getNumero().isEmpty()
                || pacienteNuevo.getDireccion().getColonia().isEmpty() || pacienteNuevo.getDireccion().getCodigoPostal().isEmpty()) {
            throw new NegocioException("Verifique que los campos obligatorios esten llenados");
        }

        // Validar que la fecha de nacimiento no sea despues de la fecha de hoy
        if (pacienteNuevo.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser después de la fecha de hoy");
        }

        // Validar que el numero de telefono tenga los 10 digitos 
        if (pacienteNuevo.getTelefono().length() != 10) {
            throw new NegocioException("El numero de telefono no puede ser mayor o menor a 10 digitos");
        }

        // Validar que el correo haya sido ingresado correctamente
        if (!EmailValidator.getInstance().isValid(pacienteNuevo.getEmail())) {
            throw new NegocioException("El formato de correo electrónico ingresado no es válido");
        }
        
        // Si el usuario y el correo son diferentes
        if (!pacienteNuevo.getUsuario().getUsuario().equals(pacienteNuevo.getEmail())) {
            throw new NegocioException("El usuario y correo deben de ser iguales.");
        }
        
        // Verificar que el teléfono no sea duplicado
        if (pacienteDAO.existeTelefonoPaciente(pacienteNuevo.getTelefono())) {
            throw new NegocioException("El teléfono ya se encuentra registrado.");
        }
        
        // Buscar si el paciente ya está registrado (email repetido)
        Paciente pacienteExiste = pacienteDAO.consultarPacientePorEmail(pacienteNuevo.getEmail());
        
        // Validar registro repetido
        if (pacienteExiste != null) {
            throw new NegocioException("El email " + pacienteNuevo.getEmail() + " ya está registrado.");
        }
        
        // Encriptar la contraseña
        String contraseniaEncriptada = encriptarContrasenia(pacienteNuevo.getUsuario().getContrasenia());
        // Setear la contraseña encriptada al Usuario del paciente
        pacienteNuevo.getUsuario().setContrasenia(contraseniaEncriptada);
        
        // Convertir el DTO a la entidad
        Paciente paciente = new Paciente();
        paciente = mapper.toEntity(pacienteNuevo);
        
        // Intentar mandar la entidad al DAO 
        try {
            boolean pacienteRegistrado = pacienteDAO.registrarPaciente(paciente);
            return pacienteRegistrado;
        // En caso de que ocurra un error, lanza una excepcion   
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al registrar un Paciente " , e);
            throw new NegocioException ("Error al registrar el paciente.");
        }
    }
    
    /**
     * Encripta la contraseña generandole un código hash.
     * @param contrasenia Contraseña a encriptar.
     * @return Contraseña encriptada.
     */
    private String encriptarContrasenia(String contrasenia) throws NegocioException {
        try {
            String contraseniaEncriptada = BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray());
            return contraseniaEncriptada;
        } catch (Exception e) {
            throw new NegocioException("Error al encriptar contraseña: " + e.getMessage());
        }
    }
}