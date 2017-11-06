package Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Poligono3D {
    public Vertice3D Central;
    public ArrayList<Aresta3D> Arestas;
    public ArrayList<Vertice3D> Vertices;
    public Poligono3D(){}
    public Poligono3D(ArrayList<Vertice3D> vertices){
        this.Central = new Vertice3D();
        this.Vertices = new ArrayList();
        Arestas = new ArrayList();
        for(Vertice3D v: vertices){
            Vertices.add(new Vertice3D(v.X,v.Y,v.Z));
        }
        this.setArestas();
        System.out.println("Area: "+getArea());
        calcCentroid();
        System.out.println("Centroid :"+Central.X+","+Central.Y);
        //this.printVertices();
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
    public double getArea(){
        double area=0;
        double soma=0;
        Aresta3D a= new Aresta3D();
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
        Aresta3D a= new Aresta3D();
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
}
