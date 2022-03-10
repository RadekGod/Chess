package chess;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame();;
            try {
                new BoardController(gameFrame);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameFrame.setBoardController(gameFrame.getBoardController());
        });
    }
}
