package practico4;

import org.junit.Test;
import static org.junit.Assert.*;


public class P4Ejercicio8Test {

    @Test
    public void testMcdPrimosEntreSi() {
        System.out.println("Mcd");
        int x = 2;
        int y = 3;
        P4Ejercicio8 instance = new P4Ejercicio8();
        int expResult = 1;
        int result = instance.Mcd(x, y);
        assertEquals(expResult, result);
    }

    @Test
    public void testMcdNoPrimosEntreSi() {
        System.out.println("Mcd");
        int x = 34;
        int y = 6;
        P4Ejercicio8 instance = new P4Ejercicio8();
        int expResult = 2;
        int result = instance.Mcd(x, y);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMcdXIgualY() {
        System.out.println("Mcd");
        int x = 63;
        int y = 63;
        P4Ejercicio8 instance = new P4Ejercicio8();
        int expResult = 63;
        int result = instance.Mcd(x, y);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSonPrimosEntreSiTrue() {
        System.out.println("SonPrimosEntreSi");
        int x = 5;
        int y = 23;
        P4Ejercicio8 instance = new P4Ejercicio8();
        boolean expResult = true;
        boolean result = instance.SonPrimosEntreSi(x, y);
        assertEquals(expResult, result);
    }

    @Test
    public void testSonPrimosEntreSiFalse() {
        System.out.println("SonPrimosEntreSi");
        int x = 34;
        int y = 14;
        P4Ejercicio8 instance = new P4Ejercicio8();
        boolean expResult = false;
        boolean result = instance.SonPrimosEntreSi(x, y);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSonPrimosEntreSi2True() {
        System.out.println("SonPrimosEntreSi2");
        int x = 8;
        int y = 17;
        P4Ejercicio8 instance = new P4Ejercicio8();
        boolean expResult = true;
        boolean result = instance.SonPrimosEntreSi2(x, y);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of SonPrimosEntreSi2 method, of class P4Ejercicio8.
     */
    @Test
    public void testSonPrimosEntreSi2False() {
        System.out.println("SonPrimosEntreSi2");
        int x = 34;
        int y = 17;
        P4Ejercicio8 instance = new P4Ejercicio8();
        boolean expResult = false;
        boolean result = instance.SonPrimosEntreSi2(x, y);
        assertEquals(expResult, result);
    }
    
}
