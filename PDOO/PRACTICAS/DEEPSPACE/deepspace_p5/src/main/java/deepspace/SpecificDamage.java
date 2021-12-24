/**
 * @file SpecificDamage.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @brief Representa el daño producido a una estación espacial por una nave enemiga cuando
 * se pierde un combate. Es una clase Hija de Damage, por lo que además de heredar el atributo
 * de potenciadores de escudo a perder, se implementa la cantidad de armas a perder con un array
 * formado por los tipos de arma.
 */ 
public class SpecificDamage extends Damage{
    private ArrayList<WeaponType> weapons;
    
    /**
     * @brief Constructor con parametro
     * @param wl : Array de tipos de armas
     * @param s : Numero de escudos
     */
    SpecificDamage(ArrayList<WeaponType> wl, int s){
        super(s);
        weapons = new ArrayList<>(wl);
    }
    
    /**
     * @brief Constructor de copia
     * @param sd : Otra instancia de SpecificDamage
     */
    SpecificDamage(SpecificDamage sd){
        super(sd);
        for(WeaponType w : sd.weapons){
            weapons.add(w);
        }
    }
    
    /**
     * @breif Devuelve los tipos de armas que hay
     * @return ArrayList con los tipos de armas
     */
    public ArrayList<WeaponType> getWeapons(){
        return weapons;
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (SpecificDamage)
     * @return Objeto UI SpecificDamage
     */
    @Override
    public SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
    }
    
    /**
     * @brief Devuelve una versión ajustada del objeto a las colecciones de armas y potenciadores de escudos suministradas como parámetro.
     * @param w : Coleccion de armas
     * @param s : Coleccion de potenciadores de escudo
     * @return Nueva version SpecificDamage
     * @author M.Anaya
     */
    @Override
    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        //AJUSTE MINIMO SHIELDBOOSTERS
        int minNShield = Math.min(nShields, s.size());
        
        //AJUSTE MINIMO WEAPONS Y WEAPONTYPE
        ArrayList<WeaponType> typesCopy = new ArrayList<>();
        ArrayList<Weapon> weaponsCopy = new ArrayList<>();
        ArrayList<WeaponType> adjustTypes = new ArrayList<>();

        //OBTENER COPIA DE OBJETOS DE DAMAGE - TYPES
        for(int i=0; i< weapons.size(); i++){
            typesCopy.add(weapons.get(i));
        }

        //OBTENER COPIA DEL VECTOR DE ARMAS
        for(int i=0; i<w.size(); i++){
            weaponsCopy.add(w.get(i));
        }

        //USO DE ITERADORES PARA REALIZAR EL AJUSTE
        Iterator<WeaponType> it = typesCopy.iterator();
        int index;

        while(it.hasNext()){
           //COMPROBAMOS SI EL TIPO DE ARMA ESTÁ EN EL ARRAY
           WeaponType type = it.next();

           index = arrayContainsType(weaponsCopy,type);
           if(index != -1){
               weaponsCopy.remove(index);
               adjustTypes.add(type);
           }
           it.remove();
        }
        
        return new SpecificDamage(adjustTypes,minNShield);
    }
    
    /**
     * @brief Intenta eliminar el tipo de arma pasada como argumento
     * @param w : Arma a pasar como argumento
     */
    @Override
    public void discardWeapon(Weapon w){
        weapons.remove(weapons.indexOf(w.getType()));
    }
    
    /**
     * @brief Devuelve true si el daño representado no tiene ningún efecto
     * @return true si no se han producido daños | false en otro caso
     */
    @Override
    public boolean hasNoEffect(){
        return ((nShields == 0) && (weapons.isEmpty()));
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String test;
        //CONTENT
        String nSh = "- NSHIELDS: " + nShields + " ";
        
        String arrWT = "- WEAPONS: ";
        if(weapons == null || weapons.isEmpty()){
            arrWT += "Ninguno\n";
        }
        else{
            arrWT += weapons.toString() + "\n";
        }
        
        test = nSh + arrWT;
        
        return test;
    }
}
