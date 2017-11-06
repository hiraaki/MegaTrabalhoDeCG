/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import Models.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ColorPicker;

import static java.lang.Math.*;

/**
 *
 * @author Hamã Cândido
 */
public class FXMLDocumentController implements Initializable {

    private ArrayList<Poligono> poligonos;
    @FXML
    private Canvas drawingArea1;
    @FXML
    private Canvas drawingArea2;
    @FXML
    private Canvas drawingArea3;
    @FXML
    private Canvas drawingArea4;
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
    private MenuItem Abrir;
    @FXML
    private ChoiceBox<Integer> N;
    @FXML
    private  Button Translada;
    @FXML
    private Button ScalaXY;
    @FXML
    private Button ScalaX;
    @FXML
    private Button ScalaY;
    @FXML
    private Button Rotacao;
    @FXML
    private ColorPicker Cor;

    public int selecionado;
    public int cliques;
    public ArrayList<Vertice> novoIrregular;
    public Vertice Pressed;
    public double Distancia;
    GraphicsContext gc;
    public double Fator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


        this.poligonos=new ArrayList();
        this.novoIrregular = new ArrayList();

        this.selecionado=-1;
        this.Pressed=new Vertice();
        this.Distancia=0.0;
        this.Fator=0.0;
       /* drawingArea1.getParent().setOnKeyPressed((KeyEvent e) -> {
            System.out.println("uheuheuhe");
        });*/
        N.getSelectionModel().getSelectedItem(); // pegar o valor selecionado

        N.getSelectionModel().selectedItemProperty().addListener( // escutar quando o valor muda
                (ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
                    System.out.println(newValue);
                });

        cliques = 0;


        gc = drawingArea1.getGraphicsContext2D();

