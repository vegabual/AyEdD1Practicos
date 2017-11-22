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
public class Servicios {
    
    NodoServicio inicio;
    NodoServicio fin;
    
    public void Servicios(){
        this.inicio = null;
        this.fin = null;
    }

    //Inicio
    public void setInicio(NodoServicio i){
        inicio=i;
    }
    
    public NodoServicio getInicio(){
        return inicio;
    }

    //Fin
    public void setFin(NodoServicio f){
        fin=f;
    }
    
    public NodoServicio getFin(){
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
    //POS: Agrega un nuevo servicio al principio de la lista
    public void agregarInicio(String n){
        NodoServicio nuevo= new NodoServicio(n);
        nuevo.setSig(inicio);
        this.inicio=nuevo;
        if(this.fin==null){
            this.fin=nuevo;
        }
    }
    
    //PRE:
    //POS: Agrega un nuevo servicio al final de la lista
    public void agregarFinal(String n){
        if (this.esVacia()){
            this.agregarInicio(n);
        }
        else
        {
            NodoServicio nuevo = new NodoServicio(n);
            fin.setSig(nuevo);
            this.fin=nuevo;
        }
    }
    
    //PRE:
    //POS: Borra el primer servicio
    public void borrarInicio(){
        if (!this.esVacia()){
            this.inicio=this.inicio.getSig();
        }
    }
    
    //PRE:
    //POS: elimina todos los servicios de la lista
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
            NodoServicio aux = this.inicio;
            while (aux != null){
                aux = aux.getSig();
                cont ++;
            }
        }
        return cont;
    }

    //PRE: 
    //POS: Borra la primer ocurrencia de un elemento dado
    public void borrarElemento(String n){
        if (this.esVacia()){
            return;
        }
        if (this.inicio.getDato().equalsIgnoreCase(n)){
            this.borrarInicio();
        }
        else
        {
            NodoServicio aux = this.inicio;
            while (aux.getSig() != null && !aux.getSig().getDato().equalsIgnoreCase(n)){
                aux = aux.getSig();
            }
            if (aux.getSig()!=null){
                NodoServicio borrar = aux.getSig();
                aux.setSig(borrar.getSig());
                borrar.setSig(null);
            }
        }
    }
    
    //PRE: 
    //POS: Devuelve un booleano indicando si existe el servicio "servicio"
    public boolean existeServicio(String servicio){
        NodoServicio aux = this.inicio;
        boolean existe = false;
        while (aux != null && !existe){
            existe = aux.getDato().equalsIgnoreCase(servicio);
            aux = aux.getSig();
        } //encontré dato o llegué al final
        return existe;
    }
}
