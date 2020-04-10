package com.javamaze.maze;

public class Main {

    public static void main(String[] args) {
        int cols = 22;
        int rows = 11;
        MakeMaze myMaze = new MakeMaze(cols, rows);
        PrintMaze print = new PrintMaze();
        print.PrintInConsole(myMaze.getMaze(), rows, cols);
        print.PrintJustNumbers(myMaze.getMaze(), rows, cols);
    }
}

