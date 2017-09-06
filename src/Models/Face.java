/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.scene.paint.Color;

/**
 *
 * @author mhi
 */
public class Face {
    public Color Preenchimento;
    public Color Contorno;
    public Face(){
        this.Contorno=Color.BLACK;
        this.Preenchimento=null;
    }
    public Face(Color contorno,Color preenchimento){
        this.Contorno=contorno;
        this.Preenchimento=preenchimento;
    }
}
