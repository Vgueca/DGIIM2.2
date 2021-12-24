/**
 * @file SpecificDamageToUI.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representación ToUI del objeto SpecificDamage
 */
public class SpecificDamageToUI extends DamageToUI{
    private ArrayList<WeaponType> weapons;
    
    /**
     * @brief Constructor con parámetro de la clase
     * @param d : Objeto SpecificDamage
     */
    SpecificDamageToUI(SpecificDamage d) {
        super(d);
        ArrayList<WeaponType> tmp=d.getWeapons();
        if (tmp!=null) {
            weapons=new ArrayList<WeaponType>(tmp);
        }
    }
    
    /**
     * @brief Consultor de weapons
     * @return weapons
     */
    public ArrayList<WeaponType> getWeapons() {
        return weapons;
    }  
    
    /**
     * @brief Devuelve el array de tipos de armas a perder
     * @return Instancia Srting con los tipos de armas a perder
     */
    @Override
    public String getWeaponInfo() {
        String aux = "";
        if(weapons.size() < 6){
            aux = weapons.toString();
            return aux.substring(1, aux.length()-1);
        }
        else{
            for(int i=0; i< weapons.size()-1; i++){
                aux += weapons.get(i).toString();
                aux += ", ";
                
                if((i % 4 == 0) && (i!=0)){
                    aux += "\n";
                }
            }
            aux += weapons.get(weapons.size()-1).toString();
            return aux;
        }
    }
}
