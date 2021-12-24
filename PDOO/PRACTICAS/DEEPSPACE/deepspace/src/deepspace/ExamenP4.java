/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author valenting
 */
public class ExamenP4 {
    
    private static final int NUMBERSTATIONS= 20;
    private static final int INDEXTOCONVERT=1;
    public static void main(String[] args) {
          
       ArrayList<SpaceStation> stations = new ArrayList<>();
       
       CardDealer dealer = CardDealer.getInstance();
         
        for (int i = 0; i < NUMBERSTATIONS; i++) {
            stations.add(new SpaceStation("Station number"+i, dealer.nextSuppliesPackage()));
        }
        System.out.print("\nAntes de crear la ciudad espacial \n");
        for (int i = 0; i < NUMBERSTATIONS; i++) {
             System.out.print ("Station number "+i + " "+ stations.get(i).protection() + "\n");
        }
        
        ArrayList<SpaceStation> stations_aux = new ArrayList<>(stations);
        stations_aux.remove(INDEXTOCONVERT);
        
        stations.set(INDEXTOCONVERT, new SpaceCity(stations.get(INDEXTOCONVERT), stations_aux));
        System.out.print("\nDespues de crear la ciudad espacial \n");
        for (int i = 0; i < NUMBERSTATIONS; i++) {
             System.out.print ("Station number "+i + " "+ stations.get(i).protection() + "\n");
        }
        
        System.out.print("\nSegunda parte del examen: \n");
        ArrayList<CombatElement> combatElements =  new ArrayList<>();
        combatElements.add(dealer.nextWeapon());
        combatElements.add(dealer.nextWeapon());
        combatElements.add(dealer.nextShieldBooster());
        combatElements.add(dealer.nextShieldBooster());
        
        for (int i = 0; i < combatElements.size(); i++) {
             System.out.print("Usos del objeto " + i +": "+ combatElements.get(i).getUses()+"\n");

        }
    }
}

