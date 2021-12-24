/**
 * @file Controller.java
 * @author Profesor
 * @brief Controlador del Juego
 * @version 5.0 FINAL
 */
package controller;

import View.DeepSpaceView;
import deepspace.CombatResult;
import deepspace.GameState;
import deepspace.GameUniverse;
import deepspace.GameUniverseToUI;
import java.util.ArrayList;
import java.util.Collections;

//==============================================================================

//IMPORT TO TEST GUI
import deepspace.LootToUI;
import deepspace.WeaponToUI;
import deepspace.ShieldToUI;
import deepspace.DamageToUI;
import deepspace.EnemyToUI;
import deepspace.SpaceStationToUI;
import deepspace.HangarToUI;

//==============================================================================

/**
 * @brief Esta instancia Singleton representa un controlador que permite conectar
 * la interfaz visible para el usuario (ya sea gráfica o de texto) con el modelo
 * (en este caso GameUniverse).
 * 
 * Para ello se disponen una serie de métodos que permiten controlar las diferentes
 * funcionalidades básicas del juego como las descritas en el siguiente enumerado:
 * 
 * FUNCIONES DISPONIBLES EN EL CONTROLADOR:
 *  -> OBTENER INSTANCIA : getInstance()
 *  -> ASOCIAR MODELO Y VISTA DE USUARIO: setModelView(GameUniverse,DeepSpaceView)
 *  -> INICIAR PARTIDA: start()
 *  -> FINALIZAR PARTIDA: finish(int)
 *  -> SIGUIENTE TURNO: nextTurn()
 *  -> COMBATIR: combat()
 *  -> OBTENER ESTADO ACTUAL DEL JUEGO: getState()
 *  -> OBTENER INSTANCIA TOUI DEL JUEGO: getUIversion()
 *  -> MONTAR OBJETOS: mount(ArrayList<Integer>, ArrayList<Integer>)
 *  -> DESCARTAR OBJETOS: discard(int, ArrayList<Integer>, ArrayList<Integer>)
 *  -> DESCARTAR HANGAR: discardHangar()
 */
public class Controller {
    private static final Controller instance = new Controller();
    
    public static final int WEAPON = 0x1;
    public static final int SHIELD = 0x2;
    public static final int HANGAR = 0x4;
    private GameUniverse game;
    private DeepSpaceView view;
    
    /**
     * @brief Constructor por defecto de la clase
     */
    private Controller () {}
    
    /**
     * @brief Obtiene la instancia de Controller
     * @return instance (Singleton)
     */
    public static Controller getInstance () {
      return instance;
    }
    
    /**
     * @brief Asocia una instancia de Juego y una instancia de Vista al controlador
     * @param aGame : Instancia GameUniverse
     * @param aView : Instancia DeepSpaceView
     */
    public void setModelView (GameUniverse aGame, DeepSpaceView aView) {
      game = aGame;
      view = aView;
    }
    
    /**
     * @brief Inicia una nueva partida
     */
    public void start() {
        game.init(view.readNamePlayers());
        view.updateView();
        view.showView();
    }
    
    /**
     * @brief Finaliza la partida y detiene la ejecución del programa
     * @param i : Flag de salida con estado final de ejecución (normalmente 0)
     */
    public void finish (int i) {
        if (view.confirmExitMessage()) {
          System.exit(i);
        }
    }
    
    /**
     * @brief Comprueba si se puede pasar al siguiente jugador
     * @return TRUE si se puede pasar de turno | FALSE en otro caso
     */
    public boolean nextTurn () {
      boolean nextTurnAllowed = game.nextTurn();
      if (!nextTurnAllowed) {
        view.nextTurnNotAllowedMessage();
      }
      return nextTurnAllowed;
    }
    
    /**
     * @brief Produce el combate entre una nave y un enemigo
     */
    public void combat () {
        CombatResult result = game.combat();
        switch (result) {
            case ENEMYWINS :
              view.lostCombatMessage();
              break;
            case STATIONESCAPES :
              view.escapeMessage();
              break;
            case STATIONWINS :
              view.wonCombatMessage();
              if (game.haveAWinner()) {
                  view.wonGameMessage();
                  System.exit (0);
              }
              break;
            case STATIONWINSANDCONVERTS :
              view.conversionMessage();
              break;
            case NOCOMBAT :
              view.noCombatMessage();
              break;
        }
    }
    
    /**
     * @brief Obtiene el estado actual del juego
     * @return state
     */
    public GameState getState() {
      return game.getState();
    }
    
    /**
     * @brief Obtiene una instancia ToUI de GameUniverse
     * @return GameUniverseToUI
     */
    public GameUniverseToUI getUIversion() {
      return game.getUIversion();
    }
    
    /**
     * @brief Invierte un array de enteros
     * @param array : Array de enteros a invertir
     */
    private void invertArray (ArrayList<Integer> array) {
      int i, n;
      
      n = array.size();
      for (i = 0; i < n/2; i++)
        Collections.swap(array, i, n-i-1);
    }
    
    /**
     * @brief Monta los objetos que se pasan como parámetro
     * @param weapons : Array de índices de los weapons a montar
     * @param shields : Array de índices de los shieldBoosters a montar
     */
    public void mount (ArrayList<Integer> weapons, ArrayList<Integer> shields) {
      invertArray (weapons);
      invertArray (shields);
      
      for (int i : weapons) {
        game.mountWeapon(i);
      }
      for (int i : shields) {
        game.mountShieldBooster(i);
      }
      view.updateView();
    }
    
    /**
     * @brief Descarta los objetos pasados como parámetro
     * @param places : Flag que permite indicar de dónde se descartan: WEAPON | SHIELD | HANGAR
     * @param weapons : Array de índices de los weapons a descartar
     * @param shields : Array de índices de los shieldBoosters a descartar
     */
    public void discard (int places, ArrayList<Integer> weapons, ArrayList<Integer> shields) {
      invertArray(weapons);
      invertArray(shields);
      
      if ((places & WEAPON) == WEAPON) {
        for (int i : weapons) {
          game.discardWeapon(i);
        }
      } else if ((places & SHIELD) == SHIELD) {
        for (int i : shields) {
          game.discardShieldBooster(i);
        }
      } else if((places & HANGAR) == HANGAR) {
        for (int i : weapons) {
          game.discardWeaponInHangar(i);
        }
        for (int i : shields) {
          game.discardShieldBoosterInHangar(i);
        }
      }
    }
    
    /**
     * @brief Descarta el hangar del jugador actual
     */
    public void discardHangar () {
        game.discardHangar();
    }
    
    //==============================================================================
    
    //FUNCIONES TEST GUI
    public LootToUI dameUnLoot(){
        return game.dameUnLoot();
    }
    
    public WeaponToUI dameUnWeapon(){
        return game.dameUnWeapon();
    }
    
    public ShieldToUI dameUnShield(){
        return game.dameUnShield();
    }
    
    public HangarToUI dameUnHangar(){
        return game.dameUnHangar();
    }
    
    public DamageToUI dameUnDamage(){
        return game.dameUnDamage();
    }
    
    public EnemyToUI dameUnEnemy(){
        return game.dameUnEnemy();
    }
    
    public SpaceStationToUI dameUnStation(){
        return game.dameUnStation();
    }
}
