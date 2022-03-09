import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame();
            Move move = new Move(gameFrame);
            try {
                new BoardController(gameFrame, move);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameFrame.setBoardController(gameFrame.boardController);
        });
    }
}
