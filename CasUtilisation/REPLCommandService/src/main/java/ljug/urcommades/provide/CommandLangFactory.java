/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.urcommades.provide;

import java.util.function.Function;
import ljug.langage.Assoc;
import ljug.langage.AddUser;
import ljug.langage.AddRole;
import ljug.langage.CommandeUR;
import ljug.langage.RmRole;

/**
 *
 * @author cnamliban
 */
public class CommandLangFactory {

    /**
     * Default Factory
     * @return 
     */
    public static CommandLangServices create() {
        CommandLangImp cl = new CommandLangImp();
        cl.setActionAddUsers((CommandeUR cu) -> (AddUser) cu);
        cl.setActionAddRoles((CommandeUR cu) -> (AddRole) cu);
        cl.setActionAssoc((CommandeUR cu) -> (Assoc) cu);
        cl.setActionRmRoles((CommandeUR cu) -> (RmRole) cu);
        //TODO ajouter les autres
        return cl;
    }    
    public static CommandLangServices create(
            Function<CommandeUR, CommandeUR> addRole,
            Function<CommandeUR, CommandeUR> addUser,
            Function<CommandeUR, CommandeUR> assoc) {
        CommandLangImp cl = new CommandLangImp();
        cl.setActionAddUsers(addUser);
        cl.setActionAddRoles(addRole);
        cl.setActionAssoc(assoc);
        //TODO ajouter les autres
        return cl;
    }
}
