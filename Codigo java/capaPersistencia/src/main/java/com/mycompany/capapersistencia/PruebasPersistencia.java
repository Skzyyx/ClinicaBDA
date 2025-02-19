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

    public static void main(String[] args) throws SQLException {
        IConexion conexionDB = new ConexionDB();
        PacienteDAO pacienteDAO = new PacienteDAO(conexionDB);
        
        /**
         * Prueba de conexión
         */
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
        
        /**
         * Prueba insertar activista
         */
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
}
