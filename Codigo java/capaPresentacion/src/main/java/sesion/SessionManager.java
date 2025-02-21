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
    // Instancia privada estática de la clase
    private static volatile SessionManager instance;

    // Información de la sesión (ejemplo de nombre de usuario)
    private String user;
    private String pass;

    // Constructor privado (impide la creación de instancias fuera de la clase)
    private SessionManager(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    

    // Método público para acceder a la instancia de la clase
    public static SessionManager getInstance(String user, String pass) {
        if (instance == null) {
            synchronized (SessionManager.class) {
                
                if (instance == null) {
                    instance = new SessionManager(user, pass);
                }
            }
        }

        return instance;
    }

    
}
