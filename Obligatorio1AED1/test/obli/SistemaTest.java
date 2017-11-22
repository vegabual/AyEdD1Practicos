/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author veronicab-ot
 */
public class SistemaTest {
    
	private Sistema sis;
	private Retorno r;
	
	@Before
	public void setUp() throws Exception {
            sis = new Sistema();
	}

	@Test
	public void testCrearSistemaReservasMenorQueCero() {
            assertEquals(Retorno.Resultado.ERROR_1, sis.crearSistemaReservas(-15).resultado);
	}
        
	@Test
	public void testCrearSistemaReservasOkIgualACero() {
            assertEquals(Retorno.Resultado.OK, sis.crearSistemaReservas(0).resultado);
	}

	@Test
	public void testCrearSistemaReservasOkMayorACero() {
            assertEquals(Retorno.Resultado.OK, sis.crearSistemaReservas(6).resultado);
	}
        
	@Test
	public void testCrearSistemaReservasOkYaExiste() {
            sis.crearSistemaReservas(3);
            assertEquals(Retorno.Resultado.OK, sis.crearSistemaReservas(6).resultado);
	}
        
	@Test
	public void testDestruirSistemaReservasMenosQueCeroCiudades() {
            sis = new Sistema();
            sis.crearSistemaReservas(-5);
            assertEquals(Retorno.Resultado.OK, sis.destruirSistemaReservas().resultado);  
	}
        
	@Test
	public void testDestruirSistemaReservasOk() {
            sis = new Sistema();
            sis.crearSistemaReservas(5);
            assertEquals(Retorno.Resultado.OK, sis.destruirSistemaReservas().resultado);  
	}

	@Test
	public void testRegistrarCiudadOk() {
            sis.crearSistemaReservas(4);
            assertEquals(Retorno.Resultado.OK, sis.registrarCiudad("Seattle").resultado);
	}
        
        @Test
	public void testRegistrarCiudadNoQuedaLugar() {
            sis.crearSistemaReservas(2);

            assertEquals(Retorno.Resultado.OK, sis.registrarCiudad("Valparaiso").resultado);
            assertEquals(Retorno.Resultado.OK, sis.registrarCiudad("Montevideo").resultado);
            assertEquals(Retorno.Resultado.ERROR_2, sis.registrarCiudad("Paris").resultado);
	}
        
