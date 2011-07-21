/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wikiCFP;

import Database.Vt;
import java.util.Scanner;

public class catInput {

    public static void main(String[] args) {
        Vt vt = new Vt();
        try {

            Scanner s = new Scanner(System.in);
            while (s.hasNext()) {
                String cat = s.nextLine().trim();
                vt.execute("Insert into wikiCFP (category) values ('" + cat + "')");
            }
        } catch (Exception e) {
        }
    }
}
