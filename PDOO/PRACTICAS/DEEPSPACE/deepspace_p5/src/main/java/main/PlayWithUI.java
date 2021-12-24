/**
 * @file PlayWithUI.java
 * @author Profesor
 * @brief Lanza la interfaz UI - Deepspace
 * @version 5.0 FINAL
 */
package main;

import View.DeepSpaceView;
import View.UI.TextMainView;
import controller.Controller;
import deepspace.GameUniverse;

/**
 * @brief Se lanza la interfaz de texto (UI) del juego. El procedimiento es el siguiente:
 * 1. Crear una nueva Vista (DeepSpaceView) y asociarla a la interfaz de texto (TextMainView)
 * 2. Crear una nueva Instancia de GameUniverse
 * 3. Inicializar el controlador del juego
 * 4. Asociar al controlador el modelo (GameUniverse) y la interfaz de texto (DeepSpaceView)
 * 5. Iniciar partida llamando al Controller
 */
public class PlayWithUI {
    public static void main(String[] args) {
      DeepSpaceView ui;
      GameUniverse game = new GameUniverse();
      ui = TextMainView.getInstance();
      Controller controller = Controller.getInstance();
      controller.setModelView(game,ui);
      controller.start();   
    }
  
}
