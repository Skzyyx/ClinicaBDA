/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Clase UsuarioNuevoDTO.
 * Esta clase representa un DTO utilizado para la creación de un usuario, 
 * que se utilizará para ingresar datos de pacientes en la base de datos. 
 * No contiene el ID del paciente, ya que este será generado automáticamente al momento de la inserción.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class UsuarioNuevoDTO {
    /** User (email para paciente, cédula profesional para médico). */
    private String usuario;
    /** Contraseña del usuario. */
    private String contrasenia;
    /** Rol/tipo de usuario (paciente o médico). */
    private String rol;
    
    /**
     * Constructor vacío.
     */
    public UsuarioNuevoDTO() {
    }
    
    /**
     * Constructor con parámetros para inicializar todos los campos de la clase.
     * @param usuario User del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param rol Rol del usuario.
     */
    public UsuarioNuevoDTO(String usuario, String contrasenia, String rol) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    /**
     * Método toString().
     * @return String con detalles de los atributos de la clase
     */
    @Override
    public String toString() {
        return "UsuarioNuevoDTO{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + ", rol=" + rol + '}';
    }
}
