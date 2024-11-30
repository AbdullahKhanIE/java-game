package com.example.ytgame;

import entity.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import tile.TileManager;

public class GamePanel extends Pane implements Runnable {

    final int originalTileSize = 64; // 16x16 tile
    final int baseWidth = 1920;      // Base resolution width
    final int baseHeight = 1080;     // Base resolution height

    public final double screenWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth();
    public final double screenHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight();

    double xScale = screenWidth / baseWidth;
    double yScale = screenHeight / baseHeight;
    double scale = Math.min(xScale, yScale); // Uniform scaling to maintain aspect ratio

    public final int tileSize = (int) (originalTileSize * scale); // Dynamically scaled tile size

    Canvas canvas;
    GraphicsContext gc;

    // FPS
    final double FPS = 60;
    TileManager tileM = new TileManager(this);
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
        final double drawInterval = 1000000000.0 / FPS; // Time per frame in nanoseconds (1 second / FPS)
        long lastTime = System.nanoTime(); // Store the time of the last frame
        double delta = 0; // Store the accumulated time for frame updates

        long frameCount = 0; // To calculate FPS
        long fpsTimer = System.currentTimeMillis(); // Timer for FPS calculation

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            // Update the game and render if enough time has passed for the next frame
            if (delta >= 1) {
                update();  // Update game state
                paintComp(); // Render to the canvas

                delta--; // Decrease delta to avoid multiple updates in a single frame
                frameCount++; // Increment the frame count
            }

            // Calculate and print the FPS every second
            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                System.out.println("Current FPS: " + frameCount);
                frameCount = 0; // Reset frame count every second
                fpsTimer = System.currentTimeMillis(); // Reset FPS timer
            }

            try {
                // Control the game's FPS by sleeping for the remaining time
                long sleepTime = (long) ((drawInterval - (System.nanoTime() - lastTime)) / 1000000);
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime); // Sleep for the remaining time
                }

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

        tileM.draw(gc);  // Draw tiles

        player.draw(gc); // Draw player


    }

}
