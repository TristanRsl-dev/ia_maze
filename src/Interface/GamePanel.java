package Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class GamePanel extends JPanel {
    private Map map_panel;
    private Stats stats_panel;

    public GamePanel() {
        this.setRequestFocusEnabled(true);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        map_panel = new Map();
        map_panel.create("Resources/map.conf");
        this.add(map_panel);
        map_panel.setLocation(0, 0);

        stats_panel = new Stats();
        this.add(stats_panel);
        stats_panel.setLocation(0, 450);
    }

    public void update() {
        map_panel.update();
    }

    public void repaintGame() {
        map_panel.repaint();
        stats_panel.repaint();
    }
}
