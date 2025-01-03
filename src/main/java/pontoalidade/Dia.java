/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

import java.time.LocalTime;
import java.time.Duration;

public class Dia {
    private static int idCounter = 1;
    private int id;
    private String data;
    private String horarioInicio;
    private String horarioFinal;
    private double horarioTotal;


    public Dia(String data, String horarioInicio, String horarioFinal) {
        this.id = idCounter++;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.horarioTotal = calcularHoras(horarioInicio, horarioFinal); 
    }

    public double calcularHoras(String horarioInicio, String horarioFinal) {
        LocalTime start = LocalTime.parse(horarioInicio);
        LocalTime end = LocalTime.parse(horarioFinal);
        
        Duration duration = Duration.between(start, end);
        return duration.toMinutes() / 60.0;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
        this.horarioTotal = calcularHoras(this.horarioInicio, this.horarioFinal); // Recalculate if start time changes
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
        this.horarioTotal = calcularHoras(this.horarioInicio, this.horarioFinal); // Recalculate if end time changes
    }

    public double getHorarioTotal() {
        return horarioTotal;
    }

    @Override
    public String toString() {
        return "Dia{id=" + id + ", data='" + data + "', horarioInicio='" + horarioInicio + "', horarioFinal='" + horarioFinal + "', horarioTotal=" + horarioTotal + '}';
    }
}
