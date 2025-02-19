/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;

/**
 * Clase Cita.
 * Maneja la entidad Direccion, usado para guardar los datos de una cita.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class Cita {

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
    private Paciente paciente;
    /** Médico que atenderá la cita. */
    private Medico medico;

    /**
     * Constructor vacío.
     */
    public Cita() {
    }
    
    /**
     * Constructor de la clase.
     * Establece todos los atributos de la clase al valor de sus parámetros.
     * 
     * @param idCita Identificador de la cita
     * @param fechaHoraInicio Fecha y hora de la cita
     * @param estado Estado de la cita
     * @param folio Folio de la cita
     * @param tipo Tipo de la cita
     * @param paciente Paciente asociado a la cita
     * @param medico Médico que atenderá la cita
     */
    public Cita(int idCita, LocalDateTime fechaHoraInicio, String estado, String folio, String tipo, Paciente paciente, Medico medico) {
        this.idCita = idCita;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.folio = folio;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    /**
     * Constructor de la clase.
     * Establece todos los atributos de la clase excepto idCita.
     * 
     * @param fechaHoraInicio Fecha y hora de la cita
     * @param estado Estado de la cita
     * @param folio Folio de la cita
     * @param tipo Tipo de la cita
     * @param paciente Paciente asociado a la cita
     * @param medico Médico que atenderá la cita
     */
    public Cita(LocalDateTime fechaHoraInicio, String estado, String folio, String tipo, Paciente paciente, Medico medico) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.folio = folio;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    /**
     * Obtiene el identificador de la cita.
     * @return Identificador de la cita
     */
    public int getIdCita() {
        return idCita;
    }

    /**
     * Establece el identificador de la cita.
     * @param idCita Nuevo identificador de la cita
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    /**
     * Obtiene la fecha y hora de inicio de la cita.
     * @return Fecha y hora de la cita
     */
    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * Establece la fecha y hora de inicio de la cita.
     * @param fechaHoraInicio Nueva fecha y hora de la cita
     */
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * Obtiene el estado de la cita.
     * @return Estado de la cita
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la cita.
     * @param estado Nuevo estado de la cita
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el folio de la cita.
     * @return Folio de la cita
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Establece el folio de la cita.
     * @param folio Nuevo folio de la cita
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * Obtiene el tipo de cita.
     * @return Tipo de la cita
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de cita.
     * @param tipo Nuevo tipo de la cita
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el paciente asociado a la cita.
     * @return Paciente de la cita
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado a la cita.
     * @param paciente Nuevo paciente de la cita
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Obtiene el médico que atenderá la cita.
     * @return Médico de la cita
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Establece el médico que atenderá la cita.
     * @param medico Nuevo médico de la cita
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Representación en cadena de la clase Cita.
     * @return Cadena con los detalles de la cita
     */
    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", fechaHoraInicio=" + fechaHoraInicio + ", estado=" + estado + ", folio=" + folio + ", tipo=" + tipo + ", paciente=" + paciente + ", medico=" + medico + '}';
    }
}