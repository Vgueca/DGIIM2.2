/**
 * @file SafeSuppliesCardDeck.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Mazo de cartas de objetos de SuppliesPackage
 */
class SafeSuppliesCardDeck extends CardDeck<SuppliesPackage> {
    /**
     * @brief Obtiene la siguiente carta del mazo de SuppliesPackage
     * @return Una instancia de SuppliesPackage
     */
    @Override
    public SuppliesPackage next() {
        SuppliesPackage h=(SuppliesPackage)(super.next());
        return new SuppliesPackage(h) ;
    }
}