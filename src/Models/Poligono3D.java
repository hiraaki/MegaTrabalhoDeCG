package Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Poligono3D {
    public Vertice3D Central;
    public ArrayList<Aresta3D> Arestas;
    public ArrayList<Vertice3D> Vertices;
    public Poligono3D(){
        this.Vertices=new ArrayList<Vertice3D>();
        this.Arestas=new ArrayList<Aresta3D>();
    }

    /*
    * Cria poligono de acordo com o plano selecionado
    * Se plano=1 cria em XY
    * Se plano=2 cria em ZY
    * se Plano=3 cria em XZ
    * */
    public Poligono3D(Vertice3D ini,double raio,int lados,int plano){

        this.Vertices = new ArrayList<>();
        this.Arestas = new ArrayList<>();
        this.Central = new Vertice3D();
        double rad= Math.toDegrees(360/lados);
        //System.out.println("desenha4");
        double R=raio;
        double xtemp;
        double ytemp;
        double ztemp;
        if(plano==1){
            for(int i=0;i<lados;i++) {
                xtemp = (R * Math.cos((2 * Math.PI * (i)) / lados + rad) + ini.X);
                ytemp = (R * Math.sin((2 * Math.PI * (i)) / lados + rad) + ini.Y);
                ztemp = 1;
                this.Vertices.add(new Vertice3D(xtemp, ytemp,ztemp));
            }
        }else if(plano==2){
            for(int i=0;i<lados;i++) {
                xtemp = 1;
                ytemp = (R * Math.sin((2 * Math.PI * (i)) / lados + rad) + ini.Y);
                ztemp = (R * Math.cos((2 * Math.PI * (i)) / lados + rad) + ini.Z);
                this.Vertices.add(new Vertice3D(xtemp, ytemp,ztemp));
            }
        }else if(plano==3){
            for(int i=0;i<lados;i++) {
                xtemp = (R * Math.sin((2 * Math.PI * (i)) / lados + rad) + ini.X);
                ytemp = 1;
                ztemp = (R * Math.cos((2 * Math.PI * (i)) / lados + rad) + ini.Z);
                this.Vertices.add(new Vertice3D(xtemp, ytemp,ztemp));
            }
        }
    }

    public Poligono3D(ArrayList<Vertice3D> vertices){
        this.Central = new Vertice3D();
        this.Vertices = new ArrayList();
        this.Arestas = new ArrayList();
        for(Vertice3D v: vertices){
            Vertices.add(new Vertice3D(v.X,v.Y,v.Z));
        }
        this.setArestas();
    }
    public void setArestas(){
        for (int i = 0; i < this.Vertices.size(); i++) {
            if(i!=this.Vertices.size()-1){
                this.Arestas.add(new Aresta3D(this.Vertices.get(i),this.Vertices.get(i+1)));
            }else if(i==this.Vertices.size()-1){
                this.Arestas.add(new Aresta3D(this.Vertices.get(i),this.Vertices.get(0)));
            }
        }
    }
    public void rotacaoZ(double degress){
        double radians=Math.toRadians(degress);
        double seno = Math.sin(radians);
        double cose = Math.cos(radians);
        double ante=0;
        for(Vertice3D v: this.Vertices){
            ante=(v.X*cose)-(v.Y*seno);
            v.Y=(v.X*seno)+(v.Y*cose);
            v.X=ante;
        }
    }
    public void rotacaoX(double degress){
        double radians=Math.toRadians(degress);
        double seno = Math.sin(radians);
        double cose = Math.cos(radians);
        double ante=0;
        for(Vertice3D v: this.Vertices){
            ante=(v.Y*cose)-(v.Z*seno);
            v.Z=(v.Y*seno)+(v.Z*cose);
            v.Y=ante;
        }
    }
    public void rotacaoY(double degress){
        double radians=Math.toRadians(degress);
        double seno = Math.sin(radians);
        double cose = Math.cos(radians);
        double ante=0;
        for(Vertice3D v: this.Vertices){
            ante=(v.X*cose)+(v.Z*seno);
            v.Z=(v.Z*cose)-(v.X*seno);
            v.X=ante;
        }
    }
    public void drawXY(GraphicsContext graphicsXY){
        graphicsXY.setStroke(Color.BLACK);
        for(int i=0;i<this.Vertices.size();i++){
            if(i!=this.Vertices.size()-1){
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Y, this.Vertices.get(i+1).X,this.Vertices.get(i+1).Y);
            }else{
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Y, this.Vertices.get(0).X,this.Vertices.get(0).Y);
            }
        }
    }

    public void drawZY(GraphicsContext graphicsXY){
        graphicsXY.setStroke(Color.BLACK);
        for(int i=0;i<this.Vertices.size();i++){
            if(i!=this.Vertices.size()-1){
                graphicsXY.strokeLine(this.Vertices.get(i).Z, this.Vertices.get(i).Y, this.Vertices.get(i+1).Z,this.Vertices.get(i+1).Y);
            }else{
                graphicsXY.strokeLine(this.Vertices.get(i).Z, this.Vertices.get(i).Y, this.Vertices.get(0).Z,this.Vertices.get(0).Y);
            }
        }
    }

    public void drawXZ(GraphicsContext graphicsXY){
        graphicsXY.setStroke(Color.BLACK);
        for(int i=0;i<this.Vertices.size();i++){
            if(i!=this.Vertices.size()-1){
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Z, this.Vertices.get(i+1).X,this.Vertices.get(i+1).Z);
            }else{
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Z, this.Vertices.get(0).X,this.Vertices.get(0).Z);
            }
        }
    }


}
