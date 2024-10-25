package run;

import java.util.ArrayList;

import controller.RaceController;
import model.Athlete;
import model.Championship;
import model.Race;
import xmlLectures.AthleteReaderXML;
import xmlLectures.RaceReaderXML;

public class Runner {

    public static void main(String[] args) {
        ArrayList<Athlete> athletes = AthleteReaderXML.xmlAthleteReader();
        ArrayList<Race> races = RaceReaderXML.xmlRaceReader();
        Championship championship = new Championship();
        RaceController controller = new RaceController(athletes, races, championship);
        controller.getMenu().setVisible(true);
    }
}
