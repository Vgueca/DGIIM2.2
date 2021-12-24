/**
 * @file HangarView.java
 * @author Daniel Pérez Ruiz
 * @brief Implementación GUI - DeepSpace::HangarView
 * @version P-05
 * type : Java-JPanel
 */
package View.GUI;

import controller.Controller;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.util.ArrayList;
import java.awt.Component;

import deepspace.HangarToUI;
import deepspace.ShieldToUI;
import deepspace.WeaponToUI;

/**
 * @brief Esta clase implementa la interfaz gráfica de un objeto de tipo HangarToUI
 */
public class HangarView extends JPanel {

    /**
     * @brief Constructor por defecto de la clase
     */
    public HangarView() {
        initComponents();
    }
    
    /**
     * @brief Asocia al panel un objeto ToUI de HangarView
     * @param h : Objeto HangarToUI que se le pasará a la vista
     */
    public void setHangar(HangarToUI h){
        if(h == null){
            setVisible(false);
        }
        else{
            hangarPanel.removeAll();
            String title = "Hangar con " + h.getMaxElements() + " lugares";
            hangarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), title));
            
            ArrayList<WeaponToUI> weapons = h.getWeapons();
            ArrayList<ShieldToUI> shieldBoosters = h.getShieldBoosters();
        
            for(WeaponToUI w : weapons){
                WeaponView weaponView = new WeaponView();
                weaponView.setWeapon(w);
                hangarPanel.add(weaponView);
            }
        
            for(ShieldToUI s : shieldBoosters){
                ShieldView shieldView = new ShieldView();
                shieldView.setShield(s);
                hangarPanel.add(shieldView);
            }
        }
        
        repaint();
        revalidate();
    }
    
    /**
     * @brief Obtiene los elementos que han sido seleccionados por el usuario
     * @param weaponsSelected : Almacena los índices de los weapons del hangar seleccionados por el usuario
     * @param shieldsSelected : Almacena los índices de los shieldBosoter del hangar seleccionados por el usuario
     */
    void getSelectedInHangar(ArrayList<Integer> weaponsSelected, ArrayList<Integer> shieldsSelected){
        int numWeapons = Controller.getInstance().getUIversion().getCurrentStation().getHangar().getWeapons().size();
        int numShields = Controller.getInstance().getUIversion().getCurrentStation().getHangar().getShieldBoosters().size();
        
        
        for(int i=0; i<numWeapons; i++){
            Component c = hangarPanel.getComponent(i);
            if(((CombatElementView) c).isSelected()){
                weaponsSelected.add(i);
            }
        }
        
        for(int i=0; i<numShields; i++){
            Component c = hangarPanel.getComponent(i+numWeapons);
            if(((CombatElementView) c).isSelected()){
                shieldsSelected.add(i);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hangarScroll = new javax.swing.JScrollPane();
        hangarPanel = new javax.swing.JPanel();

        setBorder(null);

        hangarScroll.setBorder(null);
        hangarScroll.setViewportView(hangarPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hangarScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hangarScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel hangarPanel;
    private javax.swing.JScrollPane hangarScroll;
    // End of variables declaration//GEN-END:variables
}
