package com.test.library;

import javafx.beans.property.*;

public class Author {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();

    public Author(int id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public int getId() {
        return id != null ? id.get() : 0;
    }
    public void setId(int id) {
        this.id.set(id);
    }
    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return name.get();
    }
}
