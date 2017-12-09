/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.urcommades;

import net.cofares.ljug.event.CommandeCallbackAction;
import net.cofares.ljug.event.CommandesCallback;

/**
 *
 * @author cnamliban
 */
public interface CommandLangServices {
    CommandesCallback cc=new CommandesCallback();
    
    public <I> void setActionAddUsers(CommandeCallbackAction<I, AddUser> ca);
    public <I> void setActionAddRoles(CommandeCallbackAction<I, AddRole> ca);
    public <I> void setActionAssoc(CommandeCallbackAction<I, Assoc> ca);
    public <I> void setActionRmUsers(CommandeCallbackAction<I, AddUser> ca);
    public <I> void setActionRmAddRoles(CommandeCallbackAction<I, AddUser> ca);
    public <I> void setActionDeAssoc(CommandeCallbackAction<I, AddUser> ca);
    public <I> void setActionGetUsers(CommandeCallbackAction<I, AddUser> ca);
    public <I> void setActionGetRoles(CommandeCallbackAction<I, AddUser> ca);
    public <I> void setActionGetUser(CommandeCallbackAction<I, AddUser> ca);
    public <I> void setActionGetRole(CommandeCallbackAction<I, AddUser> ca);
    
    /**
     * Read Eval Print Loop
     * @param lesSemantiques : semantiques des commandes
     */
    public void repl();
}
