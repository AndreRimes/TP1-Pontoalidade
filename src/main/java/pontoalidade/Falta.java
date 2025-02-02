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
    private static int idCounter = 0;
    private final int id;
    private String data;
    private Justificativa justificativa;
    public Falta(String data) {
        this.id = ++idCounter;
        this.data = data;
        this.justificativa = null;
    }
            
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public void setJustificativa(Justificativa justificativa){
        this.justificativa = justificativa;
    }
    
    public Justificativa getJustificativa(){
        return justificativa;
    }
}
