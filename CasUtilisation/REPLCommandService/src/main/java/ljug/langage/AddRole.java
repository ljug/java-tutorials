/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.langage;

import java.util.function.Consumer;
import ljugCallback.CommandeCallbackAction;

/**
 *
 * @author cnamliban
 */
public class AddRole extends CommandeUR {
    public String role;
    
    public static AddRole parse(CommandeCallbackAction roleCallback, URCTokenizer scan){
        
        //System.out.println("AU");
        if (scan.getTok() != URCTokenizer.Token.ADDROLE) {
            System.out.println("Pas de debut addrole");
            return null;
        }
        //Current token est ADDUSER
        scan.nToken(); //skip ADDUSER
        AddRole au = new AddRole();
        au.actionCallbackChain=roleCallback;
        if (scan.getTok() != URCTokenizer.Token.SYMBOL) {
            System.out.println("Pas de role");
            return null;
        }
        au.role = new String(scan.sval);
        scan.nToken();   
        return au;
    }
    @Override
    public void eval() {
        //System.out.println("Eval AddRole");
        //callback.accept(produce.get());
        actionCallbackChain.eval(this);
    }
    
}
