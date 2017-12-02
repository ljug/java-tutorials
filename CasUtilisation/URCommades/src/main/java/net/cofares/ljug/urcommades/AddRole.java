/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.urcommades;

/**
 *
 * @author cnamliban
 */
class AddRole extends CommandeUR {
    String role;
    public static AddRole parse(URCTokenizer scan){
        
        //System.out.println("AU");
        if (scan.getTok() != URCTokenizer.Token.ADDROLE) {
            System.out.println("Pas de debut addrole");
            return null;
        }
        //Current token est ADDUSER
        scan.nToken(); //skip ADDUSER
        AddRole au = new AddRole();
        if (scan.getTok() != URCTokenizer.Token.SYMBOL) {
            System.out.println("Pas de role");
            return null;
        }
        au.role = new String(scan.sval);
        scan.nToken();   
        return au;
    }
    public void eval () {
        System.out.printf("Addrole %s\n",role);
    }
}
