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
public class SpaceStation implements SpaceFighter {
    private static final int MAXFUEL = 100;
    private static final float SHIELDLOSSPERUNITSHOT = 0.1f;
     
     
    private float ammoPower;
    private float fuelUnits;
    private String name;
    private int nMedals;
    private float shieldPower;
    
    private Damage pendingDamage;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;
    private Hangar hangar;
    
    private void assignFuelValue(float f){
        if(f < MAXFUEL){
            fuelUnits = f;
        }
        
    }
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoeffect()){
            pendingDamage = null;
        }
    }
    SpaceStation(String n, SuppliesPackage supplies){
        name = n;
        ammoPower = supplies.getammoPower();
        fuelUnits = supplies.getfuelUnits();
        nMedals = 0;
        shieldPower = supplies.getshieldPower();
        
        pendingDamage = null;
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
        hangar = null;
        
        
    }
    
    public SpaceStation(SpaceStation s){
        name = s.getName();
        ammoPower = s.getAmmoPower();
        fuelUnits = s.getFuelUnits();
        nMedals = s.getNMedals();
        shieldPower = s.getShieldPower();
        pendingDamage = s.getPendingDamage();
        weapons = new ArrayList<>(s.getWeapons());
        shieldBoosters = new ArrayList<>(s.getShieldBoosters());
        hangar = s.getHangar();
                
    }
    
    void cleanUpMountedItems(){
        int tam= weapons.size();
        int tam2= shieldBoosters.size();
        ArrayList<Weapon> w_removed = new ArrayList<>();
        ArrayList<ShieldBooster> s_removed = new ArrayList<>();
        for(int i =0; i < tam; i++){
            if(weapons.get(i).getUses() != 0){
                w_removed.add(weapons.get(i));
            }
        }
        for(int i =0; i < tam2; i++){
            if(shieldBoosters.get(i).getUses() != 0){
                s_removed.add(shieldBoosters.get(i));
            }
        }
        
        weapons = w_removed;
        shieldBoosters = s_removed;

    }
    void discardHangar(){
        hangar = null;
    }
    void discardShieldBooster(int i){
        int size = shieldBoosters.size();
        if(i>=0 && i< size){
            ShieldBooster s = shieldBoosters.remove(i);
            if(pendingDamage != null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
            
        }
    }
    void discardShieldBoosterInHangar(int i){
        if(hangar!=null){
            hangar.removeShieldBooster(i);
        }
    }
    void discardWeapon(int i){
        int size = weapons.size();
        if(i>=0 && i< size){
            Weapon w = weapons.remove(i);
            if(pendingDamage != null){
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
            
        }
    }
    void discardWeaponInHangar(int i){
        if(hangar!=null){
            hangar.removeWeapon(i);
        }
    }
    
    
    public float fire(){
        float factor = 1;
        for(int i =0; i < weapons.size(); i++){
            factor = factor * weapons.get(i).useIt();
        }
        return factor*ammoPower;
    }
    
    float getAmmoPower(){
        return ammoPower;
    }
    float getFuelUnits(){
        return fuelUnits;
    }
    Hangar getHangar(){
        return hangar;
    }
    String getName(){
        return name;
    }
    int getNMedals(){
        return nMedals;
    }
    
    void addMedals(int nmedals){
        nMedals = getNMedals() + nmedals;
    }
    
    Damage getPendingDamage(){
        return pendingDamage;
    }
    
    ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    float getShieldPower(){
        return shieldPower;
    }
    
    float getSpeed(){
        float speed;
        speed = (getFuelUnits()/MAXFUEL);
        return speed;
    }
    
    ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    void mountShieldBooster(int i){
         if(hangar != null){
            ShieldBooster s = hangar.removeShieldBooster(i);
            if(s!= null){
                shieldBoosters.add(s);
            }
        }
    }
    
    void mountWeapon(int i ){
        if(hangar != null){
            Weapon w = hangar.removeWeapon(i);
            if(w!= null){
                weapons.add(w);
            }
        }
        
    }
    
    void move(){
        if (0<= fuelUnits - getSpeed()){
           fuelUnits= fuelUnits - getSpeed(); 
        }
    }
    
    public float protection(){
        float factor = 1;
        for(int i =0; i < shieldBoosters.size(); i++){
            factor = factor * shieldBoosters.get(i).useIt();
        }
        return factor*shieldPower;
    }
    
    void receiveHangar(Hangar h){
        if(hangar == null){
            hangar = h;
        }
    }
    
    boolean recieveShieldBooster(ShieldBooster s){
        if(hangar != null){
            return hangar.addShieldBooster(s);
        }
        else{
            return false;
        }
    }
    
    public ShotResult receiveShot(float shot){
        if(protection() < shot){
            shieldPower=0;
            return ShotResult.DONOTRESIST;
        }
        else{
            shieldPower = shieldPower - SHIELDLOSSPERUNITSHOT*shot;
            if(shieldPower <0){
                shieldPower = 0;
            }
            
            return ShotResult.RESIST;
        }
    }
    
    void recieveSupplies(SuppliesPackage s){
        ammoPower = ammoPower + s.getammoPower();
        shieldPower = shieldPower + s.getshieldPower();
        
        if(fuelUnits+s.getfuelUnits()< MAXFUEL){
            fuelUnits= fuelUnits+s.getfuelUnits();
        }
    }
    
    boolean recieveWeapon(Weapon w){
        if(hangar != null){
            return hangar.addWeapon(w);
        }
        else{
            return false;
        }
    }
    
    Transformation setLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();
        int h = loot.getNHangars();
        if(h>0){
            Hangar hangar1 = dealer.nextHangar();
            receiveHangar(hangar1);
        }
        int nsupplies = loot.getNSupplies();
        
        SuppliesPackage supplies;
        
        for(int i = 0; i < nsupplies; i++){
            supplies = dealer.nextSuppliesPackage();
            recieveSupplies(supplies);
        }
        
        int nweapons;
        nweapons = loot.getNWeapons();
        Weapon w;
        
        for(int i = 0; i < nweapons; i++){
            w = dealer.nextWeapon();
            recieveWeapon(w);
        }
        
        int nshields;
        nshields = loot.getNShields();
        ShieldBooster s;
        
        for(int i = 0; i < nshields; i++){
            s = dealer.nextShieldBooster();
            recieveShieldBooster(s);
        }
        
        int nmedals;
        nmedals = loot.getNMedals();
        
        nMedals = nMedals + nmedals;
        
        
        Transformation trans;
        if(loot.getEfficient() == true){
            trans= Transformation.GETEFFICIENT;
        }
        else if(loot.spaceCity() == true){
            trans = Transformation.SPACECITY;
        }
        else{
            trans = Transformation.NOTRANSFORM;
        }
        
        return trans;
    }
    
    void setPendingDamage(Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }
    
    boolean validState(){
        return (pendingDamage == null || pendingDamage.hasNoeffect());
  
    }
    
    @Override
    public String toString(){
        return  "SpaceStation(\n" + 
                "\tammoPower = " + ammoPower + "\n" +
                "\tfuelUnits = " + fuelUnits + "\n" +
                "\tname = " + name + "\n" +
                "\tnMedals = " + nMedals + "\n" +
                "\tshieldPower = " + shieldPower + "\n" +
                "\tpendingDamage = " + pendingDamage + "\n" +
                "\tweapons = " + weapons + "\n" +
                "\tshieldBoosters = " + shieldBoosters + "\n" +
                "\tHangar = " + hangar + "\n" +
                ")";
    }

    
    SpaceStationToUI getUIversion() {
        return new SpaceStationToUI(this);
    }
    
}
