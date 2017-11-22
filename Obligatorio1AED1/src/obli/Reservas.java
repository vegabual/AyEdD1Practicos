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
public class Reservas {
    
    NodoReserva reservasInicio;
    NodoReserva reservasFin;
    
    NodoReserva esperaInicio;
    NodoReserva esperaFin;
    
    public void Reservas(){
        this.reservasInicio = null;
        this.reservasFin = null;
        this.esperaInicio = null;
        this.esperaFin = null;
    }

    //PRE:
    //POS: Retorna true si la lista de reservas no tiene nodos
    public boolean reservasEsVacia(){
        if (this.reservasInicio == null){
            return true;
        }
        else{
            return false;
        }
    }
    
    //PRE: 
    //POS: Agrega una nueva reserva al principio de la lista
    public void agregarReservaInicio(int n){
        NodoReserva nuevo= new NodoReserva(n);
        nuevo.setSig(reservasInicio);
        this.reservasInicio=nuevo;
        if(this.reservasFin==null){
            this.reservasFin=nuevo;
        }
    }
    
    //PRE:
    //POS: Agrega una nueva reserva al final de la lista
    public void agregarReservaFinal(int n){
        if (this.reservasEsVacia()){
            this.agregarReservaInicio(n);
        }
        else
        {
            NodoReserva nuevo = new NodoReserva(n);
            reservasFin.setSig(nuevo);
            this.reservasFin=nuevo;
        }
    }
    
    //PRE:
    //POS: Borra la primer reserva
    public void borrarInicioReserva(){
        if (!this.reservasEsVacia()){
            this.reservasInicio = this.reservasInicio.getSig();
        }
    }
    
    //PRE:
    //POS: elimina todas las reservas de la lista
    public void vaciarReserva(){
        while (reservasInicio!=null){
            borrarInicioReserva();
        }
    }
    
    //PRE: 
    //POS: Retorna la cantidad de nodos que tiene la lista de reservas
    public int cantElementosReserva(){
        int cont=0;
        if (!this.reservasEsVacia()){
            NodoReserva aux = this.reservasInicio;
            while (aux != null){
                aux = aux.getSig();
                cont ++;
            }
        }
        return cont;
    }

    //PRE:
    //POS: Retorna true si la lista de espera no tiene nodos
    public boolean esperaEsVacia(){
        if (this.esperaInicio == null){
            return true;
        }
        else{
            return false;
        }
    }
    
    //PRE: 
    //POS: Agrega una nueva espera al principio de la lista
    public void agregarEsperaInicio(int n){
        NodoReserva nuevo= new NodoReserva(n);
        nuevo.setSig(esperaInicio);
        this.esperaInicio=nuevo;
        if(this.esperaFin==null){
            this.esperaFin=nuevo;
        }
    }
    
    //PRE:
    //POS: Agrega una nueva espera al final de la lista
    public void agregarEsperaFinal(int n){
        if (this.esperaEsVacia()){
            this.agregarEsperaInicio(n);
        }
        else
        {
            NodoReserva nuevo = new NodoReserva(n);
            esperaFin.setSig(nuevo);
            this.esperaFin=nuevo;
        }
    }
    
    //PRE:
    //POS: Borra la primer espera
    public void borrarInicioEspera(){
        if (!this.esperaEsVacia()){
            this.esperaInicio = this.esperaInicio.getSig();
        }
    }
    
    //PRE:
    //POS: elimina todas las esperas de la lista
    public void vaciarEspera(){
        while (esperaInicio!=null){
            borrarInicioEspera();
        }
    }
    
    //PRE: 
    //POS: Retorna la cantidad de nodos que tiene la lista de espera
    public int cantElementosEspera(){
        int cont=0;
        if (!this.esperaEsVacia()){
            NodoReserva aux = this.esperaInicio;
            while (aux != null){
                aux = aux.getSig();
                cont ++;
            }
        }
        return cont;
    }
    
    //PRE:
    //POS: elimina todas las esperas y reserfvas de las listas
    public void vaciarTodo(){
        vaciarEspera();
        vaciarReserva();
    }
    
    //PRE: 
    //POS: Devuelve la reserva cuyo cliente es "cliente"
    public NodoReserva obtenerReserva(int cliente){
        NodoReserva aux = this.reservasInicio;
        while (aux != null && aux.getDato() != cliente){
            aux = aux.getSig();
        } //encontré dato o llegué al final
        return aux;
    }
    
    //PRE: 
    //POS: Devuelve la espera cuyo cliente es "cliente"
    public NodoReserva obtenerEspera(int cliente){
        NodoReserva aux = this.esperaInicio;
        while (aux != null && aux.getDato() != cliente){
            aux = aux.getSig();
        } //encontré dato o llegué al final
        return aux;
    }
    
    //PRE: 
    //POS: Borra la primer ocurrencia de la reserva del cliente "cliente"
    public void borrarReserva(int cliente){
        if (this.reservasEsVacia())
            return;
        if (this.reservasInicio.getDato()==cliente)
            this.borrarInicioReserva();
        else
        {
            NodoReserva aux=this.reservasInicio;
            while (aux.getSig()!=null && aux.getSig().getDato() != cliente)
                aux=aux.getSig(); //lo encontré o llegué al final de la lista
            if (aux.getSig()!=null){
                NodoReserva borrar=aux.getSig();
                aux.setSig(borrar.getSig());
                borrar.setSig(null);
            }
        }
    }
    
    //PRE: 
    //POS: Borra la primer ocurrencia de la espera del cliente "cliente"
    public void borrarEspera(int cliente){
        if (this.esperaEsVacia())
            return;
        if (this.esperaInicio.getDato()==cliente)
            this.borrarInicioEspera();
        else
        {
            NodoReserva aux=this.esperaInicio;
            while (aux.getSig()!=null && aux.getSig().getDato() != cliente)
                aux=aux.getSig(); //lo encontré o llegué al final de la lista
            if (aux.getSig()!=null){
                NodoReserva borrar=aux.getSig();
                aux.setSig(borrar.getSig());
                borrar.setSig(null);
            }
        }
    }
}
