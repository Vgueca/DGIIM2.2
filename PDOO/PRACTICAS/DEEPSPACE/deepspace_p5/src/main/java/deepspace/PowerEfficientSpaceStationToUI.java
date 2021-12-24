/**
 * @file PowerEfficientSpaceStationToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representación ToUI del objeto PowerEfficientSpaceStation
 */
public class PowerEfficientSpaceStationToUI extends SpaceStationToUI {
    /**
     * @brief Constructor con parámetro de la clase
     * @param efficientStation : Objeto PowerEfficientSpaceStation
     */
    PowerEfficientSpaceStationToUI (PowerEfficientSpaceStation efficientStation) {
        super(efficientStation);
    }
    
    /**
    * @brief Obtiene el nombre de la estacion espacial
    * @return Nombre de PowerEfficientSpaceStation
    */
    @Override
    public String getName () {
        return super.getName() + " (ESTACIÓN EFICIENTE)";
    }

}
