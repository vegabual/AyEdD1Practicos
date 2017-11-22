package obli;

import obli.Retorno.Resultado;

public class Sistema implements ISistema {

    private Ciudades ciudades;
    private Distancias distancias;
    
    //PRE: CantCiudades es mayor a 0
    //POS: Crea la estructura necesaria para representar el sistema. Si ya existe un sistema de reservas, 
    //      lo destruye previo a la creacion
    @Override
    public Retorno crearSistemaReservas(int cantCiudades) {
        
        Retorno ret = new Retorno();

        if (cantCiudades >= 0){
            if (this.ciudades != null){
                destruirSistemaReservas();
            }
            this.ciudades = new Ciudades(cantCiudades);
            this.distancias = new Distancias(cantCiudades);
            ret.resultado = Resultado.OK;
        }
        else{
            ret.resultado = Resultado.ERROR_1;
            System.out.println("La cantidad de ciudades debe ser mayor a 0");
        }

        return ret;
    }
    
    //PRE: 
    //POS: Destruye el sistema de reservas y todos sus elementos
    @Override
    public Retorno destruirSistemaReservas() {
        Retorno ret = new Retorno();
        if (this.ciudades != null){
            this.ciudades.destruirCiudades();
            this.ciudades = null;
        }
        
        ret.resultado = Resultado.OK;

        return ret;
    }

    //PRE: Ciudad no existe aun en el sistema
    //POS: Registra la ciudad "ciudad"
    @Override
    public Retorno registrarCiudad(String ciudad) {
        Retorno ret = new Retorno();

        if (this.ciudades.posicionCiudad(ciudad) != -1){
            ret.resultado = Resultado.ERROR_1;
            System.out.println("Ya existe una ciudad \"" + ciudad + "\"");
        }
        else if (this.ciudades.agregarCiudad(ciudad)){
            ret.resultado = Resultado.OK;
        }
        else{
            ret.resultado = Resultado.ERROR_2;
            System.out.println("Ya no hay lugar para ciudades");
        }
        return ret;
    }

    //PRE: Estrellas esta entre 1 y 5
    //      Capacidad es mayor a 0
    //      No existe ningun crucero "nombre"
    //      Existe la ciudad "ciudad"
    //POS: Registra el crucero de nombre "nombre" en la ciudad "ciudad" con cantidad de estrellas 
    //      "estrellas", capacidad "capacidad" y ranking 0
    @Override
    public Retorno registrarCrucero(String ciudad, String nombre, int estrellas, int capacidad) {
        Retorno ret = new Retorno();
        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (estrellas < 1 || estrellas > 5){
            ret.resultado = Resultado.ERROR_1;
            System.out.println("Estrellas es menor a 1 o mayor a 5. Estrellas = " + estrellas);
        }
        else if(capacidad < 0){
            ret.resultado = Resultado.ERROR_2;
            System.out.println("Capacidad es menor a 0. Capacidad = " + capacidad);
        }
        else if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_4;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else if(this.ciudades.listadoCiudad[posCiudad].cruceros.obtenerCruceroNombre(nombre) != null){
            ret.resultado = Resultado.ERROR_3;
            System.out.println("Ya existe el crucero \"" + nombre + "\" de la ciudad \"" + ciudad + "\"");
        }
        else{
            CruceroObj c = new CruceroObj(nombre, estrellas, capacidad);
            this.ciudades.listadoCiudad[posCiudad].cruceros.agregarInicio(c);
            ret.resultado = Resultado.OK;
        }
        return ret;
    }

