import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {

    private BufferedImage image;
    private String fileName;

    public Tile(String name) {
        fileName = name;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}
