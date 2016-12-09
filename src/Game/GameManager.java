package Game;

import Interface.GamePanel;
import Model.Player;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class GameManager extends Thread {
    private GamePanel gp;
    private Actions actions;
    private Player player;

    public GameManager(GamePanel gp) {
        this.gp = gp;
        actions = new Actions();
        player = Player.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            if (player.getIsOut()) {
                gp.update();
                player.setIsOut();
            }
            actions.nextAction();
            //actions.actions(Player.Direction.s);
            gp.repaintGame();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Impossible to sleep: " + e.toString());
            }
        }
    }
}
