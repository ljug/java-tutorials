/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import java.io.IOException;
import net.cofares.ljug.urcommades.CommandeUR;
import net.cofares.ljug.urcommades.URCTokenizer;

/**
 * L ::= CommanUR L
 *
 * @author cnamliban
 */
public class Main {

    public static void main(String a[]) throws IOException {
        URCTokenizer scan = new URCTokenizer(System.in);
        CommandeUR cur;
        System.out.print("\n$> ");
            scan.nToken();
        while (true) {            
            cur = CommandeUR.parse(scan);
            if (cur == null) {
                //skip pour essayer de reprendre
                System.out.print("Pas une commande correcte " + scan.getTok());
                System.out.print("\n$> ");
                scan.nToken();
                continue;
            }
            cur.eval();
            System.out.print("\n$> ");
        }
    }

}
