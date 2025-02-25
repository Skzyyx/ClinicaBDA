/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDate;

/**
 * Clase PacienteNuevoDTO. Esta clase representa un DTO utilizado para la
 * creación de un paciente, que se utilizará para ingresar datos de pacientes en
 * la base de datos. No contiene el ID del paciente, ya que este será generado
 * automáticamente al momento de la inserción.
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
     * Constructor vacío para la clase PacienteNuevoDTO. Utilizado por
     * frameworks de serialización/deserialización o por otros métodos que
     * requieran un objeto vacío.
     */
    public PacienteNuevoDTO() {
    }

    /**
     * Constructor con parámetros para inicializar todos los campos de la clase
     * PacienteNuevoDTO.
     *
     * @param nombre Nombre del paciente.
     * @param apellidoPaterno Apellido paterno del paciente.
     * @param apellidoMaterno Apellido materno del paciente.
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     * @param email Correo electrónico del paciente.
     * @param telefono Teléfono del paciente.
     * @param usuario Usuario asociado al paciente.
     * @param direccion Dirección del paciente.
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
    /**
     * Obtiene el nombre del paciente.
     *
     * @return nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del paciente.
     *
     * @param nombre Nombre del paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del paciente.
     *
     * @return apellido paterno del paciente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del paciente.
     *
     * @param apellidoPaterno Apellido paterno del paciente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del paciente.
     *
     * @return apellido materno del paciente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del paciente.
     *
     * @param apellidoMaterno Apellido materno del paciente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene la fecha de nacimiento del paciente.
     *
     * @return fecha de nacimiento del paciente.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del paciente.
     *
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el correo electrónico del paciente.
     *
     * @return correo electrónico del paciente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del paciente.
     *
     * @param email Correo electrónico del paciente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el teléfono del paciente.
     *
     * @return teléfono del paciente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del paciente.
     *
     * @param telefono Teléfono del paciente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el usuario asociado al paciente.
     *
     * @return usuario asociado al paciente.
     */
    public UsuarioNuevoDTO getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al paciente.
     *
     * @param usuario Usuario asociado al paciente.
     */
    public void setUsuario(UsuarioNuevoDTO usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la dirección del paciente.
     *
     * @return dirección del paciente.
     */
    public DireccionNuevoDTO getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del paciente.
     *
     * @param direccion Dirección del paciente.
     */
    public void setDireccion(DireccionNuevoDTO direccion) {
        this.direccion = direccion;
    }

    /**
     * Método toString para mostrar los detalles del paciente en formato de
     * texto.
     *
     * @return Cadena con los detalles del paciente.
     */
    @Override
    public String toString() {
        return "PacienteNuevoDTO{"
                + "nombre=" + nombre
                + ", apellidoPaterno=" + apellidoPaterno
                + ", apellidoMaterno=" + apellidoMaterno
                + ", fechaNacimiento=" + fechaNacimiento
                + ", email=" + email
                + ", telefono=" + telefono
                + ", usuario=" + usuario
                + ", direccion=" + direccion
                + '}';
    }
}
