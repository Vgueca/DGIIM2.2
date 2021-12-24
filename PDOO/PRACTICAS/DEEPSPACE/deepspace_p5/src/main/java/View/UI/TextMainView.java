/**
 * @file TextMainView.java
 * @author Profesor
 * @brief Interfaz de Texto para el usuario
 * @version 5.0 FINAL
 */
package View.UI;

import View.DeepSpaceView;
import controller.Controller;
import deepspace.DamageToUI;
import deepspace.EnemyToUI;
import deepspace.GameState;
import deepspace.GameUniverseToUI;
import deepspace.HangarToUI;
import deepspace.LootToUI;
import deepspace.ShieldToUI;
import deepspace.SpaceStationToUI;
import deepspace.WeaponToUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @brief Esta clase representa a la interfaz que verá el usuario (UI) en formato
 * de texto, a través de varias salidas de tipo texto y con el menú de opciones,
 * el usuario podrá controlar las diferentes acciones del juego.
 */
public class TextMainView implements DeepSpaceView {
  
  static private TextMainView instance = null;
  
  /**
   * @breif Constructor por defecto de la clase
   */
  private TextMainView() {}
  
  /**
   * @brief Obtiene la interfaz de texto
   * @return instance (Singleton)
   */
  static public TextMainView getInstance() {
    if (instance == null) {
      instance = new TextMainView();
    }
    return instance;
  }
  
  /**
   * @brief Enumerado que permite elegir entre los diferentes elementos del juego
   * Consta de WEAPON | SHIELD | HANGAR
   */
  static private enum Element {
      WEAPON ("Arma"), 
      SHIELD ("Escudo"), 
      HANGAR ("Hangar");

      final public String text;
      
      /**
       * @brief Constructor con parámetro del enumerado
       * @param t : String al que hace referencia el objeto del enumerado
       */
      Element (String t) { text = t; }
  };
  
  /**
   * @brief Enumerado que permite elegir los dos tipos de operaciones principales
   * Consta de MOUNT | DISCARD
   */
  static private enum Operation {
      MOUNT ("Montar"), 
      DISCARD ("Descartar");

      final public String text;
      
      /**
       * @brief Constructor con parámetro del enumerado
       * @param t : String al que hace referencia el objeto del enumerado
       */
      Operation (String t) { text = t; }
  };
  
  
  private static final Scanner in = new Scanner(System.in);
  
  private GameUniverseToUI gameUI;
  private GameState state;

  private final String mainSeparator = "\n ******* ******* ******* ******* ******* ******* ******* \n";
  private final String separator = "\n ------- ------- ------- ------- ------- ------- ------- \n";
  
  /**
   * @brief Genera una pausa entre dos acciones transcurridas en el juego
   * @param s : Título de la pausa
   */
  private void pause (String s) {
      System.out.print (mainSeparator);
      System.out.print (mainSeparator);
      System.out.println (s);
      System.out.print (mainSeparator);
      System.out.print (mainSeparator);
      System.out.print ("\n (pulsa  ENTER  para continuar) ");
      in.nextLine();
  }
  
  /**
   * @brief Procesa una instrucción del menu de opciones
   * @param command : Objeto del enumerado Command
   */
  private void processCommand (Command command) { // Devolvia  Command
    switch (command) {
      case EXIT :
        Controller.getInstance().finish(0);
        break;
      case SHOWSTATION :
        showMessageln (showStation (gameUI.getCurrentStation()));
        break;
      case SHOWENEMY :
        showMessageln (showEnemy (gameUI.getCurrentEnemy()));
        break;
      case MOUNTWEAPONS :
        mountDiscardFromHangar (Operation.MOUNT, Element.WEAPON);
        break;
      case MOUNTSHIELDS :
        mountDiscardFromHangar (Operation.MOUNT, Element.SHIELD);
        break;
      case DISCARDWEAPONSINHANGAR :
        mountDiscardFromHangar (Operation.DISCARD, Element.WEAPON);
        break;
      case DISCARDSHIELDSINHANGAR :
        mountDiscardFromHangar (Operation.DISCARD, Element.SHIELD);
        break;
      case DISCARDHANGAR :
        Controller.getInstance().discardHangar();
        pause ("\n ******* Hangar Completo Descartado ******* ");
        break;
      case DISCARDWEAPONS :
        discardMountedElements (Element.WEAPON);
        break;
      case DISCARDSHIELDS :
        discardMountedElements (Element.SHIELD);
        break;
      case COMBAT :
        Controller.getInstance().combat();
        break;
      case NEXTTURN :
        Controller.getInstance().nextTurn();
        break;
    }
  }
  
