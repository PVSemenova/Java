package com.test.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.sql.PreparedStatement;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, Number> colId;
    @FXML private TableColumn<Book, String> colTitle;
    @FXML private TableColumn<Book, String> colAuthor;
    @FXML private TableColumn<Book, String> colGenre;
    @FXML private TableColumn<Book, Number> colYear;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colTitle.setCellValueFactory(data -> data.getValue().titleProperty());
        colAuthor.setCellValueFactory(data -> data.getValue().authorProperty().get().nameProperty());
        colGenre.setCellValueFactory(data -> data.getValue().genreProperty());
        colYear.setCellValueFactory(data -> data.getValue().publishedYearProperty());
        loadBooks();
    }

    private void loadBooks() {
        bookList.clear();
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                    "SELECT b.id, b.title, a.id AS aid, a.name, b.genre, b.published_year " +
                            "FROM Book b LEFT JOIN Author a ON b.author_id = a.id");

            while (rs.next()) {
                Author author = new Author(rs.getInt("aid"), rs.getString("name"));
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        author,
                        rs.getString("genre"),
                        rs.getInt("published_year")
                );
                bookList.add(book);
            }
            bookTable.setItems(bookList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onAddBook() {
        try {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Add New Book");
            dialog.setScene(new Scene(FXMLLoader.load(getClass().getResource("book_form.fxml"))));
            dialog.showAndWait();
            loadBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRefresh() {
        loadBooks();
    }
    @FXML
    private void onEditBook() {
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("book_form.fxml"));
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Edit Book");
            dialog.setScene(new Scene(loader.load()));

            BookFormController controller = loader.getController();
            controller.setBookToEdit(selected);

            dialog.showAndWait();
            loadBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onDeleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            System.out.println("Книга не выбрана для удаления");
            return;
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Book WHERE id = ?")) {
            stmt.setInt(1, selectedBook.getId());
            stmt.executeUpdate();
            loadBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
