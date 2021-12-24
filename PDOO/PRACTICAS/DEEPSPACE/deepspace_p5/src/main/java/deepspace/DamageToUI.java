/**
 * @file DamageToUI.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Abstract-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto Damage
 */
public abstract class DamageToUI {
    private int nShields;
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param d : Objeto de tipo Damage
     */
    DamageToUI(Damage d) {
        nShields=d.getNShields();
    }
    
    /**
     * @brief Devuelve el número de escudos de la instancia
     * @return Numero de escudos
     */
    public int getNShields() {
        return nShields;
    }
    
    /**
     * @brief Devuelve la información de las armas a perder del objeto Damage
     * @return Instancia String con la información sobre los Weapons a perder
     */
    public abstract String getWeaponInfo();
}
