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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardController {
    GameFrame gameFrame;
    BoardListener boardListener;

    public BoardController (GameFrame gameFrame) throws InterruptedException {
        this.gameFrame = gameFrame;
        this.boardListener = new BoardListener();
        this.gameFrame.addBoardListener(boardListener);
        gameFrame.setBoardController(this);
    }
    class BoardListener implements MouseListener {
        TimerController timerController;
        boolean firstClick = true;
        boolean whiteTurn = true;
        boolean whiteCheck = false;
        boolean blackCheck = false;
        int firstClickRow;
        int firstClickCol;
        int secondClickRow;
        int secondClickCol;
        ArrayList<Integer> pawnMovesToCheck = new ArrayList<>();
        ArrayList<Integer> knightMovesToCheck = new ArrayList<>();
        ArrayList<Integer> bishopMovesToCheck = new ArrayList<>();
        ArrayList<Integer> rookMovesToCheck = new ArrayList<>();
        ArrayList<Integer> queenMovesToCheck = new ArrayList<>();
        ArrayList<Integer> kingMovesToCheck = new ArrayList<>();
        int[] previousBlackPawnMove = new int[4];
        int[] previousBlackMove = new int[4];
        int[] previousWhitePawnMove = new int[4];
        int[] previousWhiteMove = new int[4];

        BoardListener() {
            timerController = new TimerController(gameFrame);
        }


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        public void setWhiteTurn(boolean whiteTurn) {
            this.whiteTurn = whiteTurn;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (firstClick) {
                firstClickRow = e.getY() / 75;
                firstClickCol = e.getX() / 75;
                gameFrame.getBoard().setSelectedCol(firstClickRow);
                gameFrame.getBoard().setSelectedRow(firstClickCol);
                secondClickRow = 8;
                secondClickCol = 8;
                firstClick = false;
                switch (gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().getClass().getName()) {
                    case "chess.pawn.Pawn":
                        if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possiblePawnTakes1;
                            ArrayList<Integer> possiblePawnTakes2;
                            ArrayList<Integer> possiblePawnMoves;
                            if (whiteTurn) {
                                possiblePawnMoves = PawnActionsHighlighter.possiblePawnMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board);
                                gameFrame.getBoard().setPossibleMoves(possiblePawnMoves);
                                possiblePawnTakes1 = PawnActionsHighlighter.possiblePawnTakes(firstClickRow, firstClickCol, gameFrame.getBoard().board, false, true);
                                possiblePawnTakes2 = PawnActionsHighlighter.possibleTakingInFlight(firstClickRow, firstClickCol, gameFrame.getBoard().board, previousBlackMove, true);

                            } else {
                                possiblePawnMoves = PawnActionsHighlighter.possiblePawnMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board);
                                gameFrame.getBoard().setPossibleMoves(possiblePawnMoves);
                                possiblePawnTakes1 = PawnActionsHighlighter.possiblePawnTakes(firstClickRow, firstClickCol, gameFrame.getBoard().board, false, true);
                                possiblePawnTakes2 = PawnActionsHighlighter.possibleTakingInFlight(firstClickRow, firstClickCol, gameFrame.getBoard().board, previousWhiteMove, false);
                            }
                            ArrayList<Integer> possiblePawnTakes = new ArrayList<>();
                            possiblePawnTakes.addAll(possiblePawnTakes1);
                            possiblePawnTakes.addAll(possiblePawnTakes2);
                            gameFrame.getBoard().setPossibleTakes(possiblePawnTakes);
                            pawnMovesToCheck = new ArrayList<>(possiblePawnMoves);
                            pawnMovesToCheck.addAll(possiblePawnTakes);

                        }
                        break;
                    case "chess.knight.Knight":
                        if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleMoves = KnightActionsHighlighter.possibleKnightMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, true);
                            gameFrame.getBoard().setPossibleMoves(possibleMoves);
                            gameFrame.getBoard().setPossibleTakes(KnightActionsHighlighter.possibleKnightTakes(firstClickRow, firstClickCol, possibleMoves, gameFrame.getBoard().board));
                            knightMovesToCheck = new ArrayList<>(possibleMoves);

                        }
                        break;
                    case "chess.rook.Rook":
                        if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleMoves = RookActionsHighlighter.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleTakes = RookActionsHighlighter.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_TAKES);
                            gameFrame.getBoard().setPossibleMoves(possibleMoves);
                            gameFrame.getBoard().setPossibleTakes(possibleTakes);
                            rookMovesToCheck = new ArrayList<>(possibleMoves);
                            rookMovesToCheck.addAll(possibleTakes);
                        }
                        break;
                    case "chess.bishop.Bishop":
                        if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleMoves = BishopActionsHighlighter.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleTakes = BishopActionsHighlighter.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_TAKES);
                            gameFrame.getBoard().setPossibleMoves(possibleMoves);
                            gameFrame.getBoard().setPossibleTakes(possibleTakes);
                            bishopMovesToCheck = new ArrayList<>(possibleMoves);
                            bishopMovesToCheck.addAll(possibleTakes);
                        }
                        break;
                    case "chess.queen.Queen":
                        if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleQueenMoves1 = RookActionsHighlighter.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleQueenTakes1 = RookActionsHighlighter.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_TAKES);
                            ArrayList<Integer> possibleQueenMoves2 = BishopActionsHighlighter.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleQueenTakes2 = BishopActionsHighlighter.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, StaticValues.WANT_SIMULATED_TAKES);
                            ArrayList<Integer> possibleQueenMoves = new ArrayList<>();
                            ArrayList<Integer> possibleQueenTakes = new ArrayList<>();
                            possibleQueenMoves.addAll(possibleQueenMoves1);
                            possibleQueenMoves.addAll(possibleQueenMoves2);
                            possibleQueenTakes.addAll(possibleQueenTakes1);
                            possibleQueenTakes.addAll(possibleQueenTakes2);
                            gameFrame.getBoard().setPossibleMoves(possibleQueenMoves);
                            gameFrame.getBoard().setPossibleTakes(possibleQueenTakes);
                            queenMovesToCheck = new ArrayList<>(possibleQueenMoves);
                            queenMovesToCheck.addAll(possibleQueenTakes);
                        }
                        break;
                    case "chess.king.King":
                        if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleKingMoves1 = KingActionsHighlighter.possibleKingMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, true);
                            ArrayList<Integer> possibleKingMoves = KingActionsHighlighter.finalKingMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, possibleKingMoves1);
                            gameFrame.getBoard().setPossibleMoves(possibleKingMoves);
                            ArrayList<Integer> possibleKingTakes1 = KingActionsHighlighter.possibleKingMoves(firstClickRow, firstClickCol, gameFrame.getBoard().board, false);
                            ArrayList<Integer> possibleKingTakes = KingActionsHighlighter.finalKingTakes(firstClickRow, firstClickCol, gameFrame.getBoard().board, possibleKingTakes1);
                            gameFrame.getBoard().setPossibleTakes(possibleKingTakes);
                            kingMovesToCheck = new ArrayList<>(possibleKingMoves);
                            kingMovesToCheck.addAll(possibleKingTakes);
                        }
                        break;
                }

            } else {
                secondClickRow = e.getY() / 75;
                secondClickCol = e.getX() / 75;
                switch (gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().getClass().getName()) {
                    case "chess.pawn.Pawn":
                        for (int j = 0; j < pawnMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == pawnMovesToCheck.get(j) && secondClickCol == pawnMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (Pawn.move(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.getBoard().board)) {
                                        if (whiteTurn) {
                                            setPreviousPawnMoves(previousWhiteMove, previousWhitePawnMove);
                                        } else {
                                            setPreviousPawnMoves(previousBlackPawnMove, previousBlackMove);
                                        }
                                        for (int i = 0; i < 8; i++) {
                                            if (gameFrame.getBoard().board[0][i].getPiece().getClass().getName().equals("chess.pawn.Pawn")) {
                                                PawnChangeFrame pawnChangeFrame = new PawnChangeFrame(gameFrame);
                                                switch (pawnChangeFrame.getChoice()) {
                                                    case 0 -> gameFrame.getBoard().board[0][i] = new Spot(new Queen(true, false));
                                                    case 1 -> gameFrame.getBoard().board[0][i] = new Spot(new Rook(true, false));
                                                    case 2 -> gameFrame.getBoard().board[0][i] = new Spot(new Knight(true, false));
                                                    case 3 -> gameFrame.getBoard().board[0][i] = new Spot(new Bishop(true, false));
                                                }

                                            } else if (gameFrame.getBoard().board[7][i].getPiece().getClass().getName().equals("chess.pawn.Pawn")) {
                                                PawnChangeFrame pawnChangeFrame = new PawnChangeFrame(gameFrame);
                                                switch (pawnChangeFrame.getChoice()) {
                                                    case 0 -> gameFrame.getBoard().board[7][i] = new Spot(new Queen(false, false));
                                                    case 1 -> gameFrame.getBoard().board[7][i] = new Spot(new Rook(false, false));
                                                    case 2 -> gameFrame.getBoard().board[7][i] = new Spot(new Knight(false, false));
                                                    case 3 -> gameFrame.getBoard().board[7][i] = new Spot(new Bishop(false, false));
                                                }
                                            }
                                            gameFrame.getBoard().repaint();
                                        }
                                        whiteTurn = !whiteTurn;
                                        timerController.setWhiteTurn(whiteTurn);
                                        gameFrame.getBoard().setWhiteTurn(whiteTurn);
                                        gameFrame.getBoard().setWhiteTurn(whiteTurn);
                                    }
                                }
                                break;
                            }
                        }

                        break;
                    case "chess.knight.Knight":
                        for (int j = 0; j < knightMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == knightMovesToCheck.get(j) && secondClickCol == knightMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (Knight.knightMove(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.getBoard().board)) {
                                        setPreviousMoves();
                                    }
                                }
                            }
                        }
                        break;
                    case "chess.rook.Rook":
                        for (int j = 0; j < rookMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == rookMovesToCheck.get(j) && secondClickCol == rookMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (Rook.move(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.getBoard().board)) {
                                        setPreviousMoves();
                                    }
                                }
                            }
                        }
                        break;
                    case "chess.bishop.Bishop":
                        for (int j = 0; j < bishopMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == bishopMovesToCheck.get(j) && secondClickCol == bishopMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (Bishop.move(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.getBoard().board)) {
                                        setPreviousMoves();
                                    }
                                }
                            }
                        }
                        break;
                    case "chess.queen.Queen":
                        for (int j = 0; j < queenMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == queenMovesToCheck.get(j) && secondClickCol == queenMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (Queen.move(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.getBoard().board)) {
                                        setPreviousMoves();
                                    }
                                }
                            }
                        }
                        break;
                    case "chess.king.King":
                        for (int j = 0; j < kingMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == kingMovesToCheck.get(j) && secondClickCol == kingMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.getBoard().board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (King.move(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.getBoard().board)) {
                                        setPreviousMoves();
                                    }
                                }
                            }
                        }
                        break;
                }

                ArrayList<Integer> possibleCheckMateCords = CheckMateObserver.lookForCheckMate(gameFrame.getBoard().board);
                gameFrame.getBoard().setPossibleCheckMate(possibleCheckMateCords);
                if (possibleCheckMateCords.size() > 0) {
                    int kingRow = possibleCheckMateCords.get(0);
                    int kingCol = possibleCheckMateCords.get(1);
                    if (gameFrame.getBoard().board[kingRow][kingCol].getPiece().isWhite()) {
                        whiteCheck = true;
                        blackCheck = false;
                    } else if (gameFrame.getBoard().board[kingRow][kingCol].getPiece().isBlack()) {
                        whiteCheck = false;
                        blackCheck = true;
                    }
                    CheckMateObserver.possibleCheckMateMoves(gameFrame, possibleCheckMateCords);

                    gameFrame.getBoard().repaint();
                    if (gameFrame.getBoard().getPiecesAbleToMove().size() == 0 && gameFrame.getBoard().getPossibleActions().size() == 0 && whiteCheck) {
                        try {
                            new GameFinishedFrame(gameFrame, gameFrame.getBoardController(), timerController, false);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    } else if (gameFrame.getBoard().getPiecesAbleToMove().size() == 0 && gameFrame.getBoard().getPossibleActions().size() == 0 && blackCheck) {
                        try {
                            new GameFinishedFrame(gameFrame, gameFrame.getBoardController(), timerController,true);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }


                gameFrame.getBoard().setSelectedRow(8);
                gameFrame.getBoard().setSelectedCol(8);


                firstClick = true;
            }
            if (firstClickCol == secondClickCol && firstClickRow == secondClickRow) {
                gameFrame.getBoard().setSelectedRow(8);
                gameFrame.getBoard().setSelectedCol(8);
            }
            gameFrame.getBoard().repaint();
        }

        private void setPreviousPawnMoves(int[] previousBlackPawnMove, int[] previousBlackMove) {
            previousBlackPawnMove[0] = firstClickRow;
            previousBlackPawnMove[1] = firstClickCol;
            previousBlackPawnMove[2] = secondClickRow;
            previousBlackPawnMove[3] = secondClickCol;


            previousBlackMove[0] = firstClickRow;
            previousBlackMove[1] = firstClickCol;
            previousBlackMove[2] = secondClickRow;
            previousBlackMove[3] = secondClickCol;
        }

        private void setPreviousMoves() {
            if (whiteTurn) {
                previousWhiteMove[0] = firstClickRow;
                previousWhiteMove[1] = firstClickCol;
                previousWhiteMove[2] = secondClickRow;
                previousWhiteMove[3] = secondClickCol;
            } else {
                previousBlackMove[0] = firstClickRow;
                previousBlackMove[1] = firstClickCol;
                previousBlackMove[2] = secondClickRow;
                previousBlackMove[3] = secondClickCol;
            }
            whiteTurn = !whiteTurn;
            timerController.setWhiteTurn(whiteTurn);
            gameFrame.getBoard().setWhiteTurn(whiteTurn);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
