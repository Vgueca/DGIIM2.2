/**
 * @file GameUniverse.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;
import java.util.Random;

/**
 * @brief Representa la jugabilidad del juego
 */
public class GameUniverse {
    //ATRIBUTOS DE CLASE
    private static final int WIN = 10;
    
    //ATRIBUTOS DE INSTANCIA
    private int currentStationIndex;
    private int turns;
    private boolean haveSpaceCity;
    
    //ATRIBUTOS DE RELACION
    private GameStateController gameState;
    private EnemyStarShip currentEnemy;
    private Dice dice;
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations = new ArrayList<>();
    
    /**
     * @brief Constructor por defecto
     */
    public GameUniverse(){
        turns = 0;
        currentStationIndex = 0;
        gameState = new GameStateController();
        dice = new Dice();
    }
    
    /**
     * @brief Se realiza un combate entre la estación espacial y el enemigo que se reciben como parámetros 
     * @param station : Instancia SpaceStation
     * @param enemy : Instancia EnemyStarShip
     * @return Resultado de Combate
     */
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        GameCharacter ch = dice.firstShot();
        boolean enemyWins;
        float fire;
        ShotResult result;
        CombatResult combatResult = null;
        
        if(ch == GameCharacter.ENEMYSTARSHIP){
            fire = enemy.fire();
            result = station.receiveShot(fire);
            
            if(result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
                enemyWins = (result == ShotResult.RESIST);
            }
            else
                enemyWins = true;
        }
        else{
            fire = station.fire();
            result = enemy.receiveShot(fire);
            enemyWins = (result == ShotResult.RESIST);
        }
        
        if(enemyWins){
            float s = station.getSpeed();
            boolean moves = dice.spaceStationMoves(s);
            
            if(!moves){
                Damage damage = enemy.getDamage();
                station.setPendingDamage(damage);
                
                combatResult = CombatResult.ENEMYWINS;
            }
            else{
                station.move();
                combatResult = CombatResult.STATIONESCAPES;
            }
        }
        else{
            Loot aLoot = enemy.getLoot();
            Transformation transformation = station.setLoot(aLoot);
            
            if(transformation == Transformation.GETEFFICIENT){
                makeStationEfficient();
                combatResult = CombatResult.STATIONWINSANDCONVERTS;
            }
            if(transformation == Transformation.SPACECITY){
                createSpaceCity();
                combatResult = CombatResult.STATIONWINSANDCONVERTS;
            }
            if(transformation == Transformation.NOTRANSFORM){
                combatResult = CombatResult.STATIONWINS;
            }
        }
        
        gameState.next(turns, spaceStations.size());
        
