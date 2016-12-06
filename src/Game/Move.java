package Game;

import Model.MapInfo;
import Model.Player;
import Tools.Tuple;

/**
 * Created by trist_000 on 06/12/2016.
 */
public class Move {
    private Player player;

    public Move() {
        player = Player.getInstance();
    }

    public void move(Player.Direction dir) {
        Tuple pos = player.getPos();
        MapInfo map_info = MapInfo.getInstance();

        switch (dir) {
            case n:
                if (pos.getY() - 1 >= 0) {
                    pos.setY(pos.getY() - 1);
                    Perform.changePerf(-1);
                }
                break;
            case e:
                if (pos.getX() + 1 < map_info.getSize().getX()) {
                    pos.setX(pos.getX() + 1);
                    Perform.changePerf(-1);
                }
                break;
            case s:
                if (pos.getY() + 1 < map_info.getSize().getY()) {
                    pos.setY(pos.getY() + 1);
                    Perform.changePerf(-1);
                }
                break;
            default: //w
                if (pos.getX() - 1 >= 0) {
                    pos.setX(pos.getX() - 1);
                    Perform.changePerf(-1);
                }
                break;
        }
    }
}
