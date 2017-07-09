/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import Models.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Hamã Cândido
 */
public class FXMLDocumentController implements Initializable {

    private java.util.ArrayList<Poligono> poligonos;
    @FXML
    private Canvas chico;
    Poliline pol;
    @FXML
    private Button Quadrado;
    double x1, y1, x2, y2;
    int cliquesNoChico;


    GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //pol = new Poliline();
        ///chico.setOnMouseClicked(this::cliqueinochico);


       /* chico.getParent().setOnKeyPressed((KeyEvent e) -> {
            System.out.println("uheuheuhe");
        });*/

        //cliquesNoChico = 0;

        gc = chico.getGraphicsContext2D();

        gc.setStroke(Color.BLUE);
        /*
        gc.strokeLine(50, 0, 50, 100);
        gc.strokeLine(50, 100, 150, 100);
        gc.strokeLine(150, 100, 150, 0);
        gc.strokeLine(50, 0, 150, 0);*/
    }

    void setQuadrado(){
        chico.setOnMouseClicked(this::cliqueinochico);
    }

    public void carregou(){
        chico.getScene().setOnKeyPressed((KeyEvent e) -> {
            gc.clearRect(0, 0, chico.getWidth(), chico.getHeight());
            cliquesNoChico = 0;
            pol.pontos.clear();
       // pol.draw(gc);
        });
    }
    
    private void cliqueinochico(MouseEvent e) {
        cliquesNoChico++;
        Vertice v = new Vertice();
        Ponto p = new Ponto();
        
        p.x = e.getX();
        p.y = e.getY();
        
        pol.pontos.add(p);
        
        pol.draw(gc);
        
//        switch (cliquesNoChico) {
//            case 1:
//                x1 = e.getX();
//                y1 = e.getY();
//                break;
//            case 2:
//                x2 = e.getX();
//                y2 = e.getY();
//                
//                gc.strokeLine(x1, y1, x2, y2);
//                cliquesNoChico=0;
//                break;
//        }        
    }

}

class Ponto{
    public double x, y;
}

class Poliline{
    public List<Ponto> pontos = new ArrayList<>();
    
    public void draw(GraphicsContext c){
        Ponto p = null;
        
        for(Ponto ponto : pontos){      //pega cada ponto da lista de pontos
            if(p == null){
                p = ponto;
                continue;
            }
            
            c.strokeLine(p.x, p.y, ponto.x, ponto.y);
            p = ponto;
        }
    }
}
