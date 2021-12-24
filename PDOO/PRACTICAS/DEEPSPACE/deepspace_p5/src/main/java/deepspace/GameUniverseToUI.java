/**
 * @file GameUniverseToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto GameUniverse
 */
public class GameUniverseToUI {
    SpaceStationToUI currentStation;
    EnemyToUI currentEnemy;
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param station : Instancia SpaceStation
     * @param enemy : Instancia EnemyStarShip
     */
    GameUniverseToUI(SpaceStation station,EnemyStarShip enemy) {
        currentStation = station.getUIversion();
        currentEnemy= enemy.getUIversion();
    }

    /**
     * @brief Obtiene la estacion actual
     * @return currentStation
     */
    public SpaceStationToUI getCurrentStation() {
        return currentStation;
    }
    
    /**
     * @brief Obtiene el enemigo actual
     * @return currentEnemy
     */
    public EnemyToUI getCurrentEnemy() {
        return currentEnemy;
    }
    
    
}
