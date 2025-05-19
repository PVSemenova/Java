package com.test.library;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookFormController {
    @FXML private TextField txtTitle;
    @FXML private ComboBox<Author> cmbAuthor;
    @FXML private TextField txtGenre;
    @FXML private TextField txtYear;

    private ObservableList<Author> authorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadAuthors();
    }

    private void loadAuthors() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM Author");
            while (rs.next()) {
                authorList.add(new Author(rs.getInt("id"), rs.getString("name")));
            }
            cmbAuthor.setItems(authorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSave() {
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);

            String title = txtTitle.getText().trim();
            String genre = txtGenre.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());

            String authorName = cmbAuthor.getEditor().getText().trim();
            int authorId = -1;

            if (authorName.isEmpty()) {
                throw new IllegalArgumentException("Требуется имя автора");
            }


            String checkSql = "SELECT id FROM Author WHERE name = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, authorName);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    authorId = rs.getInt("id");
                } else {
                    String insertAuthor = "INSERT INTO Author(name) VALUES(?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertAuthor, Statement.RETURN_GENERATED_KEYS)) {
                        insertStmt.setString(1, authorName);
                        insertStmt.executeUpdate();
                        ResultSet keys = insertStmt.getGeneratedKeys();
                        if (keys.next()) {
                            authorId = keys.getInt(1);
                        }
                    }
                }
            }


            String insertBook = "INSERT INTO Book(title, author_id, genre, published_year) VALUES(?,?,?,?)";
            try (PreparedStatement bookStmt = conn.prepareStatement(insertBook)) {
                bookStmt.setString(1, title);
                bookStmt.setInt(2, authorId);
                bookStmt.setString(3, genre);
                bookStmt.setInt(4, year);
                bookStmt.executeUpdate();
            }

            conn.commit();
            close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @FXML
    private void onCancel() {
        close();
    }

    private void close() {
        ((Stage) txtTitle.getScene().getWindow()).close();
    }

    private Book bookToEdit;

    public void setBookToEdit(Book book) {
        this.bookToEdit = book;
        txtTitle.setText(book.getTitle());
        txtGenre.setText(book.getGenre());
        txtYear.setText(String.valueOf(book.getPublishedYear()));

        cmbAuthor.getSelectionModel().select(book.getAuthor());
    }
}
