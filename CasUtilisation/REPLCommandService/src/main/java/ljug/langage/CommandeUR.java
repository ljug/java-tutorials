package ljug.langage;


import ljugCallback.CallBackFunction;


/**
 * CommandUR ::= AddUser | AddRole | Assoc | ListU | ListR
 * On est positionné avant CommandUR (pour connaitre le prochain token utiliser hasXXX)
 * @author Pascal Fares
 */
public class CommandeUR {

   CommandeUR commande;
    
    CallBackFunction cf;
    
    public static CommandeUR parse(CallBackFunction lesSemantiques, URCTokenizer scan){  
        //System.out.println("UR");
        switch (scan.getTok()) {
            case ADDUSER:
                return AddUser.parse(lesSemantiques,scan);
            case ADDROLE:
                return AddRole.parse(lesSemantiques,scan);
            case ASSOC:
                return Assoc.parse(lesSemantiques,scan);
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
        System.out.println(cf.apply("commandeUR", this));
    }
   
}
