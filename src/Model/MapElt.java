package Model;

import Tools.Tuple;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class MapElt {
    private Tuple pos;
    private String type;

    public MapElt(Tuple pos, String type) {
        this.pos = pos;
        this.type = type;
    }

    public Tuple getPos() {
        return pos;
    }

    public String getType() {
        return type;
    }

    public void setPos(Tuple pos) {
        this.pos = pos;
    }

    public void setType(String type) {
        this.type = type;
    }
}
