/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 *
 * @author Mauricio Hiraaki Ishida
 * @author Hamã Candido
 */
public class Poligono {
    public Vertice Central;
    public ArrayList<Aresta> Arestas;
    public ArrayList<Vertice> Vertices;
    //ArrayList<Face> Faces;
    public Poligono(){
        this.Central = new Vertice();
        this.Vertices = new ArrayList();
        this.Vertices = new ArrayList();

    }

    public Poligono(Vertice ini, Vertice fim, int lados){
        this.Central= new Vertice();
        this.Central= ini;
        this.Arestas = new ArrayList();
        this.Vertices = new ArrayList();
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

    }
    public Poligono(ArrayList<Vertice> vertices){
        Vertices = new ArrayList();
        for(Vertice v: vertices){
            Vertices.add(new Vertice(v.X,v.Y));
        }
        this.setArestas();
    }

    public void setArestas(){

        for (int i = 0; i < this.Vertices.size(); i++) {
            System.out.println(this.Vertices.get(i).X+" "+this.Vertices.get(i).Y);
            /*if(i!=this.Vertices.size()-1){
                this.Arestas.add(new Aresta(this.Vertices.get(i),this.Vertices.get(i+1)));
            }else {
                this.Arestas.add(new Aresta(Vertices.get(i),this.Vertices.get(0)));
            }
            System.out.println(this.Arestas.get(i).Inicio.X+" "+this.Arestas.get(i).Inicio.Y);
            System.out.println(this.Arestas.get(i).Fim.X+" "+this.Arestas.get(i).Fim.Y);
            */
        }
    }
    
    
}
