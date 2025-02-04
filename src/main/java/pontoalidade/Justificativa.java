/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

/**
 *
 * @author Rios_01
 */

enum StatusJustificativa {
    Aceita,
    Recusada,
    Enviada,
    Pendente
}

public class Justificativa {
    private static int idCounter = 0;
    private final int id;
    private String descricao;
    private Falta falta;
    private StatusJustificativa status;

    public Justificativa(String descricao) {
        this.id = ++idCounter;
        this.descricao = descricao;
        this.status = StatusJustificativa.Enviada;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return falta != null ? falta.getData() : "Data não disponível";
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusJustificativa getStatus() {
        return status;
    }

    public void setStatus(StatusJustificativa status) {
        this.status = status;
    }

    public Falta getFalta() {
        return falta;
    }

    public void setFalta(Falta falta) {
        this.falta = falta;
    }
}
