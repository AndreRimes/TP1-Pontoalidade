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
    private String password;
    private List<Usuario> usuarios;

    public Organizacao(String nome, String cnpj, String password) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.password = password;
        this.usuarios = new ArrayList<>();
    }

    public Organizacao(String nome, String cnpj, String senha, List<Usuario> usuarios) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.password = senha;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void deleteUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
    }
    
    
    public Usuario validateCredentials(String email, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(password)) {
                return usuario;
            }
        }

        throw new IllegalArgumentException("Invalid email or password");
    }
    
    
}
