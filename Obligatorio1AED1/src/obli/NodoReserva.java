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
public class NodoReserva {
    
    private int dato;
    private NodoReserva siguiente;
    
    
    //Constructor
    public NodoReserva(int n){
        this.dato = n;
        this.siguiente = null;
    }

    //Dato
    public void setDato(int d){
        this.dato = d;
    }
    public int getDato(){
        return this.dato;
    }

    //Siguiente
    public void setSig(NodoReserva s){
        this.siguiente = s;
    }
    public NodoReserva getSig(){
        return this.siguiente;
    }
    
}
