/**
 * @file Command.java
 * @author Profesor
 * @brief Menu de opciones para vista de texto
 * @version 5.0 FINAL
 */
package View.UI;

/**
 * @brief En este enumerado se representa todas las opciones posibles del menú
 * utilizado en la interfaz de texto.
 * Cada objeto del enumerado consta de un entero y de un texto
 */
enum Command {
  EXIT                   ( 0, "Salir del juego"),
  GOBACK                 (-1, "Volver al menú anterior"),
  SHOWENEMY              ( 2, "  Mostrar Información del Enemigo"),
  NEXTTURN               ( 3, "Siguiente Turno"),
  MOUNTWEAPONS           (47, "   Montar Armas"),
  MOUNTSHIELDS           (48, "   Montar Potenciadores de Escudo"),
  DISCARDWEAPONS         (57, "Descartar Elementos Montados: Armas"),
  DISCARDSHIELDS         (58, "Descartar Elementos Montados: Potenciadores de Escudo"),
  DISCARDWEAPONSINHANGAR (67, "Descartar del Hangar: Armas"),
  DISCARDSHIELDSINHANGAR (68, "Descartar del Hangar: Potenciadores de Escudo"),
  DISCARDHANGAR          (69, "Descartar del Hangar: Hangar Completo"),
  COMBAT                 ( 1, "¡¡¡ C O M B A T I R !!!"),
  SHOWSTATION            ( 4, "  Mostrar Información de la Estación Espacial"),
  NEXTTURNALLOWED       (-10, ""),
  ERRORINPUT             (-2, "Entrada errónea");
  
  public final int menu;
  public final String text;
  
  /**
   * @brief Constructor con parámetro de la clase
   * @param aValue : Valor entero de selección en el menú
   * @param aText : Información asociada a la operación
   */
  private Command (int aValue, String aText) {
    this.menu = aValue;
    this.text = aText;
  }
}
