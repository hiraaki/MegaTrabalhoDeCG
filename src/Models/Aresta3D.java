package Models;

import java.util.ArrayList;

public class Aresta3D{
    public Vertice3D Inicio;
    public Vertice3D Fim;
    public Aresta3D(){
        this.Inicio=new Vertice3D();
        this.Fim=new Vertice3D();
    }
    public Aresta3D(Vertice3D inicio, Vertice3D fim){
        this.Inicio=inicio;
        this.Fim=fim;
    }
}

