/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author berna
 */
abstract class Usuario {
    private static int idCounter = 1;
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private double salarioPorHora;
    
    // Constructor to initialize common properties
    public Usuario(String nome, String email, String cpf, String senha, double salarioPorHora) {
        this.id = idCounter++;  // Automatically assigns a unique id
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.salarioPorHora = salarioPorHora;
    }
    
    // Getter methods
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public double getSalarioPorHora() {
        return salarioPorHora;
    }

    // Setter methods
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setSalarioPorHora(double salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }
    
    // Abstract method to be implemented by subclasses
    public abstract void exibirTipoUsuario();
}
