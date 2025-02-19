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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skyro
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
        String sentenciaSQL = "CALL editarDatosPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)){
            
            cb.setInt(1, paciente.getIdPaciente());
            cb.setString(2, paciente.getNombre());
            cb.setString(3, paciente.getApellidoPaterno());
            cb.setString(4, paciente.getApellidoMaterno());
            cb.setObject(5, paciente.getFechaNacimiento());
            cb.setString(6, paciente.getTelefono());
            cb.setString(7, paciente.getDireccion().getCalle());
            cb.setString(8, paciente.getDireccion().getNumero());
            cb.setString(9, paciente.getDireccion().getColonia());
            cb.setString(10, paciente.getDireccion().getCodigoPostal());
            
            cb.executeUpdate();
            
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al editar paciente", e);
            throw new PersistenciaException("Error al editar datos del paciente: " + e.getMessage());
        }
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws PersistenciaException 
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
}