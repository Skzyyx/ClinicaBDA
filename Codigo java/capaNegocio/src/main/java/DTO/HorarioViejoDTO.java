/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalTime;

/**
 *
 * @author skyro
 */
public class HorarioViejoDTO {
    
    /** Identificador único del horario. */
    private int idHorario;
    /** Día de la semana en el que aplica el horario. */
    private String diaSemana;
    /** Hora de entrada del médico en el horario especificado. */
    private LocalTime horaEntrada;
    /** Hora de salida del médico en el horario especificado. */
    private LocalTime horaSalida;
    /** Médico al que pertenece este horario. */
    private MedicoViejoDTO medico;

    public HorarioViejoDTO() {
    }

    public HorarioViejoDTO(int idHorario, String diaSemana, LocalTime horaEntrada, LocalTime horaSalida, MedicoViejoDTO medico) {
        this.idHorario = idHorario;
        this.diaSemana = diaSemana;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.medico = medico;
    }

    public HorarioViejoDTO(String diaSemana, LocalTime horaEntrada, LocalTime horaSalida, MedicoViejoDTO medico) {
        this.diaSemana = diaSemana;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.medico = medico;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public MedicoViejoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoViejoDTO medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "HorarioViejoDTO{" + "idHorario=" + idHorario + ", diaSemana=" + diaSemana + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", medico=" + medico + '}';
    }
}
