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

    public void PrintJustNumbers(int[][] maze) {
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
    
    private Graphics2D Block(Graphics2D g, int x, int y, boolean vert)
    {
        g.setColor(Color.white);
        if (vert)//add vertical block
            g.fillRect(x, y, 2, 16);
        else g.fillRect(x, y, 16, 2);//add horizontal block
        return g;
    }
    
    public void SaveAsImage(int[][] maze)
    {
        int width=maze.length*16;
        int height=maze[0].length*16;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = image.createGraphics();//graphics to draw in buffered image
        
        //fill all the image with black
        graph.setColor(Color.black);
        graph.fillRect(0, 0, width, height);
        
        //TODO: print maze
        //dziura 14 px
        //klocek 16x2 px
        for (int y=0; y<height; y=y+16)
        {
            for (int x=0; x<width; x=x+16)
            {
                //HEREEEE
            }
        }
        
        
        
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

