/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IUsuarioDAO;
import DAO.UsuarioDAO;
import DTO.SesionNuevoDTO;
import DTO.UsuarioViejoDTO;
import Exception.NegocioException;
import Mapper.UsuarioMapper;
import at.favre.lib.crypto.bcrypt.BCrypt;
import conexion.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase UsuarioBO.
 * Representa el BO para la clase Usuario
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class UsuarioBO {
    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());
    
    private final IUsuarioDAO usuarioDAO;
    
    private final UsuarioMapper mapper = new UsuarioMapper();
    
    /**
     * Constructor de la clase.
     * @param conexion a utilizar;
     */
    public UsuarioBO(IConexion conexion) {
        this.usuarioDAO = new UsuarioDAO(conexion);
    }
    
    /**
     * Consulta un usuario específico mediante la DAO.
     * @param usuario Usuario a consultar.
     * @return Usuario si se encontró, null en caso contrario.
     * @throws NegocioException Si hubo un error al consultar el usuario.
     */
    public UsuarioViejoDTO consultarUsuario(String usuario) throws NegocioException {
        // Validar que el usuario no sea nulo
        if (usuario == null) {
            throw new NegocioException("El usuario no puede ser nulo.");
        }
        
        // Intentar consultar al usuario
        try {
            Usuario usuarioConsultado = usuarioDAO.consultarUsuario(usuario);
            
            // Si el usuario no existe
            if (usuarioConsultado == null) {
                return null;
            }
            
            // Si el usuario sí existe, lo convierte y regresa
            return mapper.toViejoDTO(usuarioConsultado);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al consultar un usuario" , e);
            throw new NegocioException ("Error al consultar un usuario: ", e);
        }
    }
    
    /**
     * Autenticar las credenciales de una sesión de un usuario
     * @param sesion DTO con los datos de sesion a autenticar
     * @return True si las credenciales fueron válidas, false en caso contrario.
     * @throws NegocioException Si hubo un error al intentar autenticar credenciales.
     */
    public boolean autenticarSesion(SesionNuevoDTO sesion) throws NegocioException {
        // Validar que la sesion no sea nula
        if (sesion == null) {
            throw new NegocioException("El usuario no puede ser nulo.");
        }

        // Validar que el usuario no sea nulo
        if (sesion.getUsuario() == null) {
            throw new NegocioException("El usuario no puede ser nulo.");
        }
        
        // Validar que la contraseña no sea nula
        if (sesion.getContrasenia() == null) {
            throw new NegocioException("La contraseña no puede ser nula.");
        }
        
        try {
            // Intentar consultar al usuario
            Usuario usuarioConsultado = usuarioDAO.consultarUsuario(sesion.getUsuario());
            
            // Si el usuario no existe
            if (usuarioConsultado == null) {
                throw new NegocioException("El usuario no existe.");
            }
            
            // Compara la contraseña en texto (password.toCharArray()) con la contraseña que se obtuvo de la base (encriptada)
            BCrypt.Result contraseniaValida = BCrypt.verifyer().verify(sesion.getContrasenia().toCharArray(), usuarioConsultado.getContrasenia());
            
            // Regresar si el usuario y la contraseña coinciden o no
            return usuarioConsultado.getUsuario().equals(sesion.getUsuario()) && contraseniaValida.verified;
        // En caso de que ocurra un error, lanza una excepcion   
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al autenticar un usuario." , e);
            throw new NegocioException ("Error al autenticar un usuario." + e.getMessage());
        }
    }
}