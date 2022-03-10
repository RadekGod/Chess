package chess;

public class Spot {
    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public boolean isFieldEmpty() {
        return piece.getClass().getName().equals("chess.Empty");
    }
    public Spot(Piece piece) {
        this.setPiece(piece);
    }
}
