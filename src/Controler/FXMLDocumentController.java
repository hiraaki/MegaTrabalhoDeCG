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


    @FXML
    private Canvas drawingArea1;
    @FXML
    private Canvas drawingArea2;
    @FXML
    private Canvas drawingArea3;
    @FXML
    private Canvas drawingArea4;
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
    private Button Scala;
    @FXML
    private Button Rotacao;
    @FXML
    private ColorPicker Cor;
    @FXML
    private Button Nlados;

    public Aresta raio;
    private ArrayList<Poligono3D> poligonos;
    public int selecionado;
    public int cliques;
    public ArrayList<Vertice3D> novoIrregular;
    public Vertice Pressed;
    public double Distancia;
    GraphicsContext gc1;
    GraphicsContext gc2;
    GraphicsContext gc3;
    GraphicsContext gc4;
    public double Fator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


        this.poligonos=new ArrayList<Poligono3D>();
        this.novoIrregular = new ArrayList<Vertice3D>();

        this.selecionado=-1;
        this.Pressed=new Vertice3D();
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

        gc1 = drawingArea1.getGraphicsContext2D();
        gc2 = drawingArea2.getGraphicsContext2D();
        gc3 = drawingArea3.getGraphicsContext2D();
        gc4 = drawingArea4.getGraphicsContext2D();

        gc1.setStroke(Color.BLACK);
        gc2.setStroke(Color.BLACK);
        gc3.setStroke(Color.BLACK);
        gc4.setStroke(Color.BLACK);

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


    public void criaNlados(){
        //virado para a frente
        drawingArea1.setOnMouseClicked(this::criaRegularXY);
        //virado para o lado
        drawingArea3.setOnMouseClicked(this::criaRegularZY);
        //virado para cima
        drawingArea2.setOnMouseClicked(this::criaRegularXZ);
    }
    public void criaIrregular(){
        drawingArea1.setOnMouseDragged(null);
        //drawingArea1.setOnMouseClicked(this::Irregulares);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void seleciona(){
        drawingArea1.setOnMouseDragged(null);
        drawingArea1.setOnMouseClicked(this::select);
        drawingArea2.setOnMouseClicked(this::select);
        drawingArea3.setOnMouseClicked(this::select);
        drawingArea1.setOnMousePressed(null);
        drawingArea1.setOnMouseReleased(null);
    }
    public void Arrasta(){

        drawingArea1.setOnMouseDragged(this::Translada);
        drawingArea2.setOnMouseDragged(this::Translada);
        drawingArea3.setOnMouseDragged(this::Translada);

    }

    public void scala(){

        drawingArea1.setOnMousePressed(this::setPressed);
        //drawingArea1.setOnMouseDragged(this::scalaxy);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    public void cisalhamento(){

        drawingArea1.setOnMousePressed(this::setPressed);
        //drawingArea1.setOnMouseDragged(this::cisalhamentox);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    public void rotacao(){

        drawingArea1.setOnMousePressed(this::setPressed);
        drawingArea1.setOnMouseDragged(this::rotacaoZ);
        drawingArea1.setOnMouseReleased(this::setUnpressed);
    }
    public void unsetclick(){

        drawingArea1.setOnMouseClicked(null);
        drawingArea2.setOnMouseClicked(null);
        drawingArea3.setOnMouseClicked(null);
    }
    public void unsetPresed(){

        drawingArea1.setOnMousePressed(null);
        drawingArea2.setOnMousePressed(null);
        drawingArea3.setOnMousePressed(null);
    }
    public void unsetReleased(){

        drawingArea1.setOnMouseReleased(null);
        drawingArea2.setOnMouseReleased(null);
        drawingArea3.setOnMouseReleased(null);

    }

    public void clearALL(){
        gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        gc2.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        gc3.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        gc4.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
    }
    public void drawPoligonos(){
        for(int i=0;i<this.poligonos.size();i++){
            Poligono3D pol=this.poligonos.get(i);
            if(selecionado==i){
                pol.drawXY(gc1,Color.RED);
                pol.drawZY(gc3,Color.RED);
                pol.drawXZ(gc2,Color.RED);

            }else{
                pol.drawXY(gc1,Color.BLACK);
                pol.drawZY(gc3,Color.BLACK);
                pol.drawXZ(gc2,Color.BLACK);
            }
        }
    }
    public void criaRegularXY(MouseEvent click){
        Vertice3D v=new Vertice3D();
        v.X=click.getX();
        v.Y=click.getY();
        this.poligonos.add(new Poligono3D(v,100,N.getValue(),1));
        this.drawPoligonos();
        unsetclick();
    }
    public void criaRegularZY(MouseEvent click){
        Vertice3D v=new Vertice3D();
        v.Z=click.getX();
        v.Y=click.getY();
        this.poligonos.add(new Poligono3D(v,100,N.getValue(),2));
        this.drawPoligonos();
        unsetclick();
    }
    public void criaRegularXZ(MouseEvent click){
        Vertice3D v=new Vertice3D();
        v.X=click.getX();
        v.Z=click.getY();
        this.poligonos.add(new Poligono3D(v,100,N.getValue(),3));
        drawPoligonos();
        unsetclick();
    }

    public void rotacaoZ(MouseEvent click){
        System.out.println(this.Fator);

        Vertice centro = new Vertice(this.poligonos.get(selecionado).Central.X,this.poligonos.get(selecionado).Central.Y);

        double fator = atan2((click.getX()-centro.X),(click.getY()-centro.Y));


        poligonos.get(selecionado).rotacaoZ(this.Fator-fator);

        this.Fator=fator;
        drawPoligonos();
    }
    public void select(MouseEvent click){
        double menor=9999999999999.0;
        int novoselect=-1;
        if(click.getSource()==drawingArea1) {
            for (int i = 0; i < poligonos.size(); i++) {
                if (poligonos.get(i).isSelectedXY(new Vertice3D(click.getX(), click.getY(), 0), 5)) {
                    novoselect = i;
                    System.out.println(novoselect);
                }

            }
        }else if(click.getSource()==drawingArea3){
            for (int i = 0; i < poligonos.size(); i++) {
                if (poligonos.get(i).isSelectedZY(new Vertice3D(0.0, click.getY(), click.getX()), 5)) {
                    novoselect = i;
                    System.out.println(novoselect);
                }
            }
        }
        else if(click.getSource()==drawingArea2){
            for (int i = 0; i < poligonos.size(); i++) {
                if (poligonos.get(i).isSelectedXZ(new Vertice3D(click.getX(), 0.0, click.getY()), 5)) {
                    novoselect = i;
                    System.out.println(novoselect);
                }
            }
        }
        if(novoselect==selecionado){
            selecionado=-1;
        }else{
            selecionado=novoselect;
        }
        if(selecionado==-1){
            System.out.println("nenhum pol selecionado");
        }
        this.clearALL();
        this.drawPoligonos();
        unsetclick();
    }



    public void cor(ActionEvent e){
        ColorPicker colorPicker = new ColorPicker();
        System.out.println(Cor.getValue());
        //poligonos.get(selecionado).face.Preenchimento=Cor.getValue();
        //System.out.println(poligonos.get(selecionado).face.Preenchimento);
        gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
        //drawall();
        selecionado=-1;
    }

    public void Translada(MouseEvent click){
        if(click.getSource()==drawingArea1) {
            Vertice3D v = new Vertice3D(click.getX(), click.getY(), 0.0);
            if (selecionado != -1) {
                if (click.getX() != poligonos.get(selecionado).Central.X)
                    if (click.getY() != poligonos.get(selecionado).Central.Y)
                        poligonos.get(selecionado).translada(v);
                gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
                this.drawPoligonos();
            }
        }
//        else if(click.getSource()==drawingArea3) {
//            Vertice3D v = new Vertice3D(1, click.getY(),click.getX());
//            if (selecionado != -1) {
//                if (click.getX() != poligonos.get(selecionado).Central.X)
//                    if (click.getY() != poligonos.get(selecionado).Central.Y)
//                        poligonos.get(selecionado).translada(v);
//                gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
//                this.drawPoligonos();
//            }
//        }else if(click.getSource()==drawingArea2) {
//            Vertice3D v = new Vertice3D(click.getX(), 1, click.getY());
//            if (selecionado != -1) {
//                if (click.getX() != poligonos.get(selecionado).Central.X)
//                    if (click.getY() != poligonos.get(selecionado).Central.Y)
//                        poligonos.get(selecionado).translada(v);
//                gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
//                this.drawPoligonos();
//            }
//        }
        unsetclick();

    }