  /**
   * @brief Lee un entero y comprueba que se ha introducido un parametro correcto
   * @param message : Mensaje a mostrar al usuario
   * @param min : Valor minimo posible de elección
   * @param max : Valor máximo posible de elección
   * @return numero introducido
   */
  private int readInt (String message, int min, int max) { 
    //método para comprobar que se introduce un entero correcto
    boolean valid;
    String input;
    int number = -1;
    do {
      valid = true;
      showMessage (message);
      input = in.nextLine();
      try {  
        number = Integer.parseInt(input);
        if (number<min || number>max) { // No es un entero entre los válidos
          showMessageln ("\nEl numero debe estar entre " + min + " y " + max);
          valid = false;
        }
      } catch (NumberFormatException e) { // No se ha introducido un entero
        showMessageln("\nDebes introducir un numero.");
        valid = false;  
      }
      if (!valid) {
        showMessage("\n\nInténtalo de nuevo.\n\n");
      }
    } while (!valid);
    return number;
  }

  /**
   * @brief Actualiza la vista de texto
   */
  @Override
  public void updateView() {
    gameUI = Controller.getInstance().getUIversion();
    state = Controller.getInstance().getState();
  }
  
  /**
   * @brief Muestra la vista de texto al usuario
   */
  @Override
  public void showView() {
    while (true) {// Hasta que se elija en el menú  Salir
      updateView();
      Command command = Command.EXIT;
      switch (state) {
        case INIT :
          command = getCommandInit();
          break;
        case BEFORECOMBAT :
          command = getCommandBeforeCombat();
          break;
        case AFTERCOMBAT :
          command = getCommandAfterCombat();
          break;
      }
      processCommand (command);
    }
  }

  /**
   * @brief Lee el número de jugadores que hay en total
   * @return ArrayList con los nombres de los jugadores
   */
  @Override
  public ArrayList<String> readNamePlayers() {
    ArrayList<String> names = new ArrayList<>();
    int nPlayers = readInt ("\n¿Cuántos jugadores participan (2-4)? ",2,4);
    for (int i = 0; i < nPlayers; i++) {
      showMessage ("Escribe el nombre del jugador " + (i+1) + ": ");
      names.add(in.nextLine());
    }
    return names;
  }
  
  /**
   * @brief Advierte al usuario si quiere salir del juego
   * @return TRUE si el usuario decide salir | FALSE en otro caso
   */
  @Override
  public boolean confirmExitMessage() {
    showMessage ("¿Estás segur@ que deseas salir [s/N]? ");
    String fullInput = in.nextLine();
    if (!fullInput.isEmpty()) {
        char input = fullInput.charAt(0);
        if (input == 's' || input == 'S') {
          return true;
        }
    }
    return false;
  }
  
