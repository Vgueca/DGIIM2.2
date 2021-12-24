/**
 * @file LootToUI.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto Loot
 */
public class LootToUI {
    private final int nSupplies;
    private final int nWeapons;
    private final int nShields;
    private final int nHangars;
    private final int nMedals;

    private final boolean getEfficient;
    private final boolean spaceCity;    
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param l : Objeto Loot
     */
    LootToUI(Loot l) {
        nSupplies=l.getNSupplies();
        nWeapons=l.getNWeapons();
        nShields=l.getNShields();
        nHangars=l.getNHangars();
        nMedals=l.getNMedals();
        
        getEfficient=l.getEfficient();
        spaceCity=l.spaceCity();
    }  
    
    /**
     * @brief Consultor de nSupplies
     * @return nSupplies
     */
    public int getnSupplies() {
        return nSupplies;
    }
    
    /**
     * @brief Consultor de nWeapons
     * @return nWeapons
     */
    public int getnWeapons() {
        return nWeapons;
    }
    
    /**
     * @brief Consultor de nShields
     * @return nShields
     */
    public int getnShields() {
        return nShields;
    }

    /**
     * @brief Consultor de nHangars
     * @return nHangars
     */
    public int getnHangars() {
        return nHangars;
    }

    /**
     * @brief Consultor de nMedals
     * @return nMedals
     */
    public int getnMedals() {
        return nMedals;
    }
    
    /**
     * @brief Consultor de getEfficient
     * @return getEfficient
     */
    public boolean isGetEfficient() {
        return getEfficient;
    }
    
    /**
     * @brief Consultor de spaceCity
     * @return spaceCity
     */
    public boolean isSpaceCity() {
        return spaceCity;
    }
    
    
    
    
}
