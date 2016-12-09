package Game;

import Model.MapElt;
import Model.MapInfo;
import Model.Player;
import Tools.ParseMap;
import Tools.Tuple;

/**
 * Created by trist_000 on 06/12/2016.
 */
public class Actions {
    private Player player;

    public Actions() {
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

        player.setDir(dir);
    }

    public void shoot(Tuple pos_monster) {
        ParseMap map = ParseMap.getInstance();
        map.getMap().remove(new MapElt(pos_monster, "monster"));
        Perform.incRock();
    }

    public void getout() {
        MapInfo map = MapInfo.getInstance();
        player.setIsOut();
        Perform.changePerf(10 * map.getSize().getX());
    }

    public void die() {
        MapInfo map = MapInfo.getInstance();
        Perform.changePerf(-10 * map.getSize().getX());
    }

    public String nextAction(){
        String action, type="";
        Tuple pos = player.getPos();
        ParseMap map = ParseMap.getInstance();

        for (MapElt elt : map.getMapSensors()
             ) {
            if (pos.getY() == elt.getPos().getY() && pos.getX() == elt.getPos().getX()) {
                type += elt.getType();
                //player.map.add(elt);
            }
        }

        switch (type) {
            case "out":
                action="getout";
                break;
            default:
            {
                action="move ";
                break;
            }

            case "smoke":
                action="shoot";
                break;

            case "wind":
                action="move ";
                break;

            case "smokewind":
                action="shoot";
                break;
        }

        return action;
    }
}