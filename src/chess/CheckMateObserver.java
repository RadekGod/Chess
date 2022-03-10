package chess;

import chess.bishop.Bishop;
import chess.bishop.BishopActionsHighlighter;
import chess.king.King;
import chess.king.KingActionsHighlighter;
import chess.knight.Knight;
import chess.knight.KnightActionsHighlighter;
import chess.pawn.Pawn;
import chess.pawn.PawnActionsHighlighter;
import chess.queen.Queen;
import chess.rook.Rook;
import chess.rook.RookActionsHighlighter;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckMateObserver {

    public static void possibleCheckMateMoves(GameFrame gameFrame, ArrayList<Integer> checkMateCords) {
        Spot[][] board = gameFrame.getBoard().board;
        ArrayList<Integer> possiblePieceTakes;
        ArrayList<Integer> possiblePieceMoves;
        ArrayList<Integer> possiblePawnActions = new ArrayList<>();
        ArrayList<Integer> possibleKnightActions = new ArrayList<>();
        ArrayList<Integer> possibleRookActions = new ArrayList<>();
        ArrayList<Integer> possibleBishopActions = new ArrayList<>();
        ArrayList<Integer> possibleQueenActions = new ArrayList<>();
        ArrayList<Integer> possibleKingActions = new ArrayList<>();
        ArrayList<Integer> possibleCheckMateMoves = new ArrayList<>();
        ArrayList<Integer> piecesAbleToMove = new ArrayList<>();
        int kingRow = checkMateCords.get(0);
        int kingCol = checkMateCords.get(1);
        Spot[][] simulateBoard = new Spot[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (SpotObserver.isTheSameColor(kingRow, kingCol, i, j, board)) {
                    switch (board[i][j].getPiece().getClass().getName()) {
                        case "chess.pawn.Pawn" -> {
                            boolean addCords = false;
                            possiblePieceTakes = PawnActionsHighlighter.possiblePawnTakes(i, j, board, false, false);
                            possiblePieceMoves = PawnActionsHighlighter.possiblePawnMoves(i, j, board);
                            possiblePawnActions.clear();
                            possiblePawnActions.addAll(possiblePieceTakes);
                            possiblePawnActions.addAll(possiblePieceMoves);
                            possiblePieceMoves.clear();
                            possiblePieceTakes.clear();
                            for (int a = possiblePawnActions.size() - 1; a >= 0; a -= 2) {
                                for (int x = 0; x < 8; x++) {
                                    simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                                }
                                simulateBoard[i][j] = new Spot(new Empty());
                                simulateBoard[possiblePawnActions.get(a - 1)][possiblePawnActions.get(a)] = new Spot(new Pawn(board[kingRow][kingCol].getPiece().isWhite(), false, false));
                                if (lookForCheckMate(simulateBoard).size() != 0) {
                                    possiblePawnActions.remove(a - 1);
                                    possiblePawnActions.remove(a - 1);
                                } else {
                                    addCords = true;
                                }
                            }
                            if (addCords) {
                                piecesAbleToMove.add(i);
                                piecesAbleToMove.add(j);
                            }
                            possibleCheckMateMoves.addAll(possiblePawnActions);
                        }
                        case "chess.knight.Knight" -> {
                            boolean addCords = false;
                            possiblePieceMoves = KnightActionsHighlighter.possibleKnightMoves(i, j, board, false);
                            possiblePieceTakes = KnightActionsHighlighter.possibleKnightTakes(i, j, possiblePieceMoves, board);
                            possibleKnightActions.clear();
                            possibleKnightActions.addAll(possiblePieceTakes);
                            possibleKnightActions.addAll(possiblePieceMoves);
                            possiblePieceMoves.clear();
                            possiblePieceTakes.clear();
                            for (int a = possibleKnightActions.size() - 1; a >= 0; a -= 2) {
                                for (int x = 0; x < 8; x++) {
                                    simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                                }
                                simulateBoard[i][j] = new Spot(new Empty());
                                simulateBoard[possibleKnightActions.get(a - 1)][possibleKnightActions.get(a)] = new Spot(new Knight(board[kingRow][kingCol].getPiece().isWhite(), false));
                                if (lookForCheckMate(simulateBoard).size() != 0) {
                                    possibleKnightActions.remove(a - 1);
                                    possibleKnightActions.remove(a - 1);
                                } else {
                                    addCords = true;
                                }
                            }
                            if (addCords) {
                                piecesAbleToMove.add(i);
                                piecesAbleToMove.add(j);
                            }
                            possibleCheckMateMoves.addAll(possibleKnightActions);
                        }
                        case "chess.bishop.Bishop" -> {
                            boolean addCords = false;
                            possiblePieceTakes = BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_TAKES);
                            possiblePieceMoves = BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_MOVES);
                            possibleBishopActions.clear();
                            possibleBishopActions.addAll(possiblePieceTakes);
                            possibleBishopActions.addAll(possiblePieceMoves);
                            possiblePieceMoves.clear();
                            possiblePieceTakes.clear();
                            for (int a = possibleBishopActions.size() - 1; a >= 0; a -= 2) {
                                for (int x = 0; x < 8; x++) {
                                    simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                                }
                                simulateBoard[i][j] = new Spot(new Empty());
                                simulateBoard[possibleBishopActions.get(a - 1)][possibleBishopActions.get(a)] = new Spot(new Bishop(board[kingRow][kingCol].getPiece().isWhite(), false));
                                if (lookForCheckMate(simulateBoard).size() != 0) {
                                    possibleBishopActions.remove(a - 1);
                                    possibleBishopActions.remove(a - 1);
                                } else {
                                    addCords = true;
                                }
                            }
                            if (addCords) {
                                piecesAbleToMove.add(i);
                                piecesAbleToMove.add(j);
                            }
                            possibleCheckMateMoves.addAll(possibleBishopActions);
                        }
                        case "chess.rook.Rook" -> {
                            boolean addCords = false;
                            possiblePieceTakes = RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_TAKES);
                            possiblePieceMoves = RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_MOVES);
                            possibleRookActions.clear();
                            possibleRookActions.addAll(possiblePieceTakes);
                            possibleRookActions.addAll(possiblePieceMoves);
                            possiblePieceMoves.clear();
                            possiblePieceTakes.clear();
                            for (int a = possibleRookActions.size() - 1; a >= 0; a -= 2) {
                                for (int x = 0; x < 8; x++) {
                                    simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                                }
                                simulateBoard[i][j] = new Spot(new Empty());
                                simulateBoard[possibleRookActions.get(a - 1)][possibleRookActions.get(a)] = new Spot(new Rook(board[kingRow][kingCol].getPiece().isWhite(), false));
                                if (lookForCheckMate(simulateBoard).size() != 0) {
                                    possibleRookActions.remove(a - 1);
                                    possibleRookActions.remove(a - 1);
                                } else {
                                    addCords = true;
                                }
                            }
                            if (addCords) {
                                piecesAbleToMove.add(i);
                                piecesAbleToMove.add(j);
                            }
                            possibleCheckMateMoves.addAll(possibleRookActions);
                        }
                        case "chess.queen.Queen" -> {
                            boolean addCords = false;
                            possiblePieceTakes = RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_TAKES);
                            possiblePieceTakes.addAll(BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_TAKES));
                            possiblePieceMoves = RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_MOVES);
                            possiblePieceMoves.addAll(BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_MOVES));
                            possibleQueenActions.clear();
                            possibleQueenActions.addAll(possiblePieceTakes);
                            possibleQueenActions.addAll(possiblePieceMoves);
                            possiblePieceMoves.clear();
                            possiblePieceTakes.clear();
                            for (int a = possibleQueenActions.size() - 1; a >= 0; a -= 2) {
                                for (int x = 0; x < 8; x++) {
                                    simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                                }
                                simulateBoard[i][j] = new Spot(new Empty());
                                simulateBoard[possibleQueenActions.get(a - 1)][possibleQueenActions.get(a)] = new Spot(new Queen(board[kingRow][kingCol].getPiece().isWhite(), false));
                                if (lookForCheckMate(simulateBoard).size() != 0) {
                                    possibleQueenActions.remove(a - 1);
                                    possibleQueenActions.remove(a - 1);
                                } else {
                                    addCords = true;
                                }
                            }
                            if (addCords) {
                                piecesAbleToMove.add(i);
                                piecesAbleToMove.add(j);
                            }
                            possibleCheckMateMoves.addAll(possibleQueenActions);
                        }
                        case "chess.king.King" -> {
                            boolean addCords = false;
                            possiblePieceTakes = KingActionsHighlighter.possibleKingMoves(i, j, board, false);
                            possiblePieceMoves = KingActionsHighlighter.possibleKingMoves(i, j, board, true);
                            possibleKingActions.clear();
                            possibleKingActions.addAll(possiblePieceTakes);
                            possibleKingActions.addAll(possiblePieceMoves);
                            possiblePieceMoves.clear();
                            possiblePieceTakes.clear();
                            for (int a = possibleKingActions.size() - 1; a >= 0; a -= 2) {
                                for (int x = 0; x < 8; x++) {
                                    simulateBoard[x] = Arrays.copyOf(board[x], board[x].length);
                                }
                                simulateBoard[i][j] = new Spot(new Empty());
                                simulateBoard[possibleKingActions.get(a - 1)][possibleKingActions.get(a)] = new Spot(new King(board[kingRow][kingCol].getPiece().isWhite(), false));
                                if (lookForCheckMate(simulateBoard).size() != 0) {
                                    possibleKingActions.remove(a - 1);
                                    possibleKingActions.remove(a - 1);
                                } else {
                                    addCords = true;
                                }
                            }
                            if (addCords) {
                                piecesAbleToMove.add(i);
                                piecesAbleToMove.add(j);
                            }
                            possibleCheckMateMoves.addAll(possibleKingActions);
                        }
                    }
                }
            }
        }
        gameFrame.getBoard().setPiecesAbleToMove(piecesAbleToMove);
        gameFrame.getBoard().setPossibleActions(possibleCheckMateMoves);
    }


    public static ArrayList<Integer> lookForCheckMate (Spot[][] board) {
        ArrayList<Integer> checkMateCords = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (board[i][j].getPiece().getClass().getName()) {
                    case "chess.pawn.Pawn" -> checkMateCords(checkMateCords, board, PawnActionsHighlighter.possiblePawnTakes(i, j, board, false, false), i , j);

                    case "chess.rook.Rook" -> checkMateCords(checkMateCords, board, RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_TAKES), i, j);
                    case "chess.knight.Knight" -> {
                        ArrayList<Integer> possibleMoves = KnightActionsHighlighter.possibleKnightMoves(i, j, board, false);
                        checkMateCords(checkMateCords, board, KnightActionsHighlighter.possibleKnightTakes(i, j, possibleMoves, board), i, j);
                    }
                    case "chess.bishop.Bishop" -> checkMateCords(checkMateCords, board, BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_TAKES), i, j);
                    case "chess.queen.Queen" -> {
                        ArrayList<Integer> possibleQueenTakes1 = RookActionsHighlighter.possibleRookMoves(i, j, board, StaticValues.WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes2 = BishopActionsHighlighter.possibleBishopMoves(i, j, board, StaticValues.WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes = new ArrayList<>();
                        possibleQueenTakes.addAll(possibleQueenTakes1);
                        possibleQueenTakes.addAll(possibleQueenTakes2);
                        checkMateCords(checkMateCords, board, possibleQueenTakes, i, j);
                    }
                }

            }
        }
        return checkMateCords;
    }

    private static void checkMateCords(ArrayList<Integer> previousCheck, Spot[][] board, ArrayList<Integer> takes, int row, int col) {
        for (int i = 0; i < takes.size(); i += 2) {
            if (board[takes.get(i)][takes.get(i + 1)].getPiece().getClass().getName().equals("chess.king.King")) {
                if (previousCheck.size() == 0) {
                    previousCheck.add(takes.get(i));
                    previousCheck.add(takes.get(i + 1));
                }
                previousCheck.add(row);
                previousCheck.add(col);
            }
        }
    }
}
