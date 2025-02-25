/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author skyro
 */
public class DireccionNuevoDTO {

    /**
     * Calle de la dirección.
     */
    private String calle;

    /**
     * Número de la dirección.
     */
    private String numero;

    /**
     * Colonia o barrio de la dirección.
     */
    private String colonia;

    /**
     * Código postal de la dirección.
     */
    private String codigoPostal;

    /**
     * Constructor por defecto de la clase DireccionNuevoDTO. Este constructor
     * crea una instancia vacía de la clase.
     */
    public DireccionNuevoDTO() {
    }

    /**
     * Constructor de la clase DireccionNuevoDTO con parámetros para inicializar
     * todos los atributos.
     *
     * @param calle Calle de la dirección.
     * @param numero Número de la dirección.
     * @param colonia Colonia o barrio de la dirección.
     * @param codigoPostal Código postal de la dirección.
     */
    public DireccionNuevoDTO(String calle, String numero, String colonia, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    // Métodos getters y setters
    /**
     * Obtiene la calle de la dirección.
     *
     * @return calle La calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece la calle de la dirección.
     *
     * @param calle La calle de la dirección.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el número de la dirección.
     *
     * @return numero El número de la dirección.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la dirección.
     *
     * @param numero El número de la dirección.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la colonia o barrio de la dirección.
     *
     * @return colonia La colonia o barrio de la dirección.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece la colonia o barrio de la dirección.
     *
     * @param colonia La colonia o barrio de la dirección.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene el código postal de la dirección.
     *
     * @return codigoPostal El código postal de la dirección.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el código postal de la dirección.
     *
     * @param codigoPostal El código postal de la dirección.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Devuelve una representación en formato de cadena de la dirección,
     * incluyendo todos sus atributos.
     *
     * @return String Una cadena con la información completa de la dirección.
     */
    @Override
    public String toString() {
        return "DireccionNuevoDTO{" + "calle=" + calle + ", numero=" + numero + ", colonia=" + colonia + ", codigoPostal=" + codigoPostal + '}';
    }
}
