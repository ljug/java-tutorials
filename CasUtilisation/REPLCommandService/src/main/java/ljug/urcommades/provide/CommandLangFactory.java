/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.urcommades.provide;

import ljug.langage.Assoc;
import ljug.langage.AddUser;
import ljug.langage.AddRole;
import ljugCallback.CommandeCallbackAction;

/**
 *
 * @author cnamliban
 */
public class CommandLangFactory {

    /**
     * Default Factory
     * @return 
     */
    public CommandLangServices create() {
        CommandLangImp cl = new CommandLangImp();
        cl.setActionAddUsers(new CommandeCallbackAction<>(
                (String s) -> System.out.println(s),
                (AddUser u) -> String.format("Adduser %s %s %s", u.username, u.password, u.gmail)
        ));
        cl.setActionAddRoles(new CommandeCallbackAction<>(
                (String s) -> System.out.println(s),
                (AddRole r) -> String.format("AddRole %s", r.role)
        ));
        cl.setActionAssoc(new CommandeCallbackAction<>(
                (String s) -> System.out.println(s),
                (Assoc u) -> String.format("Assoc %s %s", u.user, u.role)
        ));
        //TODO ajouter les autres
        return cl;
    }    
}
