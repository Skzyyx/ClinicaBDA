/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Clase UsuarioNuevoDTO.
 * Esta clase representa un DTO utilizado para la creación de un usuario, que se utilizará para ingresar datos de usuarios en la base de datos. 
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class SesionNuevoDTO {
    /** User del usuario */
    private String usuario;
    /** Contraseña del usuario */
    private String contrasenia;

    public SesionNuevoDTO() {
    }

    public SesionNuevoDTO(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
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

    @Override
    public String toString() {
        return "SesionNuevoDTO{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
}
