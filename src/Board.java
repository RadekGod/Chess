import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Board extends JPanel {
    public Board() {
        resetBoard();
    }
    Spot[][] board = new Spot[8][8];
    ArrayList<Integer> possibleMoves = new ArrayList<>();
    ArrayList<Integer> possibleTakes = new ArrayList<>();
    ArrayList<Integer> possibleCheckMate = new ArrayList<>();
    ArrayList<Integer> piecesAbleToMove = new ArrayList<>();
    ArrayList<Integer> possibleActions = new ArrayList<>();


    int selectedRow = 8;
    int selectedCol = 8;
    boolean whiteTurn = true;

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public ArrayList<Integer> getPiecesAbleToMove() {
        return piecesAbleToMove;
    }

    public ArrayList<Integer> getPossibleActions() {
        return possibleActions;
    }


    public void resetVariables() {
        possibleMoves = new ArrayList<>();
        possibleTakes = new ArrayList<>();
        possibleCheckMate = new ArrayList<>();
        piecesAbleToMove = new ArrayList<>();
        possibleActions = new ArrayList<>();
        selectedRow = 8;
        selectedCol = 8;
    }

    public void setPiecesAbleToMove(ArrayList<Integer> piecesAbleToMove) {
        this.piecesAbleToMove = piecesAbleToMove;
    }

    public void setPossibleActions(ArrayList<Integer> possibleActions) {
        this.possibleActions = possibleActions;
    }
    public void setPossibleCheckMate(ArrayList<Integer> possibleCheckMate) {
        this.possibleCheckMate = possibleCheckMate;
    }
    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public int getSelectedCol() {
        return selectedCol;
    }

    public void setSelectedCol(int selectedCol) {
        this.selectedCol = selectedCol;
    }

    public void resetBoard() {
        board[0][0] = new Spot(new Rook(false, true));
        board[0][7] = new Spot(new Rook(false, true));
        board[0][1] = new Spot(new Knight(false, true));
        board[0][6] = new Spot(new Knight(false, true));
        board[0][2] = new Spot(new Bishop(false, true));
        board[0][5] = new Spot(new Bishop(false, true));
        board[0][3] = new Spot(new Queen(false, true));
        board[0][4] = new Spot(new King(false, true));


        board[7][0] = new Spot(new Rook(true, true));
        board[7][7] = new Spot(new Rook(true, true));
        board[7][1] = new Spot(new Knight(true, true));
        board[7][6] = new Spot(new Knight(true, true));
        board[7][2] = new Spot(new Bishop(true, true));
        board[7][5] = new Spot(new Bishop(true, true));
        board[7][3] = new Spot(new Queen(true, true));
        board[7][4] = new Spot(new King(true, true));
        for (int row = 1; row < 7; row ++) {
            for (int col = 0; col < 8; col++) {
                if (row == 1) {
                    board[row][col] = new Spot(new Pawn(false, true, false));
                } else if ( row == 6) {
                    board[row][col] = new Spot(new Pawn(true, true, false));
                } else {
                    board[row][col] = new Spot(new Empty());
                }

            }
        }

    }

    public void setPossibleMoves(ArrayList<Integer> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public void setPossibleTakes(ArrayList<Integer> possibleTakes) {
        this.possibleTakes = possibleTakes;
    }

    public void paintComponent (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row == getSelectedRow() && col == getSelectedCol()) {
                    g2.setColor(new Color(126, 176, 230, 255));
                } else {
                    if (row % 2 == col % 2) {
                        g2.setColor(new Color(183, 185, 201, 255));
                    } else {
                        g2.setColor(new Color(45, 59, 214, 255));
                    }
                }
                g2.fillRect(row * 75, col * 75, 75, 75);
            }
        }
        for (int i = 0; i < possibleMoves.size(); i += 2) {
            int x = possibleMoves.get(i);
            int y = possibleMoves.get(i + 1);
            g2.setColor(new Color(0, 143, 38, 200));
            g2.fillRect(y * 75, x * 75, 75, 75);
        }
        for (int i = 0; i < possibleTakes.size(); i += 2) {
            int x = possibleTakes.get(i);
            int y = possibleTakes.get(i + 1);
            g2.setColor(new Color(133, 20, 58, 255));
            g2.fillRect(y * 75, x * 75, 75, 75);
        }
        for (int i = 0; i < possibleCheckMate.size(); i += 2) {
            int x = possibleCheckMate.get(i);
            int y = possibleCheckMate.get(i + 1);
            g2.setColor(new Color(28, 46, 82, 255));
            g2.fillRect(y * 75, x * 75, 75, 75);
        }
        for (int i = 0; i < piecesAbleToMove.size(); i += 2) {
            int x = piecesAbleToMove.get(i);
            int y = piecesAbleToMove.get(i + 1);
            g2.setColor(new Color(235, 207, 52, 255));
            g2.fillRect(y * 75, x * 75, 75, 75);
        }
        possibleCheckMate.clear();
        possibleMoves.clear();
        possibleTakes.clear();
        piecesAbleToMove.clear();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                    if (board[row][col].getPiece().getClass().getName().equals("Rook") && !(board[row][col].getPiece().isWhite())) {
                            BufferedImage image;
                            try {
                                image = ImageIO.read(new File("b-rook.png"));
                                g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                    } else if (board[row][col].getPiece().getClass().getName().equals("Rook") && (board[row][col].getPiece().isWhite())) {
                        BufferedImage image;
                        try {
                            image = ImageIO.read(new File("rook.png"));
                            g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                if (board[row][col].getPiece().getClass().getName().equals("Pawn") && !(board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("b-pawn.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else if (board[row][col].getPiece().getClass().getName().equals("Pawn") && (board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("pawn.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (board[row][col].getPiece().getClass().getName().equals("Knight") && !(board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("b-knight.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else if (board[row][col].getPiece().getClass().getName().equals("Knight") && (board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("knight.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (board[row][col].getPiece().getClass().getName().equals("Bishop") && !(board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("b-bishop.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else if (board[row][col].getPiece().getClass().getName().equals("Bishop") && (board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("bishop.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (board[row][col].getPiece().getClass().getName().equals("Queen") && !(board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("b-queen.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else if (board[row][col].getPiece().getClass().getName().equals("Queen") && (board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("queen.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (board[row][col].getPiece().getClass().getName().equals("King") && !(board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("b-king.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else if (board[row][col].getPiece().getClass().getName().equals("King") && (board[row][col].getPiece().isWhite())) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File("king.png"));
                        g.drawImage(image, col * 75 + 7, row * 75 + 5, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
