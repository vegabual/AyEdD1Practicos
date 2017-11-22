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
public class Comentarios {
    
    NodoComentario inicio;
    NodoComentario fin;
    
    public void Comentarios(){
        this.inicio = null;
        this.fin = null;
    }

    //Inicio
    public void setInicio(NodoComentario i){
        inicio=i;
    }
    
    public NodoComentario getInicio(){
        return inicio;
    }

    //Fin
    public void setFin(NodoComentario f){
        fin=f;
    }
    public NodoComentario getFin(){
        return fin;
    }
    
    //PRE:
    //POS: Retorna true si la lista no tiene nodos
    public boolean esVacia(){
        if (this.inicio == null){
            return true;
        }
        else{
            return false;
        }
    }
    
    //PRE: 
    //POS: Agrega un nuevo comentario al principio de la lista
    public void agregarInicio(ComentarioObj n){
        NodoComentario nuevo= new NodoComentario(n);
        nuevo.setSig(inicio);
        this.inicio=nuevo;
        if(this.fin==null){
            this.fin=nuevo;
        }
    }
    
    //PRE:
    //POS: Agrega un nuevo comentario al final de la lista
    public void agregarFinal(ComentarioObj n){
        if (this.esVacia()){
            this.agregarInicio(n);
        }
        else
        {
            NodoComentario nuevo = new NodoComentario(n);
            fin.setSig(nuevo);
            this.fin=nuevo;
        }
    }
    
    //PRE:
    //POS: Borra el primer comentario
    public void borrarInicio(){
        if (!this.esVacia()){
            this.inicio=this.inicio.getSig();
        }
    }
    
    //PRE:
    //POS: elimina todos los comentarios de la lista
    public void vaciar(){
        while (inicio!=null){
            borrarInicio();
        }
    }
    
    //PRE: 
    //POS: Retorna la cantidad de nodos que tiene la lista
    public int cantElementos(){
        int cont=0;
        if (!this.esVacia()){
            NodoComentario aux = this.inicio;
            while (aux != null){
                aux = aux.getSig();
                cont ++;
            }
        }
        return cont;
    }
    
    //PRE: 
    //POS: Retorna el promedio de ranking de los comentarios
    public int promedio(){
        int total=0;
        int cant=0;
        if (!this.esVacia()){
            NodoComentario aux = this.inicio;
            while (aux != null){
                total += aux.getDato().ranking;
                cant ++;
                aux = aux.getSig();
            }
        }
        return total/cant;
    }

}
