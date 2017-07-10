/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import Models.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    private ArrayList<Poligono> poligonos;
    @FXML
    private Canvas drawingArea;
    public Aresta raio;
    @FXML
    private Button Quadrado;
    @FXML
    private Button Irregular;
    public int cliques;
    public ArrayList<Vertice> novoIrregular;


    GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //pol = new Poliline();
        this.poligonos=new ArrayList();
        this.novoIrregular = new ArrayList();
        //drawingArea.setOnMouseClicked(this::cliqueinochico);


       /* drawingArea.getParent().setOnKeyPressed((KeyEvent e) -> {
            System.out.println("uheuheuhe");
        });*/


        cliques = 0;


        gc = drawingArea.getGraphicsContext2D();

        gc.setStroke(Color.BLUE);
        /*
        gc.strokeLine(50, 0, 50, 100);
        gc.strokeLine(50, 100, 150, 100);
        gc.strokeLine(150, 100, 150, 0);
        gc.strokeLine(50, 0, 150, 0);*/
    }

    public void criaQuadrado(){

        drawingArea.setOnMouseClicked(this::criaRegular);

    }
    public void criaIrregular(){
        drawingArea.setOnMouseClicked(this::Irregulares);
    }

    public double distancia(Vertice A, Vertice B){
        double resp = Math.sqrt(Math.pow(B.X-A.X,2)+Math.pow(B.Y-A.Y,2));
        return resp;
    }
    public void Irregulares(MouseEvent e){
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        v1.X = e.getX();
        v1.Y = e.getX();
        if(cliques==0){
            novoIrregular.add(v1);
            cliques++;
        }
        if(cliques==1){
            novoIrregular.add(v1);

        }


    }

    public void criaRegular(MouseEvent e){
        //System.out.println("dsadasdsadasdasasdas");
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        //System.out.println("fdfxsggdsgv");
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        Poligono Novo = new Poligono(v,v2,4);
        for(int i=0;i<Novo.Vertices.size();i++){
            System.out.println(Novo.Vertices.get(i).X+" "+Novo.Vertices.get(i).Y);
            if(i!=Novo.Vertices.size()-1){
                gc.strokeLine(Novo.Vertices.get(i).X, Novo.Vertices.get(i).Y, Novo.Vertices.get(i+1).X,Novo.Vertices.get(i+1).Y);
            }else{
                gc.strokeLine(Novo.Vertices.get(i).X, Novo.Vertices.get(i).Y, Novo.Vertices.get(0).X,Novo.Vertices.get(0).Y);
            }

        }


    }

    public void carregou(Scene scene){
        scene.setOnKeyPressed((KeyEvent e) -> {
            gc.clearRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
            cliques = 0;
            //pol.pontos.clear();
            // pol.draw(gc);
        });
    }

 }
