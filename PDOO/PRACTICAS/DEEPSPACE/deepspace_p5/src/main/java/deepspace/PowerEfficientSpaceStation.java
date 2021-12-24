/**
 * @file PowerEfficientSpaceStation.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representa la posible transformacion que sufre una estacion espacial.
 * Las estaciones espaciales eficientes consiguen que se incremente la potencia de disparo y de
 * protección en un determinado factor constante respecto a las estaciones estándar.
 */
public class PowerEfficientSpaceStation extends SpaceStation{
    private final float EFFICIENCYFACTOR = 1.10f;
    
    /**
     * @brief Constructor con parametro
     * @param station : Instancia de SpaceStation
     */
    PowerEfficientSpaceStation(SpaceStation station){
        super(station);
    }
    
    /**
     * @brief Realiza un disparo.
     * @return Devuelve la energia o potencia del mismo
     */
    @Override
    public float fire(){
        return super.fire() * EFFICIENCYFACTOR;
    }
    
    /**
     * @brief Se usa el escudo de protección 
     * @return Se devuelve la energía del mismo
     */
    @Override
    public float protection(){
        return super.protection() * EFFICIENCYFACTOR;
    }
    
    /**
     * @brief Recepción de un botín.
     * @param loot : Botin a recibir
     * @return Transformacion de la nave
     */
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        if(loot.getEfficient())
            return Transformation.GETEFFICIENT;
        else
            return Transformation.NOTRANSFORM;
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (PowerEfficientSpaceStation)
     * @return Objeto UI PowerEfficientSpaceStation
     */
    @Override
    public PowerEfficientSpaceStationToUI getUIversion(){
        return new PowerEfficientSpaceStationToUI(this);
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        return "* POWER EFFICIENT SPACE STATION \n" + super.toString();
    }
}
