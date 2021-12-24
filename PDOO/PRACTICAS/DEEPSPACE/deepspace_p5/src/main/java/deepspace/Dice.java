/**
 * @file Dice.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;
import java.util.Random;

/**
 * @brief Toma todas las decisiones que dependen del azar del juego.
 */
class Dice {
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    private final float EXTRAEFFICIENCYPROB;
    private Random generator;
    
    /**
     * @brief Constructor por defecto
     */
    Dice(){
        NHANGARSPROB = 0.25f;
        NSHIELDSPROB = 0.25f;
        NWEAPONSPROB = 0.33f;
        FIRSTSHOTPROB = 0.5f;
        EXTRAEFFICIENCYPROB = 0.8f;
        generator = new Random();
    }
    
    /**
     * @brief Determina el número de hangares que recibirá una estación espacial
     * al ser creada.
     */
    int initWithNHangars(){
        float prob;
        prob = generator.nextFloat();
        
        if(prob < NHANGARSPROB)
            return 0;
        else
            return 1;
    }
    
    /**
     * @brief Determina el número de armas que recibirá una estación espacial
     * al ser creada
     */
    int initWithNWeapons(){
        float prob;
        prob = generator.nextFloat();
        
        if(prob < NWEAPONSPROB)
            return 1;
        else if (prob < 2*NWEAPONSPROB)
            return 2;
        else
            return 3;
    }
    
    /**
     * @brief Determina el número de potenciadores de escudo que recibirá una
     * estación espacial al ser creada
     */
    int initWithNShields(){
        float prob;
        prob = generator.nextFloat();
        
        if(prob < NSHIELDSPROB)
            return 0;
        else
            return 1;
    }
    
    /**
     * @brief Determina el jugador (su indice) que iniciará la partida
     * @param nPlayers Numero de jugadores
     */
    int whoStarts(int nPlayers){
        int player;
        player = generator.nextInt(nPlayers);
        
        return player;
    }
    
    /**
     * @brief Determina quién (de los tipos de personajes del juego) dispara
     * primero en un combate: la estación espacial o la nave enemiga.
     */
    GameCharacter firstShot(){
        float prob;
        prob = generator.nextFloat();
        
        if(prob < FIRSTSHOTPROB)
            return GameCharacter.SPACESTATION;
        else
            return GameCharacter.ENEMYSTARSHIP;
    }
    
    /**
     * @brief Determina si la estación espacial se moverá para esquivar un disparo.
     * @param speed Velocidad máxima potencial
     */
    boolean spaceStationMoves(float speed){
        float prob;
        prob = generator.nextFloat();
        
        return prob < speed;
    }
    
    /**
     * @brief Determina si se produce un disparo mas eficiente
     * @return TRUE si se produce | FALSE en otro caso
     */
    public boolean extraEfficiency(){
        float prob;
        prob = generator.nextFloat();
        
        return prob < EXTRAEFFICIENCYPROB;
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    public String toString(int numP, float sp){
        String test;
        
        //CONTENT
        String initHangars = "- INITHANGARS: " + initWithNHangars() + " ";
        String initWeapons = "- INITWEAPONS: " + initWithNWeapons() + " ";
        String initShields = "- INITSHIELDS: " + initWithNShields() + "\n";
        String whoStart = "- WHOSTARTS: " + whoStarts(numP) + " ";
        String firstShot = "- FIRSTSHOT: " + firstShot() + " ";
        String spaceMov = "- STATIONMOVES: " + spaceStationMoves(sp) + "\n";
        
        test = initHangars + initWeapons + initShields + whoStart + firstShot + spaceMov;
        
        return test;
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String test;
        
        //CONTENT
        String initHangars = "- INITHANGARS: " + initWithNHangars() + " ";
        String initWeapons = "- INITWEAPONS: " + initWithNWeapons() + " ";
        String initShields = "- INITSHIELDS: " + initWithNShields() + " ";
        String firstShot = "- FIRSTSHOT: " + firstShot() + "\n";
        
        test = initHangars + initWeapons + initShields + firstShot;
        
        return test;
    }
}
