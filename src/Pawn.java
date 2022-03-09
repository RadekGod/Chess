public class Pawn extends Piece {
    private boolean firstMove;
    private boolean secondMove;

    public Pawn(boolean white, boolean firstMove, boolean secondMove) {
        super(white, firstMove, secondMove);
    }
    public boolean isFirstMove() {
        return firstMove;
    }

    public boolean isSecondMove() {
        return secondMove;
    }

    public void setSecondMove(boolean secondMove) {
        this.secondMove = secondMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
