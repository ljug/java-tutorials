/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import ljug.urcommades.provide.CommandLangFactory;
import ljug.urcommades.provide.CommandLangServices;

/**
 * L ::= CommanUR L
 *
 * @author cnamliban
 */
public class Main {
    public static void main(String a[])  {
        CommandLangServices cls = CommandLangFactory.create();
        cls.repl();
    }

}
