/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IPacienteDAO;
import DAO.PacienteDAO;
import DTO.PacienteNuevoDTO;
import DTO.PacienteViejoDTO;
import DTO.PerfilDTO;
import DTO.PerfilViejoDTO;
import Exception.NegocioException;
import Mapper.PacienteMapper;
import at.favre.lib.crypto.bcrypt.BCrypt;
import conexion.IConexion;
import entidades.Paciente;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.Objects;
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
            throw new NegocioException("El paciente no puede ser nulo.");
        }
        // Validar el rol
        if (!pacienteNuevo.getUsuario().getRol().equals("PACIENTE")) {
            throw new NegocioException("El rol no es correcto.");
        }

        // Validar que ninguno de los atributos requeridos sea nulo
        if (pacienteNuevo.getNombre() == null || pacienteNuevo.getApellidoPaterno() == null
                || pacienteNuevo.getFechaNacimiento() == null || pacienteNuevo.getEmail() == null 
                || pacienteNuevo.getTelefono() == null || pacienteNuevo.getUsuario() == null
                || pacienteNuevo.getUsuario().getUsuario() == null || pacienteNuevo.getUsuario().getContrasenia() == null
                || pacienteNuevo.getDireccion() == null || pacienteNuevo.getDireccion().getCalle() == null || pacienteNuevo.getDireccion().getNumero() == null
                || pacienteNuevo.getDireccion().getColonia() == null || pacienteNuevo.getDireccion().getCodigoPostal() == null) {
            throw new NegocioException("Ningún atributo requerido del paciente puede ser nulo.");
        }

        // Validar que los espacios obligatorios hayan sido llenados y no contengan solo espacios
        if (pacienteNuevo.getNombre().isBlank() || pacienteNuevo.getApellidoPaterno().isBlank()
                || pacienteNuevo.getEmail().isBlank() || pacienteNuevo.getTelefono().isBlank()
                || pacienteNuevo.getUsuario().getUsuario().isBlank() || pacienteNuevo.getUsuario().getContrasenia().isBlank()
                || pacienteNuevo.getDireccion().getCalle().isBlank() || pacienteNuevo.getDireccion().getNumero().isBlank()
                || pacienteNuevo.getDireccion().getColonia().isBlank() || pacienteNuevo.getDireccion().getCodigoPostal().isBlank()) {
            throw new NegocioException("Verifique que los campos obligatorios estén llenados correctamente.");
        }

        // Validar que la fecha de nacimiento no sea despues de la fecha de hoy
        if (pacienteNuevo.getFechaNacimiento().isAfter(LocalDate.now()) || pacienteNuevo.getFechaNacimiento().isBefore((LocalDate.of(1890, 1, 1)))) {
            throw new NegocioException("La fecha de nacimiento no es válida.");
        }

        // Validar que el numero de telefono tenga los 10 digitos 
        if (!pacienteNuevo.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("El número de teléfono debe de ser de 10 dígitos.");
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
            logger.log(Level.SEVERE, "Error al registrar un paciente " , e);
            throw new NegocioException ("Error al registrar el paciente.");
        }
    }
    
    /**
     * Obtiene un paciente con un email específico mediante la DAO.
     * @param email Email del paciente a buscar.
     * @return El paciente si lo encontró, null en caso contrario.
     * @throws NegocioException Si hubo un error al buscar el paciente.
     */
    public PacienteViejoDTO obtenerPacientePorEmail(String email) throws NegocioException {
        // Validar que el email no sea nulo
        if (email == null) {
            throw new NegocioException("El correo electrónico no puede ser nulo.");
        }
        
        // Validar que el correo haya sido ingresado correctamente
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new NegocioException("El formato de correo electrónico ingresado no es válido.");
        }
        
        // Intentar obtener el paciente
        try {
            // Busca el paciente usando la DAO
            Paciente paciente = pacienteDAO.consultarPacientePorEmail(email);
            
            // Si no se encontró registro
            if (paciente == null) {
                return null;
            }
            
            // Si se encontró registro
            // Convertir entidad a DTO y regresarlo
            return mapper.toViejoDTO(paciente);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al obtener un perfil de paciente: " , e);
            throw new NegocioException ("Error al obtener un perfil de paciente: ", e);
        }
    }
    
    /**
     * Edita los datos de un paciente específico mediante la DAO.
     * Solo se permite editar:
     * 	-- nombre
     *  -- apellidos
     *  -- fecha de nacimiento
     *  -- telefono
     *  -- direccion
     *  -- contraseña
     * @param email Email (usuario) del paciente
     * @param pacienteNuevo Paciente con los datos editados
     * @return True si se editaron los datos, false en caso contrario.
     * @throws Exception.NegocioException 
     * @throws excepciones.PersistenciaException 
     */
    public boolean editarDatosPaciente(String email, PacienteNuevoDTO pacienteNuevo) throws NegocioException, PersistenciaException {
        // Verificar que el email no sea nulo
        if (email == null) {
            throw new NegocioException("El usuario (email) no puede ser nulo.");
        }
        
        // Verificar que el paciente no sea nulo
        if (pacienteNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo.");
        }
        
        // Verificar que el paciente que se quiera editar exista
        Paciente pacienteExiste = pacienteDAO.consultarPacientePorEmail(email);
        
        // Si el paciente no existe
        if (pacienteExiste == null) {
            throw new NegocioException("El paciente no existe.");
        }
        
        // Verificar que no se intente modificar el user
        if (Objects.nonNull(pacienteNuevo.getUsuario()) && Objects.nonNull(pacienteNuevo.getUsuario().getUsuario())
                && !Objects.equals(pacienteNuevo.getUsuario().getUsuario(), pacienteExiste.getUsuario().getUsuario())) {
            throw new NegocioException("No se permite modificar el usuario.");
        }
        
        // Verificar que no se intente modificar el rol
        if (Objects.nonNull(pacienteNuevo.getUsuario()) && Objects.nonNull(pacienteNuevo.getUsuario().getRol())
                && !Objects.equals(pacienteNuevo.getUsuario().getRol(), pacienteExiste.getUsuario().getRol())) {
            throw new NegocioException("No se permite modificar el rol.");
        }
        
        // Verificar que no se intente modificar el email
        if (Objects.nonNull(pacienteNuevo.getEmail()) && !Objects.equals(pacienteNuevo.getEmail(), pacienteExiste.getEmail())) {
            throw new NegocioException("No se permite modificar el correo electrónico.");
        }
        
        // Validar que ninguno de los atributos que pueden editarse sea nulo
        if (pacienteNuevo.getNombre() == null || pacienteNuevo.getApellidoPaterno() == null
                || pacienteNuevo.getFechaNacimiento() == null || pacienteNuevo.getTelefono() == null
                || pacienteNuevo.getUsuario() == null || pacienteNuevo.getUsuario().getContrasenia() == null 
                || pacienteNuevo.getDireccion() == null || pacienteNuevo.getDireccion().getCalle() == null 
                || pacienteNuevo.getDireccion().getNumero() == null || pacienteNuevo.getDireccion().getColonia() == null 
                || pacienteNuevo.getDireccion().getCodigoPostal() == null) {
            throw new NegocioException("Ningún atributo requerido del paciente puede ser nulo.");
        }

        // Validar que los espacios obligatorios hayan sido llenados y no queden solo con espacios
        if (pacienteNuevo.getNombre().isBlank() || pacienteNuevo.getApellidoPaterno().isBlank()
                || pacienteNuevo.getTelefono().isBlank() || pacienteNuevo.getUsuario().getUsuario().isBlank() 
                || pacienteNuevo.getUsuario().getContrasenia().isBlank()
                || pacienteNuevo.getDireccion().getCalle().isBlank() || pacienteNuevo.getDireccion().getNumero().isBlank()
                || pacienteNuevo.getDireccion().getColonia().isBlank() || pacienteNuevo.getDireccion().getCodigoPostal().isBlank()) {
            throw new NegocioException("Verifique que los campos obligatorios estén llenados correctamente.");
        }

        // Validar que la fecha de nacimiento no sea despues de la fecha de hoy
        if (pacienteNuevo.getFechaNacimiento().isAfter(LocalDate.now()) || pacienteNuevo.getFechaNacimiento().isBefore((LocalDate.of(1890, 1, 1)))) {
            throw new NegocioException("La fecha de nacimiento no es válida.");
        }

        // Validar que el numero de telefono tenga los 10 digitos 
        if (!pacienteNuevo.getTelefono().matches("\\d{10}")) {
            throw new NegocioException("El número de teléfono debe de ser de 10 dígitos.");
        }
        
        // Verificar que el teléfono no sea duplicado
        if (pacienteDAO.existeTelefonoPaciente(pacienteNuevo.getTelefono()) && !pacienteExiste.getTelefono().equals(pacienteNuevo.getTelefono())) {
            throw new NegocioException("El teléfono ya se encuentra registrado.");
        }
        
        // Si el apellido paterno esta vacío, lo cambia a null
        if (pacienteNuevo.getApellidoMaterno().isBlank()) {
            pacienteNuevo.setApellidoMaterno(null);
        }
        
        // Encriptar la contraseña
        String contraseniaEncriptada = encriptarContrasenia(pacienteNuevo.getUsuario().getContrasenia());
        pacienteNuevo.getUsuario().setContrasenia(contraseniaEncriptada);
        
        // Cambiar a entidad Paciente
        Paciente paciente = mapper.toEntity(pacienteNuevo);
        
        // Intentar editar los datos del paciente
        try {
            // Regresar si hubo cambios o no
            return pacienteDAO.editarDatosPaciente(paciente);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al editar datos de paciente" , e);
            throw new NegocioException ("Error al editar datos de paciente: ", e);
        }
    }
    
    /**
     * Obtiene el perfil de un paciente mediante la DAO
     * @param email Email del paciente a buscar.
     * @return Perfil si lo encontró, null en caso contrario.
     * @throws NegocioException Si hubo un error al obtener el perfil.
     * @throws PersistenciaException Si hubo un error desde la DAO.
     */
    public PerfilViejoDTO obtenerPerfilPaciente(String email) throws NegocioException, PersistenciaException {
        // Validar que el id sea válido
        if (email == null) {
            throw new NegocioException("El correo electrónico no puede ser nulo.");
        }
        
        // Validar que el correo haya sido ingresado correctamente
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new NegocioException("El formato de correo electrónico ingresado no es válido.");
        }
        
        // Validar si el paciente existe
        Paciente pacienteExiste = pacienteDAO.consultarPacientePorEmail(email);
        
        // Si el paciente no existe
        if (pacienteExiste == null) {
            throw new NegocioException("No existe paciente con email " + email + ".");
        }
        
        // Intentar obtener el perfil del paciente
        try {
            PerfilDTO perfil = pacienteDAO.obtenerPerfilPaciente(email);
            
            // Si no se obtuvo el perfil
            if (perfil == null) {
                return null;
            }
            
            // Si se obtuvo el perfil
            return mapper.toPerfilViejoDTO(perfil);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al obtener un perfil" , e);
            throw new NegocioException ("Error al obtener la información del perfil: ", e);
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