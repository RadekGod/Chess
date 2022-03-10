package chess.king;

import chess.*;
import chess.rook.Rook;

public class King extends Piece {

    public King(boolean white, boolean firstMove) {
        super(white, firstMove, false);
    }

    public static boolean move(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        if (!SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
            if ((Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1) || (Math.abs(fromCol - toCol) == 1 && Math.abs(fromRow - toRow) == 0) || (Math.abs(fromCol - toCol) == 0 && Math.abs(fromRow - toRow) == 1)) {
                if (board[fromRow][fromCol].getPiece().isWhite()) {
                    board[fromRow][fromCol] = new Spot(new Empty());
                    board[toRow][toCol] = new Spot(new King(true, false));
                    return true;
                } else {
                    board[fromRow][fromCol] = new Spot(new Empty());
                    board[toRow][toCol] = new Spot(new King(false, false));
                    return true;
                }
            }
        } else {
            if (board[toRow][toCol].getPiece().getClass().getName().equals("chess.rook.Rook") && board[toRow][toCol].getPiece().isFirstMove() && board[fromRow][fromCol].getPiece().isFirstMove() && (fromRow == toRow)) {
                if ((toCol > fromCol) && board[toRow][toCol - 1].isFieldEmpty() && board[toRow][fromCol + 1].isFieldEmpty()) {
                    if (board[fromRow][fromCol].getPiece().isWhite()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol + 2] = new Spot(new King(true, false));
                        board[toRow][toCol - 2] = new Spot(new Rook(true, false));
                        return true;
                    } else {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol + 2] = new Spot(new King(false, false));
                        board[toRow][toCol - 2] = new Spot(new Rook(false, false));
                        return true;
                    }
                } else if (toCol < fromCol && board[toRow][toCol + 1].isFieldEmpty() && board[toRow][fromCol - 1].isFieldEmpty() && board[toRow][fromCol - 2].isFieldEmpty()) {
                    if (board[fromRow][fromCol].getPiece().isWhite()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol - 2] = new Spot(new King(true, false));
                        board[toRow][toCol + 3] = new Spot(new Rook(true, false));
                        return true;
                    } else {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol - 2] = new Spot(new King(false, false));
                        board[toRow][toCol + 3] = new Spot(new Rook(false, false));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
