import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Move {
    private final GameFrame gameFrame;
    static final int WANT_MOVES = 1;
    static final int WANT_TAKES = 2;
    static final int WANT_SIMULATED_MOVES = 3;
    static final int WANT_SIMULATED_TAKES = 4;
    public Move(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }


    public boolean newPawnMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        if (board[fromRow][fromCol].getPiece().isWhite()) {
            if ((fromRow - toRow == 1) && (Math.abs(fromCol - toCol) == 1) && board[toRow][toCol].getPiece().isBlack()) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Pawn(true, false, false));
                return true;
            }
            if (board[toRow][toCol].getPiece().isBlack() && (board[toRow][toCol].getPiece().getClass().getName().equals("Pawn")) && board[toRow - 1][toCol].isFieldEmpty() && board[toRow][toCol].getPiece().isSecondMove() && Math.abs(fromCol - toCol) == 1 && (fromRow == toRow)) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Empty());
                board[toRow - 1][toCol] = new Spot(new Pawn(true, false, false));
                return true;
            }
            if (board[fromRow][fromCol].getPiece().isFirstMove()) {
                if (fromRow - toRow == 2 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty() && board[toRow + 1][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(true, false, true));
                        return true;
                    }
                } else if (fromRow - toRow == 1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(true, false, true));
                        return true;
                    }
                }
            } else {
                if (fromRow - toRow == 1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(true, false, false));
                        return true;
                    }
                }
            }

        } else {
            if ((fromRow - toRow == -1) && (Math.abs(fromCol - toCol) == 1) && board[toRow][toCol].getPiece().isWhite()) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Pawn(false, false, false));
                return true;
            }
            if (board[toRow][toCol].getPiece().isWhite() && (board[toRow][toCol].getPiece().getClass().getName().equals("Pawn")) && board[toRow + 1][toCol].isFieldEmpty() && board[toRow][toCol].getPiece().isSecondMove() && Math.abs(fromCol - toCol) == 1 && (fromRow == toRow)) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Empty());
                board[toRow + 1][toCol] = new Spot(new Pawn(false, false, false));
                return true;
            }
            if (board[fromRow][fromCol].getPiece().isFirstMove()) {
                if (fromRow - toRow == -2 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty() && board[toRow - 1][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(false, false, true));
                        return true;
                    }
                } else if (fromRow - toRow == -1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(false, false, true));
                        return true;
                    }
                }
            } else {
                if (fromRow - toRow == -1 && fromCol == toCol) {
                    if (board[toRow][toCol].isFieldEmpty()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Pawn(false, false, false));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> possibleTakingInFlight(int row, int col, Spot[][] board, int[] previousEnemyMove, boolean isWhite) {
        int enemyFromRow = previousEnemyMove[0];
        int enemyToRow = previousEnemyMove[2];
        int enemyToCol = previousEnemyMove[3];
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (isWhite) {
            if (col > 0 && col < 7) {
                if (board[row][col + 1].getPiece().isBlack() && (board[row][col + 1].getPiece().getClass().getName().equals("Pawn")) && board[row - 1][col + 1].isFieldEmpty() && board[row][col + 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == 2) && (enemyToCol == col + 1) && (enemyToRow == row)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col + 1);
                }
                if (board[row][col - 1].getPiece().isBlack() && (board[row][col - 1].getPiece().getClass().getName().equals("Pawn")) && board[row - 1][col - 1].isFieldEmpty() && board[row][col - 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == 2) && (enemyToCol == col - 1) && (enemyToRow == row)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col - 1);
                }
            } else if (col == 0) {
                if (board[row][col + 1].getPiece().isBlack() && (board[row][col + 1].getPiece().getClass().getName().equals("Pawn")) && board[row - 1][col + 1].isFieldEmpty() && board[row][col + 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == 2) && (enemyToCol == col + 1) && (enemyToRow == row)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col + 1);
                }
            } else if (col == 7) {
                if (board[row][col - 1].getPiece().isBlack() && (board[row][col - 1].getPiece().getClass().getName().equals("Pawn")) && board[row - 1][col - 1].isFieldEmpty() && board[row][col - 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == 2) && (enemyToCol == col - 1) && (enemyToRow == row)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col - 1);
                }
            }
        } else {
                if (col > 0 && col < 7) {
                    if (board[row][col + 1].getPiece().isWhite() && (board[row][col + 1].getPiece().getClass().getName().equals("Pawn")) && board[row + 1][col + 1].isFieldEmpty() && board[row][col + 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == -2) && (enemyToCol == col + 1) && (enemyToRow == row)) {
                        possibleTakes.add(row);
                        possibleTakes.add(col + 1);
                    }
                    if (board[row][col - 1].getPiece().isWhite() && (board[row][col - 1].getPiece().getClass().getName().equals("Pawn")) && board[row + 1][col - 1].isFieldEmpty() && board[row][col - 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == -2) && (enemyToCol == col - 1) && (enemyToRow == row)) {
                        possibleTakes.add(row);
                        possibleTakes.add(col - 1);
                    }
                } else if (col == 0) {
                    if (board[row][col + 1].getPiece().isWhite() && (board[row][col + 1].getPiece().getClass().getName().equals("Pawn")) && board[row + 1][col + 1].isFieldEmpty() && board[row][col + 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == -2) && (enemyToCol == col + 1) && (enemyToRow == row)) {
                        possibleTakes.add(row);
                        possibleTakes.add(col + 1);
                    }
                } else if (col == 7) {
                    if (board[row][col - 1].getPiece().isWhite() && (board[row][col - 1].getPiece().getClass().getName().equals("Pawn")) && board[row + 1][col - 1].isFieldEmpty() && board[row][col - 1].getPiece().isSecondMove() && (enemyToRow - enemyFromRow == -2) && (enemyToCol == col - 1) && (enemyToRow == row)) {
                        possibleTakes.add(row);
                        possibleTakes.add(col - 1);
                    }
                }
        }

        return possibleTakes;
    }
