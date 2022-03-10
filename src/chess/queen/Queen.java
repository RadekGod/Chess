package chess.queen;

import chess.*;
import chess.rook.Rook;

public class Queen extends Piece {
    public Queen(boolean white, boolean firstMove) {
        super(white, firstMove, false);
    }


    public static boolean move(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean isMoveLegal = false;
        if (!SpotObserver.isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
            if ((Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1) || (Math.abs(fromCol - toCol) == 1 && Math.abs(fromRow - toRow) == 0) || (Math.abs(fromCol - toCol) == 0 && Math.abs(fromRow - toRow) == 1)) {
                return performQueenMove(fromRow, fromCol, toRow, toCol, board, true);
            } else {
                if (isItVHMove(fromRow, fromCol, toRow, toCol)) {
                    if (fromCol == toCol && fromRow > toRow) {
                        for (int i = fromRow - 1; i > toRow; i--) {
                            if (!board[i][fromCol].isFieldEmpty()) {
                                isMoveLegal = false;
                                break;
                            } else {
                                isMoveLegal = true;
                            }
                        }
                        return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    } else if (fromCol == toCol && fromRow < toRow) {
                        for (int i = fromRow + 1; i < toRow; i++) {
                            if (!board[i][fromCol].isFieldEmpty()) {
                                isMoveLegal = false;
                                break;
                            } else {
                                isMoveLegal = true;
                            }
                        }
                        return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    } else if (fromCol > toCol && fromRow == toRow) {
                        isMoveLegal = Rook.performMoveLeftSide(fromRow, fromCol, toRow, toCol, board, false);
                        return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    } else if (fromCol < toCol && fromRow == toRow) {
                        for (int i = fromCol + 1; i < toCol; i++) {
                            if (!board[fromRow][i].isFieldEmpty()) {
                                isMoveLegal = false;
                                break;
                            } else {
                                isMoveLegal = true;
                            }
                        }
                        return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                    }
                } else {
                    if (SpotObserver.isOnTheSameDiagonal(fromRow, fromCol, toRow, toCol)) {
                        //przypadek dla lewej górnej przekątnej
                        if (fromRow > toRow && fromCol > toCol) {
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
                            return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                        } else if (fromRow < toRow && fromCol > toCol) {  //przypadek dla lewej dolnej przekątnej
                            loop:
                            for (int i = fromRow + 1; i < toRow; i++) {
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
                            return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
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
                            return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
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
                            return performQueenMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
                        }
                    }
                }
            }
        }
        return false;
    }
    private static boolean performQueenMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board, boolean isMoveLegal) {
        if (isMoveLegal) {
            if (board[fromRow][fromCol].getPiece().isWhite()) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Queen(true, false));
                return true;
            } else {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Queen(false, false));
                return true;
            }
        }
        return false;
    }

    private static boolean isItVHMove(int fromRow, int fromCol, int toRow, int toCol) {
        return ((fromRow == toRow || fromCol == toCol));
    }
}
