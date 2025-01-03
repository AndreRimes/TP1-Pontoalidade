/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author berna
 */
public class Funcionario extends Usuario {
    private int metaHorasDiaria = 8;

    public Funcionario(String nome, String email, String cpf, String senha, double salarioPorHora) {
        super(nome, email, cpf, senha, salarioPorHora);
    }

    public int getMetaHorasDiaria() {
        return metaHorasDiaria;
    }

    public void setMetaHorasDiaria(int metaHorasDiaria) {
        this.metaHorasDiaria = metaHorasDiaria;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", salarioPorHora=" + salarioPorHora +
                ", metaHorasDiaria=" + metaHorasDiaria +
                '}';
    }
}

