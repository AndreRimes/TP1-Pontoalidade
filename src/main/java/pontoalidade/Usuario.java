/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author berna
 */


public abstract class Usuario implements Salario{
    private static int idCounter = 0; 
    private final int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    protected double salarioPorHora;
    public Organizacao organizacao; 


    public Usuario(String nome, String email, String cpf, String senha, double salarioPorHora, Organizacao organizacao) {
        this.id = ++idCounter; 
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.salarioPorHora = salarioPorHora;
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


