/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteUNO;


interface Interfaz1 {
    void accion1();
    default void accion2(){
        System.out.println("Acción 2 en Interfaz1");
    }
}

abstract class Padre {
    public void accionPadre1() { 
        System.out.println("Padre1");
        calcula();
    }
    public void calcula() { System.out.println("Calcula Padre");}
} 

class Hija extends Padre implements Interfaz1 {
   @Override
   public void accion1() {System.out.println("Acción 1 en Hija");}
}

class Hija2 extends Padre {
    @Override
    public void calcula() { System.out.println("Calcula Hija2");}    
}



public class Principal {


    public static void main(String[] args) {
        
//        Interfaz1 i1=new Hija();
//        i1.accion1();
//        i1.accion2();
        //i1.calcula();
        //((Hija) i1).calcula();
        
        Padre p1=new Hija();
        //Hija h1=p1;
        Hija h1=(Hija) p1;
        
        Padre p2=new Hija2();
        p2.accionPadre1();
        ((Hija2) p2).accionPadre1();
        
        Padre p3 = new Hija2();
        Hija h2 = (Hija) p3;
        
        h2.accion1();
        //((Hija) ((Padre) new Hija2())).accion1(); //Una Hija2 no es una Hija. Clases hermanas. Error en tiempo de ejecución aunque no se llame a métodos
    
    }
    
}
