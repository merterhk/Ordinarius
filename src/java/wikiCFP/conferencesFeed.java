package wikiCFP;

import MashUp.XMLMash;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class conferencesFeed {

    WikiCFP wc = new WikiCFP();

    public String conferenceJSON(String cat) {
        try {
            String json = "{";
            Document doc = new XMLMash().readXML("http://www.wikicfp.com/cfp/rss?cat=" + cat);//mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            json += "root:[";
            boolean virgul = false;
            NodeList tnodelist = doc.getElementsByTagName("title");
            NodeList lnodelist = doc.getElementsByTagName("link");
            NodeList dnodelist = doc.getElementsByTagName("description");

            for (int i = 1; i < tnodelist.getLength(); i++) {
                Node tn = tnodelist.item(i);
                Node dn = dnodelist.item(i);
                Node ln = lnodelist.item(i);
                json += (virgul) ? "," : "";
                json += "{'title':'" + tn.getTextContent()+ "','link':'" + ln.getTextContent()+ "','description':'" + dn.getTextContent()+ "'}\n";
                virgul = true;
//                System.out.println(n.getChildNodes().item(1).getNodeName());
            }
            json += "]";
            json += ",";
            json += "total:" + tnodelist.getLength();
            json += "}";

            return json;
        } catch (Exception ex) {
            System.out.println("XML Parser Error." + ex.getMessage());
            return ex.getMessage();
        }
    }

    public static void main(String[] args) {
        conferencesFeed c = new conferencesFeed();
        System.out.println(c.conferenceJSON("AI"));
    }
}
