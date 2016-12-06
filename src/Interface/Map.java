package Interface;

import Model.*;
import Tools.ParseMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class Map extends JPanel {
    private MapInfo map_infos;
    private Player player;
    private ParseMap parse_map;

    private ImgElt monster;
    private ImgElt hole;
    private ImgElt gate;
    private ImgElt smoke;
    private ImgElt wind;

    private ArrayList tiles_pics;

    public Map() {
        this.setSize(450, 450);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setDoubleBuffered(true);

        map_infos = MapInfo.getInstance();
        player = Player.getInstance();
        parse_map = ParseMap.getInstance();

        monster = new ImgElt();
        hole = new ImgElt();
        gate = new ImgElt();
        smoke = new ImgElt();
        wind = new ImgElt();

        tiles_pics = new ArrayList();
        LoadPic();
    }

    public void create(String filename) {
        parse_map.setFilename(filename);
        parse_map.create();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        int size_tile = 450 / map_infos.getSize().getX();
        for (int i = 0; i < map_infos.getSize().getX(); ++i) {
            g2.drawLine(0, i * size_tile, Window.WIDTH, i * size_tile);
            g2.drawLine(i * size_tile, 0, i * size_tile, Window.HEIGHT);
        }

        // Scale player
        float scale = 1 / ((float) player.getPicW() / size_tile);
        player.setPicW((int) (player.getPicW() * scale));
        player.setPicH((int) (player.getPicH() * scale));
        player.setPic(scalePic(player.getPic(), player.getPicW(), player.getPicH(), scale));

        // Scale monsters
        scale = 1 / ((float) monster.getPicW() / size_tile);
        monster.setPicW((int) (monster.getPicW() * scale));
        monster.setPicH((int) (monster.getPicH() * scale));
        monster.setPic(scalePic(monster.getPic(), monster.getPicW(), monster.getPicH(), scale));

        // Scale hole
        scale = 1 / ((float) hole.getPicW() / size_tile);
        hole.setPicW((int) (hole.getPicW() * scale));
        hole.setPicH((int) (hole.getPicH() * scale));
        hole.setPic(scalePic(hole.getPic(), hole.getPicW(), hole.getPicH(), scale));

        // Scale gate
        scale = 1 / ((float) gate.getPicW() / size_tile);
        gate.setPicW((int) (gate.getPicW() * scale));
        gate.setPicH((int) (gate.getPicH() * scale));
        gate.setPic(scalePic(gate.getPic(), gate.getPicW(), gate.getPicH(), scale));

        ArrayList<MapElt> map_elts = parse_map.getMap();
        for (int i = 0; i < map_elts.size(); ++i) {
            if (map_elts.get(i).getType().equals("monster"))
                g2.drawImage(monster.getPic(), map_elts.get(i).getPos().getX() * size_tile,
                        map_elts.get(i).getPos().getY() * size_tile, null);
            else if (map_elts.get(i).getType().equals("hole"))
                g2.drawImage(hole.getPic(), map_elts.get(i).getPos().getX() * size_tile,
                        map_elts.get(i).getPos().getY() * size_tile, null);
            else if (map_elts.get(i).getType().equals("out"))
                g2.drawImage(gate.getPic(), map_elts.get(i).getPos().getX() * size_tile,
                        map_elts.get(i).getPos().getY() * size_tile, null);
        }

        // Scale smoke
        scale = 1 / ((float) smoke.getPicW() / size_tile);
        smoke.setPicW((int) (smoke.getPicW() * scale));
        smoke.setPicH((int) (smoke.getPicH() * scale));
        smoke.setPic(scalePic(smoke.getPic(), smoke.getPicW(), smoke.getPicH(), scale));

        // Scale wind
        scale = 1 / ((float) wind.getPicW() / size_tile);
        wind.setPicW((int) (wind.getPicW() * scale));
        wind.setPicH((int) (wind.getPicH() * scale));
        wind.setPic(scalePic(wind.getPic(), wind.getPicW(), wind.getPicH(), scale));

        ArrayList<MapElt> map_sensors = parse_map.getMapSensors();
        for (int i = 0; i < map_sensors.size(); ++i) {
            if (map_sensors.get(i).getType().equals("smoke"))
                g2.drawImage(smoke.getPic(), map_sensors.get(i).getPos().getX() * size_tile,
                    map_sensors.get(i).getPos().getY() * size_tile, null);
            else if (map_sensors.get(i).getType().equals("wind"))
                g2.drawImage(wind.getPic(), map_sensors.get(i).getPos().getX() * size_tile,
                    map_sensors.get(i).getPos().getY() * size_tile, null);
        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(player.getPic(), player.getPos().getX() * size_tile, player.getPos().getY() * size_tile, null);
    }

    private void LoadPic() {
        try {
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("link.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("zelda.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("hole.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("gate.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("smoke.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("wind.png")));

            player.setPic((BufferedImage) tiles_pics.get(0));
            player.setPicW(player.getPic().getWidth());
            player.setPicH(player.getPic().getHeight());

            monster.setPic((BufferedImage) tiles_pics.get(1));
            monster.setPicW(monster.getPic().getWidth());
            monster.setPicH(monster.getPic().getHeight());

            hole.setPic((BufferedImage) tiles_pics.get(2));
            hole.setPicW(hole.getPic().getWidth());
            hole.setPicH(hole.getPic().getHeight());

            gate.setPic((BufferedImage) tiles_pics.get(3));
            gate.setPicW(gate.getPic().getWidth());
            gate.setPicH(gate.getPic().getHeight());

            smoke.setPic((BufferedImage) tiles_pics.get(4));
            smoke.setPicW(smoke.getPic().getWidth());
            smoke.setPicH(smoke.getPic().getHeight());

            wind.setPic((BufferedImage) tiles_pics.get(5));
            wind.setPicW(wind.getPic().getWidth());
            wind.setPicH(wind.getPic().getHeight());
        } catch (Exception e) {
            System.err.println("Impossible to load pictures: " + e.toString());
        }
    }

    private BufferedImage scalePic(BufferedImage src, int w, int h, float scale) {
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = atop.filter(src, after);
        return after;
    }
}
