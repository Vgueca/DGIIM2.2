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
public class SuppliesPackage {
    private float ammoPower;
    private float fuelUnits;
    private float shieldPower;
    
    SuppliesPackage(float ammoPower, float fuelUnits, float shieldPower){
        this.ammoPower = ammoPower;
        this.fuelUnits = fuelUnits;
        this.shieldPower = shieldPower;
    }
    SuppliesPackage(SuppliesPackage s){
        ammoPower = s.ammoPower;
        fuelUnits = s.fuelUnits;
        shieldPower = s.shieldPower;
    }
    
    public float getammoPower(){
        return ammoPower;
    }
    public float getfuelUnits(){
        return fuelUnits;
    }
    public float getshieldPower(){
        return shieldPower;
    }
    
     @Override
    public String toString() {
        String message = "/SUPPLIESPACKAGE/----   AmmoPower: " + getammoPower() + " -----   FuelUnits: " + getfuelUnits() +  " -----  ShieldPower: " + getshieldPower() + " \n"; 
        return message;
    }
    
    
}
