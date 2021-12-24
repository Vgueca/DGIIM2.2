/**
 * @file CombatElement.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Interface
 */
package deepspace;

/**
 * @brief Interfaz de juego utilizada para Weapon y ShieldBooster
 */
public interface CombatElement {
    public int getUses();
    public float useIt();
}