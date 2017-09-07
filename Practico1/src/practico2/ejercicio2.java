/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico2;

/**
 *
 * @author veronicab-ot
 */
public class ejercicio2 {
    public int encontrarPrimo(int n){
        boolean found = false;
        int ret = n + 1;
        ejercicio1 instance = new ejercicio1();
        while (!found){
            if (instance.esPrimo(ret)){
                found = true;
                ret = n;
            }
            else{
                ret++;
            }
        }
        return ret;
    }
}
