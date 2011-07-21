package Tools.WordCount;

import DBLP.DBLPPerson;
import Database.Vt;
import MashUp.WikiMash;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class Counter1 {

//    ArrayList<Word> wali = new ArrayList<Word>();
    Hashtable<String, sayac> table = new Hashtable<String, sayac>();

    protected void countThis(String s) {
        if (!table.containsKey(s)) {
            table.put(s, new sayac(1));
        } else {
            table.get(s).art();
        }

    }

    public void count_one(ArrayList<String> ali) {
//        wali.clear();

        for (String s : ali) {
            s = s.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\.", "");
//            s = s.replace('.', ' ').replace(',', ' ').replace('-', ' ').trim().toLowerCase();
            String[] words = s.split(" ");
            for (String string : words) {
                string = WordFormat.format(string);
                countThis(string);
            }
        }
    }

    public void count_two(ArrayList<String> ali) {
//        wali.clear();
        for (String s : ali) {
            s = WordFormat.format(s);
            s = s.replaceAll(" for ", " ");
            s = s.replaceAll(" and ", " ");
            s = s.replaceAll(" a ", " ");
            s = s.replaceAll(" an ", " ");
            s = s.replaceAll(" of ", " ");
            s = s.replaceAll(" by ", " ");
            s = s.replaceAll(" to ", " ");
            s = s.replaceAll(" in ", " ");
            String[] words = s.trim().split(" ");
            for (int i = 0; i < words.length - 1; i++) {
                countThis(words[i] + " " + words[i + 1]);
            }
        }
    }

    public void ikiliKonuOner() {
        DBLPPerson d = new DBLPPerson();
        WikiMash wm = new WikiMash();
        Counter1 subCounter = new Counter1();
        Vt vt = new Vt();

        ArrayList<String> pubtitles = new ArrayList<String>();

        for (String w : d.publications("k/Kaya:Mehmet")) {
//        for (String w : d.publications("a/Aydin:Galip")) {
//        for (String w : d.publications("a/Aydin:Galip")) {
//        for (String w : d.publications("a/Akin:Erhan")) {
            pubtitles.add(d.pubTitle(w));
        }

//        wali.clear();
//        count_one(pubtitles);
        count_two(pubtitles);
//        Collections.sort(table.elements(), new Comparator<Word>() {
//
//            public int compare(Word o1, Word o2) {
//                // int a = o1.count - o2.count;
//                // System.out.println("sort");
////                return a < 0 ? -1 : (a > 0 ? 1 : 0);
//                return (o1.count == o2.count) ? 0 : ((o1.count > o2.count) ? 1 : -1);
//            }
//        });
        if (true) {
            return;
        }


        System.out.println("Kelimeler : " + table.size());
        int baraj = (table.size() / 50);
//        for (sayac w : table.keys().) {
        while (table.elements().hasMoreElements()) {
            String s = table.keys().nextElement();
            if (table.get(s).i > baraj) {
                System.out.println(s);
                ArrayList<String> links = new ArrayList<String>();

                try {
                    ResultSet ls = vt.executeQuery("select * from wikicache where kelime like '" + WordFormat.format(s) + "'");
//                    System.out.println("işlem : " + "select * from wikicache where kelime like '" + WordFormat.format(w.word) + "'");
                    boolean research = true;

                    while (ls.next()) {
                        links.add(ls.getString("link"));
                        research = false;
                    }

                    if (research) {
                        links = wm.wikiApiLink(wm.wikiApiNormalize(s), "", 200);
                    }
                } catch (Exception ex) {
                    System.out.println("wikCache kontrol hata." + ex.getLocalizedMessage());
                }

                for (String string : links) {
                    try {
//                        System.out.println(string);
                        string = string.replaceAll("\'", "");
                        ResultSet rs = vt.executeQuery("select * from wikipedia where kelime like '" + string + "' or baglanti like '" + string + " %'");
                        if (rs.next()) {
                            subCounter.countThis(rs.getString("kelime"));
//                            subCounter.countThis(string);
                        }
                    } catch (Exception ex) {
                        System.out.println("db err1");
                    }
                }


            }
        }


//
//        Collections.sort(subCounter.wali, new Comparator<Word>() {
//
//            public int compare(Word o1, Word o2) {
//                // int a = o1.count - o2.count;
//                // System.out.println("sort");
////                return a < 0 ? -1 : (a > 0 ? 1 : 0);
//                return (o1.count == o2.count) ? 0 : ((o1.count > o2.count) ? 1 : -1);
//            }
//        });
//        for (Word w : subCounter.wali) {
        for (String w : table.keySet()) {
            if (table.get(w).i > 1) {

                System.out.println(table.get(w).i + " : " + w);
            }
        }
    }

    public void bisey() {
//        DBLPPerson d = new DBLPPerson();
//
//
//        ArrayList<String> pubtitles = new ArrayList<String>();
//
////        for (String w : d.publications("k/Kaya:Mehmet")) {
////        for (String w : d.publications("a/Aydin:Galip")) {
//        for (String w : d.publications("f/Fox:Geoffrey")) {
//            String t = d.pubTitle(w);
//            pubtitles.add(t);
//        }
//
//        count_two(pubtitles);
//
//        for (Word w : wali) {
//            if (w.count > 1) {
//                System.out.println(w.count + " : " + w.word);
//            }
//        }
    }

    public static void main(String[] args) {

        Counter1 c = new Counter1();
//        c.bisey();
        c.ikiliKonuOner();

    }
}
