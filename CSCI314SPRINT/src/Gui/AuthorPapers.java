/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import ControllerClass.AuthorController;
import ETC.Papers;
import User.Author;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author XDpartypooper
 */
public class AuthorPapers extends javax.swing.JFrame {
    static String name;
    static String ID;
    
    /**
     * Creates new form AuthorPapers
     */
    public AuthorPapers(String name,String ID) {
        this.name=name;
        this.ID=ID;
        initComponents();
        setLocationRelativeTo(null);
        refreshSearch("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton4.setText("Main Menu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Paper Name", "Author", "Co Author", "Paper ID", "Reviewer "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        jButton1.setText("Submit new Paper");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update paper");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete paper");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Search paper");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //Back to main menu
        Author A=new Author();
        A.GotoAuMenu(name,ID);
        ClosePanel();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            //Create paper
            JFrame  f=new JFrame();
            AuthorController AC= new AuthorController();
            
            JTextField NField = new JTextField(50);
            JComboBox PTBox =new JComboBox();
            ArrayList<Author> al = AC.getAuthorsCon(ID);//get the user name of AUTHORS
            PTBox.addItem("");
            for (int i=0; i< al.size();i++)
            {
                PTBox.addItem(al.get(i).GetName());
            }
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Paper Name:"));
            myPanel.add(NField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Co_Authors:"));
            myPanel.add(PTBox);
            int n = JOptionPane.showConfirmDialog(null, myPanel,"Please Enter details paper ", JOptionPane.OK_CANCEL_OPTION);
            
            String PaperName = NField.getText();//get name of paper
            String CO_name = PTBox.getSelectedItem().toString();//get name of co author
             if(n == JOptionPane.OK_OPTION){
                if((PaperName.isEmpty()) )
                {
                    //error message pop up if empty
                    JFrame E=new JFrame();
                    JOptionPane.showMessageDialog(E,"No Paper name Entered","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    try {                  
                        AC.SubmitPaperCOn(PaperName,ID,CO_name);                   
                        refreshSearch("");
                    } catch (SQLException ex) {
                        Logger.getLogger(AuthorPapers.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
             }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorPapers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          try {
            //update
            AuthorController AC= new AuthorController();
           
            JComboBox PTBox1 =new JComboBox();
            JComboBox PTBox =new JComboBox();

            JTextField UField = new JTextField(50);
            JPanel myPanel = new JPanel();
            
            ArrayList<Papers> al = AC.ViewPaperCon("",ID);
            for (int i=0; i< al.size();i++)
            {
                PTBox1.addItem(al.get(i).GetPName());
            }
            
            ArrayList<Author> al2 = AC.getAuthorsCon(ID);//get the user name of AUTHORS
            PTBox.addItem("");
            for (int i=0; i< al2.size();i++)
            {
                PTBox.addItem(al2.get(i).GetName());
            }
            myPanel.add(new JLabel("Paper Name:"));
            myPanel.add(PTBox1);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("New Paper Name:"));
            myPanel.add(UField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Co Author:"));
            myPanel.add(PTBox);
            int n=JOptionPane.showConfirmDialog(null, myPanel,"Select and enter the details of the Updated Paper", JOptionPane.OK_CANCEL_OPTION);
            
           if(n == JOptionPane.OK_OPTION){ // 
                String PaperName = PTBox1.getSelectedItem().toString();//getPaperName    
                 String NewPaperName = UField.getText();//get username 
                 String co_Author = PTBox.getSelectedItem().toString();//getPaperName

                 AC.UpdatePaperCon(PaperName,NewPaperName,co_Author);//update paper        
                 
                 refreshSearch("");
           }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorPapers.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            //delete
            AuthorController AC= new AuthorController();
            JComboBox PTBox =new JComboBox();
            
            JPanel myPanel = new JPanel();
            
            ArrayList<Papers> al = AC.ViewPaperCon("",ID);
            for (int i=0; i< al.size();i++)
            {
                PTBox.addItem(al.get(i).GetPName());
            }
            myPanel.add(new JLabel("Paper Name:"));
            myPanel.add(PTBox);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            JOptionPane.showConfirmDialog(null, myPanel,"Select Paper you want to Delete", JOptionPane.OK_CANCEL_OPTION);
            
            String PaperName = PTBox.getSelectedItem().toString();//getPaperName
            //delete
         
            JFrame c=new JFrame();
            int a=JOptionPane.showConfirmDialog(myPanel,"Are you sure you want to delete Paper ?");

               if(a==JOptionPane.YES_OPTION)
                {
                    //final delete
                   boolean check=AC.deletePaperCon(PaperName);//delete paper                  
                        if(check==false)
                        {
                            //return type if it deletes is friggin false
                            JOptionPane.showMessageDialog(null,"Paper will be deleted!","User Deleted",JOptionPane.ERROR_MESSAGE);
                        }
                   refreshSearch("");
                }  
        } catch (SQLException ex) {
            Logger.getLogger(AuthorPapers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //search 
        String Word = jTextField1.getText();//get search word 
        refreshSearch(Word);
        Word=null;
    }//GEN-LAST:event_jButton5ActionPerformed
     public void ClosePanel()
    {
        setVisible(false);
        dispose();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AuthorPapers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthorPapers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthorPapers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthorPapers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthorPapers(name,ID).setVisible(true);
            }
        });
    }
    
    public void refreshSearch(String word)
    {
     try {
            // TODO add your handling code here:
            //refresh Tables
            DefaultTableModel tbm1= (DefaultTableModel)jTable1.getModel();
            tbm1.setRowCount(0);
            AuthorController AC= new AuthorController();
            
            
            ArrayList<Papers> al = AC.ViewPaperCon(word,ID);
            for (int i=0; i< al.size();i++)
            {
                tbm1.addRow(al.get(i).GetPaper());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewUsers.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
