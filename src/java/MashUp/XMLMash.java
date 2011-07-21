/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MashUp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author Merter
 */
public class XMLMash {

    public Document readXML(String link) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = (Document) db.parse(link);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception ex) {
            System.out.println("XML Reader error");
            return null;
        }
    }
}
