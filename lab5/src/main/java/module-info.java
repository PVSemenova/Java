module com.test.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.test.library to javafx.fxml;
    exports com.test.library;
}