package com.javamaze.maze;
import java.io.*;
import java.sql.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

    

public class DatabaseMaze {


private static final Logger logger = LogManager.getLogger(DatabaseMaze.class);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/DatabaseMaze?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASSWORD = "archer";


        public int[][] readMazeDb(int id)  {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        int tcol=10;
        int trow=10;
        int[][] board=new int[trow][tcol];
        try {
            Class.forName("com.mysql.jdbc.Driver");
                  
            logger.trace("Connecting to database...");
            // System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String SQL = "SELECT maze_columns FROM MAZE WHERE id="+id;
            rs = stmt.executeQuery(SQL);
            logger.trace("Execute: " + SQL);
            if (rs.next())  tcol = rs.getInt(1);
            
            SQL = "SELECT maze_rows FROM MAZE WHERE id="+id;
            rs = stmt.executeQuery(SQL);
            logger.trace("Execute: " + SQL);
            if (rs.next())  trow = rs.getInt(1);
                

            SQL = "SELECT Data FROM MAZE WHERE id="+id;
            rs = stmt.executeQuery(SQL);
            logger.trace("Execute: " + SQL);
            board = new int[trow][tcol]; 
            if (rs.next()) {
         
                 
        BufferedReader reader = new BufferedReader(rs.getCharacterStream(1)); 
            String line = "";
            int row = 0;
            while((line = reader.readLine()) != null)
{
            String[] colsa = line.split(","); 
            int col = 0;
            for(String  c : colsa)
   {
            board[row][col] = Integer.parseInt(c);
            col++;
   }
            row++;
}}
           
            rs.close();
            stmt.close();
            conn.close();
            return board;
            } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

         // board = new int[trow+1][tcol+1];
        return board;


}}

        public void deleteMazeDb(int id){
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
                              
            logger.trace("Connecting to database...");
            // System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();


            String SQL = "DELETE FROM MAZE WHERE id="+id;
            logger.trace("Execute: " + SQL);
            stmt.executeUpdate(SQL);

     
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            logger.error("An error occurred.");
            se.printStackTrace();
        } catch (Exception e) {
            logger.error("An error occurred.");
            e.printStackTrace();

         }}


        public void writeMazeDb(int[][] maze_data)  {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        int rows = maze_data.length;
        int cols = maze_data[0].length;
        try {


            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < rows; i++)//for each row
            {
                for(int j = 0; j < cols; j++)//for each column
                {
                    builder.append(maze_data[i][j]+"");//append to the output string
                        if(j < maze_data[0].length - 1)//if this is not the last row element
                            builder.append(",");//add comma separator
                }
                builder.append("\n");//append new line at the end of the row
             }


            Class.forName("com.mysql.jdbc.Driver");
               
            logger.trace("Connecting to database...");
            // System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            createMazeTable(stmt);

            
            String SQL = "INSERT INTO MAZE (maze_columns,maze_rows,data) VALUES  (?,?,?)";
            pstmt = conn.prepareStatement(SQL);
            // pstmt.setInt(1, key);
            pstmt.setInt(1, cols);
            pstmt.setInt(2, rows);
            pstmt.setNString(3,builder.toString());
            pstmt.execute();





            // rss.close();
            stmt.close();
            pstmt.close();
            conn.close();
            // dddd.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
            logger.error("An error occurred.");
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
  
    }

    public static void createMazeTable(Statement statement) throws SQLException {
        
        try {
              logger.trace("Creating MAZE table...");
        // System.out.println("Creating MAZE table...");
        String SQL = "CREATE TABLE MAZE " +
                "(id INT NOT NULL AUTO_INCREMENT, maze_columns INT, maze_rows INT, data LONG,PRIMARY KEY (id))";
            logger.trace("Execute:" + SQL);    
           statement.executeUpdate(SQL);
        //     statement.executeUpdate("DROP TABLE MAZE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
    }
}

           
           