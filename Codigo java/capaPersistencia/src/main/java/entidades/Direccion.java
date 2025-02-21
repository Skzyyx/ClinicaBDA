/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Objects;

/**
 * Clase Direccion.
 * Maneja la entidad Direccion, usado para guardar los datos de dirección de un paciente.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class Direccion {
    private int idDireccion;
    private String calle;
    private String numero;
    private String colonia;
    private String codigoPostal;
    
    /**
     * Constructor vacío.
     */
    public Direccion() {
    }
    
    /**
     * Constructor de la clase.
     * Establece todos los atributos de la clase al valor de sus parámetros.
     * @param idDireccion id de la dirección
     * @param calle componente calle
     * @param numero componente número de casa
     * @param colonia componente colonia
     * @param codigoPostal componente código postal
     */
    public Direccion(int idDireccion, String calle, String numero, String colonia, String codigoPostal) {    
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Constructor de la clase.
     * Establece los atributos de la clase al valor de sus parámetros, excepto el atributo idDireccion.
     * @param calle componente calle
     * @param numero componente número de casa
     * @param colonia componente colonia
     * @param codigoPostal componente código postal
     */
    public Direccion(String calle, String numero, String colonia, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Método getIdDireccion().
     * Obtiene el id de la dirección
     * @return id de la dirección
     */
    public int getIdDireccion() {
        return idDireccion;
    }
    
    /**
     * Método setIdDireccion(int idDireccion).
     * Establece el idDireccion al valor de su parámetro
     * @param idDireccion valor de idDireccion para actualizar
     */
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    /**
     * Método getCalle().
     * Obtiene la calle de la dirección
     * @return calle de la dirección
     */
    public String getCalle() {
        return calle;
    }
    
    /**
     * Método setCalle(String calle).
     * Establece la calle al valor de su parámetro
     * @param calle valor de calle para actualizar
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    /**
     * Método getNumero().
     * Obtiene el numero de la dirección
     * @return numero de la dirección
     */
    public String getNumero() {
        return numero;
    }
    
    /**
     * Método setNumero(String numero).
     * Establece la numero al valor de su parámetro
     * @param numero valor de numero para actualizar
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    /**
     * Método getColonia().
     * Obtiene la colonia de la dirección
     * @return colonia de la dirección
     */
    public String getColonia() {
        return colonia;
    }
    
    /**
     * Método setColonia(String colonia).
     * Establece la colonia al valor de su parámetro
     * @param colonia valor de colonia para actualizar
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    
    /**
     * Método getCodigoPostal().
     * Obtiene el codigo postal de la dirección
     * @return codigo postal de la dirección
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }
     
    /**
     * Método setCodigoPostal(String codigoPostal).
     * Establece el codigoPostal al valor de su parámetro
     * @param codigoPostal valor de codigoPostal para actualizar
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    /**
     * Método toString().
     * @return String con detalles de los atributos de la clase
     */
    @Override
    public String toString() {
        return "Direccion{" + "idDireccion=" + idDireccion + ", calle=" + calle + ", numero=" + numero + ", colonia=" + colonia + ", codigoPostal=" + codigoPostal + '}';
    }
}