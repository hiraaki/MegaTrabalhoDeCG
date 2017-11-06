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
    public void rotacionaX(){

    }

}
