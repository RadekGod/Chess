package chess.rook;

import chess.Empty;
import chess.Piece;
import chess.Spot;
import chess.SpotObserver;

public class Rook extends Piece {
    public Rook(boolean white, boolean firstMove) {
        super(white, firstMove, false);
    }

    public static boolean move(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean isMoveLegal = false;
        if ((!SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board))) {
            if (fromCol == toCol && fromRow > toRow) {
                if (Math.abs(fromRow - toRow) == 1) {
                    isMoveLegal = !SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board);
                } else if (Math.abs(fromRow - toRow) != 1) {
                    for (int i = fromRow - 1; i > toRow; i--) {
                        if (!board[i][fromCol].isFieldEmpty()) {
                            isMoveLegal = false;
                            break;
                        } else {
                            isMoveLegal = true;
                        }
                    }
                }
                return performMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
            } else if (fromCol == toCol && fromRow < toRow) {
                performMoveIfPossible(fromRow, fromCol, toRow, toCol, board);
            } else if (fromCol > toCol && fromRow == toRow) {
                if (Math.abs(fromCol - toCol) == 1) {
                    isMoveLegal = !SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board);
                } else if (Math.abs(fromCol - toCol) != 1) {
                    isMoveLegal = performMoveLeftSide(fromRow, fromCol, toRow, toCol, board, false);
                }
                return performMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
            } else if (fromCol < toCol && fromRow == toRow) {
                return performMoveIfPossible(fromRow, fromCol, toRow, toCol, board);
            }
        }
        return false;
    }

    public static boolean performMoveLeftSide(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board, boolean isMoveLegal) {
        for (int i = fromCol - 1; i > toCol; i--) {
            if (!board[fromRow][i].isFieldEmpty() || SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
                isMoveLegal = false;
                break;
            } else {
                isMoveLegal = true;
            }
        }
        return isMoveLegal;
    }

    private static boolean performMoveIfPossible(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean isMoveLegal = false;
        if (Math.abs(fromCol - toCol) == 1) {
            isMoveLegal = !SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board);
        } else if (Math.abs(fromCol - toCol) != 1) {
            for (int i = fromCol + 1; i < toCol; i++) {
                if (!board[fromRow][i].isFieldEmpty()) {
                    isMoveLegal = false;
                    break;
                } else {
                    isMoveLegal = true;
                }
            }
        }
        return performMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
    }

    private static boolean performMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board, boolean isMoveLegal) {
        if (isMoveLegal) {
            if (board[fromRow][fromCol].getPiece().isWhite()) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Rook(true, false));
                return true;
            } else {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Rook(false, false));
                return true;
            }
        }
        return false;
    }
}
