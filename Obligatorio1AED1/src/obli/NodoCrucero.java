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
public class NodoCrucero {
    
    private CruceroObj dato;
    private NodoCrucero siguiente;
    
    
    //Constructor
    public NodoCrucero(CruceroObj n){
        this.dato = n;
        this.siguiente = null;
    }

    //Dato
    public void setDato(CruceroObj d){
        this.dato = d;
    }
    public CruceroObj getDato(){
        return this.dato;
    }

    //Siguiente
    public void setSig(NodoCrucero s){
        this.siguiente = s;
    }
    public NodoCrucero getSig(){
        return this.siguiente;
    }
}
