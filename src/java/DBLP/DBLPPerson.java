package DBLP;

import OBeans.Coauthor;
import Database.Vt;
import MashUp.MashIt;
import MashUp.XMLMash;
import OBeans.Publication;
import Tools.WordCount.Counter;
import Tools.WordCount.Word;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DBLPPerson {

    MashIt mash = new MashIt();
    Vt vt = new Vt();
    // Kategori Listeleri
    ArrayList<Word> kats = new ArrayList<Word>();
//    ArrayList<Word> kats1 = new ArrayList<Word>();
//    ArrayList<Word> kats2 = new ArrayList<Word>();
//    ArrayList<Word> kats3 = new ArrayList<Word>();
//    ArrayList<Word> kats4 = new ArrayList<Word>();
    ArrayList<Word> mostsignificantwords = new ArrayList<Word>();

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

    public ArrayList<String> publications(String link) {
        ArrayList<String> ali = new ArrayList<String>();
        String json = "";
        boolean virgul = false;
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/pers/" + link + "/xk"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("dblpkey");
            for (int i = 1; i < nodelist.getLength(); i++) {
                Node n = nodelist.item(i);
//                System.out.println(n.getTextContent());
                ali.add(n.getTextContent());
            }

            return ali;
        } catch (Exception ex) {
            System.out.println("Publications error..");
            return ali;
        }
    }

    public ArrayList<Publication> publicationsList(String link) {
        ArrayList<Publication> ali = new ArrayList<Publication>();
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/pers/" + link + "/xk"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("dblpkey");
            for (int i = 1; i < nodelist.getLength(); i++) { ////<---------1'e dikkat...
                Node n = nodelist.item(i);
                ali.add(pubLoad(n.getTextContent()));
            }

            return ali;
        } catch (Exception ex) {
            System.out.println("Publications error..");
            return ali;
        }
    }

    public Publication pubLoad(String link) {
        Publication p = new Publication();
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/bibtex/" + link + ".xml"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            NodeList title = doc.getElementsByTagName("title");
            NodeList year = doc.getElementsByTagName("year");
            NodeList author = doc.getElementsByTagName("author");
            NodeList journal;

            try {
                journal = doc.getElementsByTagName("journal");
                p.setJournal(journal.item(0).getTextContent());
            } catch (Exception e) {
                journal = doc.getElementsByTagName("booktitle");
                p.setBooktitle(journal.item(0).getTextContent());
            }
            p.setTitle(title.item(0).getTextContent());
            p.setYear(year.item(0).getTextContent());
            p.setLink(link);

            String au = "";
            for (int i = 0; i < author.getLength(); i++) {
                Node n = author.item(i);
                au += n.getTextContent() + (i + 1 != author.getLength() ? ", " : "");
            }
            p.setCoauthors(au);

            return p;
        } catch (Exception ex) {
            System.out.println("PubBookTitle error..");
        }
        return p;
    }

    public String pubTitle(String link) {
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/bibtex/" + link + ".xml"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            NodeList nodelist = doc.getElementsByTagName("title");
            Node n = nodelist.item(0);
            //System.out.println(n.getTextContent());
            return n.getTextContent().replace("\"", "\\\"").replace('/', ' ');
        } catch (Exception ex) {
            System.out.println("PubTitle error..");
        }
        return "";
    }

    public String pubYear(String link) {
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/bibtex/" + link + ".xml"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            NodeList nodelist = doc.getElementsByTagName("year");
            Node n = nodelist.item(0);
            return n.getTextContent();
        } catch (Exception ex) {
            System.out.println("PubTitle error..");
        }
        return "";
    }

    public String pubJournal(String link) {
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/bibtex/" + link + ".xml"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            NodeList nodelist = doc.getElementsByTagName("journal");
            Node n = nodelist.item(0);
            return n.getTextContent();
        } catch (Exception ex) {
            System.out.println("PubJournal error..");
            return pubBookTitle(link);
        }
    }

    public String pubBookTitle(String link) {
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/bibtex/" + link + ".xml"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            NodeList nodelist = doc.getElementsByTagName("booktitle");
            Node n = nodelist.item(0);
            return n.getTextContent();
        } catch (Exception ex) {
            System.out.println("PubBookTitle error..");
        }
        return "";
    }

    public String pubJSON(String link) {

        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/bibtex/" + link + ".xml"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();
            NodeList title = doc.getElementsByTagName("title");
            NodeList year = doc.getElementsByTagName("year");
            NodeList author = doc.getElementsByTagName("author");
            NodeList journal;
            try {
                journal = doc.getElementsByTagName("journal");
            } catch (Exception e) {
                journal = doc.getElementsByTagName("booktitle");
            }

            String json = "{";
            json += "title:'" + title.item(0).getTextContent() + "',";
            json += "year:'" + year.item(0).getTextContent() + "',";
            // json += "journal:'" + journal.item(0).getTextContent() + "',";
            json += "authors:'";
            for (int i = 0; i < author.getLength(); i++) {
                Node n = author.item(i);
                json += "<a class=\"link\" onclick=\"searchThis(\\\'" + n.getTextContent() + "\\\');\">" + n.getTextContent() + "</a>" + (i + 1 != author.getLength() ? ", " : "");
            }
            json += "'";
            //json += "journal:'" + journal.item(0).getTextContent() + "'";
            json += "}";

            return json;
        } catch (Exception ex) {
            System.out.println("PubBookTitle error..");
        }
        return "";
    }

    public ArrayList<String> publicationAnalyze2(String title) {
        ArrayList<String> ali = new ArrayList<String>();
        String t = title.replace('.', ' ');
        t = title.replace(':', ' ');
        t = title.replace('-', ' ');
        t = title.replace(',', ' ');
        t = title.replace('\"', ' ');
        t = title.replace('\'', ' ');
        String[] kelimeler = t.split(" ");

        for (String string : kelimeler) {
            try {
                string = string.toLowerCase();

                ResultSet rs1 = vt.executeQuery("select * from wikipedia where kelime like '" + string + "'");
                while (rs1.next()) {
                    System.out.println(string + " - " + rs1.getString("kelime"));
                    ali.add(rs1.getString("kelime"));
                }

                ResultSet rs2 = vt.executeQuery("select * from wikipedia where baglanti like '%" + string + "%'");
                while (rs2.next()) {
                    System.out.println(string + " - " + rs2.getString("kelime"));
                    ali.add(rs2.getString("kelime"));
                }

            } catch (Exception e) {
                System.out.println("Publication Analyze error..");
            }
        }

        return elek(ali);
    }

    public ArrayList<Word> publicationAnalyzeFast(ArrayList<Publication> pubs) {
        //ArrayList<Word> wali = new ArrayList<Word>();
        Counter counter = new Counter();

        //counter.wali.clear();
        counter.count_one(pubs);
        counter.count_two(pubs);
        counter.count_tree(pubs);

//        Collections.sort(counter.wali, counter.compa);
//        for (int i = 0; i < 100; i++) {
//            try {
//                Word key = counter.wali.get(i);
//                ResultSet rs = vt.executeQuery("select baglanti from wikipedia where baglanti like lcase('" + key.word + "')");
//                if (rs.next()) {
//                    mostsignificantwords.add(key);
//                }
//            } catch (Exception e) {
//            }
//        }

        System.out.println("Kelimeler : " + counter.wali.size());
        //   int baraj = (counter.wali.size() / 100);

// <editor-fold defaultstate="collapsed" desc="1. yontem Kategoriler">
// 1. yontem kategori /////////////////////////
//        System.out.println("1. yontem kategoriler : ");
        for (Word w : counter.wali) {
            try {
                ResultSet rs1 = vt.executeQuery("select * from wikicfp where lcase(category) like lcase('" + w.word + "') or lcase(category) like lcase('" + w.word + " %')");
                while (rs1.next()) {
//                    counter.countThis(w.word, kats1);
                    counter.countThis(w.word, kats);
                    counter.countThis(w.word, mostsignificantwords);
                }
                rs1.close();
            } catch (Exception e) {
                System.out.println("1. yöntem uygulanamadı." + e.getLocalizedMessage());
            }
        }
//        Collections.sort(kats1, counter.compa);
//        for (Word w : kats1) {
////            System.out.println("\t" + w.word + " (" + w.count + ")");
//        }
        //////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="2. yontem Kategoriler">
// 2. yontem kategori /////////////////////////
//        System.out.println("2. yontem kategoriler : ");
        for (Word w : counter.wali) {
            try {
                ResultSet rs2 = vt.executeQuery("select * from wikipedia where lcase(baglanti) like lcase('" + w.word + "') or lcase(baglanti) like lcase('" + w.word + " %')");
                while (rs2.next()) {
//                    counter.countThis(rs2.getString("kelime"), kats2);
                    counter.countThis(rs2.getString("kelime"), kats);
                    counter.countThis(w.word, mostsignificantwords);
                }
                rs2.close();
            } catch (Exception e) {
                System.out.println("2. yöntem hata : " + e.getLocalizedMessage());
            }
        }
//        Collections.sort(kats2, counter.compa);
//        if (!kats2.isEmpty()) {
//            int baraj2 = kats2.get(0).count / 2;
//            for (Word w : kats2) {
//                if (w.count > baraj2) {
////                    System.out.println("\t" + w.word + " (" + w.count + ")");
//                } else {
//                    break; // Sıralı olduğundan devamı hep küçük gelecek..
//                }
//            }
//        }
        ///////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="3. yontem Kategoriler">
// 3. yontem kategori //////////////////////////
//        System.out.println("3. yontem kategoriler :");

//        for (Word w : counter.wali) {
//            try {
//                ResultSet rs3 = vt.executeQuery("select * from wikicfp where lcase(category) like lcase('" + w.word + " %')");
//                while (rs3.next()) {
////                    counter.countThis(w.word, kats3);
//                    counter.countThis(w.word, kats);
//                    counter.countThis(w.word, mostsignificantwords);
//                }
//                rs3.close();
//            } catch (Exception e) {
//                System.out.println("3. yöntem hata : " + e.getLocalizedMessage());
//            }
//        }

//        Collections.sort(kats3, counter.compa);
//        if (!kats3.isEmpty()) {
//            int baraj3 = kats3.get(0).count / 2;
//            for (Word w : kats3) {
//                if (w.count > baraj3) {
//                    System.out.println("\t" + w.word + " (" + w.count + ")");
//                } else {
//                    break; // Sıralı olduğundan devamı hep küçük gelecek..
//                }
//            }
//        }
        ///////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="4. yontem Kategoriler">
// 4. yontem kategori //////////////////////////
//        System.out.println("4. yontem kategoriler :");

//        for (Word w : counter.wali) {
//            try {
//                ResultSet rs4 = vt.executeQuery("select * from wikipedia where lcase(baglanti) like lcase('" + w.word + " %')");
//                while (rs4.next()) {
////                    counter.countThis(rs4.getString("kelime"), kats4);
//                    counter.countThis(rs4.getString("kelime"), kats);
//                    counter.countThis(w.word, mostsignificantwords);
//                }
//                rs4.close();
//            } catch (Exception e) {
//                System.out.println("4. yöntem hata : " + e.getLocalizedMessage());
//            }
//        }

//        Collections.sort(kats4, counter.compa);
//        if (!kats4.isEmpty()) {
//            int baraj4 = kats4.get(0).count / 2;
//            for (Word w : kats4) {
//                if (w.count > baraj4) {
//                    System.out.println("\t" + w.word + " (" + w.count + ")");
//                } else {
//                    break; // Sıralı olduğundan devamı hep küçük gelecek..
//                }
//            }
//        }
        ///////////////////////////////////////////////////// </editor-fold>

        // Toplam kategoriler /////////////////////////////
//        System.out.println("Toplam kategoriler : ");
        //kats.trimToSize();

        Collections.sort(kats, counter.compa);
        ArrayList<Word> ktemp = new ArrayList<Word>();
        if (!kats.isEmpty()) {
            int baraj0 = kats.get(0).count / 4;

//            kats = kats.subList(0, kats.size()/4);
            for (Word w : kats) {
                if (w.count >= baraj0) {
                    ktemp.add(w);
                    //  System.out.println("\t" + w.word + " (" + w.count + ")");
                } else {
                    break; // Sıralı olduğundan devamı hep küçük gelecek..
                }
            }
        }
        return ktemp;
    }

    public ArrayList<Word> publicationAnalyze(ArrayList<Publication> pubs) {
        //ArrayList<Word> wali = new ArrayList<Word>();
        Counter counter = new Counter();

        //counter.wali.clear();
        counter.count_one(pubs);
        counter.count_two(pubs);
        counter.count_tree(pubs);

//        Collections.sort(counter.wali, counter.compa);
//        for (int i = 0; i < 100; i++) {
//            try {
//                Word key = counter.wali.get(i);
//                ResultSet rs = vt.executeQuery("select baglanti from wikipedia where baglanti like lcase('" + key.word + "')");
//                if (rs.next()) {
//                    mostsignificantwords.add(key);
//                }
//            } catch (Exception e) {
//            }
//        }

        System.out.println("Kelimeler : " + counter.wali.size());
        //   int baraj = (counter.wali.size() / 100);

// <editor-fold defaultstate="collapsed" desc="1. yontem Kategoriler">
// 1. yontem kategori /////////////////////////
//        System.out.println("1. yontem kategoriler : ");
        for (Word w : counter.wali) {
            try {
                ResultSet rs1 = vt.executeQuery("select * from wikicfp where lcase(category) like lcase('" + w.word + "')");
                while (rs1.next()) {
//                    counter.countThis(w.word, kats1);
                    counter.countThis(w.word, kats);
                    counter.countThis(w.word, mostsignificantwords);
                }
                rs1.close();
            } catch (Exception e) {
                System.out.println("1. yöntem uygulanamadı." + e.getLocalizedMessage());
            }
       // }
//        Collections.sort(kats1, counter.compa);
//        for (Word w : kats1) {
////            System.out.println("\t" + w.word + " (" + w.count + ")");
//        }
        //////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="2. yontem Kategoriler">
// 2. yontem kategori /////////////////////////
//        System.out.println("2. yontem kategoriler : ");
        //for (Word w : counter.wali) {
            try {
                ResultSet rs2 = vt.executeQuery("select * from wikipedia where baglanti like '" + w.word + "'");

                while (rs2.next()) {
//                    counter.countThis(rs2.getString("kelime"), kats2);
                    counter.countThis(rs2.getString("kelime"), kats);
                    counter.countThis(w.word, mostsignificantwords);
                }
                rs2.close();
            } catch (Exception e) {
                System.out.println("2. yöntem hata : " + e.getLocalizedMessage());
            }
        //}
//        Collections.sort(kats2, counter.compa);
//        if (!kats2.isEmpty()) {
//            int baraj2 = kats2.get(0).count / 2;
//            for (Word w : kats2) {
//                if (w.count > baraj2) {
////                    System.out.println("\t" + w.word + " (" + w.count + ")");
//                } else {
//                    break; // Sıralı olduğundan devamı hep küçük gelecek..
//                }
//            }
//        }
        ///////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="3. yontem Kategoriler">
// 3. yontem kategori //////////////////////////
//        System.out.println("3. yontem kategoriler :");
       // for (Word w : counter.wali) {
            try {
                ResultSet rs3 = vt.executeQuery("select * from wikicfp where lcase(category) like lcase('" + w.word + " %')");
                while (rs3.next()) {
//                    counter.countThis(w.word, kats3);
                    counter.countThis(w.word, kats);
//                    counter.countThis(w.word, mostsignificantwords);
                }
                rs3.close();
            } catch (Exception e) {
                System.out.println("3. yöntem hata : " + e.getLocalizedMessage());
            }
       // }
//        Collections.sort(kats3, counter.compa);
//        if (!kats3.isEmpty()) {
//            int baraj3 = kats3.get(0).count / 2;
//            for (Word w : kats3) {
//                if (w.count > baraj3) {
//                    System.out.println("\t" + w.word + " (" + w.count + ")");
//                } else {
//                    break; // Sıralı olduğundan devamı hep küçük gelecek..
//                }
//            }
//        }
        ///////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="4. yontem Kategoriler">
// 4. yontem kategori //////////////////////////
//        System.out.println("4. yontem kategoriler :");
        //for (Word w : counter.wali) {
            try {
                ResultSet rs4 = vt.executeQuery("select * from wikipedia where lcase(baglanti) like lcase('" + w.word + " %')");
                while (rs4.next()) {
//                    counter.countThis(rs4.getString("kelime"), kats4);
                    counter.countThis(rs4.getString("kelime"), kats);
//                    counter.countThis(w.word, mostsignificantwords);
                }
                rs4.close();
            } catch (Exception e) {
                System.out.println("4. yöntem hata : " + e.getLocalizedMessage());
            }
        }
//        Collections.sort(kats4, counter.compa);
//        if (!kats4.isEmpty()) {
//            int baraj4 = kats4.get(0).count / 2;
//            for (Word w : kats4) {
//                if (w.count > baraj4) {
//                    System.out.println("\t" + w.word + " (" + w.count + ")");
//                } else {
//                    break; // Sıralı olduğundan devamı hep küçük gelecek..
//                }
//            }
//        }
        ///////////////////////////////////////////////////// </editor-fold>

        // Toplam kategoriler /////////////////////////////
//        System.out.println("Toplam kategoriler : ");
        //kats.trimToSize();

        Collections.sort(kats, counter.compa);
        ArrayList<Word> ktemp = new ArrayList<Word>();
        if (!kats.isEmpty()) {
            int baraj0 = kats.get(0).count / 4;

//            kats = kats.subList(0, kats.size()/4);
            for (Word w : kats) {
                if (w.count >= baraj0) {
                    ktemp.add(w);
                    //  System.out.println("\t" + w.word + " (" + w.count + ")");
                } else {
                    break; // Sıralı olduğundan devamı hep küçük gelecek..
                }
            }
        }
        kats=null;
        counter=null;
        System.gc();
        return ktemp;
    }

    public ArrayList<String> publicationAnalyzeForce2(ArrayList<Publication> pubs) {
        ArrayList<String> ali = new ArrayList<String>();
        String pubsall = "";
//        System.out.println("----------->"+pubs.size());
        for (Publication string : pubs) {
            if (string.getTitle() != null) {
                pubsall += string.getTitle().toLowerCase();
            }
        }
//        System.out.println("pubsal"+pubsall);
        try {
            ResultSet rs = vt.executeQuery("select kelime,baglanti from wikipedia");
            while (rs.next()) {
                int say = 0;
                if (pubsall.contains(rs.getString("kelime")) || pubsall.contains(rs.getString("kelime") + "s")) {
                    ali.add(rs.getString("kelime").toLowerCase());
                } else {
                    String[] pars = rs.getString("baglanti").replaceAll("_", ",").split(",");
                    for (String par : pars) {
                        par = par.replace("_", " ").toLowerCase();
                        if (par.contains("(")) {
                            par = par.substring(0, par.indexOf("("));
                        }
                        if (pubsall.contains(" " + par + "s ") || pubsall.contains(" " + par + " ") || pubsall.contains(" " + par + ".")) {
//                        System.out.println(rs.getString("kelime"));
                            say++;
                            if (say > 100) {
                                ali.add(rs.getString("kelime").toLowerCase());
                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Publication Analyze error..");
        }
        System.out.println("Publication Analyze sakses..");
        return elek(ali);
    }

    public ArrayList<String> publicationAnalyzeForce(ArrayList<Publication> pubs) {
        ArrayList<String> ali = new ArrayList<String>();
        String pubsall = "";
//        System.out.println("----------->"+pubs.size());
        for (Publication string : pubs) {
            if (string.getTitle() != null) {
                pubsall += string.getTitle().toLowerCase();
            }
        }
//        System.out.println("pubsal"+pubsall);
        try {
            ResultSet rs = vt.executeQuery("select kelime,baglanti from wikipedia");
            while (rs.next()) {
                int say = 0;
                if (pubsall.contains(rs.getString("kelime"))) {
                    ali.add(rs.getString("kelime").toLowerCase());
                } else {
                    String[] pars = rs.getString("baglanti").replaceAll("_", ",").split(",");
                    for (String par : pars) {
                        par = par.replace("_", " ").toLowerCase();
                        if (par.contains("(")) {
                            par = par.substring(0, par.indexOf("("));
                        }
                        if (pubsall.contains(" " + par + "s ") || pubsall.contains(" " + par + " ") || pubsall.contains(" " + par + ".")) {
//                        System.out.println(rs.getString("kelime"));
                            say++;
                            if (say > 100) {
                                ali.add(rs.getString("kelime").toLowerCase());
                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Publication Analyze error..");
        }
        System.out.println("Publication Analyze sakses..");
        return elek(ali);
    }

    public ArrayList<Coauthor> coauthors(String link) {
        ArrayList<Coauthor> ali = new ArrayList<Coauthor>();
        String json = "";
        boolean virgul = false;
        try {
            Document doc = new XMLMash().readXML("http://dblp.uni-trier.de/rec/pers/" + link + "/xc"); //mash.mashIt("http://dblp.uni-trier.de/search/author/?xauthor=" + s));
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("author");
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node n = nodelist.item(i);
//                System.out.println("->" + n.getAttributes().getNamedItem("urlpt").getNodeValue());
                ali.add(new Coauthor(n.getAttributes().getNamedItem("urlpt").getNodeValue(), n.getTextContent(), Integer.parseInt(n.getAttributes().getNamedItem("count").getNodeValue())));
            }
            return ali;
        } catch (Exception ex) {
            System.out.println("Coauthors error..");
            return ali;
        }
    }

    public ArrayList<Word> getKats() {
        return mostsignificantwords;
    }

    public static void main(String[] args) {
        DBLPPerson p = new DBLPPerson();
//        ArrayList<String> ali = new ArrayList<String>();
        for (Word w : p.publicationAnalyze(p.publicationsList("k/Kaya:Mehmet"))) {
            System.out.println(w.word + " : " + w.count);
        }
//("k/Kaya:Mehmet"));
//        for (String s : p.publications("a/Akin:Erhan")) {
//            System.out.println(p.pubTitle(s));
////            for (String string : p.publicationAnalyze(p.pubTitle(s))) {
//////                System.out.print(string+",");
////                break;
////            }
////            System.out.println("");
////            System.out.println("");
////            System.out.println("");
////            break;
//        }
//
////       p.publications("a/Akin:Erhan"));
//        //System.out.println(p.pubYear(s));
//        //System.out.println(p.pubJournal(s));
//        //System.out.println("");
////        }
////        for (String string : p.publicationAnalyzeForce(p.publications("f/Fox:Geoffrey"))) {
////            System.out.println(string);
////        }
////        p.coauthors("k/Kaya:Mehmet");
//
    }
}
