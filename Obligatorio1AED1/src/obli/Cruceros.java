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
public class Cruceros {
    
    NodoCrucero inicio;
    NodoCrucero fin;
    
    public void Cruceros(){
        this.inicio = null;
        this.fin = null;
    }

    //Inicio
    public void setInicio(NodoCrucero i){
        inicio=i;
    }
    
    public NodoCrucero getInicio(){
        return inicio;
    }

    //Fin
    public void setFin(NodoCrucero f){
        fin=f;
    }
    
    public NodoCrucero getFin(){
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
    //POS: Agrega un nuevo crucero al principio de la lista
    public void agregarInicio(CruceroObj n){
        NodoCrucero nuevo= new NodoCrucero(n);
        nuevo.setSig(inicio);
        this.inicio=nuevo;
        if(this.fin==null){
            this.fin=nuevo;
        }
    }
    
    //PRE:
    //POS: Agrega un nuevo crucero al final de la lista
    public void agregarFinal(CruceroObj n){
        if (this.esVacia()){
            this.agregarInicio(n);
        }
        else
        {
            NodoCrucero nuevo = new NodoCrucero(n);
            fin.setSig(nuevo);
            this.fin=nuevo;
        }
    }
    
    
    //PRE:
    //POS: Agrega un nuevo crucero ordenado segun su ranking
    public void agregarOrdenado(CruceroObj n){
        //lista vacía o primer elemento es mayor o igual => agrego al ppio
        if (this.esVacia() || this.inicio.getDato().ranking >= n.ranking){
            this.agregarInicio(n);
            return;
        }
        //último elemento es menor o igual => agrego al final
        if (this.fin.getDato().ranking <= n.ranking){
            this.agregarFinal(n);
            return;
        }
        NodoCrucero aux=this.inicio;
        while (aux.getSig()!=null && aux.getSig().getDato().ranking < n.ranking)
            aux=aux.getSig();
        NodoCrucero nuevo=new NodoCrucero(n);
        nuevo.setSig(aux.getSig());
        aux.setSig(nuevo);
    }
    
    
    //PRE:
    //POS: Borra el primer Crucero
    public void borrarInicio(){
        if (!this.esVacia()){
            this.inicio.getDato().servicios.vaciar();
            this.inicio.getDato().reservas.vaciarTodo();
            this.inicio=this.inicio.getSig();
        }
    }
    
    //PRE:
    //POS: elimina todos los Cruceros de la lista
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
            NodoCrucero aux = this.inicio;
            while (aux != null){
                aux = aux.getSig();
                cont ++;
            }
        }
        return cont;
    }

    //PRE: 
    //POS: Devuelve el crucero cuyos datos coinciden con los de n
    public NodoCrucero obtenerCrucero(CruceroObj n){
        NodoCrucero aux = this.inicio;
        while (aux != null && !aux.getDato().equals(n)){
            aux = aux.getSig();
        } //encontré dato o llegué al final
        return aux;
    }
    
    //PRE: 
    //POS: Devuelve el crucero cuyo nombre es "nombre" y ciudad es "ciudad" o null si no existe
    public NodoCrucero obtenerCruceroNombre(String nombre){
        NodoCrucero aux = this.inicio;
        while (aux != null && aux.getDato().nombre != nombre){
            aux = aux.getSig();
        } //encontré dato o llegué al final
        return aux;
    }
    
    public String listarServicios(NodoCrucero c){
        return c.getDato().listarServicios();
    }
    
    public String listarComentarios(NodoCrucero c){
        return c.getDato().listarComentarios();
    }
    
    public String listarCrucerosNombre(){
        String ret = "";
        NodoCrucero aux = this.inicio;
        while(aux != null ){
            CruceroObj c = aux.getDato();
            ret += c.nombre + " " + c.estrellas + " " + c.ranking + "\n";
            aux = aux.getSig();
        }
        return ret;
    }
    
    public String listarCrucerosRanking(){
        String ret = "";
        NodoCrucero aux = this.inicio;
        while(aux != null ){
            CruceroObj c = aux.getDato();
            ret += c.nombre + " " + c.ranking + "\n";
            aux = aux.getSig();
        }
        return ret;
    }
    
    public Cruceros copiarListaCruceros(){
        Cruceros aux = new Cruceros();
        NodoCrucero c = this.inicio;
        while (c != null){
            aux.agregarFinal(c.getDato());
            c = c.getSig();
        }
        return aux;
    }
    
    public Cruceros ordenarCrucerosPorRankingDesc(){
        Cruceros aux = this.copiarListaCruceros();
        aux.inicio = aux.mergeSortRankingDesc(aux.inicio);
        aux.fin = aux.inicio;
        while (aux.fin.getSig() != null){
            aux.fin = aux.fin.getSig();
        }
        return aux;
    }

    public Cruceros ordenarCrucerosPorNombre(){
        Cruceros aux = this.copiarListaCruceros();
        aux.inicio = aux.mergeSortNombre(aux.inicio);
        aux.fin = aux.inicio;
        while (aux.fin.getSig() != null){
            aux.fin = aux.fin.getSig();
        }
        return aux;
    }
    
    public NodoCrucero sortedMergeRankingDesc(NodoCrucero c1, NodoCrucero c2) 
    {
        NodoCrucero result = null;
        if (c1 == null)
            return c2;
        if (c2 == null)
            return c1;
        
        if (c1.getDato().ranking >= c2.getDato().ranking) 
        {
            result = c1;
            result.setSig(sortedMergeRankingDesc(c1.getSig(), c2));
        } 
        else
        {
            result = c2;
            result.setSig(sortedMergeRankingDesc(c1, c2.getSig()));
        }
        return result;
 
    }
 
    public NodoCrucero mergeSortRankingDesc(NodoCrucero h) 
    {
        // Caso base: Si primer elem es null
        if (h == null || h.getSig() == null)
        {
            return h;
        }
 
        NodoCrucero middle = getMiddle(h);
        NodoCrucero nextofmiddle = middle.getSig();
 
        // setar null al siguiente del del medio
        middle.setSig(null);
 
        // Aplicar mergeSort a la lista de la izquierda
        NodoCrucero left = mergeSortRankingDesc(h);
 
        // Aplicar mergeSort a la lista de la derecha
        NodoCrucero right = mergeSortRankingDesc(nextofmiddle);
 
        // Mergear las listas de la izquierda y derecha
        NodoCrucero sortedlist = sortedMergeRankingDesc(left, right);
        return sortedlist;
    }
 
    
    public NodoCrucero sortedMergeNombre(NodoCrucero c1, NodoCrucero c2) 
    {
        NodoCrucero result = null;
        if (c1 == null)
            return c2;
        if (c2 == null)
            return c1;
        
        if (c1.getDato().nombre.compareToIgnoreCase(c2.getDato().nombre) <= 0) 
        {
            result = c1;
            result.setSig(sortedMergeNombre(c1.getSig(), c2));
        } 
        else
        {
            result = c2;
            result.setSig(sortedMergeNombre(c1, c2.getSig()));
        }
        return result;
 
    }
 
    public NodoCrucero mergeSortNombre(NodoCrucero h) 
    {
        // Caso base: Si primer elem es null
        if (h == null || h.getSig() == null)
        {
            return h;
        }
 
        NodoCrucero middle = getMiddle(h);
        NodoCrucero nextofmiddle = middle.getSig();
 
        // setar null al siguiente del del medio
        middle.setSig(null);
 
        // Aplicar mergeSort a la lista de la izquierda
        NodoCrucero left = mergeSortNombre(h);
 
        // Aplicar mergeSort a la lista de la derecha
        NodoCrucero right = mergeSortNombre(nextofmiddle);
 
        // Mergear las listas de la izquierda y derecha
        NodoCrucero sortedlist = sortedMergeNombre(left, right);
        return sortedlist;
    }
    
    public NodoCrucero getMiddle(NodoCrucero h) 
    {
        // Caso base
        if (h == null){
            return h;
        }
        NodoCrucero rapido = h.getSig();
        NodoCrucero lento = h;
         
        // Mover rapido de a 2 y lento de a 1
        // Al final, lento va a apuntar al nodo del medio
        while (rapido != null)
        {
            rapido = rapido.getSig();
            if(rapido!=null)
            {
                lento = lento.getSig();
                rapido=rapido.getSig();
            }
        }
        return lento;
    }
    
}
