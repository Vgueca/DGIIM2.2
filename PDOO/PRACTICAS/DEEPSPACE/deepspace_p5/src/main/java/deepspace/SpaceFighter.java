/**
 * @file SpaceFighter.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Interface
 */
package deepspace;

/**
 * @brief Interfaz de juego utilizada para SpaceStation y EnemyStarShip
 */
public interface SpaceFighter {
    public float fire();
    public float protection();
    public ShotResult receiveShot(float shot);
}
