package com.javamaze.maze;

public class Main {

    public static void main(String[] args) {
        int cols = 11;
        int rows = 11;
        MakeMaze myMaze = new MakeMaze(cols, rows);
        PrintMaze print = new PrintMaze();
        int maze[][]=myMaze.getMaze();
        print.PrintJustNumbers(maze);
        print.PrintInConsole(maze);
        
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
    }
}

