package MashUp;

import Database.Vt;
import Tools.WordCount.WordFormat;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WikiMash {

    MashIt mi = new MashIt();
    XMLMash xmash = new XMLMash();
    Vt vt = new Vt();

    void stockThis(String key, ArrayList<String> ali) {

        for (String s : ali) {
            try {
                vt.execute("insert ignore into wikicache (kelime,link) values ('" +  WordFormat.format(key) + "','" + WordFormat.format(s) + "')");
            } catch (Exception e) {
                System.out.println("insert error. " + s);
            }
        }

    }

    public boolean wikiCheck(String sub) {
        if (sub.indexOf(':') >= 0) {
            return false;
        }
        if (sub.indexOf('>') >= 0) {
            return false;
        }
        if (sub.indexOf('!') >= 0) {
            return false;
        }
        if (sub.indexOf("Main_Page") >= 0) {
            return false;
        }
        return true;
    }

    public ArrayList<String> elek(ArrayList<String> ali) {
        ArrayList<String> nali = new ArrayList<String>();
        boolean var = false;
        for (String a : ali) {
            for (String b : nali) {
                if (a.equals(b)) {
                    var = true;
                    break;
                }
            }
            if (!var) {
                nali.add(a);
            }
            var = false;

        }
        return nali;
    }

    public ArrayList<String> wikiMash(String subject) {
        ArrayList<String> ali = new ArrayList<String>();
        subject = subject.replace(' ', '_');
        subject = subject.charAt(0) + "".toUpperCase() + subject.substring(1, subject.length());
        try {
            String source = mi.mashIt("http://en.wikipedia.org/wiki/" + subject);
            /* Eğer konu bulunamamış ve Computing kategorisinde öneriyorsa */
            if (source.contains("id=\"Computing\">Computing</span></h2>")) {

                vt.execute("delete from wikicfp where category like '" + subject + "'");

                String sub2 = source.substring(source.indexOf("id=\"Computing\">Computing</span></h2>"));
                sub2 = source.substring(0, source.indexOf("</ul>"));
                String[] linksplit2 = source.split("<a href=\"/wiki/");
                for (String link : linksplit2) {
                    String sub = link.substring(0, link.indexOf("\""));
                    sub = sub.replace("_", " ").toLowerCase();
                    // wikiCFP'ye ekle, başka anlamları varmış.
                    vt.execute("insert into wikicfp (category) values ('" + subject + "')");
                }
            }
            /* ^Ekleme son */
            String[] linksplit = source.split("<a href=\"/wiki/");

            for (String link : linksplit) {
                String sub = link.substring(0, link.indexOf("\""));
                sub = sub.replace("_", " ").toLowerCase();
                if (wikiCheck(sub)) {
//                    System.out.println(sub);
                    ali.add(sub);
                }
            }

        } catch (Exception e) {
            System.out.println("Wikipedia mash error. " + e.getLocalizedMessage());
            return null;
        }
        return elek(ali);
    }

    public ArrayList<String> wikiMashLink(String subject) {
        ArrayList<String> ali = new ArrayList<String>();
        subject = subject.replace(' ', '_');
        subject = subject.charAt(0) + "".toUpperCase() + subject.substring(1, subject.length());
        try {
            String source = mi.mashIt("http://en.wikipedia.org/wiki/" + subject);

            String[] linksplit = source.split("<a href=\"/wiki/");

            for (String link : linksplit) {
                String sub = link.substring(0, link.indexOf("\""));
                if (wikiCheck(sub)) {
                    // System.out.println(sub);
                    ali.add(sub);
                }
            }

        } catch (Exception e) {
            System.out.println("Wikipedia mash error. " + e.getLocalizedMessage());
            return null;
        }
        return elek(ali);
    }

    public ArrayList<String> wikiApiLink(String key, String param) {
//        Document doc = new XMLMash().readXML("http://en.wikipedia.org/w/api.php?action=query&prop=links&titles=" + key + "&pllimit=500&format=xml" + param);
//        doc.normalize();
//        NodeList links = doc.getElementsByTagName("pl");
//        ArrayList<String> linkia = new ArrayList<String>();
//        for (int i = 0; i < links.getLength(); i++) {
//            Node n = links.item(i);
//            if (n.getAttributes().getNamedItem("ns").getNodeValue().equals("0")) {
//                linkia.add(n.getAttributes().getNamedItem("title").getNodeValue());
//            }
//        }
//        return linkia;
        return wikiApiLink(key, param, 200);
    }

    public ArrayList<String> wikiApiLink(String key, String param, int limit) {
        if (key.contains("(")) {
//            System.out.println(key);
            key = key.substring(0, key.indexOf("(") - 1);
        }

        Document doc = new XMLMash().readXML("http://en.wikipedia.org/w/api.php?action=query&prop=links&titles=" + key + "&pllimit=" + limit + "&format=xml" + param);
        doc.normalize();
        NodeList links = doc.getElementsByTagName("pl");
        ArrayList<String> linkia = new ArrayList<String>();
        String s = "";
        for (int i = 0; i < links.getLength(); i++) {
            Node n = links.item(i);
            s = n.getAttributes().getNamedItem("title").getNodeValue();
            if (n.getAttributes().getNamedItem("ns").getNodeValue().equals("0")) {

                linkia.add(WordFormat.format(s));
            }
        }
        stockThis(key, linkia);
        return linkia;
    }

    public String wikiApiNormalize(String key) {
        Document doc = new XMLMash().readXML("http://en.wikipedia.org/w/api.php?action=opensearch&search=" + key + "&format=xml&limit=1");
        doc.normalize();
        NodeList links = doc.getElementsByTagName("Text");
//        System.out.println(links.getLength());
//        System.out.println(links.item(0).getTextContent());
        if (links.getLength() > 0) {
            return links.item(0).getTextContent();
        }

        return key;
    }

//    public ArrayList<String> wikiLinks(String key){
//    //http://en.wikipedia.org/w/api.php?action=query&prop=links&titles=genetic%20algorithm
//        Document doc = xmash.readXML(key);
//        doc.normalize();
//
//    }
    public synchronized void zincirliKuyu(String subject) {

        ArrayList<String> ali = wikiMash(subject);
        String baglanti = "";
        for (String s : ali) {
            baglanti += s + ",";
            //System.out.println(s);
        }
        //System.out.println(subject + " : " + baglanti);
        try {
            if (!vt.executeQuery("select kelime from wikipedia where kelime like '" + subject + "'").next()) {
                vt.execute("insert into wikipedia (kelime,baglanti) values ('" + subject + "','" + baglanti + "')");
            }
        } catch (Exception ex) {
            System.out.println("ZincirliKuyu DB Err..");
        }


    }

    public static void main(String[] args) {
        WikiMash wm = new WikiMash();
        System.out.println(wm.wikiApiNormalize("genetic algorithm"));
//        ArrayList<String> ali = wm.wikiMash("genetic algorithm");

//        for (String s : ali) {
//            wm.zincirliKuyu(s);
//        }

        //wm.zincirliKuyu("Genetic_algorithm");

        //System.out.println(mi.mashIt("http://en.wikipedia.org/wiki/Genetic_algorithm"));
    }
}