public ArrayList<Integer> possiblePawnTakes(int row, int col, Spot[][] board, boolean wantPossibleTakes, boolean simulateIfMoveIsLegal) {
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
                if ((board[row - 1][ col + 1].getPiece().isBlack())) {
                    takes.add(row - 1);
                    takes.add(col + 1);
                } else if (board[row - 1][ col + 1].isFieldEmpty()) {
                    possibleTakes.add(row - 1);
                    possibleTakes.add(col + 1);
                }
            } else if (col == 0) {
                if ((board[row - 1][ col + 1].getPiece().isBlack())) {
                    takes.add(row - 1);
                    takes.add(col + 1);
                } else if (board[row - 1][ col + 1].isFieldEmpty()) {
                    possibleTakes.add(row - 1);
                    possibleTakes.add(col + 1);
                }
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
                if ((board[row + 1][ col - 1].getPiece().isWhite())) {
                    takes.add(row + 1);
                    takes.add(col - 1);
                } else if (board[row + 1][ col - 1].isFieldEmpty()) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col - 1);
                }
                if ((board[row + 1][ col + 1].getPiece().isWhite())) {
                    takes.add(row + 1);
                    takes.add(col + 1);
                }else if (board[row + 1][ col + 1].isFieldEmpty()) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col + 1);
                }
            } else if (col == 0) {
                if ((board[row + 1][ col + 1].getPiece().isWhite())) {
                    takes.add(row + 1);
                    takes.add(col + 1);
                } else if (board[row + 1][ col + 1].isFieldEmpty()) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col + 1);
                }
            } else if (col == 7) {
                if ((board[row + 1][ col - 1].getPiece().isWhite())) {
                    takes.add(row + 1);
                    takes.add(col - 1);
                } else if (board[row + 1][ col - 1].isFieldEmpty()) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col - 1);
                }
            }
        }

        if (simulateIfMoveIsLegal && ((row != 1 && board[row][col].getPiece().isWhite()) || (row != 6 && board[row][col].getPiece().isBlack()))) {
            for (int i = 0; i < takes.size(); i += 2) {
                for (int a = 0; a < 8; a++) {
                    simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
                }
                simulateBoard[row][col] = new Spot(new Empty());
                simulateBoard[takes.get(i)][takes.get(i + 1)] = new Spot(new Pawn(board[row][col].getPiece().isWhite(), false, false));
                ArrayList<Integer> hypotheticalCheck = lookForCheckMate(simulateBoard);
                if (hypotheticalCheck.size() != 0 && isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board)) {
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

    public ArrayList<Integer> possiblePawnMoves(int row, int col, Spot[][] board) {
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
        } else if (!board[row][col].getPiece().isWhite()) {
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
                    ArrayList<Integer> hypotheticalCheck = lookForCheckMate(simulateBoard);
                    if (!(hypotheticalCheck.size() != 0 && isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board))) {
                        finalPossibleMoves.add(possibleMoves.get(i));
                        finalPossibleMoves.add(possibleMoves.get(i + 1));
                    }
                }

        return finalPossibleMoves;
    }

    public boolean isTheSameColor(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        return board[fromRow][fromCol].getPiece().isWhite() == board[toRow][toCol].getPiece().isWhite() && (!board[fromRow][fromCol].isFieldEmpty()) && (!board[toRow][toCol].isFieldEmpty());
    }


    public boolean knightMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean legalKnightMove = (Math.abs(fromCol - toCol) == 2 && Math.abs(fromRow - toRow) == 1) || (Math.abs(fromCol - toCol) == 1 && Math.abs(fromRow - toRow) == 2);
        if ((!isTheSameColor(fromRow, fromCol, toRow, toCol, board)) && (!board[fromRow][fromCol].isFieldEmpty()) && board[fromRow][fromCol].getPiece().isWhite()) {
            if (legalKnightMove) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Knight(true, false));
                return true;
            }
        } else if ((!isTheSameColor(fromRow, fromCol, toRow, toCol, board)) && (!board[fromRow][fromCol].isFieldEmpty()) && !board[fromRow][fromCol].getPiece().isWhite()) {
            if (legalKnightMove) {
                board[fromRow][fromCol] = new Spot(new Empty());
                board[toRow][toCol] = new Spot(new Knight(false, false));
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> possibleKnightMoves (int row, int col, Spot[][] board, boolean simulateIfMoveIsLegal) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        if (row > 1 && row < 6) {
            if (col > 1 && col < 6) {
                checkPossibleKnightMoves(row, col, board, possibleMoves);
                if (!isTheSameColor(row, col, row - 1, col - 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 0) {
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 7) {
                if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
                checkAnotherPossibleKnightMoves(row, col, board, possibleMoves);
            } else if (col == 1) {
                if (!isTheSameColor(row, col, row - 2, 0, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(0);
                }
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, 0, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(0);
                }
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 6) {
                checkPossibleKnightMoves(row, col, board, possibleMoves);
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row - 1, col - 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 2);
                }
            }
        } else if (row == 0) {
            if (col > 1 && col < 6) {
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
            } else if (col == 0) {
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
            } else if (col == 7) {
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
            } else if (col == 1) {
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, 0, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
            }
        } else if (row == 1) {
            if (col > 1 && col < 6) {
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, 0, col - 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, 0, col + 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
            } else if (col == 0) {
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, 0, col + 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 7) {
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
                if (!isTheSameColor(row, col, 0, col - 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col - 2);
                }
            } else if (col == 1) {
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, 0, col + 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, 0, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, 0, col - 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col - 2);
                }
            }
        } else if (row == 7) {
            if (col > 1 && col < 6) {
                checkAnotherAnotherPossibleKnightMoves(row, col, board, possibleMoves);
            } else if (col == 0) {
                if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
            } else if (col == 7) {
                if (!isTheSameColor(row, col, row - 1, col - 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row - 2, col - 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col - 1);
                }
            } else if (col == 1) {
                if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row - 2, 0, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row - 2, col - 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col - 1);
                }
                if (!isTheSameColor(row, col, row - 1, col - 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 2);
                }
            }
        } else if (row == 6) {
            if (col > 1 && col < 6) {
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                checkAnotherAnotherPossibleKnightMoves(row, col, board, possibleMoves);
            } else if (col == 0) {
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 7) {
                if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!isTheSameColor(row, col, row - 2, col - 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col - 1);
                }
                if (!isTheSameColor(row, col, row - 1, col - 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 2);
                }
            } else if (col == 1) {
                if (!isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!isTheSameColor(row, col, row - 2, 0, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                checkAnotherPossibleKnightMoves(row, col, board, possibleMoves);
            }
        }
        if (simulateIfMoveIsLegal) {
            Spot[][] simulateBoard = new Spot[8][];
            ArrayList<Integer> finalPossibleMoves = new ArrayList<>();
            for (int i = 0; i < possibleMoves.size(); i += 2) {
                for (int a = 0; a < 8; a++) {
                    simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
                }
                simulateBoard[row][col] = new Spot(new Empty());
                simulateBoard[possibleMoves.get(i)][possibleMoves.get(i + 1)] = new Spot(new Knight(board[row][col].getPiece().isWhite(), false));
                ArrayList<Integer> hypotheticalCheck = lookForCheckMate(simulateBoard);
                if (!(hypotheticalCheck.size() != 0 && isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board))) {
                    finalPossibleMoves.add(possibleMoves.get(i));
                    finalPossibleMoves.add(possibleMoves.get(i + 1));
                }
            }
            return finalPossibleMoves;
        } else {
            return possibleMoves;
        }


    }

    public void checkAnotherAnotherPossibleKnightMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!isTheSameColor(row, col, row - 1, col - 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col - 2);
        }
        if (!isTheSameColor(row, col, row - 1, col + 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col + 2);
        }
        if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col + 1);
        }
        if (!isTheSameColor(row, col, row - 2, col - 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col - 1);
        }
    }

    public void checkAnotherPossibleKnightMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!isTheSameColor(row, col, row - 2, col - 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col - 1);
        }
        if (!isTheSameColor(row, col, row + 1, col - 2, board)) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col - 2);
        }
        if (!isTheSameColor(row, col, row - 1, col - 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col - 2);
        }
    }

    public void checkPossibleKnightMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!isTheSameColor(row, col, row - 2, col - 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col - 1);
        }
        if (!isTheSameColor(row, col, row - 2, col + 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col + 1);
        }
        if (!isTheSameColor(row, col, row + 2, col + 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col + 1);
        }
        if (!isTheSameColor(row, col, row + 2, col - 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col - 1);
        }
    }

    public ArrayList<Integer> possibleKnightTakes(int fromRow,int fromCol, ArrayList<Integer> possibleMoves, Spot[][] board) {
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        for (int i = 0; i < possibleMoves.size(); i += 2) {
            int toRow = possibleMoves.get(i);
            int toCol = possibleMoves.get(i + 1);
            if (board[fromRow][fromCol].getPiece().isWhite() && board[toRow][toCol].getPiece().isBlack()) {
                possibleTakes.add(toRow);
                possibleTakes.add(toCol);
            } else if (board[fromRow][fromCol].getPiece().isBlack() && board[toRow][toCol].getPiece().isWhite()) {
                possibleTakes.add(toRow);
                possibleTakes.add(toCol);
            }
        }
        return possibleTakes;
    }

    public ArrayList<Integer> possibleRookMoves(int row, int col, Spot[][] board, int request) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (col == 0) { //lewa strona
            if (row == 0) { //lewy górny róg
                for (int i = 1; i < 8; i++) {
                    if (board[i][0].isFieldEmpty()) { // dół
                        possibleMoves.add(i);
                        possibleMoves.add(0);
                    } else if (isTheSameColor(row, col, i, col, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, col, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(col);
                        break;
                    }
                }
                for (int i = 1; i < 8; i++) { //prawa
                    if (board[0][i].isFieldEmpty()) {
                        possibleMoves.add(0);
                        possibleMoves.add(i);
                    } else if (isTheSameColor(row, col, row, i, board)) {
                        break;
                    }else if (!isTheSameColor(row, col, row, i, board)) {
                        possibleTakes.add(row);
                        possibleTakes.add(i);
                        break;
                    }
                }
            } else if (row == 7) { //lewy dolny róg
                for (int i = 6; i >= 0; i--) {
                    if (board[i][0].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(0);
                    } else if (isTheSameColor(row, col, i, col, board)) {
                        break;
                    }else if (!isTheSameColor(row, col, i, col, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(col);
                        break;
                    }
                }
                for (int i = 1; i < 8; i++) {
                    if (board[7][i].isFieldEmpty()) {
                        possibleMoves.add(7);
                        possibleMoves.add(i);
                    } else if (isTheSameColor(row, col, row, i, board)) {
                        break;
                    }else if (!isTheSameColor(row, col, row, i, board)) {
                        possibleTakes.add(row);
                        possibleTakes.add(i);
                        break;
                    }
                }
            } else {
                checkPossibleRookMoves(row, col, board, possibleMoves, possibleTakes);
                for (int i = col + 1; i < 8; i++) { // prawa
                    if (board[row][i].isFieldEmpty()) {
                        possibleMoves.add(row);
                        possibleMoves.add(i);
                    } else if (isTheSameColor(row, col, row, i, board)) {
                        break;
                    }else if (!isTheSameColor(row, col, row, i, board)) {
                        possibleTakes.add(row);
                        possibleTakes.add(i);
                        break;
                    }
                }
            }
        } else if (col == 7) { // prawa strona
            if (row == 0) { //prawy górny róg
                for (int i = 1; i < 8; i++) {
                    if (board[i][7].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(7);
                    }else if (isTheSameColor(row, col, i, col, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, col, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(col);
                        break;
                    }
                }
                for (int i = 6; i >= 0; i--) {
                    if (board[0][i].isFieldEmpty()) {
                        possibleMoves.add(0);
                        possibleMoves.add(i);
                    } else if (isTheSameColor(row, col, row, i, board)) {
                        break;
                    }else if (!isTheSameColor(row, col, row, i, board)) {
                        possibleTakes.add(row);
                        possibleTakes.add(i);
                        break;
                    }
                }
            } else if (row == 7) { //prawy dolny róg
                for (int i = 6; i >= 0; i--) {
                    if (board[i][7].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(7);
                    }else if (isTheSameColor(row, col, i, col, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, col, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(col);
                        break;
                    }
                }
                for (int i = 6; i >= 0; i--) {
                    if (board[7][i].isFieldEmpty()) {
                        possibleMoves.add(7);
                        possibleMoves.add(i);
                    } else if (isTheSameColor(row, col, row, i, board)) {
                        break;
                    }else if (!isTheSameColor(row, col, row, i, board)) {
                        possibleTakes.add(row);
                        possibleTakes.add(i);
                        break;
                    }
                }
            } else {
                checkPossibleRookMoves(row, col, board, possibleMoves, possibleTakes);
                for (int i = col - 1; i >= 0; i--) { // lewa
                    if (board[row][i].isFieldEmpty()) {
                        possibleMoves.add(row);
                        possibleMoves.add(i);
                    } else if (isTheSameColor(row, col, row, i, board)) {
                        break;
                    }else if (!isTheSameColor(row, col, row, i, board)) {
                        possibleTakes.add(row);
                        possibleTakes.add(i);
                        break;
                    }
                }
            }
        } else {
            checkPossibleRookMoves(row, col, board, possibleMoves, possibleTakes);
            for (int i = col - 1; i >= 0; i--) { // lewa
                if (board[row][i].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(i);
                } else if (isTheSameColor(row, col, row, i, board)) {
                    break;
                }else if (!isTheSameColor(row, col, row, i, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(i);
                    break;
                }
            }
            for (int i = col + 1; i < 8; i++) { // prawa
                if (board[row][i].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(i);
                } else if (isTheSameColor(row, col, row, i, board)) {
                    break;
                }else if (!isTheSameColor(row, col, row, i, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(i);
                    break;
                }
            }
        }

        Spot[][] simulateBoard = new Spot[8][];
        switch (request) {
            case WANT_SIMULATED_MOVES:
                ArrayList<Integer> simulatedPossibleMoves = new ArrayList<>();
            for (int i = 0; i < possibleMoves.size(); i += 2) {
                for (int a = 0; a < 8; a++) {
                    simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
                }
                simulateBoard[row][col] = new Spot(new Empty());
                simulateBoard[possibleMoves.get(i)][possibleMoves.get(i + 1)] = new Spot(new Rook(board[row][col].getPiece().isWhite(), false));
                ArrayList<Integer> hypotheticalCheck = lookForCheckMate(simulateBoard);
                if (!(hypotheticalCheck.size() != 0 && isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board))) {
                    simulatedPossibleMoves.add(possibleMoves.get(i));
                    simulatedPossibleMoves.add(possibleMoves.get(i + 1));
                }
            }
                return simulatedPossibleMoves;

            case WANT_TAKES:
                return possibleTakes;
            case WANT_SIMULATED_TAKES:
                ArrayList<Integer> simulatedPossibleTakes = new ArrayList<>();
                for (int i = 0; i < possibleTakes.size(); i += 2) {
                    for (int a = 0; a < 8; a++) {
                        simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
                    }
                    simulateBoard[row][col] = new Spot(new Empty());
                    simulateBoard[possibleTakes.get(i)][possibleTakes.get(i + 1)] = new Spot(new Rook(board[row][col].getPiece().isWhite(), false));
                    ArrayList<Integer> hypotheticalCheck = lookForCheckMate(simulateBoard);
                    if (!(hypotheticalCheck.size() != 0 && isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board))) {
                        simulatedPossibleTakes.add(possibleTakes.get(i));
                        simulatedPossibleTakes.add(possibleTakes.get(i + 1));
                    }
                }
                return simulatedPossibleTakes;
            default:
                return possibleMoves;
        }
    }

    public void checkPossibleRookMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        for (int i = row + 1; i < 8; i++) { // dół
            if (board[i][col].isFieldEmpty()) {
                possibleMoves.add(i);
                possibleMoves.add(col);
            } else if (isTheSameColor(row, col, i, col, board)) {
                break;
            }else if (!isTheSameColor(row, col, i, col, board)) {
                possibleTakes.add(i);
                possibleTakes.add(col);
                break;
            }
        }
        for (int i = row - 1; i >= 0; i--) { // góra
            if (board[i][col].isFieldEmpty()) {
                possibleMoves.add(i);
                possibleMoves.add(col);
            }else if (isTheSameColor(row, col, i, col, board)) {
                break;
            } else if (!isTheSameColor(row, col, i, col, board)) {
                possibleTakes.add(i);
                possibleTakes.add(col);
                break;
            }
        }
    }


    public boolean rookMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean isMoveLegal = false;
        if ((!isTheSameColor(fromRow, fromCol, toRow, toCol, board))) {
            if (fromCol == toCol && fromRow > toRow) {
                if (Math.abs(fromRow - toRow) == 1) {
                    isMoveLegal = !isTheSameColor(fromRow, fromCol, toRow, toCol, board);
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
                return performRookMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
            } else if (fromCol == toCol && fromRow < toRow) {
                if (Math.abs(fromRow - toRow) == 1) {
                    isMoveLegal = !isTheSameColor(fromRow, fromCol, toRow, toCol, board);
                } else if (Math.abs(fromRow - toRow) != 1) {
                    for (int i = fromRow + 1; i < toRow; i++) {
                        if (!board[i][fromCol].isFieldEmpty()) {
                            isMoveLegal = false;
                            break;
                        } else {
                            isMoveLegal = true;
                        }
                    }
                }
                return performRookMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
            } else if (fromCol > toCol && fromRow == toRow) {
                if (Math.abs(fromCol - toCol) == 1) {
                    isMoveLegal = !isTheSameColor(fromRow, fromCol, toRow, toCol, board);
                } else if (Math.abs(fromCol - toCol) != 1) {
                    for (int i = fromCol - 1; i > toCol; i--) {
                        if (!board[fromRow][i].isFieldEmpty() || isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
                            isMoveLegal = false;
                            break;
                        } else {
                            isMoveLegal = true;
                        }
                    }
                }
                return performRookMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
            } else if (fromCol < toCol && fromRow == toRow) {
                if (Math.abs(fromCol - toCol) == 1) {
                    isMoveLegal = !isTheSameColor(fromRow, fromCol, toRow, toCol, board);
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
                return performRookMove(fromRow, fromCol, toRow, toCol, board, isMoveLegal);
            }
        }
        return false;
    }

    public boolean performRookMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board, boolean isMoveLegal) {
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

    public ArrayList<Integer> possibleBishopMoves(int row, int col, Spot[][] board, int request) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (row == 0) {
            if (col == 0) { //lewy górny róg
                int i = row + 1;
                int j = col + 1;
                for (int t = 0; t < 7; t++) {
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    }else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
                    i++;
                    j++;
                }
            } else if (col == 7) {
                int i = row + 1;
                int j = col - 1;
                for (int t = 0; t < 7; t++) {
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    }else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
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
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    } else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
                    i++;
                    j--;
                }
                for (int t = col; t < 7; t++) {
                    if (board[k][l].isFieldEmpty()) {
                        possibleMoves.add(k);
                        possibleMoves.add(l);
                    } else if (isTheSameColor(row, col, k, l, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, k, l, board)) {
                        possibleTakes.add(k);
                        possibleTakes.add(l);
                        break;
                    }
                    k++;
                    l++;
                }
            }
        } else if (row == 7) {
            if (col == 0) {
                int i = row - 1;
                int j = col + 1;
                for (int t = 0; t < 7; t++) {
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    }else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
                    i--;
                    j++;
                }
            } else if (col == 7) {
                int i = row - 1;
                int j = col - 1;
                for (int t = 0; t < 7; t++) {
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    }else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
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
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    } else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
                    i--;
                    j--;
                }
                    for (int t = col; t < 7; t++) {
                        if (board[k][l].isFieldEmpty()) {
                            possibleMoves.add(k);
                            possibleMoves.add(l);
                        } else if (isTheSameColor(row, col, k, l, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, k, l, board)) {
                            possibleTakes.add(k);
                            possibleTakes.add(l);
                            break;
                        }
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
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    } else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
                    i--;
                    j++;
                }
                for (int t = row; t < 7; t++) {
                    if (board[k][l].isFieldEmpty()) {
                        possibleMoves.add(k);
                        possibleMoves.add(l);
                    } else if (isTheSameColor(row, col, k, l, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, k, l, board)) {
                        possibleTakes.add(k);
                        possibleTakes.add(l);
                        break;
                    }
                    k++;
                    l++;
                }
            } else if(col == 7) {
                // lewy górny skos
                int i = row - 1;
                int j = col - 1;
                // lewy dolny skos
                int k = row + 1;
                int l = col - 1;
                for (int t = row; t > 0; t--) {
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    } else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
                    i--;
                    j--;
                }
                for (int t = row; t < 7; t++) {
                    if (board[k][l].isFieldEmpty()) {
                        possibleMoves.add(k);
                        possibleMoves.add(l);
                    } else if (isTheSameColor(row, col, k, l, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, k, l, board)) {
                        possibleTakes.add(k);
                        possibleTakes.add(l);
                        break;
                    }
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
                    if (board[i][j].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(j);
                    } else if (isTheSameColor(row, col, i, j, board)) {
                        break;
                    } else if (!isTheSameColor(row, col, i, j, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(j);
                        break;
                    }
                    i--;
                    j--;
                }


                if (((col == 1 && row != 5) || col == 2 || col == 3 || (col == 4 && row != 4)|| (col == 5 && row != 3 && row != 4 && row != 5 && row != 6) || (col == 6 && row != 2 && row != 3 && row != 4)) && row <= 4) {
                    //działa
                    for (int t = col; t > 0; t--) { // lewy dolny skos
                        if (board[k][l].isFieldEmpty()) {
                            possibleMoves.add(k);
                            possibleMoves.add(l);
                        } else if (isTheSameColor(row, col, k, l, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, k, l, board)) {
                            possibleTakes.add(k);
                            possibleTakes.add(l);
                            break;
                        }
                        k++;
                        l--;
                    }
                } else if ((row == 6 && col == 5) || (col == 1 && row == 5)) { //działa
                    for (int t = 1; t > 0; t--) { // lewy dolny skos
                        if (board[k][l].isFieldEmpty()) {
                            possibleMoves.add(k);
                            possibleMoves.add(l);
                        } else if (isTheSameColor(row, col, k, l, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, k, l, board)) {
                            possibleTakes.add(k);
                            possibleTakes.add(l);
                            break;
                        }
                        k++;
                        l--;
                    }
                } else if((col == 5 && row == 4) || (col == 4 && row == 4) || (col == 6 && row == 4)) { //działa
                    for (int t = 3; t > 0; t--) { // lewy dolny skos
                        if (board[k][l].isFieldEmpty()) {
                            possibleMoves.add(k);
                            possibleMoves.add(l);
                        } else if (isTheSameColor(row, col, k, l, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, k, l, board)) {
                            possibleTakes.add(k);
                            possibleTakes.add(l);
                            break;
                        }
                        k++;
                        l--;
                    }
                }else if(col == 5 && row == 5) { //działa
                    for (int t = 2; t > 0; t--) { // lewy dolny skos
                        if (board[k][l].isFieldEmpty()) {
                            possibleMoves.add(k);
                            possibleMoves.add(l);
                        } else if (isTheSameColor(row, col, k, l, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, k, l, board)) {
                            possibleTakes.add(k);
                            possibleTakes.add(l);
                            break;
                        }
                        k++;
                        l--;
                    }
                } else if (col == 5 || (col == 6 && row == 3)) { //działa
                    for (int t = 4; t > 0; t--) { // lewy dolny skos
                        if (board[k][l].isFieldEmpty()) {
                            possibleMoves.add(k);
                            possibleMoves.add(l);
                        } else if (isTheSameColor(row, col, k, l, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, k, l, board)) {
                            possibleTakes.add(k);
                            possibleTakes.add(l);
                            break;
                        }
                        k++;
                        l--;
                    }
                } else if (row == 2 && col == 6) { //działa
                    for (int t = 5; t > 0; t--) { // lewy dolny skos
                        if (board[k][l].isFieldEmpty()) {
                            possibleMoves.add(k);
                            possibleMoves.add(l);
                        } else if (isTheSameColor(row, col, k, l, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, k, l, board)) {
                            possibleTakes.add(k);
                            possibleTakes.add(l);
                            break;
                        }
                        k++;
                        l--;
                    }
                }else if (row > 4) { //działa
                        for (int t = 7 - row; t > 0; t--) { // lewy dolny skos
                            if (board[k][l].isFieldEmpty()) {
                                possibleMoves.add(k);
                                possibleMoves.add(l);
                            } else if (isTheSameColor(row, col, k, l, board)) {
                                break;
                            } else if (!isTheSameColor(row, col, k, l, board)) {
                                possibleTakes.add(k);
                                possibleTakes.add(l);
                                break;
                            }
                            k++;
                            l--;
                        }

                    }


                if (((col == 6 && row != 2)|| col == 5 || col == 4 || (col == 3 && row != 3) || (col == 2 && row != 4 && row != 3) || (col == 1 && row != 5 && row != 4 && row != 3)) && row >= 3) {
                    //działa
                    for (int t = 7 - col; t > 0; t--) { // prawy górny skos
                        if (board[m][n].isFieldEmpty()) {
                            possibleMoves.add(m);
                            possibleMoves.add(n);
                        } else if (isTheSameColor(row, col, m, n, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, m, n, board)) {
                            possibleTakes.add(m);
                            possibleTakes.add(n);
                            break;
                        }
                        m--;
                        n++;
                    }
                } else if (col == 6 && row == 2) { //działa
                    for (int t = 6; t < 7; t++) { // prawy górny skos
                        if (board[m][n].isFieldEmpty()) {
                            possibleMoves.add(m);
                            possibleMoves.add(n);
                        } else if (isTheSameColor(row, col, m, n, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, m, n, board)) {
                            possibleTakes.add(m);
                            possibleTakes.add(n);
                            break;
                        }
                        m--;
                        n++;
                    }
                } else if ((col == 3 && row == 3) || (col == 2 && row == 3) || (col == 1 && row == 3)) { //działa
                    for (int t = 4; t < 7; t++) { // prawy górny skos
                        if (board[m][n].isFieldEmpty()) {
                            possibleMoves.add(m);
                            possibleMoves.add(n);
                        } else if (isTheSameColor(row, col, m, n, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, m, n, board)) {
                            possibleTakes.add(m);
                            possibleTakes.add(n);
                            break;
                        }
                        m--;
                        n++;
                    }
                }
                else if ((col == 2 && row == 4) || (col == 1 && row == 4)) { // działa
                    for (int t = 3; t < 7; t++) { // prawy górny skos
                        if (board[m][n].isFieldEmpty()) {
                            possibleMoves.add(m);
                            possibleMoves.add(n);
                        } else if (isTheSameColor(row, col, m, n, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, m, n, board)) {
                            possibleTakes.add(m);
                            possibleTakes.add(n);
                            break;
                        }
                        m--;
                        n++;
                    }
                } else if ((col == 1 && row == 5)) { // działa
                    for (int t = 2; t < 7; t++) { // prawy górny skos
                        if (board[m][n].isFieldEmpty()) {
                            possibleMoves.add(m);
                            possibleMoves.add(n);
                        } else if (isTheSameColor(row, col, m, n, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, m, n, board)) {
                            possibleTakes.add(m);
                            possibleTakes.add(n);
                            break;
                        }
                        m--;
                        n++;
                    }
                } else if (row < 3) { //działa
                        for (int t = 7 - row; t < 7; t++) { // prawy górny skos
                            if (board[m][n].isFieldEmpty()) {
                                possibleMoves.add(m);
                                possibleMoves.add(n);
                            } else if (isTheSameColor(row, col, m, n, board)) {
                                break;
                            } else if (!isTheSameColor(row, col, m, n, board)) {
                                possibleTakes.add(m);
                                possibleTakes.add(n);
                                break;
                            }
                            m--;
                            n++;
                        }
                    }
                if (row == 6 || (row == 5 && col == 6) || (row == 4 && col == 6) || (row == 3 && col == 6) || (row == 2 && col == 6) || (row == 1 && col == 6)) {
                    for (int t = 1; t > 0; t--) {
                        if (board[o][p].isFieldEmpty()) {
                            possibleMoves.add(o);
                            possibleMoves.add(p);
                        } else if (isTheSameColor(row, col, o, p, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, o, p, board)) {
                            possibleTakes.add(o);
                            possibleTakes.add(p);
                            break;
                        }
                        o++;
                        p++;
                    }
                } else if (row == 4 && col == 5 || (row == 3 && col == 5) || (row == 2 && col == 5) || (row == 1 && col == 5)) {
                    for (int t = 2; t > 0; t--) {
                        if (board[o][p].isFieldEmpty()) {
                            possibleMoves.add(o);
                            possibleMoves.add(p);
                        } else if (isTheSameColor(row, col, o, p, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, o, p, board)) {
                            possibleTakes.add(o);
                            possibleTakes.add(p);
                            break;
                        }
                        o++;
                        p++;
                    }
                } else if ((row == 3 && col == 4) || (row == 2 && col == 4) || (row == 1 && col == 4)) {
                    for (int t = 3; t > 0; t--) {
                        if (board[o][p].isFieldEmpty()) {
                            possibleMoves.add(o);
                            possibleMoves.add(p);
                        } else if (isTheSameColor(row, col, o, p, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, o, p, board)) {
                            possibleTakes.add(o);
                            possibleTakes.add(p);
                            break;
                        }
                        o++;
                        p++;
                    }
                } else if ((row == 2 && col == 3) || (row == 1 && col == 3)) {
                    for (int t = 4; t > 0; t--) {
                        if (board[o][p].isFieldEmpty()) {
                            possibleMoves.add(o);
                            possibleMoves.add(p);
                        } else if (isTheSameColor(row, col, o, p, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, o, p, board)) {
                            possibleTakes.add(o);
                            possibleTakes.add(p);
                            break;
                        }
                        o++;
                        p++;
                    }
                } else if ((row == 1 && col == 2)) {
                    for (int t = 5; t > 0; t--) {
                        if (board[o][p].isFieldEmpty()) {
                            possibleMoves.add(o);
                            possibleMoves.add(p);
                        } else if (isTheSameColor(row, col, o, p, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, o, p, board)) {
                            possibleTakes.add(o);
                            possibleTakes.add(p);
                            break;
                        }
                        o++;
                        p++;
                    }
                } else if (row == 5 || row == 4 || row == 3 || row == 2 || row == 1) {
                    for (int t = row; t < 7; t++) {
                        if (board[o][p].isFieldEmpty()) {
                            possibleMoves.add(o);
                            possibleMoves.add(p);
                        } else if (isTheSameColor(row, col, o, p, board)) {
                            break;
                        } else if (!isTheSameColor(row, col, o, p, board)) {
                            possibleTakes.add(o);
                            possibleTakes.add(p);
                            break;
                        }
                        o++;
                        p++;
                    }
                }


            }
        }

        Spot[][] simulateBoard = new Spot[8][];
        switch (request) {
            case WANT_SIMULATED_MOVES:
                ArrayList<Integer> simulatedPossibleMoves = new ArrayList<>();
                for (int i = 0; i < possibleMoves.size(); i += 2) {
                    for (int a = 0; a < 8; a++) {
                        simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
                    }
                    simulateBoard[row][col] = new Spot(new Empty());
                    simulateBoard[possibleMoves.get(i)][possibleMoves.get(i + 1)] = new Spot(new Bishop(board[row][col].getPiece().isWhite(), false));
                    ArrayList<Integer> hypotheticalCheck = lookForCheckMate(simulateBoard);
                    if (!(hypotheticalCheck.size() != 0 && isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board))) {
                        simulatedPossibleMoves.add(possibleMoves.get(i));
                        simulatedPossibleMoves.add(possibleMoves.get(i + 1));
                    }
                }
                return simulatedPossibleMoves;

            case WANT_TAKES:
                return possibleTakes;
            case WANT_SIMULATED_TAKES:
                ArrayList<Integer> simulatedPossibleTakes = new ArrayList<>();
                for (int i = 0; i < possibleTakes.size(); i += 2) {
                    for (int a = 0; a < 8; a++) {
                        simulateBoard[a] = Arrays.copyOf(board[a], board[a].length);
                    }
                    simulateBoard[row][col] = new Spot(new Empty());
                    simulateBoard[possibleTakes.get(i)][possibleTakes.get(i + 1)] = new Spot(new Bishop(board[row][col].getPiece().isWhite(), false));
                    ArrayList<Integer> hypotheticalCheck = lookForCheckMate(simulateBoard);
                    if (!(hypotheticalCheck.size() != 0 && isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board))) {
                        simulatedPossibleTakes.add(possibleTakes.get(i));
                        simulatedPossibleTakes.add(possibleTakes.get(i + 1));
                    }
                }
                return simulatedPossibleTakes;
            default:
                return possibleMoves;
        }
    }

    public boolean bishopMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean isMoveLegal = false;
        if (!isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
                if (Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1) {
                    return performBishopMove(fromRow, fromCol, toRow, toCol, board, true);
                } else {
                    if (isOnTheSameDiagonal(fromRow, fromCol, toRow, toCol)) {
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

    public boolean performBishopMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board, boolean isMoveLegal) {
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
    public boolean queenMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        boolean isMoveLegal = false;
        if (!isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
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
                                for (int i = fromCol - 1; i > toCol; i--) {
                                    if (!board[fromRow][i].isFieldEmpty() || isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
                                        isMoveLegal = false;
                                        break;
                                    } else {
                                        isMoveLegal = true;
                                    }
                                }
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
                    if (isOnTheSameDiagonal(fromRow, fromCol, toRow, toCol)) {
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
    public boolean performQueenMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board, boolean isMoveLegal) {
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
    public boolean isItVHMove(int fromRow, int fromCol, int toRow, int toCol) {
        return ((fromRow == toRow || fromCol == toCol));
    }
    public boolean isOnTheSameDiagonal(int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow < toRow && fromCol < toCol) { // prawy dolny róg
            for (int i = 1; i < 8; i++) {
                if ((fromRow + i == toRow) && (fromCol + i == toCol)) {
                    return true;
                }
            }
        } else if (fromRow > toRow && fromCol < toCol) { // prawy górny róg
            for (int i = 1; i < 8; i++) {
                if ((fromRow - i == toRow) && (fromCol + i == toCol)) {
                    return true;
                }
            }
        } else if (fromRow < toRow && fromCol > toCol) { // lewy dolny róg
            for (int i = 1; i < 8; i++) {
                if ((fromRow + i == toRow) && (fromCol - i == toCol)) {
                    return true;
                }
            }
        } else if (fromRow > toRow && fromCol > toCol) { // lewy górny róg
            for (int i = 1; i < 8; i++) {
                if ((fromRow - i == toRow) && (fromCol - i == toCol)) {
                    return true;
                }
            }
        }
            return false;
    }

    public ArrayList<Integer> possibleKingMoves(int row, int col, Spot[][] board, boolean wantMove) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        ArrayList<Integer> possibleTakes = new ArrayList<>();
        if (row == 0) {
            if (board[row][col].getPiece().isFirstMove() && board[row][0].getPiece().isFirstMove() && board[row][1].isFieldEmpty() && board[row][2].isFieldEmpty() && board[row][3].isFieldEmpty() && isTheSameColor(row, col, row, 0, board)) {
                possibleMoves.add(row);
                possibleMoves.add(0);
            }
            if (board[row][col].getPiece().isFirstMove() && board[row][7].getPiece().isFirstMove() && board[row][5].isFieldEmpty() && board[row][5].isFieldEmpty() && isTheSameColor(row, col, row, 7, board)) {
                possibleMoves.add(row);
                possibleMoves.add(7);
            }
            if (col == 0) {
                for (int i = 0; i < 2; i++) {
                    if (board[i][1].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(1);
                    } else if (!isTheSameColor(0, 0, i, 1, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(1);
                    }
                }
                if (board[1][0].isFieldEmpty()) {
                    possibleMoves.add(1);
                    possibleMoves.add(0);
                } else if (!isTheSameColor(0, 0, 1, 0, board)) {
                    possibleTakes.add(1);
                    possibleTakes.add(0);
                }
            } else if (col == 7) {
                for (int i = 0; i < 2; i++) {
                    if (board[i][6].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(6);
                    } else if (!isTheSameColor(0, 7, i, 6, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(6);
                    }
                }
                if (board[1][7].isFieldEmpty()) {
                    possibleMoves.add(1);
                    possibleMoves.add(7);
                } else if (!isTheSameColor(0, 7, 1, 7, board)) {
                    possibleTakes.add(1);
                    possibleTakes.add(7);
                }
            } else {
                if (board[row][col - 1].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(col - 1);
                } else if (!isTheSameColor(0, 7, row, col - 1, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col - 1);
                }
                if (board[row][col + 1].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(col + 1);
                } else if (!isTheSameColor(0, 7, row, col + 1, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col + 1);
                }
                if (board[row + 1][col - 1].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 1);
                } else if (!isTheSameColor(0, 7, row + 1, col - 1, board)) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col - 1);
                }
                if (board[row + 1][col].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col);
                } else if (!isTheSameColor(0, 7, row + 1, col, board)) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col);
                }
                if (board[row + 1][col + 1].isFieldEmpty()) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 1);
                } else if (!isTheSameColor(0, 7, row + 1, col + 1, board)) {
                    possibleTakes.add(row + 1);
                    possibleTakes.add(col + 1);
                }
            }
        } else if (row == 7) {
            if (board[row][col].getPiece().isFirstMove() && board[row][0].getPiece().isFirstMove() && board[row][1].isFieldEmpty() && board[row][2].isFieldEmpty() && board[row][3].isFieldEmpty() && isTheSameColor(row, col, row, 0, board)) {
                possibleMoves.add(row);
                possibleMoves.add(0);
            }
            if (board[row][col].getPiece().isFirstMove() && board[row][7].getPiece().isFirstMove() && board[row][5].isFieldEmpty() && board[row][5].isFieldEmpty() && isTheSameColor(row, col, row, 7, board)) {
                possibleMoves.add(row);
                possibleMoves.add(7);
            }
            if (col == 0) {
                for (int i = 7; i > 5; i--) {
                    if (board[i][1].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(1);
                    } else if (!isTheSameColor(row, col, i, 1, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(1);
                    }
                }
                if (board[6][0].isFieldEmpty()) {
                    possibleMoves.add(6);
                    possibleMoves.add(0);
                } else if (!isTheSameColor(row, col, 6, 0, board)) {
                    possibleTakes.add(6);
                    possibleTakes.add(0);
                }
            } else if (col == 7) {
                for (int i = 7; i > 5; i--) {
                    if (board[i][6].isFieldEmpty()) {
                        possibleMoves.add(i);
                        possibleMoves.add(6);
                    } else if (!isTheSameColor(row, col, i, 6, board)) {
                        possibleTakes.add(i);
                        possibleTakes.add(6);
                    }
                }
                if (board[6][7].isFieldEmpty()) {
                    possibleMoves.add(6);
                    possibleMoves.add(7);
                } else if (!isTheSameColor(row, col, 6, 7, board)) {
                    possibleTakes.add(6);
                    possibleTakes.add(7);
                }
            } else {
                if (board[row][col - 1].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(col - 1);
                } else if (!isTheSameColor(row, col, row, col - 1, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col - 1);
                }
                if (board[row][col + 1].isFieldEmpty()) {
                    possibleMoves.add(row);
                    possibleMoves.add(col + 1);
                } else if (!isTheSameColor(row, col, row, col + 1, board)) {
                    possibleTakes.add(row);
                    possibleTakes.add(col + 1);
                }
                if (board[row - 1][col - 1].isFieldEmpty()) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 1);
                } else if (!isTheSameColor(row, col, row - 1, col - 1, board)) {
                    possibleTakes.add(row - 1);
                    possibleTakes.add(col - 1);
                }
                if (board[row - 1][col].isFieldEmpty()) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col);
                } else if (!isTheSameColor(row, col, row - 1, col, board)) {
                    possibleTakes.add(row - 1);
                    possibleTakes.add(col);
                }
                if (board[row - 1][col + 1].isFieldEmpty()) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 1);
                } else if (!isTheSameColor(row, col, row - 1, col + 1, board)) {
                    possibleTakes.add(row - 1);
                    possibleTakes.add(col + 1);
                }
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
        System.out.println(possibleMoves);
        if (wantMove) {
            return  possibleMoves;
        } else {
            return possibleTakes;
        }
    }

    public void checkForLeftSideKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row - 1][col - 1].isFieldEmpty()) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col - 1);
        } else if (!isTheSameColor(row, col, row - 1, col - 1, board)) {
            possibleTakes.add(row - 1);
            possibleTakes.add(col - 1);
        }
        if (board[row][col - 1].isFieldEmpty()) {
            possibleMoves.add(row);
            possibleMoves.add(col - 1);
        } else if (!isTheSameColor(row, col, row, col - 1, board)) {
            possibleTakes.add(row);
            possibleTakes.add(col - 1);
        }
        if (board[row + 1][col - 1].isFieldEmpty()) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col - 1);
        } else if (!isTheSameColor(row, col, row + 1, col - 1, board)) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col - 1);
        }
    }

    public void checkForRightSideKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row - 1][col + 1].isFieldEmpty()) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col + 1);
        } else if (!isTheSameColor(row, col, row - 1, col + 1, board)) {
            possibleTakes.add(row - 1);
            possibleTakes.add(col + 1);
        }
        if (board[row][col + 1].isFieldEmpty()) {
            possibleMoves.add(row);
            possibleMoves.add(col + 1);
        } else if (!isTheSameColor(row, col, row, col + 1, board)) {
            possibleTakes.add(row);
            possibleTakes.add(col + 1);
        }
        if (board[row + 1][col + 1].isFieldEmpty()) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col + 1);
        } else if (!isTheSameColor(row, col, row + 1, col + 1, board)) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col + 1);
        }
    }

    public void checkForVerticalKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, ArrayList<Integer> possibleTakes) {
        if (board[row - 1][col].isFieldEmpty()) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col );
        } else if (!isTheSameColor(row, col, row - 1, col, board)) {
            possibleTakes.add(row - 1);
            possibleTakes.add(col);
        }
        if (board[row + 1][col].isFieldEmpty()) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col);
        } else if (!isTheSameColor(row, col, row + 1, col, board)) {
            possibleTakes.add(row + 1);
            possibleTakes.add(col);
        }
    }

    public ArrayList<Integer> finalKingMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingMoves) {
        ArrayList<Integer> finalMoves;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPiece().getClass().getName().equals("Pawn") && (!isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possiblePawnTakes = possiblePawnTakes(i, j, board, true, false);
                    loop: for (int k = 0; k < possiblePawnTakes.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if ((Objects.equals(possibleKingMoves.get(l), possiblePawnTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possiblePawnTakes.get(k + 1)))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 2) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 3) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 2) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 3) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 5) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 6) || (possiblePawnTakes.get(k) == 0 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 5) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 6) || (possiblePawnTakes.get(k) == 7 && possiblePawnTakes.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                }
                            }
                    } else {
                            break;
                        }
                    }
                } else if (board[i][j].getPiece().getClass().getName().equals("Rook") && (!isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleRookMoves = possibleRookMoves(i, j, board, WANT_MOVES);
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
                                if (((possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 5) || (possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 6) || (possibleRookMoves.get(k) == 0 && possibleRookMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 2) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 3) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 5) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 6) || (possibleRookMoves.get(k) == 7 && possibleRookMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
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
                            ArrayList<Integer> possibleRookTakes = possibleRookMoves(i, j, simulateBoard, WANT_TAKES);
                            for (int k = 0; k < possibleRookTakes.size(); k += 2) {
                                if ((Objects.equals(possibleKingMoves.get(l), possibleRookTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleRookTakes.get(k + 1))) && !(board[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)].getPiece().getClass().getName().equals("Rook"))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    break loop;
                                }
                            }
                        }
                    System.out.println("Mobbyn2 :" + possibleKingMoves);
                } else if (board[i][j].getPiece().getClass().getName().equals("Bishop") && (!isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleBishopMoves = possibleBishopMoves(i, j, board, WANT_MOVES);
                    loop: for (int k = 0; k < possibleBishopMoves.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if ((Objects.equals(possibleKingMoves.get(l), possibleBishopMoves.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleBishopMoves.get(k + 1)))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleBishopMoves.get(k) == 0 && possibleBishopMoves.get(k + 1) == 2) || (possibleBishopMoves.get(k) == 0 && possibleBishopMoves.get(k + 1) == 3) || (possibleBishopMoves.get(k) == 0 && possibleBishopMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleBishopMoves.get(k) == 0 && possibleBishopMoves.get(k + 1) == 5) || (possibleBishopMoves.get(k) == 0 && possibleBishopMoves.get(k + 1) == 6) || (possibleBishopMoves.get(k) == 0 && possibleBishopMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleBishopMoves.get(k) == 7 && possibleBishopMoves.get(k + 1) == 2) || (possibleBishopMoves.get(k) == 7 && possibleBishopMoves.get(k + 1) == 3) || (possibleBishopMoves.get(k) == 7 && possibleBishopMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleBishopMoves.get(k) == 7 && possibleBishopMoves.get(k + 1) == 5) || (possibleBishopMoves.get(k) == 7 && possibleBishopMoves.get(k + 1) == 6) || (possibleBishopMoves.get(k) == 7 && possibleBishopMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                }
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
                        ArrayList<Integer> possibleBishopTakes = possibleBishopMoves(i, j, simulateKingMovesBoard, WANT_TAKES);
                        for (int k = 0; k < possibleBishopTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingMoves.get(l), possibleBishopTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleBishopTakes.get(k + 1))) && !(board[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)].getPiece().getClass().getName().equals("Rook"))) {
                                possibleKingMoves.remove(l);
                                possibleKingMoves.remove(l);
                                break loop;
                            }
                        }
                    }
                }else if (board[i][j].getPiece().getClass().getName().equals("Queen") && (!isTheSameColor(row, col, i, j, board))) {
                        ArrayList<Integer> possibleQueenMoves1 = possibleRookMoves(i, j, board, WANT_MOVES);
                        ArrayList<Integer> possibleQueenMoves2 = possibleBishopMoves(i, j, board, WANT_MOVES);
                    ArrayList<Integer> possibleQueenMoves = new ArrayList<>();
                        possibleQueenMoves.addAll(possibleQueenMoves1);
                        possibleQueenMoves.addAll(possibleQueenMoves2);
                        loop: for (int k = 0; k < possibleQueenMoves.size(); k += 2) {
                            if (possibleKingMoves.size() != 0) {
                                for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                    if ((Objects.equals(possibleKingMoves.get(l), possibleQueenMoves.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleQueenMoves.get(k + 1)))) {
                                        possibleKingMoves.remove(l);
                                        possibleKingMoves.remove(l);
                                        continue loop;
                                    }
                                    if (((possibleQueenMoves.get(k) == 0 && possibleQueenMoves.get(k + 1) == 2) || (possibleQueenMoves.get(k) == 0 && possibleQueenMoves.get(k + 1) == 3) || (possibleQueenMoves.get(k) == 0 && possibleQueenMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                        possibleKingMoves.remove(l);
                                        possibleKingMoves.remove(l);
                                        continue loop;
                                    }
                                    if (((possibleQueenMoves.get(k) == 0 && possibleQueenMoves.get(k + 1) == 5) || (possibleQueenMoves.get(k) == 0 && possibleQueenMoves.get(k + 1) == 6) || (possibleQueenMoves.get(k) == 0 && possibleQueenMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                        possibleKingMoves.remove(l);
                                        possibleKingMoves.remove(l);
                                        continue loop;
                                    }
                                    if (((possibleQueenMoves.get(k) == 7 && possibleQueenMoves.get(k + 1) == 2) || (possibleQueenMoves.get(k) == 7 && possibleQueenMoves.get(k + 1) == 3) || (possibleQueenMoves.get(k) == 7 && possibleQueenMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                        possibleKingMoves.remove(l);
                                        possibleKingMoves.remove(l);
                                        continue loop;
                                    }

                                    if (((possibleQueenMoves.get(k) == 7 && possibleQueenMoves.get(k + 1) == 5) || (possibleQueenMoves.get(k) == 7 && possibleQueenMoves.get(k + 1) == 6) || (possibleQueenMoves.get(k) == 7 && possibleQueenMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                        possibleKingMoves.remove(l);
                                        possibleKingMoves.remove(l);
                                    }
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
                            ArrayList<Integer> possibleQueenTakes1 = possibleRookMoves(i, j, simulateBoard, WANT_TAKES);
                            ArrayList<Integer> possibleQueenTakes2 = possibleBishopMoves(i, j, simulateBoard, WANT_TAKES);
                            ArrayList<Integer> possibleQueenTakes = new ArrayList<>();
                            possibleQueenTakes.addAll(possibleQueenTakes1);
                            possibleQueenTakes.addAll(possibleQueenTakes2);
                            for (int k = 0; k < possibleQueenTakes.size(); k += 2) {
                                if ((Objects.equals(possibleKingMoves.get(l), possibleQueenTakes.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleQueenTakes.get(k + 1))) && !(board[possibleKingMoves.get(l)][possibleKingMoves.get(l + 1)].getPiece().getClass().getName().equals("Rook"))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    break loop;
                                }
                            }
                        }
                } else if (board[i][j].getPiece().getClass().getName().equals("Knight") && (!isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleKnightMoves = possibleKnightMoves(i, j, board, false);
                    loop: for (int k = 0; k < possibleKnightMoves.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if ((Objects.equals(possibleKingMoves.get(l), possibleKnightMoves.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleKnightMoves.get(k + 1)))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleKnightMoves.get(k) == 0 && possibleKnightMoves.get(k + 1) == 2) || (possibleKnightMoves.get(k) == 0 && possibleKnightMoves.get(k + 1) == 3) || (possibleKnightMoves.get(k) == 0 && possibleKnightMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleKnightMoves.get(k) == 7 && possibleKnightMoves.get(k + 1) == 2) || (possibleKnightMoves.get(k) == 7 && possibleKnightMoves.get(k + 1) == 3) || (possibleKnightMoves.get(k) == 7 && possibleKnightMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleKnightMoves.get(k) == 0 && possibleKnightMoves.get(k + 1) == 5) || (possibleKnightMoves.get(k) == 0 && possibleKnightMoves.get(k + 1) == 6) || (possibleKnightMoves.get(k) == 0 && possibleKnightMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleKnightMoves.get(k) == 7 && possibleKnightMoves.get(k + 1) == 5) || (possibleKnightMoves.get(k) == 7 && possibleKnightMoves.get(k + 1) == 6) || (possibleKnightMoves.get(k) == 7 && possibleKnightMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                }
                            }
                    } else {
                            break;
                        }
                    }
                } else if (board[i][j].getPiece().getClass().getName().equals("King") && (!isTheSameColor(row, col, i, j, board))) {
                    ArrayList<Integer> possibleEnemyKingMoves = possibleKingMoves(i, j, board, true);
                    loop: for (int k = 0; k < possibleEnemyKingMoves.size(); k += 2) {
                        if (possibleKingMoves.size() != 0) {
                            for (int l = 0; l < possibleKingMoves.size(); l += 2) {
                                if ((Objects.equals(possibleKingMoves.get(l), possibleEnemyKingMoves.get(k))) && (Objects.equals(possibleKingMoves.get(l + 1), possibleEnemyKingMoves.get(k + 1)))) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleEnemyKingMoves.get(k) == 0 && possibleEnemyKingMoves.get(k + 1) == 2) || (possibleEnemyKingMoves.get(k) == 0 && possibleEnemyKingMoves.get(k + 1) == 3) || (possibleEnemyKingMoves.get(k) == 0 && possibleEnemyKingMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleEnemyKingMoves.get(k) == 0 && possibleEnemyKingMoves.get(k + 1) == 5) || (possibleEnemyKingMoves.get(k) == 0 && possibleEnemyKingMoves.get(k + 1) == 6) || (possibleEnemyKingMoves.get(k) == 0 && possibleEnemyKingMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 0 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleEnemyKingMoves.get(k) == 7 && possibleEnemyKingMoves.get(k + 1) == 2) || (possibleEnemyKingMoves.get(k) == 7 && possibleEnemyKingMoves.get(k + 1) == 3) || (possibleEnemyKingMoves.get(k) == 7 && possibleEnemyKingMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 0) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
                                if (((possibleEnemyKingMoves.get(k) == 7 && possibleEnemyKingMoves.get(k + 1) == 5) || (possibleEnemyKingMoves.get(k) == 7 && possibleEnemyKingMoves.get(k + 1) == 6) || (possibleEnemyKingMoves.get(k) == 7 && possibleEnemyKingMoves.get(k + 1) == 4)) && ((possibleKingMoves.get(l) == 7 && possibleKingMoves.get(l + 1) == 7) && board[row][col].getPiece().isFirstMove())) {
                                    possibleKingMoves.remove(l);
                                    possibleKingMoves.remove(l);
                                    continue loop;
                                }
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

    public ArrayList<Integer> finalKingTakes(int row, int col, Spot[][] board, ArrayList<Integer> possibleKingTakes) {
        ArrayList<Integer> finalTakes;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPiece().getClass().getName().equals("Pawn") && (!isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possiblePawnTakes = possiblePawnTakes(i, j, simulateKingTakesBoard, false, false);
                        for (int k = 0; k < possiblePawnTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possiblePawnTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possiblePawnTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }

                } else if (board[i][j].getPiece().getClass().getName().equals("Rook") && (!isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleRookTakes = possibleRookMoves(i, j, simulateKingTakesBoard, WANT_TAKES);
                        for (int k = 0; k < possibleRookTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleRookTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleRookTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }

                } else if (board[i][j].getPiece().getClass().getName().equals("Bishop") && (!isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleBishopTakes = possibleBishopMoves(i, j, simulateKingTakesBoard, WANT_TAKES);
                        for (int k = 0; k < possibleBishopTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleBishopTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleBishopTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }

                } else if (board[i][j].getPiece().getClass().getName().equals("Knight") && (!isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleKnightTakes = possibleKnightTakes(i, j, possibleKnightMoves(i, j, simulateKingTakesBoard, false), simulateKingTakesBoard);
                        for (int k = 0; k < possibleKnightTakes.size(); k += 2) {
                            if ((Objects.equals(possibleKingTakes.get(l), possibleKnightTakes.get(k))) && (Objects.equals(possibleKingTakes.get(l + 1), possibleKnightTakes.get(k + 1)))) {
                                possibleKingTakes.remove(l);
                                possibleKingTakes.remove(l);
                                continue loop;
                            }
                        }
                    }
                } else if (board[i][j].getPiece().getClass().getName().equals("Queen") && (!isTheSameColor(row, col, i, j, board))) {
                    loop: for (int l = 0; l < possibleKingTakes.size(); l += 2) {
                        Spot[][] simulateKingTakesBoard = new Spot[8][];
                        for (int x = 0; x < 8; x++) {
                            simulateKingTakesBoard[x] = Arrays.copyOf(board[x], board[x].length);
                        }
                        simulateKingTakesBoard[row][col] = new Spot(new Empty());
                        simulateKingTakesBoard[possibleKingTakes.get(l)][possibleKingTakes.get(l + 1)] = new Spot(new King(board[row][col].getPiece().isWhite(), false));
                        ArrayList<Integer> possibleQueenTakes1 = possibleRookMoves(i, j, simulateKingTakesBoard, WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes2 = possibleBishopMoves(i, j, simulateKingTakesBoard, WANT_TAKES);
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
                } else if (board[i][j].getPiece().getClass().getName().equals("King") && (!isTheSameColor(row, col, i, j, board))) {
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
    public boolean kingMove(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        if (!isTheSameColor(fromRow, fromCol, toRow, toCol, board)) {
            if ((Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 1) || (Math.abs(fromCol - toCol) == 1 && Math.abs(fromRow - toRow) == 0) || (Math.abs(fromCol - toCol) == 0 && Math.abs(fromRow - toRow) == 1)) {
                if (board[fromRow][fromCol].getPiece().isWhite()) {
                    board[fromRow][fromCol] = new Spot(new Empty());
                    board[toRow][toCol] = new Spot(new King(true, false));
                    return true;
                } else {
                    board[fromRow][fromCol] = new Spot(new Empty());
                    board[toRow][toCol] = new Spot(new King(false, false));
                    return true;
                }
            }
        } else {
            if (board[toRow][toCol].getPiece().getClass().getName().equals("Rook") && board[toRow][toCol].getPiece().isFirstMove() && board[fromRow][fromCol].getPiece().isFirstMove() && (fromRow == toRow)) {
                if ((toCol > fromCol) && board[toRow][toCol - 1].isFieldEmpty() && board[toRow][fromCol + 1].isFieldEmpty()) {
                    if (board[fromRow][fromCol].getPiece().isWhite()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol + 2] = new Spot(new King(true, false));
                        board[toRow][toCol - 2] = new Spot(new Rook(true, false));
                        return true;
                    } else {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol + 2] = new Spot(new King(false, false));
                        board[toRow][toCol - 2] = new Spot(new Rook(false, false));
                        return true;
                    }
                } else if (toCol < fromCol && board[toRow][toCol + 1].isFieldEmpty() && board[toRow][fromCol - 1].isFieldEmpty() && board[toRow][fromCol - 2].isFieldEmpty()) {
                    if (board[fromRow][fromCol].getPiece().isWhite()) {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol - 2] = new Spot(new King(true, false));
                        board[toRow][toCol + 3] = new Spot(new Rook(true, false));
                        return true;
                    } else {
                        board[fromRow][fromCol] = new Spot(new Empty());
                        board[toRow][toCol] = new Spot(new Empty());
                        board[fromRow][fromCol - 2] = new Spot(new King(false, false));
                        board[toRow][toCol + 3] = new Spot(new Rook(false, false));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> lookForCheckMate (Spot[][] board) {
        ArrayList<Integer> checkMateCords = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (board[i][j].getPiece().getClass().getName()) {
                    case "Pawn" -> checkMateCords(checkMateCords, board, possiblePawnTakes(i, j, board, false, false), i , j);

                    case "Rook" -> checkMateCords(checkMateCords, board, possibleRookMoves(i, j, board, WANT_TAKES), i, j);
                    case "Knight" -> {
                        ArrayList<Integer> possibleMoves = possibleKnightMoves(i, j, board, false);
                        checkMateCords(checkMateCords, board, possibleKnightTakes(i, j, possibleMoves, board), i, j);
                    }
                    case "Bishop" -> checkMateCords(checkMateCords, board, possibleBishopMoves(i, j, board, WANT_TAKES), i, j);
                    case "Queen" -> {
                        ArrayList<Integer> possibleQueenTakes1 = possibleRookMoves(i, j, board, WANT_TAKES);
                        ArrayList<Integer> possibleQueenTakes2 = possibleBishopMoves(i, j, board, WANT_TAKES);
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
    public void checkMateCords(ArrayList<Integer> previousCheck, Spot[][] board, ArrayList<Integer> takes, int row, int col) {
        for (int i = 0; i < takes.size(); i += 2) {
            if (board[takes.get(i)][takes.get(i + 1)].getPiece().getClass().getName().equals("King")) {
                if (previousCheck.size() == 0) {
                    previousCheck.add(takes.get(i));
                    previousCheck.add(takes.get(i + 1));
                }
                previousCheck.add(row);
                previousCheck.add(col);
            }
        }
    }
    public void possibleCheckMateMoves(Spot[][] board, ArrayList<Integer> checkMateCords) {
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
                if (isTheSameColor(kingRow, kingCol, i, j, board)) {
                    switch (board[i][j].getPiece().getClass().getName()) {
                        case "Pawn" -> {
                            boolean addCords = false;
                            possiblePieceTakes = possiblePawnTakes(i, j, board, false, false);
                            possiblePieceMoves = possiblePawnMoves(i, j, board);
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
                        case "Knight" -> {
                            boolean addCords = false;
                            possiblePieceMoves = possibleKnightMoves(i, j, board, false);
                            possiblePieceTakes = possibleKnightTakes(i, j, possiblePieceMoves, board);
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
                        case "Bishop" -> {
                            boolean addCords = false;
                            possiblePieceTakes = possibleBishopMoves(i, j, board, WANT_TAKES);
                            possiblePieceMoves = possibleBishopMoves(i, j, board, WANT_MOVES);
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
                        case "Rook" -> {
                            boolean addCords = false;
                            possiblePieceTakes = possibleRookMoves(i, j, board, WANT_TAKES);
                            possiblePieceMoves = possibleRookMoves(i, j, board, WANT_MOVES);
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
                        case "Queen" -> {
                            boolean addCords = false;
                            possiblePieceTakes = possibleRookMoves(i, j, board, WANT_TAKES);
                            possiblePieceTakes.addAll(possibleBishopMoves(i, j, board, WANT_TAKES));
                            possiblePieceMoves = possibleRookMoves(i, j, board, WANT_MOVES);
                            possiblePieceMoves.addAll(possibleBishopMoves(i, j, board, WANT_MOVES));
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
                        case "King" -> {
                            boolean addCords = false;
                            possiblePieceTakes = possibleKingMoves(i, j, board, false);
                            possiblePieceMoves = possibleKingMoves(i, j, board, true);
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
        gameFrame.board.setPiecesAbleToMove(piecesAbleToMove);
        gameFrame.board.setPossibleActions(possibleCheckMateMoves);
    }
}
