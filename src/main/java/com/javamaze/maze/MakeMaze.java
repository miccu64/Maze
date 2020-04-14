package com.javamaze.maze;

import java.util.Random;

public class MakeMaze {

    private final int[][] finalMaze;

    public int[][] getMaze() {
        return finalMaze;
    }

    public MakeMaze(int cols, int rows) {
        finalMaze = InitMaze(cols, rows);
    }

    //generate true or false (0 or 1)
    private int RandomTrue() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    //generate random int from <min, max>
    private int RandomInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    //generate maze
    private int[][] InitMaze(int cols, int rows) {
        //Eller's algorithm
        int unique = 1;
        int[][] maze = new int[cols][rows];
        int[][] part = new int[cols][2];
        //part[col][0] stores a set
        //part[col][1] stores info about walls (0,1,2,3):
        //0=no walls, 1=right wall, 2=bottom wall, 3=right+bottom walls

        int x;
        for (int y = 0; y < rows; y++) {
            for (x = 0; x < cols; x++) {
                //when lack of set, then add unique value to it
                if (part[x][0] == 0) {
                    part[x][0] = unique;
                    unique++;
                }
            }

            //create right walls
            for (x = 0; x < cols - 1; x++) {
                if (part[x + 1][0] == part[x][0])//prevents loops
                {
                    part[x][1] = 1;
                } else if (RandomTrue() == 1)//randomly decide if union walls
                {
                    part[x + 1][0] = part[x][0];//union the sets
                    part[x][1] = 0;//zero the wall
                } else {
                    part[x][1] = 1;//set the wall
                }
                if(x==cols-2)
                    part[x+1][1] = 1;//set the right wall on right side of maze
            }

            //create bottom walls
            int a;
            int haveBot = 0;//elements that have a bottom wall in a row in one set
            int inSet = 0;//members of set in a row
            for (x = 0; x < cols; x++) {
                for (a = 0; a < cols; a++) {
                    if (part[x][0] == part[a][0]) {
                        inSet++;//how many fields are in set (set = row)
                        if ((part[a][1] == 2) || (part[a][1] == 3)) {
                            haveBot++;//how many fields in set have a bottom wall
                        }
                    }
                }
                if (((inSet - haveBot) > 1) && (RandomTrue() == 1)) {
                    part[x][1] += 2;
                }
                inSet = 0;
                haveBot = 0;
            }
            
            //write data from 2nd row to the 1st row
            //generating that way is much better than store one big matrix
            if (y != rows - 1) {
                for (int e = 0; e < cols; e++) {
                    maze[e][y] = part[e][1];
                    if ((part[e][1] == 3) || (part[e][1] == 2)) {
                        part[e][0] = 0;
                    }
                    part[e][1] = 0;
                }
            } else {
                //last row - end the maze
                for (int f = 0; f < cols - 1; f++) {
                    //remove some right walls
                    if (part[f][0] != part[f + 1][0]) {
                        if ((part[f][1] == 3) || (part[f][1] == 1)) {
                            part[f][1]--; 
                        }
                        //union the sets
                        int search = part[f+1][0];
                        for (int aa=0; aa < cols; aa++)
                        {
                            if (part[aa][0]==search)
                                part[aa][0]=part[f][0];
                        }
                    }
                    //add a bottom wall to every cell
                    if (part[f][1] < 2) {
                        part[f][1] += 2;
                    }
                    
                    maze[f][y] = part[f][1];
                }
                //add right and bottom wall to last element
                maze[cols - 1][y] = 3;
            }
        }

        //add top wall with one hole, add left wall, add hole in bottom wall
        int width = cols + 1;
        int height = rows + 1;
        int[][] maze2 = new int[width][height];
        int num = RandomInt(1, width-1);

        //add top wall with one hole
        for (int g = 1; g < width; g++) {
            if (g != num) {
                maze2[g][0] = 2;
            }
        }

        //add left walls everywhere
        for (int h = 1; h < height; h++) {
            maze2[0][h] = 1;
        }

        //copy data to maze2
        for (int j = 1; j < height; j++) {
            for (int i = 1; i < width; i++) {
                maze2[i][j] = maze[i - 1][j - 1];
            }
        }

        //add hole in a bottom wall
        num = RandomInt(1, width-1);
        maze2[num][height - 1] -= 2;
        
        return maze2;
    }
}
