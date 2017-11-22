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
public class Ciudades {
    private static int ultimaCiudad;
    CiudadObj[] listadoCiudad;
    
    Ciudades(int cant){
        ultimaCiudad = 0;
        this.listadoCiudad = new CiudadObj[cant]; 
    }
    
    //POS: Retorna enter indicando la posicion de la ciudad "ciudad". De no existir, retorna -1
    public int posicionCiudad(String ciudad){
        int ret = -1;
        int i = 0;
        boolean existe = false;
        while(!existe && i < this.listadoCiudad.length){
            if (this.listadoCiudad[i] != null && this.listadoCiudad[i].equalsNombre(ciudad)){
                ret = i;
            }
            i++;
        }
        return ret;
    }
    
    //PRE: No existe una ciudad "ciudad"
    //POS: Retorna booleano indicando si se creo la ciudad o no
    public boolean agregarCiudad(String ciudad){
        boolean creado;
        if (ultimaCiudad == listadoCiudad.length){
            creado = false;
        }
        else{
            CiudadObj nueva = new CiudadObj(ciudad);
            this.listadoCiudad[ultimaCiudad] = nueva;
            ultimaCiudad++;
            creado = true;
        }
        return creado;
    }
    
    public void destruirCiudades(){
        for(int i=0; i < this.listadoCiudad.length; i++){
            if (this.listadoCiudad[i] != null){
                this.listadoCiudad[i].cruceros.vaciar();
            }
        }
    }
    
    public NodoCrucero obtenerCruceroNombre(int pos, String crucero){
        return this.listadoCiudad[pos].cruceros.obtenerCruceroNombre(crucero);
    }
    
    public void cambiarCrucerosAOrdenadosPorRankingAsc(int pos){
        this.listadoCiudad[pos].cruceros = this.listadoCiudad[pos].ordenarCrucerosPorRankingAsc();
    }
    
    public String listarServicios(int posCiudad, String crucero){
        return this.listadoCiudad[posCiudad].listarServicios(crucero);
    }
    
    public String listarComentarios(int posCiudad, String crucero){
        return this.listadoCiudad[posCiudad].listarComentarios(crucero);
    }
    
    public String listarCrucerosPorNombre(int posCiudad){
        return this.listadoCiudad[posCiudad].listarCrucerosPorNombre();
    }

    String listarCrucerosRankingAsc(int posCiudad) {
        return this.listadoCiudad[posCiudad].listarCrucerosPorRankingAsc();
    }

    String listarCrucerosRankingDesc(int posCiudad) {
        return this.listadoCiudad[posCiudad].listarCrucerosPorRankingDesc();
    }
    
    String listarTodosCrucerosRankingDesc() {
        Cruceros c = new Cruceros();
        String ret = "";
        for(int i = 0; i < this.listadoCiudad.length; i++)
        {
            if (this.listadoCiudad[i] != null){
                NodoCrucero aux = this.listadoCiudad[i].cruceros.inicio;
                while (aux != null){
                    c.agregarInicio(aux.getDato());
                    aux = aux.getSig();
                }
            }
        }
        if (c.cantElementos() != 0){
            ret = c.ordenarCrucerosPorRankingDesc().listarCrucerosRanking();
        }
        else{
            ret = "No hay registros de cruceros en el sistema";
        }
        return ret;
    }
}
