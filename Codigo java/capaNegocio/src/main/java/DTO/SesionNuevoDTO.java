/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Clase UsuarioNuevoDTO. Esta clase representa un DTO utilizado para la
 * creación de un usuario, que se utilizará para ingresar datos de usuarios en
 * la base de datos.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class SesionNuevoDTO {

    /**
     * User del usuario.
     */
    private String usuario;

    /**
     * Contraseña del usuario.
     */
    private String contrasenia;

    /**
     * Constructor vacío.
     */
    public SesionNuevoDTO() {
    }

    /**
     * Constructor con parámetros para inicializar los campos de la clase
     * SesionNuevoDTO.
     *
     * @param usuario Usuario del usuario
     * @param contrasenia Contraseña del usuario
     */
    public SesionNuevoDTO(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene el usuario del objeto SesionNuevoDTO.
     *
     * @return Usuario del usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario del objeto SesionNuevoDTO.
     *
     * @param usuario Usuario del usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del objeto SesionNuevoDTO.
     *
     * @return Contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del objeto SesionNuevoDTO.
     *
     * @param contrasenia Contraseña del usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Método toString(). Devuelve una representación en cadena de texto del
     * objeto SesionNuevoDTO.
     *
     * @return String con los detalles de los atributos de la clase.
     */
    @Override
    public String toString() {
        return "SesionNuevoDTO{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
}
