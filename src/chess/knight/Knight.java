package chess.knight;

import chess.Empty;
import chess.Piece;
import chess.Spot;
import chess.SpotObserver;

public class Knight extends Piece {
    public Knight(boolean white, boolean firstMove) {
        super(white, firstMove, false);
    }

    public static boolean knightMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean legalKnightMove = (Math.abs(fromCol - toCol) == 2 && Math.abs(fromRow - toRow) == 1) || (Math.abs(fromCol - toCol) == 1 && Math.abs(fromRow - toRow) == 2);
        if ((!SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board)) && (!board[fromRow][fromCol].isFieldEmpty()) && board[fromRow][fromCol].getPiece().isWhite()) {
            if (legalKnightMove) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Knight(true, false));
                return true;
            }
        } else if ((!SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board)) && (!board[fromRow][fromCol].isFieldEmpty()) && !board[fromRow][fromCol].getPiece().isWhite()) {
            if (legalKnightMove) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Knight(false, false));
                return true;
            }
        }
        return false;
    }
}
