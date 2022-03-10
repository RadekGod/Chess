package chess.pawn;

import chess.Empty;
import chess.Piece;
import chess.Spot;

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

    public static boolean move(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        if (board[fromRow][fromCol].getPiece().isWhite()) {
            if ((fromRow - toRow == 1) && (Math.abs(fromCol - toCol) == 1) && board[toRow][toCol].getPiece().isBlack()) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Pawn(true, false, false));
                return true;
            }
            if (board[toRow][toCol].getPiece().isBlack() && (board[toRow][toCol].getPiece().getClass().getName().equals("chess.pawn.Pawn")) && board[toRow - 1][toCol].isFieldEmpty() && board[toRow][toCol].getPiece().isSecondMove() && Math.abs(fromCol - toCol) == 1 && (fromRow == toRow)) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Empty());
                board[toRow - 1][toCol] = new Spot(new Pawn(true, false, false));
                return true;
            }
            if (board[fromRow][fromCol].getPiece().isFirstMove()) {
                if (fromRow - toRow == 2 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty() && board[toRow + 1][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(true, false, true));
                        return true;
                    }
                } else if (fromRow - toRow == 1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(true, false, true));
                        return true;
                    }
                }
            } else {
                if (fromRow - toRow == 1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(true, false, false));
                        return true;
                    }
                }
            }

        } else {
            if ((fromRow - toRow == -1) && (Math.abs(fromCol - toCol) == 1) && board[toRow][toCol].getPiece().isWhite()) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Pawn(false, false, false));
                return true;
            }
            if (board[toRow][toCol].getPiece().isWhite() && (board[toRow][toCol].getPiece().getClass().getName().equals("chess.pawn.Pawn")) && board[toRow + 1][toCol].isFieldEmpty() && board[toRow][toCol].getPiece().isSecondMove() && Math.abs(fromCol - toCol) == 1 && (fromRow == toRow)) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Empty());
                board[toRow + 1][toCol] = new Spot(new Pawn(false, false, false));
                return true;
            }
            if (board[fromRow][fromCol].getPiece().isFirstMove()) {
                if (fromRow - toRow == -2 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty() && board[toRow - 1][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(false, false, true));
                        return true;
                    }
                } else if (fromRow - toRow == -1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(false, false, true));
                        return true;
                    }
                }
            } else {
                if (fromRow - toRow == -1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(false, false, false));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
