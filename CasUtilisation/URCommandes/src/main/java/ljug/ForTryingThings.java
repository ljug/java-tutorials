/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.cofares.ljug.urcommades.AddUser;

/**
 *
 * @author pfares
 */
public class ForTryingThings<X,Commande> {

    Consumer<X> c;
    Supplier<Commande> s; //En principe toujours une commande
    Function<Commande,X> m; //Resulta intermédiare

    
    public ForTryingThings(Consumer<X> c, Supplier<Commande> s, Function<Commande,X> m) {
        this.c = c;
        this.s = s;
        this.m = m;
    }

    public static void tryIt() {
        ForTryingThings<AddUser,AddUser> chain= new ForTryingThings<AddUser,AddUser>(
        (AddUser u) -> System.out.println(u),
        () -> new AddUser(),
        (AddUser u) -> {
            u.gmail="toto"; 
            u.username="pascalfares";
            u.password="pascal";
            return u;
        });
        
        chain.c.accept(chain.m.apply(chain.s.get()));
    }
    
}
