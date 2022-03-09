public class Spot {
    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public boolean isFieldEmpty() {
        return piece.getClass().getName().equals("Empty");
    }
    public Spot(Piece piece) {
        this.setPiece(piece);
    }
}
