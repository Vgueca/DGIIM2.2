/**
 * @file BetaPowerEfficientSpaceStation.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Subclase de la estación espacial eficiente que utiliza una tecnología en pruebas (beta) y 
 * que es potencialmente más eficiente al disparar
 */
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    private final float EXTRAEFFICIENCY = 1.2f;
    private Dice dice = new Dice();
    
    /**
     * @brief Constructor con parametro
     * @param station : Instancia de SpaceStation
     */
    BetaPowerEfficientSpaceStation(SpaceStation station){
        super(station);
    }
    
    /**
     * @brief Realiza un disparo.
     * @return Devuelve la energia o potencia del mismo
     */
    @Override
    public float fire(){
        if(dice.extraEfficiency())
            return super.fire()*EXTRAEFFICIENCY;
        
        return super.fire();
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (BetaPowerEfficientSpaceStation)
     * @return Objeto UI BetaPowerEfficientSpaceStation
     */
    @Override
    public BetaPowerEfficientSpaceStationToUI getUIversion(){
        return new BetaPowerEfficientSpaceStationToUI(this);
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        return "* BETA POWER EFFICIENT SPACE STATION \n" + super.toString();
    }
}
