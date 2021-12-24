/**
 * @file BetaPowerEfficientSpaceStationToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Implementación de ToUI de BetaPowerEfficientSpaceStation
 */
public class BetaPowerEfficientSpaceStationToUI extends PowerEfficientSpaceStationToUI {
   /**
    * @brief Constructor con parametro de la clase
    * @param s : Instancia De BetaPowerEfficientSpaceStation
    */
   BetaPowerEfficientSpaceStationToUI(BetaPowerEfficientSpaceStation s) {
       super(s);
   }
   
   /**
    * @brief Obtiene el nombre de la estacion espacial
    * @return Nombre de BetaPowerEfficientSpaceStation
    */
   @Override
   public String getName () {
      return super.getName() + " (beta)";
   }
    
}
