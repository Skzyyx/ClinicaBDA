/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class CitaNuevoDTO {

    /**
     * Fecha y hora del inicio de la cita.
     */
    private LocalDateTime fechaHoraInicio;
    /**
     * Estado actual de la cita (pendiente, confirmada, cancelada, etc.).
     */
    private String estado;
    /**
     * Folio de la cita.
     */
    private String folio;
    /**
     * Tipo de cita (emergencia, programada, etc.).
     */
    private String tipo;
    /**
     * Paciente asociado a la cita.
     */
    private PacienteNuevoDTO paciente;
    /**
     * Médico que atenderá la cita.
     */
    private MedicoViejoDTO medico;

    /**
     * Constructor vacío.
     */
    public CitaNuevoDTO() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param fechaHoraInicio Fecha y hora del inicio de la cita.
     * @param estado Estado de la cita.
     * @param folio Folio de la cita.
     * @param tipo Tipo de la cita.
     * @param paciente Paciente asociado a la cita.
     * @param medico Médico que atenderá la cita.
     */
    public CitaNuevoDTO(LocalDateTime fechaHoraInicio, String estado, String folio, String tipo, PacienteNuevoDTO paciente, MedicoViejoDTO medico) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.folio = folio;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    /**
     * Constructor con fecha de inicio y médico.
     *
     * @param fechaHoraInicio Fecha y hora del inicio de la cita.
     * @param medico Médico que atenderá la cita.
     */
    public CitaNuevoDTO(LocalDateTime fechaHoraInicio, MedicoViejoDTO medico) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.medico = medico;
    }

    /**
     * Constructor con fecha de inicio, paciente y médico.
     *
     * @param fechaHoraInicio Fecha y hora del inicio de la cita.
     * @param paciente Paciente asociado a la cita.
     * @param medico Médico que atenderá la cita.
     */
    public CitaNuevoDTO(LocalDateTime fechaHoraInicio, PacienteNuevoDTO paciente, MedicoViejoDTO medico) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.paciente = paciente;
        this.medico = medico;
    }

    /**
     * Obtiene la fecha y hora de inicio de la cita.
     *
     * @return Fecha y hora de inicio de la cita.
     */
    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * Establece la fecha y hora de inicio de la cita.
     *
     * @param fechaHoraInicio Fecha y hora de inicio de la cita.
     */
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * Obtiene el estado de la cita.
     *
     * @return Estado de la cita.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la cita.
     *
     * @param estado Estado de la cita.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el folio de la cita.
     *
     * @return Folio de la cita.
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Establece el folio de la cita.
     *
     * @param folio Folio de la cita.
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * Obtiene el tipo de cita.
     *
     * @return Tipo de cita.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de cita.
     *
     * @param tipo Tipo de cita.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el paciente asociado a la cita.
     *
     * @return Paciente asociado a la cita.
     */
    public PacienteNuevoDTO getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado a la cita.
     *
     * @param paciente Paciente asociado a la cita.
     */
    public void setPaciente(PacienteNuevoDTO paciente) {
        this.paciente = paciente;
    }

    /**
     * Obtiene el médico que atenderá la cita.
     *
     * @return Médico que atenderá la cita.
     */
    public MedicoViejoDTO getMedico() {
        return medico;
    }

    /**
     * Establece el médico que atenderá la cita.
     *
     * @param medico Médico que atenderá la cita.
     */
    public void setMedico(MedicoViejoDTO medico) {
        this.medico = medico;
    }

    /**
     * Representación en cadena de la cita.
     *
     * @return Cadena con los datos de la cita.
     */
    @Override
    public String toString() {
        return "CitaNuevoDTO{" + "fechaHoraInicio=" + fechaHoraInicio + ", estado=" + estado + ", folio=" + folio + ", tipo=" + tipo + ", paciente=" + paciente + ", medico=" + medico + '}';
    }
}
