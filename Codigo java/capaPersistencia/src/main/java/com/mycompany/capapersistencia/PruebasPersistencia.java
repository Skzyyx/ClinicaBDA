
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.capapersistencia;

import DAO.CitaDAO;
import DAO.ICitaDAO;
import DAO.IMedicoDAO;
import DAO.IPacienteDAO;
import DAO.IUsuarioDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DAO.UsuarioDAO;
import DTO.PerfilDTO;
import conexion.ConexionDB;
import conexion.IConexion;
import entidades.Cita;
import entidades.Direccion;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PruebasPersistencia {
    private static final IConexion conexionDB = new ConexionDB();
    private static final IPacienteDAO pacienteDAO = new PacienteDAO(conexionDB);
    private static final ICitaDAO citaDAO = new CitaDAO(conexionDB);
    private static final IUsuarioDAO usuarioDAO = new UsuarioDAO(conexionDB);
    private static final IMedicoDAO medicoDAO = new MedicoDAO(conexionDB);
    
    public static void main(String[] args) throws SQLException, PersistenciaException {
        //pruebaConexion();
        //pruebaInsertarPaciente();
        //pruebaEditarDatosPaciente();
        //pruebaVerPerfilPaciente();
        //pruebaConsultarPacientePorEmail();
        //pruebaConsultarUsuario();
        //pruebaPrimerMedicoDisponible();
    }
    
    /**
     * Prueba de conexión
     */
    private static void pruebaConexion() throws SQLException {
        try (Connection con = conexionDB.crearConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("Conexión exitosa a la base de datos.");
            }
            else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (PersistenciaException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
    
    /**
     * Prueba insertar paciente
     */
    private static void pruebaInsertarPaciente() {
        try {
            // Crear instancia de Usuario
            Usuario usuario = new Usuario("maria.gomez@example.com", "claveSegura456", "PACIENTE");

            // Crear instancia de Dirección
            Direccion direccion = new Direccion("Reforma", "789", "Centro", "06010");

            // Crear instancia de Paciente
            Paciente paciente = new Paciente(
                    "María",
                    "Gómez",
                    "Hernández",
                    LocalDate.of(1995, 5, 10),
                    "maria.gomez@example.com",
                    "5556781234",
                    usuario,
                    direccion
            );
            
            boolean pacienteGuardado = pacienteDAO.registrarPaciente(paciente);
            
            if (pacienteGuardado) {
                System.out.println("Paciente registrado con éxito.");
            }
            else {
                System.out.println("No se registró el paciente.");
            }
        } catch (PersistenciaException e) {
            System.out.println("Error al insertar paciente: " + e.getMessage());
        }
    }
    
    /**
     * Prueba consultar paciente por email
     */
    private static void pruebaConsultarPacientePorEmail() {
        String email = "maria.gomez@example.com";
        
        try {
            Paciente paciente = pacienteDAO.consultarPacientePorEmail(email);

            if (paciente == null) {
                System.out.println("No existe el paciente");
            }
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Prueba editar datos paciente
     */
    private static void pruebaEditarDatosPaciente() {
        try {
            // Crear instancia de Usuario
            Usuario usuario = new Usuario("maria.gomez@example.com", "claveSegura456", "PACIENTE");

            // Crear instancia de Dirección
            Direccion direccion = new Direccion("Veracuz", "789", "Centro", "06010");

            // Crear instancia de Paciente
            Paciente paciente = new Paciente(
                    "Perla",
                    "Gómez",
                    "Perez",
                    LocalDate.of(1995, 5, 10),
                    "maria.gomez@example.com",
                    "6442635874",
                    usuario,
                    direccion
            );

            boolean pacienteEditado = pacienteDAO.editarDatosPaciente(paciente);
            
            if (pacienteEditado) {
                System.out.println("Datos editados con éxito");
            } else {
                System.out.println("No se editaron los datos");
            }
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Prueba ver perfil de paciente
     */
    private static void pruebaVerPerfilPaciente() {
        try {
            String email = "maria.gomez@example.com";
            PerfilDTO pacientePerfil = pacienteDAO.obtenerPerfilPaciente(email);
            
            if (pacientePerfil != null) {
                System.out.println("Perfil encontrado");
            }
            else {
                System.out.println("No se obtuvo perfil");
            }
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void pruebaObtenerCitasActivasPorId(int id) throws SQLException {
        System.out.println("--------------------");
        String sql = "CALL obtenerCitasActivasPorId(?)";
        try (Connection con = conexionDB.crearConexion();
                CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, id);
           
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + ", " +
                        rs.getString(2) + ", " +
                        rs.getString(3) + ", " + 
                        rs.getString(4) + ", " + 
                        rs.getString(5) + ", " + 
                        rs.getInt(6) + ", " + 
                        rs.getInt(7)
                );
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(PruebasPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void pruebaRegistrarCitaProgramada() throws SQLException {
        
        try {
            // Crear instancia de Usuario
            Usuario usuario = new Usuario(1, "juan.perez@example.com", "claveSegura456", "PACIENTE");

            // Crear instancia de Dirección
            Direccion direccion = new Direccion("Reforma", "789", "Centro", "06010");

            // Crear instancia de Paciente
            Paciente paciente = new Paciente(
                    1,
                    "Juan",
                    "Perez",
                    "Gomez",
                    LocalDate.of(1990, 05, 14),
                    "juan.perez@example.com",
                    "5551234567",
                    usuario,
                    direccion
            );
            
            // Crear instancia de Usuario
            Usuario usuarioMedico = new Usuario(11, "77777777", "claveSegura456", "MEDICO");
            
            Medico medico = new Medico(1, "Ricardo", "Castillo", "Reyes", "Cardiología", "11111111", "ACTIVO", usuarioMedico);
            
            Cita cita = new Cita(LocalDateTime.of(2025, 02, 19, 18, 24, 54), null, "12345678", "PROGRAMADA", paciente, medico);
            
            boolean resultado = citaDAO.registrarCitaProgramada(cita);
            
            if (resultado) {
                System.out.println("Se pudo agregar la cita.");
            } else {
                System.out.println("No se pudo agregar la cita. ");
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(PruebasPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Prueba consultar usuario
     */
    private static void pruebaConsultarUsuario() throws PersistenciaException {
        String usuario = "anaPer@gmail.com";
        
        Usuario usuarioConsultado = usuarioDAO.consultarUsuario(usuario);
        
        if (usuarioConsultado != null) {
            System.out.println("Usuario encontrado.");
        } else {
            System.out.println("No se encontró usuario.");
        }
    }
    
    private static void pruebaPrimerMedicoDisponible() throws PersistenciaException {
        String cedula = medicoDAO.obtenerPrimerMedicoDisponible();
        
        System.out.println(cedula);
    }
}