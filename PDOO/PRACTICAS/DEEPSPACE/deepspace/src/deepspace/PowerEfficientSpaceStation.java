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
public class PowerEfficientSpaceStation extends SpaceStation {
    private final float EFFICIENCYFACTOR;
    
    public PowerEfficientSpaceStation(SpaceStation station){
        super(station);
        EFFICIENCYFACTOR = 1.1f;
    }
    
    
    @Override
    public float fire(){
        return super.fire()*EFFICIENCYFACTOR;
    }
    @Override
    public float protection(){
        return super.protection()*EFFICIENCYFACTOR;
        
    }
    
    @Override
    Transformation setLoot(Loot loot){
        Transformation trans;
        
        trans = super.setLoot(loot);
        
        if(trans == Transformation.SPACECITY){
            trans = Transformation.NOTRANSFORM;
        }
        
        return trans;
    }
}
