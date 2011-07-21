package Tools.WordCount;

import java.util.ArrayList;

public class WordFormat {

    public static String format(String s) {

        if (s.contains("(")) {
            s = s.substring(0, s.indexOf("(") - 1);
        }
        if (s.endsWith("s")) {
            s = s.substring(0, s.length() - 1);
        }

        if (s.endsWith("al")) {
            s = s.substring(0, s.length() - 2);
        }

        return s.replace('-', ' ').replaceAll("\'", "").replaceAll("&", "").replace('.', ' ').replaceAll(":", "").replaceAll(",", "").replaceAll("!", "").trim().toLowerCase();
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
}
