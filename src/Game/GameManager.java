package Game;

import Interface.GamePanel;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class GameManager extends Thread {
    private GamePanel gp;

    public GameManager(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void run() {
        while (true) {
            gp.repaintGame();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Impossible to sleep: " + e.toString());
            }
        }
    }
}
