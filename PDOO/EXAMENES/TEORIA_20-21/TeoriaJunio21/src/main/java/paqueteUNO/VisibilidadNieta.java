/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteUNO;

import paqueteDOS.VisibilidadHija;

public class VisibilidadNieta extends VisibilidadHija {
    void metodo() {
        protegido();
        (new VisibilidadHija()).protegido(); //No penalizo falsos positivos aquí
        (new VisibilidadPadre()).protegido();
        (new VisibilidadPadre()).calculo();
    }    
}


