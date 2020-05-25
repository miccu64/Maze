package com.javamaze.maze;
import java.io.*;
import java.sql.*;

public class DatabaseMaze {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/DatabaseMaze?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "";
    static final String PASSWORD = "";


        public int[][] readMazeDb(int id) throws ClassNotFoundException, SQLException, java.io.IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        int tcol=0;
        int trow=0;
            Class.forName("com.mysql.jdbc.Driver");
 
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String SQL = "SELECT maze_columns FROM MAZE WHERE id="+id;
            rs = stmt.executeQuery(SQL);
            if (rs.next()) { tcol = rs.getInt(1);
             System.out.println("cols"+tcol);}

            SQL = "SELECT maze_rows FROM MAZE WHERE id="+id;
            rs = stmt.executeQuery(SQL);
            if (rs.next()) { trow = rs.getInt(1);
                System.out.println("rows"+trow);}

            SQL = "SELECT Data FROM MAZE WHERE id="+id;
            rs = stmt.executeQuery(SQL);
            int[][] board = new int[trow+1][tcol+1];// TODO: get zise freom DB 
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


}


        public void writeMazeDb(int[][] maze_data)  {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        int cols = maze_data.length;
        int rows = maze_data[0].length;
        try {


            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < cols; i++)//for each row
            {
                for(int j = 0; j < rows; j++)//for each column
                {
                    builder.append(maze_data[i][j]+"");//append to the output string
                        if(j < maze_data.length - 1)//if this is not the last row element
                            builder.append(",");//add comma separator
                }
                builder.append("\n");//append new line at the end of the row
             }


            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
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
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
//           catch (IOException e) {
//              System.out.println("An error occurred.");
//           e.printStackTrace();
// }
        }
  
    }

    public static void createMazeTable(Statement statement) throws SQLException {
        
        try {System.out.println("Creating MAZE table...");
        String SQL = "CREATE TABLE MAZE " +
                "(id INT NOT NULL AUTO_INCREMENT, maze_columns INT, maze_rows INT, data LONG,PRIMARY KEY (id))";
           statement.executeUpdate(SQL);
        //     statement.executeUpdate("DROP TABLE MAZE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
    }
}
