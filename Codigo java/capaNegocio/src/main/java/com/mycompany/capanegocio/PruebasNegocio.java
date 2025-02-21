/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.capanegocio;

import BO.PacienteBO;
import DTO.PacienteNuevoDTO;
import DTO.PerfilViejoDTO;
import Exception.NegocioException;
import at.favre.lib.crypto.bcrypt.BCrypt;
import conexion.ConexionDB;
import conexion.IConexion;
import entidades.Direccion;
import entidades.Usuario;
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
    
    public static void main(String[] args) throws PersistenciaException, NegocioException {
        //pruebaRegistrarPaciente();
        pruebaObtenerPerfilPaciente();
        pruebaEncriptarContrasenia();
    }
    
    /**
     * Prueba registrar paciente
     */
    private static void pruebaRegistrarPaciente() throws PersistenciaException {
        try {
            // Crear instancia de Usuario
            Usuario usuario = new Usuario("anaPer@gmail.com", "seguraClave2024", "PACIENTE");

            // Crear instancia de Dirección
            Direccion direccion = new Direccion("Calle Reforma", "102", "Centro", "85000");

            PacienteNuevoDTO pacienteAGuardar = new PacienteNuevoDTO("Ana", "Pérez", "López", LocalDate.of(1992, 8, 25), "anaPer@gmail.com", "6666666666", usuario, direccion);

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
            System.out.println("Error al obtener perfil paciente: " + e.getMessage());
        }
    }
    
    /**
     * Prueba de encriptar contraseña
     */
    private static void pruebaEncriptarContrasenia() {
        String password = "1234";
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
}