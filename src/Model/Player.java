package Model;

import Tools.Tuple;

import java.awt.image.BufferedImage;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class Player {
    private static Player player;

    private Tuple pos;
    private BufferedImage pic;
    private int pic_width;
    private int pic_height;

    public static Player getInstance() {
        if (player == null)
            player = new Player();
        return player;
    }

    private Player() {
        pos = new Tuple(0, 0);
        pic_width = 0;
        pic_height = 0;
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
}
