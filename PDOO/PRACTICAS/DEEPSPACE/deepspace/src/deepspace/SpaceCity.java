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
public class SpaceCity extends SpaceStation{
    
    private ArrayList<SpaceStation> collaborators;
    private SpaceStation base;

    public SpaceCity(SpaceStation base, ArrayList<SpaceStation> rest) {
        super(base);
        this.base = base;
        this.collaborators = new ArrayList<>(rest);
    }
    
    public ArrayList<SpaceStation> getCollaborators(){
        return collaborators;
    }
    
    @Override
    public float fire(){
        float factor = 1;
        for(int i =0; i < collaborators.size() ; i++){
            factor = factor + collaborators.get(i).fire();
        }
        
        return factor + base.fire();
    }
    
    @Override
    public float protection(){
        float factor = 1;
        for(int i =0; i < collaborators.size() ; i++){
            factor = factor + collaborators.get(i).protection();
        }
        
        return factor + base.protection();
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }
}
