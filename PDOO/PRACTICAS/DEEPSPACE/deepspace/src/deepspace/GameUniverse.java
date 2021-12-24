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
public class GameUniverse {
    private static final int WIN = 10;
    
    private int currentStationIndex;
    private int turns;
    
    private Dice dice;
    private EnemyStarShip currentEnemy;
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations;
    private GameStateController gameState;
            
    private boolean haveSpaceCity;
            
    public GameUniverse(){
        gameState = new GameStateController();
        turns = 0;
        dice = new Dice();
    }
    
    
    
    
    
    private void createSpaceCity(){
        if(haveSpaceCity == false){
            ArrayList<SpaceStation> collaborators = new ArrayList<>(spaceStations);
            collaborators.remove(currentStation);
            currentStation =  new SpaceCity(currentStation, collaborators);
            spaceStations.set(currentStationIndex, currentStation);
            haveSpaceCity = true;
        }
    }
    
    public boolean gethaveSpaceCity(){
        return haveSpaceCity;
    }
    
    private void makeStationEfficient(){
        if(dice.extraEfficiency()){
            currentStation = new BetaPowerEfficientSpaceStation(currentStation);
            spaceStations.set(currentStationIndex, currentStation);
        }
        else{
            currentStation = new PowerEfficientSpaceStation(currentStation);
            spaceStations.set(currentStationIndex, currentStation);
        }
    }
    
    
    
    
    
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        GameCharacter ch = dice.firstShot();
        boolean enemyWins;
        CombatResult combatResult;
        if(ch == GameCharacter.ENEMYSTARSHIP){
            float fire = enemy.fire();
            ShotResult result = station.receiveShot(fire);
            if(result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
                enemyWins = (result == ShotResult.RESIST);
            }
            else{
                enemyWins = true;
            }
        }
        else{
            float fire = station.fire();
            ShotResult result = enemy.receiveShot(fire);
            
            enemyWins = (result == ShotResult.RESIST);
        }
        
        if(enemyWins){
            float s = station.getSpeed();
            boolean moves = dice.spaceStationMoves(s);
            
            if(!moves){
                Damage damage = enemy.getDamage();
                station.setPendingDamage(damage);
                combatResult= CombatResult.ENEMYWINS;
            }
            else{
                station.move();
                combatResult = CombatResult.STATIONESCAPES;
            }
        }
        else{
            Loot aLoot = enemy.getLoot();
            Transformation trans;
            trans = station.setLoot(aLoot);
            if(trans != Transformation.NOTRANSFORM){
                combatResult = CombatResult.STATIONWINSANDCONVERTS;
                if(trans == Transformation.GETEFFICIENT){
                   makeStationEfficient();
                }
                else{
                    createSpaceCity();
                }
            }
            else{
                combatResult = CombatResult.STATIONWINS;
            }
        }
        
        gameState.next(turns, spaceStations.size());
        
        return combatResult;
    }

    public CombatResult combat(){
        
        GameState state = gameState.getState();
        
        if(state == GameState.BEFORECOMBAT || state == GameState.INIT){
               return combat(currentStation, currentEnemy);
        }
        else{
            return CombatResult.NOCOMBAT;
        }
        
        
    }
    
    public void discardHangar(){
        if(gameState.getState()== GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardHangar();
        }
    }
    
    public void discardShieldBooster(int i ){
        if(gameState.getState()== GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBooster(i);
        }
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(gameState.getState()== GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBoosterInHangar(i);
        }
    }
    
    public void discardWeapon(int i ){
        if(gameState.getState()== GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeapon(i);
        }
    }
    
    public void discardWeaponInHangar(int i ){
       if(gameState.getState()== GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeaponInHangar(i);
        }
    }
    
    public GameState getState(){
       return gameState.getState();
    }
    
    public boolean haveAWinner() {
        return (currentStation.getNMedals()== WIN);
    }
    
    public void init(ArrayList<String> names){
        GameState state = getState();
        if( state == GameState.CANNOTPLAY){
            spaceStations = new ArrayList<>();
            CardDealer dealer = CardDealer.getInstance();
            int i =0, size = names.size();
            
            while(i<size){
                SuppliesPackage supplies = dealer.nextSuppliesPackage();
                SpaceStation s_i = new SpaceStation(names.get(i),supplies);
                spaceStations.add(s_i);
                
                int nh, nw, ns;
                nh = dice.initWithNHangars();
                nw = dice.initWithNWeapons();
                ns = dice.initWithNShields();
                
                Loot lo = new Loot(0, nw, ns, nh, 0);
                
                s_i.setLoot(lo);
                
                i++;
            }
            
            currentStationIndex = dice.whoStarts(size);
            currentStation= spaceStations.get(currentStationIndex);
            currentEnemy = dealer.nextEnemy();
            
            gameState.next(turns, size);
        }
    }
    
    public void mountShieldBooster(int i ){
        if(gameState.getState()== GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.mountShieldBooster(i);
        }
    }
    public void mountWeapon(int i ){
        if(gameState.getState()== GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.mountWeapon(i);
        }
    }
    public boolean nextTurn(){
        GameState state = gameState.getState();
        if(state==GameState.AFTERCOMBAT){
           boolean stationState = currentStation.validState();
           
           if(stationState){
               currentStationIndex=(currentStationIndex+1)%spaceStations.size();
               turns++;
               
               currentStation = spaceStations.get(currentStationIndex);
               currentStation.cleanUpMountedItems();
               CardDealer dealer = CardDealer.getInstance();
               currentEnemy = dealer.nextEnemy();
               gameState.next(turns, spaceStations.size());
               return true;
           }
           else{
               return false;
           }
       }
       else{
           return false;
       }
    }
    
    
    public GameUniverseToUI getUIversion() {
        return new GameUniverseToUI(currentStation, currentEnemy); 
    }
    
    @Override
    public String toString() {
        return  "GameUniverse(\n" +
                "\tcurrentStationIndex = " + currentStationIndex + "\n" +
                "\tcurrentStation = " + currentStation + "\n" +
                "\tcurrentEnemy = " + currentEnemy + "\n" +
                "\tturns = " + turns + "\n" +
                "\tdice = " + dice + "\n" +
                "\tgameState = " + gameState + "\n" +
                "\tspaceStations = " + spaceStations + "\n" +
                "\tWIN = " + WIN + "\n" +
                ")";
    }

}
