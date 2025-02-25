/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Isabel
 */
public class ConsultaNuevoDTO {

    /**
     * Estado actual de la consulta.
     */
    private String estado;

    /**
     * Diagnóstico realizado en la consulta.
     */
    private String diagnostico;

    /**
     * Tratamiento recomendado en la consulta.
     */
    private String tratamiento;

    /**
     * Notas adicionales sobre la consulta.
     */
    private String notas;

    /**
     * Cita asociada a la consulta.
     */
    private CitaNuevoDTO cita;

    /**
     * Constructor por defecto de la clase ConsultaNuevoDTO. Este constructor
     * crea una instancia vacía de la clase.
     */
    public ConsultaNuevoDTO() {
    }

    /**
     * Constructor de la clase ConsultaNuevoDTO con parámetros para inicializar
     * los atributos.
     *
     * @param estado Estado actual de la consulta.
     * @param diagnostico Diagnóstico realizado en la consulta.
     * @param tratamiento Tratamiento recomendado en la consulta.
     * @param notas Notas adicionales sobre la consulta.
     * @param cita Cita asociada a la consulta.
     */
    public ConsultaNuevoDTO(String estado, String diagnostico, String tratamiento, String notas, CitaNuevoDTO cita) {
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    // Métodos getters y setters
    /**
     * Obtiene el estado actual de la consulta.
     *
     * @return estado El estado actual de la consulta.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la consulta.
     *
     * @param estado El estado actual de la consulta.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el diagnóstico realizado en la consulta.
     *
     * @return diagnostico El diagnóstico realizado en la consulta.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Establece el diagnóstico realizado en la consulta.
     *
     * @param diagnostico El diagnóstico realizado en la consulta.
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Obtiene el tratamiento recomendado en la consulta.
     *
     * @return tratamiento El tratamiento recomendado en la consulta.
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Establece el tratamiento recomendado en la consulta.
     *
     * @param tratamiento El tratamiento recomendado en la consulta.
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Obtiene las notas adicionales sobre la consulta.
     *
     * @return notas Las notas adicionales sobre la consulta.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas adicionales sobre la consulta.
     *
     * @param notas Las notas adicionales sobre la consulta.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Obtiene la cita asociada a la consulta.
     *
     * @return cita La cita asociada a la consulta.
     */
    public CitaNuevoDTO getCita() {
        return cita;
    }

    /**
     * Establece la cita asociada a la consulta.
     *
     * @param cita La cita asociada a la consulta.
     */
    public void setCita(CitaNuevoDTO cita) {
        this.cita = cita;
    }

    /**
     * Devuelve una representación en formato de cadena de la consulta,
     * incluyendo todos sus atributos.
     *
     * @return String Una cadena con la información completa de la consulta.
     */
    @Override
    public String toString() {
        return "ConsultaNuevoDTO{" + "estado=" + estado + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", notas=" + notas + ", cita=" + cita + '}';
    }
}
