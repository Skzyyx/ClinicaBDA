/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Direccion;
import entidades.Usuario;
import java.time.LocalDate;

/**
 * Clase PerfilDTO. Representa un DTO utilizado para recopilar los datos que
 * conforman el perfil del paciente. Se utilizará para pasar la información de
 * perfil a Negocio.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class PerfilDTO {

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
    /* Usuario asociado al paciente. */
    private Usuario usuario;
    /* Dirección del paciente. */
    private Direccion direccion;

    /**
     * Constructor vacío.
     */
    public PerfilDTO() {
    }

    /**
     * Constructor con parámetros para inicializar todos los campos de la clase
     * PerfilDTO.
     *
     * @param nombre Nombre del paciente
     * @param apellidoPaterno Apellido paterno del paciente
     * @param apellidoMaterno Apellido materno del paciente
     * @param fechaNacimiento Fecha de nacimiento del paciente
     * @param edad Edad del paciente
     * @param email Correo electrónico del paciente
     * @param telefono Teléfono del paciente
     * @param usuario Usuario asociado al paciente
     * @param direccion Dirección del paciente
     */
    public PerfilDTO(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, int edad, String email, String telefono, Usuario usuario, Direccion direccion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.email = email;
        this.telefono = telefono;
        this.usuario = usuario;
        this.direccion = direccion;
    }

    /**
     * Retorna el nombre del paciente.
     *
     * @return Nombre del paciente.
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
     * Retorna el apellido paterno del paciente.
     *
     * @return Apellido paterno del paciente.
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
     * Retorna el apellido materno del paciente.
     *
     * @return Apellido materno del paciente.
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
     * Retorna la fecha de nacimiento del paciente.
     *
     * @return Fecha de nacimiento del paciente.
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
     * Retorna la edad del paciente.
     *
     * @return Edad del paciente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del paciente.
     *
     * @param edad Edad del paciente.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Retorna el correo electrónico del paciente.
     *
     * @return Correo electrónico del paciente.
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
     * Retorna el teléfono del paciente.
     *
     * @return Teléfono del paciente.
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
     * Retorna el usuario asociado al paciente.
     *
     * @return Objeto Usuario asociado al paciente.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al paciente.
     *
     * @param usuario Objeto Usuario a asociar al paciente.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna la dirección del paciente.
     *
     * @return Objeto Direccion del paciente.
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del paciente.
     *
     * @param direccion Objeto Direccion a asociar al paciente.
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Método toString().
     *
     * @return String con detalles de los atributos de la clase.
     */
    @Override
    public String toString() {
        return "PerfilDTO{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", email=" + email + ", telefono=" + telefono + ", usuario=" + usuario + ", direccion=" + direccion + '}';
    }
}