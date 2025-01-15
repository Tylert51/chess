
public class Testing {
    public static void main(String[] args) {
        PieceColor white = new PieceColor("white");
        PieceColor black = new PieceColor("black");

        Board board = new Board(white, black);

        Piece piece = new Piece("tert", 0, black, board, 0, 0, "img\\pieces\\black\\rook.png");
        Pawn pawn = new Pawn(black, board, 0, 0);

        Piece[] pieceList = { piece, pawn };

        System.out.println(pieceList[0].getClass());
        System.out.println(pieceList[1].getClass());

        // board.printBoard();
    }
}
