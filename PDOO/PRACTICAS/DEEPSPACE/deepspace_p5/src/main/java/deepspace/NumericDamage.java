/**
 * @file NumericDamage.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representa el daño producido a una estación espacial por una nave enemiga cuando
 * se pierde un combate. Es una clase Hija de Damage, por lo que además de heredar el atributo
 * de potenciadores de escudo a perder, se implementa la cantidad de armas a perder con un contador.
 */ 
public class NumericDamage extends Damage{
    private int nWeapons;
    
    /**
     * @brief Constructor con parametro
     * @param w : Numero de weapons
     * @param s : Numero de escudos
     */
    NumericDamage(int w, int s){
        super(s);
        nWeapons = w;
    }
    
    /**
     * @brief Constructor de copia
     * @param nd : Otra instancia de NumericDamage
     */
    NumericDamage(NumericDamage nd){
        super(nd);
        nWeapons = nd.nWeapons;
    }
    
    /**
     * @brief Devuelve el numero de armas
     * @return nWeapons
     */
    public int getNWeapons(){
        return nWeapons;
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (NumericDamage)
     * @return Objeto UI NumericDamage
     */
    @Override
    public NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }
    
    /**
     * @brief Devuelve una versión ajustada del objeto a las colecciones de armas y potenciadores de escudos suministradas como parámetro.
     * @param w : Coleccion de armas
     * @param s : Coleccion de potenciadores de escudo
     * @return Nueva version NumericDamage
     */
    @Override
    public NumericDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        //AJUSTE DE PARAMETROS
        int minNShield = Math.min(nShields, s.size());
        int minNWeapons = Math.min(nWeapons, w.size());
        
        return new NumericDamage(minNWeapons, minNShield);
    }
    
    /**
     * @brief Decrementa en 1 el contador de armas
     * @param w : Arma a pasar como argumento
     */
    @Override
    public void discardWeapon(Weapon w){
        if(nWeapons > 0)
            nWeapons--;
    }
    
    /**
     * @brief Devuelve true si el daño representado no tiene ningún efecto
     * @return true si no se han producido daños | false en otro caso
     */
    @Override
    public boolean hasNoEffect(){
        return ((nShields == 0) && (nWeapons == 0));
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String test;
        
        String nSh = "- NSHIELDS: " + nShields + " ";
        String nW = "- NWEAPONS: " + nWeapons + "\n";
        
        test = nSh + nW;
        
        return test;
    }
}
