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
public class CruceroObj {
    String nombre;
    int estrellas;
    int capacidad;
    int ranking;
    Servicios servicios;
    Reservas reservas;
    Comentarios comentarios;
    
    public CruceroObj(String Nombre, int Estrellas, int Capacidad){
        this.nombre = Nombre;
        this.estrellas = Estrellas;
        this.capacidad = Capacidad;
        this.servicios = new Servicios();
        this.reservas = new Reservas();
        this.comentarios = new Comentarios();
        this.ranking = 0;
    }
    
    public boolean equals(CruceroObj obj){
        boolean equalNombre = this.nombre.equalsIgnoreCase(obj.nombre);
        boolean equalEstrellas = this.estrellas == obj.estrellas;
        boolean equalCapacidad = this.capacidad == obj.capacidad;
        boolean equalRanking = this.ranking == obj.ranking;
        
        return equalNombre && equalEstrellas && equalCapacidad && equalRanking;
    }
    
//    public int compareTo(CruceroObj obj){
//        return this.nombre.compareTo(obj.nombre);
//    }
    
    public void agregarServicio(String servicio){
        this.servicios.agregarFinal(servicio);
    }
    
    public boolean existeServicio(String servicio){
        return this.servicios.existeServicio(servicio);
    }
    
    public void borrarServicio(String servicio){
        this.servicios.borrarElemento(servicio);
    }
    
    public void crearReserva(int cliente){
        if (this.reservas.cantElementosReserva() < this.capacidad){
            this.reservas.agregarReservaFinal(cliente);
        }
        else{
            this.reservas.agregarEsperaFinal(cliente);
        }
    }
    
    public boolean existeReserva(int cliente){
        return this.reservas.obtenerReserva(cliente) != null || this.reservas.obtenerEspera(cliente) != null;
    }
    
    public void cancelarReserva(int cliente){
        if (this.reservas.obtenerReserva(cliente) != null){
            this.reservas.borrarReserva(cliente);
            if (!this.reservas.esperaEsVacia()){
                int c = this.reservas.esperaInicio.getDato();
                this.reservas.agregarReservaFinal(c);
                this.reservas.borrarInicioEspera();
            }
        }
        else{
            this.reservas.borrarEspera(cliente);
        }
    }
    
    public void agregarComentario(int puntos, String texto){
        ComentarioObj c = new ComentarioObj(puntos, texto);
        this.comentarios.agregarInicio(c);
        this.ranking = this.comentarios.promedio();
    }
    
    public String listarServicios(){
        String ret= "";
        NodoServicio aux = this.servicios.getInicio();
        int i = 1;
        while(aux != null ){
            ret += i + " - " + aux.getDato() + "\n";
            aux = aux.getSig();
            i++;
        }
        return ret;
    }

    String listarComentarios() {
        String ret= "";
        NodoComentario aux = this.comentarios.getInicio();
        int i = 1;
        while(aux != null ){
            ret += i + " - " + aux.getDato().coment + " - " +  aux.getDato().ranking + "\n";
            aux = aux.getSig();
            i++;
        }
        return ret;
    }
}
