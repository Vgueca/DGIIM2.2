/**
 * @file HangarToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representación ToUI del objeto Hangar
 */
public class HangarToUI {
    private int maxElements;
    private ArrayList<WeaponToUI> weapons;
    private ArrayList<ShieldToUI> shieldBoosters;    
    
    /**
     * @brief Constructor con parámetro de la clase Hangar
     * @param h : Objeto Hangar
     */
    HangarToUI(Hangar h) {
        weapons=new ArrayList<>();
        shieldBoosters=new ArrayList<>(); 
        
        maxElements=h.getMaxElements();
        
        for (Weapon w:h.getWeapons()) {
            weapons.add(w.getUIversion());
        }
        
        for(ShieldBooster s:h.getShieldBoosters()) {
            shieldBoosters.add(s.getUIversion());
        }
    }
    
    /**
     * @brief Obtiene la capacidad maxima del Hangar
     * @return maxElements
     */
    public int getMaxElements() {
        return maxElements;
    }

    /**
     * @brief Obtiene las armas disponibles dentro del Hangar
     * @return weapons
     */
    public ArrayList<WeaponToUI> getWeapons() {
        return weapons;
    }
    
    /**
     * @brief Obtiene los escudos disponibles dentro del Hangar
     * @return shieldBoosters
     */
    public ArrayList<ShieldToUI> getShieldBoosters() {
        return shieldBoosters;
    }
    
}
