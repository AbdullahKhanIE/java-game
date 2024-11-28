package com.example.ytgame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class GamePane extends Pane implements Runnable{
        // Screen Settings
        final int originalTileSize = 16; // 16x16 tile
        final int scale = 3;
        final int tileSize = originalTileSize * scale; // 48x48 tile
        final int maxScreenCol = 16;
        final int maxScreenRow = 12;
        final int screenWidth = tileSize * maxScreenCol; // 768 pixels
        final int screenHeight = tileSize * maxScreenRow; // 576 pixels

        private final Canvas canvas;

        Thread gameThread;

        public GamePane() {
            // Set preferred size by creating a Canvas
            this.setPrefSize(screenWidth, screenHeight);

            // Set background color
            this.setStyle("-fx-background-color: black;");

            // Create a canvas for double buffering
            canvas = new Canvas(screenWidth, screenHeight);
            this.getChildren().add(canvas); // Add canvas to the pane

        }

        public GraphicsContext getGraphicsContext() {
            return canvas.getGraphicsContext2D(); // To draw on the canvas
        }

        public void startGameThread() {
            gameThread.start();
        }

    @Override
    public void run() {
        while (gameThread!=null) {
            System.out.println("game loop is running");
        }
    }
}