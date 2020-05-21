
package com.javamaze.maze;


public class GenSaveSolve {
    public GenSaveSolve(int cols, int rows) {
        //generate maze
        MakeMaze myMaze = new MakeMaze(cols, rows);
        int [][] maze = myMaze.getMaze();
        //print unresolved maze as image
        PrintMaze print = new PrintMaze();
        print.SaveAsImage(maze);
        
        //find entrance and exit
        int sx=0;
        int tx=0;
        for (int a=1; a<=cols; a++)
        {
            if (maze[a][0]==0)
                sx=a;
            if (maze[a][rows]<2)
                tx=a;
        }
        
        //solve maze
        MazeSolver solve = new MazeSolver();
        solve.SolveMaze(maze,sx,1,tx,rows);
        print.SaveAsImageResolved(solve.GetPath(), cols, rows);
    }
}
