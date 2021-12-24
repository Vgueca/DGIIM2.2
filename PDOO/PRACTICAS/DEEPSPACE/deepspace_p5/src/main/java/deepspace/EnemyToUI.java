/**
 * @file EnemyToUI.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto EnemyStarShip
 */
public class EnemyToUI {
    private final String name;
    private final float ammoPower;
    private final float shieldPower;
    
    private LootToUI loot;
    private DamageToUI damage;   
    
    /**
     * @brief Constructor con parametro de la clase
     * @param enemy : Objeto EnemyStarShip
     */
    EnemyToUI(EnemyStarShip enemy) {
        name=enemy.getName();
        ammoPower=enemy.getAmmoPower();
        shieldPower=enemy.getShieldPower();
    
        loot=enemy.getLoot().getUIversion();
        damage = enemy.getDamage().getUIversion();
    }
    
    /**
     * @brief Consultor de atributo nombre
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @brief Consultor de atributo ammoPower
     * @return ammoPower
     */
    public float getAmmoPower() {
        return ammoPower;
    }
    
    /**
     * @brief Consultor de atributo shieldPower
     * @return shieldPower
     */
    public float getShieldPower() {
        return shieldPower;
    }
    
    /**
     * @brief Consultor de atributo loot
     * @return loot
     */
    public LootToUI getLoot() {
        return loot;
    }
    
    /**
     * @brief Consultor de atributo damage
     * @return damage
     */
    public DamageToUI getDamage() {
        return damage;
    }
}
