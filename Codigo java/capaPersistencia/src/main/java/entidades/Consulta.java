/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 * Clase Consulta.
 * Maneja la entidad Consulta, usado principalmente para guardar los datos de cada consulta.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class Consulta {
    
    /** Identificador único de la consulta. */
    private int idConsulta;
    /** Estado actual de la consulta. */
    private String estado;
    /** Diagnóstico realizado en la consulta. */
    private String diagnostico;
    /** Tratamiento recomendado en la consulta. */
    private String tratamiento;
    /** Notas adicionales sobre la consulta. */
    private String notas;
    /** Cita asociada a la consulta. */
    private Cita cita;

    /**
     * Constructor vacío de la clase Consulta.
     */
    public Consulta() {
    }

    /**
     * Constructor que inicializa todos los atributos de la consulta.
     * 
     * @param idConsulta Identificador único de la consulta.
     * @param estado Estado actual de la consulta.
     * @param diagnostico Diagnóstico realizado en la consulta.
     * @param tratamiento Tratamiento recomendado en la consulta.
     * @param notas Notas adicionales sobre la consulta.
     * @param cita Cita asociada a la consulta.
     */
    public Consulta(int idConsulta, String estado, String diagnostico, String tratamiento, String notas, Cita cita) {
        this.idConsulta = idConsulta;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    /**
     * Constructor que inicializa todos los atributos excepto el ID.
     * 
     * @param estado Estado actual de la consulta.
     * @param diagnostico Diagnóstico realizado en la consulta.
     * @param tratamiento Tratamiento recomendado en la consulta.
     * @param notas Notas adicionales sobre la consulta.
     * @param cita Cita asociada a la consulta.
     */
    public Consulta(String estado, String diagnostico, String tratamiento, String notas, Cita cita) {
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    /**
     * Obtiene el identificador único de la consulta.
     * @return ID de la consulta.
     */
    public int getIdConsulta() {
        return idConsulta;
    }

    /**
     * Establece el identificador único de la consulta.
     * @param idConsulta ID de la consulta.
     */
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    /**
     * Obtiene el estado actual de la consulta.
     * @return Estado de la consulta.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la consulta.
     * @param estado Estado de la consulta.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el diagnóstico de la consulta.
     * @return Diagnóstico de la consulta.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Establece el diagnóstico de la consulta.
     * @param diagnostico Diagnóstico de la consulta.
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Obtiene el tratamiento recomendado en la consulta.
     * @return Tratamiento de la consulta.
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Establece el tratamiento recomendado en la consulta.
     * @param tratamiento Tratamiento de la consulta.
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Obtiene las notas adicionales de la consulta.
     * @return Notas de la consulta.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Establece las notas adicionales de la consulta.
     * @param notas Notas de la consulta.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * Obtiene la cita asociada a la consulta.
     * @return Cita de la consulta.
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * Establece la cita asociada a la consulta.
     * @param cita Cita de la consulta.
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * Representación en cadena del objeto Consulta.
     * @return Cadena con los datos de la consulta.
     */
    @Override
    public String toString() {
        return "Consulta{" + "idConsulta=" + idConsulta + ", estado=" + estado + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", notas=" + notas + ", cita=" + cita + '}';
    }
}

