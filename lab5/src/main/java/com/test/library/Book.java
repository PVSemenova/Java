package com.test.library;


import javafx.beans.property.*;

public class Book {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<Author> author = new SimpleObjectProperty<>();
    private final StringProperty genre = new SimpleStringProperty();
    private final IntegerProperty publishedYear = new SimpleIntegerProperty();


    public Book(int id, String title, Author author, String genre, int year) {
        this.id.set(id);
        this.title.set(title);
        this.author.set(author);
        this.genre.set(genre);
        this.publishedYear.set(year);
    }
    public int getId() {
        return id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public IntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }
    public void setTitle(String title) {
        this.title.set(title);
    }
    public StringProperty titleProperty() {
        return title;
    }

    public Author getAuthor() {
        return author.get();
    }
    public void setAuthor(Author author) {
        this.author.set(author);
    }
    public ObjectProperty<Author> authorProperty() {
        return author;
    }

    public String getGenre() {
        return genre.get();
    }
    public void setGenre(String genre) {
        this.genre.set(genre);
    }
    public StringProperty genreProperty() {
        return genre;
    }

    public int getPublishedYear() {
        return publishedYear.get();
    }
    public void setPublishedYear(int year) {
        this.publishedYear.set(year);
    }
    public IntegerProperty publishedYearProperty() {
        return publishedYear;
    }
}



