import java.awt.Graphics;
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
    private int[] index;
    private Board board;

    private BufferedImage image;
    private final int imgWidth;
    private Point imageCorner;
    private Point previousPoint;
    private Point initialClick;

    private String fileName;
    private ArrayList<int[]> possibleMoves;

    private Graphics2D g;

    public Piece(String name, int value, PieceColor color, Board board, int row, int col, String fileName) {
        this.name = name;
        this.value = value;
        this.color = color;
        this.board = board;

        index = new int[] { row, col };

        board.setPiece(this, row, col);

        this.fileName = name;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // dragging and dropping img
        imgWidth = image.getWidth();
        imageCorner = new Point(indexToCoords(index[0]), indexToCoords(index[1]));

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    private class ClickListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent evt) {
            previousPoint = evt.getPoint();
            initialClick = evt.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
            int indexCol = coordsToIndex((int) evt.getPoint().getX());
            int indexRow = coordsToIndex((int) evt.getPoint().getY());
            board.setPiece(Piece.this, indexRow, indexCol);
            board.printBoard();
        }
    }

    private class DragListener extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent evt) {

            int indexCol = coordsToIndex((int) initialClick.getX());
            int indexRow = coordsToIndex((int) initialClick.getY());

            if (indexCol == index[1] && indexRow == index[0]) {
                board.setPiece(null, indexRow, indexCol);
                Point currPoint = evt.getPoint();
                imageCorner.translate((int) (currPoint.getX() - previousPoint.getX()),
                        (int) (currPoint.getY() - previousPoint.getY()));

                previousPoint = currPoint;
                repaint();
            }
        }
    }

    public int coordsToIndex(int coord) {
        return coord / imgWidth;
    }

    public int indexToCoords(int ind) {
        return ind * imgWidth;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        board.draw(g2);
        g2.drawImage(image, (int) imageCorner.getX(), (int) imageCorner.getY(), null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    public PieceColor getColor() {
        return color;
    }

    public void movePiece(int x, int y) {
        board.setPiece(this, x, y);
        board.setPiece(null, index[0], index[1]);
        index[0] = x;
        index[1] = y;
        System.out.println(index[0]);
        System.out.println(index[1]);
    }

    @Override
    public String toString() {
        return name;
    }

}
