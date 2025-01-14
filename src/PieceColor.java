import java.util.ArrayList;

public class PieceColor {

    String color;
    private ArrayList<Piece> pieces;
    private int material;

    public PieceColor(String c) {
        color = c;
        pieces = new ArrayList<>();
        material = 0;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void updateMaterial() {
        for (Piece p : pieces) {
            material += p.getValue();
        }
    }

    public int getMaterial() {
        return material;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return color;
    }
}
