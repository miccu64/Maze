package com.javamaze.maze;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int cols = 111;
        int rows = 111;
        MakeMaze myMaze = new MakeMaze(cols, rows);
        PrintMaze print = new PrintMaze();
        int maze[][]=myMaze.getMaze();
        //print.PrintJustNumbers(maze);
        //print.PrintInConsole(maze);
        
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
    }
}

