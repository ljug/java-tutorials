/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.event;

import java.util.function.Consumer;
import java.util.function.Function;
import net.cofares.ljug.urcommades.AddUser;

/**
 *
 * @author pfares
 * @param <X>
 * @param <Commande>
 */
public class CommandeCallbackAction<X,Commande> {

    Consumer<X> c;
    Function<Commande,X> m; //Resulta intermédiare

    
    public CommandeCallbackAction(Consumer<X> c, Function<Commande,X> m) {
        this.c = c;
        this.m = m;
    }
    
    public void eval(Commande commande) {
        c.accept(m.apply(commande));
    }
    public static void tryIt() {
        CommandeCallbackAction<AddUser,AddUser> chain= new CommandeCallbackAction<>(
        (AddUser u) -> System.out.println(u),
        (AddUser u) -> {
            u.gmail="toto"; 
            u.username="pascalfares";
            u.password="pascal";
            return u;
        });
        
        chain.c.accept(chain.m.apply(new AddUser()));
    }
    
}
