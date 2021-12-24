/**
 * @file SafeEnemyCardDeck.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Mazo de cartas de objetos de EnemyStarShip
 */
class SafeEnemyCardDeck extends CardDeck<EnemyStarShip> {
    /**
     * @brief Obtiene la siguiente carta del mazo de EnemyStarShip
     * @return Una instancia de EnemyStarShip
     */
    @Override
    public EnemyStarShip next() {
        EnemyStarShip h=(EnemyStarShip)(super.next());
        return new EnemyStarShip(h) ;
    }
}
