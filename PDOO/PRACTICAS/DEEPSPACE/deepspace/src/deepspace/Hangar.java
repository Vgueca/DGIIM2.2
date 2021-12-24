package deepspace;


import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author valenting
 */
public class Hangar {
    private int maxElements;
    
    private ArrayList<ShieldBooster> shieldBoosters;
    private ArrayList<Weapon> weapons;
    
    Hangar(int capacity){
        maxElements = capacity;
        shieldBoosters = new ArrayList<>();
        weapons = new ArrayList<>();
    }
    
    Hangar(Hangar h){
        maxElements = h.maxElements;
        shieldBoosters = new ArrayList<>();
        weapons = new ArrayList<>();
        
        for (Weapon w: h.getWeapons()){
            weapons.add(w);
        }
        for (ShieldBooster s: h.getShieldBoosters()){
            shieldBoosters.add(s);
        }
    
    }
    
    private boolean spaceAvailable(){
        return (maxElements > (shieldBoosters.size() + weapons.size()));
    }
    
    boolean addWeapon(Weapon w){
        if(spaceAvailable()){
            weapons.add(w);
            return true;
        }
        else{
            return false;
        }
    }
    
    boolean addShieldBooster(ShieldBooster s){
        if(spaceAvailable()){
            shieldBoosters.add(s);
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getMaxElements(){
        return maxElements;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    
    
    public ShieldBooster removeShieldBooster(int s){
        if(shieldBoosters.size()<= s || shieldBoosters.isEmpty() ){
            return null;
        }
        else{
            return shieldBoosters.remove(s);
        }
    }
   
    public Weapon removeWeapon(int w){
        
        if(weapons.size()<= w || weapons.size()==0){
           
            return null;
        }
        else{
            
            return weapons.remove(w);
        }
        
    }
    
    
    
    HangarToUI getUIversion() {
        return new HangarToUI(this);
    }
    
    @Override
    public String toString(){
        String message = " / Hangar / -----  Maxelements: " + maxElements + "----- Shields: " + shieldBoosters.toString() + "----- Weapons: " + weapons.toString() + "\n";
        return message;
    }

}
