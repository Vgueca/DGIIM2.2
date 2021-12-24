/**
 * @file SpaceStationView.java
 * @author Daniel Pérez Ruiz
 * @brief Implementación GUI - DeepSpace::SpaceStationView
 * @version P-05
 * type : Java-JPanel
 */
package View.GUI;

import controller.Controller;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import deepspace.HangarToUI;
import deepspace.WeaponToUI;
import deepspace.ShieldToUI;
import deepspace.SpaceStationToUI;
import deepspace.GameState;
import java.awt.Color;

/**
 * @brief Esta clase implementa la interfaz gráfica de un objeto de tipo SpaceStationToUI
 */
public class SpaceStationView extends JPanel {
    DamageView damageView;
    HangarView hangarView;
    
    
    /**
     * Constructor por defecto de la clase
     */
    public SpaceStationView() {
        initComponents();
        
        repaint();
        revalidate();
    }
    
    /**
     * @brief Asocia al panel un objeto ToUI de SpaceStation
     * @param station : Objeto SpaceStationToUI que se le pasará a la vista
     */
    void setSpaceStation(SpaceStationToUI station){
        stationName.setText(station.getName());
        stationName.setBackground(Color.gray);
        stationName.setOpaque(true);
        
        fireStation.setText(Float.toString(station.getAmmoPower()));
        shieldStation.setText(Float.toString(station.getShieldPower()));
        fuelStation.setText(Float.toString(station.getFuelUnits()));
        medalsStation.setText(Integer.toString(station.getnMedals()));
        
        ArrayList<WeaponToUI> weapons = station.getWeapons();
        ArrayList<ShieldToUI> shieldBoosters = station.getShieldBoosters();
        
        panelWeaponsMounted.removeAll();
        panelShieldsMounted.removeAll();
        panelHangarStation.removeAll();
        pendingDamage.removeAll();
        
        WeaponView weaponView;
        for(WeaponToUI w : weapons){
            weaponView = new WeaponView();
            weaponView.setWeapon(w);
            panelWeaponsMounted.add(weaponView);
        }
        
        ShieldView shieldView;
        for(ShieldToUI s : shieldBoosters){
            shieldView = new ShieldView();
            shieldView.setShield(s);
            panelShieldsMounted.add(shieldView);
        }
        
        damageView = new DamageView();
        damageView.setDamage(station.getPendingDamage());
        pendingDamage.add(damageView);
             
        hangarView = new HangarView(); 
        hangarView.setHangar(station.getHangar());
        panelHangarStation.add(hangarView);
        
        enabledButtons(station);
        
        repaint();
        revalidate();
    }
    
    /**
     * @brief Determina la disponibilidad de los botones de las acciones de la estación
     * espacial en función del estado del juego y de los elementos de la estación
     * @param station : Objeto SpaceStationToUI
     */
    void enabledButtons(SpaceStationToUI station){
        boolean hangarAvaiable = station.getHangar() != null;
        boolean shieldsInHangar = false;
        boolean weaponsInHangar = false;
        boolean elementsInHangar = false;
        if(hangarAvaiable){
            weaponsInHangar = (station.getHangar().getWeapons().size() != 0);
            shieldsInHangar = (station.getHangar().getShieldBoosters().size() != 0);
            
            elementsInHangar = weaponsInHangar || shieldsInHangar;
        }
        boolean weaponsMounted = station.getWeapons().size() != 0;
        boolean shieldsMounted = station.getShieldBoosters().size() != 0;
        
        GameState gameState = Controller.getInstance().getState();
        boolean init = gameState == GameState.INIT;
        boolean aftercombat = gameState == GameState.AFTERCOMBAT;
        
        discardHangarButton.setEnabled(hangarAvaiable && (init  || aftercombat));
        mountButton.setEnabled(hangarAvaiable && elementsInHangar && (init  || aftercombat));
        discardButton.setEnabled((elementsInHangar || weaponsMounted || shieldsMounted) && (init  || aftercombat));
        
    }
    
    /**
     * @brief Determina cuáles son las armas montadas seleccionadas por el usuario
     * @return ArrayList con los índices de las armas montadas seleccionadas por el usuario
     */
    ArrayList<Integer> getSelectedWeaponsMounted(){
        ArrayList<Integer> selectedWeaponsMounted = new ArrayList<>();
        
        int i = 0;
        for (Component c : panelWeaponsMounted.getComponents()) {
            if (((CombatElementView) c).isSelected()) {
                selectedWeaponsMounted.add(i);
            }
            i++;
        }
        
        return selectedWeaponsMounted;
    }
    
