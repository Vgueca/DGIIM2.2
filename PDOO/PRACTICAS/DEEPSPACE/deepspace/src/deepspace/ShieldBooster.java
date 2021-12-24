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
public class ShieldBooster implements CombatElement {
    private String name;
    private float boost;
    private int uses;
    
    ShieldBooster(String n, float b, int u){
        name = n;
        boost = b;
        uses = u;
    }
    ShieldBooster(ShieldBooster s){
        name = s.name;
        boost = s.boost;
        uses = s.uses;
    }
    public float getBoost(){
        return boost;
    }
    
    @Override
    public int getUses(){
        return uses;
    }
    
    @Override
    public float useIt(){
        if(getUses()>0){
            uses--;
            return getBoost();
        }
        else{
            return 1.0f;
        }
    }
    
    @Override
    public String toString() {
        String message = "/ShieldBooster/ ----- Boost: " + boost + " ------" + " Uses: " + uses;
        return message;
    }
    
    ShieldToUI getUIversion() {
        return new ShieldToUI(this);
    }
}
