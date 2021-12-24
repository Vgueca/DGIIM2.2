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
public class SpecificDamage extends Damage{
    
    private ArrayList<WeaponType> weapons;
    
    SpecificDamage(ArrayList<WeaponType> w, int s){
        super(s);
        if(w != null){
            weapons = new ArrayList<>(w);
        }
        else{
            weapons = null;
        }
       
    }
    
    @Override 
    public Damage copy(){
        SpecificDamage copy = new SpecificDamage(this.getWeapons(), this.getNShields());
        return copy;
    }
    
    public ArrayList<WeaponType> getWeapons(){
        return weapons;
    }
    
    
    @Override
    public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        int min;
        boolean found = false, end = false;
        ArrayList<WeaponType> w2 = new ArrayList<>();
        ArrayList<Weapon> waux = new ArrayList<>(w);
        int j;
        
        if(s.size() < getNShields()){
            min = s.size();
        }
        else{
            min = getNShields();
        }
            
        for(int i=0; i < weapons.size(); i++){
            for(j =0; j < waux.size() && !end; j++ ){  
                if(weapons.get(i) == waux.get(j).getType()){
                    found = true;
                    end = true;
                }
            }
            if(found){
                w2.add(weapons.get(i));
                waux.remove(j-1);
            }
            found = false;
            end = false; 
        }
        if(w2.isEmpty()){
            w2 = null;
        }
        
        SpecificDamage adjusted = new SpecificDamage(w2,min);
        return adjusted;
    }
    
    @Override
    public boolean hasNoeffect(){
        return ((weapons == null || weapons.isEmpty()) && getNShields() == 0);
    }
    
    @Override
    public void discardWeapon(Weapon w){
      if(weapons != null){
        weapons.remove(w.getType());
      }
    }
    
    @Override
     public SpecificDamageToUI getUIversion(){
         return new SpecificDamageToUI(this);
     }
    

    @Override
    public String toString(){
        String message2;
        if ( weapons == null){
            message2 = " ";
        }
        else{
            message2 = weapons.toString();
        }
        
        String message = "/ Damage / ----- Number of Shields: " + getNShields() + " ----- Weapon Type: " + message2;

        return message;
    } 

}
