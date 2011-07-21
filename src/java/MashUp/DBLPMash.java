package MashUp;

import Database.Vt;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBLPMash {

    MashIt mi = new MashIt();
    Vt vt = new Vt();

    public String linker(String name) {

        String[] names = name.toLowerCase().split(" ");
        String newName = "";
        for (String string : names) {
            string = string.toLowerCase(Locale.UK);
            System.out.println(string);
        }
        return "";
    }

    public ArrayList<String> publications(String link) {
        ArrayList<String> ali = new ArrayList<String>();

        String source = mi.mashIt(link);
        Pattern p = Pattern.compile("[:]\\w+\\s+[^<>]*[<]");
        Matcher m = p.matcher(source);
        while (m.find()) {
            String s = m.group();
            s = s.replaceAll("\\W", " ");
            s = s.replaceAll("  ", " ");
            ali.add(s);
            //System.out.println(s);
            // s now contains "BAR"
        }
        return ali;
    }

    public ArrayList<String> mutual(String link) {
        ArrayList<String> ali = new ArrayList<String>();

        String source = mi.mashIt(link);
        source = source.substring(source.indexOf("DBLP keys"), source.indexOf("Coauthor Index"));
        //System.out.println(source);
        Pattern p = Pattern.compile("<a\\b[^>]*>[^<]([a-zA-Z_\\s;&]*)</a>");//"[^<]*(<a href=\"([^\"]+)\">([^<]+)</a>)");//"[a></td><td><a(.*?)>][\\w+\\s*]+[^\\W\\d<>][</td></tr>]");
        Matcher m = p.matcher(source);
        while (m.find()) {
            String s = m.group();
            s = s.substring(s.indexOf('>') + 1, s.indexOf("</a>"));
            //if (s.matches("[>][\\w*\\s*]+[<]")) {

            if (ali.indexOf(s) < 0) {
                ali.add(s);
                System.out.println(s + " ");
                //System.out.println("Mevcuttur..");
            }


        }
        return ali;
    }

    public void getKey(ArrayList<String> ali) {
        ArrayList<String> words = new ArrayList<String>();
        /*Tüm kelimeleri toparla*/
        String allPublications = "";
        for (String string : ali) {
            allPublications += string;
            //System.out.println(ali);
        }
        /* System.out.println(allPublications);
        System.out.println("");
        System.out.println("");
        System.out.println("");*/
        /*Aynı kelimeleri yok et ve say*/
        String[] keys = allPublications.split(" ");
        int[] counts = new int[keys.length];

        for (String string : keys) {
            string = string.trim();
            if (words.indexOf(string) < 0) {
                words.add(string);
                counts[words.indexOf(string)] = 1;
                //System.out.println(string);
            } else {
                counts[words.indexOf(string)]++;
                //System.out.println("Saydım : " + string);
            }
        }

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
               // System.out.println(keys[i] + " (" + counts[i] + ")");
           
            try {
                if (vt.executeQuery("select * from popKey where kelime like '" + keys[i] + "'").next()) {
                    vt.execute("update popKey set say=say+" + counts[i] + " where kelime like '" + keys[i] + "'");
                } else {
                    vt.execute("insert into popKey (kelime,say) values ('" + keys[i] + "'," + counts[i] + ")");
                }
            } catch (Exception ex) {
                System.out.println("DB Error..");
            } }
        }

    }

    public static void main(String[] args) {
        DBLPMash dm = new DBLPMash();
        //dm.linker("Merter Hami KARACAN");


/*
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/a/Alhajj:Reda.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/k/Karak=ouml=se:Mehmet.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/k/Karci:Ali.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/a/Akin:Erhan.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/k/Kaya:Mehmet.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/g/Garousi:Vahid.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/=/=Ouml=zyer:Tansel.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/b/Barker:Ken.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/g/Gordon:Paul_M=_K=.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/l/Lo:Anthony.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/h/Hager:William_W=.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/t/Turinsky:Andrei_L=.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/s/Sensen:Christoph_W=.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/m/Maurer:Frank.html"));
        dm.getKey(dm.publications("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/s/Soh:Jung.html"));
        */
        //dm.mutual("http://www.informatik.uni-trier.de/~ley/db/indices/a-tree/k/Kaya:Mehmet.html");

    }
}
