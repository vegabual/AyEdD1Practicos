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
public class CiudadObj {
    String nombre;
    Cruceros cruceros;
    
    public CiudadObj(String nombre){
        this.nombre = nombre;
        this.cruceros = new Cruceros();
    }
    
    public boolean equalsNombre(String nombre){
        return this.nombre.equalsIgnoreCase(nombre);
    }
    
    public String listarServicios(String nombreCrucero){
        NodoCrucero c = this.cruceros.obtenerCruceroNombre(nombreCrucero);
        String listaServicios = this.cruceros.listarServicios(c);
        String ret = "";
        if (listaServicios.length() == 0){
            ret = "No existen servicios registrados en el crucero " + nombreCrucero + " " + this.nombre;
        }
        else{
            ret = "Servicios del crucero " + nombreCrucero + this.nombre + "\n";
            ret += listaServicios;
        }
        return ret;
    }
    
    
    public String listarComentarios(String nombreCrucero){
        NodoCrucero c = this.cruceros.obtenerCruceroNombre(nombreCrucero);
        String listaComentarios = this.cruceros.listarComentarios(c);
        String ret = "";
        if (listaComentarios.length() == 0){
            ret = "No se han agregado comentarios al crucero " + nombreCrucero + " " + this.nombre;
        }
        else{
            ret = listaComentarios;
        }
        return ret;
    }
    
    public Cruceros ordenarCrucerosPorRankingAsc(){
        NodoCrucero aux = this.cruceros.inicio;
        Cruceros nueva = new Cruceros();
        while (aux != null){
            nueva.agregarOrdenado(aux.getDato());
            aux = aux.getSig();
        }
        return nueva;
    }
    
    public String listarCrucerosPorNombre() {
        String ret = "";
        if (this.cruceros.cantElementos() > 0){
            Cruceros c = this.cruceros.ordenarCrucerosPorNombre();
            ret = "Cruceros en " + this.nombre + "\n" + c.listarCrucerosNombre();
        }
        else{
            ret = "No existen cruceros registrados en " + this.nombre;
        }
        return ret;
    }
    
    public String listarCrucerosPorRankingAsc() {
        String ret = "";
        if (this.cruceros.cantElementos() > 0){
            ret =  "Cruceros en " + this.nombre + "\n" + this.cruceros.listarCrucerosRanking();
        }
        else{
            ret = "No existen cruceros registrados en el sistema";
        }
        return ret;
    }
    
    public String listarCrucerosPorRankingDesc() {
        String ret = "";
        if (this.cruceros.cantElementos() > 0){
            Cruceros c = this.cruceros.ordenarCrucerosPorRankingDesc();
            ret = "Cruceros en " + this.nombre + "\n" + c.listarCrucerosRanking();
        }
        else{
            ret = "No existen cruceros registrados en el sistema";
        }
        return ret;
    }
}