	@Test
	public void testRegistrarCiudadExistente() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.OK, sis.registrarCiudad("Valparaiso").resultado);
            assertEquals(Retorno.Resultado.ERROR_1, sis.registrarCiudad("Valparaiso").resultado);
	}
        
	@Test
	public void testRegistrarCruceroMenosEstrellas() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");

            assertEquals(Retorno.Resultado.ERROR_1, sis.registrarCrucero("Montevideo", "Donkey Kong", 0, 100).resultado);
	}

	@Test
	public void testRegistrarCruceroMasEstrellas() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");

            assertEquals(Retorno.Resultado.ERROR_1, sis.registrarCrucero("Montevideo", "Donkey Kong", 6, 100).resultado);
	}
        
	@Test
	public void testRegistrarCruceroMenosCapacidad() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Paris");

            assertEquals(Retorno.Resultado.ERROR_2, sis.registrarCrucero("Paris", "Titanic", 5, -1).resultado);
	}
        
	@Test
	public void testRegistrarCruceroNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Paris");

            assertEquals(Retorno.Resultado.ERROR_4, sis.registrarCrucero("Maldonado", "Capitan Miranda", 5, 100).resultado);
	}
        
	@Test
	public void testRegistrarCruceroOk() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Auckland");

            assertEquals(Retorno.Resultado.OK, sis.registrarCrucero("Auckland", "Libertad", 5, 100).resultado);
	}
        
	@Test
	public void testRegistrarCruceroExistente() {
		sis.crearSistemaReservas(4);

            sis.registrarCiudad("Cuenca ");
            sis.registrarCrucero("Cuenca ", "Jose", 5, 100);

            assertEquals(Retorno.Resultado.ERROR_3, sis.registrarCrucero("Cuenca ", "Jose", 2, 120).resultado);
	}
        
	@Test
	public void testIngresarServicioNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_2, sis.ingresarServicio("Tarariras", "Crucerito", "Casino").resultado);
	}
        
	@Test
	public void testIngresarServicioNoExisteCrucero() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");
            assertEquals(Retorno.Resultado.ERROR_1, sis.ingresarServicio("Montevideo", "Felipe II", "Pesca").resultado);
	}
        
	@Test
	public void testIngresarServicioOk() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Punta del este");
            sis.registrarCrucero("Punta del este", "Francisco Papa", 5, 100);

            assertEquals(Retorno.Resultado.OK, sis.ingresarServicio("Punta del este", "Francisco Papa", "Teatro").resultado);
	}
        
	@Test
	public void testIngresarServicioRepetido() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Punta del este");
            sis.registrarCrucero("Punta del este", "Francisco Papa", 5, 100);
            sis.ingresarServicio("Punta del este", "Francisco Papa", "Teatro");

            assertEquals(Retorno.Resultado.OK, sis.ingresarServicio("Punta del este", "Francisco Papa", "Teatro").resultado);
	}

	@Test
	public void testBorrarServicioNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_3, sis.borrarServicio("Pando", "German", "Origami").resultado);
	}
        
	@Test
	public void testBorrarServicioNoExisteCrucero() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Madrid");
            assertEquals(Retorno.Resultado.ERROR_1, sis.borrarServicio("Madrid", "Sonrisa", "Origami").resultado);
	}

	@Test
	public void testBorrarServicioNoExisteServicio() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Madrid");
            sis.registrarCrucero("Madrid", "Sonrisa", 5, 100);
            assertEquals(Retorno.Resultado.ERROR_2, sis.borrarServicio("Madrid", "Sonrisa", "Origami").resultado);
	}
        
	@Test
	public void testBorrarServicioOk() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Madrid");
            sis.registrarCrucero("Madrid", "Crucerito", 5, 100);
            sis.ingresarServicio("Madrid", "Crucerito", "Origami");
            assertEquals(Retorno.Resultado.OK, sis.borrarServicio("Madrid", "Crucerito", "Origami").resultado);
	}
        
	@Test
	public void testRealizarReservaNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_2, sis.realizarReserva(1,"Pando", "Crucerito").resultado);
	}

	@Test
	public void testRealizarReservaNoExisteCrucero() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Rocha");
            assertEquals(Retorno.Resultado.ERROR_1, sis.realizarReserva(1,"Rocha", "Rocanova").resultado);
	}

	@Test
	public void testRealizarReservaOk() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Buenos Aires");
            sis.registrarCrucero("Buenos Aires", "Libre", 5, 2);
            assertEquals(Retorno.Resultado.OK, sis.realizarReserva(1,"Buenos Aires", "Libre").resultado);
	}
        
	@Test
	public void testRealizarReservaEspera() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Valparaiso");
            sis.registrarCrucero("Valparaiso", "Crucero", 5, 2);
            assertEquals(Retorno.Resultado.OK, sis.realizarReserva(1,"Valparaiso", "Crucero").resultado);
            assertEquals(Retorno.Resultado.OK, sis.realizarReserva(2,"Valparaiso", "Crucero").resultado);
            assertEquals(Retorno.Resultado.OK, sis.realizarReserva(3,"Valparaiso", "Crucero").resultado);
	}
        
	@Test
	public void testCancelarReservaNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_3, sis.cancelarReserva(1,"Pando", "Cru1").resultado);
	}
        
	@Test
	public void testCancelarReservaNoExisteCrucero() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Buenos aires");

            assertEquals(Retorno.Resultado.ERROR_1, sis.cancelarReserva(1,"Buenos Aires", "MSC Fantasia").resultado);
	}
        
	@Test
	public void testCancelarReservaNoExisteReserva() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Londres");
            sis.registrarCrucero("Londres", "MSC Opera", 5, 2);
            assertEquals(Retorno.Resultado.ERROR_2, sis.cancelarReserva(1,"Londres", "MSC Opera").resultado);
	}
        
	@Test
	public void testCancelarReservaOk() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Londres");
            sis.registrarCrucero("Londres", "MSC Opera", 5, 2);
            sis.realizarReserva(1,"Londres", "MSC Opera");
            assertEquals(Retorno.Resultado.OK, sis.cancelarReserva(1,"Londres", "MSC Opera").resultado);
	}
        
	@Test
	public void testCancelarReservaYaCancelada() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Londres");
            sis.registrarCrucero("Londres", "MSC Opera", 5, 2);
            sis.realizarReserva(1,"Londres", "Cru1");
            sis.cancelarReserva(1,"Londres", "MSC Opera");

            assertEquals(Retorno.Resultado.ERROR_2, sis.cancelarReserva(1,"Londres", "MSC Opera").resultado);
	}

	@Test
	public void testIngresarComentarioMenosPuntos() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_1, sis.ingresarComentario("Tarariras", "MSC Fantasia", "Espantoso!",-1).resultado);
	}

	@Test
	public void testIngresarComentarioMasPuntos() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_1, sis.ingresarComentario("Tarariras", "MSC Fantasia", "Espantoso!",6).resultado);
	}

	@Test
	public void testIngresarComentarioNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_3, sis.ingresarComentario("Montevideo", "MSC Fantasia", "Espantoso!",1).resultado);
	}

	@Test
	public void testIngresarComentarioNoExisteCrucero() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");

            assertEquals(Retorno.Resultado.ERROR_2, sis.ingresarComentario("Montevideo", "MSC Fantasia", "Espantoso!",1).resultado);
	}
        
	@Test
	public void testIngresarComentarioOk() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");
            sis.registrarCrucero("Montevideo", "MSC Fantasia", 5, 2);

            assertEquals(Retorno.Resultado.OK, sis.ingresarComentario("Montevideo", "MSC Fantasia", "Espantoso!",1).resultado);
	}

	@Test
	public void testListarServiciosNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_2, sis.listarServicios("Tarariras", "MSC Opera").resultado);
	}

	@Test
	public void testListarServiciosNoExisteCrucero() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");

            assertEquals(Retorno.Resultado.ERROR_1, sis.listarServicios("Montevideo", "MSC Opera").resultado);
	}

	@Test
	public void testListarServiciosOk() {
            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");
            sis.registrarCiudad("Valparaiso");
            sis.registrarCrucero("Montevideo", "MSC Opera", 2, 22);
            sis.registrarCrucero("Montevideo", "MSC Fantasia", 5, 2);
            sis.registrarCrucero("Valparaiso", "MSC Fantasia", 3, 4);
            sis.ingresarServicio("Montevideo", "MSC Opera", "Malabares");
            sis.ingresarServicio("Montevideo", "MSC Opera", "Orquesta");
            sis.ingresarServicio("Montevideo", "MSC Opera", "Mago");
            sis.ingresarServicio("Montevideo", "MSC Opera", "Animador");
            sis.ingresarServicio("Montevideo", "MSC Opera", "Casino");
            sis.ingresarServicio("Montevideo", "MSC Fantasia", "Casino");

            r = sis.listarServicios("Montevideo", "MSC Opera");
            assertEquals(Retorno.Resultado.OK, r.resultado);
            assertTrue(r.valorString.indexOf("Malabares") < r.valorString.indexOf("Orquesta"));
            assertTrue(r.valorString.indexOf("Orquesta") < r.valorString.indexOf("Mago"));
            assertTrue(r.valorString.indexOf("Mago") < r.valorString.indexOf("Animador"));
            assertTrue(r.valorString.indexOf("Animador") < r.valorString.indexOf("Casino"));
	}

	@Test
	public void testListarCrucerosCiudadNoExisteCiudad() {
		
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_1, sis.listarCrucerosCiudad("Pando").resultado);
	}
        
	@Test
	public void testListarCrucerosCiudadNoHayCruceros() {
		
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");

            r = sis.listarCrucerosCiudad("Montevideo");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertEquals("No existen cruceros registrados en Montevideo", strR);
	}

	@Test
	public void testListarCrucerosCiudadOk() {

            sis.crearSistemaReservas(4);

            sis.registrarCiudad("Montevideo");
            sis.registrarCrucero("Montevideo", "Cru1", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru1", "Espantoso!",1);
            sis.registrarCrucero("Montevideo", "Cru4", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru4", "Maomeno!",3);
            sis.registrarCrucero("Montevideo", "Cru2", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru2", "Biennn!",4);
            sis.ingresarComentario("Montevideo", "Cru2", "Espantoso!",2);
            sis.registrarCrucero("Montevideo", "Cru3", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru3", "Esselllente!!",5);

            r = sis.listarCrucerosCiudad("Montevideo");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertTrue(strR.indexOf("Montevideo")>-1);
            assertTrue(strR.indexOf("Cru1")<strR.indexOf("5",strR.indexOf("Cru1")) && 
                            strR.indexOf("5",strR.indexOf("Cru1"))<strR.indexOf("1",strR.indexOf("5",strR.indexOf("Cru1"))) &&
                            strR.indexOf("1",strR.indexOf("5",strR.indexOf("Cru1"))) < strR.indexOf("Cru2"));
            assertTrue(strR.indexOf("Cru2")<strR.indexOf("5",strR.indexOf("Cru2")) && 
                            strR.indexOf("5",strR.indexOf("Cru2"))<strR.indexOf("3",strR.indexOf("5",strR.indexOf("Cru2"))) &&
                            strR.indexOf("3",strR.indexOf("5",strR.indexOf("Cru2"))) < strR.indexOf("Cru3"));
            assertTrue(strR.indexOf("Cru3")<strR.indexOf("5",strR.indexOf("Cru3")) && 
                            strR.indexOf("5",strR.indexOf("Cru3"))<strR.indexOf("5",strR.indexOf("5",strR.indexOf("Cru3"))+1) &&
                            strR.indexOf("5",strR.indexOf("5",strR.indexOf("Cru3"))+1) < strR.indexOf("Cru4"));
            assertTrue(strR.indexOf("Cru4")<strR.indexOf("5",strR.indexOf("Cru4")) && 
                            strR.indexOf("5",strR.indexOf("Cru4"))<strR.indexOf("3",strR.indexOf("5",strR.indexOf("Cru4"))));

	}
        
	@Test
	public void testListarCrucerosRankingAscNoExisteCiudad() {
            sis.crearSistemaReservas(4);

            assertEquals(Retorno.Resultado.ERROR_1, sis.listarCrucerosRankingAsc("Pando").resultado);
	}

	@Test
	public void testListarCrucerosRankingAscNoHayCruceros() {
            sis.crearSistemaReservas(4);
            assertEquals(Retorno.Resultado.ERROR_1, sis.listarCrucerosRankingAsc("Tarariras").resultado);
            sis.registrarCiudad("Montevideo");

            r = sis.listarCrucerosRankingAsc("Montevideo");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertEquals("No existen cruceros registrados en el sistema", strR);
	}
        
	@Test
	public void testListarCrucerosRankingAscOk() {
            sis.crearSistemaReservas(4);
            assertEquals(Retorno.Resultado.ERROR_1, sis.listarCrucerosRankingAsc("Tarariras").resultado);
            sis.registrarCiudad("Montevideo");
            sis.registrarCrucero("Montevideo", "Cru2", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru2", "Biennn!",4);
            sis.registrarCrucero("Montevideo", "Cru3", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru3", "Exelente!",5);
            sis.registrarCrucero("Montevideo", "Cru1", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru1", "Espantoso!",1);
            sis.registrarCrucero("Montevideo", "Cru5", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru5", "Un asco",1);
            sis.ingresarComentario("Montevideo", "Cru5", "Meh!!",3);
            sis.registrarCrucero("Montevideo", "Cru4", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru4", "Maomeno!",3);

            r = sis.listarCrucerosRankingAsc("Montevideo");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertTrue(strR.indexOf("Montevideo")>-1);
            assertTrue(strR.indexOf("Cru1")<strR.indexOf("1",strR.indexOf("Cru1")) && 
                            strR.indexOf("1",strR.indexOf("Cru1"))<strR.indexOf("Cru4"));
            assertTrue(strR.indexOf("Cru4")<strR.indexOf("3",strR.indexOf("Cru4")) && 
                            strR.indexOf("3",strR.indexOf("Cru4"))<strR.indexOf("Cru2"));
            assertTrue(strR.indexOf("Cru2")<strR.indexOf("4",strR.indexOf("Cru2")) && 
                            strR.indexOf("4",strR.indexOf("Cru2"))<strR.indexOf("Cru3"));
            assertTrue(strR.indexOf("Cru5")<strR.indexOf("2",strR.indexOf("Cru5")) && 
                            strR.indexOf("2",strR.indexOf("Cru5"))<strR.indexOf("Cru4"));
            assertTrue(strR.indexOf("Cru3")<strR.indexOf("5",strR.indexOf("Cru3")));
	}
        
	@Test
	public void testListarCrucerosRankingDescNoExisteCiudad() {
            sis.crearSistemaReservas(4);
            assertEquals(Retorno.Resultado.ERROR_1, sis.listarCrucerosRankingAsc("Tarariras").resultado);
        }

	@Test
	public void testListarCrucerosRankingDescNoHayCruceros() {
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");

            r = sis.listarCrucerosRankingDesc("Montevideo");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertEquals("No existen cruceros registrados en el sistema", strR);
	}

	@Test
	public void testListarCrucerosRankingDescOk() {
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");
            sis.registrarCrucero("Montevideo", "Cru2", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru2", "Biennn!",4);
            sis.registrarCrucero("Montevideo", "Cru1", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru1", "Espantoso!",1);
            sis.registrarCrucero("Montevideo", "Cru3", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru3", "Esselllente!!",5);
            sis.registrarCrucero("Montevideo", "Cru5", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru5", "Un asco",1);
            sis.ingresarComentario("Montevideo", "Cru5", "Meh!!",3);
            sis.registrarCrucero("Montevideo", "Cru4", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru4", "Maomeno!",3);

            r = sis.listarCrucerosRankingDesc("Montevideo");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertTrue(strR.indexOf("Montevideo")>-1);
            assertTrue(strR.indexOf("Cru3")<strR.indexOf("5",strR.indexOf("Cru3")) && 
                            strR.indexOf("5",strR.indexOf("Cru3"))<strR.indexOf("Cru2"));
            assertTrue(strR.indexOf("Cru2")<strR.indexOf("4",strR.indexOf("Cru2")) && 
                            strR.indexOf("4",strR.indexOf("Cru2"))<strR.indexOf("Cru4"));
            assertTrue(strR.indexOf("Cru4")<strR.indexOf("3",strR.indexOf("Cru4")) && 
                            strR.indexOf("3",strR.indexOf("Cru4"))<strR.indexOf("Cru1"));
            assertTrue(strR.indexOf("Cru5")<strR.indexOf("2",strR.indexOf("Cru5")) && 
                            strR.indexOf("2",strR.indexOf("Cru5"))<strR.indexOf("Cru1"));
            assertTrue(strR.indexOf("Cru1")<strR.indexOf("1",strR.indexOf("Cru1")));
	}

	@Test
	public void testListarCrucerosRankingNoHayCruceros() {
	
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");
            sis.registrarCiudad("Melo");
            sis.registrarCiudad("Trinidad");

            r = sis.listarCrucerosRanking();
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertEquals("No hay registros de cruceros en el sistema", strR);
	}
        
	@Test
	public void testListarCrucerosRankingOk() {
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");
            sis.registrarCiudad("Melo");
            sis.registrarCiudad("Trinidad");
            sis.registrarCrucero("Montevideo", "Cru2", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru2", "Biennn!",4);
            sis.registrarCrucero("Melo", "Cru1", 5, 2);
            sis.ingresarComentario("Melo", "Cru1", "Espantoso!",1);
            sis.registrarCrucero("Trinidad", "Cru3", 5, 2);
            sis.ingresarComentario("Trinidad", "Cru3", "Esselllente!!",5);
            sis.registrarCrucero("Melo", "Cru4", 5, 2); 
            sis.ingresarComentario("Melo", "Cru4", "Maomeno!",3);
            sis.registrarCrucero("Montevideo", "Cru5", 5, 2); 
            sis.ingresarComentario("Montevideo", "Cru5", "Horrible!",1);
            sis.ingresarComentario("Montevideo", "Cru5", "Meh",3);

            r = sis.listarCrucerosRanking();
            assertEquals(Retorno.Resultado.OK, r.resultado);

            String strR = r.valorString;
            assertTrue(strR.indexOf("Cru3")<strR.indexOf("5",strR.indexOf("Cru3")) && 
                            strR.indexOf("5",strR.indexOf("Cru3"))<strR.indexOf("Cru2"));
            assertTrue(strR.indexOf("Cru2")<strR.indexOf("4",strR.indexOf("Cru2")) && 
                            strR.indexOf("4",strR.indexOf("Cru2"))<strR.indexOf("Cru4"));
            assertTrue(strR.indexOf("Cru4")<strR.indexOf("3",strR.indexOf("Cru4")) && 
                            strR.indexOf("3",strR.indexOf("Cru4"))<strR.indexOf("Cru1"));
            assertTrue(strR.indexOf("Cru5")<strR.indexOf("2",strR.indexOf("Cru5")) && 
                            strR.indexOf("2",strR.indexOf("Cru5"))<strR.indexOf("Cru1"));
            assertTrue(strR.indexOf("Cru1")<strR.indexOf("1",strR.indexOf("Cru1")));
	}

	@Test
	public void testListarComentariosNoExisteCiudad() {
            sis.crearSistemaReservas(4);
            assertEquals(Retorno.Resultado.ERROR_2, sis.listarComentarios("Montevideo", "Cru2").resultado);
	}
        
	@Test
	public void testListarComentariosNoExisteCrucero() {
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");
            assertEquals(Retorno.Resultado.ERROR_1, sis.listarComentarios("Montevideo", "Cru2").resultado);
	}
        
	@Test
	public void testListarComentarios() {
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");
            sis.registrarCrucero("Montevideo", "Cru2", 5, 2);
            sis.ingresarComentario("Montevideo", "Cru2", "Maomeno!",3);
            sis.ingresarComentario("Montevideo", "Cru2", "Esselllente!!",5);
            sis.ingresarComentario("Montevideo", "Cru2", "Genial!!",5);
            sis.ingresarComentario("Montevideo", "Cru2", "Espantoso!",1);
            sis.ingresarComentario("Montevideo", "Cru2", "Biennn!",4);
            sis.ingresarComentario("Montevideo", "Cru2", "Se pasa lindo!",4);

            r = sis.listarComentarios("Montevideo", "Cru2");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            System.out.println(r.valorString);

            String strR = r.valorString;
            assertTrue(strR.indexOf("Biennn")<strR.indexOf("Espantoso"));
            assertTrue(strR.indexOf("Espantoso")<strR.indexOf("Esselllente"));
            assertTrue(strR.indexOf("Esselllente")<strR.indexOf("Maomeno"));
	}
	@Test
	public void testListarComentariosNoHayComentarios() {
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");
            sis.registrarCrucero("Montevideo", "Cru2", 5, 2);

            r = sis.listarComentarios("Montevideo", "Cru2");
            assertEquals(Retorno.Resultado.OK, r.resultado);

            System.out.println(r.valorString);

            String strR = r.valorString;
            assertEquals("No se han agregado comentarios al crucero Cru2 Montevideo", strR);
	}
        
	@Test
	public void testCargarDistanciasOk() {
            sis.crearSistemaReservas(6);
            sis.registrarCiudad("Montevideo");
            sis.registrarCiudad("Santiago");
            sis.registrarCiudad("Lima");
            sis.registrarCiudad("San Pablo");
            sis.registrarCiudad("Panama");
            sis.registrarCiudad("New York");

            assertEquals(Retorno.Resultado.OK,sis.cargarDistancias(new int[][] {
                    {0,10,25,15,30,0},
                    {10,0,20,0,0,0},
                    {25,20,0,0,0,40},
                    {15,0,0,0,0,45},
                    {30,0,0,0,0,25},
                    {0,0,40,45,25,0}
            }).resultado);
	}
        
	@Test
	public void testCargarDistanciasArrayDistintoTam() {
            sis.crearSistemaReservas(4);
            sis.registrarCiudad("Montevideo");
            sis.registrarCiudad("Santiago");
            sis.registrarCiudad("Lima");
            sis.registrarCiudad("San Pablo");

            assertEquals(Retorno.Resultado.ERROR_1,sis.cargarDistancias(new int[][] {
                    {0,10,25,15,30,0},
                    {10,0,20,0,0,0},
                    {25,20,0,0,0,40},
                    {15,0,0,0,0,45},
                    {30,0,0,0,0,25},
                    {0,0,40,45,25,0}
            }).resultado);
	}

	@Test
	public void testBuscarCaminoDistintoTam() {
		
		sis.crearSistemaReservas(5);
		sis.registrarCiudad("Montevideo");
		sis.registrarCiudad("Santiago");
		sis.registrarCiudad("Lima");
		sis.registrarCiudad("San Pablo");
		sis.registrarCiudad("Panama");
		
		int[][] mat = new int[][] {
			{0,10,25,15,30,0},
			{10,0,20,0,0,0},
			{25,20,0,0,0,40},
			{15,0,0,0,0,45},
			{30,0,0,0,0,25},
			{0,0,40,45,25,0}
		};
		
                r = sis.buscarCamino(mat,"Montevideo","New York");
		assertEquals(Retorno.Resultado.ERROR_1,r.resultado);
	}
    
	@Test
	public void testBuscarCaminoNoExisteCamino() {
		
		sis.crearSistemaReservas(6);
		sis.registrarCiudad("Montevideo");
		sis.registrarCiudad("Santiago");
		sis.registrarCiudad("Lima");
		sis.registrarCiudad("San Pablo");
		sis.registrarCiudad("Panama");
		sis.registrarCiudad("New York");
		
		int[][] mat = new int[][] {
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,2},
			{0,0,0,0,0,0}
		};
		
                r = sis.buscarCamino(mat,"Montevideo","New York");
		assertEquals(Retorno.Resultado.ERROR_4,r.resultado);
	}
    
	@Test
	public void testBuscarCaminoNoExisteOrigen() {
		
		sis.crearSistemaReservas(6);
		sis.registrarCiudad("Montevideo");
		sis.registrarCiudad("Santiago");
		sis.registrarCiudad("Lima");
		sis.registrarCiudad("San Pablo");
		sis.registrarCiudad("Panama");
		sis.registrarCiudad("New York");
		
		int[][] mat = new int[][] {
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,2},
			{0,0,0,0,0,0}
		};
		
                r = sis.buscarCamino(mat,"Pando","New York");
		assertEquals(Retorno.Resultado.ERROR_2,r.resultado);
	}
        
	@Test
	public void testBuscarCaminoNoExisteDestino() {
		
		sis.crearSistemaReservas(6);
		sis.registrarCiudad("Montevideo");
		sis.registrarCiudad("Santiago");
		sis.registrarCiudad("Lima");
		sis.registrarCiudad("San Pablo");
		sis.registrarCiudad("Panama");
		sis.registrarCiudad("New York");
		
		int[][] mat = new int[][] {
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,2},
			{0,0,0,0,0,0}
		};
		
                r = sis.buscarCamino(mat,"Montevideo","Guadalajara");
		assertEquals(Retorno.Resultado.ERROR_3,r.resultado);
	}
        
	@Test
	public void testBuscarCaminoOk() {
		
		sis.crearSistemaReservas(6);
		sis.registrarCiudad("Montevideo");
		sis.registrarCiudad("Santiago");
		sis.registrarCiudad("Lima");
		sis.registrarCiudad("San Pablo");
		sis.registrarCiudad("Panama");
		sis.registrarCiudad("New York");
		
		int[][] mat = new int[][] {
			{0,10,25,15,30,0},
			{10,0,20,0,0,0},
			{25,20,0,0,0,40},
			{15,0,0,0,0,45},
			{30,0,0,0,0,25},
			{0,0,40,45,25,0}
		};
		
                r = sis.buscarCamino(mat,"Montevideo","New York");
		assertEquals(Retorno.Resultado.OK,r.resultado);
		
		String strR = r.valorString;
		assertTrue(strR.indexOf("Montevideo")<strR.indexOf("Panama"));
		assertTrue(strR.indexOf("Panama")<strR.indexOf("New York"));
	}
}
