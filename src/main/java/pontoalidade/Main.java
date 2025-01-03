/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author berna
 */
public class Main {
public static void main(String[] args) {
        Organizacao organizacao = new Organizacao("Tech Corp", "12345678000199");

        Administrador admin = new Administrador("Carlos Silva", "admin@techcorp.com", "12345678900", "adminpass", 50.0, organizacao);

        Funcionario func1 = new Funcionario("João Silva", "joao@techcorp.com", "98765432100", "senha123", 20.0, organizacao);
        Funcionario func2 = new Funcionario("Maria Santos", "maria@techcorp.com", "87654321000", "senha456", 25.0, organizacao);

        admin.addFuncionario(func1);
        admin.addFuncionario(func2);

        System.out.println("Usuários na organização:");
        for (Usuario usuario : organizacao.getUsuarios()) {
            System.out.println(usuario);
        }

        admin.deleteFuncionario(func1);

        System.out.println("\nUsuários na organização após exclusão:");
        for (Usuario usuario : organizacao.getUsuarios()) {
            System.out.println(usuario);
        }
    }



}

