package chess;

import javax.swing.*;

public class PawnChangeFrame {
    int choice;

    public PawnChangeFrame(GameFrame gameFrame) {
        JOptionPane frame = new JOptionPane();
        frame.setSize(700, 200);
        String[] options = {"chess.queen.Queen", "chess.rook.Rook", "chess.knight.Knight", "chess.bishop.Bishop"};
        do  {
            choice = JOptionPane.showOptionDialog(gameFrame, "Wybierz bierkę", "Wybierz odpowiedni przycisk", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        } while (choice == JOptionPane.CLOSED_OPTION);
    }
    public int getChoice() {
        return choice;
    }

}
