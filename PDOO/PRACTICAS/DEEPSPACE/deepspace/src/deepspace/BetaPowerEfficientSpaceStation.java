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
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation {
    private final float EXTRAEFFICIENCY;
    Dice dice;
    
    public BetaPowerEfficientSpaceStation(SpaceStation station){
        super(station);
        EXTRAEFFICIENCY = 1.2f; 
    }
    
    @Override
    public float fire(){
        if(dice.extraEfficiency()){  
            return super.fire()*EXTRAEFFICIENCY;
        }
        else{
            return super.fire();
        }
        
    }
}
