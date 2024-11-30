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
            up1 = new Image(getClass().getResource("/playerIMG/boy_up_1.png").toExternalForm());
            up2 = new Image(getClass().getResource("/playerIMG/boy_up_2.png").toExternalForm());
            down1 = new Image(getClass().getResource("/playerIMG/boy_down_1.png").toExternalForm());
            down2 = new Image(getClass().getResource("/playerIMG/boy_down_2.png").toExternalForm());
            left1 = new Image(getClass().getResource("/playerIMG/boy_left_1.png").toExternalForm());
            left2 = new Image(getClass().getResource("/playerIMG/boy_left_2.png").toExternalForm());
            right1 = new Image(getClass().getResource("/playerIMG/boy_right_1.png").toExternalForm());
            right2 = new Image(getClass().getResource("/playerIMG/boy_right_2.png").toExternalForm());
        } catch (Exception e) {
            e.printStackTrace(); // Print error if images fail to load
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
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
            spriteCounter++;
            if (spriteCounter >= 12) {
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;

        }


        }
    }

    public void draw(GraphicsContext gc) {
//        gc.clearRect(0, 0, gp.screenWidth, gp.screenHeight); // Clear screen
        Image image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }

        // Draw the image directly using JavaFX's Image
        gc.drawImage(image, x, y, gp.tileSize, gp.tileSize);
    }
}
