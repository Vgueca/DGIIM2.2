/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;


import java.util.ArrayList;

/**
 *
 * @author valenting
 */
public class NumericDamage extends Damage{
    
    private int nWeapons;
    public NumericDamage(int s, int w){
        super(s);
        nWeapons = w;
    }
    
    @Override 
    public Damage copy(){
        NumericDamage copy = new NumericDamage(this.getNShields(), this.getNWeapons());
        return copy;
    }
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    
    @Override
    public void discardWeapon(Weapon w){
        if(nWeapons >0){
            nWeapons--;
        }
    }
    @Override
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        int min, min2;
        if(s.size() < getNShields()){
            min = s.size();
        }
        else{
            min = getNShields();
        }
        if(w.size() < nWeapons){
            min2 = w.size();
            
        }
        else{
            min2 = nWeapons;
        }
        
        NumericDamage adjusted = new NumericDamage(min, min2);
        return adjusted;
    }
    
    @Override
    public boolean hasNoeffect(){
        return (getNWeapons()==0 && getNShields()==0);
         
    }
    
    @Override
     public NumericDamageToUI getUIversion(){
         return new NumericDamageToUI(this);
     }
     
    @Override
    public String toString(){
        String message = " / Damage / ----- Number of Shields: " + getNShields() + " ----- Number of Weapons: " + getNWeapons();
        return message;
    } 

     
}
