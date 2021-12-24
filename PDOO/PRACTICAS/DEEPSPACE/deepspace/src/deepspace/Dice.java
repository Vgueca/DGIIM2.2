package deepspace;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
/**
 *
 * @author valenting
 */
public class Dice {
    ;
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    private final float EXTRAEFFICIENCYPROB;
    
    private Random generator = new Random();
    
    Dice(){
        generator.nextFloat();
        NHANGARSPROB=0.25f;
        NSHIELDSPROB=0.25f;
        NWEAPONSPROB=0.33f;
        FIRSTSHOTPROB=0.5f;
        EXTRAEFFICIENCYPROB = 0.8f;
    }
    int initWithNHangars(){
        float random = generator.nextFloat();
        if(random <= NHANGARSPROB){
            return 0;
        }
        else{
            return 1;
        }
    }
    int initWithNWeapons(){
        float random = generator.nextFloat();
        if(random<=NWEAPONSPROB){
            return 1;
        }
        else if(random>NWEAPONSPROB && random <= 2*NWEAPONSPROB){
            return  2;
        }
        else{
            return 3;
        }
    }
    
    int initWithNShields(){
        float random = generator.nextFloat();
        if(random <= NSHIELDSPROB){
            return 0;
        }
        else{
            return 1;
        }
    }
    
    int whoStarts(int nPlayers){
        int playerindex = (int) (generator.nextFloat()* nPlayers + 0);
        
        return playerindex;
    }
    
    GameCharacter firstShot(){
        float random = generator.nextFloat();
    
        if(random <= FIRSTSHOTPROB){
            return GameCharacter.ENEMYSTARSHIP;
        }
        else{
            return GameCharacter.SPACESTATION;
        }
    }
    
    boolean spaceStationMoves(float speed){
        float random = generator.nextFloat();
        
        if(random <= speed ){
            return true;
        }
        else{
            return false;
        }
    }
    
    boolean extraEfficiency(){
        float random = generator.nextFloat();
        if (random <= EXTRAEFFICIENCYPROB){
            return false;
        }
        else{
            return true;
        }
    }
}


    