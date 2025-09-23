module org.example.supermarket {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires javafx.base;

    opens org.example.supermarket to javafx.fxml;
    exports org.example.supermarket;
}