/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.urcommades;

import java.util.Scanner;

/**
 *
 * @author cnamliban
 */
class Assoc extends CommandeUR {
    String user;
    String role;
    public static Assoc parse(URCTokenizer scan){
        //System.out.println("AU");
        if (scan.getTok() != URCTokenizer.Token.ASSOC) {
            System.out.println("Pas de debut assoc");
            return null;
        }
        
        scan.nToken(); 
        Assoc au = new Assoc();
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
    public void eval () {
        System.out.printf("Assoc %s %s\n",user,role);
    }
}
