/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.urcommades;

import java.util.function.Consumer;

/**
 * CommandUR ::= AddUser | AddRole | Assoc | ListU | ListR
 * On est positionné avant CommandUR (pour connaitre le prochain token utiliser hasXXX)
 * @author cnamliban
 */
public class CommandeUR {
    Consumer<CommandeUR> commandURCallback = cr -> System.out.println("CommandeUR");
    
    public static CommandeUR parse(URCTokenizer scan){  
        //System.out.println("UR");
        switch (scan.getTok()) {
            case ADDUSER:
                return AddUser.parse(scan);
            case ADDROLE:
                return AddRole.parse(scan);
            case ASSOC:
                return Assoc.parse(scan);
            default:
                System.out.println("rien ! "+scan.getTok());
                return null;
        }
    }
    public void eval() {
        commandURCallback.accept(this);
    }
   
}
