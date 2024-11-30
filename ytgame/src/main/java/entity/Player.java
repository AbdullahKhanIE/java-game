package entity;

import com.example.ytgame.GamePanel;
import com.example.ytgame.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    // Declare Image variables
    Image up1, up2, down1, down2, left1, left2, right1, right2;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        loadPlayerImages();
    }

    public void setDefaultValues() {
        x = 50;
        y = 50;
        speed = 4;
        direction = "down";
    }

    public void loadPlayerImages() {
        try {
            // Loading images using getClass().getResource() for resources folder
            up1 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_up_1.png").toExternalForm());
            up2 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_up_2.png").toExternalForm());
            down1 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_down_1.png").toExternalForm());
            down2 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_down_2.png").toExternalForm());
            left1 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_left_1.png").toExternalForm());
            left2 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_left_2.png").toExternalForm());
            right1 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_right_1.png").toExternalForm());
            right2 = new Image(getClass().getResource("/Walking sprites-20241130T164413Z-001/WalkingSprites/boy_right_2.png").toExternalForm());
        } catch (Exception e) {
            e.printStackTrace(); // Print error if images fail to load
        }
    }

    public void update() {
        // Movement logic
        if (keyH.upPressed) {
            direction = "up";
            y -= speed; // Move up
        }
        if (keyH.downPressed) {
            direction = "down";
            y += speed; // Move down
        }
        if (keyH.leftPressed) {
            direction = "left";
            x -= speed; // Move left
        }
        if (keyH.rightPressed) {
            direction = "right";
            x += speed; // Move right
        }
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, gp.screenWidth, gp.screenHeight); // Clear screen
        Image image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        // Draw the image directly using JavaFX's Image
        gc.drawImage(image, x, y, gp.tileSize, gp.tileSize);
    }
}
