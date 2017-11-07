package Models;

import java.util.ArrayList;

public class Poliedro {
    public ArrayList<Poligono3D> Faces;
    public ArrayList<Vertice3D> Vertices;
    public Poliedro(){}
    /*
    * Gera um poliedro conectando faces uma a outra, criando novos poligonos laterias.
    * */
    public Poliedro(ArrayList<Poligono3D> faces){
        this.Faces=new ArrayList<Poligono3D>();
        this.Faces=faces;
        this.Vertices=new ArrayList<Vertice3D>();
        for (Poligono3D p:Faces) {
            this.addFace(p);
        }
        this.addFace(faces.get(0));

    }
    /*
    * Gera poliedros por rotação.
    * Para lado=1 gera por rotação em xy;
    * Para lado=2 gera por rotação em xz;
    * Para lado=3 gera por rotação em zy;
    * */
    public void Poliedro(Poligono3D geratriz,int NumerodeFaces,int lado){
        if(lado==1){
            this.genByRotationXY(geratriz,NumerodeFaces);
        }else if(lado==2){
            this.genByRotationXZ(geratriz,NumerodeFaces);
        }else if(lado==3){
            this.genByRotationZY(geratriz,NumerodeFaces);
        }

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
                if ((i % 2) != 0) {
                    Poligono3D aux = new Poligono3D();
                    aux.Vertices.add(poligono1.Vertices.get(i));
                    aux.Vertices.add(poligono2.Vertices.get(i));
                    aux.Vertices.add(poligono2.Vertices.get(i - 1));
                    aux.Vertices.add(poligono1.Vertices.get(i - 1));
                    aux.setArestas();
                    this.Faces.add(aux);
                }

            }
        }

    }
    public void genByRotationXY(Poligono3D geratriz,int faces){
        ArrayList <Poligono3D> polList= new ArrayList<Poligono3D>();
        for(int i=0;i<faces;i++){
            polList.add(new Poligono3D(geratriz.Vertices));
            geratriz.rotacaoZ(360/faces);
        }
    }
    public void genByRotationZY(Poligono3D geratriz,int faces){
        ArrayList <Poligono3D> polList= new ArrayList<Poligono3D>();
        for(int i=0;i<faces;i++){
            polList.add(new Poligono3D(geratriz.Vertices));
            geratriz.rotacaoX(360/faces);
        }
    }
    public void genByRotationXZ(Poligono3D geratriz,int faces){
        ArrayList <Poligono3D> polList= new ArrayList<Poligono3D>();
        for(int i=0;i<faces;i++){
            polList.add(new Poligono3D(geratriz.Vertices));
            geratriz.rotacaoY(360/faces);
        }
    }


}