        return combatResult;
    }
    
    /**
     * @brief Si la aplicación se encuentra en un estado en donde el combatir está permitido, 
     * se realiza un combate entre la estación espacial que tiene el turno y el enemigo actual.
     * @return Se devuelve el resultado del combate
     */
    public CombatResult combat(){
        GameState state = gameState.getState();
        if(state == GameState.INIT || state == GameState.BEFORECOMBAT){
            return combat(currentStation,currentEnemy);
        }
        else
            return CombatResult.NOCOMBAT;
    }
    
    /**
     * @brief Fija la referencia del hangar a null para indicar que no se dispone del mismo.
     */
    public void discardHangar(){
        if((gameState.getState() == GameState.INIT) || (gameState.getState() == GameState.AFTERCOMBAT))
            currentStation.discardHangar();
    }
    
    /**
     * @brief Si el juego esta en estado iniciado o despues de combate, se monta el arma
     * @param i : Indice del potenciador de escudo
     */
    public void discardShieldBooster(int i){
        if((gameState.getState() == GameState.INIT) || (gameState.getState() == GameState.AFTERCOMBAT))
            currentStation.discardShieldBooster(i);
    }
    
    /**
     * @brief Si el juego esta en estado iniciado o despues de combate, se descarta el potenciador de escudo del Hangar
     * @param i : Indice del potenciador de escudo
     */
    public void discardShieldBoosterInHangar(int i){
        if((gameState.getState() == GameState.INIT) || (gameState.getState() == GameState.AFTERCOMBAT))
            currentStation.discardShieldBoosterInHangar(i);
    }
    
    /**
     * @brief Si el juego esta en estado iniciado o despues de combate, se descarta el arma
     * @param i : Indice del arma
     */
    public void discardWeapon(int i){
        if((gameState.getState() == GameState.INIT) || (gameState.getState() == GameState.AFTERCOMBAT))
            currentStation.discardWeapon(i);
    }
    
    /**
     * @brief Si el juego esta en estado iniciado o despues de combate, se descarta el arma del Hangar
     * @param i : Indice de arma
     */
    public void discardWeaponInHangar(int i){
        if((gameState.getState() == GameState.INIT) || (gameState.getState() == GameState.AFTERCOMBAT))
            currentStation.discardWeaponInHangar(i);
    }
    
    /**
     * @brief Devuelve el estado del juego
     * @return gamestate.getState() 
     */
    public GameState getState(){
        return gameState.getState();
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (GameUniverse)
     * @return Objeto UI GameUniverse
     */
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(this.currentStation,this.currentEnemy);
    }
    
    /**
     * @brief Devuelve true si la estación espacial que tiene el turno ha llegado al número de medallas necesarias para ganar.
     * @return True en condicion anterior | False en otro caso
     */
    public boolean haveAWinner(){
        return currentStation.getNMedals() >= WIN;
    }
    
    /**
     * @brief Inicia una partida
     * @param names : Nombres de los jugadores
     */
    public void init(ArrayList<String> names){
        GameState state = gameState.getState();
        int turns = 0;
        if(state == GameState.CANNOTPLAY){
            CardDealer dealer = CardDealer.getInstance();
            for(int i=0; i<names.size(); i++){
                SuppliesPackage supplies = dealer.nextSuppliesPackage();
                SpaceStation station = new SpaceStation(names.get(i), supplies);
                
                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();
                
                Loot lo = new Loot(0,nw,ns,nh,0);
                
                station.setLoot(lo);
                
                spaceStations.add(station);
            }
            
            currentStationIndex = dice.whoStarts(names.size());
            currentStation = spaceStations.get(currentStationIndex);
            
            currentEnemy = dealer.nextEnemy();
            
            gameState.next(turns, spaceStations.size());
        }
    }
    
    /**
     * @brief Si el juego esta en estado iniciado o despues de combate, se monta el potenciador de escudo
     * @param i : indice de potenciador de escudo
     */
    public void mountShieldBooster(int i){
        if((gameState.getState() == GameState.INIT) || (gameState.getState() == GameState.AFTERCOMBAT))
            currentStation.mountShieldBooster(i);
    }
    
    /**
     * @brief Si el juego esta en estado iniciado o despues de combate, se monta el arma
     * @param i : indice de arma
     */
    public void mountWeapon(int i){
        if((gameState.getState() == GameState.INIT) || (gameState.getState() == GameState.AFTERCOMBAT))
            currentStation.mountWeapon(i);
    }
    
    /**
     * @brief Se comprueba que el jugador actual no tiene ningún daño pendiente de cumplir, en 
     * cuyo caso se realiza un cambio de turno al siguiente jugador con un nuevo enemigo con quien combatir
     * @return TRUE en las condiciones anteriores | FALSE en otro caso
     */
    public boolean nextTurn(){
        GameState state = gameState.getState();
        if(state == GameState.AFTERCOMBAT){
            boolean stationState = currentStation.validState();
            if(stationState){
                currentStationIndex = (currentStationIndex+1)%spaceStations.size();
                turns++;
                currentStation = spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                
                CardDealer dealer = CardDealer.getInstance();
                currentEnemy = dealer.nextEnemy();
                
                gameState.next(turns, spaceStations.size());
                
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * @brief Si el juego no dispone ya de una ciudad espacial (haveSpaceCity==false) convierte la estación espacial actual 
     * en una ciudad espacial usando como estación espacial base la actual y como colaboradoras el resto las estaciones espaciales.
     */
    private void createSpaceCity(){
       if(!haveSpaceCity){
           ArrayList<SpaceStation> stations = new ArrayList<>();
           for(int i=0; i<spaceStations.size(); i++)
               if(i != currentStationIndex)
                   stations.add(spaceStations.get(i));
           
           currentStation = new SpaceCity(currentStation, stations);
           spaceStations.remove(currentStationIndex);
           spaceStations.add(currentStationIndex, currentStation);
           haveSpaceCity = true;
       } 
    }
    
    /**
     * @brief Pregunta al dado si debe convertir la estación espacial actual en una estación eficiente o en una estación eficiente beta
     */
    private void makeStationEfficient(){
        currentStation = new PowerEfficientSpaceStation(currentStation);
        if(dice.extraEfficiency()){
            currentStation = new BetaPowerEfficientSpaceStation(currentStation);
        }
        
        spaceStations.remove(currentStationIndex);
        spaceStations.add(currentStationIndex, currentStation);
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String test;
        
        //TITLE
        String franja = "===========================================\n";
        String title = "   DEBUG TEST : INSTANCIA GAMEUNIVERSE   \n";
        
        //CONTENT
        String actualStationInd = ">> CURRENTSTATIONINDEX --> " + currentStationIndex + "\n";
        String turn = ">> TURNS --> " + turns + "\n";
        String gState = ">> GAMESTATE --> " + gameState + "\n";
        String actualEnemy = ">> CURRENTENEMY --> null\n";
        if(currentEnemy != null){
            actualEnemy = ">> CURRENTENEMY << \n\n" + currentEnemy.toString() + "\n\n";
        }
        String actualStation = ">> CURRENSTATION--> null\n";
        if(currentStation != null){
            actualStation = ">> CURRENTENEMY << \n\n" + currentStation.toString() + "\n\n";
        }
        String dicE = ">> DICE << \n\n" + dice.toString() + "\n\n";
        
        String spaceStats = ">> SPACESTATIONS << \n\n" + spaceStations.toString() + "\n\n";
        
        test = franja + title + franja + actualStationInd + turn + gState + actualEnemy + actualStation + dicE + spaceStats;
        
        
        return test;
    }
    
    //============================================================================================================================
    
    //METODOS DE TEST DE GUI
    public LootToUI dameUnLoot(){
        return new LootToUI(new Loot(1,2,3,4,5));
    }
    
    public WeaponToUI dameUnWeapon(){
        Weapon w = new Weapon("TEST-VIEW",WeaponType.LASER,20);
        return new WeaponToUI(w);
    }
    
    public ShieldToUI dameUnShield(){
        ShieldBooster sb = new ShieldBooster("TEST-VIEW",4.0f,100);
        return new ShieldToUI(sb);
    }
    
    public HangarToUI dameUnHangar(){
        Random random = new Random();
        int value = random.nextInt(5)+1;
        
        Hangar h = new Hangar(value);
        
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        h.addWeapon(CardDealer.getInstance().nextWeapon());
        h.addShieldBooster(CardDealer.getInstance().nextShieldBooster());
        
        return new HangarToUI(h);
        
    }
    
    public DamageToUI dameUnDamage(){
        Random random = new Random();
        int dice = random.nextInt(6)+1;
        
        if(dice <= 3){
            NumericDamage np = new NumericDamage(1,3);
            return new NumericDamageToUI(np);
        }
        else{
            ArrayList<WeaponType> test = new ArrayList<>();
            test.add(WeaponType.LASER);
            test.add(WeaponType.MISSILE);
            test.add(WeaponType.PLASMA);
            
            SpecificDamage sp = new SpecificDamage(test,5);
            return new SpecificDamageToUI(sp);
        }
    }
    
    public EnemyToUI dameUnEnemy(){
        EnemyStarShip enemy = CardDealer.getInstance().nextEnemy();
        
        return new EnemyToUI(enemy);
    }
    
    public SpaceStationToUI dameUnStation(){
        Random random = new Random();
        SuppliesPackage supplies = CardDealer.getInstance().nextSuppliesPackage();
        SpaceStation s = new SpaceStation("DanielsP",supplies);
        
        int value = random.nextInt(5)+1;
        s.receiveHangar(new Hangar(value));
        
        value = random.nextInt(5)+1;
        for(int i=0; i<value; i++){
            s.receiveWeapon(CardDealer.getInstance().nextWeapon());
        }
        value = random.nextInt(5)+1;
        for(int i=0; i<value; i++){
            s.receiveShieldBooster(CardDealer.getInstance().nextShieldBooster());
        }
        
        value = random.nextInt(5)+1;
        for(int i=0; i<value; i++){
            s.mountWeapon(i);
        }
        value = random.nextInt(5)+1;
        for(int i=0; i<value; i++){
            s.mountShieldBooster(i);
        }
        
        value = random.nextInt(5)+1;
        if(value <= 3){
            s.setPendingDamage(new NumericDamage(4,5));
        }
        
        return new SpaceStationToUI(s);
    }
}
