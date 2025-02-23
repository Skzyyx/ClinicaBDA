/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Clase UsuarioViejoDTO.
 * Este DTO representa los datos del usuario que se van a mostrar o utilizar para realizar peticiones.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class UsuarioViejoDTO {
    /** Identificador único del usuario. */
    private String idUsuario;
    /** User (email para paciente, cédula profesional para médico). */
    private String usuario;
    /** Contraseña del usuario. */
    private String contrasenia;
    /** Rol/tipo de usuario (paciente o médico). */
    private String rol;
    
    /**
     * Constructor vacío.
     */
    public UsuarioViejoDTO() {
    }
    
    /**
     * Constructor con parámetros para inicializar todos los campos de la clase.
     * @param idUsuario Identificador único del usuario.
     * @param usuario User del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param rol Rol del usuario.
     */
    public UsuarioViejoDTO(String idUsuario, String usuario, String contrasenia, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
        return "UsuarioViejoDTO{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", rol=" + rol + '}';
    }
}
