/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berna
 */
public class Funcionario extends Usuario {
    private int metaHorasDiaria = 8;
    private List<Dia> diasTrabalhados;


    public Funcionario(String nome, String email, String cpf, String senha, double salarioPorHora, Organizacao organizacao) {
        super(nome, email, cpf, senha, salarioPorHora, organizacao);
        this.diasTrabalhados = new ArrayList<>();

    }

    @Override
    public double calcularSalario() {
        double totalHoras = this.horasMes();
        return totalHoras * salarioPorHora;
    }  
    
     public List<Dia> getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void addDiaTrabalhado(Dia dia) {
        diasTrabalhados.add(dia);
    }
    
    public int getMetaHorasDiaria() {
        return metaHorasDiaria;
    }

    public void setMetaHorasDiaria(int metaHorasDiaria) {
        this.metaHorasDiaria = metaHorasDiaria;
    }
    
    public Justificativa justificarFalta(Falta falta, String conteudo){
        Justificativa justificativa1 = new Justificativa(conteudo);
        falta.setJustificativa(justificativa1);
        return justificativa1;
    }
    
     public final Dia findToday() {
        LocalDate today = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayFormatted = today.format(formatter);

        for (Dia dia : diasTrabalhados) {
            if (dia.getData().equals(todayFormatted)) {
                return dia;
            }
        }

        return null;
    }
    
    public final double horasMes(){
        LocalDate today = LocalDate.now(); 
        Month currentMonth = today.getMonth(); 

        double totalHoras = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Dia dia : diasTrabalhados) {
            LocalDate diaDate = LocalDate.parse(dia.getData(), formatter); 
            if (dia.getStatus() == Status.ENDED && diaDate.getMonth() == currentMonth) {
                System.out.println(dia.getData() + " " + dia.getHorarioTotal());
                totalHoras += dia.getHorarioTotal();
            }
        }
        
        return totalHoras;
    }
    
    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", salarioPorHora=" + salarioPorHora +
                ", metaHorasDiaria=" + metaHorasDiaria +
                '}';
    }
}

