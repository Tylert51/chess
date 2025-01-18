import java.io.IOException;
import javax.swing.*;

public class MainFrame {

    public MainFrame() throws IOException, ClassNotFoundException {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Chess");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        Piece[][] pieceBoard = gamePanel.getBoard().getPieceBoard();
        for (int i = 0; i < pieceBoard.length; i++) {
            for (int j = 0; j < pieceBoard[0].length; j++) {
                Piece piece = pieceBoard[i][j];
                if (piece != null) {
                    window.add(piece);
                }
            }
        }

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}