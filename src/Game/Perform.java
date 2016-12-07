package Game;

/**
 * Created by trist_000 on 06/12/2016.
 */
public class Perform {
    private static int perf = 0;
    private static int rock = 0;

    public static int getPerf() {
        return perf;
    }

    public static int getRock() {
        return rock;
    }

    public static void changePerf(int a) {
        perf += a;
    }

    public static void incRock() {
        rock++;
        changePerf(-10);
    }
}
