/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico1;

/**
 *
 * @author alumnoFI
 */
public class ejercicio1ParaTest {
 
    public int imprimirLaSumaDeLosNumerosImparesEntre(int desde, int hasta) {
        int acumulador=0;
        //suma de los impares entre uno y cincuenta
        for (int i = desde; i <= hasta; i++) {
            if(esImpar(i)){
                acumulador+=i;
            }
        }
        return acumulador;
    }
    
    private boolean esImpar(int num){
        return !(num%2==0);
    }
}
