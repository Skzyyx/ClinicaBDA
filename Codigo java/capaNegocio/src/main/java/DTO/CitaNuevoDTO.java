/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Isabel
 */
public class CitaNuevoDTO {
    /** Fecha y hora del inicio de la cita. */
    private LocalDateTime fechaHoraInicio;
    /** Estado actual de la cita (pendiente, confirmada, cancelada, etc.). */
    private String estado;
    /** Folio de la cita. */
    private String folio;
    /** Tipo de cita (emergencia, programada). */
    private String tipo;
    /** Paciente asociado a la cita. */
    private PacienteNuevoDTO paciente;
    /** Médico que atenderá la cita. */
    private MedicoNuevoDTO medico;

    public CitaNuevoDTO() {
    }

    public CitaNuevoDTO(LocalDateTime fechaHoraInicio, String estado, String folio, String tipo, PacienteNuevoDTO paciente, MedicoNuevoDTO medico) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.folio = folio;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public PacienteNuevoDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteNuevoDTO paciente) {
        this.paciente = paciente;
    }

    public MedicoNuevoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoNuevoDTO medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "CitaNuevoDTO{" + "fechaHoraInicio=" + fechaHoraInicio + ", estado=" + estado + ", folio=" + folio + ", tipo=" + tipo + ", paciente=" + paciente + ", medico=" + medico + '}';
    }
    
    
}
