/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author Rios_01
 */
public class Dia {
    private static int id;
    private String data;
    private String horarioInicio;
    private String horarioFinal;
    private int horarioTotal;

    public Dia(int id, String data, String horarioInicio, String horarioFinal) {
        this.id = id;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.horarioTotal = calcularHoras(horarioInicio, horarioFinal);
    }

    public int calcularHoras(String horarioInicio, String horarioFinal) {
       
    }

    public int getHorarioTotal() {
        return horarioTotal;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Dia.id = id;
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
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }
}
