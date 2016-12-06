package Interface;

import Game.Perform;

import javax.swing.*;
import java.awt.*;

/**
 * Created by trist_000 on 06/12/2016.
 */
public class Stats extends JPanel {
    public Stats() {
        this.setSize(450, 50);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawString("Performance: " + Integer.toString(Perform.getPerf()), 20, 15);
        g2.drawString("Roches utilisées: " + Integer.toString(Perform.getRock()), 200, 15);
    }
}
