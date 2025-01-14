public class Testing {
    public static void main(String[] args) {
        PieceColor white = new PieceColor("white");
        PieceColor black = new PieceColor("black");

        Board board = new Board(white, black);
        board.printBoard();
    }
}
