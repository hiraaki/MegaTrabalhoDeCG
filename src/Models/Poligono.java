/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 *
 * @author Mauricio Hiraaki Ishida
 * @author Hamã Candido
 */
public class Poligono implements Serializable{
    public Vertice Central;
    public ArrayList<Aresta> Arestas;
    public ArrayList<Vertice> Vertices;
    public Face face;
    public Poligono(){
        this.Central = new Vertice();
        this.Vertices = new ArrayList();
        this.Vertices = new ArrayList();
        this.face = new Face();
    }
    public Poligono(Vertice ini, Vertice fim, int lados, Color cor){
        this.Central= new Vertice();
        this.Central= ini;
        this.Arestas = new ArrayList();
        this.Vertices = new ArrayList();
        this.face = new Face(Color.BLACK,cor);
        Vertice V=new Vertice();
        V=ini;
        double cos;
        double sen;
        double xtemp;
        double ytemp;
        double grau = (360/lados);
        double R=abs(abs(fim.X)-abs(ini.X));
        System.out.println(this.Central.X+" "+this.Central.Y);
        for(int i=0;i<lados;i++){
            xtemp = (R * Math.cos((2 * Math.PI * (i)) / lados + grau) + V.X);
            ytemp = (R * Math.sin((2 * Math.PI * (i)) / lados + grau) + V.Y);
            Vertices.add(new Vertice(xtemp,ytemp));
            //System.out.println(this.Vertices.get(i).X+" "+this.Vertices.get(i).Y);
            //System.out.println(R);
        }
        /*for (int i =0 ; i<this.Vertices.size();i++){
            System.out.println(this.Vertices.get(i).X+" "+this.Vertices.get(i).Y);
        }*/
        this.setArestas();
        System.out.println("Area: "+getArea());
        calcCentroid();
        System.out.println("Centroid :"+Central.X+","+Central.Y);
        this.printVertices();

    }

    public Poligono(Vertice ini, Vertice fim, int lados){
        this.Central= new Vertice();
        this.Central= ini;
        this.Arestas = new ArrayList();
        this.Vertices = new ArrayList();
        this.face = new Face();
        Vertice V=new Vertice();
        V=ini;
        double cos;
        double sen;
        double xtemp;
        double ytemp;
        double grau = (360/lados);
        double R=abs(abs(fim.X)-abs(ini.X));
        System.out.println(this.Central.X+" "+this.Central.Y);
        for(int i=0;i<lados;i++){
            xtemp = (R * Math.cos((2 * Math.PI * (i)) / lados + grau) + V.X);
            ytemp = (R * Math.sin((2 * Math.PI * (i)) / lados + grau) + V.Y);
            Vertices.add(new Vertice(xtemp,ytemp));
            //System.out.println(this.Vertices.get(i).X+" "+this.Vertices.get(i).Y);
            //System.out.println(R);
        }
        /*for (int i =0 ; i<this.Vertices.size();i++){
            System.out.println(this.Vertices.get(i).X+" "+this.Vertices.get(i).Y);
        }*/
        this.setArestas();
        System.out.println("Area: "+getArea());
        calcCentroid();
        System.out.println("Centroid :"+Central.X+","+Central.Y);
        this.printVertices();

    }
    public Poligono(ArrayList<Vertice> vertices){
        this.Central = new Vertice();
        Vertices = new ArrayList();
        Arestas = new ArrayList();
        this.face = new Face();
        for(Vertice v: vertices){
            Vertices.add(new Vertice(v.X,v.Y));
        }
        this.setArestas();
        System.out.println("Area: "+getArea());
        calcCentroid();
        System.out.println("Centroid :"+Central.X+","+Central.Y);
        this.printVertices();
    }

