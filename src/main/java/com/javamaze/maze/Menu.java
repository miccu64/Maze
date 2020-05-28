package com.javamaze.maze;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu extends javax.swing.JFrame {

    private static final Logger logger = LogManager.getLogger(Menu.class);

    private final DatabaseMaze mazeDb;
    private JFrame frame = null;

    private void PrintInList() {
        ArrayList<String> arr = mazeDb.GetMazeDb();
        //print elements in jlist
        DefaultListModel listModel = new DefaultListModel();
        for (String s : arr) {
            listModel.addElement(s);
        }
        listOfMazes.setModel(listModel);
    }

    public Menu() {

        initComponents();
        mazeDb = new DatabaseMaze();
        //print in list elements from database
        PrintInList();
        //block multiple selection
        listOfMazes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        widthField = new javax.swing.JTextField();
        heightField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        random = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        loadFromDb = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOfMazes = new javax.swing.JList<>();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        widthField.setText("50");
        widthField.setToolTipText("");
        widthField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                widthFieldActionPerformed(evt);
            }
        });

        heightField.setText("35");
        heightField.setToolTipText("");

        jLabel1.setText("Width:");

        jLabel2.setText("Height:");

        random.setText("Random values");
        random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomActionPerformed(evt);
            }
        });

        jButton2.setText("Generate maze");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMazeActionPerformed(evt);
            }
        });

        loadFromDb.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        loadFromDb.setText("Load from database");
        loadFromDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromDbActionPerformed(evt);
            }
        });

        jLabel3.setText("Size of your maze");

        listOfMazes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listOfMazes.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                listOfMazesComponentAdded(evt);
            }
        });
        jScrollPane1.setViewportView(listOfMazes);

        delete.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(random)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(widthField)
                                            .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(88, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadFromDb)
                        .addGap(18, 18, 18)
                        .addComponent(delete))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(random))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadFromDb)
                    .addComponent(delete))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void widthFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widthFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_widthFieldActionPerformed

    private void randomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomActionPerformed
        // TODO add your handling code here:
        //generate random int between <min, max> 
        Random r = new Random();
        int max = 50;
        int min = 20;
        int num = r.nextInt((max - min) + 1) + min;
        widthField.setText(String.valueOf(num));
        num = r.nextInt((max - min) + 1) + min;
        heightField.setText(String.valueOf(num));
    }//GEN-LAST:event_randomActionPerformed

    private void OpenNewWindow(GenSaveSolve doIt) {
        //close child window if exists, then generate new
        if (frame != null) {
            frame.dispose();
        }
        frame = new JFrame();
        frame.setTitle("Maze solver");
        //get heigth of top title panel of window
        Insets insets = this.getInsets();

        //show maze in JFrame using JPanel
        ShowMaze show = new ShowMaze(insets.top + insets.left, doIt.GetMaze(), doIt.GetPathLength());//JPanel
        //listener changing size of image
        frame.addComponentListener(show);
        frame.setLayout(new BorderLayout());
        frame.add(show, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void generateMazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateMazeActionPerformed
        int cols = Integer.parseInt(widthField.getText());
        int rows = Integer.parseInt(heightField.getText());
        //generate and solve maze

        logger.trace("Generate " + cols + "x" + rows + " Maze");
        GenSaveSolve doIt = new GenSaveSolve(cols, rows);

        //save maze to database
        mazeDb.writeMazeDb(doIt.GetMaze(), doIt.GetPathLength());
        //print in list elements from database
        PrintInList();
        //open new window with maze
        EventQueue.invokeLater(() -> {
            OpenNewWindow(doIt);
        });
    }//GEN-LAST:event_generateMazeActionPerformed

    private void loadFromDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromDbActionPerformed
        int mazeId = GetChoice();
        logger.trace("Load Maze " + mazeId + " from Database");
        GenSaveSolve doIt = new GenSaveSolve(mazeDb.readMazeDb(mazeId));

        EventQueue.invokeLater(() -> {
            OpenNewWindow(doIt);
        });
    }//GEN-LAST:event_loadFromDbActionPerformed

    private int GetChoice() {
        ListModel model = listOfMazes.getModel();
        String s = "";
        for (int index : listOfMazes.getSelectedIndices()) {
            s = (String) model.getElementAt(index);
        }
        if (s.isEmpty() == false) {
            //split string to get ID
            String[] splitted = s.split(" ");
            //delete comma
            splitted[1] = splitted[1].substring(0, splitted[1].length() - 1);
            return Integer.parseInt(splitted[1]);
        } else {
            logger.trace("You didn't chosen anything!");
        }
        return -1;
    }

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int mazeId = GetChoice();
        logger.trace("Delete Maze " + mazeId + " from Database");
        mazeDb.deleteMazeDb(mazeId);
        //print in list elements from database
        PrintInList();
    }//GEN-LAST:event_deleteActionPerformed

    private void listOfMazesComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_listOfMazesComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_listOfMazesComponentAdded

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JTextField heightField;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listOfMazes;
    private javax.swing.JButton loadFromDb;
    private javax.swing.JButton random;
    private javax.swing.JTextField widthField;
    // End of variables declaration//GEN-END:variables
}
