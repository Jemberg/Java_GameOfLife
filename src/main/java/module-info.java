module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.json;

    exports com.example.controller;
    exports com.example to javafx.graphics;

    opens com.example.controller to javafx.graphics, javafx.base, javafx.fxml; // Allows javafx to access GUI package for controllers.
    opens com.example.model to javafx.base;
}
