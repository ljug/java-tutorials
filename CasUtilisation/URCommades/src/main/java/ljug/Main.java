/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import java.io.IOException;
import net.cofares.ljug.event.CommandCallback;
import net.cofares.ljug.urcommades.AddRole;
import net.cofares.ljug.urcommades.AddUser;
import net.cofares.ljug.urcommades.Assoc;
import net.cofares.ljug.urcommades.CommandsLang;
import net.cofares.ljug.urcommades.URCTokenizer;

/**
 * L ::= CommanUR L
 *
 * @author cnamliban
 */
public class Main {

    public static void initCommandCallbacks(CommandCallback cc) {
        cc.registerCommandAction("adduser", (AddUser u) -> System.out.printf("\nAdduser %s %s %s \n", u.username, u.password, u.gmail));
        cc.registerCommandAction("addrole", (AddRole r) -> System.out.printf("\nAddRole %s \n", r.role));
        cc.registerCommandAction("assoc", (Assoc r) -> System.out.printf("\nAssoc %s %s \n", r.user, r.role));
    }
    public static void main(String a[])  {
        URCTokenizer scan = new URCTokenizer(System.in);
        CommandCallback cc=new CommandCallback();
        initCommandCallbacks(cc);
        System.out.printf("START");
        CommandsLang.parse(cc, scan);
        System.out.println("END");
    }

}
