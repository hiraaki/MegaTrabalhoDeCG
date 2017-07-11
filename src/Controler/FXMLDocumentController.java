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
    public double x,y;

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

        gc.setStroke(Color.BLACK);
        /*
        gc.strokeLine(50, 0, 50, 100);
        gc.strokeLine(50, 100, 150, 100);
        gc.strokeLine(150, 100, 150, 0);
        gc.strokeLine(50, 0, 150, 0);*/
    }

    public void criaQuadrado(){
        drawingArea.setOnMouseClicked(this::criaRegularQuadrado);
    }
    public void criaTriangulo(){
        drawingArea.setOnMouseClicked(this::criaRegularTriangulo);
    }
    public void criaPentagono(){
        drawingArea.setOnMouseClicked(this::criaRegularPentagono);
    }
    public void criaHexagono(){
        drawingArea.setOnMouseClicked(this::criaRegularHexagono);
    }
    public void criaNlados(){
        drawingArea.setOnMouseClicked(this::criaRegularNlados);
    }
    public void criaIrregular(){
        drawingArea.setOnMouseClicked(this::Irregulares);
    }



    public double distancia(Vertice A, Vertice B){
        double resp = Math.sqrt(Math.pow(B.X-A.X,2)+Math.pow(B.Y-A.Y,2));
        return resp;
    }
    public void Irregulares(MouseEvent e){

        if(cliques==0){
            novoIrregular.add( new Vertice(e.getX(),e.getY()));
            cliques++;

        }else if(cliques==1){
            novoIrregular.add( new Vertice(e.getX(),e.getY()));
            double x1,y1,x2,y2;
            x1=novoIrregular.get(novoIrregular.size()-1).X;
            y1=novoIrregular.get(novoIrregular.size()-1).Y;
            x2=novoIrregular.get(novoIrregular.size()-2).X;
            y2=novoIrregular.get(novoIrregular.size()-2).Y;

            if(distancia(novoIrregular.get(0),novoIrregular.get(novoIrregular.size()-1))<10){
                poligonos.add(new Poligono(novoIrregular));
                x1=novoIrregular.get(0).X;
                y1=novoIrregular.get(0).Y;
                System.out.println(x1+" "+y1+" size "+novoIrregular.size());
                System.out.println(x2+" "+y2);
                gc.strokeLine(x1, y1, x2, y2);
                novoIrregular.clear();
                cliques=0;

            }else {
                System.out.println(x1+" "+y1+" size "+novoIrregular.size());
                System.out.println(x2+" "+y2);
                gc.strokeLine(x1, y1, x2, y2);
                cliques=1;
            }
        }



    }

    public void criaRegularQuadrado(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        Poligono Novo = new Poligono(v,v2,4);
        this.draw(Novo);
    }
    public void criaRegularTriangulo(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        Poligono Novo = new Poligono(v,v2,3);
        this.draw(Novo);
    }
    public void criaRegularPentagono(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        Poligono Novo = new Poligono(v,v2,5);
        this.draw(Novo);
    }
    public void criaRegularHexagono(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        Poligono Novo = new Poligono(v,v2,6);
        this.draw(Novo);
    }
    public void criaRegularNlados(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;

        Poligono Novo = new Poligono(v,v2,4);
        this.draw(Novo);
    }
    public void draw(Poligono Novo){
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
