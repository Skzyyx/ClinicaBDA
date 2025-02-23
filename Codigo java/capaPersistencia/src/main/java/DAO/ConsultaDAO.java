/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;

/**
 *
 * @author Isabel
 */
public class ConsultaDAO implements IConsultaDAO {
    /**
     * Conexión a la base de datos.
     */
    private final IConexion conexion;

    /**
     * Constructor de la clase ConsultaDAO.
     * 
     * @param conexion Objeto que maneja la conexión a la base de datos.
     */
    public ConsultaDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    
}
