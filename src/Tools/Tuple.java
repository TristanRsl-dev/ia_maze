package Tools;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class Tuple {
    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Tuple tuple) {
        return x == tuple.getX() && y == tuple.getY();
    }
}
