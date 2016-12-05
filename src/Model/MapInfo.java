package Model;

import Tools.Tuple;

import java.util.ArrayList;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class MapInfo {
    private Tuple size;
    private ArrayList<ArrayList<String>> map;

    private static MapInfo mapInfo;

    public static MapInfo getInstance() {
        if (mapInfo == null)
            mapInfo = new MapInfo();
        return mapInfo;
    }

    private MapInfo() {
        map = new ArrayList<>();
        size = new Tuple(3, 3);
    }

    public Tuple getSize() {
        return size;
    }

    public ArrayList<ArrayList<String>> getMap() {
        return map;
    }

    public void setSize(Tuple size) {
        this.size = size;
    }

    public void setMap(ArrayList<ArrayList<String>> map) {
        this.map = map;
    }
}
