module com.example.ytgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ytgame to javafx.fxml;
    exports com.example.ytgame;
}