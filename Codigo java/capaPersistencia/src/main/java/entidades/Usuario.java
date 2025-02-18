/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 * Clase Usuario.
 * Maneja la entidad Usuario, usado principalmente para guardar los datos de inicio de sesión.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @autor 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class Usuario {
    //Atributos de la clase
    private int idUsuario; //Id del usuario
    private String usuario; //user (email para paciente, cédula profesional para médico)
    private String contrasenia; //contraseña del usuario
    private String rol; //rol/tipo de usuario (paciente o médico)
    
    /**
     * Constructor vacío.
     */
    public Usuario() {
    }
    
    /**
     * Constructor de la clase.
     * Establece todos los atributos de la clase al valor de sus parámetros.
     * @param idUsuario id del usuario
     * @param usuario usuario para iniciar sesión
     * @param contrasenia contraseña para iniciar sesión
     * @param rol tipo del usuario (paciente o médico)
     */
    public Usuario(int idUsuario, String usuario, String contrasenia, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
    
    /**
     * Constructor de la clase.
     * Establece los atributos de la clase al valor de sus parámetros, excepto el atributo idUsuario.
     * @param usuario usuario para iniciar sesión
     * @param contrasenia contraseña para iniciar sesión
     * @param rol tipo del usuario (paciente o médico)
     */
    public Usuario(String usuario, String contrasenia, String rol) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
    
    /**
     * Método getIdUsuario().
     * Obtiene el id del usuario.
     * @return id del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    
    /**
     * Método setIdUsuario(int idUsuario).
     * Establece el idUsuario al valor de su parámetro
     * @param idUsuario valor de idUsuario para actualizar
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Método getUsuario().
     * Obtiene el user del usuario.
     * @return user del usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Método setUsuario(String usuario).
     * Establece el usuario al valor de su parámetro
     * @param usuario valor de usuario para actualizar
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Método getContraseña().
     * Obtiene la contraseña del usuario
     * @return contraseña del usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }
    
    /**
     * Método setContrasenia(String contrasenia).
     * Establece la contraseña al valor de su parámetro
     * @param contrasenia valor de contraseña para actualizar
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    /**
     * Método getRol().
     * Obtiene el rol del usuario
     * @return rol del usuario
     */
    public String getRol() {
        return rol;
    }
    
    /**
     * Método setRol(String rol).
     * Establece el rol al valor de su parámetro
     * @param rol valor de rol para actualizar
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    /**
     * Método toString().
     * @return String con detalles de los atributos de la clase
     */
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", rol=" + rol + '}';
    }
}