/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.event;

import java.util.HashMap;
import java.util.function.Consumer;
import net.cofares.ljug.urcommades.CommandeUR;

/**
 *
 * @author cnamliban
 */
public class CommandCallback {
    HashMap<String,Consumer<? extends CommandeUR>> callbacks = new HashMap<>();
    public <T> void registerCommandAction(String command, Consumer<? extends CommandeUR> f){
        callbacks.put(command, f);
    }
    public Consumer getCommadAction(String command){
        return callbacks.get(command);
    }
}
