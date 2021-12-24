package deepspace;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author valenting
 */
public class Loot {
  ;
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;
    private boolean getEfficient;
    private boolean spaceCity;
  
    Loot(int nSupplies , int nWeapons , int nShields, int nHangars, int nMedals) {
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
    }  
    
    //Constructor para los dos nuevos parámetros
    Loot(int nSupplies , int nWeapons , int nShields, int nHangars, int nMedals, boolean getEfficient, boolean spaceCity) {
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        this.getEfficient = getEfficient;
        this.spaceCity = spaceCity;
    }
    
    
    public int getNSupplies(){
        return nSupplies;
    }
    public int getNWeapons(){
        return nWeapons;
    }
    public int getNShields(){
        return nShields;
    }
    public int getNHangars(){
        return nHangars;
    }
    public int getNMedals(){
        return nMedals;
    }
    public boolean getEfficient(){
        return getEfficient;
    }
    public boolean spaceCity(){
        return spaceCity;
    }
    
    
    @Override
    public String toString(){
        return  "Loot(nSupplies = " + getNSupplies() + 
                ", nWeapons = " + getNWeapons() + 
                ", nShields = " + getNShields() + 
                ", nHangars = " + getNHangars() + 
                ", nMedals = " + getNMedals() + 
                ", Converts to efficient = " + getEfficient() +
                ", Converts to a City = " + spaceCity() +
                ")"; 
    }
    
    
    LootToUI getUIversion() {
        return new LootToUI(this);
    }
}
