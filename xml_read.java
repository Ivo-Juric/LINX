
import java.io.*;
import java.text.DateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.util.*;

public class xml_read {
	
	public static void main(String[] args) {
		
		List<Athlete> athlets=new ArrayList<>();
		
		try {
			
			final DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			final DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document document=docBuilder.parse("triatlon.xml");
			document.getDocumentElement().normalize();
			
			NodeList nodelist=document.getElementsByTagName("atleta");
			for (int i=0;i<nodelist.getLength();i++) {
				
				Node node=nodelist.item(i);
				
				if (node.getNodeType()==Node.ELEMENT_NODE) {
					Element element=(Element)node;
					
					String number = element.getAttribute("numero");
					String surname = element.getElementsByTagName("apellido").item(0).getTextContent();
					String name = element.getElementsByTagName("nombre").item(0).getTextContent();
					String id = element.getElementsByTagName("dni").item(0).getTextContent();
					String nationality=element.getElementsByTagName("nacionalidad").item(0).getTextContent();
					Date birthdate= DateFormat.parse(element.getElementsByTagName("apellido").item(0).getTextContent());
					String gender = element.getElementsByTagName("genero").item(0).getTextContent();
					String category=element.getElementsByTagName("categoria").item(0).getTextContent();
					Athlete a = new Athlete(surname,name,id,nationality,birthdate,gender,category);
					athlets.add(a);
				}
			}

		}
		catch (Exception e){}
	}	
}
