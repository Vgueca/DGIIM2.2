/**
 * @file SpaceCityToUI.java
 * @author Profesor
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representación ToUI del objeto SpaceCity
 */
public class SpaceCityToUI extends SpaceStationToUI {
    // MIGUEL: He añadido este atributo para que el ToUI sea completo
    // Modificado el constructor y añadido un consultor
    
    ArrayList<SpaceStationToUI> collaborators = new ArrayList<>();
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param city : Objeto SpaceCity
     */
    SpaceCityToUI (SpaceCity city) {
        super (city);
        for (SpaceStation s : city.getCollaborators()) {
            collaborators.add (new SpaceStationToUI(s));
        }
    }
    
    /**
     * @brief Obtiene el nombre de la estación espacial
     * @return Nombre de SpaceCity
     */
    @Override
    public String getName () {
        return super.getName()+" (CIUDAD ESPACIAL)";
    }
    
    /**
     * @brief Obtiene los colaboradores de la ciudad espacial
     * @return collaborators
     */
    public ArrayList<SpaceStationToUI> getCollaborators() {
        return collaborators;
    }
}
