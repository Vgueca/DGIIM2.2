/**
 * @file ShieldBooster.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representa los potenciadores de escudo que pueden tener las estaciones
 * espaciales.
 */
class ShieldBooster implements CombatElement{
    private String name;
    private float boost;
    private int uses;
    
    /**
     * @brief Constructor con parametro
     * @param p_name Valor nombre
     * @param p_boost Valor potenciador
     * @param p_uses Valor usos
     */
    ShieldBooster(String p_name, float p_boost, int p_uses){
        name = p_name;
        boost = p_boost;
        uses = p_uses;
    }
    
    /**
     * @brief Constructor de Copia
     * @param sb Otro objeto de la clase ShieldBooster
     */
    ShieldBooster(ShieldBooster sb){
        name = sb.name;
        boost = sb.boost;
        uses = sb.uses;
    }
    
    /**
     * @brief Consultor de potenciador
     * @return boost
     */
    public final float getBoost(){
        return boost;
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
     * @brief Emula uso del potenciador de escudo.
     */
    @Override
    public float useIt(){
        if(getUses() > 0){
            uses--;
            return boost;
        }
        
        else{
            return 1.0f;
        }
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (ShieldBooster)
     * @return Objeto UI ShieldBooster
     */
    public ShieldToUI getUIversion(){
        return new ShieldToUI(this);
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
        String bS = "- BOOST: " + boost + " ";
        String use = "- USES: " + uses + "\n";
        
        test = namE + bS + use;
        
        return test;
    }
}
