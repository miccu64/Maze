package com.javamaze.maze;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class MazeSolver {

static final Logger logger = LogManager.getLogger(MazeSolver.class);

    private final ArrayList<Cell> Path = new ArrayList<>();
    private int mind;//minimum distance
    
    public ArrayList<Cell> GetPath() {
        return Path;
    }
    
    public int GetMinLength() {
        return mind;
    }



    private boolean FindPath(int distance[][], int col, int row, int where, int[][] maze) {


        // logger.entry();

        int xmax = distance.length - 1;
        int ymax = distance[0].length - 1;
        switch (where) {
            case 0://move down
                if (row < ymax) {
                    if ((distance[col][row] == (distance[col][row + 1] + 1)) && (maze[col][row]) < 2) {
                        return true;
                    }
                }
                break;
            case 1://move left
                if (col > 1) {
                    if ((distance[col][row] == (distance[col - 1][row] + 1)) && (maze[col - 1][row] != 1) && (maze[col - 1][row] != 3)) {
                        return true;
                    }
                }
                break;
            case 2://move right
                if (col < xmax) {
                    if ((distance[col][row] == (distance[col + 1][row] + 1)) && (maze[col][row] != 1) && (maze[col][row] != 3)) {
                        return true;
                    }
                }
                break;
            case 3://move up
                if (row > 1) {
                    if ((distance[col][row] == (distance[col][row - 1] + 1)) && (maze[col][row - 1] < 2)) {
                        return true;
                    }
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private void FindWay(ArrayList<Cell> ways, int cols, int rows, int[][] maze) {
        int[][] distance = new int[cols][rows];
        int len = ways.size();//length of arraylist

        for (int a = len - 1; a >= 0; a--) {
            distance[ways.get(a).x][ways.get(a).y] = ways.get(a).d;
        }
        /*
        for(int c=0;c<rows;c++)//print how program works
        {
            for(int b=0;b<cols;b++)
            {
                System.out.print(distance[b][c]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }*/
        System.out.print("\n");

        int tx = ways.get(len - 1).x;//x of target 
        int ty = ways.get(len - 1).y;//y of target 
        int td = ways.get(len - 1).d;//distance of target 
        Path.add(new Cell(tx, ty, td));//add final point to path

        //list with directions to make them randomly chosen
        ArrayList<Integer> direction = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            direction.add(i);
        }
        boolean found = false;

        int cx = tx;//current x
        int cy = ty;//current y
        for (int cd = td - 1; cd > 0; cd--)//cd - current distance
        {
            found = false;
            Collections.shuffle(direction);//random direction
            for (int dir : direction) {
                //find neighbour element with distance smaller by 1
                if (FindPath(distance, cx, cy, dir, maze) == true) {
                    switch (dir) {
                        case 0://move down
                            cy += 1;
                            break;
                        case 1://move left
                            cx -= 1;
                            break;
                        case 2://move right
                            cx += 1;
                            break;
                        case 3://move up
                            cy -= 1;
                            break;
                    }
                    Path.add(new Cell(cx, cy, cd));//add to path
                    found = true;
                    break;
                }
            }
            if (found == false) {
                Path.clear();
                FindWay(ways, cols, rows, maze);
                return;
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
        mind = 0;//minimum distance
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
            logger.info("Length of the shortest path: " + (mind + 1));
            System.out.print("Length of the shortest path: " + (mind + 1));
        } else {
            logger.info("Can't find path!");
            System.out.print("Can't find path!");
        }

        FindWay(ways, cols, rows, maze);//reconstruct shortest way
       
        
    }
}
