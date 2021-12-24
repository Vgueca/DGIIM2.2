/**
 * @file SafeWeaponCardDeck.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Mazo de cartas de objetos de Weapon
 */
class SafeWeaponCardDeck extends CardDeck<Weapon> {
    /**
     * @brief Obtiene la siguiente carta del mazo de Weapon
     * @return Una instancia de Weapon
     */
    @Override
    public Weapon next() {
        Weapon h=(Weapon)(super.next());
        return new Weapon(h) ;
    }
}
