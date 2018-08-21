/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.impl;

import java.io.IOException;
import java.util.Scanner;
import net.cofares.GEvent;
import net.cofares.Source;
import net.cofares.boundary.MenuPrincipal;

/**
 *
 * @author pfares
 */
public class MySource {
   
    
    

    /**
     * @param args 
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Source<String> source = new Source<>();
        source.addGEventListener((GEvent ev) -> System.out.println("Je suis la callback" + ev.getData()));
       
        Thread t = new Thread(new MenuPrincipal(source));
        t.start();
        t.join();
    }
}
