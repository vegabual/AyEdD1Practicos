/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alumnoFI
 */
public class testEjercicio1 {
    
    public testEjercicio1() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testImparesEntre1y50() {
         ejercicio1ParaTest ejer = new ejercicio1ParaTest();
         int res = ejer.imprimirLaSumaDeLosNumerosImparesEntre(1, 50);
         System.out.println("el resultado fue "+res);
         assertEquals("No calculo correctamente",625, res);
     }
}
