/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Mauricio Hiraaki Ishida
 * @author Hamã Candido
 */
public class Aresta {
    public Vertice Inicio;
    public Vertice Fim;
   // public Face Esquerda;
   // public Face Direita;
    public Aresta(){}
    public Aresta(Vertice inicio, Vertice fim){
        this.Inicio=inicio;
        this.Fim=fim;
    }
    
}
