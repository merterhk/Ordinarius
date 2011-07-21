package OBeans;

import DBLP.DBLPPerson;
import Database.Vt;
import Tools.WordCount.Word;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonData {

    String urlpt = "";
    private String error = "";
    private ArrayList<Word> categories;
    private ArrayList<Word> significantWords;
    private ArrayList<Publication> publications;
    private ArrayList<PersonData> coauthors;
    private ArrayList<PersonData> morecoauthors;
    int countMoreCoauthors = 0;
    DBLPPerson dblp = new DBLPPerson();

    public boolean loadPublications() {

        if (publications.isEmpty()) {
            System.out.println("internetten yükleniyor..");
            System.gc();
            try {
                publications = dblp.publicationsList(urlpt);
                return !publications.isEmpty();
            } catch (Exception e) {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean loadCoauthors() {
        if (coauthors.isEmpty()) {
            System.gc();

            try {
                coauthors = new ArrayList<PersonData>();
                for (Coauthor c : dblp.coauthors(urlpt)) {
//                System.out.println(c.getUrlpt());
                    PersonData pd = new PersonData();
                    pd.setUrlpt(c.getUrlpt());
                    //pd.loadPublications();
//                System.out.println(pd.getPublications().get(1));
//                pd.loadCategories();
//                pd.loadCoauthors();
                    coauthors.add(pd);
//                System.out.println("-----> "+pd.getPublications().size());
                }
                return !coauthors.isEmpty();
            } catch (Exception e) {
                error = e.getLocalizedMessage();
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean loadMoreCoauthors() {
        System.gc();
        if (morecoauthors.isEmpty()) {
            try {
                // coauthors = new ArrayList<PersonData>();
                for (PersonData c : coauthors) {
                    c.loadCoauthors();
                    morecoauthors.addAll(c.coauthors);
                    morecoauthors = elek(morecoauthors);
                    if (countMoreCoauthors > 100) {
                        break;
                    }
                }
                /*Sorun çıkabilir. Direkt true dönderilmiş.*/
                return true;
            } catch (Exception e) {
                error = e.getLocalizedMessage();
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean loadCategories() {
        if (categories.isEmpty()) {
            System.gc();
            if (publications.isEmpty()) {
                return false;
            }
            try {
                categories = dblp.publicationAnalyze(publications);
                significantWords = dblp.getKats();
                savePerson();
                return !categories.isEmpty();
            } catch (Exception e) {
                System.out.println("Category loading error..");
                return false;
            }
        } else {
            return true;
        }
    }

    public ArrayList<Word> getMostUsedWords() {
        return significantWords;
    }

    public void savePerson() {
        Vt vt = new Vt();
        try {

            ResultSet rs = vt.executeQuery("select urlpt from turboperson where urlpt like '" + urlpt + "'");
            if (!rs.next()) {
                //String urlpt = this.urlpt;
                String coauthors1 = "", significantwords = "", categories1 = "";

                for (PersonData personData : getCoauthors()) {
                    coauthors1 += personData.getUrlpt() + ",";
                }
                for (Word personData : getCategories()) {
                    categories1 += personData.word + "-" + personData.count + ",";
                }
                for (Word personData : getMostUsedWords()) {
                    significantwords += personData.word + "-" + personData.count + ",";
                }
                for (Publication publication : publications) {
                    vt.execute("insert into turbopubs (urlpt,link,title,year,coauthors,journal,booktitle) values ('" + urlpt + "','" + publication.getLink() + "','" + publication.getTitle() + "','" + publication.getYear() + "','" + publication.getCoauthors() + "','" + publication.getJournal() + "','" + publication.getBooktitle() + "')");
                }

                vt.execute("insert into turboperson (urlpt,coauthors,significantwords,categories) values ('" + urlpt + "','" + coauthors1 + "','" + significantwords + "','" + categories1 + "')");
            }
        } catch (Exception e) {
            System.out.println("Kişi bilgilerini kayıt etme başarısız.. " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public boolean loadPerson() {
        Vt vt = new Vt();
        try {
            ResultSet rs1 = vt.executeQuery("select * from turboperson where urlpt like '" + urlpt + "'");
            if (rs1.next()) {
                String coauthors1 = rs1.getString("coauthors"), significantwords1 = rs1.getString("significantwords"), categories1 = rs1.getString("categories");

                try {
                    for (String s : coauthors1.split(",")) {
                        PersonData pd = new PersonData();
                        pd.setUrlpt(s);
                        this.coauthors.add(pd);
                    }
                } catch (Exception e) {
                    System.out.println("Ortak yazarlar yüklenemedi. " + e.getMessage());
                }

                try {
                    for (String s : significantwords1.split(",")) {
                        Word ww = new Word(s.split("-")[0].replace(",", ""));
                        ww.count = Integer.parseInt(s.split("-")[1]);
                        this.significantWords.add(ww);
                    }
                } catch (Exception e) {
                    System.out.println("En çok kullanılan kelimeler yüklenemedi. " + e.getMessage());
                }

                try {
                    for (String s : categories1.split(",")) {
                        Word ww = new Word(s.split("-")[0].replace(",", ""));
                        ww.count = Integer.parseInt(s.split("-")[1]);
                        this.categories.add(ww);
                    }
                } catch (Exception e) {
                    System.out.println("En çok kullanılan kelimeler yüklenemedi. " + e.getMessage());
                }

                ResultSet rs2 = vt.executeQuery("select * from turbopubs where urlpt like '" + urlpt + "'");
                while (rs2.next()) {
                    Publication p = new Publication(rs2.getString("title"), rs2.getString("year"), rs2.getString("coauthors"), rs2.getString("journal"), rs2.getString("booktitle"));
                    this.publications.add(p);
                }
                rs2.close();
                rs1.close();
                return true;
            } else {
                rs1.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Veritabanından kişi bilgileri yüklenirken hata oluştu...");
            return false;
        }
    }

    public void loadPersonMoreCoauthors() {
        for (PersonData pd : morecoauthors) {
            pd.loadPerson();
        }
    }

    public static void main(String[] args) {
        PersonData p = new PersonData();
        p.setUrlpt("k/Kaya:Mehmet");
        p.loadPublications();
        p.loadCoauthors();
        p.savePerson();
    }

    /**************************/
    public String getUrlpt() {
        return urlpt;
    }

    public void setUrlpt(String urlpt) {
//        if (!this.urlpt.equals(urlpt)) {
        this.urlpt = urlpt;
        error = "";
        dblp = new DBLPPerson();
        categories = new ArrayList<Word>();
        publications = new ArrayList<Publication>();
        coauthors = new ArrayList<PersonData>();
        morecoauthors = new ArrayList<PersonData>();
        significantWords = new ArrayList<Word>();
        countMoreCoauthors = 0;
        System.gc();
        loadPerson();
//        }
    }

    public ArrayList<Publication> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<Publication> publications) {
        this.publications = publications;
    }

    public void setCoauthors(ArrayList<PersonData> coauthors) {
        this.coauthors = coauthors;
    }

    public ArrayList<PersonData> getCoauthors() {
        return coauthors;
    }

    public ArrayList<Word> getCategories() {

        return (ArrayList<Word>) categories.clone();
    }

    public void setCategories(ArrayList<Word> categories) {
        this.categories = categories;
    }

    public String getError() {
        return error;
    }

    public int getCountMoreCoauthors() {
        return morecoauthors.size();
    }

    public ArrayList<PersonData> getMorecoauthors() {
        return morecoauthors;
    }

    public ArrayList<Word> getSignificantWords() {
        return significantWords;
    }

    public ArrayList<PersonData> elek(ArrayList<PersonData> ali) {
        ArrayList<PersonData> nali = new ArrayList<PersonData>();
        boolean var = false;
        for (PersonData a : ali) {

            for (PersonData b : nali) {
                if (a.getUrlpt().equals(b.getUrlpt())) {
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
}