  /**
   * @brief Muestra el menú de opciones
   * @param message : Mensaje a mostrar al usuario
   * @param menu : Menú de opciones disponibles a elegir
   * @return Command Option
   */
  private Command manageMenu (String message, Command[] menu) {
    HashMap <Integer, Command> menuCheck = new HashMap<>(); // Para comprobar que se hace una selección válida
    boolean validInput;
    String capture;
    int option;

    for (Command c : menu) {
      menuCheck.put (c.menu, c);
    }
    do { // Until a valid selection
      validInput = true;
      option = Command.GOBACK.menu;
      System.out.println (separator);
      System.out.println ("**** " + message + " ****\n");
      for (Command c : menu) { 
        System.out.println (String.format ("%3d",c.menu) + " : " + c.text);
      } 
      System.out.print ("\n Elige una opción: ");
      capture = in.nextLine();
      try {
        option = Integer.valueOf(capture);
        if (! menuCheck.containsKey(option)) { // It's not a valid integer
          validInput = false;
        }
      } catch (NumberFormatException e) { // It's not an integer
        validInput = false;
      }
      if (!validInput) {
        inputErrorMessage ();
      }
    } while (! validInput);
    return (menuCheck.get (option));    
  }
  
  /**
   * @brief Descarta elementos del hangar
   * @param operation : Objeto del enumerado Operation
   * @param element : Objeto del enumerado Element
   */
  private void mountDiscardFromHangar (Operation operation, Element element) {
      int howMany;
      int option = Command.GOBACK.menu;
      ArrayList<Integer> elements = new ArrayList<>();
      ArrayList<Integer> noElements = new ArrayList<>();
      
      do {   // Choice and mount weapons or shields until go back
        howMany = showHangarToMountDiscard (operation, element); 
        option = getChoice (howMany);
        elements.clear();
        if (option != Command.ERRORINPUT.menu) {
          elements.add(option);
        }
        switch (element) {
            case WEAPON :
              if (operation == Operation.MOUNT)
                  Controller.getInstance().mount(elements,noElements);
              else
                  Controller.getInstance().discard(Controller.HANGAR, elements, noElements);
              break;
            case SHIELD :
              if (operation == Operation.MOUNT)
                  Controller.getInstance().mount (noElements,elements);
              else
                  Controller.getInstance().discard(Controller.HANGAR, noElements, elements);
              break;
        }
        updateView();
      } while (option != Command.GOBACK.menu);
    }
  
  /**
   * @brief Muestra al usuario que no se puede pasar de turno
   */
  @Override
  public void nextTurnNotAllowedMessage() {
    showMessageln ("\n No puedes avanzar de turno, no has cumplido tu castigo");
  }
  
  /**
   * @brief Pide un entero para después realizar una de las operaciones disponibles
   * @param howMany : Flag que permite ver las diferentes opciones
   * @return Opción realizada
   */
  private int getChoice (int howMany) {
    boolean validInput;
    String capture;
    int option;
    
    validInput = true;
    option = Command.GOBACK.menu;
    System.out.print ("\n Elige: ");
    capture = in.nextLine();
    try {
      option = Integer.valueOf(capture);
      if (option < Command.GOBACK.menu || option > howMany) { // no se ha escrito un entero en el rango permitido
        validInput = false;
      }
    } catch (NumberFormatException e) { // no se ha escrito un entero
      validInput = false;
    }
    if (! validInput) {
      inputErrorMessage ();
      return Command.ERRORINPUT.menu;
    }
    return option;
  }
  
  /**
   * @brief Procesa las diferentes opciones del menú tras un combate
   * @return Opción del menú realizada
   */
  private Command getCommandAfterCombat () {
    Command commands[] = { Command.SHOWSTATION, 
          Command.MOUNTWEAPONS, Command.MOUNTSHIELDS, 
          Command.DISCARDWEAPONS, Command.DISCARDSHIELDS,
          Command.DISCARDWEAPONSINHANGAR, Command.DISCARDSHIELDSINHANGAR,
          Command.DISCARDHANGAR, 
          Command.SHOWENEMY, Command.NEXTTURN, Command.EXIT
      };
      return manageMenu (gameUI.getCurrentStation().getName() + ",  puedes Reorganizar tu Armamento antes de pasar de turno.\n Opciones disponibles", commands);
  }
  
