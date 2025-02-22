/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Direccion;
import entidades.Usuario;
import java.time.LocalDate;

/**
 * Clase PacienteNuevoDTO.
 * Esta clase representa un DTO utilizado para la creación de un paciente, 
 * que se utilizará para ingresar datos de pacientes en la base de datos. 
 * No contiene el ID del paciente, ya que este será generado automáticamente al momento de la inserción.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PacienteNuevoDTO {
    
    /* Nombre del paciente. */
    private String nombre;
    /* Apellido paterno del paciente. */
    private String apellidoPaterno;
    /* Apellido materno del paciente. */
    private String apellidoMaterno;
    /* Fecha de nacimiento del paciente. */
    private LocalDate fechaNacimiento;
    /* Correo electrónico del paciente. */
    private String email;
    /* Teléfono del paciente. */
    private String telefono;
    /* Usuario asociado al paciente. */
    private UsuarioNuevoDTO usuario;
    /* Dirección del paciente. */
    private DireccionNuevoDTO direccion;

    /**
     * Constructor vacío para la clase PacienteNuevoDTO.
     * Utilizado por frameworks de serialización/deserialización o por otros métodos que requieran un objeto vacío.
     */
    public PacienteNuevoDTO() {
    }

    /**
     * Constructor con parámetros para inicializar todos los campos de la clase PacienteNuevoDTO.
     * 
     * @param nombre Nombre del paciente
     * @param apellidoPaterno Apellido paterno del paciente
     * @param apellidoMaterno Apellido materno del paciente
     * @param fechaNacimiento Fecha de nacimiento del paciente
     * @param email Correo electrónico del paciente
     * @param telefono Teléfono del paciente
     * @param usuario Usuario asociado al paciente
     * @param direccion Dirección del paciente
     */
    public PacienteNuevoDTO(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String email, String telefono, UsuarioNuevoDTO usuario, DireccionNuevoDTO direccion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.usuario = usuario;
        this.direccion = direccion;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public UsuarioNuevoDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioNuevoDTO usuario) {
        this.usuario = usuario;
    }

    public DireccionNuevoDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionNuevoDTO direccion) {
        this.direccion = direccion;
    }

    /**
     * Método toString para mostrar los detalles del paciente en formato de texto.
     * 
     * @return Cadena con los detalles del paciente.
     */
    @Override
    public String toString() {
        return "PacienteNuevoDTO{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", email=" + email + ", telefono=" + telefono + ", usuario=" + usuario + ", direccion=" + direccion + '}';
    }
}