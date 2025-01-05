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
    private Falta falta;
    private Pausa pausa; 
    
    
    public Pausa getPausa() {
        return pausa;
    }

    public void setPausa(Pausa pausa) {
        this.pausa = pausa;
    }
    
    public Falta getFalta() {
        return falta;
    }

    public void setFalta(Falta falta) {
        this.falta = falta;
    }
    
    public boolean faltou(int metaHorasDiaria){
        if (horarioTotal < metaHorasDiaria){
            Falta falta1 = new Falta(this.data);
            this.falta = falta1;
            return true;
        }
        return false;
    }
    
    public Dia(String data, String horarioInicio, String horarioFinal) {
        this.id = idCounter++;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.horarioTotal = calcularHoras(horarioInicio, horarioFinal)-calcularHoras(pausa.getHorarioInicio(),pausa.getHorarioFim()); 
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
        this.horarioTotal = calcularHoras(this.horarioInicio, this.horarioFinal);
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
        this.horarioTotal = calcularHoras(this.horarioInicio, this.horarioFinal);
    }

    public double getHorarioTotal() {
        return horarioTotal;
    }

    @Override
    public String toString() {
        return "Dia{id=" + id + ", data='" + data + "', horarioInicio='" + horarioInicio + "', horarioFinal='" + horarioFinal + "', horarioTotal=" + horarioTotal + '}';
    }
}
