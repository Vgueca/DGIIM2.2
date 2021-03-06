/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI2;

import Controller.Controller;
import View.View;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.AppState;

/**
 *
 * @author Profe
 */
public class MainWindow_v2 extends JFrame implements View {
    private static MainWindow_v2 instance = null;
    
    private String appName;
    private PersonView_v2 personView;

    public static MainWindow_v2 getInstance() {
      if (instance == null) {
        instance = new MainWindow_v2();
      }
      return instance;
    }
    /**
     * Creates new form MainWindow_v2
     */
    private MainWindow_v2() {
        initComponents();
        appName = "Tutorial GUI 1.0 con otra estética";

        personView = new PersonView_v2();
        panelPersonView.add (personView);
        
        setTitle (appName);
        getContentPane().setBackground(Color.yellow);
        this.setLocationByPlatform(true);
        
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Controller.getInstance().finish(0);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbExit = new javax.swing.JButton();
        jbNext = new javax.swing.JButton();
        jbGetChecks = new javax.swing.JButton();
        jcSpendChecks = new javax.swing.JCheckBox();
        panelPersonView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        jbExit.setText("Adiós");
        jbExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExitActionPerformed(evt);
            }
        });

        jbNext.setText("Otro/a tío/a");
        jbNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNextActionPerformed(evt);
            }
        });

        jbGetChecks.setText("2 cheques más");
        jbGetChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGetChecksActionPerformed(evt);
            }
        });

        jcSpendChecks.setText("Gastar Cheques a Golpe de Ratón");
        jcSpendChecks.setOpaque(false);
        jcSpendChecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcSpendChecksActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbExit))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jbGetChecks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcSpendChecks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbNext))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelPersonView, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPersonView, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNext)
                    .addComponent(jbGetChecks)
                    .addComponent(jcSpendChecks))
                .addGap(18, 18, 18)
                .addComponent(jbExit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExitActionPerformed
        // TODO add your handling code here:
        Controller.getInstance().finish(0);
    }//GEN-LAST:event_jbExitActionPerformed

    private void jbNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNextActionPerformed
        // TODO add your handling code here:
        jcSpendChecks.setSelected(false);
        Controller.getInstance().next();
        updateView();
    }//GEN-LAST:event_jbNextActionPerformed

    private void jbGetChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGetChecksActionPerformed
        // TODO add your handling code here:
        Controller.getInstance().getNewBankChecks (2);
        updateView();
    }//GEN-LAST:event_jbGetChecksActionPerformed

    private void jcSpendChecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcSpendChecksActionPerformed
        // TODO add your handling code here:
        personView.setSpendMode (jcSpendChecks.isSelected());
    }//GEN-LAST:event_jcSpendChecksActionPerformed

    @Override
    public void updateView() {
        boolean canSpend = (Controller.getInstance().getAppState() == AppState.PERSONCANSPEND);
        if (!canSpend)
            jcSpendChecks.setSelected (false);
        jcSpendChecks.setEnabled(canSpend);
        personView.setPerson(Controller.getInstance().getModelToUI().getCurrent(), jcSpendChecks.isSelected());
    }

    @Override
    public void showView() {
        setVisible(true);
    }
    
    @Override 
    public String getAppName() {
        return appName;
    }
    
    @Override 
    public ArrayList<String> getNames() {
        NamesCapture namesCapture = new NamesCapture (this);
        return namesCapture.getNames();
    }

    @Override
    public boolean confirmExitMessage() {
        return (JOptionPane.showConfirmDialog(this, "¿Quieres cerrar esta Killer App?", getAppName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbExit;
    private javax.swing.JButton jbGetChecks;
    private javax.swing.JButton jbNext;
    private javax.swing.JCheckBox jcSpendChecks;
    private javax.swing.JPanel panelPersonView;
    // End of variables declaration//GEN-END:variables
}
