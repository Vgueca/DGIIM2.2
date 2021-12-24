/**
 * @file NumericDamageToUI.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto NumericDamage
 */
public class NumericDamageToUI extends DamageToUI{
    private int nWeapons;

    /**
     * @brief Constructor con parámetro de la clase NumericDamage
     * @param d : Objeto NumericDamage
     */
    NumericDamageToUI(NumericDamage d) {
        super(d);
        nWeapons=d.getNWeapons();
    }
    
    /**
     * @brief Consultor de nWeapons
     * @return nWeapons
     */
    public int getNWeapons() {
        return nWeapons;
    } 
    
    /**
     * @brief Obtiene el numero de armas a perder
     * @return Instancia String con numero de armas
     */
    @Override
    public String getWeaponInfo() {
        return ""+nWeapons;
    }
}
