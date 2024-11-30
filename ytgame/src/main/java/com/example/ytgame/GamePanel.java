package com.example.ytgame;

import entity.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class GamePanel extends Pane implements Runnable {

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile

    public final double screenWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
    public final double screenHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();

    Canvas canvas;
    GraphicsContext gc;

    // FPS
    int Fps = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    public GamePanel() {
        // Set preferred size by creating a Canvas
        this.setPrefSize(screenWidth, screenHeight);
        this.setStyle("-fx-background-color: #000000;");

        // Create a canvas for double buffering
        canvas = new Canvas(screenWidth, screenHeight);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas); // Add canvas to the pane
    }

    // Start the game thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / Fps; // Time per frame in nanoseconds
        long lastTime = System.nanoTime();
        double delta = 0;

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {

                update();  // Update game state
                paintComp(); // Render to the canvas


                delta--;
            }

            try {
                // Control the game's FPS, sleep for the remaining time
                Thread.sleep(2); // Small sleep to reduce CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComp() {
        gc.clearRect(0, 0, screenWidth, screenHeight); // Clear screen
        player.draw(gc); // Draw player
    }
}
