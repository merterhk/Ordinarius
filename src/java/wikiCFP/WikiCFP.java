package wikiCFP;

import MashUp.MashIt;
import java.util.ArrayList;

public class WikiCFP {

    MashIt mi = new MashIt();

    public synchronized String getLink(String link) {

        try {
            String source = mi.mashIt(link);
            source = source.substring(source.indexOf("Link: <a href=\"") + 15);
            source = source.substring(0, source.indexOf("\""));
            return source;
        } catch (Exception e) {
            System.out.println("wikiCFP Link Error (getLink)");
        }
        return "";
    }

    public ArrayList<CFP> getCFPs(String s, int page) {
        ArrayList<CFP> cfps = new ArrayList<CFP>();
        try {
            String source = mi.mashIt("http://www.wikicfp.com/cfp/call?conference=" + s + "&page=" + page);
            if (!source.contains("Category \"" + s + "\" is undefined.")) {
                source = source.substring(source.indexOf("<table cellpadding=\"3\" cellspacing=\"1\" align=\"center\" width=\"100%\">"));
                source = source.substring(0, source.indexOf("</table>"));
                String[] a = source.split("</a>");
                for (String string : a) {
                    String link = "http://www.wikicfp.com/" + string.substring(string.lastIndexOf("<a href=\"") + 9, string.lastIndexOf("\""));
                    link = getLink(link);
                    //System.out.println(link);

                    String name = string.substring(string.lastIndexOf("\">") + 2);
                    //System.out.println(string);
                    cfps.add(new CFP(name, link));

                }
                //System.out.println(source);
            }
        } catch (Exception e) {
            System.out.println("WikiCFP CFPs Error (getCFPs)");
        }

        return cfps;
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> cate = new ArrayList<Category>();
        try {
            String source = mi.mashIt("http://www.wikicfp.com/cfp/allcat");
            source = source.substring(source.indexOf("<td align=\"center\">Category</td><td align=\"center\"># CFPs</td></tr>"));
            source = source.substring(0, source.indexOf("<div class=\"footer\">"));
            String[] a = source.split("conference=");
            boolean ilkatla=false;
            for (String string : a) {
                if(ilkatla){
               // System.out.println("a: "+string);
                String name = string.substring(0,string.indexOf("\">"));
                System.out.println("name: "+name);
                    //System.out.println(string.indexOf("align=\"center\">")+14);
                    //System.out.println(string.indexOf("</td>"));
                String count = string.substring(string.indexOf("align=\"center\">")+15);
                count = count.substring(0,count.indexOf("</td>"));
               System.out.println("cout:"+count);
                }
                ilkatla=true;
               //String name =string.substring(string.lastIndexOf("conference=") + 12, string.lastIndexOf("\""));
               //System.out.println(name);
                //name = getCount(name);

                //System.out.println(link);

                //String count = string.substring(string.lastIndexOf("\">") + 2);
                //System.out.println(string);
                //cate.add(new Category(name, count));
            }


        } catch (Exception e) {
            System.out.println("Categories  Error (getCategories)" + e.getMessage());
            e.printStackTrace();
        }
        return cate;
    }

    public synchronized String getCount(String count) {

        try {
            String source = mi.mashIt(count);
            source = source.substring(source.indexOf("\"center\">") + 7);
            source = source.substring(0, source.indexOf("</td><td>"));
            return source;
        } catch (Exception e) {
            System.out.println("getCount  Error (getCount)");
        }
        return "";
    }

    public static void main(String[] args) {
        WikiCFP w = new WikiCFP();
        for (CFP c : w.getCFPs("NLP", 1)) {
           System.out.println(c.name);
          System.out.println(c.link);
       }
       // w.getCategories();

//

        //System.out.println(wiki.mashIt("http://www.wikicfp.com/cfp/"));
    }
}
