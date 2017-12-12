/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljugCallback;

import java.util.HashMap;
import java.util.function.Function;
import ljug.langage.CommandeUR;

/**
 *
 * @author cnamliban
 */
public interface CallBackFunction {
    HashMap<String,Function<CommandeUR,CommandeUR>> callbacks = new HashMap<>();
    
    public void registerFunction(String command, Function<CommandeUR,CommandeUR> f);
    public  Function<CommandeUR,CommandeUR> getFunction(String commande);
    public CommandeUR apply(String commandename, CommandeUR commande);
}
