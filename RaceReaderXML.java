package xmlLectures;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.DataBaseCC;
import model.City;
import model.Country;
import model.Cycling;
import model.DistanceDiscipline;
import model.Drink;
import model.Food;
import model.Modality;
import model.Pedestrianism;
import model.Provisioning;
import model.Race;
import model.Swimming;

public class RaceReaderXML {
    public static ArrayList<Race> xmlRaceReader() {
        ArrayList<Race> races = new ArrayList<>();
        try {
            File inputFile = new File("C:\\Users\\Ivo\\eclipse-workspace\\LINX\\triatlon.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("carrera");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String ciudad = eElement.getElementsByTagName("ciudad").item(0).getTextContent();
                    String pais = eElement.getElementsByTagName("pais").item(0).getTextContent();
                    String fecha = eElement.getElementsByTagName("fecha").item(0).getTextContent();
                    String modalidadDesc = eElement.getElementsByTagName("modalidad").item(0).getTextContent();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date raceDate = sdf.parse(fecha);
                    City city = new City(ciudad, new Country(pais));
                    Modality modality = new Modality(modalidadDesc);	
                    String distanceSwimming = eElement.getElementsByTagName("natacion").item(0).getTextContent();
                    DistanceDiscipline	distanceDisciplineSwimming= new DistanceDiscipline(Float.parseFloat(distanceSwimming), new Swimming("Natación"));
                    modality.addDiscipline(distanceDisciplineSwimming);
                    String distanceCycling = eElement.getElementsByTagName("ciclismo").item(0).getTextContent();
                    DistanceDiscipline	distanceDisciplineCycling = new DistanceDiscipline(Float.parseFloat(distanceCycling), new Cycling("Ciclismo"));
                    modality.addDiscipline(distanceDisciplineCycling);
                    String distancePedestrianism = eElement.getElementsByTagName("pedestrismo").item(0).getTextContent();
                    DistanceDiscipline	distanceDisciplinePedestrianism = new DistanceDiscipline(Float.parseFloat(distancePedestrianism), new Pedestrianism("Pedestrismo"));
                    modality.addDiscipline(distanceDisciplinePedestrianism);
                    List<Provisioning> provisioning = parseProvisioning(eElement);
                    DataBaseCC.getConnection();
                    Race race = new Race(raceDate, modality, city, provisioning);
                    races.add(race);
                    
                }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
		return races;
    }
    private static List<Provisioning> parseProvisioning(Element eElement) {
        NodeList provisioningList = eElement.getElementsByTagName("puesto"); 
        List<Provisioning> provisioning = new ArrayList<>();

        for (int i = 0; i < provisioningList.getLength(); i++) {
            Element provisioningElement = (Element) provisioningList.item(i);
            String type = provisioningElement.getAttribute("tipo");
            float distanceKm = Float.parseFloat(provisioningElement.getElementsByTagName("distancia").item(0).getTextContent());
            
            Food food = new Food(300); 
            Drink drink = new Drink(300);
            Provisioning prov = new Provisioning(distanceKm, food, drink, type);
            provisioning.add(prov);
        }

        return provisioning;
    }

}