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

        tiles[0] = new Tile("img/tiles/white.png");
        tiles[1] = new Tile("img/tiles/gray.png");

        this.white = white;
        this.black = black;

        initializeBoard();
    }

    /* Testing Puposes */
    public Board(PieceColor white, PieceColor black) {
        pieceBoard = new Piece[8][8];
        tiles = new Tile[2];
        board = new Tile[8][8];

        tiles[0] = new Tile("img\\tiles\\white.png");
        tiles[1] = new Tile("img\\tiles\\gray.png");

        this.white = white;
        this.black = black;

        initializeBoard();
    }

    public Piece[][] getPieceBoard() {
        return pieceBoard;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(pieceBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /* Testing Puposes (end) */

    public void draw(Graphics2D g2) {

        // draw the board

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                g2.drawImage(board[i][j].getImage(), j * gamePanel.getTileSize(), i * gamePanel.getTileSize(),
                        gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }

        // draw the pieces
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                Piece piece = pieceBoard[i][j];

                if (piece != null) {
                    g2.drawImage(piece.getImage(), j * gamePanel.getTileSize(), i * gamePanel.getTileSize(),
                            gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }
            }
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
        String boardStr[][] = BoardReader.getIndexes("src/files/board.txt");

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
                color.addPiece(new Pawn(color, this, row, j));
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
                color.addPiece(new Rook(color, this, row, j));
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
                color.addPiece(new Knight(color, this, row, j));
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
                color.addPiece(new Bishop(color, this, row, j));
            }
            color = black;
            row = 0;
        }
    }

    private void initializeQueen() {
        PieceColor color = white;
        int row = 7;
        for (int i = 0; i < 2; i++) {

            color.addPiece(new Queen(color, this, row, 3));

            color = black;
            row = 0;
        }
    }

    private void initializeKing() {
        PieceColor color = white;
        int row = 7;
        for (int i = 0; i < 2; i++) {

            color.addPiece(new King(color, this, row, 4));

            color = black;
            row = 0;
        }
    }
}
