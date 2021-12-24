/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author valenting
 */
public class EnemyStarShip implements SpaceFighter {
    
    private final float ammoPower;
    private final String name;
    private final float shieldPower;
    
    private Loot loot;
    
    private Damage damage;
    
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        name = n;
        ammoPower = a;
        shieldPower = s;
        loot = l;
        damage = d;
    }
    EnemyStarShip(EnemyStarShip e){
        name = e.name;
        ammoPower = e.ammoPower;
        shieldPower = e.shieldPower;
        loot = e.loot;
        damage = e.damage;
    }
    
    
    public float fire(){
        return getAmmoPower();
    }
   
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public Damage getDamage(){
        return damage;
    }
    
    public Loot getLoot(){
        return loot;
    }
    
    public String getName(){
        return name;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    public float protection(){
        return getShieldPower();
    }
    
    public ShotResult receiveShot(float shot){
        if(shot > protection()){
            return ShotResult.DONOTRESIST;
        }
        else{
            return ShotResult.RESIST;
        }
    }
    
    EnemyToUI getUIversion() {
        return new EnemyToUI(this);
    }
    
    @Override
    public String toString(){
        String message = "[EnemyStarship]: Name: " + name
                + ", Protection: " + protection()
                + ", Fire: " + fire()
                + ", Loot: " + loot.toString()
                + ", Damage: " + damage.toString();
        return message;
    }

}
