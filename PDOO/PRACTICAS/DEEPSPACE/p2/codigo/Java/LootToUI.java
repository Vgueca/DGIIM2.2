/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author Profe
 */
public class LootToUI {
    private final int nSupplies;
    private final int nWeapons;
    private final int nShields;
    private final int nHangars;
    private final int nMedals;

    LootToUI(Loot l) {
        nSupplies=l.getNSupplies();
        nWeapons=l.getNWeapons();
        nShields=l.getNShields();
        nHangars=l.getNHangars();
        nMedals=l.getNMedals();
    }  

    public int getnSupplies() {
        return nSupplies;
    }

    public int getnWeapons() {
        return nWeapons;
    }

    public int getnShields() {
        return nShields;
    }

    public int getnHangars() {
        return nHangars;
    }

    public int getnMedals() {
        return nMedals;
    }
    
    
}
