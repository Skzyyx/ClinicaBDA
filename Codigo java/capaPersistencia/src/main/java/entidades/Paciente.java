/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 * Clase Usuario.
 * Maneja la entidad Paciente, usado para guardar los datos de un paciente.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class Paciente {
    
    /** Identificador único del paciente. */
    private int idPaciente;
    /** Nombre del paciente. */
    private String nombre;
    /** Apellido paterno del paciente. */
    private String apellidoPaterno;
    /** Apellido materno del paciente. */
    private String apellidoMaterno;
    /** Fecha de nacimiento del paciente. */
    private LocalDate fechaNacimiento;
    /** Edad del paciente*/
    private int edad;
    /** Correo electrónico del paciente. */
    private String email;
    /** Teléfono del paciente. */
    private String telefono;
    /** Usuario asociado al paciente. */
    private Usuario usuario;
    /** Dirección del paciente. */
    private Direccion direccion;
    
    /**
     * Constructor vacío.
     */
    public Paciente() {
    }
    
    /**
     * Constructor de la clase.
     * Establece todos los atributos de la clase al valor de sus parámetros, excepto edad.
     * @param idPaciente id del paciente
     * @param nombre nombre del paciente
     * @param apellidoPaterno apellido paterno del paciente
     * @param apellidoMaterno apellido materno del paciente
     * @param fechaNacimiento fecha de nacimiento del paciente
     * @param email correo electrónico del paciente
     * @param telefono teléfono del paciente
     * @param usuario usuario del paciente
     * @param direccion direccion del paciente
     */
    public Paciente(int idPaciente, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String email, String telefono, Usuario usuario, Direccion direccion) {
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
     * Contructor de la clase.
     * Establece los atributos de la clase al valor de sus parámetros, excepto idPaciente y edad.
     * @param nombre nombre del paciente
     * @param apellidoPaterno apellido paterno del paciente
     * @param apellidoMaterno apellido materno del paciente
     * @param fechaNacimiento fecha de nacimiento del paciente
     * @param email correo electrónico del paciente
     * @param telefono teléfono del paciente
     * @param usuario usuario del paciente
     * @param direccion direccion del paciente
     */
    public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String email, String telefono, Usuario usuario, Direccion direccion) {
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
     * Constructor de la clase.
     * Establece los atributos de la clase al valor de sus parámetros, excepto idPaciente.
     * @param nombre nombre del paciente
     * @param apellidoPaterno apellido paterno del paciente
     * @param apellidoMaterno apellido materno del paciente
     * @param fechaNacimiento fecha de nacimiento del paciente
     * @param edad edad del paciente
     * @param email correo electrónico del paciente
     * @param telefono teléfono del paciente
     * @param usuario usuario del paciente
     * @param direccion direccion del paciente
     */
    public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, int edad, String email, String telefono, Usuario usuario, Direccion direccion) {
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
     * Método getIdPaciente().
     * Obtiene el id del paciente
     * @return id del paciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }
    
    /**
     * Método setIdPaciente(int idPaciente).
     * Establece el idPaciente al valor de su parámetro
     * @param idPaciente valor de idPaciente para actualizar
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    /**
     * Método getNombre().
     * Obtiene el nombre del paciente
     * @return nombre del paciente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método setNombre(String nombre).
     * Establece el nombre al valor de su parámetro
     * @param nombre valor de nombre para actualizar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método getApellidoPaterno().
     * Obtiene el apellido paterno del paciente
     * @return apellido paterno del paciente
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    
    /**
     * Método setApellidoPaterno(String apellidoPaterno).
     * Establece el apellidoPaterno al valor de su parámetro
     * @param apellidoPaterno valor de apellidoPaterno para actualizar
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    
    /**
     * Método getApellidoMaterno().
     * Obtiene el apellido materno del paciente
     * @return apellido materno del paciente
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    
    /**
     * Método setApellidoMaterno(String apellidoMaterno).
     * Establece el apellidoMaterno al valor de su parámetro
     * @param apellidoMaterno valor de apellidoMaterno para actualizar
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    /**
     * Método getFechaNacimiento().
     * Obtiene la fecha de nacimiento del paciente
     * @return fecha de nacimiento del paciente
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    /**
     * Método setFechaNacimiento(LocalDate fechaNacimiento).
     * Establece la fechaNacimiento al valor de su parámetro
     * @param fechaNacimiento valor de fechaNacimiento para actualizar
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Método getEdad(). 
     * Obtiene la edad del paciente
     * @return edad del paciente
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * Método setEdad(int edad).
     * @param edad valor de edad para actualizar
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método getEmail().
     * Obtiene el correo electrónico del paciente
     * @return correo electrónico del paciente
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Método setEmail(String email).
     * Establece el email al valor de su parámetro
     * @param email valor de email para actualizar
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Método getTelefono().
     * Obtiene el teléfono del paciente
     * @return teléfono del paciente
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * Método setTelefono(String telefono).
     * Establece el telefono al valor de su parámetro
     * @param telefono valor de telefono para actualizar
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Método getUsuario().
     * Obtiene el usuario del paciente
     * @return Usuario del paciente
     */
    public Usuario getUsuario() {
        return usuario;
    }
    
    /**
     * Método setUsuario(int idUsuario).
     * Establece el Usuario a la instancia del su parámetro
     * @param usuario instancia del usuario para actualizar
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Método getDireccion().
     * Obtiene la direccion del paciente
     * @return Direccion del paciente
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Método setDireccion(Direccion direccion).
     * Establece la direccion a la instancia del su parámetro
     * @param direccion instancia de la direccion para actualizar
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Método toString().
     *
     * @return String con detalles de los atributos de la clase
     */
    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", email=" + email + ", telefono=" + telefono + ", usuario=" + usuario + ", direccion=" + direccion + '}';
    }
}