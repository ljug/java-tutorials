/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import java.util.function.Function;
import ljug.langage.AddRole;
import ljug.langage.AddUser;
import ljug.langage.Assoc;
import ljug.langage.CommandeUR;
import ljug.provide.ServiceFactory;
import ljug.provide.Services;
import ljug.urcommades.provide.CommandLangFactory;
import ljug.urcommades.provide.CommandLangServices;

/**
 * L ::= CommanUR L
 *
 * @author cnamliban
 */
public class Main {

    public static void main(String a[]) {
        Services s = ServiceFactory.ServicesBuilder();
        Function<CommandeUR, CommandeUR> addUser = (CommandeUR comm) -> {
            AddUser au = (AddUser) comm;
            s.addUser(au.username, au.password, au.gmail);
            return comm;
        };
        Function<CommandeUR, CommandeUR> addRole = (CommandeUR comm) -> {
            AddRole au = (AddRole) comm;
            s.addRole(au.role);
            return comm;
        };
        Function<CommandeUR, CommandeUR> assoc = (CommandeUR comm) -> {
            Assoc au = (Assoc) comm;
            s.associate(au.user, au.role);
            return comm;
        };
        CommandLangServices cls = CommandLangFactory.create(
                addRole, 
                addUser, 
                assoc);
        cls.repl();

    }

}
