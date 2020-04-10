package com.javamaze.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PrintMaze {
    public void PrintInConsole(int[][] maze) {
        int cols=maze.length;
        int rows=maze[0].length;
        System.out.print("\n");
        String str = "";
        int x;
        for (int y = 0; y < rows; ++y) {
            for (x = 0; x < cols; ++x) {
                if (maze[x][y] == 2) {
                    if (x != cols - 1)
                        str = "___ ";
                    else str = "___";
                } else if (maze[x][y] == 3) {
                    str = "___|";
                } else if (maze[x][y] == 1) {
                    str = "   |";
                } else if (maze[x][y] == 0) {
                    if (x != cols - 1)
                        str = "    ";
                    else str = "   ";
                }
                System.out.print(str);
            }
            System.out.print("\n");
        }
    }

    void PrintJustNumbers(int[][] maze) {
        int cols=maze.length;
        int rows=maze[0].length;
        System.out.print("\n\n");
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < cols; ++x) {
                System.out.print(maze[x][y] + "\t");
            }
            System.out.print("\n");
        }
    }
    
    void SaveAsImage(int[][] maze)
    {
        int width=maze.length;
        int height=maze[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = image.createGraphics();//graphics to draw in buffered image
        
        //fill all the image with black
        graph.setColor(Color.black);
        graph.fillRect(0, 0, width, height);
        
        //TODO: print maze
        
        
        graph.dispose();//release resources
        File file = new File("MazeUnresolved.png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            //Logger.getLogger(PrintMaze.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Error!");
        }
        
    }
}

