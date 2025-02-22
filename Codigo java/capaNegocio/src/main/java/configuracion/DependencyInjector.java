/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuracion;

import BO.MedicoBO;
import BO.PacienteBO;
import BO.UsuarioBO;
import conexion.ConexionDB;
import conexion.IConexion;

/**
 * Clase DependencyInjector.
 * Su función es inyectar dependencias para evitar acoplamientos directos.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class DependencyInjector {
    
    /**
     * Crea y devuelve una instancia de PacienteBO, inyectando la conexión a la base de datos mediante IConexionBD.
     * @return Instancia de PacienteBO
     */
    public static PacienteBO crearPacienteBO() {
        IConexion conexion = new ConexionDB();
        PacienteBO pacienteBO = new PacienteBO(conexion);
        
        return pacienteBO;
    }
    
    /**
     * Crea y devuelve una instancia de UsuarioBO, inyectando la conexión a la base de datos mediante IConexionBD.
     * @return Instancia de UsuarioBO
     */
    public static UsuarioBO crearUsuarioBO() {
        IConexion conexion = new ConexionDB();
        UsuarioBO usuarioBO = new UsuarioBO(conexion);
        
        return usuarioBO;
    }
    
    public static MedicoBO crearMedicoBO() {
        IConexion conexion = new ConexionDB();
        MedicoBO medicoBO = new MedicoBO(conexion);
        
        return medicoBO;
    }
}
