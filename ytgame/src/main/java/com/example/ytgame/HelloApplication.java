package com.example.ytgame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("2D Game");
        Group root = new Group();
        final double screenWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
        final double screenHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();

        Scene scene = new Scene(root, screenWidth, screenHeight);

        stage.setScene(scene);
        stage.setResizable(false);

        GamePanel panel = new GamePanel();
        root.getChildren().add(panel);

        panel.keyH.attachToScene(scene);  // Attach key handler before starting the game loop
        panel.startGameThread(); // Start the game loop

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
