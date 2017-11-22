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
public class NodoServicio {
    
    private String dato;
    private NodoServicio siguiente;
    
    
    //Constructor
    public NodoServicio(String n){
        this.dato = n;
        this.siguiente = null;
    }

    //Dato
    public void setDato(String d){
        this.dato = d;
    }
    public String getDato(){
        return this.dato;
    }

    //Siguiente
    public void setSig(NodoServicio s){
        this.siguiente = s;
    }
    public NodoServicio getSig(){
        return this.siguiente;
    }
}
