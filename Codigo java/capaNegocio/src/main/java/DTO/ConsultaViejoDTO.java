/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Isabel
 */
public class ConsultaViejoDTO {

    /**
     * Identificador único de la consulta.
     */
    private String idConsulta;

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
    private CitaViejoDTO cita;

    /**
     * Constructor por defecto de la clase ConsultaViejoDTO. Este constructor
     * crea una instancia vacía de la clase.
     */
    public ConsultaViejoDTO() {
    }

    /**
     * Constructor de la clase ConsultaViejoDTO con parámetros para inicializar
     * todos los atributos.
     *
     * @param idConsulta Identificador único de la consulta.
     * @param estado Estado actual de la consulta.
     * @param diagnostico Diagnóstico realizado en la consulta.
     * @param tratamiento Tratamiento recomendado en la consulta.
     * @param notas Notas adicionales sobre la consulta.
     * @param cita Cita asociada a la consulta.
     */
    public ConsultaViejoDTO(String idConsulta, String estado, String diagnostico, String tratamiento, String notas, CitaViejoDTO cita) {
        this.idConsulta = idConsulta;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    /**
     * Constructor de la clase ConsultaViejoDTO sin el identificador único de la
     * consulta.
     *
     * @param estado Estado actual de la consulta.
     * @param diagnostico Diagnóstico realizado en la consulta.
     * @param tratamiento Tratamiento recomendado en la consulta.
     * @param notas Notas adicionales sobre la consulta.
     * @param cita Cita asociada a la consulta.
     */
    public ConsultaViejoDTO(String estado, String diagnostico, String tratamiento, String notas, CitaViejoDTO cita) {
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    // Métodos getters y setters
    /**
     * Obtiene el identificador único de la consulta.
     *
     * @return idConsulta El identificador único de la consulta.
     */
    public String getIdConsulta() {
        return idConsulta;
    }

    /**
     * Establece el identificador único de la consulta.
     *
     * @param idConsulta El identificador único de la consulta.
     */
    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

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
    public CitaViejoDTO getCita() {
        return cita;
    }

    /**
     * Establece la cita asociada a la consulta.
     *
     * @param cita La cita asociada a la consulta.
     */
    public void setCita(CitaViejoDTO cita) {
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
        return "ConsultaViejoDTO{" + "idConsulta=" + idConsulta + ", estado=" + estado + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", notas=" + notas + ", cita=" + cita + '}';
    }
}