  /**
   * @brief Procesa las diferentes opciones del menú antes de un combate
   * @return Opción del menú realizada
   */
  private Command getCommandBeforeCombat() {
      Command commands[] = { Command.SHOWSTATION, Command.COMBAT, Command.EXIT };
      return manageMenu (gameUI.getCurrentStation().getName() + ",  estás en un punto de no retorno.\n Solo te queda Combatir.", commands);
  }
  
  /**
   * @brief Procesa las diferentes opciones del menú tras iniciar la partida
   * @return Opción del menú realizada
   */
  private Command getCommandInit() {
    Command commands[] = { 
      Command.SHOWSTATION, Command.MOUNTWEAPONS,Command.MOUNTSHIELDS, 
      Command.COMBAT, Command.EXIT
    };
    return manageMenu ("Bienvenido  " + gameUI.getCurrentStation().getName() + 
              ",  es tu primera vez.\n Organiza tu Armamento para el Combate.\n --- Opciones disponibles", commands);
  }
  
  /**
   * @brief Mensaje de error tras no hacer una selección válida
   */
  private void inputErrorMessage () {
    System.out.println ("\n\n ¡¡¡ E R R O R !!! \n\n Selección errónea. Inténtalo de nuevo.\n\n");    
  }
  
  /**
   * @brief Mensaje de pérdida de un combate
   */
  @Override
  public void lostCombatMessage() {
    showMessageln ("Has PERDIDO el combate. \tCumple tu castigo.");
  }

  /**
   * @brief Descarta, entre los objetos montados, el elemento pasado como parámetro
   * @param element : Elemento a descartar
   */
  private void discardMountedElements (Element element) {
      int howMany = 0;
      int option = Command.GOBACK.menu;
      ArrayList<Integer> elements = new ArrayList<>();
      ArrayList<Integer> noElements = new ArrayList<>();
      
      do {   // Choice and discard weapons or shields until go back
        System.out.println (separator);
        System.out.println ("Elige un " + element.text + " para Descartar");
        System.out.println ("\n" + String.format ("%3d",Command.GOBACK.menu) + " : " + Command.GOBACK.text + "\n");
        switch (element) {
            case WEAPON :
                showMessageln (showWeapons (gameUI.getCurrentStation().getWeapons(), true));
                howMany = gameUI.getCurrentStation().getWeapons().size();
                break;
            case SHIELD :
                showMessageln (showShields (gameUI.getCurrentStation().getShieldBoosters(), true));
                howMany = gameUI.getCurrentStation().getShieldBoosters().size();
                break;
        }
        option = getChoice (howMany);
        elements.clear();
        if (option != Command.ERRORINPUT.menu) {
          elements.add(option);
        }
        switch (element) {
            case WEAPON :
              Controller.getInstance().discard(Controller.WEAPON, elements, noElements);
              break;
            case SHIELD :
              Controller.getInstance().discard(Controller.SHIELD, noElements, elements);
              break;
        }
        updateView();
    } while (option != Command.GOBACK.menu);
  }
  
  /**
   * @brief Mensaje de huida de la estación espacial
   */
  @Override
  public void escapeMessage() {
    showMessageln("Has logrado escapar. \tEres una Gallina Espacial.");
  }
  
  /**
   * @brief Mensaje de victoria de la estación espacial
   */
  @Override
  public void wonCombatMessage() {
    showMessageln ("Has GANADO el combate. \tDisfruta de tu botín.");
  }
  
  /**
   * @brief Mensaje de victoria del juego por parte del jugador actual
   */
  @Override
  public void wonGameMessage() {
    showMessageln ("\n\tHAS GANADO LA PARTIDA");
  }
  
