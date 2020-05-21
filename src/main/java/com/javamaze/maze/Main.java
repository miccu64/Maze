package com.javamaze.maze;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;



public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args)  {

       logger.trace("Entering application.");
        int cols = 11;
        int rows = 11;
        MakeMaze myMaze = new MakeMaze(cols, rows);
        PrintMaze print = new PrintMaze();
        int maze[][]=myMaze.getMaze();
        // int maze[][]=new int[rows][cols];




/*
DatabaseMaze mazeDb = new DatabaseMaze();
mazeDb.writeMazeDb(cols,rows,maze);
try{
maze = mazeDb.readMazeDb(2);

}        catch (Exception e) {
            e.printStackTrace();
        } 

        
 
        print.SaveAsImage(maze);
        
        int sx=0;
        int tx=0;
        for (int a=1; a<=cols; a++)
        {
            if (maze[a][0]==0)
                sx=a;
            if (maze[a][rows]<2)
                tx=a;
        }
        MazeSolver solve = new MazeSolver();
        solve.SolveMaze(maze,sx,1,tx,rows);
        ArrayList<Cell> path = solve.GetPath();
        //print.PrintSolutionInConsole(path,cols+1,rows+1);
        print.SaveAsImageResolved(path,cols,rows);
*/
        EventQueue.invokeLater(() -> {
         var frame = new Menu();
         frame.setTitle("Maze generator");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
      });

        logger.trace("Exiting application.");
    }
}


 
  

class ImageViewerFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 700;
   private static final int DEFAULT_HEIGHT = 700;

   public ImageViewerFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      // use a label to display the images
      var label = new JLabel();
      add(label);

      // set up the file chooser
      var chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));



      // set up the menu bar
      var menuBar = new JMenuBar();
      setJMenuBar(menuBar);

      var menu = new JMenu("File");
      menuBar.add(menu);

      var openItem = new JMenuItem("Open");
      menu.add(openItem);

      var resolved = new JMenuItem("MazeReesolved");
      var unresolved = new JMenuItem("MazeUnresolved");
      menuBar.add(resolved);
      menuBar.add(unresolved);


      openItem.addActionListener(event -> {
         // show file chooser dialog
         int result = chooser.showOpenDialog(null);

         // if file selected, set it as icon of the label
         if (result == JFileChooser.APPROVE_OPTION)
         {
            String name = chooser.getSelectedFile().getPath();
            label.setIcon(new ImageIcon(name));
         }
      });

      resolved.addActionListener(event -> {
      
            ImageIcon ii =  new ImageIcon("MazeResolved.png");
            Image image = ii.getImage().getScaledInstance(600, -1, Image. SCALE_SMOOTH);
            ii=new ImageIcon(image);
            label.setIcon(ii);
         
      });

        unresolved.addActionListener(event -> {

            ImageIcon ii =  new ImageIcon("MazeUnresolved.png");
            Image image = ii.getImage().getScaledInstance(600, -1, Image. SCALE_SMOOTH);
            ii=new ImageIcon(image);        
            label.setIcon(ii);       
      });


      var exitmenu = new JMenuItem("Exit");
      menuBar.add(exitmenu);
      exitmenu.addActionListener(event -> System.exit(0));

      var exitItem = new JMenuItem("Exit");
      menu.add(exitItem);
      exitItem.addActionListener(event -> System.exit(0));





   }
}