    public void setArestas(){
        for (int i = 0; i < this.Vertices.size(); i++) {
            if(i!=this.Vertices.size()-1){
                this.Arestas.add(new Aresta(this.Vertices.get(i),this.Vertices.get(i+1)));
            }else if(i==this.Vertices.size()-1){
                this.Arestas.add(new Aresta(this.Vertices.get(i),this.Vertices.get(0)));
            }

        }
    }
    public double getArea(){
        double area=0;
        double soma=0;
        Aresta a= new Aresta();
        for (int i = 0; i < this.Arestas.size(); i++) {
            a=this.Arestas.get(i);
            soma+=(a.Inicio.X*a.Fim.Y)-(a.Fim.X*a.Inicio.Y);
        }
        area=soma/2;
        return area;
    }
    public void calcCentroid(){
        double soma=0;
        double area = getArea();
        Aresta a= new Aresta();
        for (int i = 0; i < this.Arestas.size(); i++) {
            a=this.Arestas.get(i);
            soma+=(a.Inicio.X+a.Fim.X)*((a.Inicio.X*a.Fim.Y)-(a.Fim.X*a.Inicio.Y));
        }
        this.Central.X=soma/(6*area);
        soma=0;
        for (int i = 0; i < this.Arestas.size(); i++) {
            a=this.Arestas.get(i);
            soma+=(a.Inicio.Y+a.Fim.Y)*((a.Inicio.X*a.Fim.Y)-(a.Fim.X*a.Inicio.Y));
        }
        this.Central.Y=soma/(6*area);

    }
    public void printVertices(){
        for (Vertice v:Vertices) {
            System.out.println("("+v.X+","+v.Y+")");
        }
    }
    public void translada(Vertice novoCentro){
        Vertice diferenca = new Vertice();
        diferenca.X=novoCentro.X-this.Central.X;
        diferenca.Y=novoCentro.Y-this.Central.Y;
        for (Vertice v: Vertices) {
            v.X=v.X+diferenca.X;
            v.Y=v.Y+diferenca.Y;
        }
        this.calcCentroid();
    }
    public void scalaX(double novoTamanho){
        for(Vertice v : Vertices){
            v.X=v.X*novoTamanho;
        }
        Vertice antigoCentro = new Vertice(this.Central.X,this.Central.Y);
        calcCentroid();
        this.translada(antigoCentro);
    }
    public void scalaY(double novoTamanho){
        for(Vertice v : Vertices){
            v.Y=v.Y*novoTamanho;
        }
        Vertice antigoCentro = new Vertice(this.Central.X,this.Central.Y);
        calcCentroid();
        this.translada(antigoCentro);
    }
    public void scalaXY(double multiplicador){
        for(Vertice v : Vertices){
            v.Y=v.Y*multiplicador;
            v.X=v.X*multiplicador;
        }
        Vertice antigoCentro = new Vertice(this.Central.X,this.Central.Y);
        calcCentroid();
        //System.out.println(antigoCentro.X+" "+antigoCentro.Y);
        //System.out.println(this.Central.X+" "+this.Central.Y);
        this.translada(antigoCentro);

    }
    public void rotaciona(double radians){
        double seno = Math.sin(radians);
        double cose = Math.cos(radians);
        double ante=0;
        for(Vertice v: Vertices){
            ante=(v.X*cose)-(v.Y*seno);
            v.Y=(v.X*seno)+(v.Y*cose);
            v.X=ante;
        }
        Vertice antigoCentro = new Vertice(this.Central.X,this.Central.Y);
        calcCentroid();
        this.translada(antigoCentro);

    }
    public void cisalhamentoX(double fator){
        for(Vertice v: Vertices){
            v.X=v.X+(fator*v.Y);
        }
        Vertice antigoCentro = new Vertice(this.Central.X,this.Central.Y);
        calcCentroid();
        this.translada(antigoCentro);
    }
    public void cisalhamentoY(double fator){
        for(Vertice v: Vertices){
            v.Y=v.Y+(fator*v.X);
        }
        Vertice antigoCentro = new Vertice(this.Central.X,this.Central.Y);
        calcCentroid();
        this.translada(antigoCentro);
    }


}
