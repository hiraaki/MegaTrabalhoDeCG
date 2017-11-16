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
        this.setArestas();
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
    public void drawXY(GraphicsContext graphicsXY,Color type){
        graphicsXY.setStroke(type);
        for(int i=0;i<this.Vertices.size();i++){
            if(i!=this.Vertices.size()-1){
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Y, this.Vertices.get(i+1).X,this.Vertices.get(i+1).Y);
            }else{
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Y, this.Vertices.get(0).X,this.Vertices.get(0).Y);
            }
        }
    }
    public void drawZY(GraphicsContext graphicsXY,Color type){
        graphicsXY.setStroke(type);
        for(int i=0;i<this.Vertices.size();i++){
            if(i!=this.Vertices.size()-1){
                graphicsXY.strokeLine(this.Vertices.get(i).Z, this.Vertices.get(i).Y, this.Vertices.get(i+1).Z,this.Vertices.get(i+1).Y);
            }else{
                graphicsXY.strokeLine(this.Vertices.get(i).Z, this.Vertices.get(i).Y, this.Vertices.get(0).Z,this.Vertices.get(0).Y);
            }
        }
    }
    public void drawXZ(GraphicsContext graphicsXY,Color type){
        graphicsXY.setStroke(type);
        for(int i=0;i<this.Vertices.size();i++){
            if(i!=this.Vertices.size()-1){
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Z, this.Vertices.get(i+1).X,this.Vertices.get(i+1).Z);
            }else{
                graphicsXY.strokeLine(this.Vertices.get(i).X, this.Vertices.get(i).Z, this.Vertices.get(0).X,this.Vertices.get(0).Z);
            }
        }
    }
    public double distanceXY(Vertice3D ponto){
        double menor = 999999;
        for (Aresta3D a: this.Arestas) {
            double distanceSegment,distanceLine;
            double r_numerator = (ponto.X-a.Inicio.X)*(a.Fim.X-a.Inicio.X) + (ponto.Y-a.Inicio.Y)*(a.Fim.Y-a.Inicio.Y);
            double r_denomenator = (a.Fim.X-a.Inicio.X)*(a.Fim.X-a.Inicio.X) + (a.Fim.Y-a.Inicio.Y)*(a.Fim.Y-a.Inicio.Y);
            double r = r_numerator / r_denomenator;

            double px = a.Inicio.X + r*(a.Fim.X-a.Inicio.X);
            double py = a.Inicio.Y + r*(a.Fim.Y-a.Inicio.Y);

            double s =  ((a.Inicio.Y-ponto.Y)*(a.Fim.X-a.Inicio.X)-(a.Inicio.X-ponto.X)*(a.Fim.Y-a.Inicio.Y) ) / r_denomenator;

            distanceLine = abs(s)*Math.sqrt(r_denomenator);

            double xx = px;
            double yy = py;

            if ( (r >= 0) && (r <= 1) ){
                distanceSegment = distanceLine;
            }
            else{
                double dist1 = (ponto.X-a.Inicio.X)*(ponto.X-a.Inicio.X) + (ponto.Y-a.Inicio.Y)*(ponto.Y-a.Inicio.Y);
                double dist2 = (ponto.X-a.Fim.X)*(ponto.X-a.Fim.X) + (ponto.Y-a.Fim.Y)*(ponto.Y-a.Fim.Y);
                if (dist1 < dist2){
                    xx = a.Inicio.X;
                    yy = a.Inicio.Y;
                    distanceSegment = Math.sqrt(dist1);
                }
                else{
                    xx = a.Fim.X;
                    yy = a.Fim.Y;
                    distanceSegment = Math.sqrt(dist2);
                }
            }
            if(menor>=distanceSegment){
                menor=distanceSegment;
            }
        }
        return menor;
    }
    public double distanceZY(Vertice3D ponto){
        double menor = 999999;
        for (Aresta3D a: this.Arestas) {
            double distanceSegment,distanceLine;
            double r_numerator = (ponto.Z-a.Inicio.Z)*(a.Fim.Z-a.Inicio.Z) + (ponto.Y-a.Inicio.Y)*(a.Fim.Y-a.Inicio.Y);
            double r_denomenator = (a.Fim.Z-a.Inicio.Z)*(a.Fim.Z-a.Inicio.Z) + (a.Fim.Y-a.Inicio.Y)*(a.Fim.Y-a.Inicio.Y);
            double r = r_numerator / r_denomenator;

            double px = a.Inicio.Z + r*(a.Fim.X-a.Inicio.Z);
            double py = a.Inicio.Y + r*(a.Fim.Y-a.Inicio.Y);

            double s =  ((a.Inicio.Y-ponto.Y)*(a.Fim.Z-a.Inicio.Z)-(a.Inicio.Z-ponto.Z)*(a.Fim.Y-a.Inicio.Y) ) / r_denomenator;

            distanceLine = abs(s)*Math.sqrt(r_denomenator);

            double xx = px;
            double yy = py;

            if ( (r >= 0) && (r <= 1) ){
                distanceSegment = distanceLine;
            }
            else{
                double dist1 = (ponto.Z-a.Inicio.Z)*(ponto.Z-a.Inicio.Z) + (ponto.Y-a.Inicio.Y)*(ponto.Y-a.Inicio.Y);
                double dist2 = (ponto.Z-a.Fim.Z)*(ponto.Z-a.Fim.Z) + (ponto.Y-a.Fim.Y)*(ponto.Y-a.Fim.Y);
                if (dist1 < dist2){
                    xx = a.Inicio.Z;
                    yy = a.Inicio.Y;
                    distanceSegment = Math.sqrt(dist1);
                }
                else{
                    xx = a.Fim.Z;
                    yy = a.Fim.Y;
                    distanceSegment = Math.sqrt(dist2);
                }
            }
            if(menor>=distanceSegment){
                menor=distanceSegment;
            }
        }
        return menor;
    }
    public boolean isSelectedXY(Vertice3D V,double distancia){
        if(this.distanceXY(V)<=distancia){
            return true;
        }else {
            return false;
        }
    }
    public boolean isSelectedZY(Vertice3D V,double distancia){
        if(this.distanceZY(V)<=distancia){
            return true;
        }else {
            return false;
        }
    }
    public boolean isSelectedXZ(Vertice3D V,double distancia){
        if(this.distanceZY(V)<=distancia){
            return true;
        }else {
            return false;
        }
    }
    public void calcCentroid(){
        Vertice3D maior=new Vertice3D(-99999,-9999,-9999);
        Vertice3D menor=new Vertice3D(999999,99999,99999);
        for (Vertice3D V: this.Vertices) {
            if(V.X>=maior.X){
                maior.X=V.X;
            }
            if(V.Y>=maior.Y){
                maior.Y=V.Y;
            }
            if(V.Z>maior.Z){
                maior.Z=V.Z;
            }
            if(V.X<menor.X){
                menor.X=V.X;
            }
            if(V.Y<menor.Y){
                menor.Y=V.Y;
            }
            if(V.Z<menor.Z){
                menor.Z=V.Z;
            }
        }
        this.Central.X=menor.X+((maior.X-menor.X)/2);
        this.Central.Y=menor.Y+((maior.Y-menor.Y)/2);
        this.Central.Z=menor.Z+((maior.Z-menor.Z)/2);
    }
    public void translada(Vertice3D novoCentro){
        Vertice3D diferenca = new Vertice3D();
        diferenca.X=novoCentro.X-this.Central.X;
        diferenca.Y=novoCentro.Y-this.Central.Y;
        diferenca.Z=novoCentro.Z-this.Central.Z;
        for (Vertice3D v: this.Vertices) {
            v.X=diferenca.X+v.X;
            v.Y=diferenca.Y+v.Y;
            v.Z=diferenca.Z+v.Z;
        }
        calcCentroid();
    }


}
