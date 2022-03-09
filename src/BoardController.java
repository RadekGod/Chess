import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardController {
    GameFrame gameFrame;
    Move move;
    BoardListener boardListener;

    public BoardController (GameFrame gameFrame, Move move) throws InterruptedException {
        this.gameFrame = gameFrame;
        this.move = move;
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
                gameFrame.board.setSelectedCol(firstClickRow);
                gameFrame.board.setSelectedRow(firstClickCol);
                secondClickRow = 8;
                secondClickCol = 8;
                firstClick = false;
                switch (gameFrame.board.board[firstClickRow][firstClickCol].getPiece().getClass().getName()) {
                    case "Pawn":
                        if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            if (whiteTurn) {
                                ArrayList<Integer> possiblePawnMoves = move.possiblePawnMoves(firstClickRow, firstClickCol, gameFrame.board.board);
                                gameFrame.board.setPossibleMoves(possiblePawnMoves);
                                ArrayList<Integer> possiblePawnTakes1 = move.possiblePawnTakes(firstClickRow, firstClickCol, gameFrame.board.board, false, true);
                                ArrayList<Integer> possiblePawnTakes2 = move.possibleTakingInFlight(firstClickRow, firstClickCol, gameFrame.board.board, previousBlackMove, true);
                                ArrayList<Integer> possiblePawnTakes = new ArrayList<>();
                                possiblePawnTakes.addAll(possiblePawnTakes1);
                                possiblePawnTakes.addAll(possiblePawnTakes2);
                                gameFrame.board.setPossibleTakes(possiblePawnTakes);
                                pawnMovesToCheck = new ArrayList<>(possiblePawnMoves);
                                pawnMovesToCheck.addAll(possiblePawnTakes);
                            } else {
                                ArrayList<Integer> possiblePawnMoves = move.possiblePawnMoves(firstClickRow, firstClickCol, gameFrame.board.board);
                                gameFrame.board.setPossibleMoves(possiblePawnMoves);
                                ArrayList<Integer> possiblePawnTakes1 = move.possiblePawnTakes(firstClickRow, firstClickCol, gameFrame.board.board, false, true);
                                ArrayList<Integer> possiblePawnTakes2 = move.possibleTakingInFlight(firstClickRow, firstClickCol, gameFrame.board.board, previousWhiteMove, false);
                                ArrayList<Integer> possiblePawnTakes = new ArrayList<>();
                                possiblePawnTakes.addAll(possiblePawnTakes1);
                                possiblePawnTakes.addAll(possiblePawnTakes2);
                                gameFrame.board.setPossibleTakes(possiblePawnTakes);
                                pawnMovesToCheck = new ArrayList<>(possiblePawnMoves);
                                pawnMovesToCheck.addAll(possiblePawnTakes);
                            }

                        }
                        break;
                    case "Knight":
                        if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleMoves = move.possibleKnightMoves(firstClickRow, firstClickCol, gameFrame.board.board, true);
                            gameFrame.board.setPossibleMoves(possibleMoves);
                            gameFrame.board.setPossibleTakes(move.possibleKnightTakes(firstClickRow, firstClickCol, possibleMoves, gameFrame.board.board));
                            knightMovesToCheck = new ArrayList<>(possibleMoves);

                        }
                        break;
                    case "Rook":
                        if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleMoves = move.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleTakes = move.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_TAKES);
                            gameFrame.board.setPossibleMoves(possibleMoves);
                            gameFrame.board.setPossibleTakes(possibleTakes);
                            rookMovesToCheck = new ArrayList<>(possibleMoves);
                            rookMovesToCheck.addAll(possibleTakes);
                        }
                        break;
                    case "Bishop":
                        if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleMoves = move.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleTakes = move.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_TAKES);
                            gameFrame.board.setPossibleMoves(possibleMoves);
                            gameFrame.board.setPossibleTakes(possibleTakes);
                            bishopMovesToCheck = new ArrayList<>(possibleMoves);
                            bishopMovesToCheck.addAll(possibleTakes);
                        }
                        break;
                    case "Queen":
                        if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleQueenMoves1 = move.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleQueenTakes1 = move.possibleRookMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_TAKES);
                            ArrayList<Integer> possibleQueenMoves2 = move.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_MOVES);
                            ArrayList<Integer> possibleQueenTakes2 = move.possibleBishopMoves(firstClickRow, firstClickCol, gameFrame.board.board, Move.WANT_SIMULATED_TAKES);
                            ArrayList<Integer> possibleQueenMoves = new ArrayList<>();
                            ArrayList<Integer> possibleQueenTakes = new ArrayList<>();
                            possibleQueenMoves.addAll(possibleQueenMoves1);
                            possibleQueenMoves.addAll(possibleQueenMoves2);
                            possibleQueenTakes.addAll(possibleQueenTakes1);
                            possibleQueenTakes.addAll(possibleQueenTakes2);
                            gameFrame.board.setPossibleMoves(possibleQueenMoves);
                            gameFrame.board.setPossibleTakes(possibleQueenTakes);
                            queenMovesToCheck = new ArrayList<>(possibleQueenMoves);
                            queenMovesToCheck.addAll(possibleQueenTakes);
                        }
                        break;
                    case "King":
                        if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                            ArrayList<Integer> possibleKingMoves1 = move.possibleKingMoves(firstClickRow, firstClickCol, gameFrame.board.board, true);
                            ArrayList<Integer> possibleKingMoves = move.finalKingMoves(firstClickRow, firstClickCol, gameFrame.board.board, possibleKingMoves1);
                            gameFrame.board.setPossibleMoves(possibleKingMoves);
                            ArrayList<Integer> possibleKingTakes1 = move.possibleKingMoves(firstClickRow, firstClickCol, gameFrame.board.board, false);
                            ArrayList<Integer> possibleKingTakes = move.finalKingTakes(firstClickRow, firstClickCol, gameFrame.board.board, possibleKingTakes1);
                            gameFrame.board.setPossibleTakes(possibleKingTakes);
                            kingMovesToCheck = new ArrayList<>(possibleKingMoves);
                            kingMovesToCheck.addAll(possibleKingTakes);
                        }
                        break;
                }

            } else {
                secondClickRow = e.getY() / 75;
                secondClickCol = e.getX() / 75;
                switch (gameFrame.board.board[firstClickRow][firstClickCol].getPiece().getClass().getName()) {
                    case "Pawn":
                        for (int j = 0; j < pawnMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == pawnMovesToCheck.get(j) && secondClickCol == pawnMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (move.newPawnMove(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.board.board)) {
                                        if (whiteTurn) {
                                            previousWhiteMove[0] = firstClickRow;
                                            previousWhiteMove[1] = firstClickCol;
                                            previousWhiteMove[2] = secondClickRow;
                                            previousWhiteMove[3] = secondClickCol;


                                            previousWhitePawnMove[0] = firstClickRow;
                                            previousWhitePawnMove[1] = firstClickCol;
                                            previousWhitePawnMove[2] = secondClickRow;
                                            previousWhitePawnMove[3] = secondClickCol;
                                        } else {
                                            previousBlackPawnMove[0] = firstClickRow;
                                            previousBlackPawnMove[1] = firstClickCol;
                                            previousBlackPawnMove[2] = secondClickRow;
                                            previousBlackPawnMove[3] = secondClickCol;


                                            previousBlackMove[0] = firstClickRow;
                                            previousBlackMove[1] = firstClickCol;
                                            previousBlackMove[2] = secondClickRow;
                                            previousBlackMove[3] = secondClickCol;
                                        }
                                        for (int i = 0; i < 8; i++) {
                                            if (gameFrame.board.board[0][i].getPiece().getClass().getName().equals("Pawn")) {
                                                PawnChangeFrame pawnChangeFrame = new PawnChangeFrame(gameFrame);
                                                switch (pawnChangeFrame.getChoice()) {
                                                    case 0 -> gameFrame.board.board[0][i] = new Spot(new Queen(true, false));
                                                    case 1 -> gameFrame.board.board[0][i] = new Spot(new Rook(true, false));
                                                    case 2 -> gameFrame.board.board[0][i] = new Spot(new Knight(true, false));
                                                    case 3 -> gameFrame.board.board[0][i] = new Spot(new Bishop(true, false));
                                                }

                                            } else if (gameFrame.board.board[7][i].getPiece().getClass().getName().equals("Pawn")) {
                                                PawnChangeFrame pawnChangeFrame = new PawnChangeFrame(gameFrame);
                                                switch (pawnChangeFrame.getChoice()) {
                                                    case 0 -> gameFrame.board.board[7][i] = new Spot(new Queen(false, false));
                                                    case 1 -> gameFrame.board.board[7][i] = new Spot(new Rook(false, false));
                                                    case 2 -> gameFrame.board.board[7][i] = new Spot(new Knight(false, false));
                                                    case 3 -> gameFrame.board.board[7][i] = new Spot(new Bishop(false, false));
                                                }
                                            }
                                            gameFrame.board.repaint();
                                        }
                                        whiteTurn = !whiteTurn;
                                        timerController.setWhiteTurn(whiteTurn);
                                        gameFrame.board.setWhiteTurn(whiteTurn);
                                    }
                                }
                                break;
                            }
                        }

                        break;
                    case "Knight":
                        for (int j = 0; j < knightMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == knightMovesToCheck.get(j) && secondClickCol == knightMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (move.knightMove(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.board.board)) {
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
                                        gameFrame.board.setWhiteTurn(whiteTurn);
                                    }
                                }
                            }
                        }
                        break;
                    case "Rook":
                        for (int j = 0; j < rookMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == rookMovesToCheck.get(j) && secondClickCol == rookMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (move.rookMove(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.board.board)) {
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
                                        gameFrame.board.setWhiteTurn(whiteTurn);
                                    }
                                }
                            }
                        }
                        break;
                    case "Bishop":
                        for (int j = 0; j < bishopMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == bishopMovesToCheck.get(j) && secondClickCol == bishopMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (move.bishopMove(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.board.board)) {
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
                                        gameFrame.board.setWhiteTurn(whiteTurn);
                                    }
                                }
                            }
                        }
                        break;
                    case "Queen":
                        for (int j = 0; j < queenMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == queenMovesToCheck.get(j) && secondClickCol == queenMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (move.queenMove(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.board.board)) {
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
                                        gameFrame.board.setWhiteTurn(whiteTurn);
                                    }
                                }
                            }
                        }
                        break;
                    case "King":
                        for (int j = 0; j < kingMovesToCheck.size(); j+= 2) {
                            if (secondClickRow == kingMovesToCheck.get(j) && secondClickCol == kingMovesToCheck.get(j + 1)) {
                                if (whiteTurn == gameFrame.board.board[firstClickRow][firstClickCol].getPiece().isWhite()) {
                                    if (move.kingMove(firstClickRow, firstClickCol, secondClickRow, secondClickCol, gameFrame.board.board)) {
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
                                        gameFrame.board.setWhiteTurn(whiteTurn);
                                    }
                                }
                            }
                        }
                        break;
                }

                ArrayList<Integer> possibleCheckMateCords = move.lookForCheckMate(gameFrame.board.board);
                gameFrame.board.setPossibleCheckMate(possibleCheckMateCords);
                if (possibleCheckMateCords.size() > 0) {
                    int kingRow = possibleCheckMateCords.get(0);
                    int kingCol = possibleCheckMateCords.get(1);
                    if (gameFrame.board.board[kingRow][kingCol].getPiece().isWhite()) {
                        whiteCheck = true;
                        blackCheck = false;
                    } else if (gameFrame.board.board[kingRow][kingCol].getPiece().isBlack()) {
                        whiteCheck = false;
                        blackCheck = true;
                    }
                    move.possibleCheckMateMoves(gameFrame.board.board, possibleCheckMateCords);

                    gameFrame.board.repaint();
                    if (gameFrame.board.getPiecesAbleToMove().size() == 0 && gameFrame.board.getPossibleActions().size() == 0 && whiteCheck) {
                        try {
                            new GameFinishedFrame(gameFrame, gameFrame.boardController, timerController, false);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    } else if (gameFrame.board.getPiecesAbleToMove().size() == 0 && gameFrame.board.getPossibleActions().size() == 0 && blackCheck) {
                        try {
                            new GameFinishedFrame(gameFrame, gameFrame.boardController, timerController,true);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }


                gameFrame.board.setSelectedRow(8);
                gameFrame.board.setSelectedCol(8);


                firstClick = true;
            }
            if (firstClickCol == secondClickCol && firstClickRow == secondClickRow) {
                gameFrame.board.setSelectedRow(8);
                gameFrame.board.setSelectedCol(8);
            }
            gameFrame.board.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
