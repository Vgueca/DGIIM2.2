/**
 * @file SpaceCity.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representa la transformacion de unas estaciones espaciales en ciudades espaciales. 
 * Las ciudades espaciales están formadas por la estación que tenía el jugador 
 * y las estaciones espaciales del resto de jugadores. Son a su vez un tipo de estación espacial.
 */
public class SpaceCity extends SpaceStation{
    private SpaceStation base;
    private ArrayList<SpaceStation> collaborators;
    
    /**
     * @brief Constructor con parametro
     * @param b : SpaceStation base
     * @param rest : Colaboradores
     */
    SpaceCity(SpaceStation b, ArrayList<SpaceStation> rest){
        super(b);
        base = b;
        collaborators = new ArrayList<>(rest);
    }
    
    /**
     * @brief Devuelve los collaborators de SpaceCity
     * @return collaborators
     */
    public ArrayList<SpaceStation> getCollaborators(){
        return collaborators;
    }
    
    /**
     * @brief Realiza un disparo entre todas las naves
     * @return Devuelve la energia o potencia del mismo
     */
    @Override
    public float fire(){
        float factor = base.fire();
        for(int i=0; i<collaborators.size(); i++)
            factor += collaborators.get(i).fire();
        
        return factor;
    }
    
    /**
     * @brief Se usa el escudo de protección de todas las naves
     * @return Se devuelve la energía del mismo
     */
    @Override
    public float protection(){
        float factor = base.protection();
        for(int i=0; i<collaborators.size(); i++)
            factor += collaborators.get(i).protection();
        
        return factor;
    }
    
    /**
     * @brief Recepción de un botín.
     * @param loot : Botin a recibir
     * @return Transformacion de la nave
     */
    @Override
    public Transformation setLoot(Loot loot){
        base.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (SpaceCityToUI)
     * @return Objeto UI SpaceCity
     */
    @Override
    public SpaceCityToUI getUIversion(){
        return new SpaceCityToUI(this);
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String test;
        String header_base = "* ESTACION BASE: \n";
        String basE = base.toString();
        
        String header_coll = "\n* COLLABORATORS: \n";
        String colab = collaborators.get(0).toString();
        
        for(int i=1; i<collaborators.size(); i++){
            colab += collaborators.get(i).toString();
        }
        
        test = header_base + basE + header_coll + colab;
                
        return test;
    }
}
