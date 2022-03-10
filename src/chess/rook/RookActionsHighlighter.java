package chess.rook;

import chess.Empty;
import chess.StaticValues;
import chess.Spot;
import chess.SpotObserver;
import chess.knight.KnightActionsHighlighter;

import java.util.ArrayList;
import java.util.Arrays;

public class RookActionsHighlighter {

    public static ArrayList<Integer> possibleRookMoves(int row, int col, Spot[][] board, int request) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (col == 0) { //lewa strona
            if (row == 0) { //lewy górny róg
                for (int i = 1; i < 8; i++) {
                    if (addPossibleMovesForBottomOfLeftTopCorner(row, col, board, possibleMoves, possibleTakes, i))
                        break;
                }
                for (int i = 1; i < 8; i++) { //prawa
                    if (addPossibleMovesForRightOfLeftTopCorner(row, col, board, possibleMoves, possibleTakes, i))
                        break;
                }
            } else if (row == 7) { //lewy dolny róg
                for (int i = 6; i >= 0; i--) {
                    if (addPossibleMovesForBottomOfLeftTopCorner(row, col, board, possibleMoves, possibleTakes, i))
                        break;
                }
                for (int i = 1; i < 8; i++) {
                    if (addPossibleMovesOnRegularBoard(row, col, board, possibleMoves, possibleTakes, i)) break;
                }
            } else {
                checkPossibleRookMoves(row, col, board, possibleMoves, possibleTakes);
                addPossibleMovesForRightSide(row, col, board, possibleMoves, possibleTakes);
            }
        } else if (col == 7) { // prawa strona
            if (row == 0) { //prawy górny róg
                for (int i = 1; i < 8; i++) {
                    if (addPossibleMovesForRightTopSide(row, col, board, possibleMoves, possibleTakes, i)) break;
                }
                for (int i = 6; i >= 0; i--) {
                    if (addPossibleMovesForRightOfLeftTopCorner(row, col, board, possibleMoves, possibleTakes, i))
                        break;
                }
            } else if (row == 7) { //prawy dolny róg
                for (int i = 6; i >= 0; i--) {
                    if (addPossibleMovesForRightTopSide(row, col, board, possibleMoves, possibleTakes, i)) break;
                }
                for (int i = 6; i >= 0; i--) {
                    if (addPossibleMovesOnRegularBoard(row, col, board, possibleMoves, possibleTakes, i)) break;
                }
            } else {
                addPossibleMovesForLefttSide(row, col, board, possibleMoves, possibleTakes);
            }
        } else {
            addPossibleMovesForLefttSide(row, col, board, possibleMoves, possibleTakes);
            addPossibleMovesForRightSide(row, col, board, possibleMoves, possibleTakes);
        }

