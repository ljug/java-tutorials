package ljug.langage;

import java.util.function.Consumer;
import java.util.function.Supplier;
import ljugCallback.CommandeCallbackAction;
import ljugCallback.CommandesCallback;

/**
 * CommandUR ::= AddUser | AddRole | Assoc | ListU | ListR
 * On est positionné avant CommandUR (pour connaitre le prochain token utiliser hasXXX)
 * @author Pascal Fares
 */
public class CommandeUR {
    Consumer callback = (c) -> System.out.println(c);
    Supplier produce = () -> this;
    CommandeCallbackAction actionCallbackChain;
    
    public static CommandeUR parse(CommandesCallback lesSemantiques, URCTokenizer scan){  
        //System.out.println("UR");
        switch (scan.getTok()) {
            case ADDUSER:
                return AddUser.parse(lesSemantiques.getAction("adduser"),scan);
            case ADDROLE:
                return AddRole.parse(lesSemantiques.getAction("addrole"),scan);
            case ASSOC:
                return Assoc.parse(lesSemantiques.getAction("assoc"),scan);
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
