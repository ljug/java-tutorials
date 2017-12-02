/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.urcommades;

import java.util.function.Consumer;

/**
 * le ? dans la règele est pour indiquer une option parser et semantique de
 * AddUser ::= addusr usename password gmail? ; des AddUSer valides adduser
 * pascal pascal adduser pascal pascal pfares@cofares.net
 *
 * @author Pascal Fares
 */
public class AddUser extends CommandeUR {

    Consumer<AddUser> adduserCallback;
    String username;
    String password;
    String gmail;

    public AddUser() {
        adduserCallback = u -> System.out.printf("Adduser %s %s %s \n", u.username, u.password, u.gmail);
            
        
    }
    public static AddUser parse(URCTokenizer scan) {
        //System.out.println("AU");
        if (scan.getTok() != URCTokenizer.Token.ADDUSER) {
            System.out.println("Pas de debut adduser");
            return null;
        }
        //Current token est ADDUSER
        scan.nToken(); //skip ADDUSER
        AddUser au = new AddUser();
        if (scan.getTok() != URCTokenizer.Token.SYMBOL) {
            System.out.println("Pas de username");
            return null;
        }
        au.username = new String(scan.sval);
        scan.nToken();

        if (scan.getTok() != URCTokenizer.Token.SYMBOL) {
            System.out.println("Pas de password");
            return null;
        }
        au.password = new String(scan.sval);
        scan.nToken();

        //C'est d;eja bon mais peut être un gmail en plus 
        
        if (scan.getTok() == URCTokenizer.Token.SYMBOL) {           
            au.gmail = new String(scan.sval);
            System.out.println("-gmail?"+au.gmail);
            scan.nToken();
        }
        System.out.println("fin adduser"+scan.getTok());
        return au;
    }

    @Override
    public void eval() {
        adduserCallback.accept(this);
    }
}
