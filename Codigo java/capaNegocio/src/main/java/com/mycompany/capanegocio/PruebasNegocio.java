/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.capanegocio;

import BO.ConsultaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import BO.UsuarioBO;
import DTO.DireccionNuevoDTO;
import DTO.PacienteNuevoDTO;
import DTO.PerfilViejoDTO;
import DTO.SesionNuevoDTO;
import DTO.UsuarioNuevoDTO;
import DTO.UsuarioViejoDTO;
import Exception.NegocioException;
import at.favre.lib.crypto.bcrypt.BCrypt;
import conexion.ConexionDB;
import conexion.IConexion;
import excepciones.PersistenciaException;
import java.time.LocalDate;

/**
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PruebasNegocio {
    private static final IConexion conexion = new ConexionDB();
    private static final PacienteBO pacienteBO = new PacienteBO(conexion);
    private static final UsuarioBO usuarioBO = new UsuarioBO(conexion);
    private static final MedicoBO medicoBO = new MedicoBO(conexion);
    private static final ConsultaBO consultaBO = new ConsultaBO(conexion);
            
    public static void main(String[] args) throws PersistenciaException, NegocioException {
        //pruebaRegistrarPaciente();
        //pruebaObtenerPerfilPaciente();
        //pruebaEditarDatosPaciente();
        //pruebaEncriptarContrasenia();
        //pruebaConsultarUsuario();
        //pruebaValidarSesion();
    }
    
    /**
     * Prueba registrar paciente
     */
    private static void pruebaRegistrarPaciente() throws PersistenciaException {
        try {
            // Crear instancia de Usuario
            UsuarioNuevoDTO usuario = new UsuarioNuevoDTO("sof@gmail.com", "sof", "PACIENTE");

            // Crear instancia de Dirección
            DireccionNuevoDTO direccion = new DireccionNuevoDTO("Calle 200", "102", "Orados", "85150");

            PacienteNuevoDTO pacienteAGuardar = new PacienteNuevoDTO("Sofia", "Villegas", "Cinco", LocalDate.of(1992, 8, 25), "sof@gmail.com", "6441259873", usuario, direccion);

            boolean pacienteRegistrado = pacienteBO.registrarPaciente(pacienteAGuardar);
            
            if (pacienteRegistrado) {
                System.out.println("Paciente registrado con éxito.");
            } else {
                System.out.println("No se registró el paciente.");
            }
        } catch (NegocioException e) {
            System.out.println("Error al insertar paciente: " + e.getMessage());
        }
    }
    
    /**
     * Prueba ver perfil paciente
     */
    private static void pruebaObtenerPerfilPaciente() throws NegocioException, PersistenciaException {
        try {
            String email = "anaPer@gmail.com";
            
            PerfilViejoDTO perfil = pacienteBO.obtenerPerfilPaciente(email);
            
            if (perfil != null) {
                System.out.println("Se encontró perfil.");
            } else {
                System.out.println("No se encontró perfil.");
            }
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void pruebaEditarDatosPaciente() throws PersistenciaException {
        try {
            String email = "maria.gomez@example.com";
            
            // Crear instancia de Usuario
            UsuarioNuevoDTO usuario = new UsuarioNuevoDTO("maria.gomez@example.com", "hola", "PACIENTE");

            // Crear instancia de Dirección
            DireccionNuevoDTO direccion = new DireccionNuevoDTO("Puebla", "111", "Centro", "06010");

            // Crear instancia de Paciente
            PacienteNuevoDTO pacienteNuevo = new PacienteNuevoDTO(
                    "Karla",
                    "Rosas",
                    "",
                    LocalDate.of(1980, 5, 10),
                    "maria.gomez@example.com",
                    "6442635871",
                    usuario,
                    direccion
            );
            
            boolean datosEditados = pacienteBO.editarDatosPaciente(email, pacienteNuevo);
            
            if (datosEditados) {
                System.out.println("Datos editados con éxito.");
            } else {
                System.out.println("No se editaron los datos.");
            }
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Prueba de encriptar contraseña
     */
    private static void pruebaEncriptarContrasenia() {
        String password = "pedro";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        System.out.println(bcryptHashString);
        
        // Compara la contraseña en texto (password.toCharArray()) con el hash (bcryptHashString)
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        
        // Comprobar si la contraseña es correcta
        if (result.verified) {
            System.out.println("Contraseña correcta.");
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }
    
    /**
     * Prueba consultar usuario
     */
    private static void pruebaConsultarUsuario() throws NegocioException {
        String usuario = "anaPer@gmail.com";
        
        UsuarioViejoDTO usuarioEncontrado = usuarioBO.consultarUsuario(usuario);
        
        if (usuarioEncontrado != null) {
            System.out.println("Usuario encontrado.");
        } else {
            System.out.println("No se encontró usuario.");
        }
    }
    
    /**
     * Prueba validar sesion
     */
    private static void pruebaValidarSesion() throws NegocioException {
        SesionNuevoDTO sesion = new SesionNuevoDTO("anazzz@gmail.com", "contra");
        
        boolean valido = usuarioBO.autenticarSesion(sesion);
        if (valido) {
            System.out.println("Sesión válida.");
        } else {
            System.out.println("La sesión no es válida.");
        }
    }   
}