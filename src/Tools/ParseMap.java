package Tools;

import Model.MapElt;
import Model.MapInfo;
import Model.Player;
import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class ParseMap {
    private String filename;
    private Tuple player_pos;

    private ArrayList<MapElt> map_elts;
    private ArrayList<MapElt> map_sensors;

    private static ParseMap pmap;

    public static ParseMap getInstance() {
        if (pmap == null)
            pmap = new ParseMap();
        return pmap;
    }

    private ParseMap() {
        filename = "";
        map_elts = new ArrayList<>();
        map_sensors = new ArrayList<>();
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
        Player tmp_player = Player.getInstance();
        tmp_player.setPos(player_pos);

        fillArray(map_model);
    }

    public void update() {
        map_elts = new ArrayList<>();
        map_sensors = new ArrayList<>();
        MapInfo map_info = MapInfo.getInstance();
        Tuple map_size = map_info.getSize();
        map_size.setX(map_size.getX() + 1);
        map_size.setY(map_size.getY() + 1);
        map_info.setSize(map_size);

        map_info.setMonsters(map_info.getMonsters() + 1);
        map_info.setHoles(map_info.getHoles() + 1);

        fillArray(map_info);
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<MapElt> getMap() {
        return map_elts;
    }

    public ArrayList<MapElt> getMapSensors() {
        return map_sensors;
    }

    private MapElt getRdmPos(int x, String type) {
        Random rdm = new Random();
        int rdm_x = rdm.nextInt(x - 1);
        int rdm_y = rdm.nextInt(x - 1);
        MapElt res = new MapElt(new Tuple(rdm_x, rdm_y), type);
        while (inList(res)) {
            rdm_x = rdm.nextInt(x - 1);
            rdm_y = rdm.nextInt(x - 1);
            res.setPos(new Tuple(rdm_x, rdm_y));
        }
        return res;
    }

    private boolean inList(MapElt elt) {
        for (int i = 0; i < map_elts.size(); ++i)
            if (map_elts.get(i).getPos().equals(elt.getPos()))
                return true;
        return false;
    }

    private void fillAdj(MapElt elt, MapInfo map_info) {
        int x = elt.getPos().getX();
        int y = elt.getPos().getY();
        String type = "wind";
        if (elt.getType().equals("monster"))
            type = "smoke";

        if (x - 1 >= 0)
            map_sensors.add(new MapElt(new Tuple(x - 1, y), type));

        if (x + 1 < map_info.getSize().getX())
            map_sensors.add(new MapElt(new Tuple(x + 1, y), type));

        if (y - 1 >= 0)
            map_sensors.add(new MapElt(new Tuple(x, y - 1), type));

        if (y + 1 < map_info.getSize().getY())
            map_sensors.add(new MapElt(new Tuple(x, y + 1), type));
    }

    private void fillArray(MapInfo map_model) {
        map_elts.add(new MapElt(player_pos, "player"));
        for (int i = 0; i < map_model.getMonsters(); ++i) {
            MapElt elt = getRdmPos(map_model.getSize().getX(), "monster");
            map_elts.add(elt);
            fillAdj(elt, map_model);
        }
        for (int i = 0; i < map_model.getHoles(); ++i) {
            MapElt elt = getRdmPos(map_model.getSize().getX(), "hole");
            map_elts.add(elt);
            fillAdj(elt, map_model);
        }
        map_elts.add(getRdmPos(map_model.getSize().getX(), "out"));

    }
}
