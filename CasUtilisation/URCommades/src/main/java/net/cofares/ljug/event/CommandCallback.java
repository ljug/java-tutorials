/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.event;

import java.util.function.Consumer;
import net.cofares.ljug.urcommades.AddUser;

/**
 *
 * @author cnamliban
 */
public class CommandCallback<T>  {
    Consumer<T> callback;
    public void registerCommandAction(Consumer<T> c){
        callback=c;
    }
    public Consumer<T> getCommadAction(){
        return callback;
    }
}
