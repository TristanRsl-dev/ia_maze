package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by trist_000 on 05/12/2016.
 */
public class FileManager {
    private String filename;
    private FileReader fr;

    public FileManager(String filename) {
        this.filename = filename;
        fr = null;
    }

    public void open() {
        File f = new File(filename);
        try {
            fr = new FileReader(f);
        } catch (Exception e) {
            System.err.println("Impossible to open your file: " + e.toString());
        }
    }

    public ArrayList<String> read() {
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> res = new ArrayList<>();
        try {
            String line;
            while ((line = br.readLine()) != null)
                res.add(line);
            br.close();
        } catch (Exception e) {
            System.err.println("Impossible to read your file: " + e.toString());
        }
        return res;
    }

    public void close() {
        try {
            fr.close();
        } catch (Exception e) {
            System.err.println("Impossible to close the FileReader: " + e.toString());
        }
    }
}
