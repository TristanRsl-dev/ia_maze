package Model;

import Tools.Tuple;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class MapInfo {
    private static MapInfo mapInfo;

    private Tuple size;
    private int monsters;
    private int holes;

    public static MapInfo getInstance() {
        if (mapInfo == null)
            mapInfo = new MapInfo();
        return mapInfo;
    }

    private MapInfo() {
        size = new Tuple(3, 3);
        monsters = 0;
        holes = 0;
    }

    public Tuple getSize() {
        return size;
    }

    public int getMonsters() {
        return monsters;
    }

    public int getHoles() {
        return holes;
    }

    public void setSize(Tuple size) {
        this.size = size;
    }

    public void setMonsters(int monsters) {
        this.monsters = monsters;
    }

    public void setHoles(int holes) {
        this.holes = holes;
    }
}
