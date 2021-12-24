/**
 * @file DeepSpaceView.java
 * @author Profesor
 * @version P-05
 * type : Java-Interface
 */
package View;

import controller.Controller;
import deepspace.GameUniverse;
import java.util.ArrayList;

/**
 * @brief Interfaz que implementan las vistas de usuario:
 *  -> Interfaz de Texto (User Interface : UI)
 *  -> Interfaz Gráfica (Graphic User Interface : GUI)
 */
public interface DeepSpaceView {
  public void updateView();
  public void showView();
  // Inputs
  public ArrayList<String> readNamePlayers();
  // Outputs
  public boolean confirmExitMessage();
  public void nextTurnNotAllowedMessage();
  public void lostCombatMessage();
  public void escapeMessage();
  public void wonCombatMessage();
  public void wonGameMessage();
  public void conversionMessage();
  public void noCombatMessage();
}
