package Model;

import Tools.ParseMap;
import Tools.Tuple;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class Player {
    private static Player player;

    private Tuple pos;
    private BufferedImage pic;
    private int pic_width;
    private int pic_height;
    private boolean is_out;
    public ArrayList<MapElt> map;

    public enum Direction {
        n,
        s,
        w,
        e
    }

    private Direction dir;

    public static Player getInstance() {
        if (player == null)
            player = new Player();
        return player;
    }

    private Player() {
        pos = new Tuple(0, 0);
        pic_width = 0;
        pic_height = 0;
        is_out = false;
        dir = Direction.s;
    }

    public Tuple getPos() {
        return pos;
    }

    public BufferedImage getPic() {
        return pic;
    }

    public int getPicW() {
        return pic_width;
    }

    public int getPicH() {
        return pic_height;
    }

    public Direction getDir() {
        return dir;
    }

    public boolean getIsOut() {
        return is_out;
    }

    public void setPos(Tuple pos) {
        this.pos = pos;
    }

    public void setPic(BufferedImage pic) {
        this.pic = pic;
    }

    public void setPicW(int pic_width) {
        this.pic_width = pic_width;
    }

    public void setPicH(int pic_height) {
        this.pic_height = pic_height;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void setIsOut() {
        is_out = !is_out;
    }
}
