package tile;

import com.example.ytgame.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
//            /home/eno/github/java-game/ytgame/src/main/resources/tilesIMG/Tiles/Old version/grass.png
            tile[0].image = new Image(getClass().getResource("/tilesIMG/Tiles/Old version/grass.png").toExternalForm());

//            tile[1] = new Tile();
////            /home/eno/github/java-game/ytgame/src/main/resources/tilesIMG/Tiles/Old version/wall.png
//            tile[1].image = new Image(getClass().getResource("/tilesIMG/Tiles/Old version/wall.png").toExternalForm());
//
//            tile[2] = new Tile();
////            /home/eno/github/java-game/ytgame/src/main/resources/tilesIMG/Tiles/Old version/water.png
//            tile[2].image = new Image(getClass().getResource("/tilesIMG/Tiles/Old version/water.png").toExternalForm());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//        gc.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize);
        public void draw(GraphicsContext gc) {

            for (int y = 0; y < gp.screenHeight / gp.tileSize; y++) {
                for (int x = 0; x < gp.screenWidth / gp.tileSize; x++) {
                    gc.drawImage(tile[0].image, x * gp.tileSize, y * gp.tileSize, gp.tileSize, gp.tileSize);
                }
            }
        }

    }
