/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PerfilDTO;
import conexion.IConexion;
import entidades.Cita;
import entidades.Consulta;
import entidades.Direccion;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase PacienteDAO.
 * Representa el DAO para la clase Paciente.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PacienteDAO implements IPacienteDAO {
    private static final Logger logger = Logger.getLogger(Paciente.class.getName());
    
    /** Conexión a utilizar */
    IConexion conexion;
    
    MedicoDAO medicoDAO = new MedicoDAO(conexion);
    
    /**
     * Constructor que inicializa todos los atributos de la clase. 
     * @param conexion Conexión a utlizar
     */
    public PacienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Registra un nuevo paciente.
     * @param paciente Objeto paciente a registrar.
     * @return True si se registró, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar registrar el paciente.
     */
    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        // Sentencia SQL
        String sentenciaSQL = "CALL registrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                CallableStatement cb = con.prepareCall(sentenciaSQL)) {
        
            // Se setean los datos personales del paciente
            cb.setString(1, paciente.getNombre());
            cb.setString(2, paciente.getApellidoPaterno());
            cb.setString(3, paciente.getApellidoMaterno());
            cb.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));
            cb.setString(5, paciente.getEmail());
            cb.setString(6, paciente.getTelefono());
            
            // Se setean los datos de dirección del paciente
            cb.setString(7, paciente.getDireccion().getCalle());
            cb.setString(8, paciente.getDireccion().getNumero());
            cb.setString(9, paciente.getDireccion().getColonia());
            cb.setString(10, paciente.getDireccion().getCodigoPostal());
                
            // Se setean los datos de perfil del paciente
            cb.setString(11, paciente.getUsuario().getContrasenia());
            
            // Se ejecuta el procedimiento
            cb.executeUpdate();
            
            // Si llega aquí, la inserción fue exitosa
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al registrar paciente", e);
            throw new PersistenciaException("No se pudo registrar el paciente.");
        }
    }
    
    /**
     * Consulta un paciente por su email.
     * @param email Email del paciente a consultar.
     * @return Paciente si se encontró, o null.
     * @throws PersistenciaException Si hubo un error al intentar consultar el paciente.
     */
    @Override
    public Paciente consultarPacientePorEmail(String email) throws PersistenciaException {
        Paciente paciente = null;
        
        String sentenciaSQL = "CALL consultarPacientePorEmail(?)";
        
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            cb.setString(1, email);
            
            try (ResultSet rs = cb.executeQuery()) {
                // Si se encontró registro
                if (rs.next()) {
                    // Inicializa la instancia
                    paciente = new Paciente();
                    // Setea los datos del paciente
                    paciente.setIdPaciente(rs.getInt(1));
                    paciente.setNombre(rs.getString(2));
                    paciente.setApellidoPaterno(rs.getString(3));
                    paciente.setApellidoMaterno(rs.getString(4));
                    paciente.setFechaNacimiento(rs.getDate(5).toLocalDate());
                    paciente.setEmail(rs.getString(6));
                    paciente.setTelefono(rs.getString(7));
                    
                    // Setea los datos del usuario asociado al paciente
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt(8));
                    usuario.setUsuario(rs.getString(9));
                    usuario.setContrasenia(rs.getString(10));
                    usuario.setRol(rs.getString(11));
                    paciente.setUsuario(usuario);
                    
                    // Setea los datos de la dirección asociada al paciente
                    Direccion direccion = new Direccion();
                    direccion.setIdDireccion(rs.getInt(12));
                    direccion.setCalle(rs.getString(13));
                    direccion.setNumero(rs.getString(14));
                    direccion.setColonia(rs.getString(15));
                    direccion.setCodigoPostal(rs.getString(16));
                    paciente.setDireccion(direccion);

                    logger.log(Level.INFO, "Paciente encontrado: {0}", paciente);
                }
            }
            
        // Si ocurre un error
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar paciente: " + e.getMessage());
        }
        
        // Regresa el paciente
        return paciente;
    }
    
    /**
     * Edita los datos de un paciente.
     * Solo se permite editar:
     * 	-- nombre
     *  -- apellidos
     *  -- fecha de nacimiento
     *  -- telefono
     *  -- direccion
     *  -- contraseña
     * @param paciente Paciente para editar
     * @return True si se editó el registro, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar editar los datos.
     */
    @Override
    public boolean editarDatosPaciente(Paciente paciente) throws PersistenciaException {
        // Sentencia SQL
        String sentenciaSQL = "CALL editarDatosPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Intentar la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)){
            
            // Settea el valor de los ? del paciente
            cb.setString(1, paciente.getEmail());
            cb.setString(2, paciente.getNombre());
            cb.setString(3, paciente.getApellidoPaterno());
            cb.setString(4, paciente.getApellidoMaterno());
            cb.setObject(5, paciente.getFechaNacimiento());
            cb.setString(6, paciente.getTelefono());
            // Settea el valor de los ? de la dirección del paciente
            cb.setString(7, paciente.getDireccion().getCalle());
            cb.setString(8, paciente.getDireccion().getNumero());
            cb.setString(9, paciente.getDireccion().getColonia());
            cb.setString(10, paciente.getDireccion().getCodigoPostal());
            // Settea la contraseña
            cb.setString(11, paciente.getUsuario().getContrasenia());
            
            // Se ejecuta el procedimiento
            cb.executeUpdate();
            
            // Si llega aquí, la actualización fue exitosa
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al editar paciente", e);
            throw new PersistenciaException("Error al editar datos del paciente: " + e.getMessage());
        }
    }
    
    /**
     * Edita los datos de un paciente, excepto la contraseña
     * @param paciente Paciente para editar
     * @return True si se editó el registro, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar editar los datos.
     */
    @Override
    public boolean editarDatosSinContraPaciente(Paciente paciente) throws PersistenciaException {
        // Sentencia SQL
        String sentenciaSQL = "CALL editarDatosSinContraPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Intentar la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)){
            
            // Settea el valor de los ? del paciente
            cb.setString(1, paciente.getEmail());
            cb.setString(2, paciente.getNombre());
            cb.setString(3, paciente.getApellidoPaterno());
            cb.setString(4, paciente.getApellidoMaterno());
            cb.setObject(5, paciente.getFechaNacimiento());
            cb.setString(6, paciente.getTelefono());
            // Settea el valor de los ? de la dirección del paciente
            cb.setString(7, paciente.getDireccion().getCalle());
            cb.setString(8, paciente.getDireccion().getNumero());
            cb.setString(9, paciente.getDireccion().getColonia());
            cb.setString(10, paciente.getDireccion().getCodigoPostal());
            
            // Se ejecuta el procedimiento
            cb.executeUpdate();
            
            // Si llega aquí, la actualización fue exitosa
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al editar paciente", e);
            throw new PersistenciaException("Error al editar datos del paciente: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene los datos que se muestran en el perfil de un paciente.
     * @param email Email del paciente a buscar.
     * @return Objeto paciente con los datos de perfil.
     * @throws PersistenciaException Si hubo un error al consultar los datos.
     */
    @Override
    public PerfilDTO obtenerPerfilPaciente(String email) throws PersistenciaException {
        // Paciente que se va a regresar
        PerfilDTO perfil = null;
        
        // Sentencia SQL
        String sentenciaSQL = "CALL verPerfilPaciente(?)";
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            // Se setea el valor del id a buscar
            cb.setString(1, email);
            
            // Intenta la consulta
            try (ResultSet rs = cb.executeQuery()) { 
                // Si se encontró registro
                if (rs.next()) {
                    // Inicializa la instancia
                    perfil = new PerfilDTO();
                    // Setea los datos del paciente
                    perfil.setNombre(rs.getString(1));
                    perfil.setApellidoPaterno(rs.getString(2));
                    perfil.setApellidoMaterno(rs.getString(3));
                    perfil.setFechaNacimiento(rs.getDate(4).toLocalDate());
                    perfil.setEdad(rs.getInt(5));
                    perfil.setEmail(rs.getString(6));
                    perfil.setTelefono(rs.getString(7));
                    
                    // Setea los datos de la dirección asociada al paciente
                    Direccion direccion = new Direccion();
                    direccion.setCalle(rs.getString(8));
                    direccion.setNumero(rs.getString(9));
                    direccion.setColonia(rs.getString(10));
                    direccion.setCodigoPostal(rs.getString(11));
                    perfil.setDireccion(direccion);

                    logger.log(Level.INFO, "Perfil encontrado: {0}", perfil);
                }
            }
        // Si ocurre un error
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar paciente: " + e.getMessage());
        }
        
        // Regresa el paciente
        return perfil;
    }
    
    /**
     * Busca si un teléfono ya está en la tabla pacientes.
     * @param telefono Teléfono a buscar.
     * @return True si el teléfono ya existe, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al consultar los datos.
     */
    @Override
    public boolean existeTelefonoPaciente(String telefono) throws PersistenciaException {
        // Sentencia SQL
        String sentenciaSQL = "SELECT COUNT(telefono) FROM pacientes WHERE telefono = ?";
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sentenciaSQL)) {
            
            // Se setea el valor del teléfono a buscar
            ps.setString(1, telefono);
            
            // Intenta la búsqueda
            try (ResultSet rs = ps.executeQuery()) {
                // Si encontró coincidencia
                if (rs.next()) {
                    // Si COUNT(*) devuelve un número mayor a 0, significa que el teléfono ya existe
                    return rs.getInt(1) > 0;
                }
            }
            return false;
        // Si ocurre un error
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar teléfono: " + e.getMessage());
        }
    }
    
    
    /**
     * Consulta las citas activas de un paciente en específico.
     * @param email Email a consultar.
     * @return Lista con citas activas.
     * @throws PersistenciaException Si hubo un error al consultar los datos.
     */
    @Override
    public List<Cita> obtenerCitasActivasPaciente(String email) throws PersistenciaException {
        String sentenciaSQL = "CALL obtenerCitasActivasPaciente(?)";
        List<Cita> citas = new ArrayList<>();
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            // Se setea el valor del id a buscar
            cb.setString(1, email);
            
            // Intenta la consulta
            try (ResultSet rs = cb.executeQuery()) { 
                // Si se encontró registro
                while (rs.next()) {
                    Paciente paciente = new Paciente(
                            rs.getInt(6),
                            rs.getString(14),
                            rs.getString(15),
                            rs.getString(16),
                            rs.getDate(17).toLocalDate(),
                            rs.getString(18),
                            rs.getString(19)
                    );
                    
                    Medico medico = new Medico(
                            rs.getInt(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12),
                            rs.getString(13)
                    );
                    
                    Cita cita = new Cita(
                        rs.getInt(1),
                        rs.getTimestamp(2).toLocalDateTime(),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        paciente,
                        medico
                    );
                    citas.add(cita);
                }
            }
        // Si ocurre un error
        } catch (SQLException e) {
            try {
                throw new PersistenciaException("Error al consultar citas: " + e.getMessage());
            } catch (PersistenciaException ex) {
                Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return citas;
    }
    
    @Override
    public List<Consulta> obtenerConsultasPaciente(String email) {
        String sentenciaSQL = "CALL obtenerConsultasPaciente(?)";
        List<Consulta> consultas = new ArrayList<>();
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            // Se setea el valor del id a buscar
            cb.setString(1, email);
            
            // Intenta la consulta
            try (ResultSet rs = cb.executeQuery()) { 
                // Si se encontró registro
                while (rs.next()) {
                    Paciente paciente = new Paciente ();
                    paciente.setIdPaciente(rs.getInt(11));
                    
                    Medico medico = new Medico();
                    medico.setIdMedico(rs.getInt(12));
                    medico.setEspecialidad(rs.getString(14));
                    medico.setCedula(rs.getString(13));
                    
                    Cita cita = new Cita(
                        rs.getInt(6),
                        rs.getTimestamp(7).toLocalDateTime(),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        paciente,
                        medico
                    );
                    
                    Consulta consulta = new Consulta(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            cita
                    );
                    
                    consultas.add(consulta);
                }
            }
        // Si ocurre un error
        } catch (SQLException e) {
            try {
                throw new PersistenciaException("Error al consultar citas: " + e.getMessage());
            } catch (PersistenciaException ex) {
                Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consultas;
    }
}