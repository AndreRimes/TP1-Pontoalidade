/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pontoalidade;

import java.util.List;

/**
 *
 * @author Rios_01
 */
public class Falta {
    private int id;
    private String data;
    private Justificativa justificativa;
    public Falta(int id, String data) {
        this.id = id;
        this.data = data;
    }
            
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