        Spot[][] simulateBoard = new Spot[8][];
        switch (request) {
            case StaticValues.WANT_SIMULATED_MOVES:
                ArrayList<Integer> simulatedPossibleMoves = new ArrayList<>();
                simulateActions(row, col, board, possibleMoves, simulateBoard, simulatedPossibleMoves);
                return simulatedPossibleMoves;

            case StaticValues.WANT_TAKES:
                return possibleTakes;
            case StaticValues.WANT_SIMULATED_TAKES:
                ArrayList<Integer> simulatedPossibleTakes = new ArrayList<>();
                simulateActions(row, col, board, possibleTakes, simulateBoard, simulatedPossibleTakes);
                return simulatedPossibleTakes;
            default:
                return possibleMoves;
        }
    }

    private static void simulateActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, Spot[][] simulateBoard, ArrayList<Integer> simulatedPossibleMoves) {
        for (int i = 0; i < possibleMoves.size(); i += 2) {
            for (int a = 0; a < 8; a++) {
                simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
            }
            simulateBoard[row][col] = new Spot(new Empty());
            simulateBoard[possibleMoves.get(i)][possibleMoves.get(i + 1)] = new Spot(new Rook(board[row][col].getPiece().isWhite(), false));
            KnightActionsHighlighter.addToFinalMoves(row, col, board, possibleMoves, simulateBoard, simulatedPossibleMoves, i);
        }
    }

    private static void addPossibleMovesForLefttSide(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        checkPossibleRookMoves(row, col, board, possibleMoves, possibleTakes);
        for (int i = col - 1; i >= 0; i--) { // lewa
            if (board[row][i].isFieldEmpty()) {
                possibleMoves.add(row);
                possibleMoves.add(i);
            } else if (SpotObserver.isTheSameColor(row, col, row, i, board)) {
                break;
            }else if (!SpotObserver.isTheSameColor(row, col, row, i, board)) {
                possibleTakes.add(row);
                possibleTakes.add(i);
                break;
            }
        }
    }

    private static boolean addPossibleMovesForRightTopSide(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes, int i) {
        if (board[i][7].isFieldEmpty()) {
            possibleMoves.add(i);
            possibleMoves.add(7);
        }else if (SpotObserver.isTheSameColor(row, col, i, col, board)) {
            return true;
        } else if (!SpotObserver.isTheSameColor(row, col, i, col, board)) {
            possibleTakes.add(i);
            possibleTakes.add(col);
            return true;
        }
        return false;
    }

    private static void addPossibleMovesForRightSide(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        for (int i = col + 1; i < 8; i++) { // prawa
            if (board[row][i].isFieldEmpty()) {
                possibleMoves.add(row);
                possibleMoves.add(i);
            } else if (SpotObserver.isTheSameColor(row, col, row, i, board)) {
                break;
            }else if (!SpotObserver.isTheSameColor(row, col, row, i, board)) {
                possibleTakes.add(row);
                possibleTakes.add(i);
                break;
            }
        }
    }

    private static boolean addPossibleMovesOnRegularBoard(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes, int i) {
        if (board[7][i].isFieldEmpty()) {
            possibleMoves.add(7);
            possibleMoves.add(i);
        } else if (SpotObserver.isTheSameColor(row, col, row, i, board)) {
            return true;
        }else if (!SpotObserver.isTheSameColor(row, col, row, i, board)) {
            possibleTakes.add(row);
            possibleTakes.add(i);
            return true;
        }
        return false;
    }

    private static boolean addPossibleMovesForRightOfLeftTopCorner(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes, int i) {
        if (board[0][i].isFieldEmpty()) {
            possibleMoves.add(0);
            possibleMoves.add(i);
        } else if (SpotObserver.isTheSameColor(row, col, row, i, board)) {
            return true;
        }else if (!SpotObserver.isTheSameColor(row, col, row, i, board)) {
            possibleTakes.add(row);
            possibleTakes.add(i);
            return true;
        }
        return false;
    }

    private static boolean addPossibleMovesForBottomOfLeftTopCorner(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes, int i) {
        if (board[i][0].isFieldEmpty()) { // dół
            possibleMoves.add(i);
            possibleMoves.add(0);
        } else if (SpotObserver.isTheSameColor(row, col, i, col, board)) {
            return true;
        } else if (!SpotObserver.isTheSameColor(row, col, i, col, board)) {
            possibleTakes.add(i);
            possibleTakes.add(col);
            return true;
        }
        return false;
    }

    private static void checkPossibleRookMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        for (int i = row + 1; i < 8; i++) { // dół
            if (board[i][col].isFieldEmpty()) {
                possibleMoves.add(i);
                possibleMoves.add(col);
            } else if (SpotObserver.isTheSameColor(row, col, i, col, board)) {
                break;
            }else if (!SpotObserver.isTheSameColor(row, col, i, col, board)) {
                possibleTakes.add(i);
                possibleTakes.add(col);
                break;
            }
        }
        for (int i = row - 1; i >= 0; i--) { // góra
            if (board[i][col].isFieldEmpty()) {
                possibleMoves.add(i);
                possibleMoves.add(col);
            }else if (SpotObserver.isTheSameColor(row, col, i, col, board)) {
                break;
            } else if (!SpotObserver.isTheSameColor(row, col, i, col, board)) {
                possibleTakes.add(i);
                possibleTakes.add(col);
                break;
            }
        }
    }
}