  /**
   * @brief Mensaje de conversión del jugador a una ciudad espacial o a una estación eficiente
   */
  @Override
  public void conversionMessage() {
    if (gameUI.getCurrentEnemy().getLoot().isGetEfficient())
        showMessageln("Has GANADO el combate. \nAdemás te has CONVERTIDO en una estación EFICIENTE. \nDisfruta de tu botín");
    else
        showMessageln("Has GANADO el combate. \nAdemás te has CONVERTIDO en una CIUDAD ESPACIAL. \nDisfruta de tu botín");
  }
  
  /**
   * @brief Mensaje de combate no disponible
   */
  @Override
  public void noCombatMessage() {
    showMessageln("No puedes combatir en este momento");
  }

  /**
   * @brief Se muestra por pantalla la instancia de la estación actual
   * @param station : Objeto SpaceStationToUI actual
   * @return Instancia string con la información de la estación actual
   */
  private String showStation (SpaceStationToUI station) {
    String out = "";

    out += mainSeparator + "\n";
    out += " ***** Información de la  Estación Espacial Actual *****\n";
    out += "       -------------------------------------------\n";
    out += "Nombre ............ : " + station.getName() + "\n";
    out += "Potencia de fuego . : " + station.getAmmoPower() + "\n";
    out += "Potencia de defensa : " + station.getShieldPower() + "\n";
    out += "Medallas .......... : " + station.getnMedals() + "\n";
    out += "Armas montadas : \n";
    String tmp = showWeapons (station.getWeapons(), false);
    if ("".equals(tmp))
        out += "   Ninguna \n";
    else
        out += tmp;
    out += "Potenciadores de Escudos montados : \n";
    tmp = showShields (station.getShieldBoosters(), false);
    if ("".equals(tmp))
        out += "   Ninguno \n";
    else
        out += tmp;
    out += showHangar (station.getHangar());
    out += "Castigo pendiente: ";
    out += showDamage (station.getPendingDamage());
    return out;
  }
  
  /**
   * @brief Muestra las armas montadas o en hangar
   * @param someWeapons : ArrayList con armas
   * @param menu : Valor booleano para mostrar un tipo de representación u otra
   * @return Instancia String con el arma
   */
  private String showWeapons (ArrayList<WeaponToUI> someWeapons, boolean menu) {
    String out = "";

    int i = 0;
    for (WeaponToUI aWeapon : someWeapons) {
        out += showWeapon (aWeapon, (menu ? (String.format ("%3d",i) + " : ") : " - "));
        i++;
    }
    return out;
  }
  
  /**
   * @brief Muestra un arma
   * @param aWeapon : Arma a mostrar
   * @param tab : Tabulación
   * @return Instancia String con el arma
   */
  private String showWeapon (WeaponToUI aWeapon, String tab) {
    return (tab + aWeapon.getType() + " - Potencia: " + aWeapon.getPower() + " - Usos: " + aWeapon.getUses()+"\n");
  }
  
  /**
   * @brief Muestra los escudos montados o en hangar
   * @param someShields : ArrayList con escudos
   * @param menu : Valor booleano par mostrar un tipo de representación u otra
   * @return Instancia string con los escudos
   */
  private String showShields (ArrayList<ShieldToUI> someShields, boolean menu) {
    String out = "";
    
    int i = 0;
    for (ShieldToUI aShield : someShields) {
        out += showShield (aShield, (menu ? (String.format ("%3d",i) + " : ") : " - "));
        i++;
    }
    return out;
  }
  
  /**
   * @brief Muestra un escudo
   * @param aShield : Escudo a mostrar
   * @param tab : Tabulación
   * @return Instancia String con el escudo
   */
  private String showShield (ShieldToUI aShield, String tab) {
      return (tab + "Escudo - Potencia: " + aShield.getBoost() + " - Usos: " + aShield.getUses() + "\n");
  }
  
