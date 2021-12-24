/**
 * @file ShieldBoosterToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto ShieldBooster
 */
public class ShieldToUI {
    private float boost;
    private int uses;
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param s : Objeto ShieldBooster
     */
    ShieldToUI (ShieldBooster s) {
        boost=s.getBoost();
        uses=s.getUses();
    }    
    
    /**
     * @brief Consultor de boost
     * @return boost
     */
    public float getBoost() {
        return boost;
    }
    
    /**
     * @brief Consultor de uses
     * @return uses
     */
    public int getUses() {
        return uses;
    }
}
