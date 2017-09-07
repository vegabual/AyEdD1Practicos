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
public class ejercicio1 {
    
    
    //Pre: nro es mayor o igual a 1
    //Pos: devuelve true si el numero es primo
    public boolean esPrimo(int nro){
        // Recorrer los enteros entre 2 y la raiz cuadrada del mismo, verificando
        // Si encuentro que no es primo, corto la ejecucion
        boolean primo = true;
        int i = 2;
        double raiz = Math.sqrt(nro);
        int sqrt = (int) raiz;
        if (nro != 2 && nro != 1){
            while (primo && i <= sqrt){
                if (nro % i == 0){
                    primo = false;
                }
                i++;
            }
        }
        return primo;
    }
}
