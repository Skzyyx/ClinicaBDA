/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IUsuarioDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioViejoDTO;
import Exception.NegocioException;
import Mapper.UsuarioMapper;
import conexion.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isabel
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
        if (usuario == null) {
            throw new NegocioException("El usuario no puede ser nulo.");
        }
        
        try {
            Usuario usuarioConsultado = usuarioDAO.consultarUsuario(usuario);
            
            if (usuarioConsultado == null) {
                return null;
            }
            
            return mapper.toViejoDTO(usuarioConsultado);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al consultar un usuario" , e);
            throw new NegocioException ("Error al consultar un usuario: ", e);
        }
    }
}