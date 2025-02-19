/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalTime;

/**
 * Clase Horario.
 * Maneja la entidad Horario, usado principalmente para guardar los datos de los horarios para los medicos.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class Horario {
    
    /** Identificador único del horario. */
    private int idHorario;
    /** Día de la semana en el que aplica el horario. */
    private String diaSemana;
    /** Hora de entrada del médico en el horario especificado. */
    private LocalTime horaEntrada;
    /** Hora de salida del médico en el horario especificado. */
    private LocalTime horaSalida;
    /** Médico al que pertenece este horario. */
    private Medico medico;

    /**
     * Constructor vacío de la clase Horario.
     */
    public Horario() {
    }

    /**
     * Constructor que inicializa todos los atributos del horario.
     * 
     * @param idHorario Identificador único del horario.
     * @param diaSemana Día de la semana en el que aplica el horario.
     * @param horaEntrada Hora de entrada del médico.
     * @param horaSalida Hora de salida del médico.
     * @param medico Médico al que pertenece el horario.
     */
    public Horario(int idHorario, String diaSemana, LocalTime horaEntrada, LocalTime horaSalida, Medico medico) {
        this.idHorario = idHorario;
        this.diaSemana = diaSemana;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.medico = medico;
    }

    /**
     * Constructor que inicializa todos los atributos excepto el ID.
     * 
     * @param diaSemana Día de la semana en el que aplica el horario.
     * @param horaEntrada Hora de entrada del médico.
     * @param horaSalida Hora de salida del médico.
     * @param medico Médico al que pertenece el horario.
     */
    public Horario(String diaSemana, LocalTime horaEntrada, LocalTime horaSalida, Medico medico) {
        this.diaSemana = diaSemana;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.medico = medico;
    }

    /**
     * Obtiene el identificador único del horario.
     * @return ID del horario.
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * Establece el identificador único del horario.
     * @param idHorario ID del horario.
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * Obtiene el día de la semana del horario.
     * @return Día de la semana.
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * Establece el día de la semana del horario.
     * @param diaSemana Día de la semana.
     */
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * Obtiene la hora de entrada del horario.
     * @return Hora de entrada.
     */
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * Establece la hora de entrada del horario.
     * @param horaEntrada Hora de entrada.
     */
    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * Obtiene la hora de salida del horario.
     * @return Hora de salida.
     */
    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    /**
     * Establece la hora de salida del horario.
     * @param horaSalida Hora de salida.
     */
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * Obtiene el médico asociado al horario.
     * @return Médico del horario.
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Establece el médico asociado al horario.
     * @param medico Médico del horario.
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Representación en cadena del objeto Horario.
     * @return Cadena con los datos del horario.
     */
    @Override
    public String toString() {
        return "Horario{" + "idHorario=" + idHorario + ", diaSemana=" + diaSemana + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", medico=" + medico + '}';
    }
}
