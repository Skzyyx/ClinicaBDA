/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class DireccionViejoDTO {

    /**
     * Identificador único de la dirección.
     */
    private String idDireccion;

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
     * Constructor por defecto de la clase DireccionViejoDTO. Este constructor
     * crea una instancia vacía de la clase.
     */
    public DireccionViejoDTO() {
    }

    /**
     * Constructor de la clase DireccionViejoDTO con parámetros para inicializar
     * todos los atributos.
     *
     * @param idDireccion Identificador único de la dirección.
     * @param calle Calle de la dirección.
     * @param numero Número de la dirección.
     * @param colonia Colonia o barrio de la dirección.
     * @param codigoPostal Código postal de la dirección.
     */
    public DireccionViejoDTO(String idDireccion, String calle, String numero, String colonia, String codigoPostal) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Constructor de la clase DireccionViejoDTO sin el identificador de
     * dirección.
     *
     * @param calle Calle de la dirección.
     * @param numero Número de la dirección.
     * @param colonia Colonia o barrio de la dirección.
     * @param codigoPostal Código postal de la dirección.
     */
    public DireccionViejoDTO(String calle, String numero, String colonia, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    // Métodos getters y setters
    /**
     * Obtiene el identificador único de la dirección.
     *
     * @return idDireccion El identificador único de la dirección.
     */
    public String getIdDireccion() {
        return idDireccion;
    }

    /**
     * Establece el identificador único de la dirección.
     *
     * @param idDireccion El identificador único de la dirección.
     */
    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

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
        return "DireccionViejoDTO{" + "idDireccion=" + idDireccion + ", calle=" + calle + ", numero=" + numero + ", colonia=" + colonia + ", codigoPostal=" + codigoPostal + '}';
    }
}
