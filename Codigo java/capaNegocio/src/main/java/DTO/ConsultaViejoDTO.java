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
    /** Identificador único de la consulta. */
    private String idConsulta;
    /** Estado actual de la consulta. */
    private String estado;
    /** Diagnóstico realizado en la consulta. */
    private String diagnostico;
    /** Tratamiento recomendado en la consulta. */
    private String tratamiento;
    /** Notas adicionales sobre la consulta. */
    private String notas;
    /** Cita asociada a la consulta. */
    private CitaViejoDTO cita;

    public ConsultaViejoDTO() {
    }

    public ConsultaViejoDTO(String idConsulta, String estado, String diagnostico, String tratamiento, String notas, CitaViejoDTO cita) {
        this.idConsulta = idConsulta;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    public ConsultaViejoDTO(String estado, String diagnostico, String tratamiento, String notas, CitaViejoDTO cita) {
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notas = notas;
        this.cita = cita;
    }

    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
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

    public CitaViejoDTO getCita() {
        return cita;
    }

    public void setCita(CitaViejoDTO cita) {
        this.cita = cita;
    }

    @Override
    public String toString() {
        return "ConsultaViejoDTO{" + "idConsulta=" + idConsulta + ", estado=" + estado + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", notas=" + notas + ", cita=" + cita + '}';
    }
}
