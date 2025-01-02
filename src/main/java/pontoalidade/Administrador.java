/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author berna
 */

public class Administrador extends Usuario {

    public Administrador(String nome, String email, String cpf, String senha, double salarioPorHora) {
        super(nome, email, cpf, senha, salarioPorHora);
    }

    @Override
    public void exibirTipoUsuario() {
        System.out.println("Este é um Administrador.");
    }
}
