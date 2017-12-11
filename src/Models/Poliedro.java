package Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

public class Poliedro {
    public Vertice3D Central;
    public ArrayList<Poligono3D> Faces;
    public ArrayList<Vertice3D> Vertices;
    public Poliedro(){}
    /*
    * Gera um poliedro conectando faces uma a outra, criando novos poligonos laterias.
    * */
    public void linkfaces(ArrayList<Poligono3D> faces){
//        this.Faces=new ArrayList<Poligono3D>();
//        this.Faces=faces;
//        this.Vertices=new ArrayList<Vertice3D>();
        for (Poligono3D p:faces) {
            this.addFace(p);
        }
        this.addFace(faces.get(0));
        this.calcCentroid();

    }
    /*
    * Gera poliedros por rotação.
    * Para lado=1 gera por rotação em xy;
    * Para lado=2 gera por rotação em xz;
    * Para lado=3 gera por rotação em zy;
    * */
    public Poliedro(Poligono3D geratriz,int NumerodeFaces,int lado){
        this.Central = new Vertice3D();
        this.Vertices = new ArrayList<>();
        this.Faces = new ArrayList<>();
        if(lado==1){
            this.genByRotationXY(geratriz,NumerodeFaces);
        }else if(lado==2){
            this.genByRotationXZ(geratriz,NumerodeFaces);
        }else if(lado==3){
            this.genByRotationZY(geratriz,NumerodeFaces);
        }
        this.calcCentroid();

    }
    /*
    * Adiciona Uma face, Considerando que esta está na frente da ultima facem,
    * cria novas faces laterais para preenchimento do espaço entre as duas.
    * OBS:A lista de faces não pode ser vazia.
    * */
    public void addFace(Poligono3D poligono2){
        if(this.Faces.size()==0){
            this.Faces.add(poligono2);
        }else {
            Poligono3D poligono1 = this.Faces.get(this.Faces.size() - 1);
            for (int i = 0; i < poligono1.Vertices.size(); i++) {
                this.Vertices.add(poligono1.Vertices.get(i));
                this.Vertices.add(poligono2.Vertices.get(i));
                if (i!=poligono1.Vertices.size()-1) {
                    Poligono3D aux = new Poligono3D();
                    aux.Vertices.add(poligono2.Vertices.get(i));
                    aux.Vertices.add(poligono1.Vertices.get(i));
                    aux.Vertices.add(poligono1.Vertices.get(i + 1));
                    aux.Vertices.add(poligono2.Vertices.get(i + 1));
                    aux.setArestas();
                    this.Faces.add(aux);
                }
            }
            Poligono3D aux = new Poligono3D();
            aux.Vertices.add(poligono2.Vertices.get(poligono1.Vertices.size()-1));
            aux.Vertices.add(poligono1.Vertices.get(poligono1.Vertices.size()-1));
            aux.Vertices.add(poligono1.Vertices.get(0));
            aux.Vertices.add(poligono2.Vertices.get(0));
            aux.setArestas();
            this.Faces.add(aux);

            this.Faces.remove(poligono1);
            this.Faces.add(poligono2);

        }

    }
    public void genByRotationXY(Poligono3D geratriz,int faces){
        ArrayList <Poligono3D> polList= new ArrayList<Poligono3D>();
        double grau = 360.0/(double)faces;
        for(int i=0;i<=faces;i++){
            polList.add(new Poligono3D(geratriz.Vertices));
            geratriz.rotacaoY(grau);
        }
        linkfaces(polList);
    }
    public void genByRotationZY(Poligono3D geratriz,int faces){
        ArrayList <Poligono3D> polList= new ArrayList<Poligono3D>();
        for(int i=0;i<faces;i++){
            polList.add(new Poligono3D(geratriz.Vertices));
            geratriz.rotacaoZ(360.0/(double)faces);
        }

    }
    public void genByRotationXZ(Poligono3D geratriz,int faces){
        ArrayList <Poligono3D> polList= new ArrayList<Poligono3D>();
        for(int i=0;i<faces;i++){
            polList.add(new Poligono3D(geratriz.Vertices));
            geratriz.rotacaoX(360/faces);
        }
    }
    /*
    * Desenha de acordo com o plano selecionado
    * */
    public void draw(GraphicsContext gc,Color type, int plano){
        for (Poligono3D pol: this.Faces) {
            if(plano==1){
                pol.drawXY(gc,type);
            }else if(plano==2){
                pol.drawZY(gc,type);
            }else if (plano==3){
                pol.drawXZ(gc,type);
            }
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
    public void transladaXY(Vertice3D diferenca){
        for (Vertice3D v: this.Vertices) {
            v.X=diferenca.X+v.X;
            v.Y=diferenca.Y+v.Y;
        }
        calcCentroid();
    }
    public void transladaZY(Vertice3D diferenca){
        for (Vertice3D v: this.Vertices) {
            v.Y=diferenca.Y+v.Y;
            v.Z=diferenca.Z+v.Z;
        }
        calcCentroid();
    }
    public void transladaXZ(Vertice3D diferenca){
        for (Vertice3D v: this.Vertices) {
            v.X=diferenca.X+v.X;
            v.Z=diferenca.Z+v.Z;
        }
        calcCentroid();
    }
    public boolean isSelected(Vertice3D V){
        boolean selected=false;
        for (Poligono3D pol: this.Faces) {
            if(pol.isSelectedXY(V,5)){
                selected=true;
                break;
            }
            if(pol.isSelectedZY(V,5)){
                selected=true;
                break;
            }
            if(pol.isSelectedXZ(V,5)){
                selected=true;
                break;
            }

        }

        return selected;
    }


}
