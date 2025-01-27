/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author berna
 */
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
    public Organizacao organizacao; // Associating user with an organization

    public Usuario(String nome, String email, String cpf, String senha, double salarioPorHora, Organizacao organizacao) {
        this.id = ++idCounter; 
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.salarioPorHora = salarioPorHora;
        this.diasTrabalhados = new ArrayList<>();
        this.organizacao = organizacao;
    }
    
    public Organizacao getOrganizacao() {
        return organizacao;
    }
    
    public void setOrganizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
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
        double totalHoras = this.horasMes();
        return totalHoras * salarioPorHora;
    }
    
    public final double horasMes(){
        LocalDate today = LocalDate.now(); 
        Month currentMonth = today.getMonth(); 

        double totalHoras = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Dia dia : diasTrabalhados) {
            LocalDate diaDate = LocalDate.parse(dia.getData(), formatter); 
            if (diaDate.getMonth() == currentMonth) {
                totalHoras += dia.getHorarioTotal();
            }
        }
        
        return totalHoras;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salarioPorHora=" + salarioPorHora +
                ", organizacao=" + (organizacao != null ? organizacao.getNome() : "Nenhuma") +
                '}';
    }
}


