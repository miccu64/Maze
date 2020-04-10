package com.javamaze.maze;

public class PrintMaze {
    public void PrintInConsole(int[][] maze, int rows, int cols) {
        System.out.print(" ");
        for (int a = 0; a < cols; a++)
            System.out.print("___ ");
        System.out.print("\n");
        String str = "";
        int x;
        for (int y = 0; y < rows; ++y) {
            System.out.print("|");
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
            if ((maze[cols - 1][y] != 1) && (maze[cols - 1][y] != 3))
                System.out.print("|");
            System.out.print("\n");
        }
    }

    void PrintJustNumbers(int[][] maze, int rows, int cols) {
        System.out.print("\n\n");
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < cols; ++x) {
                System.out.print(maze[x][y] + "\t");
            }
            System.out.print("\n");
        }
    }
}

