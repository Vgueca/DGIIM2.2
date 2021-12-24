/**
 * @file SuppliesPackage.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representa a un paquete de suministros para una estación espacial. Puede
 * contener armamento, combustible y/o energía para los escudos.
 */
class SuppliesPackage {
    private float ammoPower;
    private float fuelUnits;
    private float shieldPower;
    
    /**
     * @brief Constructor con parametro.
     * @param p_ammoPower Valor Armamento
     * @param p_fuelUnits Valor Combustible
     * @param p_shielPower Valor Escudo
     */
    SuppliesPackage(float p_ammoPower, float p_fuelUnits, float p_shieldPower){
        ammoPower = p_ammoPower;
        fuelUnits = p_fuelUnits;
        shieldPower = p_shieldPower;
    }
    
    /**
     * @brief Constructor de copia
     * @param s Otro objeto de la clase SuppliesPackage
     */
    SuppliesPackage(SuppliesPackage s){
        ammoPower = s.ammoPower;
        fuelUnits = s.fuelUnits;
        shieldPower = s.shieldPower;
    }
    
    /**
     * @brief Consultor de Armamento
     * @return ammoPower
     */
    public final float getAmmoPower(){
        return ammoPower;
    }
    
    /**
     * @brief Consultor de Combustible
     * @return fuelUnits
     */
    public final float getFuelUnits(){
        return fuelUnits;
    }
    
    /**
     * @brief Consultor de Escudo
     * @return shieldPower
     */
    public final float getShieldPower(){
        return shieldPower;
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    public String toString(){
        String test;
        
        //CONTENT
        String aP = "- AMMOPOWER: " + ammoPower + " ";
        String fU = "- FUELUNITS: " + fuelUnits + " ";
        String sP = "- SHIELDPOWER: " + shieldPower + "\n";
        
        return test = aP + fU + sP;
    }
}
