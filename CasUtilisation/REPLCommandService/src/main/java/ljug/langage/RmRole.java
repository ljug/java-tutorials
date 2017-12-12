/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.langage;

import ljugCallback.CallBackFunction;

/**
 *
 * @author cnamliban
 */
public class RmRole extends CommandeUR {
    public String role;
    
    public static RmRole parse(CallBackFunction lesSemantiques, URCTokenizer scan){
        
        //System.out.println("AU");
        if (scan.getTok() != URCTokenizer.Token.RMROLE) {
            System.out.println("Pas de debut rmrole");
            return null;
        }
        //Current token est RMROLE
        scan.nToken(); //skip RMROLE
        RmRole au = new RmRole();
        au.cf=lesSemantiques;
        if (scan.getTok() != URCTokenizer.Token.SYMBOL) {
            System.out.println("Pas de role à suprimmé!");
            return null;
        }
        au.role = new String(scan.sval);
        scan.nToken();   
        return au;
    }
    @Override
    public void eval() {
        System.out.println(cf.apply("rmrole", this)); 
    }
    @Override
    public String toString() {
        return String.format("RmRole %s\n", role);
    }
}
