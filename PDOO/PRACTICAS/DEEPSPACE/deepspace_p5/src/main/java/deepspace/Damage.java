/**
 * @file Damage.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Abstract-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representa el daño producido a una estación espacial por una nave enemiga cuando
 * se pierde un combate. Cada instancia indicará a pérdida de una cantidad de potenciadores
 * de escudo y por otro lado, o bien una cantidad de tipos indeterminados de armas
 * o un conjunto de tipos de armas concretas que se deben eliminar.
 */
public abstract class Damage{
    public int nShields;
    
    /**
     * @brief Constructor con parametro
     * @param s : Numero de escudos
     */
    Damage(int s){
        nShields = s;
    }
    
    /**
     * @brief Constructor de copia
     * @param d : Otra instancia de Damage
     */
    Damage(Damage d){
        nShields = d.nShields;
    }
    
    public int getNShields(){
        return nShields;
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (Damage)
     * @return Objeto UI Damage
     */
    abstract public DamageToUI getUIversion();
    
    /**
     * @brief Devuelve una versión ajustada del objeto a las colecciones de armas y potenciadores de escudos suministradas como parámetro.
     * @param w : Coleccion de armas
     * @param s : Coleccion de potenciadores de escudo
     * @return Nueva version damage
     */
    abstract public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    /**
     * @brief Devuelve el indice de la posicion de la primera arma de la coleccion que coincida con el tipo dado
     * @param w : Coleccion de armas disponibles
     * @param t : Tipo de arma a buscar
     * @return indice de primer arma que cumpla las condiciones | -1 si no se ha encontrado
     */
    int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
        int index = 0;
        for (Weapon weapon : w){
            if(weapon.getType() == t)
                return index;
            else
                index++;
        }
        
        return -1;
    }
    
    /**
     * @brief Descarta un arma
     * @param w : Arma a pasar como argumento
     */
    abstract public void discardWeapon(Weapon w);
    
    /**
     * @brief Decrementa en una unidad el número de potenciadores de escudo que deben ser eliminados
     */
    public void discardShieldBooster(){
        if(nShields > 0)
            nShields--;
    }
    
    /**
     * @brief Devuelve true si el daño representado no tiene ningún efecto
     * @return true si no se han producido daños | false en otro caso
     */
    abstract public boolean hasNoEffect();
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String nSh;
        //CONTENT
        nSh = "- NSHIELDS: " + nShields + " ";
        
        return nSh;
    }
}
