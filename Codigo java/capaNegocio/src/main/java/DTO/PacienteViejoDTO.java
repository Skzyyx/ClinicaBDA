/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Direccion;
import entidades.Usuario;
import java.time.LocalDate;

/**
 * Clase PacienteViejoDTO.
 * Este DTO representa los datos del paciente que se van a mostrar o utilizar para realizar peticiones.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class PacienteViejoDTO {
    
    /* Identificador único del paciente. */
    private String idPaciente;
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
    private Usuario usuario;
    /* Dirección del paciente. */
    private Direccion direccion;
    
    /**
     * Constructor de la clase PacienteViejoDTO con todos los atributos, incluido el ID del paciente.
     * 
     * @param idPaciente El identificador único del paciente.
     * @param nombre El nombre del paciente.
     * @param apellidoPaterno El apellido paterno del paciente.
     * @param apellidoMaterno El apellido materno del paciente.
     * @param fechaNacimiento La fecha de nacimiento del paciente.
     * @param email El correo electrónico del paciente.
     * @param telefono El número de teléfono del paciente.
     * @param usuario El objeto Usuario relacionado con el paciente.
     * @param direccion El objeto Direccion relacionado con el paciente.
     */

    public PacienteViejoDTO(String idPaciente, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String email, String telefono, Usuario usuario, Direccion direccion) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.usuario = usuario;
        this.direccion = direccion;
    }

    /**
     * Constructor de la clase PacienteViejoDTO sin el atributo ID del paciente.
     * 
     * @param nombre El nombre del paciente.
     * @param apellidoPaterno El apellido paterno del paciente.
     * @param apellidoMaterno El apellido materno del paciente.
     * @param fechaNacimiento La fecha de nacimiento del paciente.
     * @param email El correo electrónico del paciente.
     * @param telefono El número de teléfono del paciente.
     * @param usuario El objeto Usuario relacionado con el paciente.
     * @param direccion El objeto Direccion relacionado con el paciente.
     */
    public PacienteViejoDTO(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String email, String telefono, Usuario usuario, Direccion direccion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.usuario = usuario;
        this.direccion = direccion;
    }

    /**
     * Obtiene el identificador único del paciente.
     * 
     * @return El ID del paciente.
     */
    public String getIdPaciente() {
        return idPaciente;
    }

    /**
     * Establece el identificador único del paciente.
     * 
     * @param idPaciente El ID del paciente.
     */
    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * Obtiene el nombre del paciente.
     * 
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del paciente.
     * 
     * @param nombre El nombre del paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del paciente.
     * 
     * @return El apellido paterno del paciente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del paciente.
     * 
     * @param apellidoPaterno El apellido paterno del paciente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del paciente.
     * 
     * @return El apellido materno del paciente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del paciente.
     * 
     * @param apellidoMaterno El apellido materno del paciente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene la fecha de nacimiento del paciente.
     * 
     * @return La fecha de nacimiento del paciente.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del paciente.
     * 
     * @param fechaNacimiento La fecha de nacimiento del paciente.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el correo electrónico del paciente.
     * 
     * @return El correo electrónico del paciente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del paciente.
     * 
     * @param email El correo electrónico del paciente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono del paciente.
     * 
     * @return El número de teléfono del paciente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del paciente.
     * 
     * @param telefono El número de teléfono del paciente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el objeto Usuario relacionado con el paciente.
     * 
     * @return El objeto Usuario relacionado con el paciente.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el objeto Usuario relacionado con el paciente.
     * 
     * @param usuario El objeto Usuario relacionado con el paciente.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el objeto Direccion relacionado con el paciente.
     * 
     * @return El objeto Direccion relacionado con el paciente.
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece el objeto Direccion relacionado con el paciente.
     * 
     * @param direccion El objeto Direccion relacionado con el paciente.
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve una representación en cadena de texto del objeto PacienteViejoDTO.
     * 
     * @return Una cadena que representa el objeto PacienteViejoDTO
     */
    @Override
    public String toString() {
        return "PacienteViejoDTO{" + "idPaciente=" + idPaciente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", email=" + email + ", telefono=" + telefono + ", usuario=" + usuario + ", direccion=" + direccion + '}';
    }
}