package com.javamaze.maze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class MazeSolver {

    private void FindWay(ArrayList<Cell> ways, int distance)
    {
        int[][] v = new int[12][12];
        int len=ways.size();//length of arraylist
        
        for(int a=len-1; a>=0; a--)
        {
            v[ways.get(a).x][ways.get(a).y]=ways.get(a).d;
        }
        
        for(int c=0;c<=11;c++)
        {
            for(int b=0;b<=11;b++)
            {
                System.out.print(v[b][c]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.print(ways.get(ways.size() - 1).d);
        ArrayList<Cell> result = new ArrayList<>();
        
        int tx=ways.get(len-1).x;
        int ty=ways.get(len-1).y;
        int td=ways.get(len-1).d;
        
        
        for (int g=len-1; g<0; g++)
        {
            Cell current=ways.get(g);
            for (int dir=0; dir<4; dir++)
            {
                Cell next=ways.get(g-1);
            }
        }
        
        
    }
    
    private boolean isMovePossible(int maze[][], boolean visited[][], int col, int row, int where) {
        int xmax = maze.length - 1;
        int ymax = maze[0].length - 1;
        switch (where) {
            case 0://move down
                if (row < ymax) {
                    if ((maze[col][row] < 2) && (visited[col][row + 1] == false)) {
                        return true;
                    }
                }
                break;
            case 1://move left
                if (col > 1) {
                    //col must be bigger than one, bcs in 0 is only wall info always
                    if ((maze[col - 1][row] != 1) && (maze[col - 1][row] != 3) && (visited[col - 1][row] == false)) {
                        return true;
                    }
                }
                break;
            case 2://move right
                if (col < xmax) {
                    if ((maze[col][row] != 1) && (maze[col][row] != 3) && (visited[col + 1][row] == false)) {
                        return true;
                    }
                }
                break;
            case 3://move up
                if (row > 1) {
                    //row must be bigger than one, bcs in 0 is only wall info always
                    if ((maze[col][row - 1] < 2) && (visited[col][row - 1] == false)) {
                        return true;
                    }
                }
                break;
            default:
                return false;
        }
        return false;
    }

    //sx1 and sy1 - start x and start y
    //tx and ty - target x and target y
    public void SolveMaze(int[][] maze, int sx1, int sy1, int tx, int ty) {
        //Lee's algorithm to solve maze
        int cols = maze.length;
        int rows = maze[0].length;
        //matrix keeping info if that cell was visited
        boolean[][] visited = new boolean[cols][rows];
        Queue<Cell> q = new ArrayDeque<>();//queue
        ArrayList<Cell> ways = new ArrayList<>();//for find the shortest way
        int sx = sx1;
        int sy = sy1;
        int d = 1;//distance
        int mind = 0;//minimum distance
        //add starting point to queue and set as visited
        visited[sx][sy] = true;
        q.add(new Cell(sx, sy, d));
        ways.add(new Cell(sx, sy, d));

        while (!q.isEmpty()) {
            Cell cell = q.poll();//pop front cell and delete it from queue
            //take info from cell
            sx = cell.x;
            sy = cell.y;
            d = cell.d;

            //if target is reached, end finding the way
            if ((sx == tx) && (sy == ty)) {
                ways.add(new Cell(sx, sy, d));//adds final element
                mind = d;
                break;
            }
            //check 4 possible moves from current cell and add to queue if valid
            for (int direction = 0; direction < 4; direction++) {
                if (isMovePossible(maze, visited, sx, sy, direction) == true) {
                    //add next cell to queue and make it visited
                    switch (direction) {
                        case 0://move down
                            q.add(new Cell(sx, sy + 1, d + 1));
                            ways.add(new Cell(sx, sy + 1, d + 1));
                            visited[sx][sy + 1] = true;
                            break;
                        case 1://move left
                            q.add(new Cell(sx - 1, sy, d + 1));
                            ways.add(new Cell(sx - 1, sy, d + 1));
                            visited[sx - 1][sy] = true;
                            break;
                        case 2://move right
                            q.add(new Cell(sx + 1, sy, d + 1));
                            ways.add(new Cell(sx + 1, sy, d + 1));
                            visited[sx + 1][sy] = true;
                            break;
                        case 3://move up
                            q.add(new Cell(sx, sy - 1, d + 1));
                            ways.add(new Cell(sx, sy - 1, d + 1));
                            visited[sx][sy - 1] = true;
                            break;
                    }
                }
            }
        }
        if (mind != 0) {
            System.out.print("Dlugosc najkrotszej drogi: " + (mind + 1));
        } else {
            System.out.print("Nie znaleziono sciezki!");
        }
        
        FindWay(ways,mind);
    }
}
