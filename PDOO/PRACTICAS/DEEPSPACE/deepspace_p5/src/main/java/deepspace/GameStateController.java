/**
 * @file GameStateController.java
 * @author Profe
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Controlador del estado interno del juego
 */
class GameStateController {
    private GameState state;
    
    /**
     * @brief Constructor por defecto
     */
    GameStateController() {
        state=GameState.CANNOTPLAY;        
    }

    /**
     * @brief Devuelve el estado actual del juego
     * @return state
     */
    public GameState getState() {
        return state;
    }
    
    /**
     * @brief Determina el siguiente estado del juego
     * @param turn : Turno de jugador
     * @param nPlayers : Numero de jugadores totales
     * @return Estado siguiente del juego
     */
    public GameState next(int turn,int nPlayers) {
        switch (state) {
            case CANNOTPLAY: 
                state=GameState.INIT;
                break;
            case INIT: 
                state=GameState.AFTERCOMBAT;
                break;                
            case BEFORECOMBAT: 
                state=GameState.AFTERCOMBAT;
                break;
            case AFTERCOMBAT: 
                if (turn>=nPlayers)
                    state=GameState.BEFORECOMBAT;
                else
                    state=GameState.INIT;
                break;                
        }
        return state;
    }
}
