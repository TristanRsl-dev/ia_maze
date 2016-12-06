package Interface;

import javax.swing.*;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class Window extends JFrame {
    public static final int WIDTH = 450;
    public static final int HEIGHT = 500;

    public Window(GamePanel gp) {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Maze 3.0");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gp);
        this.setVisible(true);
    }
}
