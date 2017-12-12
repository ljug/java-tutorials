/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljugCallback;

import java.util.function.Function;
import ljug.langage.CommandeUR;

/**
 *
 * @author cnamliban
 */
public class CallBackFunctionImpl implements CallBackFunction {

    
    @Override
    public void registerFunction(String command, Function<CommandeUR, CommandeUR> f) {
        callbacks.put(command, f);
    }

    @Override
    public Function<CommandeUR, CommandeUR> getFunction(String commande) {
        return callbacks.get(commande);
         }

    @Override
    public CommandeUR apply(String commandename, CommandeUR commande) {
        return getFunction(commandename).apply(commande);
    }

}
