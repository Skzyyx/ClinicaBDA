/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalTime;

/**
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class HorarioViejoDTO {

    /**
     * Identificador único del horario.
     */
    private int idHorario;

    /**
     * Día de la semana en el que aplica el horario.
     */
    private String diaSemana;

    /**
     * Hora de entrada del médico en el horario especificado.
     */
    private LocalTime horaEntrada;

    /**
     * Hora de salida del médico en el horario especificado.
     */
    private LocalTime horaSalida;

    /**
     * Médico al que pertenece este horario.
     */
    private MedicoViejoDTO medico;

    /**
     * Constructor por defecto de la clase HorarioViejoDTO. Este constructor
     * crea una instancia vacía de la clase.
     */
    public HorarioViejoDTO() {
    }

    /**
     * Constructor de la clase HorarioViejoDTO con parámetros para inicializar
     * todos los atributos.
     *
     * @param idHorario Identificador único del horario.
     * @param diaSemana Día de la semana en el que aplica el horario.
     * @param horaEntrada Hora de entrada del médico.
     * @param horaSalida Hora de salida del médico.
     * @param medico Médico al que pertenece este horario.
     */
    public HorarioViejoDTO(int idHorario, String diaSemana, LocalTime horaEntrada, LocalTime horaSalida, MedicoViejoDTO medico) {
        this.idHorario = idHorario;
        this.diaSemana = diaSemana;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.medico = medico;
    }

    /**
     * Constructor de la clase HorarioViejoDTO sin el identificador de horario.
     *
     * @param diaSemana Día de la semana en el que aplica el horario.
     * @param horaEntrada Hora de entrada del médico.
     * @param horaSalida Hora de salida del médico.
     * @param medico Médico al que pertenece este horario.
     */
    public HorarioViejoDTO(String diaSemana, LocalTime horaEntrada, LocalTime horaSalida, MedicoViejoDTO medico) {
        this.diaSemana = diaSemana;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.medico = medico;
    }

    // Métodos getters y setters
    /**
     * Obtiene el identificador único del horario.
     *
     * @return idHorario El identificador único del horario.
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * Establece el identificador único del horario.
     *
     * @param idHorario El identificador único del horario.
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * Obtiene el día de la semana en el que aplica el horario.
     *
     * @return diaSemana El día de la semana.
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * Establece el día de la semana en el que aplica el horario.
     *
     * @param diaSemana El día de la semana.
     */
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * Obtiene la hora de entrada del médico en el horario especificado.
     *
     * @return horaEntrada La hora de entrada.
     */
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * Establece la hora de entrada del médico.
     *
     * @param horaEntrada La hora de entrada del médico.
     */
    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * Obtiene la hora de salida del médico en el horario especificado.
     *
     * @return horaSalida La hora de salida.
     */
    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    /**
     * Establece la hora de salida del médico.
     *
     * @param horaSalida La hora de salida del médico.
     */
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * Obtiene el médico al que pertenece este horario.
     *
     * @return medico El médico al que pertenece el horario.
     */
    public MedicoViejoDTO getMedico() {
        return medico;
    }

    /**
     * Establece el médico al que pertenece este horario.
     *
     * @param medico El médico al que pertenece el horario.
     */
    public void setMedico(MedicoViejoDTO medico) {
        this.medico = medico;
    }

    /**
     * Devuelve una representación en formato de cadena del horario, incluyendo
     * todos sus atributos.
     *
     * @return String Una cadena con la información completa del horario.
     */
    @Override
    public String toString() {
        return "HorarioViejoDTO{" + "idHorario=" + idHorario + ", diaSemana=" + diaSemana + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", medico=" + medico + '}';
    }
}
