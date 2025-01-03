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

public class Administrador extends Usuario {
    private List<Usuario> funcionarios;

    public Administrador(String nome, String email, String cpf, String senha, double salarioPorHora) {
        super(nome, email, cpf, senha, salarioPorHora);
        this.funcionarios = new ArrayList<>();
    }

    public void addFuncionario(Usuario usuario) {
        funcionarios.add(usuario);
    }

    public void deleteFuncionario(int id) {
        funcionarios.removeIf(usuario -> usuario.getId() == id);
    }

    public Usuario findFuncionario(int id) {
        return funcionarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public void editFuncionario(Usuario usuario) {
        Usuario existing = findFuncionario(usuario.getId());
        if (existing != null) {
            existing.setNome(usuario.getNome());
            existing.setEmail(usuario.getEmail());
            existing.setCpf(usuario.getCpf());
            existing.setSenha(usuario.getSenha());
        }
    }
    
    public double calculaHoras(Dia[] dias) {
        return 0;
    }
}
