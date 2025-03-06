public class Pawn extends Piece {

    public Pawn(PieceColor color, Board board, int x, int y) {
        super("pawn", 1, color, board, x, y, "img/pieces/" + color + "/pawn.png");
    }

}
