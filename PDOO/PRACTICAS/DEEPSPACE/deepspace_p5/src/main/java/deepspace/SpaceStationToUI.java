/**
 * @file SpaceStationToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representación ToUI del objeto SpaceStation
 */
public class SpaceStationToUI {
    private String name;
    private int nMedals;
    
    private float ammoPower=1.0f;
    private float fuelUnits=1.0f;
    private float shieldPower=1.0f;
    
    private ArrayList<WeaponToUI> weapons;
    private ArrayList<ShieldToUI> shieldBoosters;
    private HangarToUI hangar;   
    private DamageToUI pendingDamage;
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param station : Objeto SpaceStation
     */
    SpaceStationToUI(SpaceStation station) {
        weapons=new ArrayList<>();
        shieldBoosters=new ArrayList<>();         
        
        name=station.getName();
        nMedals=station.getNMedals();
        
        ammoPower=station.getAmmoPower();
        fuelUnits=station.getFuelUnits();
        shieldPower=station.getShieldPower();
        
        for (Weapon w:station.getWeapons()) {
            weapons.add(w.getUIversion());
        }
        
        for(ShieldBooster s:station.getShieldBoosters()) {
            shieldBoosters.add(s.getUIversion());
        }
        
        Hangar h=station.getHangar();
        if (h!=null) {
            hangar=h.getUIversion();
        }
        else {
            hangar=null;
        }
        
        // MIGUEL: Añadido de  pendingDamage  y su consultor
        
        Damage d = station.getPendingDamage();
        if (d != null) {
          pendingDamage = d.getUIversion();
        } else {
          pendingDamage = null;
        }
            
    }
    
    /**
     * @brief Consultor de name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @brief Consultor de nMedals
     * @return nMedals
     */
    public int getnMedals() {
        return nMedals;
    }
    
    /**
     * @brief Consultor de ammoPower
     * @return ammoPower
     */
    public float getAmmoPower() {
        return ammoPower;
    }
    
    /**
     * @brief Consultor de fuelUnits
     * @return fuelUnits
     */
    public float getFuelUnits() {
        return fuelUnits;
    }
    
    /**
     * @brief Consultor de shieldPower
     * @return shieldPower
     */
    public float getShieldPower() {
        return shieldPower;
    }
    
    /**
     * @brief Consultor de weapons
     * @return weapons
     */
    public ArrayList<WeaponToUI> getWeapons() {
        return weapons;
    }
    
    /**
     * @brief Consultor de shieldBoosters
     * @return shieldBoosters
     */
    public ArrayList<ShieldToUI> getShieldBoosters() {
        return shieldBoosters;
    }
    
    /**
     * @brief Consultor de Hangar
     * @return hangar
     */
    public HangarToUI getHangar() {
        return hangar;
    }
    
    /**
     * @brief Consultor de pendingDamage
     * @return pendingDamge
     */
    public DamageToUI getPendingDamage() {
        return pendingDamage;
    }
}
