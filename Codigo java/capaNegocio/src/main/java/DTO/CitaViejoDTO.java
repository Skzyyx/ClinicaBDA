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
public class CitaViejoDTO {
    /** Identificador único de la cita. */
    private int idCita;
    /** Fecha y hora del inicio de la cita. */
    private LocalDateTime fechaHoraInicio;
    /** Estado actual de la cita (pendiente, confirmada, cancelada, etc.). */
    private String estado;
    /** Folio de la cita. */
    private String folio;
    /** Tipo de cita (emergencia, programada). */
    private String tipo;
    /** Paciente asociado a la cita. */
    private PacienteViejoDTO paciente;
    /** Médico que atenderá la cita. */
    private MedicoViejoDTO medico;

    public CitaViejoDTO() {
    }
    
    public CitaViejoDTO(int idCita, LocalDateTime fechaHoraInicio, String estado, String folio, String tipo, PacienteViejoDTO paciente, MedicoViejoDTO medico) {
        this.idCita = idCita;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.folio = folio;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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

    public PacienteViejoDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteViejoDTO paciente) {
        this.paciente = paciente;
    }

    public MedicoViejoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoViejoDTO medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "CitaViejaDTO{" + "idCita=" + idCita + ", fechaHoraInicio=" + fechaHoraInicio + ", estado=" + estado + ", folio=" + folio + ", tipo=" + tipo + ", paciente=" + paciente + ", medico=" + medico + '}';
    }
}
