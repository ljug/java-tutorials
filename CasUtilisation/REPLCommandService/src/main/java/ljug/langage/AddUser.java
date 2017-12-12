/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.langage;

import java.util.function.Function;
import ljugCallback.CallBackFunction;


/**
 * le ? dans la règele est pour indiquer une option parser et semantique de
 * AddUser ::= addusr usename password gmail? ; des AddUSer valides adduser
 * pascal pascal adduser pascal pascal pfares@cofares.net
 *
 * @author Pascal Fares
 */
public class AddUser extends CommandeUR {

    static CommandeUR parse(Function commadAction, URCTokenizer scan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Consumer<AddUser> adduserCallback;
    public String username;
    public String password;
    public String gmail;

    public AddUser() {
        //adduserCallback = u -> System.out.printf("Adduser %s %s %s \n", u.username, u.password, u.gmail);
            
        
    }
    public static AddUser parse(CallBackFunction lesSemantiques, URCTokenizer scan) {
        
        if (scan.getTok() != URCTokenizer.Token.ADDUSER) {
            System.out.println("Pas de debut adduser");
            return null;
        }
        //Current token est ADDUSER
        scan.nToken(); //skip ADDUSER
        AddUser au = new AddUser();
        au.cf=lesSemantiques;
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
        au.password = scan.sval;
        scan.nToken();

        //C'est d;eja bon mais peut être un gmail en plus 
        
        if (scan.getTok() == URCTokenizer.Token.SYMBOL) {           
            au.gmail = scan.sval;
            System.out.println("-gmail?"+au.gmail);
            scan.nToken();
        }
        //System.out.println("fin adduser"+scan.getTok());
        return au;
    }

    @Override
    public void eval() {
        System.out.println(cf.apply("adduser", this)); 
    }
    
    @Override
    public String toString() {
        return String.format("AddUser %s %s %s\n",username, password, gmail);
    }
    
}