        gc.setStroke(Color.BLACK);

    }
    public void setPressed(MouseEvent e){
        this.Pressed.X=e.getX();
        this.Pressed.Y=e.getY();
    }
    public void setUnpressed(MouseEvent e){
        this.Pressed.X=0;
        this.Pressed.Y=0;
        this.Fator=0;
    }

    public void criaQuadrado(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::criaRegularQuadrado);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void criaTriangulo(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::criaRegularTriangulo);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void criaPentagono(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::criaRegularPentagono);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void criaHexagono(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::criaRegularHexagono);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void criaNlados(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::criaRegularNlados);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void criaIrregular(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::Irregulares);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void seleciona(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::selecionar);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void Arrasta(){
        drawingArea1.setOnMouseClicked(null);
        drawingArea1.setOnMouseDragged(this::Translada);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }

    public void scalaY(){
        drawingArea1.setOnMouseClicked(null);
        drawingArea1.setOnMousePressed(this::setPressed);
        drawingArea1.setOnMouseDragged(this::scalay);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    public void scalaX(){
        drawingArea1.setOnMouseClicked(null);
        drawingArea1.setOnMousePressed(this::setPressed);
        drawingArea1.setOnMouseDragged(this::scalax);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }


    public void scalaXY(){
        drawingArea1.setOnMouseClicked(null);
        drawingArea1.setOnMousePressed(this::setPressed);
        drawingArea1.setOnMouseDragged(this::scalaxy);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    public void cisalhamentoX(){
        drawingArea1.setOnMouseClicked(null);
        drawingArea1.setOnMousePressed(this::setPressed);
        drawingArea1.setOnMouseDragged(this::cisalhamentox);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    public void cisalhamentoY(){
        drawingArea1.setOnMouseClicked(null);
        drawingArea1.setOnMousePressed(this::setPressed);
        drawingArea1.setOnMouseDragged(this::cisalhamentoy);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    public void rotacao(){
        drawingArea1.setOnMouseClicked(null);
        drawingArea1.setOnMousePressed(this::setPressed);
        drawingArea1.setOnMouseDragged(this::rotaciona);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    @FXML
    public void cor(ActionEvent e){
        //ColorPicker colorPicker = new ColorPicker();
        System.out.println(Cor.getValue());
        poligonos.get(selecionado).face.Preenchimento=Cor.getValue();
        //System.out.println(poligonos.get(selecionado).face.Preenchimento);
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        drawall();
        //selecionado=-1;
    }

    public void rotaciona(MouseEvent e){
        System.out.println(this.Fator);
        Vertice centro = new Vertice(this.poligonos.get(selecionado).Central.X,this.poligonos.get(selecionado).Central.Y);
        double fator = atan2((e.getX()-centro.X),(e.getY()-centro.Y));
        poligonos.get(selecionado).rotaciona(this.Fator-fator);
        System.out.println(fator +" "+(fator-this.Fator)+" "+toDegrees(fator));
        this.Fator=fator;
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        drawall();

    }

    public void cisalhamentox(MouseEvent e){
        double fator = distancia(Pressed,new Vertice(e.getX(),e.getY()));
        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).cisalhamentoX(0.2);

        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).cisalhamentoX(-0.2);

        }
        setPressed(e);
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        drawall();
    }
    public void cisalhamentoy(MouseEvent e){
        double fator = distancia(Pressed,new Vertice(e.getX(),e.getY()));
        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).cisalhamentoY(0.2);

        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).cisalhamentoY(-0.2);

        }
        setPressed(e);
        this.Fator=fator;
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        drawall();
    }
    public void scalax(MouseEvent e){
        double fator = distancia(Pressed,new Vertice(e.getX(),e.getY()));
        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).scalaX(1.03);

        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).scalaX(0.97);

        }
        setPressed(e);
        this.Fator=fator;
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        drawall();
    }

    public void scalay(MouseEvent e){
        double fator = distancia(Pressed,new Vertice(e.getX(),e.getY()));
        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).scalaY(1.03);

        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

            poligonos.get(selecionado).scalaY(0.97);

        }
        setPressed(e);
        this.Fator=fator;
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        drawall();
    }

    public void scalaxy(MouseEvent e){
        double fator = distancia(this.Pressed,new Vertice(e.getX(),e.getY()));
        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

                poligonos.get(selecionado).scalaXY(1.03);

        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
                (distancia(poligonos.get(selecionado).Central,this.Pressed))){

                poligonos.get(selecionado).scalaXY(0.97);

        }
        setPressed(e);
        this.Fator=fator;
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        drawall();
        //poligonos.get(selecionado).printVertices();
    }


    public void Translada(MouseEvent mouseEvent) {
        Vertice v = new Vertice(mouseEvent.getX(),mouseEvent.getY());
        if(selecionado!=-1){
            if(mouseEvent.getX()!=poligonos.get(selecionado).Central.X)
                if(mouseEvent.getY()!=poligonos.get(selecionado).Central.Y)
                    poligonos.get(selecionado).translada(v);
                    gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
                    drawall();
        }
    }

    public void Exclui(){
        if(selecionado!=-1) {
            poligonos.remove(selecionado);
            selecionado = -1;
            gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
            for (Poligono p : poligonos) {
                draw(p);
            }
        }
    }


    public void salvar(){
        FileChooser chooser = new FileChooser();
        FileChooser filter = new FileChooser();
        FileChooser.ExtensionFilter extFiler = new FileChooser.ExtensionFilter("POLIGON Files (*.out)", "*.out");
        chooser.getExtensionFilters().add(extFiler);
        chooser.setTitle("Salvar Cena");
        String savef = chooser.showSaveDialog(drawingArea1.getScene().getWindow()).toString();
        save(savef);

    }
    public void save(String fileName) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(poligonos);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void open() {
        FileChooser chooser = new FileChooser();
        FileChooser filter = new FileChooser();
        FileChooser.ExtensionFilter extFiler = new FileChooser.ExtensionFilter("POLIGON Files (*.out)", "*.out");
        chooser.getExtensionFilters().add(extFiler);
        chooser.setTitle("Abrir Cena");
        String openf = chooser.showOpenDialog(drawingArea1.getScene().getWindow()).toString();
        load(openf);
    }

    public void load(String fileName) {
        try {
            FileInputStream in = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(in);
            poligonos = (ArrayList<Poligono>) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        System.out.println("este é o arquivo");
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        this.drawall();
        //return null;
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
        gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
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
        for(int i=0;i<(novoIrregular.size()-1);i++){
            gc.strokeLine(novoIrregular.get(i).X, novoIrregular.get(i).Y, novoIrregular.get(i+1).X,novoIrregular.get(i+1).Y);
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
        poligonos.add( new Poligono(v,v2,4,Cor.getValue()));
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
        novoIrregular.clear();
    }
    public void draw(Poligono Novo){

        Vertice maior=new Vertice(-1,-1);
        Vertice menor=new Vertice(9999999,9999999);
        for(Vertice v:Novo.Vertices){
            if(v.X<menor.X)
                menor.X=v.X;
            if(v.Y<menor.Y)
                menor.Y=v.Y;
            if(v.X>maior.X)
                maior.X=v.X;
            if(v.Y>maior.Y)
                maior.Y=v.Y;
        }
        gc.setStroke(Novo.face.Preenchimento);
        fill(Novo.Vertices);

        gc.setStroke(Novo.face.Contorno);
        for(int i=0;i<Novo.Vertices.size();i++){
            //System.out.println(Novo.Vertices.get(i).X+" "+Novo.Vertices.get(i).Y);
            if(i!=Novo.Vertices.size()-1){
                gc.strokeLine(Novo.Vertices.get(i).X, Novo.Vertices.get(i).Y, Novo.Vertices.get(i+1).X,Novo.Vertices.get(i+1).Y);
            }else{
                gc.strokeLine(Novo.Vertices.get(i).X, Novo.Vertices.get(i).Y, Novo.Vertices.get(0).X,Novo.Vertices.get(0).Y);
            }

        }

    }

    private void fill(ArrayList<Vertice> poli){
        double ymin=99999999,ymax=-999999;
        for(Vertice v: poli){
            if(v.Y>ymax)
                ymax=v.Y;
            if(v.Y<ymin)
                ymin=v.Y;
        }
        System.out.println(ymin+" "+ymax);

        ArrayList<ArrayList<Models.Vertice>> scanlines = new ArrayList<>();

        for (double i = ymin; i <= ymax; i++ ){
            ArrayList<Models.Vertice> intersectionPoints = new ArrayList<>();
            for (int j = 0; j < poli.size() - 1; j++){
                double x1, x2, y1, y2;
                double deltax, deltay, x;
                x1 = poli.get(j).X;
                y1 = poli.get(j).Y;
                x2 = poli.get(j + 1).X;
                y2 = poli.get(j + 1).Y;

                deltax = x2 - x1;
                deltay = y2 - y1;

                double roundedX;
                x = x1 + deltax / deltay * (i - y1);
                roundedX = Math.round(x);
                if ((y1 <= i && y2 > i) || (y2 <= i && y1 > i)) {
                    intersectionPoints.add(new Vertice(roundedX,i));
                    System.out.println(roundedX+" "+i);
                }
            }
            //for the last interval
            double x1, x2, y1, y2;
            x1 = poli.get(poli.size() - 1).X;
            y1 = poli.get(poli.size() - 1).Y;
            x2 = poli.get(0).X;
            y2 = poli.get(0).Y;

            double deltax, deltay, x;
            deltax = x2 - x1;
            deltay = y2 - y1;
            x = x1 + deltax / deltay * (i - y1);
            double roundedX = Math.round(x);

            if ((y1 <= i && y2 > i) || (y2 <= i && y1 > i)) {
                intersectionPoints.add(new Vertice(roundedX, i));
            }

            Collections.sort(intersectionPoints, new Comparator<Models.Vertice>() {
                @Override
                public int compare(final Vertice A, final Vertice B) {
                    return (int) (A.X - B.X);
                }
            });
            scanlines.add(intersectionPoints);
        }
        Vertice p = null;
        for(ArrayList<Vertice> list : scanlines){
            for (Vertice ponto:list){
                if (p == null){
                    p = ponto;
                    continue;
                }
                System.out.println("dsadsadsa");
                System.out.println(p.X+" "+p.Y+" "+ponto.X+" "+ponto.Y);
                gc.strokeLine(p.X, p.Y, ponto.X, ponto.Y);
                p = null;
            }
        }
    }

    public void novo(){
            this.gc.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
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

    public void drawall(){
        for (Poligono p: poligonos) {
            draw(p);
        }
        for(int i=0;i<(novoIrregular.size()-1);i++){
            gc.strokeLine(novoIrregular.get(i).X, novoIrregular.get(i).Y, novoIrregular.get(i+1).X,novoIrregular.get(i+1).Y);
        }
    }
    public int intersec2d(Vertice iniScanL, Vertice fimScanL, Vertice iniAres, Vertice fimAres, Double s, Double t){
        double det;

        det = (fimAres.X - iniAres.X) * (fimScanL.Y - iniScanL.Y)  -  (fimAres.Y - iniAres.Y) * (fimScanL.X - iniScanL.Y);

        if (det == 0.0)
            return 0 ; // não há intersecção

        s = ((fimAres.X - iniAres.X) * (iniAres.Y - iniScanL.Y) - (fimAres.Y - iniAres.Y) * (iniAres.X - iniScanL.X))/ det ;
        t = ((fimScanL.X - iniScanL.X) * (iniAres.Y - iniScanL.Y) - (fimScanL.Y - iniScanL.Y) * (iniAres.X - iniScanL.X))/ det ;

        return 1; // há intersecção
    }
    public Vertice PontoI(Aresta scanL,Aresta arestaPol){
        Double s = new Double(0.0);
        Double t = new Double(0.0);
        int intercede=intersec2d(scanL.Inicio,scanL.Fim,arestaPol.Inicio,arestaPol.Fim,s,t);
        if(intercede==0){
            return null;
        }else{
            return (new Vertice((scanL.Inicio.X+((scanL.Fim.X-scanL.Inicio.X)*s)),(scanL.Inicio.Y+((scanL.Fim.Y-scanL.Inicio.Y)*s))));
        }

    }


}
