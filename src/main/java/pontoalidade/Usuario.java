/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author berna
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private static int idCounter = 0; 
    private final int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    protected double salarioPorHora;
    private List<Dia> diasTrabalhados;

    public Usuario(String nome, String email, String cpf, String senha, double salarioPorHora) {
        this.id = ++idCounter;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.salarioPorHora = salarioPorHora;
        this.diasTrabalhados = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSalarioPorHora() {
        return salarioPorHora;
    }

    public void setSalarioPorHora(double salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }

    public List<Dia> getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void addDiaTrabalhado(Dia dia) {
        diasTrabalhados.add(dia);
    }

    public final double calculaSalario() {
        double totalHoras = 0.0;
        for (Dia dia : diasTrabalhados) {
            totalHoras += dia.getHorarioTotal();
        }
        return totalHoras * salarioPorHora;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salarioPorHora=" + salarioPorHora +
                '}';
    }
}

