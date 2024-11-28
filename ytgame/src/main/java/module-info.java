module com.example.ytgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ytgame to javafx.fxml;
    exports com.example.ytgame;
}