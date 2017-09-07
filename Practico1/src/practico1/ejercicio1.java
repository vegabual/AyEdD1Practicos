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
public class ejercicio1 {

    private int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Empezo el programa");
        imprimirLaSumaDeLosNumerosImparesEntre1y50();
        int suma = imprimirLaSumaDeLosNumerosImparesEntre(1, 50);
        System.out.println("La suma es:"+suma);
        System.out.println("Termino el programa");
    }

    public static void imprimirLaSumaDeLosNumerosImparesEntre1y50() {
        int acumulador=0;
        //suma de los impares entre uno y cincuenta
        for (int i = 1; i <= 50; i++) {
            if(esImpar(i)){
                acumulador+=i;
            }
        }
        System.out.println(acumulador);
    }
    public static int imprimirLaSumaDeLosNumerosImparesEntre(int desde, int hasta) {
        int acumulador=0;
        //suma de los impares entre uno y cincuenta
        for (int i = desde; i <= hasta; i++) {
            if(esImpar(i)){
                acumulador+=i;
            }
        }
        return acumulador;
    }
    
    
    private static boolean esImpar(int num){
        return !(num%2==0);
    }

}
