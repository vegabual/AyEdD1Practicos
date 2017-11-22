/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obli;

/**
 *
 * @author veronicab-ot
 */
public class NodoComentario {
    ComentarioObj dato;
    NodoComentario siguiente;
    
    //Constructor
    public NodoComentario(ComentarioObj n){
        this.dato = n;
        this.siguiente = null;
    }

    //Dato
    public void setDato(ComentarioObj d){
        this.dato = d;
    }
    public ComentarioObj getDato(){
        return this.dato;
    }

    //Siguiente
    public void setSig(NodoComentario s){
        this.siguiente = s;
    }
    public NodoComentario getSig(){
        return this.siguiente;
    }
    
}
