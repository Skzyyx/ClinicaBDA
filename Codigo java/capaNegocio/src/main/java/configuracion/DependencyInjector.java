/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuracion;

import BO.PacienteBO;
import conexion.ConexionDB;
import conexion.IConexion;

/**
 *
 * @author skyro
 */
public class DependencyInjector {
    
    public static PacienteBO crearPacienteBO() {
        IConexion conexion = new ConexionDB();
        PacienteBO pacienteBO = new PacienteBO(conexion);
        
        return pacienteBO;
    }
}
