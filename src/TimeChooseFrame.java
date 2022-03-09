import javax.swing.*;

public class TimeChooseFrame {
        int choice;
        int timer;


    public TimeChooseFrame(GameFrame gameFrame, TimerController timerController, GameFinishedFrame gameFinishedFrame) {
            JOptionPane frame = new JOptionPane();
            frame.setSize(700, 200);
            String[] options = {"1 minuta", "10 minut", "30 minut", "60 minut", "bez limitu"};
            do  {
                choice = JOptionPane.showOptionDialog(gameFrame, "Wybierz limit czasowy", "Wybierz odpowiedni przycisk", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            } while (choice == JOptionPane.CLOSED_OPTION);
            if (timerController != null && gameFinishedFrame != null) {
                switch (choice) {
                    case 0 -> {
                        timer = 60;
                        gameFrame.setTimer(timer);
                        timerController.setBlackTimer(timer);
                        timerController.setWhiteTimer(timer);
                        TimerController.unlimitedTime = false;
                        timerController.setCount(true);
                        gameFrame.whiteTimer.setText("Biały czas: 01:00");
                        gameFrame.blackTimer.setText("Czarny czas: 01:00");
                        gameFinishedFrame.resetGame();
                    }
                    case 1 -> {
                        timer = 600;
                        gameFrame.setTimer(timer);
                        timerController.setBlackTimer(timer);
                        timerController.setWhiteTimer(timer);
                        TimerController.unlimitedTime = false;
                        timerController.setCount(true);
                        gameFrame.whiteTimer.setText("Biały czas: 10:00");
                        gameFrame.blackTimer.setText("Czarny czas: 10:00");
                        gameFinishedFrame.resetGame();
                    }
                    case 2 -> {
                        timer = 1800;
                        gameFrame.setTimer(timer);
                        timerController.setBlackTimer(timer);
                        timerController.setWhiteTimer(timer);
                        TimerController.unlimitedTime = false;
                        timerController.setCount(true);
                        gameFrame.whiteTimer.setText("Biały czas: 30:00");
                        gameFrame.blackTimer.setText("Czarny czas: 30:00");
                        gameFinishedFrame.resetGame();
                    }
                    case 3 -> {
                        timer = 3600;
                        gameFrame.setTimer(timer);
                        timerController.setBlackTimer(timer);
                        timerController.setWhiteTimer(timer);
                        TimerController.unlimitedTime = false;
                        timerController.setCount(true);
                        gameFrame.whiteTimer.setText("Biały czas: 60:00");
                        gameFrame.blackTimer.setText("Czarny czas: 60:00");
                        gameFinishedFrame.resetGame();
                    }
                    case 4 -> {
                        timer = -1;
                        gameFrame.setTimer(timer);
                        timerController.setBlackTimer(timer);
                        timerController.setWhiteTimer(timer);
                        TimerController.unlimitedTime = true;
                        gameFrame.whiteTimer.setText("Biały czas: BEZ LIMITU");
                        gameFrame.blackTimer.setText("Czarny czas: BEZ LIMITU");
                        gameFinishedFrame.resetGame();
                    }
                }
            } else {
                switch (choice) {
                    case 0 -> timer = 60;
                    case 1 -> timer = 600;
                    case 2 -> timer = 1800;
                    case 3 -> timer = 3600;
                    case 4 -> {
                        timer = -1;
                        TimerController.unlimitedTime = true;
                    }
                }
            }
        }
        public int getTimer() {
            return timer;
        }

}
