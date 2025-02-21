/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Direccion;
import java.time.LocalDate;

/**
 * Clase PerfilViejoDTO.
 * Representa un DTO utilizado para recopilar de perfil de paciente.
 * Se utilizará para pasar la información de perfil a Presentación. 
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PerfilViejoDTO {
    /* Nombre del paciente. */
    private String nombre;
    /* Apellido paterno del paciente. */
    private String apellidoPaterno;
    /* Apellido materno del paciente. */
    private String apellidoMaterno;
    /* Fecha de nacimiento del paciente. */
    private LocalDate fechaNacimiento;
    /* Edad del paciente */
    private int edad;
    /* Correo electrónico del paciente. */
    private String email;
    /* Teléfono del paciente. */
    private String telefono;
    /* Dirección del paciente. */
    private Direccion direccion;
    
    /**
     * Constructor vacío.
     */
    public PerfilViejoDTO() {
    }
    
    /**
     * Constructor con parámetros para inicializar todos los campos de la clase PerfilNuevoDTO.
     * @param nombre Nombre del paciente
     * @param apellidoPaterno Apellido paterno del paciente
     * @param apellidoMaterno Apellido materno del paciente
     * @param fechaNacimiento Fecha de nacimiento del paciente
     * @param email Correo electrónico del paciente
     * @param telefono Teléfono del paciente
     * @param direccion Dirección del paciente
     */
    public PerfilViejoDTO(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, int edad, String email, String telefono, Direccion direccion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Método toString().
     * @return String con detalles de los atributos de la clase.
     */
    @Override
    public String toString() {
        return "PerfilViejoDTO{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
}