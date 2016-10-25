/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

/**
 *
 * @author pascalfares
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(hello(args[0]));
        System.out.println(somme(10,50));
    }

    private static String hello(java.lang.String name) {
        net.cofares.NewWebService_Service service = new net.cofares.NewWebService_Service();
        net.cofares.NewWebService port = service.getNewWebServicePort();
        return port.hello(name);
    }

    private static Integer somme(java.lang.Integer x, java.lang.Integer y) {
        net.cofares.NewWebService_Service service = new net.cofares.NewWebService_Service();
        net.cofares.NewWebService port = service.getNewWebServicePort();
        return port.add(x, y);
    }
    
}
