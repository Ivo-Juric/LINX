package xmlLectures;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import model.Athlete;
import model.PhysicalCondition;

public class AthleteReaderXML{
    public static ArrayList<Athlete> xmlAthleteReader() {
        List<Athlete> athletes = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            final DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            final DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document document = docBuilder.parse("C:\\Users\\Nicolas\\Desktop\\Facultad\\Programacion B\\triatlon.xml");
            document.getDocumentElement().normalize();
            NodeList nodelist = document.getElementsByTagName("atleta");

            for (int i = 0; i < nodelist.getLength(); i++) {
                Node node = nodelist.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String number = element.getAttribute("numero");
                    String surname = element.getElementsByTagName("apellido").item(0).getTextContent();
                    String name = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String id = element.getElementsByTagName("dni").item(0).getTextContent();
                    String nationality = element.getElementsByTagName("nacionalidad").item(0).getTextContent();
                    Date birthdate = dateFormat.parse(element.getElementsByTagName("fechaNacimiento").item(0).getTextContent());
                    String gender = element.getElementsByTagName("genero").item(0).getTextContent();
                    String category = element.getElementsByTagName("categoria").item(0).getTextContent();
                    double weight = Double.parseDouble(element.getElementsByTagName("peso").item(0).getTextContent());
                    double height = Double.parseDouble(element.getElementsByTagName("altura").item(0).getTextContent());
                    int finishedRaces = Integer.parseInt(element.getElementsByTagName("porcentajeCarrerasTerminadas").item(0).getTextContent());
                    double economicBudget = Double.parseDouble(element.getElementsByTagName("presupuestoEconomico").item(0).getTextContent());
                    int ranking = Integer.parseInt(element.getElementsByTagName("ranking").item(0).getTextContent());

                    Element condicionesFisicasElement = (Element) element.getElementsByTagName("condicionesFisicas").item(0);
                    double swimmingFitness = Double.parseDouble(condicionesFisicasElement.getElementsByTagName("aptitudNatacion").item(0).getTextContent());
                    double cyclingFitness = Double.parseDouble(condicionesFisicasElement.getElementsByTagName("aptitudCiclismo").item(0).getTextContent());
                    double pedestrianFitness = Double.parseDouble(condicionesFisicasElement.getElementsByTagName("aptitudPedestrismo").item(0).getTextContent());
                    double resistance = Double.parseDouble(condicionesFisicasElement.getElementsByTagName("resistencia").item(0).getTextContent());
                    double psychologicalStrength = Double.parseDouble(condicionesFisicasElement.getElementsByTagName("fortalezaPsicologica").item(0).getTextContent());

                    PhysicalCondition physicalCondition = new PhysicalCondition (swimmingFitness, cyclingFitness, pedestrianFitness, resistance, psychologicalStrength);
                    Athlete athlete = new Athlete(number, surname, name, id, nationality, birthdate, gender, category, weight, height, finishedRaces, economicBudget, ranking, physicalCondition);
                    athlete.getPhysicalCondition().setInitialSpeed(athlete);
                    athletes.add(athlete);
                }
            }
        }  
        catch (Exception e) {
            e.printStackTrace();
        }
		return (ArrayList<Athlete>) athletes;
    }
}
