/**
 * @file Hangar.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representa al Hangar donde se alojara la nave
 */
class Hangar {
    //ATRIBUTOS DE INSTANCIA
    private int maxElements;
    
    //ATRIBUTOS RELACION
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList<>();
    
    /**
     * @brief Constructor con parametro
     * @param capacity : Capacidad inicial
     */
    Hangar(int capacity){
        maxElements = capacity;
    }
    
    /**
     * @brief Constructor de copia
     * @param h : Otra instancia de Hangar
     */
    Hangar(Hangar h){
        maxElements = h.maxElements;
        for(Weapon w : h.weapons){
            weapons.add(w);
        }
        for(ShieldBooster sb : h.shieldBoosters){
            shieldBoosters.add(sb);
        }
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (Hangar)
     * @return Objeto UI Hangar
     */
    HangarToUI getUIversion(){
        return new HangarToUI(this);
    }
    
    /**
     * @brief Comprueba el espacio disponible
     * @return true si hay espacio | false en otro caso
     */
    private boolean spaceAvaiable(){
        return (weapons.size()+shieldBoosters.size()) < maxElements;
    }
    
    /**
     * @brief Añade arma en el hangar si queda espacio
     * @param w : Arma a insertar
     * @return true si se ha insertado | false en otro caso
     */
    public boolean addWeapon(Weapon w){
        boolean result = this.spaceAvaiable();
        if(result)
            weapons.add(w);
        
        return result;    
    }
    
    /**
     * @brief Añade potenciador de escudo en el hangar si queda espacio
     * @param s : Potenciador a insertar
     * @return true si se ha insertado | false en otro caso
     */
    public boolean addShieldBooster(ShieldBooster s){
        boolean result = this.spaceAvaiable();
        if(result)
            shieldBoosters.add(s);
        
        return result;
    }
    
    /**
     * @brief Obtiene la capacidad maxima de elementos del Hangar
     * @return maxElements
     */
    public int getMaxElements(){
        return maxElements;
    }
    
    /**
     * @brief Obtiene los potenciadores de escudo del Hangar
     * @return Array de potenciadores de escudo del Hangar
     */
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    /**
     * @brief Obtiene las armas del Hangar
     * @return Array de armas
     */
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    /**
     * @brief Elimina uno de los potenciadores de escudo del Hangar
     * @param s : Indice del potenciador a eliminar
     * @return Copia del potenciador si existe | NULL en otro caso
     */
    public ShieldBooster removeShieldBooster(int s){
        if(s<0 || shieldBoosters.size() <= s)
            return null;
        else
            return shieldBoosters.remove(s);
    }
    
    /**
     * @brief Elimina una de las armas del Hangar
     * @param w : Indice del arma a eliminar
     * @return Copia del arma si existe | NULL en otro caso
     */
    public Weapon removeWeapon(int w){
        if(w < 0 || weapons.size() <= w)
            return null;
        else
            return weapons.remove(w);
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    public String toString(){
        String test;
        
        //CONTENT
        String numElements = "+ MAXELEMENTS IN HANGAR: " + maxElements + "\n";
        String arrayWeapons = "+ WEAPONS IN HANGAR: \n";
        if(weapons == null || weapons.isEmpty()){
            arrayWeapons += "Ninguna\n";
        }
        else{
            for(Weapon w : weapons){
                arrayWeapons += " " +w.toString();
            }
        }
        
        String arrayShieldBoosters = "\n+ SHIELDBOOSTERS IN HANGAR: \n";
        if(shieldBoosters == null || shieldBoosters.isEmpty()){
            arrayShieldBoosters += "Ninguno\n";
        }
        else{
            for(ShieldBooster sb : shieldBoosters){
                arrayShieldBoosters += " " +sb.toString();
            }
        }
        
        test = numElements + arrayWeapons + arrayShieldBoosters;
        
        return test;
    }
}
