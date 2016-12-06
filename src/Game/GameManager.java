package Game;

import Interface.GamePanel;
import Model.Player;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class GameManager extends Thread {
    private GamePanel gp;
    private Move move;

    public GameManager(GamePanel gp) {
        this.gp = gp;
        move = new Move();
    }

    @Override
    public void run() {
        while (true) {
            //gp.update();
            //move.move(Player.Direction.s);
            gp.repaintGame();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Impossible to sleep: " + e.toString());
            }
        }
    }
}
