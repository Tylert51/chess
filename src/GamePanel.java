import java.awt.*;
import java.io.IOException;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    // screen stuff
    private final int originalTitleSize = 96;
    private final int scale = 1;
    private final int tileSize = originalTitleSize * scale;

    private final int screenWidth = tileSize * 8;

    private Board board;

    public GamePanel() throws IOException, ClassNotFoundException {
        this.setPreferredSize(new Dimension(screenWidth, screenWidth));
        setBackground(Color.black);
        setDoubleBuffered(true);
        setFocusable(true);

        PieceColor white = new PieceColor("white");
        PieceColor black = new PieceColor("black");
        board = new Board(white, black, this);

        Piece[][] pieceBoard = board.getPieceBoard();
        for (int i = 0; i < pieceBoard.length; i++) {
            for (int j = 0; j < pieceBoard[0].length; j++) {
                Piece piece = pieceBoard[i][j];
                if (piece != null) {
                    this.add(piece);
                }
            }
        }

    }

    public int getTileSize() {
        return tileSize;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        board.draw(g2);
    }

}