    /**
     * @brief Determina cuáles son los escudos montados seleccionados por el usuario
     * @return ArrayList con los índices de los escudos montados seleccionados por el usuario
     */
    ArrayList<Integer> getSelectedShieldsMounted(){
        ArrayList<Integer> selectedShieldsMounted = new ArrayList<>();
        
        int i = 0;
        for (Component c : panelShieldsMounted.getComponents()) {
            if (((CombatElementView) c).isSelected()) {
                selectedShieldsMounted.add(i);
            }
            i++;
        }
        
        return selectedShieldsMounted;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stationName = new javax.swing.JLabel();
        fireTitle = new javax.swing.JLabel();
        shieldTitle = new javax.swing.JLabel();
        fuelTitle = new javax.swing.JLabel();
        medalsTitle = new javax.swing.JLabel();
        fireStation = new javax.swing.JLabel();
        medalsStation = new javax.swing.JLabel();
        shieldStation = new javax.swing.JLabel();
        fuelStation = new javax.swing.JLabel();
        shieldsMounted = new javax.swing.JScrollPane();
        panelShieldsMounted = new javax.swing.JPanel();
        weaponsMounted = new javax.swing.JScrollPane();
        panelWeaponsMounted = new javax.swing.JPanel();
        pendingDamage = new javax.swing.JPanel();
        panelHangarStation = new javax.swing.JPanel();
        mountButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();
        discardHangarButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        stationName.setBackground(new java.awt.Color(255, 0, 0));
        stationName.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        stationName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stationName.setText("ESTACIÓN DE EJEMPLO");

        fireTitle.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        fireTitle.setText("Potencia de ataque:");

        shieldTitle.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        shieldTitle.setText("Potencia de defensa:");

        fuelTitle.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        fuelTitle.setText("Nivel de combustible:");

        medalsTitle.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        medalsTitle.setText("Medallas:");

        fireStation.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        fireStation.setText("jLabel5");

        medalsStation.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        medalsStation.setText("jLabel5");

        shieldStation.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        shieldStation.setText("jLabel5");

        fuelStation.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        fuelStation.setText("jLabel5");

        shieldsMounted.setBackground(new java.awt.Color(239, 239, 239));
        shieldsMounted.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Potenciadores de Defensa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(1, 1, 1))); // NOI18N
        shieldsMounted.setViewportView(panelShieldsMounted);

        weaponsMounted.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Potenciadores de Fuego", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(1, 1, 1))); // NOI18N
        weaponsMounted.setViewportView(panelWeaponsMounted);

        pendingDamage.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        panelHangarStation.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        mountButton.setText("Equipar");
        mountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mountButtonActionPerformed(evt);
            }
        });

        discardButton.setText("Descartar");
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        discardHangarButton.setText("Descartar Hangar Completo");
        discardHangarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardHangarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(weaponsMounted, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addComponent(shieldsMounted, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fuelTitle)
                                .addGap(18, 18, 18)
                                .addComponent(fuelStation))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(medalsTitle)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(medalsStation))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fireTitle)
                                        .addComponent(shieldTitle))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(23, 23, 23)
                                            .addComponent(fireStation))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(shieldStation)))))
                            .addComponent(stationName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(pendingDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(panelHangarStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(mountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discardHangarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stationName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fireTitle)
                            .addComponent(fireStation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(shieldTitle)
                            .addComponent(shieldStation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fuelTitle)
                            .addComponent(fuelStation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medalsTitle)
                            .addComponent(medalsStation)))
                    .addComponent(pendingDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weaponsMounted, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(shieldsMounted, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelHangarStation, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mountButton)
                    .addComponent(discardButton)
                    .addComponent(discardHangarButton))
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void discardHangarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardHangarButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Hangar Completo Descartado!!!", MainWindow.getInstance().getAppName(), JOptionPane.INFORMATION_MESSAGE);
        Controller.getInstance().discardHangar();
        
        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_discardHangarButtonActionPerformed

    private void mountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mountButtonActionPerformed
        ArrayList<Integer> weaponsSelected = new ArrayList<>();
        ArrayList<Integer> shieldsSelected = new ArrayList<>();
        
        hangarView.getSelectedInHangar(weaponsSelected, shieldsSelected);
        Controller.getInstance().mount(weaponsSelected, shieldsSelected);
        
        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_mountButtonActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        ArrayList<Integer> weaponsMountedSelected = getSelectedWeaponsMounted();
        ArrayList<Integer> shieldsMountedSelected = getSelectedShieldsMounted();
        
        ArrayList<Integer> weaponsSelectedInHangar = new ArrayList<>();
        ArrayList<Integer> shieldsSelectedInHangar = new ArrayList<>();
        
        Controller.getInstance().discard(Controller.WEAPON, weaponsMountedSelected, shieldsMountedSelected);
        Controller.getInstance().discard(Controller.SHIELD, weaponsMountedSelected, shieldsMountedSelected);
        
        if(Controller.getInstance().getUIversion().getCurrentStation().getHangar() != null){
            hangarView.getSelectedInHangar(weaponsSelectedInHangar, shieldsSelectedInHangar);
            Controller.getInstance().discard(Controller.HANGAR, weaponsSelectedInHangar, shieldsSelectedInHangar);
        }
        
        MainWindow.getInstance().updateView();
    }//GEN-LAST:event_discardButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton discardButton;
    private javax.swing.JButton discardHangarButton;
    private javax.swing.JLabel fireStation;
    private javax.swing.JLabel fireTitle;
    private javax.swing.JLabel fuelStation;
    private javax.swing.JLabel fuelTitle;
    private javax.swing.JLabel medalsStation;
    private javax.swing.JLabel medalsTitle;
    private javax.swing.JButton mountButton;
    private javax.swing.JPanel panelHangarStation;
    private javax.swing.JPanel panelShieldsMounted;
    private javax.swing.JPanel panelWeaponsMounted;
    private javax.swing.JPanel pendingDamage;
    private javax.swing.JLabel shieldStation;
    private javax.swing.JLabel shieldTitle;
    private javax.swing.JScrollPane shieldsMounted;
    private javax.swing.JLabel stationName;
    private javax.swing.JScrollPane weaponsMounted;
    // End of variables declaration//GEN-END:variables
}
