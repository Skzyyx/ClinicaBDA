/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Usuario;

/**
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class MedicoNuevoDTO {

    /**
     * Nombre del médico.
     */
    private String nombre;

    /**
     * Apellido paterno del médico.
     */
    private String apellidoPaterno;

    /**
     * Apellido materno del médico.
     */
    private String apellidoMaterno;

    /**
     * Especialidad del médico.
     */
    private String especialidad;

    /**
     * Cédula profesional del médico.
     */
    private String cedula;

    /**
     * Estado del médico (por ejemplo, activo, inactivo).
     */
    private String estado;

    /**
     * Usuario asociado al médico, posiblemente para autenticación o gestión de
     * acceso.
     */
    private Usuario usuario;

    /**
     * Constructor de la clase MedicoNuevoDTO para inicializar todos los
     * atributos.
     *
     * @param nombre Nombre del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     * @param especialidad Especialidad del médico.
     * @param cedula Cédula profesional del médico.
     * @param estado Estado del médico (activo, inactivo, etc.).
     * @param usuario Usuario asociado al médico para gestión de acceso.
     */
    public MedicoNuevoDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String cedula, String estado, Usuario usuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.estado = estado;
        this.usuario = usuario;
    }

    // Métodos getters y setters
    /**
     * Obtiene el nombre del médico.
     *
     * @return nombre El nombre del médico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del médico.
     *
     * @param nombre El nombre del médico.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del médico.
     *
     * @return apellidoPaterno El apellido paterno del médico.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del médico.
     *
     * @param apellidoPaterno El apellido paterno del médico.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del médico.
     *
     * @return apellidoMaterno El apellido materno del médico.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del médico.
     *
     * @param apellidoMaterno El apellido materno del médico.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene la especialidad del médico.
     *
     * @return especialidad La especialidad del médico.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del médico.
     *
     * @param especialidad La especialidad del médico.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene la cédula profesional del médico.
     *
     * @return cedula La cédula profesional del médico.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula profesional del médico.
     *
     * @param cedula La cédula profesional del médico.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene el estado del médico (por ejemplo, activo, inactivo).
     *
     * @return estado El estado del médico.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del médico.
     *
     * @param estado El estado del médico.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario asociado al médico.
     *
     * @return usuario El usuario asociado al médico.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado al médico.
     *
     * @param usuario El usuario asociado al médico.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve una representación en formato de cadena del médico, incluyendo
     * todos sus atributos.
     *
     * @return String Una cadena con la información completa del médico.
     */
    @Override
    public String toString() {
        return "MedicoNuevoDTO{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", cedula=" + cedula + ", estado=" + estado + ", usuario=" + usuario + '}';
    }
}
