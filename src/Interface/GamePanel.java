package Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class GamePanel extends JPanel {
    private Map map_panel;

    public GamePanel() {
        this.setRequestFocusEnabled(true);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        map_panel = new Map();
        this.add(map_panel);
        map_panel.setLocation(0, 0);
    }

    public void repaintGame() {
        map_panel.repaint();
    }
}
