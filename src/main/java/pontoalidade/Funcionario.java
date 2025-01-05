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

    public Funcionario(String nome, String email, String cpf, String senha, double salarioPorHora, Organizacao organizacao) {
        super(nome, email, cpf, senha, salarioPorHora, organizacao);
    }

    public int getMetaHorasDiaria() {
        return metaHorasDiaria;
    }

    public void setMetaHorasDiaria(int metaHorasDiaria) {
        this.metaHorasDiaria = metaHorasDiaria;
    }
    
    public Justificativa justificarFalta(Falta falta, String conteudo){
        Justificativa justificativa1 = new Justificativa(falta.getData(), conteudo);
        falta.setJustificativa(justificativa1);
        return justificativa1;
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

