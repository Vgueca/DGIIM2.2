/**
 * @file EnemyStarShip.java
 * @author Daniel Pérez Ruiz
 * @version P-05
 * type : Java-Class
 */
package deepspace;

/**
 * @brief Representa una instancia de la nave enemiga
 */
class EnemyStarShip implements SpaceFighter{
    //ATRIBUTOS DE INSTANCIA
    private float ammoPower;
    private String name;
    private float shieldPower;
    
    //ATRIBUTOS DE RELACION
    private Loot loot;
    private Damage damage;
    
    /**
     * @brief Constructor con parametro
     * @param n : Nombre
     * @param a : Valor de ammoPower inicial
     * @param s : Valor de shieldPower inicial
     * @param l : Instancia de loot
     * @param d : Instancia de Damage
     */
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        ammoPower = a;
        name = n;
        shieldPower = s;
        loot = l;
        damage = d;
    }
    
    /**
     * @brief Constructor de copia
     * @param e : Otra instancia de EnemyStarShip
     */
    EnemyStarShip(EnemyStarShip e){
        ammoPower = e.ammoPower;
        name = e.name;
        shieldPower = e.shieldPower;
        loot = e.loot;
        damage = e.damage;
    }
    
    /**
     * @brief @brief Permite conectar el modelo con la interfaz de usuario (EnemyStarShip)
     * @return Objeto UI EnemyStarShip
     */
    EnemyToUI getUIversion(){
        return new EnemyToUI(this);
    }
    
    /**
     * @brief Devuelve el nivel de energia de disparo de la nave enemiga
     * @return ammoPower
     */
    @Override
    public float fire(){
        return ammoPower;
    }
    
    /**
     * @brief Devuelve el nivel de proteccion de la nave
     * @return shieldPower
     */
    @Override
    public float protection(){
        return shieldPower;
    }
    
    /**
     * @brief Deuvelve el poder de ammo
     * @return ammoPower
     */
    public float getAmmoPower(){
        return ammoPower;
    }
    
    /**
     * @brief Devuelve el atributo de daño
     * @return damage
     */
    public Damage getDamage(){
        return damage;
    }
    
    /**
     * @brief Devuelve el atributo de lool
     * @return loot
     */
    public Loot getLoot(){
        return loot;
    }
    
    /**
     * @brief Devuelve el nombre de nave enemiga
     * @return name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @brief Devuelve energia de escudo
     * @return shieldPower 
     */
    public float getShieldPower(){
        return shieldPower;
    }
    
    /**
     * @brief Devuelve el nivel de energia de escudo de la nave enemiga
     * @return shieldPower
     */
    public float getProtection(){
        return shieldPower;
    }
    
    /**
     * @brief Devuelve el resultado que se produce al recibir un disparo de una
     * determinada potencia (pasada como parametor).
     * @param shot : Valor de disparo
     * @return DONOTRESIST si no resiste la nave enemiga | RESIST en caso contrario
     */
    @Override
    public ShotResult receiveShot(float shot){
        if(shot > shieldPower)
            return ShotResult.DONOTRESIST;
        else
            return ShotResult.RESIST;
    }
    
    /**
     * @brief Funcion de Debug
     * @return test : Representacion en cadena String de la instancia
     */
    @Override
    public String toString(){
        String test;
        
        //CONTENT
        String nameT = "+ NAME: " + name + "\n";
        String ammoP = "+ AMMOPOWER: " + ammoPower + " ";
        String shieldP = "+ SHIELDPOWER: " + shieldPower + "\n";
        String LootT= "+ LOOT: \n";
        if(loot == null)
            LootT += "Ninguno\n";
        else 
            LootT += loot.toString();
        
        String damageT= "\n+ DAMAGE: \n";
        if(damage == null)
            damageT += "Ninguno\n";
        else
            damageT += damage.toString() + "\n";
        
        test = nameT + ammoP + shieldP + LootT + damageT;
        
        return test;
    }
}
