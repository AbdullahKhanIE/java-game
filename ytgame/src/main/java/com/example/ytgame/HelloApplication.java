package com.example.ytgame;

import entity.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("2D Game");
        Group root = new Group();

        GamePanel panel = new GamePanel();
        Scene scene = new Scene(root, panel.screenWidth, panel.screenHeight);

        stage.setScene(scene);
        stage.setResizable(false);

        root.getChildren().add(panel);

        panel.keyH.attachToScene(scene);  // Attach key handler before starting the game loop
        panel.startGameThread(); // Start the game loop

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
