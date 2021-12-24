/**
 * @file SpaceStation.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

import java.util.ArrayList;

/**
 * @brief Representa a la nave del juego
 */
class SpaceStation implements SpaceFighter{
    //ATRIBUTOS DE CLASE
    private static final float MAXFUEL = 100f;
    private static final float SHIELDLOSSPERUNITSHOT = 0.1f;
    
    //ATRIBUTOS DE INSTANCIA
    private float ammoPower;
    private float fuelUnits;
    private String name;
    private int nMedals;
    private float shieldPower;
    
    //ATRIBUTOS DE RELACION
    private Damage pendingDamage;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList<>();
    private Hangar hangar;
    
    
    /**
     * @brief Fija la cantidad de combustible al valor pasado como param
     * sin que nunca exceda del limite
     * @param f : Valor de fuel
     */
    private void assignFuelValue(float f){
        if(f > MAXFUEL)
            fuelUnits = MAXFUEL;
        else
            fuelUnits = f;
    }
    
    /**
     * @brief Si el daño pendiente no tiene efecto fija la referencia
     * al mismo a null
     */
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoEffect()){
            pendingDamage = null;
        }
    }
    
    /**
     * @brief Constructor con parametros
     * @param n : Nombre
     * @param supplies : Paquete de suministros
     */
    SpaceStation(String n, SuppliesPackage supplies){
        ammoPower = supplies.getAmmoPower();
        fuelUnits = supplies.getFuelUnits();
        name = n;
        nMedals = 0;
        shieldPower = supplies.getShieldPower();
        pendingDamage = null;
    }
    
    /**
     * @brief Constructor de copia
     * @param station : Otra instancia de SpaceStation
     */
    SpaceStation(SpaceStation station){
        ammoPower = station.ammoPower;
        fuelUnits = station.fuelUnits;
        name = station.name;
        nMedals = station.nMedals;
        shieldPower = station.shieldPower;
        pendingDamage = station.pendingDamage;
        
        for(Weapon w : station.weapons){
            weapons.add(w);
        }
        for(ShieldBooster s : station.shieldBoosters){
            shieldBoosters.add(s);
        }
        hangar = station.hangar;
    }
    
    /**
     * @brief Elimina todas las armas y potenciadores de escudo montados a
     * las que no les queden usos
     */
    public void cleanUpMountedItems(){
        //OBTENGO LAS ARMAS QUE NO TIENEN USOS
        ArrayList<Weapon> weaponsWithNoUses = new ArrayList<>();
        for(Weapon w: weapons){
            if (w.getUses() == 0)
                weaponsWithNoUses.add(w);
        }
        
        //ELIMINO LAS ARMAS QUE NO TENGAN USOS
        for(Weapon w: weaponsWithNoUses){
            weapons.remove(w);
        }
        
        //OBTENGO LOS SHIELD QUE NO TIENEN USOS
        ArrayList<ShieldBooster> shieldWithNoUses = new ArrayList<>();
        for(ShieldBooster s: shieldBoosters){
            if(s.getUses() == 0)
                shieldWithNoUses.add(s);
        }
        
        //ELIMINO LOS SHIELD QUE NO TENGAN USOS
        for(ShieldBooster s: shieldWithNoUses){
            shieldBoosters.remove(s);
        }
    }
    
    /**
     * @brief Fija la referencia del hangar a null para indicar que no se
     * dispone del mismo
     */
    public void discardHangar(){
        hangar = null;
    }
    
    /**
     * @brief Descarta un potenciador de escudo
     * @param i : Indice de potenciador de escudo a descartar
     */
    public void discardShieldBooster(int i){
        int size = shieldBoosters.size();
        if((0<=i) && (i<size)){
            shieldBoosters.remove(i);
            
            if(pendingDamage != null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }
    
    /**
     * @brief Si se dispone de hangar, se solicita al mismo descartar el
     * potenciador de escudo con indice i
     * @param i : Indice de potenciador de escudo a descartar
     */
    public void discardShieldBoosterInHangar(int i){
        if(hangar != null)
            hangar.removeShieldBooster(i);
    }
    
    /**
     * @brief Descarta un arma
     * @param i : Indice de arma a descartar
     */
    public void discardWeapon(int i){
        int size = weapons.size();
        if((0<=i) && (i<size)){
            Weapon weapon = weapons.remove(i);
            
            if(pendingDamage != null){
                pendingDamage.discardWeapon(weapon);
                cleanPendingDamage();
            }
        }
    }
    
    /**
     * @brief Si se dispone de hangar, se solicita al mismo descartar el
     * arma con indice i
     * @param i : Indice de arma a descartar
     */
    public void discardWeaponInHangar(int i){
        if(hangar != null)
            hangar.removeWeapon(i);
    }
    
    /**
     * @brief Realiza un disparo.
     * @return Devuelve la energia o potencia del mismo
     */
    @Override
    public float fire(){
        int size = weapons.size();
        float factor = 1;
        
        for(int i=0;i<size;i++){
            Weapon w = weapons.get(i);
            factor *= w.useIt();
        }
        
        return ammoPower*factor;
    }
    
    /**
     * @brief Devuelve poder de arma
     * @return ammoPower
     */
    public float getAmmoPower(){
        return ammoPower;
    }
    
    /**
     * @brief Devuelve unidades Fuel
     * @return fuelUnits
     */
    public float getFuelUnits(){
        return fuelUnits;
    }
    
    /**
     * @brief Devuelve el Hangar
     * @return hangar
     */
    public Hangar getHangar(){
        return hangar;
    }
    
    /**
     * @brief Devuelve el nombre
     * @return name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @brief Devuelve el numero de medallas
     * @return nMedals
     */
    public int getNMedals(){
        return nMedals;
    }
    
    /**
     * @brief Devuelve el daño pendiente
     * @return pendingDamage
     */
    public Damage getPendingDamage(){
        return pendingDamage;
    }
    
    /**
     * @brief Devuelve todos los potenciadores de escudo
     * @return ArrayList con ShieldBoosters
     */
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    /**
     * @brief Devuelve la potencia del escudo
     * @return shieldPower
     */
    public float getShieldPower(){
        return shieldPower;
    }
    
    /**
     * @brief Devuelve la velocidad de la estación espacial
     * @return speed
     */
    public float getSpeed(){
        return fuelUnits/MAXFUEL;
    }
    
    /**
     * @brief Permite conectar el modelo con la interfaz de usuario (SpaceStation)
     * @return Objeto UI SpaceStation
     */
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
    
    /**
     * @brief Devuelve las armas disponibles
     * @return ArrayList con las armas
     */
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    /**
     * @brief Se intenta montar el potenciador de escudo con el índice i dentro
     * del hangar. 
     * Si se dispone de hangar, se le indica que elimine el potenciador de escudo de esa 
     * posición y si esta operación tiene éxito (el hangar proporciona el potenciador), se añade el mismo a
     * la colección de potenciadores en uso.
     * @param i : Indice de potenciador de escudo a montar
     */
    public void mountShieldBooster(int i){
        if(hangar != null){
            if((0<=i) && (i<hangar.getShieldBoosters().size())){
                ShieldBooster shieldBooster = hangar.removeShieldBooster(i);
                if(shieldBooster != null)
                    shieldBoosters.add(shieldBooster);
            }
        }
    }
    
    /**
     * @brief Se intenta montar el arma con el índice i dentro del hangar. Si se dispone
     * de hangar, se le indica que elimine el arma de esa posición y si esta operación tiene éxito (el hangar
     * proporciona el arma), se añade el arma a la colección de armas en uso.
     * @param i : Indice de arma a montar
     */
    public void mountWeapon(int i){
        if(hangar != null){
            if((0<=i) && (i<hangar.getWeapons().size())){
                Weapon weapon = hangar.removeWeapon(i);
                if(weapon != null)
                    weapons.add(weapon);
            }
        }
    }
    
    /**
     * @brief Decremento de unidades de combustible disponibles a causa de un desplazamiento.
     */
    public void move(){
        fuelUnits -= getSpeed()*fuelUnits;
        if(fuelUnits < 0)
            fuelUnits = 0;
    }
    
    /**
     * @brief Se usa el escudo de protección 
     * @return Se devuelve la energía del mismo
     */
    @Override
    public float protection(){
        int size=shieldBoosters.size();
        float factor=1;
        
        for(int i=0;i<size;i++){
            ShieldBooster s = shieldBoosters.get(i);
            factor *= s.useIt();
        }
        
        return shieldPower*factor;
    }
    
    /**
     * @brief Si no se dispone de hangar, el parámetro pasa a ser el hangar de la
     * estación espacial. Si ya se dispone de hangar esta operación no tiene efecto.
     * @param h : Nuevo hangar
     */
    public void receiveHangar(Hangar h){
        if(hangar == null)
            hangar = h;
    }
    
    /**
     * @brief Si se dispone de hangar, devuelve el resultado de
     * intentar añadir el potenciador de escudo al mismo. Si no se dispone de hangar devuelve false .
     * @param s : Potenciador de escudo a insertar
     * @return True si se dispone de Hangar | False en otro caso
     */
    public boolean receiveShieldBooster(ShieldBooster s){
        if(hangar != null)
            return hangar.addShieldBooster(s);
        else
            return false;
    }
    
    /**
     * @brief Realiza las operaciones relacionadas con la recepción del impacto de un disparo enemigo.
     * @param shot : Disparo enemigo
     * @return Devuelve el resultado de si se ha resistido el disparo o no.
     */
    @Override
    public ShotResult receiveShot(float shot){
        float myProtection = protection();
        if(myProtection >= shot){
            shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
            shieldPower = Math.max(0.0f, shieldPower);
            return ShotResult.RESIST;
        }
        else{
            shieldPower = 0.0f;
            return ShotResult.DONOTRESIST;
        }
    }
    
    /**
     * @brief La potencia de disparo, la del escudo y las unidades de 
     * combustible se incrementan con el contenido del paquete de suministro.
     * @param s : Paquete de suministros
     */
    public void receiveSupplies(SuppliesPackage s){
        ammoPower += s.getAmmoPower();
        fuelUnits += s.getFuelUnits();
        shieldPower += s.getShieldPower();
        
        if(fuelUnits > MAXFUEL)
            fuelUnits = MAXFUEL;
    }
    
    /**
     * @brief Si se dispone de hangar, devuelve el resultado de intentar añadir el arma al mismo. Si no se dispone de hangar devuelve false.
     * @param w : Arma a insertar
     * @return True si se ha realizado con exito | False en otro caso
     */
    public boolean receiveWeapon(Weapon w){
        if(hangar != null)
            return hangar.addWeapon(w);
        else
            return false;
    }
    
    /**
     * @brief Recepción de un botín.
     * @param loot : Botin a recibir
     * @return Transformacion de la nave
     */
    public Transformation setLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();
        int h = loot.getNHangars();
        
        if(h>0){
            Hangar hangar = dealer.nextHangar();
            receiveHangar(hangar);
        }
        
        int elements = loot.getNSupplies();
        for(int i=0; i<elements; i++){
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }
        
        elements = loot.getNWeapons();
        for(int i=0; i<elements; i++){
            Weapon weap = dealer.nextWeapon();
            receiveWeapon(weap);
        }
        
        elements = loot.getNShields();
        for(int i=0; i<elements; i++){
            ShieldBooster sh = dealer.nextShieldBooster();
            receiveShieldBooster(sh);
        }
        
        int medals = loot.getNMedals();
        nMedals += medals;
        
        if(loot.getEfficient()){
            return Transformation.GETEFFICIENT;
        }
        if(loot.spaceCity()){
            return Transformation.SPACECITY;
        }
        
        return Transformation.NOTRANSFORM;
    }
    
    /**
     * @brief Se calcula el parámetro ajustado (adjust) a la lista de armas y 
     * potenciadores de escudo de la estación y se almacena el resultado en el atributo correspondiente.
     * @param d : Instancia de Damage
     */
    public void setPendingDamage(Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
        cleanPendingDamage();
    }
    
    /**
     * @brief Devuelve true si la estación espacial está en un estado válido. Eso implica que 
     * o bien no se tiene ningún daño pendiente o que este no tiene efecto.
     * @return True si estado es valido | False en otro caso
     */
    public boolean validState(){
        if(pendingDamage == null)
            return true;
        
        return pendingDamage.hasNoEffect();
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    public String toString(){
        String test;
        
        //CONTENT
        String namE = "+ NAME: " + name + "\n";
        String amP = "+ AMMOPOWER: " + ammoPower + " ";
        String fU = "+ FUELUNITS: " + fuelUnits + " ";
        String sPow = "+ SHIELDPOWER: " + shieldPower + " ";
        String nMed = "+ NMEDALS: " + nMedals + "\n";
        
        String weap = "\n+ WEAPONS MOUNTED: \n";
        if(weapons == null || weapons.isEmpty())
            weap += " Ninguna\n";
        else{
            for(Weapon w : weapons){
                weap += w.toString();
            }
        }
        
        String shB = "\n+ SHIELDBOOSTERS MOUNTED: \n";
        if(shieldBoosters == null || shieldBoosters.isEmpty())
            shB += " Ninguno\n";
        else{
            for(ShieldBooster sB : shieldBoosters){
                shB += sB.toString();
            }
        }
        
        String han = "\n* HANGAR: \n";
        if(hangar == null)
            han += " Ninguno\n";
        else
            han += hangar.toString();
        
         
        String pDam = "\n* PENDINGDAMAGE: \n";
        if(pendingDamage == null)
            pDam += " Ninguno\n";
        else
            pDam += pendingDamage.toString() + "\n";
        
        
        test = namE + amP + fU + sPow + nMed + weap + shB + han + pDam;
        
        return test;
    }
}
