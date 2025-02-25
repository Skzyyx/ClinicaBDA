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

    /**
     * Identificador único de la cita.
     */
    private String idCita;

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
     * Tipo de cita (emergencia, programada).
     */
    private String tipo;

    /**
     * Paciente asociado a la cita.
     */
    private PacienteViejoDTO paciente;

    /**
     * Médico que atenderá la cita.
     */
    private MedicoViejoDTO medico;

    /**
     * Constructor por defecto de la clase CitaViejoDTO. Este constructor crea
     * una instancia vacía de la clase.
     */
    public CitaViejoDTO() {
    }

    /**
     * Constructor de la clase CitaViejoDTO con parámetros para inicializar los
     * atributos.
     *
     * @param idCita Identificador único de la cita.
     * @param fechaHoraInicio Fecha y hora del inicio de la cita.
     * @param estado Estado actual de la cita.
     * @param folio Folio de la cita.
     * @param tipo Tipo de cita (emergencia, programada).
     * @param paciente Paciente asociado a la cita.
     * @param medico Médico que atenderá la cita.
     */
    public CitaViejoDTO(String idCita, LocalDateTime fechaHoraInicio, String estado, String folio, String tipo, PacienteViejoDTO paciente, MedicoViejoDTO medico) {
        this.idCita = idCita;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.folio = folio;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    // Métodos getters y setters
    /**
     * Obtiene el identificador único de la cita.
     *
     * @return idCita El identificador único de la cita.
     */
    public String getIdCita() {
        return idCita;
    }

    /**
     * Establece el identificador único de la cita.
     *
     * @param idCita El identificador único de la cita.
     */
    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    /**
     * Obtiene la fecha y hora del inicio de la cita.
     *
     * @return fechaHoraInicio La fecha y hora de inicio de la cita.
     */
    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * Establece la fecha y hora del inicio de la cita.
     *
     * @param fechaHoraInicio La fecha y hora de inicio de la cita.
     */
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * Obtiene el estado actual de la cita.
     *
     * @return estado El estado actual de la cita.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la cita.
     *
     * @param estado El estado actual de la cita.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el folio de la cita.
     *
     * @return folio El folio de la cita.
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Establece el folio de la cita.
     *
     * @param folio El folio de la cita.
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * Obtiene el tipo de la cita (emergencia, programada).
     *
     * @return tipo El tipo de la cita.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de la cita (emergencia, programada).
     *
     * @param tipo El tipo de la cita.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el paciente asociado a la cita.
     *
     * @return paciente El paciente asociado a la cita.
     */
    public PacienteViejoDTO getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado a la cita.
     *
     * @param paciente El paciente asociado a la cita.
     */
    public void setPaciente(PacienteViejoDTO paciente) {
        this.paciente = paciente;
    }

    /**
     * Obtiene el médico que atenderá la cita.
     *
     * @return medico El médico que atenderá la cita.
     */
    public MedicoViejoDTO getMedico() {
        return medico;
    }

    /**
     * Establece el médico que atenderá la cita.
     *
     * @param medico El médico que atenderá la cita.
     */
    public void setMedico(MedicoViejoDTO medico) {
        this.medico = medico;
    }

    /**
     * Devuelve una representación en formato de cadena de la cita, incluyendo
     * todos sus atributos.
     *
     * @return String Una cadena con la información completa de la cita.
     */
    @Override
    public String toString() {
        return "CitaViejaDTO{" + "idCita=" + idCita + ", fechaHoraInicio=" + fechaHoraInicio + ", estado=" + estado + ", folio=" + folio + ", tipo=" + tipo + ", paciente=" + paciente + ", medico=" + medico + '}';
    }
}
