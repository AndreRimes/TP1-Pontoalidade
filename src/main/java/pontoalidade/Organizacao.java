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

public class Organizacao {
    private String nome;
    private String cnpj;
    private List<Usuario> usuarios;

    public Organizacao(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.usuarios = new ArrayList<>();
    }

    public Organizacao(String nome, String cnpj, List<Usuario> usuarios) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.usuarios = usuarios;
    }

    // Getters and setters
    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    // Methods to manage users
    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void deleteUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
    }
}
