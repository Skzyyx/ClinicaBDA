/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase UsuarioDAO.
 * Representa el DAO para la clase Usuario
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class UsuarioDAO implements IUsuarioDAO {
    private static final Logger logger = Logger.getLogger(Usuario.class.getName());

    /**
     * Conexion que se va utilizar
     */
    IConexion conexion;

    /**
     * Metodo constructor que inicializa todos los atributos de la clase
     *
     * @param conexion Conexion a utilizar
     */
    public UsuarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Consultar si un usuario existente de la base de datos
     *
     * @param usuario Usuario a buscar.
     * @return Usuario si se encontró, null en caso contrario.
     * @throws PersistenciaException Si hubo un error al consultar el usuario.
     */
    @Override
    public Usuario consultarUsuario(String usuario) throws PersistenciaException {
        Usuario usuarioEncontrado = null;

        //sentencia SQL que selecciona todos los campos de 
        String consultaSQL = "SELECT idUsuario,usuario,contrasenia,rol FROM usuarios WHERE usuario =?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            //Asignamos el parametro usuario de la consulta
            ps.setString(1, usuario);

            //Ejecutamos la consulta
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setIdUsuario(rs.getInt("idUsuario"));
                    usuarioEncontrado.setUsuario(rs.getString("usuario"));
                    usuarioEncontrado.setContrasenia(rs.getString("contrasenia"));
                    usuarioEncontrado.setRol(rs.getString("rol"));
                    
                    logger.log(Level.INFO, "Usuario encontrado: {0}", usuarioEncontrado);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar el usuario: "+ e.getMessage());
        }
        return usuarioEncontrado;
    }
}