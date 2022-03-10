package chess.bishop;

import chess.*;
import chess.knight.KnightActionsHighlighter;

import java.util.ArrayList;
import java.util.Arrays;

public class BishopActionsHighlighter {


    public static ArrayList<Integer> possibleBishopMoves(int row, int col, Spot[][] board, int request) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (row == 0) {
            if (col == 0) { //lewy górny róg
                int i = row + 1;
                int j = col + 1;
                for (int t = 0; t < 7; t++) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i++;
                    j++;
                }
            } else if (col == 7) {
                int i = row + 1;
                int j = col - 1;
                for (int t = 0; t < 7; t++) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i++;
                    j--;
                }
            } else {
                // lewy dolny skos
                int i = row + 1;
                int j = col - 1;
                // prawy dolny skos
                int k = row + 1;
                int l = col + 1;
                for (int t = col; t > 0; t--) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i++;
                    j--;
                }
                for (int t = col; t < 7; t++) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                    k++;
                    l++;
                }
            }
        } else if (row == 7) {
            if (col == 0) {
                int i = row - 1;
                int j = col + 1;
                for (int t = 0; t < 7; t++) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i--;
                    j++;
                }
            } else if (col == 7) {
                int i = row - 1;
                int j = col - 1;
                for (int t = 0; t < 7; t++) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i--;
                    j--;
                }
            } else {
                // lewy górny skos
                int i = row - 1;
                int j = col - 1;
                // prawy górny skos
                int k = row - 1;
                int l = col + 1;
                for (int t = col; t > 0; t--) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i--;
                    j--;
                }
                for (int t = col; t < 7; t++) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                    k--;
                    l++;
                }
            }
        } else {
            if (col == 0) {
                // prawy górny skos
                int i = row - 1;
                int j = col + 1;
                // prawy dolny skos
                int k = row + 1;
                int l = col + 1;
                for (int t = row; t > 0; t--) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i--;
                    j++;
                }
                addActionsForSpecifiedRows(row, col, board, possibleMoves, possibleTakes, k, l);
            } else if(col == 7) {
                // lewy górny skos
                int i = row - 1;
                int j = col - 1;
                // lewy dolny skos
                int k = row + 1;
                int l = col - 1;
                for (int t = row; t > 0; t--) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i--;
                    j--;
                }
                for (int t = row; t < 7; t++) {
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                    k++;
                    l--;
                }
            } else {
                // lewy górny skos
                int i = row - 1;
                int j = col - 1;
                // lewy dolny skos
                int k = row + 1;
                int l = col - 1;
                // prawy górny skos
                int m = row - 1;
                int n = col + 1;
                // prawy dolny skos
                int o = row + 1;
                int p = col + 1;

                for (int t = Math.min(row, col); t > 0; t--) { // lewy górny skos //działa
                    if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, i, j)) break;
                    i--;
                    j--;
                }

                if (((col == 1 && row != 5) || col == 2 || col == 3 || (col == 4 && row != 4)|| (col == 5 && row != 3 && row != 4 && row != 5 && row != 6) || (col == 6 && row != 2 && row != 3 && row != 4)) && row <= 4) {
                    //działa
                    for (int t = col; t > 0; t--) { // lewy dolny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                        k++;
                        l--;
                    }
                } else if ((row == 6 && col == 5) || (col == 1 && row == 5)) { //działa
                    for (int t = 1; t > 0; t--) { // lewy dolny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                        k++;
                        l--;
                    }
                } else if((col == 5 && row == 4) || (col == 4 && row == 4) || (col == 6 && row == 4)) { //działa
                    for (int t = 3; t > 0; t--) { // lewy dolny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                        k++;
                        l--;
                    }
                }else if(col == 5 && row == 5) { //działa
                    for (int t = 2; t > 0; t--) { // lewy dolny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                        k++;
                        l--;
                    }
                } else if (col == 5 || (col == 6 && row == 3)) { //działa
                    for (int t = 4; t > 0; t--) { // lewy dolny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                        k++;
                        l--;
                    }
                } else if (row == 2 && col == 6) { //działa
                    for (int t = 5; t > 0; t--) { // lewy dolny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                        k++;
                        l--;
                    }
                }else if (row > 4) { //działa
                    for (int t = 7 - row; t > 0; t--) { // lewy dolny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, k, l)) break;
                        k++;
                        l--;
                    }

                }


                if (((col == 6 && row != 2)|| col == 5 || col == 4 || (col == 3 && row != 3) || (col == 2 && row != 4 && row != 3) || (col == 1 && row != 5 && row != 4 && row != 3)) && row >= 3) {
                    //działa
                    for (int t = 7 - col; t > 0; t--) { // prawy górny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, m, n)) break;
                        m--;
                        n++;
                    }
                } else if (col == 6 && row == 2) { //działa
                    for (int t = 6; t < 7; t++) { // prawy górny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, m, n)) break;
                        m--;
                        n++;
                    }
                } else if ((col == 3 && row == 3) || (col == 2 && row == 3) || (col == 1 && row == 3)) { //działa
                    for (int t = 4; t < 7; t++) { // prawy górny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, m, n)) break;
                        m--;
                        n++;
                    }
                }
                else if ((col == 2 && row == 4) || (col == 1 && row == 4)) { // działa
                    for (int t = 3; t < 7; t++) { // prawy górny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, m, n)) break;
                        m--;
                        n++;
                    }
                } else if ((col == 1 && row == 5)) { // działa
                    for (int t = 2; t < 7; t++) { // prawy górny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, m, n)) break;
                        m--;
                        n++;
                    }
                } else if (row < 3) { //działa
                    for (int t = 7 - row; t < 7; t++) { // prawy górny skos
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, m, n)) break;
                        m--;
                        n++;
                    }
                }
                if (row == 6 || (row == 5 && col == 6) || (row == 4 && col == 6) || (row == 3 && col == 6) || (row == 2 && col == 6) || (row == 1 && col == 6)) {
                    for (int t = 1; t > 0; t--) {
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, o, p)) break;
                        o++;
                        p++;
                    }
                } else if (row == 4 && col == 5 || (row == 3 && col == 5) || (row == 2 && col == 5) || (row == 1 && col == 5)) {
                    for (int t = 2; t > 0; t--) {
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, o, p)) break;
                        o++;
                        p++;
                    }
                } else if ((row == 3 && col == 4) || (row == 2 && col == 4) || (row == 1 && col == 4)) {
                    for (int t = 3; t > 0; t--) {
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, o, p)) break;
                        o++;
                        p++;
                    }
                } else if ((row == 2 && col == 3) || (row == 1 && col == 3)) {
                    for (int t = 4; t > 0; t--) {
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, o, p)) break;
                        o++;
                        p++;
                    }
                } else if ((row == 1 && col == 2)) {
                    for (int t = 5; t > 0; t--) {
                        if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, o, p)) break;
                        o++;
                        p++;
                    }
                } else if (row == 5 || row == 4 || row == 3 || row == 2 || row == 1) {
                    addActionsForSpecifiedRows(row, col, board, possibleMoves, possibleTakes, o, p);
                }


            }
        }

        Spot[][] simulateBoard = new Spot[8][];
        switch (request) {
            case StaticValues.WANT_SIMULATED_MOVES:
                ArrayList<Integer> simulatedPossibleMoves = new ArrayList<>();
                simulate(row, col, board, possibleMoves, simulateBoard, simulatedPossibleMoves);
                return simulatedPossibleMoves;

            case StaticValues.WANT_TAKES:
                return possibleTakes;
            case StaticValues.WANT_SIMULATED_TAKES:
                ArrayList<Integer> simulatedPossibleTakes = new ArrayList<>();
                simulate(row, col, board, possibleTakes, simulateBoard, simulatedPossibleTakes);
                return simulatedPossibleTakes;
            default:
                return possibleMoves;
        }
    }

    private static void simulate(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, Spot[][] simulateBoard, ArrayList<Integer> simulatedPossibleMoves) {
        for (int i = 0; i < possibleMoves.size(); i += 2) {
            for (int a = 0; a < 8; a++) {
                simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
            }
            simulateBoard[row][col] = new Spot(new Empty());
            simulateBoard[possibleMoves.get(i)][possibleMoves.get(i + 1)] = new Spot(new Bishop(board[row][col].getPiece().isWhite(), false));
            KnightActionsHighlighter.addToFinalMoves(row, col, board, possibleMoves, simulateBoard, simulatedPossibleMoves, i);
        }
    }

    private static void addActionsForSpecifiedRows(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes, int o, int p) {
        for (int t = row; t < 7; t++) {
            if (addPossibleActions(row, col, board, possibleMoves, possibleTakes, o, p)) break;
            o++;
            p++;
        }
    }

    private static boolean addPossibleActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes, int i, int j) {
        if (board[i][j].isFieldEmpty()) {
            possibleMoves.add(i);
            possibleMoves.add(j);
        } else if (SpotObserver.isTheSameColor(row, col, i, j, board)) {
            return true;
        } else if (!SpotObserver.isTheSameColor(row, col, i, j, board)) {
            possibleTakes.add(i);
            possibleTakes.add(j);
            return true;
        }
        return false;
    }
}
