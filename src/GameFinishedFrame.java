import javax.swing.*;

public class GameFinishedFrame {
        int choice;
        GameFrame gameFrame;
        BoardController boardController;
        TimerController timerController;
        public GameFinishedFrame(GameFrame gameFrame, BoardController boardController, TimerController timerController, boolean whiteWon) throws InterruptedException {
            this.gameFrame = gameFrame;
            this.boardController = boardController;
            this.timerController = timerController;
            timerController.setCount(false);
            JOptionPane frame = new JOptionPane();
            frame.setSize(700, 200);
            String[] options = {"Zagraj ponownie", "Zamknij grę", "Zmień limity czasowe"};
            if (whiteWon) {
                do {
                    choice = JOptionPane.showOptionDialog(gameFrame, "Biały zwyciężył!", "Koniec gry", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                } while (choice == JOptionPane.CLOSED_OPTION);
        } else {
                do {
                    choice = JOptionPane.showOptionDialog(gameFrame, "Czarny zwyciężył!", "Koniec gry", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                } while (choice == JOptionPane.CLOSED_OPTION);
            }
            switch (choice) {
                case 0 -> {
                    gameFrame.board.resetBoard();
                    gameFrame.board.resetVariables();
                    gameFrame.board.repaint();
                    if (TimerController.isUnlimitedTime()) {
                        gameFrame.whiteTimer.setText("Biały czas: BEZ LIMITU");
                        gameFrame.blackTimer.setText("Czarny czas: BEZ LIMITU");
                    } else {
                        timerController.whiteTimer = gameFrame.getTimer();
                        timerController.blackTimer = gameFrame.getTimer();
                        String minutes;
                        String seconds;
                        int temp1 = timerController.blackTimer / 60;
                        if (temp1 < 10 && temp1 >= 0) {
                            minutes = "0" + temp1;
                        } else {
                            minutes = String.valueOf(temp1);
                        }
                        int temp2 = timerController.blackTimer % 60;
                        if (temp2 < 10 && temp2 >= 0) {
                            seconds = "0" + temp2;
                        } else {
                            seconds = String.valueOf(temp2);
                        }
                        gameFrame.whiteTimer.setText("Biały czas: " + minutes + ":" + seconds);
                        gameFrame.blackTimer.setText("Czarny czas: " + minutes + ":" + seconds);
                        timerController.setCount(true);
                    }
                    boardController.boardListener.setWhiteTurn(true);
                    timerController.setWhiteTurn(true);
                    timerController.setTimerActive(true);
                }
                case 1 -> System.exit(0);
                case 2 -> new TimeChooseFrame(gameFrame, timerController, this);
            }
        }

    public void resetGame() {
            gameFrame.board.resetBoard();
            gameFrame.board.resetVariables();
            gameFrame.board.repaint();
            boardController.boardListener.setWhiteTurn(true);
            timerController.setWhiteTurn(true);
            timerController.setTimerActive(true);
        }
    }

