package Interface;

import Model.MapInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class Map extends JPanel {
    private MapInfo map_infos;
    private ArrayList tiles_pics;

    public Map() {
        this.setSize(700, 450);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setDoubleBuffered(true);

        map_infos = MapInfo.getInstance();
        tiles_pics = new ArrayList();
        LoadPic();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        int size_tile = 700 / map_infos.getSize().getX();
        for (int i = 0; i < map_infos.getSize().getX(); ++i) {
            g2.drawLine(0, i * size_tile, Window.WIDTH, i * size_tile);
            g2.drawLine(i * size_tile, 0, i * size_tile, Window.HEIGHT);
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
        //g2.drawImage(aspiro.getPic(), aspiro.getPosX(), aspiro.getPosY(), null);
    }

    private void LoadPic() {
        try {
            //tiles_pics.add(ImageIO.read(getClass().getResource("../Resources/tile_dirt.png")));
        } catch (Exception e) {
            System.err.println("Impossible to load pictures: " + e.toString());
        }
    }
}
