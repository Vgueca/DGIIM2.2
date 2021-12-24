/**
 * @file Weapon.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * Representa a las armas de las que puede disponer una estación espacial para
 * potenciar su energía al disparar
 */
class Weapon implements CombatElement{
    private String name;
    private WeaponType type;
    private int uses;
    
    /**
     * @brief Constructor con parametro
     * @param p_name Valor nombre
     * @param p_type Valor tipo de arma
     * @param p_uses Valor de uso
     */
    Weapon(String p_name, WeaponType p_type, int p_uses){
        name = p_name;
        type = p_type;
        uses = p_uses;
    }
    
    /**
     * @brief Constructor de Copia
     * @param w Otro objeto de la clase Weapon
     */
    Weapon(Weapon w){
        name = w.name;
        type = w.type;
        uses = w.uses;
    }
    
    /**
     * @brief Consultor de tipo de arma
     * @return type
     */
    public final WeaponType getType(){
        return type;
    }
    
    /**
     * @brief Consultor de usos
     * @return uses
     */
    @Override
    public final int getUses(){
        return uses;
    }
    
    /**
     * @brief Devuelve la potencia de disparo indicada por el tipo de arma
     */
    public final float power(){
        return type.getPower();
    }
    
    /**
     * @brief Emula el uso del arma
     * @return power() value si uses > 0 | 1.0 en otro caso.
     */
    @Override
    public final float useIt(){
        if(getUses() > 0){
            uses--;
            return power();
        }
        else{
            return 1.0f;
        }
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (Weapon)
     * @return Objeto UI WeaponToUI
     */
    public WeaponToUI getUIversion(){
        return new WeaponToUI(this);
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String test;
        //CONTENT
        String namE = "- NAME: " + name + " ";
        String wTy = "- " + type + " ";
        String pow = "- POWER: " + power() + " ";
        String use = "- USES: " + uses + "\n";
        
        
        test = namE + wTy + pow + use;
        
        return test;
    }
}
