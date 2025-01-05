/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author Rios_01
 */
public class Justificativa {
    private static int idCounter = 0;
    private final int id;
    private String descricao;
    private Falta falta;

    public Justificativa(String descricao) {
        this.id = ++idCounter;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return falta.getData();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}