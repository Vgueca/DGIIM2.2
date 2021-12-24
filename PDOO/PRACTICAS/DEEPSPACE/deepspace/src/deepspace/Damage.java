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
public abstract class Damage {
   
    
    private int nShields;
    
    public Damage(int s){
        nShields = s;
    }
    
    public abstract Damage copy();
            
    int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
        int index = -1;
        boolean end = false;
        
        for(int i =0; i < w.size() && !end; i++){
            if(w.get(i).getType() == t){
                index = i;
                end = true;
            }
        }
        return index;
    }

    public abstract Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);

    
    public abstract void discardWeapon(Weapon w);

    void discardShieldBooster(){
        if(nShields>0){
            nShields--;
        }
    }
    public int getNShields(){
        return nShields;
    }
    
    public abstract boolean hasNoeffect();


    
    abstract DamageToUI getUIversion() ;
    
    @Override
    public abstract String toString();

}
