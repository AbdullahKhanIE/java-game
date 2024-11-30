package com.example.ytgame;

import entity.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GamePanel extends Pane implements Runnable {

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48 tile

    final double screenWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
    final double screenHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();

    Canvas canvas;
    GraphicsContext gc;

    // FPS
    int Fps = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    // Game State
    int playerX = 50; // Starting position of the player
    int playerY = 50;
    int playerSpeed = 4;

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
        // Movement logic
        if (keyH.upPressed) {
            playerY -= playerSpeed; // Move up
        }
        if (keyH.downPressed) {
            playerY += playerSpeed; // Move down
        }
        if (keyH.leftPressed) {
            playerX -= playerSpeed; // Move left
        }
        if (keyH.rightPressed) {
            playerX += playerSpeed; // Move right
        }

//        // Prevent the player from moving outside the screen
//        playerX = Math.max(0, Math.min(playerX, (int) screenWidth - tileSize));
//        playerY = Math.max(0, Math.min(playerY, (int) screenHeight - tileSize));
    }

    public void paintComp() {
        gc.clearRect(0, 0, screenWidth, screenHeight); // Clear screen
        gc.setFill(Color.BLUEVIOLET); // Draw the player
        gc.fillRect(playerX, playerY, tileSize, tileSize);
    }
}
