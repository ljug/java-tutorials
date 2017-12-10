/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljugCallback;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import ljug.langage.CommandeUR;

/**
 *
 * @author cnamliban
 */
public interface CallBackFunction {
    HashMap<String,Function<? extends CommandeUR,? extends CommandeUR>> callbacks = new HashMap<>();
    
    public void registerFunction(String command, Function<? extends CommandeUR,? extends CommandeUR> f);
}
