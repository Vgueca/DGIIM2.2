/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteDOS;

import paqueteUNO.VisibilidadPadre;


public class VisibilidadHija extends VisibilidadPadre{
    void metodo() {       
        protegido();
        (new VisibilidadHija()).protegido(); 
        //(new VisibilidadPadre()).protegido();
        //(new VisibilidadPadre()).calculo();
    }
    
    
    //protected void protegido(){} //Hacría fallar (new VisibilidadHija()).protegido(); en Nieta
}
