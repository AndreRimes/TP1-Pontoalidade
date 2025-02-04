/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

import java.time.LocalTime;
import java.time.Duration;

enum Status {
    RUNNING,
    PAUSED,
    ENDED,
}

public class Dia {
    private static int idCounter = 1;
    private int id;
    private String data;
    private String horarioInicio;
    private String horarioFinal;
    private double horarioTotal;
    private Falta falta;
    private Pausa pausa; 
    private Status status;
    private Funcionario user;
    
    
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
    
    public Dia(String data, String horarioInicio, String horarioFinal, Pausa pausa, Funcionario user) {
        this.id = idCounter++;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.status = Status.RUNNING;
        this.user = user;
        
        double totalHoras = 0.0;
        if(horarioFinal != null){
            totalHoras = calcularHoras(horarioInicio, horarioFinal);
            if (pausa != null) {
                totalHoras -= calcularHoras(pausa.getHorarioInicio(), pausa.getHorarioFim());
            }
        }
        this.horarioTotal = totalHoras;
    }
    
    private double calcularHoras(String horarioInicio, String horarioFinal) {
        LocalTime start = LocalTime.parse(horarioInicio);
        LocalTime end = LocalTime.parse(horarioFinal);

        Duration duration = Duration.between(start, end);
        
        double aux = 0;
        if(this.getPausa() != null) {
            aux = this.getPausa().getHorarioTotal();            
        }
        
        return (duration.toMinutes() / 60.0) - aux;
    }
    
    public void endDia(String horarioFinal){
        this.horarioFinal = horarioFinal;
        this.status = status.ENDED;
        this.horarioTotal = calcularHoras(horarioInicio, horarioFinal);
    }
    
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
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
        if(this.getFalta() != null && this.getFalta().getJustificativa() != null && this.getFalta().getJustificativa().getStatus() == StatusJustificativa.Aceita){
            return this.user.getMetaHorasDiaria();
        }
        
        return horarioTotal;
    }

    @Override
    public String toString() {
        return "Dia{id=" + id + ", data='" + data + "', horarioInicio='" + horarioInicio + "', horarioFinal='" + horarioFinal + "', horarioTotal=" + horarioTotal + '}';
    }
}
