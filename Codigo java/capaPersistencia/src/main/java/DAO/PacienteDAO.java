/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Direccion;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
     * Consulta un paciente por su id.
     * @param id Id del paciente a consultar.
     * @return Paciente si se encontró, o null.
     * @throws PersistenciaException Si hubo un error al intentar consultar el paciente.
     */
    @Override
    public Paciente consultarPacientePorId(int id) throws PersistenciaException {
        // Paciente que se va a regresar
        Paciente paciente = null;
        
        // Sentencia SQL
        String sentenciaSQL = "CALL consultarPacientePorId(?)";
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            // Se setea el valor del id a buscar
            cb.setInt(1, id);
            
            // Intenta la consulta
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
     * @param paciente Paciente para editar
     * @return True si se editó el registro, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar editar los datos.
     */
    @Override
    public boolean editarDatosPaciente(Paciente paciente) throws PersistenciaException {
        // Sentencia SQL
        String sentenciaSQL = "CALL editarDatosPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Intentar la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)){
            
            // Settea el valor de los ? del paciente
            cb.setInt(1, paciente.getIdPaciente());
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
     * @param id Id del paciente a consultar.
     * @return Objeto paciente con los datos de perfil.
     * @throws PersistenciaException Si hubo un error al consultar los datos.
     */
    @Override
    public Paciente verPerfilPaciente(int id) throws PersistenciaException {
        // Paciente que se va a regresar
        Paciente paciente = null;
        
        // Sentencia SQL
        String sentenciaSQL = "CALL verPerfilPaciente(?)";
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            // Se setea el valor del id a buscar
            cb.setInt(1, id);
            
            // Intenta la consulta
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
                    paciente.setEdad(rs.getInt(6));
                    paciente.setEmail(rs.getString(7));
                    paciente.setTelefono(rs.getString(8));
                    
                    // Setea los datos de la dirección asociada al paciente
                    Direccion direccion = new Direccion();
                    direccion.setCalle(rs.getString(9));
                    direccion.setNumero(rs.getString(10));
                    direccion.setColonia(rs.getString(11));
                    direccion.setCodigoPostal(rs.getString(12));
                    paciente.setDireccion(direccion);

                    logger.log(Level.INFO, "Perfil encontrado: {0}", paciente);
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
}