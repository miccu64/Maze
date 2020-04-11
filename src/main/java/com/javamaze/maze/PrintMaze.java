package com.javamaze.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PrintMaze {

    public void PrintInConsole(int[][] maze) {
        int cols = maze.length;
        int rows = maze[0].length;
        System.out.print("\n");
        String str = "";
        int x;
        for (int y = 0; y < rows; ++y) {
            for (x = 0; x < cols; ++x) {
                switch (maze[x][y]) {
                    case 0:
                        if (x != cols - 1) {
                            str = "    ";
                        } else {
                            str = "   ";
                        }
                        break;
                    case 1:
                        str = "   |";
                        break;
                    case 2:
                        if (x != cols - 1) {
                            str = "___ ";
                        } else {
                            str = "___";
                        }
                        break;
                    case 3:
                        str = "___|";
                        break;
                }
                System.out.print(str);
            }
            System.out.print("\n");
        }
    }

    public void PrintJustNumbers(int[][] maze) {
        int cols = maze.length;
        int rows = maze[0].length;
        System.out.print("\n\n");
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < cols; ++x) {
                System.out.print(maze[x][y] + "\t");
            }
            System.out.print("\n");
        }
    }

    private Graphics2D Block(Graphics2D g, int x, int y, boolean vert) {
        g.setColor(Color.white);
        if (vert)//add vertical block
        {
            g.fillRect(16 * x, 16 * y, 2, 18);
        } else {
            g.fillRect(16 * x, 16 * y, 18, 2);//add horizontal block
        }
        return g;
    }

    public void SaveAsImage(int[][] maze) {
        int xmax = maze.length;
        int ymax = maze[0].length;
        int width = (xmax + 1) * 16;
        int height = (ymax + 1) * 16;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = image.createGraphics();//graphics to draw in buffered image

        //fill all the image with black
        graph.setColor(Color.black);
        graph.fillRect(0, 0, width, height);

        //print maze
        for (int y = 0; y < ymax; y++) {
            for (int x = 0; x < xmax; x++) {
                if ((maze[x][y] == 1) || (maze[x][y] == 3)) {
                    Block(graph, x + 1, y, true);
                }
                if ((maze[x][y] == 2) || (maze[x][y] == 3)) {
                    Block(graph, x, y + 1, false);
                }
            }
        }

        graph.dispose();//release resources
        File file = new File("MazeUnresolved.png");//make file
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            //Logger.getLogger(PrintMaze.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Error!");
        }
    }
}
