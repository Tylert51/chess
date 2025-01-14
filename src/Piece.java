import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Piece {

    private String name;
    private int value;
    private PieceColor color;
    private int[] coords;
    private Board board;
    private BufferedImage image;
    private String fileName;
    private ArrayList<int[]> possibleMoves;

    public Piece(String name, int value, PieceColor color, Board board, int x, int y, String fileName) {
        this.name = name;
        this.value = value;
        this.color = color;
        this.board = board;
        coords = new int[] { x, y };
        board.setPiece(this, x, y);

        this.fileName = name;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getCoords() {
        return coords;
    }

    public int getValue() {
        return value;
    }

    public PieceColor getColor() {
        return color;
    }

    public void move(int x, int y) {
        board.setPiece(this, x, y);
        board.setPiece(null, coords[0], coords[1]);
        coords[0] = x;
        coords[1] = y;
    }

    @Override
    public String toString() {
        return name;
    }

}
