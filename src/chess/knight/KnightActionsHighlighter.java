package chess.knight;

import chess.CheckMateObserver;
import chess.Empty;
import chess.Spot;
import chess.SpotObserver;

import java.util.ArrayList;
import java.util.Arrays;

public class KnightActionsHighlighter {
    public static ArrayList<Integer> possibleKnightMoves (int row, int col, Spot[][] board, boolean simulateIfMoveIsLegal) {
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        if (row > 1 && row < 6) {
            if (col > 1 && col < 6) {
                checkPossibleKnightMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row - 1, col - 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 2);
                }
                addLeftBottomMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 0) {
                if (!SpotObserver.isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!SpotObserver.isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                addRightBottomMoves(row, col, board, possibleMoves);
            } else if (col == 7) {
                if (!SpotObserver.isTheSameColor(row, col, row + 2, col - 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col - 1);
                }
                checkAnotherPossibleKnightMoves(row, col, board, possibleMoves);
            } else if (col == 1) {
                if (!SpotObserver.isTheSameColor(row, col, row - 2, 0, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(0);
                }
                addRightTopMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row + 2, 0, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(0);
                }
                addRightBottomMoves(row, col, board, possibleMoves);
            } else if (col == 6) {
                checkPossibleKnightMoves(row, col, board, possibleMoves);
                addLeftTopMoves(row, col, board, possibleMoves);
            }
        } else if (row == 0) {
            if (col > 1 && col < 6) {
                addLeftBottomMoves(row, col, board, possibleMoves);
                addZeroRowRightTopMoves(row, col, board, possibleMoves);
            } else if (col == 0) {
                addZeroColRightBottomMoves(row, col, board, possibleMoves);
            } else if (col == 7) {
                addSevenColLeftBottomMoves(row, col, board, possibleMoves);
            } else if (col == 1) {
                addZeroColRightBottomMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row + 2, 0, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                addZeroRowRightTopMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
            }
        } else if (row == 1) {
            if (col > 1 && col < 6) {
                addLeftBottomMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, 0, col - 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col - 2);
                }
                if (!SpotObserver.isTheSameColor(row, col, 0, col + 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col + 2);
                }
                addZeroRowRightTopMoves(row, col, board, possibleMoves);
            } else if (col == 0) {
                addZeroColRightBottomMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, 0, col + 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 7) {
                addSevenColLeftBottomMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, 0, col - 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col - 2);
                }
            } else if (col == 1) {
                if (!SpotObserver.isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!SpotObserver.isTheSameColor(row, col, 0, col + 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col + 2);
                }
                if (!SpotObserver.isTheSameColor(row, col, row + 2, col + 1, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(col + 1);
                }
                if (!SpotObserver.isTheSameColor(row, col, row + 2, 0, board)) {
                    possibleMoves.add(row + 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                addZeroRowRightTopMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                if (!SpotObserver.isTheSameColor(row, col, 0, col - 2, board)) {
                    possibleMoves.add(0);
                    possibleMoves.add(col - 2);
                }
            }
        } else if (row == 7) {
            if (col > 1 && col < 6) {
                checkAnotherAnotherPossibleKnightMoves(row, col, board, possibleMoves);
            } else if (col == 0) {
                addZeroColRightTopMoves(row, col, board, possibleMoves);
            } else if (col == 7) {
                if (!SpotObserver.isTheSameColor(row, col, row - 1, col - 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col - 2);
                }
                if (!SpotObserver.isTheSameColor(row, col, row - 2, col - 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col - 1);
                }
            } else if (col == 1) {
                addZeroColRightTopMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row - 2, 0, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                if (!SpotObserver.isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                addSixColLeftTopMoves(row, col, board, possibleMoves);
            }
        } else if (row == 6) {
            if (col > 1 && col < 6) {
                addLeftBottomMoves(row, col, board, possibleMoves);
                checkAnotherAnotherPossibleKnightMoves(row, col, board, possibleMoves);
            } else if (col == 0) {
                if (!SpotObserver.isTheSameColor(row, col, row + 1, col + 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col + 2);
                }
                if (!SpotObserver.isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!SpotObserver.isTheSameColor(row, col, row - 1, col + 2, board)) {
                    possibleMoves.add(row - 1);
                    possibleMoves.add(col + 2);
                }
            } else if (col == 7) {
                if (!SpotObserver.isTheSameColor(row, col, row + 1, col - 2, board)) {
                    possibleMoves.add(row + 1);
                    possibleMoves.add(col - 2);
                }
                addSixColLeftTopMoves(row, col, board, possibleMoves);
            } else if (col == 1) {
                addRightBottomMoves(row, col, board, possibleMoves);
                if (!SpotObserver.isTheSameColor(row, col, row - 2, col + 1, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(col + 1);
                }
                if (!SpotObserver.isTheSameColor(row, col, row - 2, 0, board)) {
                    possibleMoves.add(row - 2);
                    possibleMoves.add(0);
                }
            } else if (col == 6) {
                if (!SpotObserver.isTheSameColor(row, col, row - 2, col + 1, board)) {
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
                fillFinalPossibleMoves(row, col, board, possibleMoves, simulateBoard, finalPossibleMoves, i);
            }
            return finalPossibleMoves;
        } else {
            return possibleMoves;
        }

    }

    public static void fillFinalPossibleMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, Spot[][] simulateBoard, ArrayList<Integer> finalPossibleMoves, int i) {
        addToFinalMoves(row, col, board, possibleMoves, simulateBoard, finalPossibleMoves, i);
    }

    public static void addToFinalMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves, Spot[][] simulateBoard, ArrayList<Integer> finalPossibleMoves, int i) {
        ArrayList<Integer> hypotheticalCheck = CheckMateObserver.lookForCheckMate(simulateBoard);
        if (!(hypotheticalCheck.size() != 0 && SpotObserver.isTheSameColor(hypotheticalCheck.get(0), hypotheticalCheck.get(1), row, col, board))) {
            finalPossibleMoves.add(possibleMoves.get(i));
            finalPossibleMoves.add(possibleMoves.get(i + 1));
        }
    }

    private static void addSixColLeftTopMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row - 2, col - 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col - 1);
        }
        if (!SpotObserver.isTheSameColor(row, col, row - 1, col - 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col - 2);
        }
    }

    private static void addZeroColRightTopMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row - 1, col + 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col + 2);
        }
        if (!SpotObserver.isTheSameColor(row, col, row - 2, col + 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col + 1);
        }
    }

    private static void addSevenColLeftBottomMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row + 1, col - 2, board)) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col - 2);
        }
        if (!SpotObserver.isTheSameColor(row, col, row + 2, col - 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col - 1);
        }
    }

    private static void addZeroColRightBottomMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row + 1, col + 2, board)) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col + 2);
        }
        if (!SpotObserver.isTheSameColor(row, col, row + 2, col + 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col + 1);
        }
    }

    private static void addZeroRowRightTopMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row + 2, col + 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col + 1);
        }
        if (!SpotObserver.isTheSameColor(row, col, row + 2, col - 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col - 1);
        }
    }

    private static void addLeftTopMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row + 1, col - 2, board)) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col - 2);
        }
        if (!SpotObserver.isTheSameColor(row, col, row - 1, col - 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col - 2);
        }
    }

    private static void addRightTopMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row - 2, col + 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col + 1);
        }
        if (!SpotObserver.isTheSameColor(row, col, row + 2, col + 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col + 1);
        }
    }

    private static void addRightBottomMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row + 1, col + 2, board)) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col + 2);
        }
        if (!SpotObserver.isTheSameColor(row, col, row - 1, col + 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col + 2);
        }
    }

    private static void addLeftBottomMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row + 1, col - 2, board)) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col - 2);
        }
        if (!SpotObserver.isTheSameColor(row, col, row + 1, col + 2, board)) {
            possibleMoves.add(row + 1);
            possibleMoves.add(col + 2);
        }
    }

    private static void checkPossibleKnightMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row - 2, col - 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col - 1);
        }
        addRightTopMoves(row, col, board, possibleMoves);
        if (!SpotObserver.isTheSameColor(row, col, row + 2, col - 1, board)) {
            possibleMoves.add(row + 2);
            possibleMoves.add(col - 1);
        }
    }

    private static void checkAnotherPossibleKnightMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row - 2, col - 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col - 1);
        }
        addLeftTopMoves(row, col, board, possibleMoves);
    }

    private static void checkAnotherAnotherPossibleKnightMoves(int row, int col, Spot[][] board, ArrayList<Integer> possibleMoves) {
        if (!SpotObserver.isTheSameColor(row, col, row - 1, col - 2, board)) {
            possibleMoves.add(row - 1);
            possibleMoves.add(col - 2);
        }
        addZeroColRightTopMoves(row, col, board, possibleMoves);
        if (!SpotObserver.isTheSameColor(row, col, row - 2, col - 1, board)) {
            possibleMoves.add(row - 2);
            possibleMoves.add(col - 1);
        }
    }

    public static ArrayList<Integer> possibleKnightTakes(int fromRow,int fromCol, ArrayList<Integer> possibleMoves, Spot[][] board) {
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

}
