/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

import java.time.Duration;
import java.time.LocalTime;

/**
 *
 * @author Rios_01
 */
public class Pausa {
    private static int idCounter = 0;
    private final int id;
    private String horarioInicio;
    private String horarioFinal;
    private String horarioTotal;
    private Dia dia;

    public Pausa(String horarioInicio, String horarioFinal) {
        this.id = ++idCounter;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
    }

    public double calcularHoras(String horarioInicio, String horarioFinal) {
        LocalTime start = LocalTime.parse(horarioInicio);
        LocalTime end = LocalTime.parse(horarioFinal);
        
        Duration duration = Duration.between(start, end);
        return duration.toMinutes() / 60.0;
    }
    
    public int getId() {
        return id;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFinal;
    }
}