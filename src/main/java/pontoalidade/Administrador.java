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
    public Administrador(String nome, String email, String cpf, String senha, double salarioPorHora, Organizacao organizacao) {
        super(nome, email, cpf, senha, salarioPorHora, organizacao);
    }

    public void addFuncionario(Funcionario funcionario) {
        this.organizacao.addUsuario(funcionario);
    }

    public void deleteFuncionario(Usuario usuario) {
        this.organizacao.deleteUsuario(usuario);
    }
}
