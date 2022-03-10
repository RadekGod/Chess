package chess.bishop;

import chess.Empty;
import chess.Piece;
import chess.Spot;
import chess.SpotObserver;

public class Bishop extends Piece {
    public Bishop(boolean white, boolean firstMove) {
        super(white, firstMove, false);
    }


    public static boolean move(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean isMoveLegal = false;
        if (!SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
            if (Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1) {
                return performBishopMove(fromRow, fromCol, toRow, toCol, board, true);
            } else {
                if (SpotObserver.isOnTheSameDiagonal(fromRow, fromCol, toRow, toCol)) {
                    if (fromRow > toRow && fromCol > toCol) { //przypadek dla lewej górnej przekątnej
                        loop:
                        for (int i = fromRow - 1; i > toRow; i--) {
                            for (int j = fromCol - 1; j > toCol; j--) {
                                if ((i - fromRow) == (j - fromCol)) {
                                    if (!board[i][j].isFieldEmpty()) {
                                        isMoveLegal = false;
                                        break loop;
                                    } else {
                                        isMoveLegal = true;
                                    }
                                }
                            }
                        }
                        return performBishopMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    } else if (fromRow < toRow && fromCol > toCol) {  //przypadek dla lewej dolnej przekątnej
                        loop: for (int i = fromRow + 1; i < toRow; i++) {
                            for (int j = fromCol - 1; j > toCol; j--) {
                                if (Math.abs(i - fromRow) == Math.abs(j - fromCol)) {
                                    if (!board[i][j].isFieldEmpty()) {
                                        isMoveLegal = false;
                                        break loop;
                                    } else {
                                        isMoveLegal = true;
                                    }
                                }
                            }
                        }
                        return performBishopMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    } else if (fromRow > toRow && fromCol < toCol) {  //przypadek dla prawej górnej przekątnej
                        loop:
                        for (int i = fromRow - 1; i > toRow; i--) {
                            for (int j = fromCol + 1; j < toCol; j++) {
                                if (Math.abs(i - fromRow) == Math.abs(j - fromCol)) {
                                    if (!board[i][j].isFieldEmpty()) {
                                        isMoveLegal = false;
                                        break loop;
                                    } else {
                                        isMoveLegal = true;
                                    }
                                }
                            }
                        }
                        return performBishopMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    } else if (fromRow < toRow && fromCol < toCol) {  //przypadek dla prawej dolnej przekątnej
                        loop:
                        for (int i = fromRow + 1; i < toRow; i++) {
                            for (int j = fromCol + 1; j < toCol; j++) {
                                if (Math.abs(i - fromRow) == Math.abs(j - fromCol)) {
                                    if (!board[i][j].isFieldEmpty()) {
                                        isMoveLegal = false;
                                        break loop;
                                    } else {
                                        isMoveLegal = true;
                                    }
                                }
                            }
                        }
                        return performBishopMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    }
                }
            }
        }
        return false;
    }

    private static boolean performBishopMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board, boolean isMoveLegal) {
        if (isMoveLegal) {
            if (board[fromRow][fromCol].getPiece().isWhite()) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Bishop(true, false));
                return true;
            } else {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Bishop(false, false));
                return true;
            }
        }
        return false;
    }
}
