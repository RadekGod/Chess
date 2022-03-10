package chess.king;

import chess.*;
import chess.bishop.BishopActionsHighlighter;
import chess.knight.KnightActionsHighlighter;
import chess.pawn.PawnActionsHighlighter;
import chess.rook.RookActionsHighlighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class KingActionsHighlighter {


    public static ArrayList<Integer> possibleKingMoves(int row, int col, Spot[][] board, boolean wantMove) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (row == 0) {
            addPossibleMoves(row, col, board, possibleMoves);
            if (col == 0) {
                for (int i = 0; i < 2; i++) {
                    if (board[i][1].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(1);
                    } else if (!SpotObserver.isTheSameColor(0, 0, i, 1, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(1);
                    }
                }
                if (board[1][0].isFieldEmpty()) {
                    possibleMoves.add(1);
                    possibleMoves.add(0);
                } else if (!SpotObserver.isTheSameColor(0, 0, 1, 0, board)) {
                    possibleTakes.add(1);
                    possibleTakes.add(0);
                }
            } else if (col == 7) {
                for (int i = 0; i < 2; i++) {
                    if (board[i][6].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(6);
                    } else if (!SpotObserver.isTheSameColor(0, 7, i, 6, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(6);
                    }
                }
                if (board[1][7].isFieldEmpty()) {
                    possibleMoves.add(1);
                    possibleMoves.add(7);
                } else if (!SpotObserver.isTheSameColor(0, 7, 1, 7, board)) {
                    possibleTakes.add(1);
                    possibleTakes.add(7);
                }
            } else {
                if (board[row][col - 1].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(col - 1);
                } else if (!SpotObserver.isTheSameColor(0, 7, row, col - 1, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col - 1);
                }
                if (board[row][col + 1].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(col + 1);
                } else if (!SpotObserver.isTheSameColor(0, 7, row, col + 1, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col + 1);
                }
                if (board[row + 1][col - 1].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 1);
                } else if (!SpotObserver.isTheSameColor(0, 7, row + 1, col - 1, board)) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col - 1);
                }
                if (board[row + 1][col].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col);
                } else if (!SpotObserver.isTheSameColor(0, 7, row + 1, col, board)) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col);
                }
                if (board[row + 1][col + 1].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 1);
                } else if (!SpotObserver.isTheSameColor(0, 7, row + 1, col + 1, board)) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col + 1);
                }
            }
        } else if (row == 7) {
            addPossibleMoves(row, col, board, possibleMoves);
            if (col == 0) {
                for (int i = 7; i > 5; i--) {
                    if (board[i][1].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(1);
                    } else if (!SpotObserver.isTheSameColor(row, col, i, 1, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(1);
                    }
                }
                if (board[6][0].isFieldEmpty()) {
                    possibleMoves.add(6);
                    possibleMoves.add(0);
                } else if (!SpotObserver.isTheSameColor(row, col, 6, 0, board)) {
                    possibleTakes.add(6);
                    possibleTakes.add(0);
                }
            } else if (col == 7) {
                for (int i = 7; i > 5; i--) {
                    if (board[i][6].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(6);
                    } else if (!SpotObserver.isTheSameColor(row, col, i, 6, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(6);
                    }
                }
                if (board[6][7].isFieldEmpty()) {
                    possibleMoves.add(6);
                    possibleMoves.add(7);
                } else if (!SpotObserver.isTheSameColor(row, col, 6, 7, board)) {
                    possibleTakes.add(6);
                    possibleTakes.add(7);
                }
            } else {
                addLeftColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
                addRightColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
                addLeftTopColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
                addTopColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
                addRightTopColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
            }
        } else if (col == 0) {
            checkForVerticalKingMoves(row, col, board, possibleMoves, possibleTakes);
            checkForRightSideKingMoves(row, col, board, possibleMoves, possibleTakes);
        } else if (col == 7) {
            checkForVerticalKingMoves(row, col, board, possibleMoves, possibleTakes);
            checkForLeftSideKingMoves(row, col, board, possibleMoves, possibleTakes);
        } else {
            // lewa strona
            checkForLeftSideKingMoves(row, col, board, possibleMoves, possibleTakes);

            // prawa strona
            checkForRightSideKingMoves(row, col, board, possibleMoves, possibleTakes);
            checkForVerticalKingMoves(row, col, board, possibleMoves, possibleTakes);
        }
        if (wantMove) {
            return  possibleMoves;
        } else {
            return possibleTakes;
        }
    }

    private static void addRightTopColumnPossibleActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row - 1][col + 1].isFieldEmpty()) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col + 1);
        } else if (!SpotObserver.isTheSameColor(row, col, row - 1, col + 1, board)) {
            possibleTakes.add(row - 1);
            possibleTakes.add(col + 1);
        }
    }

    private static void addTopColumnPossibleActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row - 1][col].isFieldEmpty()) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col);
        } else if (!SpotObserver.isTheSameColor(row, col, row - 1, col, board)) {
            possibleTakes.add(row - 1);
            possibleTakes.add(col);
        }
    }

    private static void addLeftTopColumnPossibleActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row - 1][col - 1].isFieldEmpty()) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col - 1);
        } else if (!SpotObserver.isTheSameColor(row, col, row - 1, col - 1, board)) {
            possibleTakes.add(row - 1);
            possibleTakes.add(col - 1);
        }
    }

    private static void addRightColumnPossibleActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row][col + 1].isFieldEmpty()) {
            possibleMoves.add(row);
            possibleMoves.add(col + 1);
        } else if (!SpotObserver.isTheSameColor(row, col, row, col + 1, board)) {
            possibleTakes.add(row);
            possibleTakes.add(col + 1);
        }
    }

    private static void addLeftColumnPossibleActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row][col - 1].isFieldEmpty()) {
            possibleMoves.add(row);
            possibleMoves.add(col - 1);
        } else if (!SpotObserver.isTheSameColor(row, col, row, col - 1, board)) {
            possibleTakes.add(row);
            possibleTakes.add(col - 1);
        }
    }

    private static void addPossibleMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (board[row][col].getPiece().isFirstMove() && board[row][0].getPiece().isFirstMove() && board[row][1].isFieldEmpty() && board[row][2].isFieldEmpty() && board[row][3].isFieldEmpty() && SpotObserver.isTheSameColor(row, col, row, 0, board)) {
            possibleMoves.add(row);
            possibleMoves.add(0);
        }
        if (board[row][col].getPiece().isFirstMove() && board[row][7].getPiece().isFirstMove() && board[row][5].isFieldEmpty() && board[row][5].isFieldEmpty() && SpotObserver.isTheSameColor(row, col, row, 7, board)) {
            possibleMoves.add(row);
            possibleMoves.add(7);
        }
    }

    public static void checkForLeftSideKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        addLeftTopColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
        addLeftColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
        if (board[row + 1][col - 1].isFieldEmpty()) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col - 1);
        } else if (!SpotObserver.isTheSameColor(row, col, row + 1, col - 1, board)) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col - 1);
        }
    }

    public static void checkForRightSideKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        addRightTopColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
        addRightColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
        if (board[row + 1][col + 1].isFieldEmpty()) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col + 1);
        } else if (!SpotObserver.isTheSameColor(row, col, row + 1, col + 1, board)) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col + 1);
        }
    }

    public static void checkForVerticalKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        addTopColumnPossibleActions(row, col, board, possibleMoves, possibleTakes);
        if (board[row + 1][col].isFieldEmpty()) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col);
        } else if (!SpotObserver.isTheSameColor(row, col, row + 1, col, board)) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col);
        }
    }

    public static ArrayList<Integer> finalKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves) {
        ArrayList<Integer> finalMoves;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPiece().getClass().getName().equals("chess.pawn.Pawn") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possiblePawnTakes = PawnActionsHighlighter.possiblePawnTakes(i, j, board, true, false);
                    removeKingsActions(row, col, board, possibleKingMoves, possiblePawnTakes);
                } else if (board[i][j].getPiece().getClass().getName().equals("chess.rook.Rook") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleRookMoves = RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_MOVES);
                    loop: for (int k = 0; k < possibleRookMoves.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if ((Objects.equals(possibleKingMoves.get(l), possibleRookMoves.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleRookMoves.get(k + 1)))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 2) || (possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 3) || (possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 0 && board[row][col].getPiece().isFirstMove()))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (removeAnotherKingsActions(row, col, board, possibleKingMoves, possibleRookMoves, k, l))
                                    continue loop;
                            }
                        } else {
                            break;
                        }
                    }
                    Spot[][] simulateBoard = new Spot[8][];
                    for (int x = 0; x < 8; x++) {
                        simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                    }
                    simulateBoard[row][col] = new Spot(new Empty());

                    loop: for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                        simulateBoard[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleRookTakes = RookActionsHighlighter.possibleRookMoves(i, j, simulateBoard, StaticValues.WANT_TAKES);
                        for (int k = 0; k < possibleRookTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingMoves.get(l), possibleRookTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleRookTakes.get(k + 1))) && !(board[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)].getPiece().getClass().getName().equals("chess.rook.Rook"))) {
                                possibleKingMoves.remove(l);
                                possibleKingMoves.remove(l);
                                break loop;
                            }
                        }
                    }
                } else if (board[i][j].getPiece().getClass().getName().equals("chess.bishop.Bishop") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleBishopMoves = BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_MOVES);
                    loop: for (int k = 0; k < possibleBishopMoves.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if (extractAnotherAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleBishopMoves, k, l))
                                    continue;
                                if (extractAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleBishopMoves, k, l))
                                    continue;
                                if (extractAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleBishopMoves, k, l))
                                    continue;
                                extractAnotherAnotherAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleBishopMoves, k, l);
                            }
                        } else {
                            break;
                        }
                    }
                    Spot[][] simulateKingMovesBoard = new Spot[8][];
                    for (int x = 0; x < 8; x++) {
                        simulateKingMovesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                    }
                    simulateKingMovesBoard[row][col] = new Spot(new Empty());


                    loop: for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                        simulateKingMovesBoard[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleBishopTakes = BishopActionsHighlighter.possibleBishopMoves(i, j, simulateKingMovesBoard, StaticValues.WANT_TAKES);
                        for (int k = 0; k < possibleBishopTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingMoves.get(l), possibleBishopTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleBishopTakes.get(k + 1))) && !(board[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)].getPiece().getClass().getName().equals("chess.rook.Rook"))) {
                                possibleKingMoves.remove(l);
                                possibleKingMoves.remove(l);
                                break loop;
                            }
                        }
                    }
                }else if (board[i][j].getPiece().getClass().getName().equals("chess.queen.Queen") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleQueenMoves1 = RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_MOVES);
                    ArrayList<Integer> possibleQueenMoves2 = BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_MOVES);
                    ArrayList<Integer> possibleQueenMoves = new ArrayList<>();
                    possibleQueenMoves.addAll(possibleQueenMoves1);
                    possibleQueenMoves.addAll(possibleQueenMoves2);
                    loop: for (int k = 0; k < possibleQueenMoves.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if (extractAnotherAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleQueenMoves, k, l))
                                    continue;
                                if (extractAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleQueenMoves, k, l))
                                    continue;
                                if (extractAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleQueenMoves, k, l))
                                    continue;

                                extractAnotherAnotherAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleQueenMoves, k, l);
                            }
                        } else {
                            break;
                        }
                    }
                    Spot[][] simulateBoard = new Spot[8][];
                    for (int x = 0; x < 8; x++) {
                        simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                    }
                    simulateBoard[row][col] = new Spot(new Empty());

                    loop: for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                        simulateBoard[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleQueenTakes1 = RookActionsHighlighter.possibleRookMoves(i, j, simulateBoard, StaticValues.WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes2 = BishopActionsHighlighter.possibleBishopMoves(i, j, simulateBoard, StaticValues.WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes = new ArrayList<>();
                        possibleQueenTakes.addAll(possibleQueenTakes1);
                        possibleQueenTakes.addAll(possibleQueenTakes2);
                        for (int k = 0; k < possibleQueenTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingMoves.get(l), possibleQueenTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleQueenTakes.get(k + 1))) && !(board[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)].getPiece().getClass().getName().equals("chess.rook.Rook"))) {
                                possibleKingMoves.remove(l);
                                possibleKingMoves.remove(l);
                                break loop;
                            }
                        }
                    }
                } else if (board[i][j].getPiece().getClass().getName().equals("chess.knight.Knight") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleKnightMoves = KnightActionsHighlighter.possibleKnightMoves(i, j, board, false);
                    removeKingsActions(row, col, board, possibleKingMoves, possibleKnightMoves);
                } else if (board[i][j].getPiece().getClass().getName().equals("chess.king.King") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleEnemyKingMoves = possibleKingMoves(i, j, board, true);
                    loop: for (int k = 0; k < possibleEnemyKingMoves.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if (extractAnotherAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possibleEnemyKingMoves, k, l))
                                    continue;
                                if (removeAnotherKingsActions(row, col, board, possibleKingMoves, possibleEnemyKingMoves, k, l))
                                    continue;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        finalMoves = possibleKingMoves;
        return finalMoves;
    }

    private static boolean removeAnotherKingsActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves, ArrayList<Integer> possibleRookMoves, int k, int l) {
        if (((possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 5) || (possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 6) || (possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
            return true;
        }
        if (((possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 2) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 3) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
            return true;
        }
        if (((possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 5) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 6) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
            return true;
        }
        return false;
    }

    private static void removeKingsActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves, ArrayList<Integer> possiblePawnTakes) {
        loop: for (int k = 0; k < possiblePawnTakes.size(); k += 2) {
            if (possibleKingMoves.size() != 0) {
                for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                    if (extractAnotherAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possiblePawnTakes, k, l))
                        continue loop;
                    if (extractAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possiblePawnTakes, k, l))
                        continue loop;
                    if (extractAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possiblePawnTakes, k, l))
                        continue loop;
                    extractAnotherAnotherAnotherAnotherAnotherKingsActions(row, col, board, possibleKingMoves, possiblePawnTakes, k, l);
                }
            } else {
                break;
            }
        }
    }

    private static void extractAnotherAnotherAnotherAnotherAnotherKingsActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves, ArrayList<Integer> possiblePawnTakes, int k, int l) {
        if (((possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 5) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 6) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
        }
    }

    private static boolean extractAnotherAnotherAnotherAnotherKingsActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves, ArrayList<Integer> possiblePawnTakes, int k, int l) {
        if ((Objects.equals(possibleKingMoves.get(l), possiblePawnTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possiblePawnTakes.get(k + 1)))) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
            return true;
        }
        if (((possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 2) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 3) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
            return true;
        }
        return false;
    }

    private static boolean extractAnotherAnotherAnotherKingsActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves, ArrayList<Integer> possiblePawnTakes, int k, int l) {
        if (((possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 5) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 6) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
            return true;
        }
        return false;
    }

    private static boolean extractAnotherAnotherKingsActions(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves, ArrayList<Integer> possiblePawnTakes, int k, int l) {
        if (((possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 2) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 3) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
            possibleKingMoves.remove(l);
            possibleKingMoves.remove(l);
            return true;
        }
        return false;
    }

    public static ArrayList<Integer> finalKingTakes(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingTakes) {
        ArrayList<Integer> finalTakes;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPiece().getClass().getName().equals("chess.pawn.Pawn") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possiblePawnTakes = PawnActionsHighlighter.possiblePawnTakes(i, j, simulateKingTakesBoard, false, false);
                        for (int k = 0; k < possiblePawnTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possiblePawnTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possiblePawnTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }

                } else if (board[i][j].getPiece().getClass().getName().equals("chess.rook.Rook") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleRookTakes = RookActionsHighlighter.possibleRookMoves(i, j, simulateKingTakesBoard, StaticValues.WANT_TAKES);
                        for (int k = 0; k < possibleRookTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleRookTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleRookTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }

                } else if (board[i][j].getPiece().getClass().getName().equals("chess.bishop.Bishop") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleBishopTakes = BishopActionsHighlighter.possibleBishopMoves(i, j, simulateKingTakesBoard, StaticValues.WANT_TAKES);
                        for (int k = 0; k < possibleBishopTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleBishopTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleBishopTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }

                } else if (board[i][j].getPiece().getClass().getName().equals("chess.knight.Knight") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleKnightTakes = KnightActionsHighlighter.possibleKnightTakes(i, j, KnightActionsHighlighter.possibleKnightMoves(i, j, simulateKingTakesBoard, false), simulateKingTakesBoard);
                        for (int k = 0; k < possibleKnightTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleKnightTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleKnightTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }
                } else if (board[i][j].getPiece().getClass().getName().equals("chess.queen.Queen") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleQueenTakes1 = RookActionsHighlighter.possibleRookMoves(i, j, simulateKingTakesBoard, StaticValues.WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes2 = BishopActionsHighlighter.possibleBishopMoves(i, j, simulateKingTakesBoard, StaticValues.WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes = new ArrayList<>();
                        possibleQueenTakes.addAll(possibleQueenTakes1);
                        possibleQueenTakes.addAll(possibleQueenTakes2);
                        for (int k = 0; k < possibleQueenTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleQueenTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleQueenTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }
                } else if (board[i][j].getPiece().getClass().getName().equals("chess.king.King") && (!SpotObserver.isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleEnemyKingTakes = possibleKingMoves(i, j, simulateKingTakesBoard, false);
                        for (int k = 0; k < possibleEnemyKingTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleEnemyKingTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleEnemyKingTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }
                }
            }
        }
        finalTakes = possibleKingTakes;

        return finalTakes;
    }
}
