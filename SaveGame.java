package Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Championship;

public class SaveGame {

    public static void saveGame(Championship championship, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(championship);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}