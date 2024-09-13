package run;

import java.util.ArrayList;
import java.util.Random;

import controller.RaceController;
import model.Athlete;
import model.Race;
import xmlLectures.AthleteReaderXML;
import xmlLectures.RaceReaderXML;

public class Runner {

    public static void main(String[] args) {
        ArrayList<Athlete> athletes = AthleteReaderXML.xmlAthleteReader();
        ArrayList<Race> races = RaceReaderXML.xmlRaceReader();

        RaceController controller = new RaceController(athletes, races);

        for (Athlete athlete : athletes) {
            athlete.setMovementListener(controller);
        }
        controller.getMenu().setVisible(true);
    }
}
