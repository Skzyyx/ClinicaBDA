/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuracion;

import BO.CitaBO;
import BO.ConsultaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import BO.UsuarioBO;
import conexion.ConexionDB;
import conexion.IConexion;

/**
 * Clase DependencyInjector. Su función es inyectar dependencias para evitar
 * acoplamientos directos.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class DependencyInjector {

    /**
     * Crea y devuelve una instancia de {@link PacienteBO}, inyectando la
     * conexión a la base de datos mediante {@link IConexion}.
     *
     * @return Instancia de {@link PacienteBO}
     */
    public static PacienteBO crearPacienteBO() {
        IConexion conexion = new ConexionDB();
        return new PacienteBO(conexion);
    }

    /**
     * Crea y devuelve una instancia de {@link UsuarioBO}, inyectando la
     * conexión a la base de datos mediante {@link IConexion}.
     *
     * @return Instancia de {@link UsuarioBO}
     */
    public static UsuarioBO crearUsuarioBO() {
        IConexion conexion = new ConexionDB();
        return new UsuarioBO(conexion);
    }

    /**
     * Crea y devuelve una instancia de {@link MedicoBO}, inyectando la conexión
     * a la base de datos mediante {@link IConexion}.
     *
     * @return Instancia de {@link MedicoBO}
     */
    public static MedicoBO crearMedicoBO() {
        IConexion conexion = new ConexionDB();
        return new MedicoBO(conexion);
    }

    /**
     * Crea y devuelve una instancia de {@link CitaBO}, inyectando la conexión a
     * la base de datos mediante {@link IConexion}.
     *
     * @return Instancia de {@link CitaBO}
     */
    public static CitaBO crearCitaBO() {
        IConexion conexion = new ConexionDB();
        return new CitaBO(conexion);
    }

    /**
     * Crea y devuelve una instancia de {@link ConsultaBO}, inyectando la
     * conexión a la base de datos mediante {@link IConexion}.
     *
     * @return Instancia de {@link ConsultaBO}
     */
    public static ConsultaBO crearConsultaBO() {
        IConexion conexion = new ConexionDB();
        return new ConsultaBO(conexion);
    }
}
