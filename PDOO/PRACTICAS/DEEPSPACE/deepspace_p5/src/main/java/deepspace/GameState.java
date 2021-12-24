/**
 * @file GameState.java
 * @author Profe
 * @version P-05
 * type : Java-enumerate
 */
package deepspace;

/**
 * @brief Representa el estado interno en el que se encuentra el juego
 */
public enum GameState {
    CANNOTPLAY, INIT,BEFORECOMBAT,AFTERCOMBAT
    /*
     * CANNOTPLAT: the Gameuniverse has benn created but not initialized
     * 
     * INIT: the GameUniverse has been initialized and the current player has not combated. 
     *       Players can mount/discard weapons,shielboosters and hangars or combat
     *
     * BEFORECOMBAT: players can only combat
     * AFTERCOMBAT:  players can mount/discard weapons,shielboosters and hangars 
     */
}