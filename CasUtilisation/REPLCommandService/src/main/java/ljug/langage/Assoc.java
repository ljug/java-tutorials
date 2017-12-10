/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.langage;

import java.util.Scanner;
import java.util.function.Consumer;
import ljugCallback.CommandeCallbackAction;

/**
 *
 * @author cnamliban
 */
public class Assoc extends CommandeUR {
    public String user;
    public String role;
    public static Assoc parse(CommandeCallbackAction roleCallback, URCTokenizer scan){
        //System.out.println("AU");
        if (scan.getTok() != URCTokenizer.Token.ASSOC) {
            System.out.println("Pas de debut assoc");
            return null;
        }
        
        scan.nToken(); 
        Assoc au = new Assoc();
        au.actionCallbackChain = roleCallback;
        if (scan.getTok() != URCTokenizer.Token.SYMBOL) {
            System.out.println("Pas de username");
            return null;
        }
        au.user = new String(scan.sval);
        scan.nToken();

        if (scan.getTok() != URCTokenizer.Token.SYMBOL) {
            System.out.println("Pas de role");
            return null;
        }
        au.role = new String(scan.sval);
        scan.nToken();

        return au;
    }

    @Override
    public void eval() {
        //System.out.println("Eval ASSOC");
        //callback.accept(produce.get());
        actionCallbackChain.eval(this);
    }
}
