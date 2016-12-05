import Game.GameManager;
import Interface.GamePanel;
import Interface.Window;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class Main {
    public static void main(String[] args) {
        GamePanel gp = new GamePanel();

        GameManager gm = new GameManager(gp);
        gm.start();

        new Window(gp);
    }
}
