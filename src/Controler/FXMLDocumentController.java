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

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

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
    private Button Seleciona;
    @FXML
    private Button Irregular;
    @FXML
    private Button Excluir;
    @FXML
    private MenuItem Novo;
    @FXML
    private MenuItem Salvar;
    @FXML
    private ChoiceBox<Integer> N;
    public int selecionado;
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
        this.selecionado=-1;

       /* drawingArea.getParent().setOnKeyPressed((KeyEvent e) -> {
            System.out.println("uheuheuhe");
        });*/
        N.getSelectionModel().getSelectedItem(); // pegar o valor selecionado

        N.getSelectionModel().selectedItemProperty().addListener( // escutar quando o valor muda
                (ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
                    System.out.println(newValue);
                });

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
    public void seleciona(){drawingArea.setOnMouseClicked(this::selecionar);}
    public void Exclui(){
        if(selecionado!=-1) {
            poligonos.remove(selecionado);
            selecionado = -1;
            gc.clearRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
            for (Poligono p : poligonos) {
                draw(p);
            }
        }
    }

    public void salvar(){
        Stage stage=new Stage();
        FileChooser filedir = new FileChooser();
        filedir.setTitle("Salvar");
        filedir.showOpenDialog(stage);

    }

    public void selecionar(MouseEvent e){
        double menor=9999999999999.0;
        int novoselect=-1;
        for(int i=0;i<poligonos.size();i++){
            ArrayList<Aresta> a=poligonos.get(i).Arestas;
            for(int j=0;j<a.size();j++){
                double dist=DistanceFromLine(e.getX(),e.getY(),a.get(j).Inicio.X,a.get(j).Inicio.Y,a.get(j).Fim.X,a.get(j).Fim.Y);
                System.out.println(dist);
                if(dist<=5){
                    novoselect=i;
                }
            }
            //System.out.println(i);
        }
        if(novoselect==selecionado){
            selecionado=-1;
        }else{
            selecionado=novoselect;
        }
        if(selecionado==-1){
            System.out.println("nenhum pol selecionado");
        }
        gc.clearRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
        for(int i=0;i<poligonos.size();i++){
            if(i==selecionado){
                gc.setStroke(Color.RED);
                draw(poligonos.get(i));
                gc.setStroke(Color.BLACK);

            }else{
                gc.setStroke(Color.BLACK);
                draw(poligonos.get(i));
            }
        }

    }
    

    public double distancia(Vertice A, Vertice B){
        double resp = sqrt(Math.pow(B.X-A.X,2)+Math.pow(B.Y-A.Y,2));
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

            if(distancia(novoIrregular.get(0),novoIrregular.get(novoIrregular.size()-1))<3){
                novoIrregular.remove(novoIrregular.size()-1);
                poligonos.add(new Poligono(novoIrregular));
                x1=novoIrregular.get(0).X;
                y1=novoIrregular.get(0).Y;
                //System.out.println(x1+" "+y1+" size "+novoIrregular.size());
                //System.out.println(x2+" "+y2);
                gc.strokeLine(x1, y1, x2, y2);
                novoIrregular.clear();
                cliques=0;

            }else {
                //System.out.println(x1+" "+y1+" size "+novoIrregular.size());
                //System.out.println(x2+" "+y2);
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
        poligonos.add( new Poligono(v,v2,4));
        this.draw(poligonos.get(poligonos.size()-1));
    }
    public void criaRegularTriangulo(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        poligonos.add( new Poligono(v,v2,3));
        this.draw(poligonos.get(poligonos.size()-1));
    }
    public void criaRegularPentagono(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        poligonos.add( new Poligono(v,v2,5));
        this.draw(poligonos.get(poligonos.size()-1));
    }
    public void criaRegularHexagono(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        poligonos.add( new Poligono(v,v2,6));
        this.draw(poligonos.get(poligonos.size()-1));
    }
    public void criaRegularNlados(MouseEvent e){
        Vertice v = new Vertice();
        Vertice v2 = new Vertice();
        v.X = e.getX();
        v.Y = e.getY();
        v2.X=v.X+100;
        v2.Y=v.Y;
        int lados= N.getSelectionModel().getSelectedItem();
        poligonos.add( new Poligono(v,v2,lados));
        this.draw(poligonos.get(poligonos.size()-1));
    }
    public void draw(Poligono Novo){
        for(int i=0;i<Novo.Vertices.size();i++){
            //System.out.println(Novo.Vertices.get(i).X+" "+Novo.Vertices.get(i).Y);
            if(i!=Novo.Vertices.size()-1){
                gc.strokeLine(Novo.Vertices.get(i).X, Novo.Vertices.get(i).Y, Novo.Vertices.get(i+1).X,Novo.Vertices.get(i+1).Y);
            }else{
                gc.strokeLine(Novo.Vertices.get(i).X, Novo.Vertices.get(i).Y, Novo.Vertices.get(0).X,Novo.Vertices.get(0).Y);
            }
        }
    }

    public void novo(){
            this.gc.clearRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
            cliques = 0;
            poligonos.clear();
            selecionado=-1;
            novoIrregular.clear();
    }


    public double DistanceFromLine(double cx, double cy, double ax, double ay ,double bx, double by){
        double distanceSegment,distanceLine;
        double r_numerator = (cx-ax)*(bx-ax) + (cy-ay)*(by-ay);
        double r_denomenator = (bx-ax)*(bx-ax) + (by-ay)*(by-ay);
        double r = r_numerator / r_denomenator;

        double px = ax + r*(bx-ax);
        double py = ay + r*(by-ay);

        double s =  ((ay-cy)*(bx-ax)-(ax-cx)*(by-ay) ) / r_denomenator;

        distanceLine = abs(s)*sqrt(r_denomenator);

        double xx = px;
        double yy = py;

        if ( (r >= 0) && (r <= 1) )
        {
            distanceSegment = distanceLine;
        }
        else
        {

            double dist1 = (cx-ax)*(cx-ax) + (cy-ay)*(cy-ay);
            double dist2 = (cx-bx)*(cx-bx) + (cy-by)*(cy-by);
            if (dist1 < dist2)
            {
                xx = ax;
                yy = ay;
                distanceSegment = sqrt(dist1);
            }
            else
            {
                xx = bx;
                yy = by;
                distanceSegment = sqrt(dist2);
            }


        }

        return distanceSegment;
    }




 }
