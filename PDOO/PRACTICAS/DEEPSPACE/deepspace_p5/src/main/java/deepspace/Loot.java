/**
 * @file Loot.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representa el botín que se obtiene al vencer a una nave enemiga. Puede
 * incluir cantidades que representen un número de paquetes de suministros, armas,
 * potenciadores de escudo, hangares y/o medallas.
 */
class Loot {
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;
    private boolean getEfficient;
    private boolean spaceCity;
    
    /**
     * @brief Constructor con parametro
     * @param p_supplies : Número suministros
     * @param p_weapons : Número armas
     * @param p_shields : Número escudos
     * @param p_hangars : Número hangars
     * @param p_medals : Número medallas
     */
    Loot(int p_supplies, int p_weapons, int p_shields, int p_hangars, int p_medals){
        nSupplies = p_supplies;
        nWeapons = p_weapons;
        nShields = p_shields;
        nHangars = p_hangars;
        nMedals = p_medals;
        getEfficient = false;
        spaceCity = false;
    }
    
    /**
     * @brief Constructor con parametro
     * @param p_supplies : Número suministros
     * @param p_weapons : Número armas
     * @param p_shields : Número escudos
     * @param p_hangars : Número hangars
     * @param p_medals : Número medallas
     * @param ef : Eficiencia SpaceStation
     * @param city : Ciudad SpaceStation
     */
    Loot(int p_supplies, int p_weapons, int p_shields, int p_hangars, int p_medals, boolean ef, boolean city){
        nSupplies = p_supplies;
        nWeapons = p_weapons;
        nShields = p_shields;
        nHangars = p_hangars;
        nMedals = p_medals;
        getEfficient = ef;
        spaceCity = city;
    }
    
    /**
     * @brief Consultor de suministros
     * @return nSupplies
     */
    public int getNSupplies(){
        return nSupplies;
    }
    
    /**
     * @brief Consultor de armas
     * @return nWeapons
     */
    public int getNWeapons(){
        return nWeapons;
    }
    
    /**
     * @brief Consultor de escudos
     * @return nShields
     */
    public int getNShields(){
        return nShields;
    }
    
    /**
     * @brief Consultor de Hangares
     * @return nHangars
     */
    public int getNHangars(){
        return nHangars;
    }
    
    /**
     * @brief Consultor de Medallas
     * @return nMedals
     */
    public int getNMedals(){
        return nMedals;
    }
    
    /**
     * @brief Consultor de getEfficient
     * @return getEfficient
     */
    public boolean getEfficient(){
        return getEfficient;
    }
    
    /**
     * @brief Consultor de spaceCity
     * @return spaceCity
     */
    public boolean spaceCity(){
        return spaceCity;
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (Loot)
     * @return Objeto UI Loot
     */
    public LootToUI getUIversion(){
        return new LootToUI(this);
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    public String toString(){
        String test;
        //CONTENT
        String supplies = "- NSUPPLIES: " + nSupplies + "\n";
        String weapons = "- NWEAPONS: " + nWeapons + "\n";
        String shields = "- NSHIELDS: " + nShields + "\n";
        String hangars = "- NHANGARS: " + nHangars + "\n";
        String medals = "- NMEDALS: " + nMedals + "\n";
        
        test = supplies + weapons + shields + hangars + medals;
        
        return test;
    }
}
