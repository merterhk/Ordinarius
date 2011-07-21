package wikiCFP;

import Database.Vt;
import MashUp.WikiMash;
import MashUp.XMLMash;
import java.sql.ResultSet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Wiki_cfp2pedia {

    Vt vt = new Vt();
    WikiMash pedia = new WikiMash();

    public NodeList loadFromWikiApi(String key, String param) {
        Document doc = new XMLMash().readXML("http://en.wikipedia.org/w/api.php?action=query&prop=links&titles=" + key + "&pllimit=500&format=xml" + param);
        doc.normalize();
        NodeList links = doc.getElementsByTagName("pl");
        return links;
    }

//// <editor-fold defaultstate="collapsed" desc="Eski update metodu">
//    public void updateContents() {
//        try {
//            ResultSet rs = vt.executeQuery("select category from wikicfp");
//            while (rs.next()) {
//                String subject = rs.getString("category");
//                System.out.println(subject);
//                try {
//                    ArrayList<String> ali = pedia.wikiMash(subject);
//                    for (String s : ali) {
//                        s = s.replace('½', ' ');
//                        s = s.replace('%', ' ');
//                        s = s.replace('&', ' ');
//                        s = s.replace('#', ' ');
//                        s = s.replace('_', ' ');
//                        s = s.charAt(0) + "".toUpperCase() + s.substring(1);
////                        if (!vt.executeQuery("select kelime from wikipedia where kelime like '" + subject + "'").next()) {
//                        new Vt().execute("insert into wikipedia (kelime,baglanti) values ('" + subject + "','" + s + "')");
////                        }
//                    }
//                } catch (Exception e) {
//                    System.out.println("cfp2pedia Err");
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("updateContents error.." + e.getLocalizedMessage());
//        }
//    }// </editor-fold>
    public void updateWithApi() {
        try {
            ResultSet rs = vt.executeQuery("select category from wikicfp");
            while (rs.next()) {
                String subject = rs.getString("category");
                System.out.println(subject);
                try {
                    NodeList links = loadFromWikiApi(subject, "");
                    System.out.println("links:" + links.getLength());
                    for (int i = 0; i < links.getLength(); i++) {
                        Node link = links.item(i);
                        String title = link.getAttributes().getNamedItem("title").getNodeValue();
                        title = title.replace('\'', ' ');
                        title = title.replace('\"', ' ');
                        title = title.replace('+', ' ');
                        title = title.replace(':', ' ');
                        title = title.replace('@', ' ');
                        title = title.replace('%', ' ');
                        if (link.getAttributes().getNamedItem("ns").getNodeValue().equals("0")) {
//                        System.out.println(link.getAttributes().getNamedItem("title").getNodeValue());
                            new Vt().execute("insert into wikipedia (kelime,baglanti) values ('" + subject + "','" + title + "')");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("cfp2pedia Err");
                }
            }
        } catch (Exception e) {
            System.out.println("updateContents error.." + e.getLocalizedMessage());
        }
    }

    public void updateWithApi2() {
        try {
            ResultSet rs = vt.executeQuery("select category from wikipedia");
            while (rs.next()) {
                String subject = rs.getString("baglanti");
                System.out.println(subject);
                try {
                    NodeList links = loadFromWikiApi(subject, "");
                    System.out.println("links:" + links.getLength());
                    for (int i = 0; i < links.getLength(); i++) {
                        Node link = links.item(i);
                        String title = link.getAttributes().getNamedItem("title").getNodeValue();
                        title = title.replace('\'', ' ');
                        title = title.replace('\"', ' ');
                        title = title.replace('+', ' ');
                        title = title.replace(':', ' ');
                        title = title.replace('@', ' ');
                        title = title.replace('%', ' ');
                        if (link.getAttributes().getNamedItem("ns").getNodeValue().equals("0")) {
//                        System.out.println(link.getAttributes().getNamedItem("title").getNodeValue());
                            new Vt().execute("insert into wikipedia (kelime,baglanti) values ('" + subject + "','" + title + "')");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("cfp2pedia Err");
                }
            }
        } catch (Exception e) {
            System.out.println("updateContents error.." + e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        Wiki_cfp2pedia w = new Wiki_cfp2pedia();
//        w.updateContents();
        w.updateWithApi();
    }
}
