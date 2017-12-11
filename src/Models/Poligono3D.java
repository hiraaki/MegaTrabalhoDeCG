package Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.StrictMath.sqrt;

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
        double rad= Math.toRadians(360.0/lados);
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
        double resp=Double.MAX_VALUE;
        double aux;
        for (Aresta3D aresta: this.Arestas) {
            aux=this.DistanceFromLine(ponto.X,ponto.Y,aresta.Inicio.X,aresta.Inicio.Y,aresta.Fim.X,aresta.Fim.Y);
            if(resp>=aux){
                resp=aux;
            }
        }

        return resp;
    }
    public double distanceZY(Vertice3D ponto){
        double resp=Double.MAX_VALUE;
        double aux;
        for (Aresta3D aresta: this.Arestas) {
            aux=this.DistanceFromLine(ponto.Z,ponto.Y,aresta.Inicio.Z,aresta.Inicio.Z,aresta.Fim.Z,aresta.Fim.Y);
            if(resp>=aux){
                resp=aux;
            }
        }
        return resp;
    }
    public double distanceXZ(Vertice3D ponto){
        double resp=Double.MAX_VALUE;
        double aux;
        for (Aresta3D aresta: this.Arestas) {
            aux=this.DistanceFromLine(ponto.X,ponto.Z,aresta.Inicio.X,aresta.Inicio.Z,aresta.Fim.X,aresta.Fim.Z);
            if(resp>=aux){
                resp=aux;
            }
        }
        return resp;
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
        if(this.distanceXZ(V)<=distancia){
            return true;
        }else {
            return false;
        }
    }
    public void calcCentroid(){
        Vertice3D maior=new Vertice3D(Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE);
        Vertice3D menor=new Vertice3D(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE);
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
    public void transladaXY(Vertice3D novoCentro){
        Vertice3D diferenca = new Vertice3D();
        diferenca.X=novoCentro.X-this.Central.X;
        diferenca.Y=novoCentro.Y-this.Central.Y;
        for (Vertice3D v: this.Vertices) {
            v.X=diferenca.X+v.X;
            v.Y=diferenca.Y+v.Y;
        }
        calcCentroid();
    }
    public void transladaZY(Vertice3D novoCentro){
        Vertice3D diferenca = new Vertice3D();
        diferenca.Y=novoCentro.Y-this.Central.Y;
        diferenca.Z=novoCentro.Z-this.Central.Z;
        for (Vertice3D v: this.Vertices) {
            v.Y=diferenca.Y+v.Y;
            v.Z=diferenca.Z+v.Z;
        }
        calcCentroid();
    }
    public void transladaXZ(Vertice3D novoCentro){
        Vertice3D diferenca = new Vertice3D();
        diferenca.X=novoCentro.X-this.Central.X;
        diferenca.Z=novoCentro.Z-this.Central.Z;
        for (Vertice3D v: this.Vertices) {
            v.X=diferenca.X+v.X;
            v.Z=diferenca.Z+v.Z;
        }
        calcCentroid();
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
