/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.urcommades;

import net.cofares.ljug.event.CommandeCallbackAction;

/**
 *
 * @author cnamliban
 */
public class CommandLangImp implements CommandLangServices {

    @Override
    public void repl() {
        URCTokenizer scan = new URCTokenizer(System.in);
        System.out.printf("START");
        CommandsLang.parse(cc, scan);
        System.out.println("END");
    }

    @Override
    public <I> void setActionAddUsers(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("adduser", ca);
    }

    @Override
    public <I> void setActionAddRoles(CommandeCallbackAction<I, AddRole> ca) {
        cc.registerAction("addrole", ca);
    }

    @Override
    public <I> void setActionAssoc(CommandeCallbackAction<I, Assoc> ca) {
        cc.registerAction("assoc", ca);
    }

    @Override
    public <I> void setActionRmUsers(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("rmuser", ca);
    }

    @Override
    public <I> void setActionRmAddRoles(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("rmrole", ca);
    }

    @Override
    public <I> void setActionDeAssoc(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("deassoc", ca);
    }

    @Override
    public <I> void setActionGetUsers(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("getusers", ca);
    }

    @Override
    public <I> void setActionGetRoles(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("getroles", ca);}

    @Override
    public <I> void setActionGetUser(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("getuser", ca);
    }

    @Override
    public <I> void setActionGetRole(CommandeCallbackAction<I, AddUser> ca) {
        cc.registerAction("getrole", ca);
    }

}
