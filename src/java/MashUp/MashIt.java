package MashUp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class MashIt {

    public String mashIt(String requestUrl) {
        String source = "";
        try {
            URL url = new URL(requestUrl.toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            //System.out.println("-----RESPONSE START-----");
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                source += inputLine;
            }
            in.close();
            //System.out.println("-----RESPONSE END-----");

        } catch (Exception e) {
            System.out.println("Mash Error." + e.getLocalizedMessage());
            //e.printStackTrace();
        }
        return source;
    }

    public String mashIt(String requestUrl, String post) {
        String source = "";
        try {
            URL url = new URL(requestUrl.toString());

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(post);
            wr.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            //System.out.println("-----RESPONSE START-----");
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                source += inputLine;
            }
            in.close();
            //System.out.println("-----RESPONSE END-----");

        } catch (Exception e) {
            System.out.println("Mash Error." + e.getLocalizedMessage());
            //e.printStackTrace();
        }
        return source;
    }

    public static void main(String[] args) {
        MashIt m = new MashIt();
        System.out.println(m.mashIt("http://en.wikipedia.org/wiki/image processing"));
    }
}
