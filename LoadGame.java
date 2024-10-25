package Serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.Championship;

public class LoadGame {
	public static Championship loadUserSettings(String filePath) {
    	Championship championship = new Championship();
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
        	championship =  (Championship) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return championship;
    }
}