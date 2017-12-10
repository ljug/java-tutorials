/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljugCallback;

import java.util.HashMap;
import java.util.function.Consumer;
import ljug.langage.CommandeUR;

/**
 *
 * @author cnamliban
 */
public class CommandesCallback {
    HashMap<String,CommandeCallbackAction> actions = new HashMap<>();
    public <I,T> void registerAction(String command, CommandeCallbackAction<I,T> ca){
        actions.put(command, ca);
    }
    public CommandeCallbackAction getAction(String command){
        return actions.get(command);
    }
    
    HashMap<String,Consumer<? extends CommandeUR>> callbacks = new HashMap<>();
    public void registerCommandAction(String command, Consumer<? extends CommandeUR> f){
        callbacks.put(command, f);
    }
    public Consumer getCommadAction(String command){
        return callbacks.get(command);
    }
}
