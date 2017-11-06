package Models;

import java.util.ArrayList;

public class Poliedro {
    public ArrayList<Poligono3D> Faces;
    public ArrayList<Vertice3D> Vertices;
    public Poliedro(){}
    public Poliedro(ArrayList<Poligono3D> faces){
        this.Faces=new ArrayList<Poligono3D>();
        this.Faces=faces;
        this.Vertices=new ArrayList<Vertice3D>();
        for (Poligono3D p:Faces) {
            for (Vertice3D v: p.Vertices) {
                this.Vertices.add(v);
            }
        }

    }
    public void Poliedro(Poligono3D poligono1,Poligono3D poligono2){
        for (int i=0;i<poligono1.Vertices.size();i++){

        }

    }
}
