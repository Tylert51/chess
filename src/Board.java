import java.awt.Graphics2D;

public class Board {

    private Piece[][] pieceBoard;
    private Tile[][] board;
    private PieceColor white;
    private PieceColor black;
    private Tile[] tiles;
    private GamePanel gamePanel;

    public Board(PieceColor white, PieceColor black, GamePanel gamePanel) {
        pieceBoard = new Piece[8][8];
        tiles = new Tile[2];
        this.gamePanel = gamePanel;
        board = new Tile[8][8];

        tiles[0] = new Tile("tiles\\white.png");
        tiles[1] = new Tile("tiles\\gray.png");

        this.white = white;
        this.black = black;

        initializeBoard();
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                g2.drawImage(board[i][j].getImage(), j * gamePanel.getTileSize(), i * gamePanel.getTileSize(),
                        gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }

    }

    public void printBoard() {
        for (int i = 0; i < pieceBoard.length; i++) {
            for (int j = 0; j < pieceBoard[0].length; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void initializeBoard() {
        initializePawn();
        initializeRook();
        initializeKnight();
        initializeBishop();
        initializeQueen();
        initializeKing();

        // convert string board to tile board
        String boardStr[][] = BoardReader.getIndexes("src\\board.txt");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = tiles[Integer.parseInt(boardStr[i][j])];
            }
        }

    }

    public void setPiece(Piece piece, int x, int y) {
        pieceBoard[x][y] = piece;
    }

    private void initializePawn() {
        PieceColor color = white;
        int row = 6;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                color.addPiece(new Piece("pawn", 1, color, this, row, j));
            }
            color = black;
            row = 1;
        }
    }

    private void initializeRook() {
        PieceColor color = white;
        int row = 7;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j += 7) {
                color.addPiece(new Piece("rook", 5, color, this, row, j));
            }
            color = black;
            row = 0;
        }
    }

    private void initializeKnight() {
        PieceColor color = white;
        int row = 7;
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 8; j += 5) {
                color.addPiece(new Piece("knight", 3, color, this, row, j));
            }
            color = black;
            row = 0;
        }
    }

    private void initializeBishop() {
        PieceColor color = white;
        int row = 7;
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j < 8; j += 3) {
                color.addPiece(new Piece("bishop", 3, color, this, row, j));
            }
            color = black;
            row = 0;
        }
    }

    private void initializeQueen() {
        PieceColor color = white;
        int row = 7;
        for (int i = 0; i < 2; i++) {

            color.addPiece(new Piece("queen", 9, color, this, row, 3));

            color = black;
            row = 0;
        }
    }

    private void initializeKing() {
        PieceColor color = white;
        int row = 7;
        for (int i = 0; i < 2; i++) {

            color.addPiece(new Piece("king", 0, color, this, row, 4));

            color = black;
            row = 0;
        }
    }
}
