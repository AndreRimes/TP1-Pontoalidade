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
    Funcionario funcionario = new Funcionario("João Silva", "joao@empresa.com", "12345678900", "senha123", 20.0);

    funcionario.addDiaTrabalhado(new Dia("2025-01-01", "08:00", "16:00"));
    funcionario.addDiaTrabalhado(new Dia("2025-01-02", "09:00", "17:00"));
    
    System.out.println("Salário do Funcionario: " + funcionario.calculaSalario());
    System.out.println(funcionario);
}



}

