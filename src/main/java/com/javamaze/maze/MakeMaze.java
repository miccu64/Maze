package com.javamaze.maze;



import java.util.Random;

public class MakeMaze {

    private int[][] maze;

    public int[][] getMaze() {
        return maze;
    }

    public MakeMaze(int cols, int rows)
    {
        maze = InitMaze(cols, rows);
    }

    private int RandomTrue() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    private int[][] InitMaze(int cols, int rows) {
        //Eller's algorithm
        int unique = 1;
        int[][] maze = new int[cols][rows];
        int[][] part = new int[cols][2];
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
                } else part[x][1] = 1;//set the wall
            }

            //create bottom walls
            int a;
            int haveBot = 0;
            int inSet = 0;
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

            if (y != rows - 1) {
                for (int e = 0; e < cols; e++) {
                    maze[e][y] = part[e][1];
                    if ((part[e][1] == 3) || (part[e][1] == 2)) {
                        part[e][0] = 0;
                    }
                    part[e][1] = 0;
                }
            } else {
                for (int f = 0; f < cols - 1; f++) {//last row
                    if (part[f][1] < 2)
                        part[f][1] += 2;

                    if (part[f][0] != part[f + 1][0]) {
                        if (part[f][1] == 3)
                            part[f][1]--;
                        part[f + 1][0] = part[f][0];
                    }
                    maze[f][y] = part[f][1];
                }
                if (part[cols - 1][1] < 2)
                    part[cols - 1][1] += 2;
                maze[cols - 1][y] = part[cols - 1][1];
            }
        }
        return maze;
    }
}