import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Piece extends JPanel {

    private String name;
    private int value;
    private PieceColor color;
    private int[] coords;
    private Board board;

    protected BufferedImage image;
    protected final int imgWidth;
    protected Point imageCorner;
    protected Point previousPoint;

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

        // dragging and dropping img
        imgWidth = image.getWidth();
        imageCorner = new Point(0, 0);

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics2D g) {
        super.paintComponent(g);
        g.drawImage(image, (int) imageCorner.getX(), (int) imageCorner.getY(), null);
    }

    private class ClickListener extends MouseAdapter {

        public void mousePressed(MouseEvent evt) {
            previousPoint = evt.getPoint();
        }

    }

    private class DragListener extends MouseMotionAdapter {

        public void mouseDragged(MouseEvent evt) {

            Point currPoint = evt.getPoint();
            imageCorner.translate((int) (currPoint.getX() - previousPoint.getX()),
                    (int) (currPoint.getY() - previousPoint.getY()));

            previousPoint = currPoint;
            repaint();
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

    public void movePiece(int x, int y) {
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
