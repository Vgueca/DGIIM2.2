/**
 * @file SafeShieldBoosterCardDeck.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Mazo de cartas de objetos de ShieldBooster
 */
class SafeShieldBoosterCardDeck extends CardDeck<ShieldBooster> {
    /**
     * @brief Obtiene la siguiente carta del mazo de ShieldBooster
     * @return Una instancia de ShieldBooster
     */
    @Override
    public ShieldBooster next() {
        ShieldBooster h=(ShieldBooster)(super.next());
        return new ShieldBooster(h) ;
    }
}
