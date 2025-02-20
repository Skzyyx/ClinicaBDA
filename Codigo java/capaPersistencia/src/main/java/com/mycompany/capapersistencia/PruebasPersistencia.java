/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.capapersistencia;

import DAO.PacienteDAO;
import conexion.ConexionDB;
import conexion.IConexion;
import entidades.Direccion;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PruebasPersistencia {
    private static final IConexion conexionDB = new ConexionDB();
    private static final PacienteDAO pacienteDAO = new PacienteDAO(conexionDB);
    
    public static void main(String[] args) throws SQLException {
        //pruebaConexion();
        //pruebaInsertarPaciente();
        //pruebaConsultarPacientePorId();
        //pruebaEditarDatosPaciente();
        //pruebaVerPerfilPaciente();
        pruebaConsultarPacientePorEmail();
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
     * Prueba consultar paciente por id
     */
    private static void pruebaConsultarPacientePorId() {
        int id = 30;
        try {
            Paciente paciente = pacienteDAO.consultarPacientePorId(id);

            if (paciente == null) {
                System.out.println("No existe el paciente");
            }
        } catch (PersistenciaException e) {
            System.out.println(e.getMessage());
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
                    10,
                    "María",
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
            int id = 1;
            Paciente pacientePerfil = pacienteDAO.verPerfilPaciente(id);
            
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
}