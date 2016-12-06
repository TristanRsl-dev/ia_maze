package Tools;

import Interface.Map;
import Model.MapElt;
import Model.MapInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class ParseMap {
    private String filename;
    private Tuple player_pos;

    private ArrayList<MapElt> map_elts;

    private static ParseMap pmap;

    public static ParseMap getInstance() {
        if (pmap == null)
            pmap = new ParseMap();
        return pmap;
    }

    private ParseMap() {
        filename = "";
        map_elts = new ArrayList<>();
    }

    public void create() {
        FileManager fm = new FileManager(filename);
        fm.open();
        ArrayList<String> map_infos = fm.read();
        fm.close();

        MapInfo map_model = MapInfo.getInstance();

        map_model.setMonsters(Integer.parseInt(map_infos.get(0)));
        map_model.setHoles(Integer.parseInt(map_infos.get(1)));
        player_pos = new Tuple(Integer.parseInt(map_infos.get(2)),
                Integer.parseInt(map_infos.get(3)));

        map_elts.add(new MapElt(player_pos, "player"));
        for (int i = 0; i < map_model.getMonsters(); ++i)
            map_elts.add(getRdmPos(map_model.getSize().getX(), "monster"));
        for (int i = 0; i < map_model.getHoles(); ++i)
            map_elts.add(getRdmPos(map_model.getSize().getX(), "hole"));
        map_elts.add(getRdmPos(map_model.getSize().getX(), "out"));
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<MapElt> getMap() {
        return map_elts;
    }

    private MapElt getRdmPos(int x, String type) {
        Random rdm = new Random();
        int rdm_x = rdm.nextInt(x - 1);
        int rdm_y = rdm.nextInt(x - 1);
        MapElt res = new MapElt(new Tuple(rdm_x, rdm_y), type);
        while (map_elts.contains(res)) {
            rdm_x = rdm.nextInt(x - 1);
            rdm_y = rdm.nextInt(x - 1);
            res.setPos(new Tuple(rdm_x, rdm_y));
        }
        return res;
    }
}
