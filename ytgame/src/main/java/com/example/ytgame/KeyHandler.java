package com.example.ytgame;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {

    public boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false;

    // Attach key handlers to a Scene
    public void attachToScene(Scene scene) {
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);
    }

    // Handle key press events
    public void handleKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.W) upPressed = true;
        if (code == KeyCode.S) downPressed = true;
        if (code == KeyCode.A) leftPressed = true;
        if (code == KeyCode.D) rightPressed = true;
    }

    // Handle key release events
    public void handleKeyReleased(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.W) upPressed = false;
        if (code == KeyCode.S) downPressed = false;
        if (code == KeyCode.A) leftPressed = false;
        if (code == KeyCode.D) rightPressed = false;
    }
}
