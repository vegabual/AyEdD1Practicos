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
public class ComentarioObj {
    
    int ranking;
    String coment;
    
    public ComentarioObj(int puntaje, String texto){
        this.ranking = puntaje;
        this.coment = texto;
    }
}
