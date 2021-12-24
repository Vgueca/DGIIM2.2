/**
 * @file SafeHangarCardDeck.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Mazo de cartas de objetos de Hangar
 */
class SafeHangarCardDeck extends CardDeck<Hangar> {
    /**
     * @brief Obtiene la siguiente carta del mazo de Hangar
     * @return Una instancia de Hangar
     */
    @Override
    public Hangar next() {
        Hangar h=(Hangar)(super.next());
        return new Hangar(h) ;
    }
}

