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
    private int nb_monsters;
    private int nb_holes;
    private Tuple player_pos;

    private ArrayList<MapElt> map_elts;

    private static ParseMap pmap;

    private static ParseMap getInstance() {
        if (pmap == null)
            pmap = new ParseMap();
        return pmap;
    }

    public ParseMap() {
        filename = "";
        map_elts = new ArrayList<>();
    }

    public void create() {
        FileManager fm = new FileManager(filename);
        fm.open();
        ArrayList<String> map_infos = fm.read();
        fm.close();

        nb_monsters = Integer.parseInt(map_infos.get(0));
        nb_holes = Integer.parseInt(map_infos.get(1));
        player_pos = new Tuple(Integer.parseInt(map_infos.get(2)),
                Integer.parseInt(map_infos.get(3)));

        MapInfo map_model = MapInfo.getInstance();

        map_elts.add(new MapElt(player_pos, "player"));
        for (int i = 0; i < nb_monsters; ++i)
            map_elts.add(getRdmPos(map_model.getSize().getX(), "monster"));
        for (int i = 0; i < nb_holes; ++i)
            map_elts.add(getRdmPos(map_model.getSize().getX(), "hole"));
        map_elts.add(getRdmPos(map_model.getSize().getX(), "out"));
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
