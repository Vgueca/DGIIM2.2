/**
 * @file WeaponToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto Weapon
 */
public class WeaponToUI {
    private WeaponType type;
    private float power;
    private int uses;  
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param w : Objeto Weapon
     */
    WeaponToUI(Weapon w) {
        type=w.getType();
        power=w.power();
        uses=w.getUses();
    }  
    
    /**
     * @brief Consultor de Type
     * @return type
     */
    public WeaponType getType() {
        return type;
    }
    
    /**
     * @brief Consultor de power
     * @return power
     */
    public float getPower() {
        return power;
    }
    
    /**
     * @brief Consultor de uses
     * @return uses
     */
    public int getUses() {
        return uses;
    }
    
}
