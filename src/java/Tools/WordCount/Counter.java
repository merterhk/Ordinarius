package Tools.WordCount;

import DBLP.DBLPPerson;
import Database.Vt;
import MashUp.WikiMash;
import OBeans.Publication;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Counter {

    public ArrayList<Word> wali = new ArrayList<Word>();
    public Comparator<Word> compa = new Comparator<Word>() {

        public int compare(Word o1, Word o2) {
            // int a = o1.count - o2.count;
            // System.out.println("sort");
//                return a < 0 ? -1 : (a > 0 ? 1 : 0);
            return (o1.count == o2.count) ? 0 : ((o1.count < o2.count) ? 1 : -1);
        }
    };
    public Comparator<Word> equa = new Comparator<Word>() {

        public int compare(Word o1, Word o2) {
            // int a = o1.count - o2.count;
            // System.out.println("sort");
//                return a < 0 ? -1 : (a > 0 ? 1 : 0);
            return (o1.word.equalsIgnoreCase(o2.word)) ? 1 : -1;
        }
    };

    public void countThis(String s) {
//        long a = System.currentTimeMillis();
//        wali.trimToSize();
//        int index = Collections.binarySearch(wali, new Word(s), equa);
//        if (index > 0) {
//            wali.get(index).increaseCount();
//            return;
//        }
//        System.out.println((System.currentTimeMillis()-a)+"ms");
        for (Word w : wali) {
            if (w.word.equalsIgnoreCase(s)) {
                w.increaseCount();
                return;
            }
        }
        wali.add(new Word(s));
    }

    public void countThis(String s, ArrayList<Word> wali) {
//        long a = System.currentTimeMillis();
//        wali.trimToSize();
//        int index = Collections.binarySearch(wali, new Word(s), equa);
//        if (index > 0) {
//            wali.get(index).increaseCount();
//            return;
//        }
//        System.out.println((System.currentTimeMillis()-a)+"ms");
        for (Word w : wali) {
            if (w.word.equalsIgnoreCase(s)) {
                w.increaseCount();
                return;
            }
        }
        wali.add(new Word(s));
    }

    public void count_one_s(ArrayList<String> ali) {
//        wali.clear();

        for (String s : ali) {
            s = s.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\.", "");
//            s = s.replace('.', ' ').replace(',', ' ').replace('-', ' ').trim().toLowerCase();
            String[] words = s.split(" ");
            for (String string : words) {
                string = WordFormat.format(string);
                countThis(WordFormat.format(string));
            }
        }
    }

    public void count_two_s(ArrayList<String> ali) {
//        wali.clear();
        for (String s : ali) {
            s = WordFormat.format(s);
//            s = s.replaceAll(" for ", " ");
//            s = s.replaceAll(" and ", " ");
//            s = s.replaceAll(" a ", " ");
//            s = s.replaceAll(" an ", " ");
//            s = s.replaceAll(" of ", " ");
//            s = s.replaceAll(" by ", " ");
//            s = s.replaceAll(" to ", " ");
//            s = s.replaceAll(" in ", " ");
            String[] words = s.trim().split(" ");
            for (int i = 0; i < words.length - 1; i++) {
                countThis(WordFormat.format(words[i]) + " " + WordFormat.format(words[i + 1]));
            }
        }
    }

    public void count_tree_s(ArrayList<String> ali) {
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
            for (int i = 0; i < words.length - 2; i++) {
                System.out.println("\t\t\t" + words[i] + " " + words[i + 1] + " " + words[i + 2]);
                countThis(WordFormat.format(words[i]) + " " + WordFormat.format(words[i + 1]) + " " + WordFormat.format(words[i + 2]));
            }
        }
    }

    public void count_one(ArrayList<Publication> ali) {
//        wali.clear();

        for (Publication p : ali) {
            String s = p.getTitle();
            s = s.replaceAll(" the ", " ");
            s = s.replaceAll("the ", " ");
            s = s.replaceAll(" for ", " ");
            s = s.replaceAll(" and ", " ");
            s = s.replaceAll(" a ", " ");
            s = s.replaceAll("a ", " ");
            s = s.replaceAll(" an ", " ");
            s = s.replaceAll("an ", " ");
            s = s.replaceAll(" of ", " ");
            s = s.replaceAll(" by ", " ");
            s = s.replaceAll(" to ", " ");
            s = s.replaceAll(" in ", " ");
            s = s.replaceAll("in ", " ");
            s = s.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\.", "");
//            s = s.replace('.', ' ').replace(',', ' ').replace('-', ' ').trim().toLowerCase();
            String[] words = s.split(" ");
            for (String string : words) {
                string = WordFormat.format(string);
                countThis(WordFormat.format(string));
            }
        }
    }

    public void count_two(ArrayList<Publication> ali) {
//        wali.clear();
        for (Publication p : ali) {
            String s = p.getTitle();
            s = WordFormat.format(s);
//            s = s.replaceAll(" for ", " ");
//            s = s.replaceAll(" and ", " ");
//            s = s.replaceAll(" a ", " ");
//            s = s.replaceAll(" an ", " ");
//            s = s.replaceAll(" of ", " ");
//            s = s.replaceAll(" by ", " ");
//            s = s.replaceAll(" to ", " ");
//            s = s.replaceAll(" in ", " ");
            String[] words = s.trim().split(" ");
            for (int i = 0; i < words.length - 1; i++) {
                countThis(WordFormat.format(words[i]) + " " + WordFormat.format(words[i + 1]));
            }
        }
    }

    public void count_tree(ArrayList<Publication> ali) {
//        wali.clear();
        for (Publication p : ali) {
            String s = p.getTitle();
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
            for (int i = 0; i < words.length - 2; i++) {
//                System.out.println("\t\t\t" + words[i] + " " + words[i + 1] + " " + words[i + 2]);
                countThis(WordFormat.format(words[i]) + " " + WordFormat.format(words[i + 1]) + " " + WordFormat.format(words[i + 2]));
            }
        }
    }

    public synchronized void lookUp(String key, Counter subCounter, ArrayList<String> links, Vt vt) {
        links.trimToSize();
        System.out.println("links : " + links.size());

        for (String string : links) {
            try {
//                System.out.println("ikinci blok");
//                        System.out.println(string);
//                        string = string.replaceAll("\'", "");
                String kelime = "";
                ResultSet rs = vt.executeQuery("select * from wikipedia where kelime like '" + string + "' or baglanti like '" + string + " %'");
                if (rs.next()) {
                    kelime = rs.getString("kelime");
                    subCounter.countThis(rs.getString("kelime"));
                    //                            subCounter.countThis(string);
                }
                vt.execute("insert into wikiextra (kelime,baglanti) values ('" + kelime + "','" + key + "')");

            } catch (Exception ex) {
                System.out.println("db err1");
            }
        }
    }

    public void ikiliKonuOner() {
        DBLPPerson d = new DBLPPerson();
//        WikiMash wm = new WikiMash();
//        Counter subCounter = new Counter();

        ArrayList<Word> kats = new ArrayList<Word>();
        ArrayList<Word> kats1 = new ArrayList<Word>();
        ArrayList<Word> kats2 = new ArrayList<Word>();
        ArrayList<Word> kats3 = new ArrayList<Word>();
        ArrayList<Word> kats4 = new ArrayList<Word>();

        Vt vt = new Vt();

        ArrayList<String> pubtitles = new ArrayList<String>();

        for (String w : d.publications("k/Kaya:Mehmet")) {
//        for (String w : d.publications("a/Aydin:Galip")) {
//        for (String w : d.publications("a/Akin:Erhan")) {
//        for (String w : d.publications("k/Karci:Ali")) {
//        for (String w : d.publications("a/Alatas:Bilal")) {
//        for (String w : d.publications("k/Kianmehr:Keivan")) {
            String title = d.pubTitle(w);
            pubtitles.add(title);
            System.out.println(title);
        }

        wali.clear();
        count_one_s(pubtitles);
        count_two_s(pubtitles);
        count_tree_s(pubtitles);

        System.out.println("Kelimeler : " + wali.size());
        int baraj = (wali.size() / 100);

// <editor-fold defaultstate="collapsed" desc="1. yontem Kategoriler">
// 1. yontem kategori /////////////////////////
        System.out.println("1. yontem kategoriler : ");
        for (Word w : wali) {
            try {
                ResultSet rs1 = vt.executeQuery("select * from wikicfp where lcase(category) like lcase('" + w.word + "')");
                while (rs1.next()) {
                    countThis(w.word, kats1);
                    countThis(w.word, kats);
                }
            } catch (Exception e) {
            }
        }
        Collections.sort(kats1, compa);
        for (Word w : kats1) {
            System.out.println("\t" + w.word + " (" + w.count + ")");
        }
        //////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="2. yontem Kategoriler">
// 2. yontem kategori /////////////////////////
        System.out.println("2. yontem kategoriler : ");
        for (Word w : wali) {
            try {
                ResultSet rs2 = vt.executeQuery("select * from wikipedia where lcase(baglanti) like lcase('" + w.word + "')");
                while (rs2.next()) {
                    countThis(rs2.getString("kelime"), kats2);
                    countThis(rs2.getString("kelime"), kats);
                }
            } catch (Exception e) {
            }
        }
        Collections.sort(kats2, compa);
        if (!kats2.isEmpty()) {
            int baraj2 = kats2.get(0).count / 2;
            for (Word w : kats2) {
                if (w.count > baraj2) {
                    System.out.println("\t" + w.word + " (" + w.count + ")");
                } else {
                    break; // Sıralı olduğundan devamı hep küçük gelecek..
                }
            }
        }
        ///////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="3. yontem Kategoriler">
// 3. yontem kategori //////////////////////////
        System.out.println("3. yontem kategoriler :");
        for (Word w : wali) {
            try {
                ResultSet rs3 = vt.executeQuery("select * from wikicfp where lcase(category) like lcase('" + w.word + " %')");
                while (rs3.next()) {
                    countThis(w.word, kats3);
                    countThis(w.word, kats);
                }
            } catch (Exception e) {
            }
        }
        Collections.sort(kats3, compa);
        if (!kats3.isEmpty()) {
            int baraj3 = kats3.get(0).count / 2;
            for (Word w : kats3) {
                if (w.count > baraj3) {
                    System.out.println("\t" + w.word + " (" + w.count + ")");
                } else {
                    break; // Sıralı olduğundan devamı hep küçük gelecek..
                }
            }
        }
        ///////////////////////////////////////////////////// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="4. yontem Kategoriler">
// 4. yontem kategori //////////////////////////
        System.out.println("4. yontem kategoriler :");
        for (Word w : wali) {
            try {
                ResultSet rs4 = vt.executeQuery("select * from wikipedia where lcase(baglanti) like lcase('" + w.word + " %')");
                while (rs4.next()) {
                    countThis(rs4.getString("kelime"), kats4);
                    countThis(rs4.getString("kelime"), kats);
                }
            } catch (Exception e) {
            }
        }
        Collections.sort(kats4, compa);
        if (!kats4.isEmpty()) {
            int baraj4 = kats4.get(0).count / 2;
            for (Word w : kats4) {
                if (w.count > baraj4) {
                    System.out.println("\t" + w.word + " (" + w.count + ")");
                } else {
                    break; // Sıralı olduğundan devamı hep küçük gelecek..
                }
            }
        }
        ///////////////////////////////////////////////////// </editor-fold>

        // Toplam kategoriler /////////////////////////////
        System.out.println("Toplam kategoriler : ");
        Collections.sort(kats, compa);
        if (!kats.isEmpty()) {
            int baraj0 = kats.get(0).count / 4;
            for (Word w : kats) {
                if (w.count >= baraj0) {
                    System.out.println("\t" + w.word + " (" + w.count + ")");
                } else {
                    break; // Sıralı olduğundan devamı hep küçük gelecek..
                }
            }
        }
        ///////////////////////////////////////////////////
    }

    public void ikiliKonuOner2() {
        DBLPPerson d = new DBLPPerson();
        WikiMash wm = new WikiMash();
        Counter subCounter = new Counter();
        Vt vt = new Vt();

        ArrayList<String> pubtitles = new ArrayList<String>();

        for (String w : d.publications("k/Kaya:Mehmet")) {
//        for (String w : d.publications("a/Aydin:Galip")) {
            pubtitles.add(d.pubTitle(w));
        }

        wali.clear();
        count_one_s(pubtitles);
        count_two_s(pubtitles);


        System.out.println("Kelimeler : " + wali.size());
        int baraj = (wali.size() / 100);
        for (Word w : wali) {
//            if (w.count > baraj) {
//                System.out.println("* " + w.word);
//            }
//            if (true) {
//                continue;
//            }

            if (w.count > baraj) {

                ArrayList<String> links = new ArrayList<String>();

                try {
                    ResultSet ls = vt.executeQuery("select * from wikicache where kelime like '" + WordFormat.format(w.word) + "'");
//                    System.out.println("işlem : " + "select * from wikicache where kelime like '" + WordFormat.format(w.word) + "'");
                    boolean research = true;

                    while (ls.next()) {
                        links.add(ls.getString("link"));
                        research = false;
                    }

                    if (research) {
                        links = wm.wikiApiLink(wm.wikiApiNormalize(w.word), "", 200);
                    }
                } catch (Exception ex) {
                    System.out.println("wikCache kontrol hata." + ex.getLocalizedMessage());
                }

                try {
                    ResultSet ko = vt.executeQuery("select * from wikiextra where baglanti like '" + w.word + "'");
                    if (ko.next()) {
                        System.out.println("varmıs..");
                        subCounter.countThis(ko.getString("kelime"));
                    } else {
                        System.out.println("yokmus");
                        lookUp(w.word, subCounter, links, vt);
                    }

                } catch (Exception e) {
                    System.out.println("Lookup error..");
                }


//                for (String string : links) {
//                    try {
//                        System.out.println("ikinci blok");
////                        System.out.println(string);
////                        string = string.replaceAll("\'", "");
//                        ResultSet rs = vt.executeQuery("select * from wikipedia where kelime like '" + string + "' or baglanti like '" + string + " %'");
//                        if (rs.next()) {
//                            subCounter.countThis(rs.getString("kelime"));
////                            subCounter.countThis(string);
//                        }
//                    } catch (Exception ex) {
//                        System.out.println("db err1");
//                    }
//                }


            }
        }

        System.out.println("Sorting..");

        Collections.sort(subCounter.wali, new Comparator<Word>() {

            public int compare(Word o1, Word o2) {
                // int a = o1.count - o2.count;
                // System.out.println("sort");
//                return a < 0 ? -1 : (a > 0 ? 1 : 0);
                return (o1.count == o2.count) ? 0 : ((o1.count > o2.count) ? 1 : -1);
            }
        });
        System.out.println("listing");
        for (Word w : subCounter.wali) {
            if (w.count > 1) {

                System.out.println(w.count + " : " + w.word);
            }
        }
    }

    public void bisey() {
        DBLPPerson d = new DBLPPerson();


        ArrayList<String> pubtitles = new ArrayList<String>();

//        for (String w : d.publications("k/Kaya:Mehmet")) {
//        for (String w : d.publications("a/Aydin:Galip")) {
        for (String w : d.publications("f/Fox:Geoffrey")) {
            String t = d.pubTitle(w);
            pubtitles.add(t);
        }

        count_two_s(pubtitles);

        for (Word w : wali) {
            if (w.count > 1) {
                System.out.println(w.count + " : " + w.word);
            }
        }


    }

    public static void main(String[] args) {

        Counter c = new Counter();
//        c.bisey();
        c.ikiliKonuOner();

    }
}
