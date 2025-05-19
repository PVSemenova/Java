package com.test.library;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String URL = "jdbc:sqlite:library.sqlite";
    public static void initDB() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String sqlAuthor = "CREATE TABLE IF NOT EXISTS Author (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL" +
                    ");";
            String sqlBook = "CREATE TABLE IF NOT EXISTS Book (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, " +
                    "author_id INTEGER, " +
                    "genre TEXT, " +
                    "published_year INTEGER, " +
                    "FOREIGN KEY(author_id) REFERENCES Author(id)" +
                    ");";
            stmt.execute(sqlAuthor);
            stmt.execute(sqlBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