//    public void cisalhamentox(MouseEvent e){
//        double fator = distancia(Pressed,new Vertice(e.getX(),e.getY()));
//        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
//                (distancia(poligonos.get(selecionado).Central,this.Pressed))){
//
//            poligonos.get(selecionado).cisalhamentoX(0.2);
//
//        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
//                (distancia(poligonos.get(selecionado).Central,this.Pressed))){
//
//            poligonos.get(selecionado).cisalhamentoX(-0.2);
//
//        }
//        setPressed(e);
//        gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
//        drawall();
//    }
//    public void cisalhamentoy(MouseEvent e){
//        double fator = distancia(Pressed,new Vertice(e.getX(),e.getY()));
//        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
//                (distancia(poligonos.get(selecionado).Central,this.Pressed))){
//
//            poligonos.get(selecionado).cisalhamentoY(0.2);
//
//        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
//                (distancia(poligonos.get(selecionado).Central,this.Pressed))){
//
//            poligonos.get(selecionado).cisalhamentoY(-0.2);
//
//        }
//        setPressed(e);
//        this.Fator=fator;
//        gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
//        drawall();
//    }

//    public void scalaxy(MouseEvent e){
//        double fator = distancia(this.Pressed,new Vertice(e.getX(),e.getY()));
//        if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )>
//                (distancia(poligonos.get(selecionado).Central,this.Pressed))){
//
//                poligonos.get(selecionado).scalaXY(1.03);
//
//        }else if((distancia(poligonos.get(selecionado).Central,new Vertice(e.getX(),e.getY())) )<
//                (distancia(poligonos.get(selecionado).Central,this.Pressed))){
//
//                poligonos.get(selecionado).scalaXY(0.97);
//
//        }
//        setPressed(e);
//        this.Fator=fator;
//        gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
//        drawall();
//        //poligonos.get(selecionado).printVertices();
//    }
//
//
//    public void Translada(MouseEvent mouseEvent) {
//        Vertice v = new Vertice(mouseEvent.getX(),mouseEvent.getY());
//        if(selecionado!=-1){
//            if(mouseEvent.getX()!=poligonos.get(selecionado).Central.X)
//                if(mouseEvent.getY()!=poligonos.get(selecionado).Central.Y)
//                    poligonos.get(selecionado).translada(v);
//                    gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
//                    drawall();
//        }
//        unsetclick();
//    }
//
    public void Exclui(){
        if(selecionado!=-1) {
            poligonos.remove(selecionado);
            selecionado = -1;
            gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
            for (Poligono3D p : poligonos) {
                p.drawXY(gc1,Color.BLACK);
            }
        }
    }

