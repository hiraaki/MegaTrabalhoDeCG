/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import java.util.ArrayList;
/**
 *
 * @author mhi
 */
public class Poligono {
    Vertice Central;
    ArrayList<Aresta> Arestas;
    ArrayList<Vertice> Vertices;
    //ArrayList<Face> Faces;
    
    public Poligono(Vertice ini, Vertice fim, int lados){
        this.Central= ini;
        //x' = x*cos(0) - y* sen(0);
        //y' = x*sen(0) + y*cos(0);
        Vertice V=new Vertice();        
        double cos;
        cos = Math.cos(Math.toRadians((360/lados)/2));
        double sen;
        sen = Math.sin(Math.toRadians((360/lados)/2));
        V.X=(fim.X*cos)-(fim.Y*sen);
        V.Y=(fim.X*sen)+(fim.Y*cos);
        cos = Math.cos(Math.toRadians(360/lados));
        sen = Math.sin(Math.toRadians(360/lados));
        this.Vertices.add(V);
        Vertice N=new Vertice();           
        for(int i=0;i<lados;i++){
            N.X=(V.X*cos)-(V.Y*sen);
            N.Y=(V.X*sen)+(V.Y*cos);          
            this.Vertices.add(N);
            V=N;     
        }        
        setArestas();
    }
    public Poligono(ArrayList<Aresta> arestas){
        this.Arestas=arestas;
        this.setArestas();
    }
    public void setArestas(){
        Aresta Na =new Aresta();
        for (int i = 0; i < Vertices.size(); i++) {
            Na.Inicio=Vertices.get(i);
            Na.Fim=Vertices.get(i+1);
            this.Arestas.add(Na);
        }
    }
    
    
}
