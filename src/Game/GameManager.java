package Game;

import Interface.GamePanel;
import Model.MapElt;
import Model.Player;
import Tools.ParseMap;
import Tools.Tuple;

import java.util.ArrayList;

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
            gp.repaintGame();
            isOnGate();
            if (player.getIsOut()) {
                gp.update();
                player.setIsOut();
            }
            else {
            // int a = interpret(nextAction());
                int result_interpret = interpret("move s");
                switch (result_interpret) {
                    case 1:
                        actions.shoot(new Tuple(1, 1));
                        break;
                    case 2:
                        actions.move(Player.Direction.n);
                        break;
                    case 3:
                        actions.move(Player.Direction.e);
                        break;
                    case 4:
                        actions.move(Player.Direction.s);
                        break;
                    default:
                        actions.move(Player.Direction.w);
                        break;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("Impossible to sleep: " + e.toString());
            }
        }
    }

    private int interpret(String src) {
        String[] src_split = src.split(" ");
        if (src_split.length == 1)
            return 1;
        else {
            switch (src_split[1]) {
                case "n":
                    return 2;
                case "e":
                    return 3;
                case "s":
                    return 4;
                default:
                    return 5;
            }
        }
    }

    private void isOnGate() {
        Tuple pos = player.getPos();
        ParseMap parse_map = ParseMap.getInstance();
        ArrayList<MapElt> map_elts = parse_map.getMap();
        Tuple pos_gate = new Tuple(0, 0);
        for (int i = 0; i < map_elts.size(); ++i) {
            if (map_elts.get(i).getType() == "out")
                pos_gate = map_elts.get(i).getPos();
        }
        if (pos.equals(pos_gate))
            player.setIsOut();
    }
}
