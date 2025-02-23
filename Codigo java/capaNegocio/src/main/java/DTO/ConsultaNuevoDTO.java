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
    /** Estado actual de la consulta. */
    private String estado;
    /** Diagnóstico realizado en la consulta. */
    private String diagnostico;
    /** Tratamiento recomendado en la consulta. */
    private String tratamiento;
    /** Notas adicionales sobre la consulta. */
    private String notas;
    /** Cita asociada a la consulta. */
    private CitaNuevoDTO cita;

    public ConsultaNuevoDTO() {
    }

    public ConsultaNuevoDTO(String estado, String diagnostico, String tratamiento, String notas, CitaNuevoDTO cita) {
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public CitaNuevoDTO getCita() {
        return cita;
    }

    public void setCita(CitaNuevoDTO cita) {
        this.cita = cita;
    }

    @Override
    public String toString() {
        return "ConsultaNuevoDTO{" + "estado=" + estado + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", notas=" + notas + ", cita=" + cita + '}';
    }
}