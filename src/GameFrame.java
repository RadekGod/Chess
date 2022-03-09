import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame {
    BoardController boardController;
    Board board = new Board();
    JLabel blackTimer;
    JLabel whiteTimer;
    JButton surrenderButton = new JButton("Poddaj się");
    int timer;
    public static final int HEIGHT = 669; //było 639
    public static final int WIDTH = 616; //było 616

    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public GameFrame() {
         timer = new TimeChooseFrame(this, null, null).getTimer();
         if (timer != -1) {
             String minutes;
             String seconds;
             int temp1 = timer / 60;
             if (temp1 < 10 && temp1 >= 0) {
                 minutes = "0" + temp1;
             } else {
                 minutes = String.valueOf(temp1);
             }
             int temp2 = timer % 60;
             if (temp2 < 10 && temp2 >= 0) {
                 seconds = "0" + temp2;
             } else {
                 seconds = String.valueOf(temp2);
             }
             whiteTimer = new JLabel("Biały czas: " + minutes + ":" + seconds);
             blackTimer = new JLabel("Czarny czas: " + minutes + ":" + seconds);
         } else {
             whiteTimer = new JLabel("Biały czas: BEZ LIMITU");
             blackTimer = new JLabel("Czarny czas: BEZ LIMITU");
         }
         this.setSize(WIDTH, HEIGHT);
         this.setLocationRelativeTo(null);
         BorderLayout layout = new BorderLayout();
         this.setLayout(layout);
         board.setPreferredSize(new Dimension(639, 616));
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);

         JPanel optionPanel = new JPanel();
         optionPanel.setLayout(new FlowLayout());
         blackTimer.setSize(50, 20);
         whiteTimer.setSize(50, 20);
         surrenderButton.setSize(30,20);
         surrenderButton.addActionListener((e) -> {
             try {
                 new GameFinishedFrame(this, getBoardController() ,getBoardController().boardListener.timerController, !board.isWhiteTurn());
             } catch (InterruptedException ex) {
                 ex.printStackTrace();
             }
         });
         optionPanel.add(blackTimer);
         optionPanel.add(surrenderButton);
         optionPanel.add(whiteTimer);

         this.add(board, BorderLayout.CENTER);
         this.add(optionPanel, BorderLayout.NORTH);
         this.setVisible(true);
     }
     public void addBoardListener (MouseListener mouseListener) {
         board.addMouseListener(mouseListener);
     }
}
