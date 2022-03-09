public abstract class Piece {
    private boolean white = false;
    private boolean black = false;
    private boolean firstMove = true;
    private boolean secondMove = false;


    public Piece(boolean white, boolean firstMove, boolean secondMove) {
        this.setWhite(white);
        this.setFirstMove(firstMove);
        this.setSecondMove(secondMove);
        this.setBlack(!white);
    }

    public Piece() {

    }
    public boolean isSecondMove() {
        return secondMove;
    }

    public void setSecondMove(boolean secondMove) {
        this.secondMove = secondMove;
    }
    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
