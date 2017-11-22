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
public class Distancias {
    
    private int[][] matriz;
    
    public Distancias(int tam){
        matriz = new int[tam][tam];
    }
    
    public boolean cargarDistancias(int[][] cargar){
        boolean ret;
        if (cargar.length == this.matriz.length && cargar[0].length == this.matriz[0].length){
            int tam = cargar.length;
            for(int i = 0; i < tam; i++){
                for (int j = 0; j < tam; j++){
                    this.matriz[i][j] = cargar[i][j];
                }
            }
            ret = true;
        }
        else{
            ret = false;
        }
        return ret;
    }
    
    public int caminoMasCorto(int origen, int destino){
        int tam = this.matriz.length;
        int menorDistancia = Integer.MAX_VALUE;
        int mejorEscala = -1;
        for(int i = 0; i < tam; i++){
            int d = distanciaCiudades(origen, i, destino);
            if (d != 0 && d < menorDistancia){
                menorDistancia = d;
                mejorEscala = i;
            }
        }
        return mejorEscala;
    }
    
    public int distanciaCiudades(int origen, int escala, int destino){
        int dist1 = this.matriz[origen][escala];
        int dist2 = this.matriz[escala][destino];
        int ret = 0;
        if (dist1 != 0 && dist2 != 0){
            ret = this.matriz[origen][escala] + this.matriz[escala][destino];
        }
        return ret;
    }

    /**
     * @return the matriz
     */
    public int[][] getMatriz() {
        return matriz;
    }

    /**
     * @param matriz the matriz to set
     */
    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
}
