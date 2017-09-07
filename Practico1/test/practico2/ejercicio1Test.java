/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author veronicab-ot
 */
public class ejercicio1Test {
    
    public ejercicio1Test() {
    }

    @Test
    public void testEsPrimoFalse() {
        System.out.println("esPrimo");
        int nro = 45;
        ejercicio1 instance = new ejercicio1();
        boolean expResult = false;
        boolean result = instance.esPrimo(nro);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsPrimoTrue() {
        System.out.println("esPrimo");
        int nro = 67;
        ejercicio1 instance = new ejercicio1();
        boolean expResult = true;
        boolean result = instance.esPrimo(nro);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsPrimoSiendo2() {
        System.out.println("esPrimo");
        int nro = 2;
        ejercicio1 instance = new ejercicio1();
        boolean expResult = true;
        boolean result = instance.esPrimo(nro);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsPrimoSiendo1() {
        System.out.println("esPrimo");
        int nro = 1;
        ejercicio1 instance = new ejercicio1();
        boolean expResult = true;
        boolean result = instance.esPrimo(nro);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testEsPrimoMultiploDePrimos() {
        System.out.println("esPrimo");
        int nro = 169;
        ejercicio1 instance = new ejercicio1();
        boolean expResult = false;
        boolean result = instance.esPrimo(nro);
        assertEquals(expResult, result);
    }
}
