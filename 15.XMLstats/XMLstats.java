import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLstats {
    public static void main(String[] args) {
        // Counting Pitch and Duration stats
        int [] pitchStats = {0,0,0,0,0,0,0}; 

        try {
            // Opens the XML document
            // Steps:
            // * Document Builder Factory
            // * Document Builder
            // * File
            // * Document (parsed)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File("gounod_ave_maria.xml");
            Document doc = builder.parse(file);

            
            // Altezze
            // Steps:
            // * getElemtentsByTagName -> NodeList
            // * for NodeList -> Node -> Element
            // * getAttributeNode -> String
            NodeList pitchs = doc.getElementsByTagName("pitch");
            for (int i=0; i<pitchs.getLength(); i++) {
                Element pitch = (Element) pitchs.item(i);
                String attribute = pitch.getAttribute("step");
                int note = attribute.toLowerCase().charAt(0) - 'A';

                // Counts an occurrence
                pitchStats[note]++;
            }
        
        } catch (SAXException|IOException|ParserConfigurationException e) {
            System.out.println("Exception... meh");
            System.exit(1);
        }
    }
}
