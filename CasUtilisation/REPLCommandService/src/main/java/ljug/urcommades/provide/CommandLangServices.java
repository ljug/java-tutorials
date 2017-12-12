/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.urcommades.provide;

import java.util.function.Function;
import ljug.langage.CommandeUR;
import ljugCallback.CallBackFunction;
import ljugCallback.CallBackFunctionImpl;

/**
 *
 * @author cnamliban
 */
public interface CommandLangServices {

    CallBackFunction cc = new CallBackFunctionImpl();

    public void setActionAddUsers(Function<CommandeUR, CommandeUR> f);

    public void setActionAddRoles(Function<CommandeUR, CommandeUR> f);

    public void setActionAssoc(Function<CommandeUR, CommandeUR> f);

    public void setActionRmUsers(Function<CommandeUR, CommandeUR> f);

    public void setActionRmRoles(Function<CommandeUR, CommandeUR> f);

    public void setActionDeAssoc(Function<CommandeUR, CommandeUR> f);

    public void setActionGetUsers(Function<CommandeUR, CommandeUR> f);

    public void setActionGetRoles(Function<CommandeUR, CommandeUR> f);

    public void setActionGetUser(Function<CommandeUR, CommandeUR> f);

    public void setActionGetRole(Function<CommandeUR, CommandeUR> f);

    /**
     * Read Eval Print Loop
     *
     * @param lesSemantiques : semantiques des commandes
     */
    public void repl();
}
