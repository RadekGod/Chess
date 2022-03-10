package chess;

public class TimerController{
    boolean whiteTurn = true;
    boolean isTimerActive = false;
    boolean count = true;
    static boolean unlimitedTime = false;
    GameFrame gameFrame;
    int blackTimer;
    int whiteTimer;

    public static boolean isUnlimitedTime() {
        return unlimitedTime;
    }

    Thread thread1 = new Thread(() -> {
        synchronized (this) {
            while (isTimerActive) {
                if (count && !unlimitedTime) {
                    try {
                        timerController(gameFrame, isWhiteTurn());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        this.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        this.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });


    public void setCount(boolean count) {
        this.count = count;
    }

    public TimerController(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        blackTimer = gameFrame.getTimer();
        whiteTimer = gameFrame.getTimer();
        setTimerActive(true);
        thread1.start();
    }

    public void setBlackTimer(int blackTimer) {
        this.blackTimer = blackTimer;
    }

    public void setWhiteTimer(int whiteTimer) {
        this.whiteTimer = whiteTimer;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public void setTimerActive(boolean timerActive) {
        isTimerActive = timerActive;
    }

    public synchronized void timerController(GameFrame gameFrame, boolean whiteTurn) throws InterruptedException {
        if (whiteTurn) {
            //for (int i = whiteTimer; i >= 0; i--) {
            whiteTimer--;

            String minutes;
            String seconds;
            int temp1 = whiteTimer / 60;
            if (temp1 < 10 && temp1 >= 0) {
                minutes = "0" + temp1;
            } else {
                minutes = String.valueOf(temp1);
            }
            int temp2 = whiteTimer % 60;
            if (temp2 < 10 && temp2 >= 0) {
                seconds = "0" + temp2;
            } else {
                seconds = String.valueOf(temp2);
            }
            gameFrame.getWhiteTimer().setText("Biały czas: " + minutes + ":" + seconds);
            //Thread.sleep(1000);
            //}
            if (whiteTimer == 0) {
                new GameFinishedFrame(gameFrame, gameFrame.getBoardController(), this, false);
            }
        } else {
            //for (int i = blackTimer; i >= 0; i--) {
            blackTimer--;

            String minutes;
            String seconds;
            int temp1 = blackTimer / 60;
            if (temp1 < 10 && temp1 >= 0) {
                minutes = "0" + temp1;
            } else {
                minutes = String.valueOf(temp1);
            }
            int temp2 = blackTimer % 60;
            if (temp2 < 10 && temp2 >= 0) {
                seconds = "0" + temp2;
            } else {
                seconds = String.valueOf(temp2);
            }
            gameFrame.getBlackTimer().setText("Czarny czas: " + minutes + ":" + seconds);
            if (blackTimer == 0) {
                new GameFinishedFrame(gameFrame, gameFrame.getBoardController(), this, true);
            }
            //Thread.sleep(1000);
            //}
        }

    }
}
