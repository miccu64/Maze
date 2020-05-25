package com.javamaze.maze;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ShowMaze extends javax.swing.JPanel implements ComponentListener {
//componentListener listens to changes in size of window, then resize image

    private Image image;
    private Image imageOriginal;
    private Dimension newSize;
    private int inset;//size of top title panel
    private double x1 = -1, x2 = -1, y1 = -1, y2 = -1;//coordinates
    private int[][] maze;
    private double scale;//scale for fitting image
    private int origPathLen;//length of solved image

    //function for rescaling image
    private void Resized() {
        //-200 is a offset for buttons etc.
        double size2 = (newSize.getWidth() - 200) / imageOriginal.getWidth(this);
        double size1 = (newSize.getHeight() - inset) / imageOriginal.getHeight(this);
        if (size2 < size1) {
            scale = size2;
        } else {
            scale = size1;
        }
        //scaled dimensions of image
        double sc1 = scale * imageOriginal.getWidth(this);
        double sc2 = scale * imageOriginal.getHeight(this);
        image = null;
        image = imageOriginal.getScaledInstance((int) sc1, (int) sc2, Image.SCALE_DEFAULT);
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        newSize = e.getComponent().getBounds().getSize();
        Resized();
    }

    private int ScaleCoords(int num) {

        return 0;
    }

    public ShowMaze(int ins, int[][] myMaze, int len) {
        File imageFile = new File("MazeResolved.png");
        maze = myMaze;
        inset = ins;
        origPathLen = len;
        initComponents();
        try {
            image = ImageIO.read(imageFile);
            imageOriginal = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Error while loading maze!");
            e.printStackTrace();
        }
        PathLength.setText(String.valueOf((int) origPathLen));
        addMouseListener(new MouseAdapter() {
            //scale number of pixels after click
            @Override
            public void mousePressed(MouseEvent e) {
                //-1, bcs it have +1 special col and row for walls
                int mazeWidth = maze.length - 1;
                int mazeHeight = maze[0].length - 1;
                System.out.println(mazeWidth + " " + mazeHeight);
                if (x1 < 0) {
                    x1 = Math.round(e.getX() / 16 / scale);
                    y1 = Math.round(e.getY() / 16 / scale);
                    System.out.println((x1) + " " + (y1));
                    if ((x1 < 1) || (x1 > mazeWidth) || (y1 < 1) || (y1 > mazeHeight)) {
                        x1 = y1 = -1;
                    } else {
                        //set text to text field
                        StartX.setText(String.valueOf((int) x1));
                        StartY.setText(String.valueOf((int) y1));
                        FinishX.setText("-");
                        FinishY.setText("-");
                        PathLength.setText("-");
                    }
                } else if (x2 < 0) {
                    x2 = Math.round(e.getX() / 16 / scale);
                    y2 = Math.round(e.getY() / 16 / scale);
                    if ((x2 < 1) || (x2 > mazeWidth) || (y2 < 1) || (y2 > mazeHeight)) {
                        x2 = y2 = -1;
                    } else {
                        System.out.println((x1) + " " + (y1) + " " + (x2) + " " + (y2));
                        System.out.println(Math.round(x1) + " " + Math.round(y1) + " " + Math.round(x2) + " " + Math.round(y2));
                        FinishX.setText(String.valueOf((int) x2));
                        FinishY.setText(String.valueOf((int) y2));
                        MazeSolver solve = new MazeSolver();
                        solve.SolveMaze(maze, (int) x1, (int) y1, (int) x2, (int) y2);
                        PrintMaze print = new PrintMaze();
                        print.SaveAsImageResolved(solve.GetPath(), false);
                        imageOriginal = print.GetImg();
                        PathLength.setText(String.valueOf(solve.GetMinLength()));
                        //resize result and refresh JPanel 
                        Resized();
                        revalidate();
                        repaint();
                        //init values again with -1
                        x1 = x2 = y1 = y2 = -1;
                    }
                }
            }
        });
    }

    //show image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StartX = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        StartY = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        FinishX = new javax.swing.JTextField();
        FinishY = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        PathLength = new javax.swing.JTextField();
        Solved = new javax.swing.JButton();
        Unresolved = new javax.swing.JButton();

        StartX.setEditable(false);
        StartX.setText("-");
        StartX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartXActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html><div style='text-align: center; font-size: 12px;'>Click to mark<br>a start or finish</div></html>");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Start X");

        jLabel3.setText("Start Y");

        StartY.setEditable(false);
        StartY.setText("-");
        StartY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartYActionPerformed(evt);
            }
        });

        jLabel4.setText("Finish X");

        jLabel5.setText("Finish Y");

        FinishX.setEditable(false);
        FinishX.setText("-");
        FinishX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishXActionPerformed(evt);
            }
        });

        FinishY.setEditable(false);
        FinishY.setText("-");
        FinishY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishYActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("<html><div style=\"text-align: center;\">Minimum distance<br>beetween points</div></html>");

        PathLength.setEditable(false);
        PathLength.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        PathLength.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PathLength.setText("-");
        PathLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PathLengthActionPerformed(evt);
            }
        });

        Solved.setText("Show solved maze");
        Solved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SolvedActionPerformed(evt);
            }
        });

        Unresolved.setText("Show unresolved maze");
        Unresolved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnresolvedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(329, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(PathLength, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(FinishX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(21, 21, 21)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(FinishY, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(StartX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(StartY, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(36, 36, 36))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Solved)
                        .addGap(20, 20, 20))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(332, Short.MAX_VALUE)
                    .addComponent(Unresolved)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StartY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FinishX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FinishY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PathLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(Solved)
                .addGap(56, 56, 56))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(275, 275, 275)
                    .addComponent(Unresolved)
                    .addContainerGap(93, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StartXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StartXActionPerformed

    private void StartYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StartYActionPerformed

    private void FinishXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinishXActionPerformed

    private void FinishYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinishYActionPerformed

    private void PathLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PathLengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PathLengthActionPerformed

    private void PrintImageAfterClick(String name) {
        File imageFile = new File(name);
        try {
            image = ImageIO.read(imageFile);
            imageOriginal = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Error while loading maze!");
        }
        Resized();
        revalidate();
        repaint();
        StartX.setText("-");
        StartY.setText("-");
        FinishX.setText("-");
        FinishY.setText("-");
        PathLength.setText("-");
        x1 = x2 = y1 = y2 = -1;
    }

    private void SolvedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SolvedActionPerformed
        PrintImageAfterClick("MazeResolved.png");
        PathLength.setText(String.valueOf((int) origPathLen));
    }//GEN-LAST:event_SolvedActionPerformed

    private void UnresolvedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnresolvedActionPerformed
        PrintImageAfterClick("MazeUnresolved.png");
    }//GEN-LAST:event_UnresolvedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FinishX;
    private javax.swing.JTextField FinishY;
    private javax.swing.JTextField PathLength;
    private javax.swing.JButton Solved;
    private javax.swing.JTextField StartX;
    private javax.swing.JTextField StartY;
    private javax.swing.JButton Unresolved;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
