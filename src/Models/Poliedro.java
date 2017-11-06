package Models;

import java.util.ArrayList;

public class Poliedro {
    public ArrayList<Poligono3D> Faces;
    public ArrayList<Vertice3D> vertices;
    public Poliedro(){}
    public Poliedro(ArrayList<Poligono3D> faces){
        this.Faces=new ArrayList<Poligono3D>();
        this.Faces=faces;
        this.vertices=new ArrayList<Vertice3D>();
        for (Poligono3D p:Faces) {
            for (Vertice3D v: p.Vertices) {
                this.vertices.add(v);
            }
        }

    }
}
