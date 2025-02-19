/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 * Clase Medico.
 * Maneja la entidad Medico, usado principalmente para guardar los datos de inicio de sesión.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class Medico {
    
    /** Identificador único del médico. */
    private int idMedico;
    /** Nombre del médico. */
    private String nombre;
    /** Apellido paterno del médico. */
    private String apellidoPaterno;
    /** Apellido materno del médico. */
    private String apellidoMaterno;
    /** Especialidad médica del médico. */
    private String especialidad;
    /** Cédula profesional del médico. */
    private String cedula;
    /** Estado actual del médico (activo/inactivo). */
    private String estado;
    /** Usuario asociado al médico en el sistema. */
    private Usuario usuario;
    
    /**
     * Constructor vacío de la clase Medico.
     */
    public Medico() {
    }

    /**
     * Constructor que inicializa todos los atributos del médico.
     * 
     * @param idMedico Identificador único del médico.
     * @param nombre Nombre del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     * @param especialidad Especialidad médica del médico.
     * @param cedula Cédula profesional del médico.
     * @param estado Estado actual del médico.
     * @param usuario Usuario asociado al médico.
     */
    public Medico(int idMedico, String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String cedula, String estado, Usuario usuario) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.estado = estado;
        this.usuario = usuario;
    }

    /**
     * Constructor que inicializa todos los atributos excepto el ID.
     * 
     * @param nombre Nombre del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     * @param especialidad Especialidad médica del médico.
     * @param cedula Cédula profesional del médico.
     * @param estado Estado actual del médico.
     * @param usuario Usuario asociado al médico.
     */
    public Medico(String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String cedula, String estado, Usuario usuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.estado = estado;
        this.usuario = usuario;
    }

    /**
     * Obtiene el identificador único del médico.
     * @return ID del médico.
     */
    public int getIdMedico() {
        return idMedico;
    }

    /**
     * Establece el identificador único del médico.
     * @param idMedico ID del médico.
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * Obtiene el nombre del médico.
     * @return Nombre del médico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del médico.
     * @param nombre Nombre del médico.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del médico.
     * @return Apellido paterno del médico.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del médico.
     * @return Apellido materno del médico.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene la especialidad médica del médico.
     * @return Especialidad médica.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad médica del médico.
     * @param especialidad Especialidad médica.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene la cédula profesional del médico.
     * @return Cédula profesional.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula profesional del médico.
     * @param cedula Cédula profesional.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene el estado del médico (activo/inactivo).
     * @return Estado del médico.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del médico (activo/inactivo).
     * @param estado Estado del médico.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario asociado al médico.
     * @return Usuario del médico.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al médico.
     * @param usuario Usuario del médico.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Representación en cadena del objeto Medico.
     * @return Cadena con los datos del médico.
     */
    @Override
    public String toString() {
        return "Medico{" + "idMedico=" + idMedico + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", cedula=" + cedula + ", estado=" + estado + ", usuario=" + usuario + '}';
    }
}