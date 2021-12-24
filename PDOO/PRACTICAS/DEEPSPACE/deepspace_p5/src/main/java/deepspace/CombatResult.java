/**
 * @file CombatResult.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-enumerate
 */
package deepspace;

/**
 * @brief Representa todos los resultados posibles de un combate entre una
 * estacion espacial y una nave enemiga.
 */
public enum CombatResult {
    ENEMYWINS, NOCOMBAT, STATIONESCAPES, STATIONWINS, STATIONWINSANDCONVERTS;
}