//
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
            poligonos = (ArrayList<Poligono3D>) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        System.out.println("este é o arquivo");
        gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
       // this.drawall();
        //return null;
    }


    public void novo(){
            this.gc1.clearRect(0, 0, drawingArea1.getWidth(), drawingArea1.getHeight());
            cliques = 0;
            poligonos.clear();
            selecionado=-1;
            novoIrregular.clear();
    }
//
//
//    public double DistanceFromLine(double cx, double cy, double ax, double ay ,double bx, double by){
//        double distanceSegment,distanceLine;
//        double r_numerator = (cx-ax)*(bx-ax) + (cy-ay)*(by-ay);
//        double r_denomenator = (bx-ax)*(bx-ax) + (by-ay)*(by-ay);
//        double r = r_numerator / r_denomenator;
//
//        double px = ax + r*(bx-ax);
//        double py = ay + r*(by-ay);
//
//        double s =  ((ay-cy)*(bx-ax)-(ax-cx)*(by-ay) ) / r_denomenator;
//
//        distanceLine = abs(s)*sqrt(r_denomenator);
//
//        double xx = px;
//        double yy = py;
//
//        if ( (r >= 0) && (r <= 1) )
//        {
//            distanceSegment = distanceLine;
//        }
//        else
//        {
//
//            double dist1 = (cx-ax)*(cx-ax) + (cy-ay)*(cy-ay);
//            double dist2 = (cx-bx)*(cx-bx) + (cy-by)*(cy-by);
//            if (dist1 < dist2)
//            {
//                xx = ax;
//                yy = ay;
//                distanceSegment = sqrt(dist1);
//            }
//            else
//            {
//                xx = bx;
//                yy = by;
//                distanceSegment = sqrt(dist2);
//            }
//
//
//        }
//
//        return distanceSegment;
//    }
//
//    public void drawall(){
//        for (Poligono p: poligonos) {
//            draw(p);
//        }
//        for(int i=0;i<(novoIrregular.size()-1);i++){
//            gc1.strokeLine(novoIrregular.get(i).X, novoIrregular.get(i).Y, novoIrregular.get(i+1).X,novoIrregular.get(i+1).Y);
//        }
//    }
//    public int intersec2d(Vertice iniScanL, Vertice fimScanL, Vertice iniAres, Vertice fimAres, Double s, Double t){
//        double det;
//
//        det = (fimAres.X - iniAres.X) * (fimScanL.Y - iniScanL.Y)  -  (fimAres.Y - iniAres.Y) * (fimScanL.X - iniScanL.Y);
//
//        if (det == 0.0)
//            return 0 ; // não há intersecção
//
//        s = ((fimAres.X - iniAres.X) * (iniAres.Y - iniScanL.Y) - (fimAres.Y - iniAres.Y) * (iniAres.X - iniScanL.X))/ det ;
//        t = ((fimScanL.X - iniScanL.X) * (iniAres.Y - iniScanL.Y) - (fimScanL.Y - iniScanL.Y) * (iniAres.X - iniScanL.X))/ det ;
//
//        return 1; // há intersecção
//    }
//    public Vertice PontoI(Aresta scanL,Aresta arestaPol){
//        Double s = new Double(0.0);
//        Double t = new Double(0.0);
//        int intercede=intersec2d(scanL.Inicio,scanL.Fim,arestaPol.Inicio,arestaPol.Fim,s,t);
//        if(intercede==0){
//            return null;
//        }else{
//            return (new Vertice((scanL.Inicio.X+((scanL.Fim.X-scanL.Inicio.X)*s)),(scanL.Inicio.Y+((scanL.Fim.Y-scanL.Inicio.Y)*s))));
//        }
//
//    }
//

}