    //PRE: Existe la ciudad "ciudad"
    //      Existe el crucero "crucero" de la ciudad "ciudad"
    //POS: Ingresa el servicio "servicio" al crucero "crucero"
    @Override
    public Retorno ingresarServicio(String ciudad, String crucero, String servicio) {
        Retorno ret = new Retorno();
        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_2;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            NodoCrucero c = this.ciudades.obtenerCruceroNombre(posCiudad,crucero);
            if (c == null){
                ret.resultado = Resultado.ERROR_1;
                System.out.println("No existe el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\"");
            }
            else{
                c.getDato().agregarServicio(servicio);
                ret.resultado = Resultado.OK;
            }
        }
        return ret;
    }

    //PRE: Existe el crucero "crucero" de la ciudad "ciudad"
    //      Existe el servicio "servicio" registrado en el crucero "crucero"
    //      Existe la ciudad "ciudad"
    //POS: Borra la primera occurrencia del servicio "servicio" del crucero "crucero"
    @Override
    public Retorno borrarServicio(String ciudad, String crucero, String servicio) {
        Retorno ret = new Retorno();
        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_3;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            NodoCrucero c = this.ciudades.obtenerCruceroNombre(posCiudad,crucero);
            if (c == null){
                ret.resultado = Resultado.ERROR_1;
                System.out.println("No existe el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\"");
            }
            else if (!c.getDato().existeServicio(servicio)){
                ret.resultado = Resultado.ERROR_2;
                System.out.println("No existe el servicio \"" + servicio + "\" registrado en el crucero \"" + crucero + "\"");

            }
            else{
                c.getDato().borrarServicio(servicio);
                ret.resultado = Resultado.OK;
            }
        }
        return ret;
    }

    //PRE: Existe el crucero "crucero" dentro de la ciudad "ciudad"
    //      Existe la ciudad "ciudad"
    //POS: Realiza la reserva de una habitacio en el crucero "crucero" dentro de la ciudad "ciudad".
    //      En caso de que no haya cupo de habitaciones disponibles, el cliente "cliente" quedara en 
    //      lista de espera
    @Override
    public Retorno realizarReserva(int cliente, String ciudad, String crucero) {
        Retorno ret = new Retorno();
        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_2;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            NodoCrucero c = this.ciudades.obtenerCruceroNombre(posCiudad,crucero);
            if (c == null){
                ret.resultado = Resultado.ERROR_1;
                System.out.println("No existe el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\"");
            }
            else{
                c.getDato().crearReserva(cliente);
                ret.resultado = Resultado.OK;
            }
        }

        return ret;
    }

    //PRE: Existe un crucero "crucero" en la ciudad "ciudad"
    //      El cliente tiene al menos una reserva en el crucero "crucero" de la ciudad "ciudad"
    //      Existe la ciudad "ciudad"
    //POS: Cancela la reserva en el crucero "crucero" en caso de que la cancelacion sea exitosa 
    //      y haya clientes en lista de espera, el cupo sera ocupado por el primer cliente de la lista.
    //      En caso de que el cliente "cliente" tenga mas de una reserva para el crucero "crucero" se cancelara solo la 
    //      primera reserva en orden (primero se buscara reservas de habitacion y luego en lista de espera.
    @Override
    public Retorno cancelarReserva(int cliente, String ciudad, String crucero) {
        Retorno ret = new Retorno();

        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_3;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            NodoCrucero c = this.ciudades.obtenerCruceroNombre(posCiudad,crucero);
            if (c == null){
                ret.resultado = Resultado.ERROR_1;
                System.out.println("No existe el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\"");
            }
            else if (!c.getDato().existeReserva(cliente)){
                ret.resultado = Resultado.ERROR_2;
                System.out.println("No existe reserva en el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\" para el cliente nro: " + cliente );
            }
            else{
                c.getDato().cancelarReserva(cliente);
                ret.resultado = Resultado.OK;
            }
        }
        return ret;
    }

    //PRE: Existe un crucero "crucero" en la ciudad "ciudad"
    //      El ranking es mayor a 0 y menor a 5
    //      Existe la ciudad "ciudad"
    //POS: Ingresa el comentario "comentario" al crucero "crucero". El ranking general del crucero quedara definido por el 
    //      promedio de todas sus calificaciones. Ademas, ordena los cruceros por ranking ascendente
    @Override
    public Retorno ingresarComentario(String ciudad, String crucero, String comentario, int ranking) {
        Retorno ret = new Retorno();
        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (ranking < 0 || ranking > 5){
            ret.resultado = Resultado.ERROR_1;
            System.out.println("Ranking es menor a 0 o mayor a 5. Ranking = " + ranking);
        }
        else if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_3;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            NodoCrucero c = this.ciudades.obtenerCruceroNombre(posCiudad,crucero);
            if (c == null){
                ret.resultado = Resultado.ERROR_2;
                System.out.println("No existe el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\"");
            }
            else{
                c.getDato().agregarComentario(ranking, comentario);
                this.ciudades.cambiarCrucerosAOrdenadosPorRankingAsc(posCiudad);
                ret.resultado = Resultado.OK;
            }
        }

        return ret;
    }

    //PRE: Existe un crucero "crucero" en la ciudad "ciudad"
    //      Existe la ciudad "ciudad"
    //POS: lista todos los servicios prestados por el crucero "crucero" de la ciudad "ciudad". 
    //      En caso de que no haya servicios registrados en el crucero, se imprimira:
    //      "No existen servicios registrados en el crucero <crucero><ciudad>"
    @Override
    public Retorno listarServicios(String ciudad, String crucero) {
        Retorno ret = new Retorno();

        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_2;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            NodoCrucero c = this.ciudades.obtenerCruceroNombre(posCiudad,crucero);
            if (c == null){
                ret.resultado = Resultado.ERROR_1;
                System.out.println("No existe el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\"");
            }
            else{
                ret.valorString = this.ciudades.listarServicios(posCiudad,crucero);
                ret.resultado = Resultado.OK;
            }
        }
        return ret;
    }

    //PRE: Existe la ciudad "ciudad"
    //POS: lista todos los cruceros "crucero" de la ciudad "ciudad" ordenados por nombre. 
    //      En caso de que no haya cruceros registrados en la ciudad, se imprimira:
    //      "No existen cruceros registrados en <ciudad>"
    @Override
    public Retorno listarCrucerosCiudad(String ciudad) {
        Retorno ret = new Retorno();

        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_1;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            ret.valorString = this.ciudades.listarCrucerosPorNombre(posCiudad);
            ret.resultado = Resultado.OK;
        }
        return ret;
    }

    //PRE: Existe la ciudad "ciudad"
    //POS: lista todos los cruceros "crucero" de la ciudad "ciudad" ordenados por ranking ascendente. 
    //      En caso de que no haya cruceros registrados en la ciudad, se imprimira:
    //      "No existen cruceros registrados en el sistema"
    @Override
    public Retorno listarCrucerosRankingAsc(String ciudad) {
        Retorno ret = new Retorno();

        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_1;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            ret.valorString = this.ciudades.listarCrucerosRankingAsc(posCiudad);
            ret.resultado = Resultado.OK;
        }
        return ret;
    }

    //PRE: Existe la ciudad "ciudad"
    //POS: lista todos los cruceros "crucero" de la ciudad "ciudad" ordenados por ranking descendente. 
    //      En caso de que no haya cruceros registrados en la ciudad , se imprimira:
    //      "No existen cruceros registrados en el sistema"
    @Override
    public Retorno listarCrucerosRankingDesc(String ciudad) {
        Retorno ret = new Retorno();

        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_1;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            ret.valorString = this.ciudades.listarCrucerosRankingDesc(posCiudad);
            ret.resultado = Resultado.OK;
        }
        return ret;
    }

    //PRE: 
    //POS:Lista todos los cruceros registrados en el sistemas ordenados por ranking descendente
    @Override
    public Retorno listarCrucerosRanking() {
        Retorno ret = new Retorno();
        ret.valorString = this.ciudades.listarTodosCrucerosRankingDesc();
        ret.resultado = Resultado.OK;
        return ret;
    }

    //PRE: Existe un crucero "crucero" en la ciudad "ciudad"
    //      Existe la ciudad "ciudad"
    //POS: Lista todos los comentarios ingresados para el Crucero "crucero" dentro de la ciudad "ciudad".
    //      En caso que no existan comentarios para el crucero "crucero" el sistema imprimira:
    //      "No se han agregado comentarios al crucero <crucero> <ciudad>"
    @Override
    public Retorno listarComentarios(String ciudad, String crucero) {
        Retorno ret = new Retorno();

        int posCiudad = this.ciudades.posicionCiudad(ciudad);
        if (posCiudad == -1){
            ret.resultado = Resultado.ERROR_2;
            System.out.println("No existe la ciudad \"" + ciudad + "\"");
        }
        else {
            NodoCrucero c = this.ciudades.obtenerCruceroNombre(posCiudad,crucero);
            if (c == null){
                ret.resultado = Resultado.ERROR_1;
                System.out.println("No existe el crucero \"" + crucero + "\" de la ciudad \"" + ciudad + "\"");
            }
            else{
                ret.valorString = this.ciudades.listarComentarios(posCiudad,crucero);
                ret.resultado = Resultado.OK;
            }
        }
        return ret;
    }

    //PRE: La matriz "ciudades" es del mismo tamaño que la cantidad de ciudades en sistema
    //POS: Carga las distancias en la matriz de distancias del sistema
    @Override
    public Retorno cargarDistancias(int[][] ciudades) {
        Retorno ret = new Retorno();
        if (this.distancias.cargarDistancias(ciudades)){
            ret.resultado = Resultado.OK;
        }
        else{
            ret.resultado = Resultado.ERROR_1;
            System.out.println("La matriz ingresada no es del tamaño correcto");
        }
        return ret;
    }

    //PRE: La matriz "m" es del mismo tamaño que la cantidad de ciudades en sistema
    //      Existe la ciudad "origen"
    //      Existe la ciudad "destino"
    //      Hay un camino entre "origen" y "destino" con solo una ciudad intermedia
    //POS: Carga las distancias en "m" y devuelve el camino mas corto para llegar del origen a destino 
    //      restringiendo la busqueda a caminos que solo tengan una ciudad intermedia
    @Override
    public Retorno buscarCamino(int[][] m, String origen, String destino) {
        Retorno ret = new Retorno();
        if (this.distancias.cargarDistancias(m)){
            int i = this.ciudades.posicionCiudad(origen);
            int j = this.ciudades.posicionCiudad(destino);
            if (i == -1){
                ret.resultado = Resultado.ERROR_2;
                System.out.println("No existe la ciudad \"" + origen + "\"");
            }
            else if (j == -1){
                ret.resultado = Resultado.ERROR_3;
                System.out.println("No existe la ciudad \"" + destino + "\"");
            }
            else{
                int escala = this.distancias.caminoMasCorto(i,j);
                if (escala == -1){
                    ret.resultado = Resultado.ERROR_4;
                    System.out.println("No existe la coneccion entre ciudad \"" + origen + "\" y la ciudad \"" + destino + "\" con una sola ciudad intermedia");
                }
                else{
                    ret.resultado = Resultado.OK;
                    String ciudadEscala = this.ciudades.listadoCiudad[escala].nombre;
                    ret.valorString = origen + " | " + ciudadEscala + " | " + destino;
                }
            }
        }
        else{
            ret.resultado = Resultado.ERROR_1;
            System.out.println("La matriz ingresada no es del tamaño correcto");
        }
        return ret;
    }

}
