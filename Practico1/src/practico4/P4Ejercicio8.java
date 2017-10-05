package practico4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author veronicab-ot
 */
public class P4Ejercicio8 {
    
    public int Mcd(int x, int y){
        int ret = 0;
        if (y == 0){
            ret = x;
        }
        else{
            ret = Mcd(y, x % y);
        }
        return ret;
    }
    
    public boolean SonPrimosEntreSi(int x, int y) {
        return (Mcd(x,y) == 1);
    }
    
    
    public boolean SonPrimosEntreSi2(int x, int y) {
        boolean ret;
        if (y == 0){
            ret = x == 1;
        }
        else{
            ret = SonPrimosEntreSi2(y, x % y);
        }
        return ret;    
    }
}
