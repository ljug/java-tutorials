/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.urcommades.provide;

import java.util.function.Function;
import ljug.langage.CommandsLang;

import ljug.langage.CommandeUR;
import ljug.langage.URCTokenizer;


/**
 *
 * @author cnamliban
 */
public class CommandLangImp implements CommandLangServices {

    @Override
    public void repl() {
        URCTokenizer scan = new URCTokenizer(System.in);
        System.out.printf("START");
        CommandsLang.parse(cc, scan);
        System.out.println("END");
    }

    @Override
    public void setActionAddUsers(Function<CommandeUR, CommandeUR> f) {
        cc.registerFunction("adduser", f);
    }

    @Override
    public void setActionAddRoles(Function<CommandeUR, CommandeUR> f) {
        cc.registerFunction("addrole", f); }

    @Override
    public void setActionAssoc(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("assoc", f);}

    @Override
    public void setActionRmUsers(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("rmuser", f); }

    @Override
    public void setActionRmRoles(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("rmrole", f);}

    @Override
    public void setActionDeAssoc(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("deassoc", f);}

    @Override
    public void setActionGetUsers(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("listeu", f); }

    @Override
    public void setActionGetRoles(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("lister", f);}

    @Override
    public void setActionGetUser(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("getuser", f);}

    @Override
    public void setActionGetRole(Function<CommandeUR, CommandeUR> f) {
         cc.registerFunction("getrole", f);}

    

}
