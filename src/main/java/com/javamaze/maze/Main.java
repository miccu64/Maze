package com.javamaze.maze;

public class Main {

    public static void main(String[] args) {
        int cols = 111;
        int rows = 111;
        MakeMaze myMaze = new MakeMaze(cols, rows);
        PrintMaze print = new PrintMaze();
        print.PrintInConsole(myMaze.getMaze());
        print.PrintJustNumbers(myMaze.getMaze());
        print.SaveAsImage(myMaze.getMaze());
    }
}

