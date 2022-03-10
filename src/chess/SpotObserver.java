package chess;

public class SpotObserver {

    public static boolean isTheSameColor(int fromRow, int fromCol, int toRow, int toCol, Spot[][] board) {
        return board[fromRow][fromCol].getPiece().isWhite() == board[toRow][toCol].getPiece().isWhite() && (!board[fromRow][fromCol].isFieldEmpty()) && (!board[toRow][toCol].isFieldEmpty());
    }

    public static boolean isOnTheSameDiagonal(int fromRow, int fromCol, int toRow, int toCol) {
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

}
