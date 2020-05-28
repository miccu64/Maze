package com.javamaze.maze;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseMaze {

    private static final Logger logger = LogManager.getLogger(DatabaseMaze.class);

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://mysql.agh.edu.pl:3306";
    //static final String DATABASE_URL = "jdbc:mysql://localhost/DatabaseMaze?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String USER = "kmicek1";
    private static final String PASSWORD = "2cp8ikU6BiVRSE1Y";

    public ArrayList<String> GetMazeDb() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<String> mazes = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);

            logger.trace("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String SQL = "use kmicek1;";
            rs = stmt.executeQuery(SQL);
            //create table if not exists
            createMazeTable(stmt);
            SQL = "SELECT id, maze_columns, maze_rows, path FROM MAZE;";
            rs = stmt.executeQuery(SQL);
            logger.trace("Execute: " + SQL);
            while (rs.next()) {
                //make string which contains info about maze
                String res = "ID: ";
                res += rs.getString("id");
                res += ", cols: ";
                res += rs.getString("maze_columns");
                res += ", rows: ";
                res += rs.getString("maze_rows");
                res += ", path: ";
                res += rs.getString("path");
                mazes.add(res);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mazes;
    }

    public int[][] readMazeDb(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int tcol = 0;
        int trow = 0;
        int[][] board = null;
        try {
            Class.forName(JDBC_DRIVER);

            logger.trace("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String SQL = "use kmicek1;";
            rs = stmt.executeQuery(SQL);
            SQL = "SELECT maze_columns FROM MAZE WHERE id=" + id;
            rs = stmt.executeQuery(SQL);
            logger.trace("Execute: " + SQL);
            if (rs.next()) {
                tcol = rs.getInt(1);
            }

            SQL = "SELECT maze_rows FROM MAZE WHERE id=" + id;
            rs = stmt.executeQuery(SQL);
            logger.trace("Execute: " + SQL);
            if (rs.next()) {
                trow = rs.getInt(1);
            }

            SQL = "SELECT Data FROM MAZE WHERE id=" + id;
            rs = stmt.executeQuery(SQL);
            logger.trace("Execute: " + SQL);
            board = new int[tcol][trow];
            if (rs.next()) {

                BufferedReader reader = new BufferedReader(rs.getCharacterStream(1));
                String line = "";
                int row = 0;
                while ((line = reader.readLine()) != null) {
                    String[] colsa = line.split(",");
                    int col = 0;
                    for (String c : colsa) {
                        board[col][row] = Integer.parseInt(c);
                        col++;
                    }
                    row++;
                }
            }

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
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            return board;
        }
    }

    public void deleteMazeDb(int id) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            logger.trace("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String SQL = "use kmicek1;";
            logger.trace("Execute: " + SQL);
            stmt.executeUpdate(SQL);

            SQL = "DELETE FROM MAZE WHERE id=" + id;
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
        }
    }

    public void writeMazeDb(int[][] maze_data, int pathLen) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        int rows = maze_data[0].length;
        int cols = maze_data.length;
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < rows; i++)//for each row
            {
                for (int j = 0; j < cols; j++)//for each column
                {
                    builder.append(maze_data[j][i] + "");//append to the output string
                    if (j < cols - 1)//if this is not the last row element
                    {
                        builder.append(",");//add comma separator
                    }
                }
                builder.append("\n");//append new line at the end of the row
            }

            Class.forName(JDBC_DRIVER);

            logger.trace("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String SQL = "use kmicek1";
            pstmt = conn.prepareStatement(SQL);
            pstmt.execute();

            SQL = "INSERT INTO MAZE (maze_columns,maze_rows,path,data) VALUES  (?,?,?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, cols);
            pstmt.setInt(2, rows);
            pstmt.setInt(3, pathLen);
            pstmt.setNString(4, builder.toString());
            pstmt.execute();

            stmt.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logger.error("An error occurred.");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void createMazeTable(Statement statement) throws SQLException {
        try {
            logger.trace("Creating MAZE table if not exists...");
            String SQL = "use kmicek1;";
            logger.trace("Execute:" + SQL);
            statement.executeUpdate(SQL);
            SQL = "CREATE TABLE IF NOT EXISTS MAZE "
                    + "(id INT NOT NULL AUTO_INCREMENT, maze_columns INT NOT NULL, maze_rows INT NOT NULL, path INT NOT NULL, data LONG NOT NULL, PRIMARY KEY (id))";
            logger.trace("Execute:" + SQL);
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
