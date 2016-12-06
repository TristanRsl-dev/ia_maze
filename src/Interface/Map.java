package Interface;

import Game.GameManager;
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

    private Monster monster;
    private Hole hole;
    private Gate gate;

    private ArrayList tiles_pics;

    public Map() {
        this.setSize(450, 450);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setDoubleBuffered(true);

        map_infos = MapInfo.getInstance();
        player = Player.getInstance();
        parse_map = ParseMap.getInstance();

        monster = new Monster();
        hole = new Hole();
        gate = new Gate();

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

        // Scale gate

        ArrayList<MapElt> map_elts = parse_map.getMap();
        for (int i = 0; i < map_elts.size(); ++i) {
            if (map_elts.get(i).getType().equals("monster"))
                g2.drawImage(monster.getPic(), map_elts.get(i).getPos().getX() * size_tile,
                        map_elts.get(i).getPos().getY() * size_tile, null);
            else if (map_elts.get(i).getType().equals("hole"))
                g2.drawImage((BufferedImage) tiles_pics.get(2), map_elts.get(i).getPos().getX() * size_tile,
                        map_elts.get(i).getPos().getY() * size_tile, null);
            else if (map_elts.get(i).getType().equals("out"))
                g2.drawImage((BufferedImage) tiles_pics.get(3), map_elts.get(i).getPos().getX() * size_tile,
                        map_elts.get(i).getPos().getY() * size_tile, null);
        }

       /*for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                Tile tile = (Tile)list.get(j).get(i);
                if (tile.getType() == TileType.TileTypes.wall) {
                    for (int k = 0; k < 150; ++k)
                        g2.drawLine(i*aspiro.getBox().width + k, j*aspiro.getBox().height,
                                i*aspiro.getBox().width + k, (j + 1)*aspiro.getBox().height);
                }
                else if (tile.getType() == TileType.TileTypes.dirt) {
                    g2.drawImage((BufferedImage)tiles_pics.get(0), i*aspiro.getBox().width,
                            j*aspiro.getBox().height, null);
                    Color c = g2.getColor();
                    g2.setColor(Color.RED);
                    g2.drawString(Integer.toString(tile.getQuantity()), i*aspiro.getBox().width + 25,
                            j*aspiro.getBox().height + 15);
                    g2.setColor(c);
                }
                else if (tile.getType() == TileType.TileTypes.jewelry) {
                    g2.drawImage((BufferedImage)tiles_pics.get(1), i*aspiro.getBox().width,
                            j*aspiro.getBox().height, null);
                }
                else if (tile.getType() == TileType.TileTypes.dirtjewelry) {
                    g2.drawImage((BufferedImage)tiles_pics.get(0), i*aspiro.getBox().width,
                            j*aspiro.getBox().height, null);
                    g2.drawImage((BufferedImage)tiles_pics.get(1), i*aspiro.getBox().width,
                            j*aspiro.getBox().height, null);
                    Color c = g2.getColor();
                    g2.setColor(Color.RED);
                    g2.drawString(Integer.toString(tile.getQuantity()), i*aspiro.getBox().width + 25,
                            j*aspiro.getBox().height + 15);
                    g2.setColor(c);
                }
            }
        }*/

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(player.getPic(), player.getPos().getX(), player.getPos().getY(), null);
    }

    private void LoadPic() {
        try {
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("link.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("zelda.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("hole.png")));
            tiles_pics.add(ImageIO.read(getClass().getClassLoader().getResourceAsStream("gate.png")));

            player.setPic((BufferedImage) tiles_pics.get(0));
            player.setPicW(player.getPic().getWidth());
            player.setPicH(player.getPic().getHeight());

            monster.setPic((BufferedImage) tiles_pics.get(1));
            monster.setPicW(monster.getPic().getWidth());
            monster.setPicH(monster.getPic().getHeight());
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
