package net.cofares.ljug.urcommades;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.cofares.ljug.event.CommandCallback;

/**
 * CommandUR ::= AddUser | AddRole | Assoc | ListU | ListR
 * On est positionné avant CommandUR (pour connaitre le prochain token utiliser hasXXX)
 * @author Pascal Fares
 */
public class CommandeUR {
    Consumer callback = (c) -> System.out.println(c);
    Supplier produce = () -> this;
    
    public static CommandeUR parse(CommandCallback lesSemantiques, URCTokenizer scan){  
        //System.out.println("UR");
        switch (scan.getTok()) {
            case ADDUSER:
                return AddUser.parse(lesSemantiques.getCommadAction("adduser"),scan);
            case ADDROLE:
                return AddRole.parse(lesSemantiques.getCommadAction("addrole"),scan);
            case ASSOC:
                return Assoc.parse(lesSemantiques.getCommadAction("assoc"),scan);
            case FININST:
                return null;
            case QUIT:
                System.exit(0);
            default:
                System.out.println("rien ! "+scan.getTok());
                return null;
        }
    }
    public void eval() {
        System.out.println("Eval CUR");
        callback.accept(produce.get());
    }
   
}
