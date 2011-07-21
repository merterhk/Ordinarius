package DBLP;

import MashUp.MashIt;
import MashUp.XMLMash;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DBLPSearch {

    MashIt mash = new MashIt();

    public String search(String s) {
        try {
            String json = "{";
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/search/author/?xauthor=" + s);//mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            json += "root:[";
            boolean virgul = false;
            NodeList nodelist = doc.getElementsByTagName("author");
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node n = nodelist.item(i);
                json += (virgul) ? "," : "";
                json += "{'name':'" + n.getTextContent() + "','url':'" + n.getAttributes().getNamedItem("urlpt").getNodeValue() + "'}";
                virgul = true;
            }
            json += "]";
            json += ",";
            json += "total:" + nodelist.getLength();
            json += "}";

            return json;
        } catch (Exception ex) {
            System.out.println("XML Parser Error." + ex.getMessage());
            return ex.getMessage();
        }

    }

    public ArrayList<String> publications(String link) {
        ArrayList<String> ali = new ArrayList<String>();
        String json = "";
        boolean virgul = false;
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/pers/" + link + "/xk"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("dblpkey");
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node n = nodelist.item(i);
                System.out.println(n.getTextContent());
                ali.add(n.getTextContent());
            }

            return ali;
        } catch (Exception ex) {
            System.out.println("Publications error..");
            return ali;
        }
    }

    public String publication(String link) {
        //http://dblp.uni-trier.de/rec/bibtex/key.xml
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/bibtex/" + link + ".xml"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("dblpkey");
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node n = nodelist.item(i);
                System.out.println(n.getTextContent());
                //ali.add(n.getTextContent());
            }


        } catch (Exception ex) {
            System.out.println("Publications error..");

        }
        return "";
    }

    public String publicationsJSON(String urlpt) {

        return "";
    }

    public static void main(String[] args) {
        DBLPSearch d = new DBLPSearch();
        //System.out.println(d.search("mehmet kaya"));
        System.out.println(d.publications("k/Kaya:Mehmet"));
    }
}