  /**
   * @brief Muestra un hangar
   * @param aHangar : Hangar a mostrar
   * @return Instancia String con el hangar
   */
  private String showHangar (HangarToUI aHangar) {
    String out = "";
    if (aHangar != null) {
        int slots = aHangar.getMaxElements();
        out += "Dispone de un Hangar con " + slots;
        if (slots == 1) {
            out += " lugar \n";
        } else {
            out += " lugares \n";
        }
        out += showWeapons (aHangar.getWeapons(), false);
        out += showShields (aHangar.getShieldBoosters(), false);
    } else out = "No tiene ningún Hangar\n";
    return out;
  }
  
  /**
   * @brief Muestra un enemigo
   * @param anEnemy : Enemigo a mostar
   * @return Instancia String con el enemigo
   */
  private String showEnemy (EnemyToUI anEnemy) {
    String out = "";
    out += separator + "\n";
    out += " *** Información del Enemigo actual ***\n";
    out += "     ------------------------------\n";
    out += "Nombre ............ : " + anEnemy.getName() + "\n";
    out += "Potencia de fuego . : " + anEnemy.getAmmoPower() + "\n";
    out += "Potencia de defensa : " + anEnemy.getShieldPower() + "\n";
    out += "Botín : \n";
    out += showLoot (anEnemy.getLoot());
    out += "Pérdidas : \n";
    out += showDamage (anEnemy.getDamage());
    return out;
  }
  
  /**
   * @brief Muestra un menú para montar | descartar armas | escudos
   * @param operation : Operación a realizar [Montar | Descartar]
   * @param element : Elemento a realizar operación [Weapon | ShieldBooster]
   * @return Entero con la opción realizada
   */
  private int showHangarToMountDiscard (Operation operation, Element element) {
      int option = Command.GOBACK.menu;
      
      System.out.println (separator);
      System.out.println ("Elige un " + element.text + " para " + operation.text);
      System.out.println ("\n" + String.format ("%3d",Command.GOBACK.menu) + " : " + Command.GOBACK.text + "\n");
      HangarToUI hangar = gameUI.getCurrentStation().getHangar();
      if (hangar != null) {
        switch (element) {
            case WEAPON :
                for (WeaponToUI weapon : hangar.getWeapons()) {
                    option++;
                    showMessage (showWeapon (weapon, String.format ("%3d",option) + " : "));
                }
                break;
            case SHIELD :
                for (ShieldToUI shield : hangar.getShieldBoosters()) {
                    option++;
                    showMessage (showShield (shield, String.format ("%3d",option) + " : "));
                }
                break;
        }
      }
      return option;
  }
  
  /**
   * @brief Muestra un Loot
   * @param aLoot : Loot a mostrar
   * @return Instancia String con el loot
   */
  private String showLoot (LootToUI aLoot) {
      String out = "";
      out += " - Armas ..... : " + aLoot.getnWeapons() + "\n";
      out += " - Escudos ... : " + aLoot.getnShields() + "\n";
      out += " - Hangares .. : " + aLoot.getnHangars() + "\n";
      out += " - Suministros : " + aLoot.getnSupplies() + "\n";
      out += " - Medallas .. : " + aLoot.getnMedals() + "\n";
      return out;
  }
  
  /**
   * @brief Muestra un damage
   * @param aDamage : Damage a Mostrar
   * @return Instancia String con el Damage
   */
  private String showDamage (DamageToUI aDamage) {
      String out = "";
      if (aDamage != null) {
        out += "\n - Armas . : " + aDamage.getWeaponInfo() + "\n";
        out += " - Escudos : " + aDamage.getNShields() + "\n";
      } else {
        out = "Ninguno\n";
      }
      return out;
  }
  
  /**
   * @brief Muestra un mensaje
   * @param string : String a mostrar por pantalla
   */
  private void showMessage(String string) {
    System.out.print (string);
  }
  
  /**
   * @brief Muestra un mensaje
   * @param string : String a mostrar por pantalla
   */
  private void showMessageln(String string) {
    System.out.println (string);
  }
  
}
