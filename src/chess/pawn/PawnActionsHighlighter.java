package chess.pawn;

import chess.*;
import chess.knight.KnightActionsHighlighter;

import java.util.ArrayList;
import java.util.Arrays;

public class PawnActionsHighlighter {

    public static ArrayList<Integer> possiblePawnMoves(int row, int col, Spot[][] board) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        Spot[][] simulateBoard = new Spot[8][];
        if (board[row][col].getPiece().isWhite()) {
            if (board[row][col].getPiece().isFirstMove()) {
                if (board[row - 2][col].isFieldEmpty() && board[row - 1][col].isFieldEmpty()) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col);
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col);
                } else if (board[row - 1][col].isFieldEmpty()) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col);
                }
            } else {
                if (board[row - 1][col].isFieldEmpty()) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col);
                }
            }
        } else {
            if (board[row][col].getPiece().isFirstMove()) {
                if (board[row + 2][col].isFieldEmpty() && board[row + 1][col].isFieldEmpty()) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col);
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col);
                } else if (board[row + 1][col].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col);
                }
            } else {
                if (board[row + 1][col].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col);
                }
            }
        }
        ArrayList<Integer> finalPossibleMoves = new ArrayList<>();
        for (int i = 0; i < possibleMoves.size(); i += 2) {
            for (int a = 0; a < 8; a++) {
                simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
            }
            simulateBoard[row][col] = new Spot(new Empty());
            simulateBoard[possibleMoves.get(i)][possibleMoves.get(i + 1)] = new Spot(new Pawn(board[row][col].getPiece().isWhite(), false, false));
            KnightActionsHighlighter.fillFinalPossibleMoves(row, col, board, possibleMoves, simulateBoard, finalPossibleMoves, i);
        }

        return finalPossibleMoves;
    }

    public static ArrayList<Integer> possiblePawnTakes(int row, int col, Spot[][] board, boolean wantPossibleTakes, boolean simulateIfMoveIsLegal) {
        Spot[][] simulateBoard = new Spot[8][8];
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        ArrayList<Integer> takes = new ArrayList<>();
        if (board[row][col].getPiece().isWhite()) {
            if (col >= 1 && col <= 6) {
                if (board[row - 1][ col - 1].getPiece().isBlack()) {
                    takes.add(row - 1);
                    takes.add(col - 1);
                } else if (board[row - 1][ col - 1].isFieldEmpty()) {
                    possibleTakes.add(row - 1);
                    possibleTakes.add(col - 1);
                }
                addPossibleTakeOnBlack(row, col, board, possibleTakes, takes);
            } else if (col == 0) {
                addPossibleTakeOnBlack(row, col, board, possibleTakes, takes);
            } else if (col == 7) {
                if ((board[row - 1][ col - 1].getPiece().isBlack())) {
                    takes.add(row - 1);
                    takes.add(col - 1);
                } else if (board[row - 1][ col - 1].isFieldEmpty()) {
                    possibleTakes.add(row - 1);
                    possibleTakes.add(col - 1);
                }
            }
        } else if (board[row][col].getPiece().isBlack()) {
            if (col >= 1 && col <= 6) {
                addWhitePossibleTakeOnLeftDiagonal(row, col, board, possibleTakes, takes);
                addWhitePossibleTakeOnRightDiagonal(row, col, board, possibleTakes, takes);
            } else if (col == 0) {
                addWhitePossibleTakeOnRightDiagonal(row, col, board, possibleTakes, takes);
            } else if (col == 7) {
                addWhitePossibleTakeOnLeftDiagonal(row, col, board, possibleTakes, takes);
            }
        }

        if (simulateIfMoveIsLegal && ((row != 1 && board[row][col].getPiece().isWhite()) || (row != 6 && board[row][col].getPiece().isBlack()))) {
            for (int i = 0; i < takes.size(); i += 2) {
                for (int a = 0; a < 8; a++) {
                    simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
                }
                simulateBoard[row][col] = new Spot(new Empty());
                simulateBoard[takes.get(i)][takes.get(i + 1)] = new Spot(new Pawn(board[row][col].getPiece().isWhite(), false, false));
                ArrayList<Integer> hypotheticalCheck = CheckMateObserver.lookForCheckMate(simulateBoard);
                if (hypotheticalCheck.size() != 0 && SpotObserver.isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board)) {
                    takes.remove(i);
                    takes.remove(i);
                }
            }
        }


        if (wantPossibleTakes) {
            return possibleTakes;
        } else {
            return takes;
        }
    }

    private static void addWhitePossibleTakeOnRightDiagonal(int row, int col, Spot[][] board, ArrayList<Integer> possibleTakes, ArrayList<Integer> takes) {
        if ((board[row + 1][ col + 1].getPiece().isWhite())) {
            takes.add(row + 1);
            takes.add(col + 1);
        }else if (board[row + 1][ col + 1].isFieldEmpty()) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col + 1);
        }
    }

    private static void addWhitePossibleTakeOnLeftDiagonal(int row, int col, Spot[][] board, ArrayList<Integer> possibleTakes, ArrayList<Integer> takes) {
        if ((board[row + 1][ col - 1].getPiece().isWhite())) {
            takes.add(row + 1);
            takes.add(col - 1);
        } else if (board[row + 1][ col - 1].isFieldEmpty()) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col - 1);
        }
    }

    private static void addPossibleTakeOnBlack(int row, int col, Spot[][] board, ArrayList<Integer> possibleTakes, ArrayList<Integer> takes) {
        if ((board[row - 1][ col + 1].getPiece().isBlack())) {
            takes.add(row - 1);
            takes.add(col + 1);
        } else if (board[row - 1][ col + 1].isFieldEmpty()) {
            possibleTakes.add(row - 1);
            possibleTakes.add(col + 1);
        }
    }

    public static ArrayList<Integer> possibleTakingInFlight(int row, int col, Spot[][] board, int[] previousEnemyMove, boolean isWhite) {
        int enemyFromRow = previousEnemyMove[0];
        int enemyToRow = previousEnemyMove[2];
        int enemyToCol = previousEnemyMove[3];
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (isWhite) {
            if (col > 0 && col < 7) {
                whiteTakingInFlightIsPossibleRightDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
                whiteTakingInFlightIsPossibleLeftDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
            } else if (col == 0) {
                whiteTakingInFlightIsPossibleRightDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
            } else if (col == 7) {
                whiteTakingInFlightIsPossibleLeftDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
            }
        } else {
            if (col > 0 && col < 7) {
                blackTakingInFlightIsPossibleRightDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
                blackTakingInFlightIsPossibleLeftDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
            } else if (col == 0) {
                blackTakingInFlightIsPossibleRightDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
            } else if (col == 7) {
                blackTakingInFlightIsPossibleLeftDiagonal(row, col, board, enemyFromRow, enemyToRow, enemyToCol, possibleTakes);
            }
        }

        return possibleTakes;
    }

    private static void whiteTakingInFlightIsPossibleRightDiagonal(int row, int col, Spot[][] board, int enemyFromRow, int enemyToRow, int enemyToCol, ArrayList<Integer> possibleTakes) {
        if (board[row][col + 1].getPiece().isBlack() && (board[row][col + 1].getPiece().getClass().getName().equals("chess.pawn.Pawn")) && board[row - 1][col + 1].isFieldEmpty() && board[row][col + 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == 2) && (enemyToCol == col + 1) && (enemyToRow == row)) {
            possibleTakes.add(row);
            possibleTakes.add(col + 1);
        }
    }

    private static void whiteTakingInFlightIsPossibleLeftDiagonal(int row, int col, Spot[][] board, int enemyFromRow, int enemyToRow, int enemyToCol, ArrayList<Integer> possibleTakes) {
        if (board[row][col - 1].getPiece().isBlack() && (board[row][col - 1].getPiece().getClass().getName().equals("chess.pawn.Pawn")) && board[row - 1][col - 1].isFieldEmpty() && board[row][col - 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == 2) && (enemyToCol == col - 1) && (enemyToRow == row)) {
            possibleTakes.add(row);
            possibleTakes.add(col - 1);
        }
    }

    private static void blackTakingInFlightIsPossibleRightDiagonal(int row, int col, Spot[][] board, int enemyFromRow, int enemyToRow, int enemyToCol, ArrayList<Integer> possibleTakes) {
        if (board[row][col + 1].getPiece().isWhite() && (board[row][col + 1].getPiece().getClass().getName().equals("chess.pawn.Pawn")) && board[row + 1][col + 1].isFieldEmpty() && board[row][col + 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == -2) && (enemyToCol == col + 1) && (enemyToRow == row)) {
            possibleTakes.add(row);
            possibleTakes.add(col + 1);
        }
    }

    private static void blackTakingInFlightIsPossibleLeftDiagonal(int row, int col, Spot[][] board, int enemyFromRow, int enemyToRow, int enemyToCol, ArrayList<Integer> possibleTakes) {
        if (board[row][col - 1].getPiece().isWhite() && (board[row][col - 1].getPiece().getClass().getName().equals("chess.pawn.Pawn")) && board[row + 1][col - 1].isFieldEmpty() && board[row][col - 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == -2) && (enemyToCol == col - 1) && (enemyToRow == row)) {
            possibleTakes.add(row);
            possibleTakes.add(col - 1);
        }
    }
}
