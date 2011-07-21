package wikiCFP;

import Database.Vt;
import MashUp.WikiMash;
import java.sql.ResultSet;

public class CFPCatDuzeltme {

    public void tekleriDuzelt() {
        WikiMash wm = new WikiMash();
        Vt vt = new Vt();
        try {
            ResultSet rs = vt.executeQuery("select * from (SELECT kelime,baglanti,count(*) as say FROM `wikipedia` group by kelime)  as kelime where say=1");
            while (rs.next()) {
                System.out.println(rs.getString("kelime")+" -> "+rs.getString("baglanti"));
                new Vt().execute("insert into wikicfp (category,duzeltme) values ('"+rs.getString("baglanti")+"',1)");
//                wm.wikiMashLink(rs.getString("kelime"));
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        CFPCatDuzeltme c = new CFPCatDuzeltme();
        c.tekleriDuzelt();
    }
}
