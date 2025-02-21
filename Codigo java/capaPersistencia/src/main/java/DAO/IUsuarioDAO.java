/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 * Interfaz IUsuarioDAO.
 * Define los métodos para el manejo de usuario en la base de datos.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public interface IUsuarioDAO {
    
    /**
     * Consulta al Usuario en la Base de Datos
     * @param usuario
     * @return
     * @throws PersistenciaException 
     */
    public Usuario consultarUsuario (String usuario) throws PersistenciaException;
    
}
