package Model;

import java.awt.image.BufferedImage;

/**
 * Created by trist_000 on 06/12/2016.
 */
public class ImgElt {
    private BufferedImage pic;
    private int pich;
    private int picw;

    public BufferedImage getPic() {
        return pic;
    }

    public int getPicW() {
        return picw;
    }

    public int getPicH() {
        return pich;
    }

    public void setPic(BufferedImage pic) {
        this.pic = pic;
    }

    public void setPicW(int picw) {
        this.picw = picw;
    }

    public void setPicH(int pich) {
        this.pich = pich;
    }
}
