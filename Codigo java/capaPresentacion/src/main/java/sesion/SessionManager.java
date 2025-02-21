/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesion;


/**
 *
 * @author Isabel
 */
public class SessionManager {
    /** Instancia privada estática de la clase */
    private static volatile SessionManager instance;

    /** Información de la sesión */
    private String user;
    private String rol;
    
    /**
     * Constructor privado. 
     * Impide la creación de instancias fuera de la clase.
     */
    private SessionManager() {
    }
    
    /**
     * Accede a la instancia de la clase.
     * @return Instancia de la clase.
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            // Sincronizar los hilos.
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                    
                }
            }
        }
        return instance;
    }
                
    /**
     * Inicia sesión y guarda los datos del usuario y rol.
     * @param user Nombre de usuario.
     * @param rol Rol del usuario.
     */
    public void iniciarSesion(String user, String rol) {
        this.user = user;
        this.rol = rol;
    }
    
    /**
     * Verifica si el usuario ha iniciado sesión
     * @return True si ha iniciado sesión, false en caso contrario.
     */ 
    public boolean sesionActiva() {
        return this.user != null;
    }

    // Métodos getter y setter
    public String getUser() {
        return user;
    }

    public String getRol() {
        return rol;
    }
    
    /**
     * Cierra la sesión
     */
    public void cerrarSesion() {
        this.user = null;
        this.rol = null;
    }
}
