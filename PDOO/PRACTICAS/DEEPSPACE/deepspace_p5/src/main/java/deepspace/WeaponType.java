/**
 * @file WeaponType.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-enumerate
 */
package deepspace;

/**
 * @brief Representa a los tipos de armas del juego, además de incluir
 * algunas funcionalidades adicionales
 */
public enum WeaponType {
    LASER(2.0f),
    MISSILE(3.0f),
    PLASMA(4.0f);
    
    private float power;
    
    /**
     * @brief Constructor privado
     * @param power Potencia del arma
     */
    WeaponType(float power){
        this.power = power;
    }
    
    /**
     * @brief Consultor. Devuelve el poder.
     * @return power
     */
    final float getPower(){
        return power;
    }
}
