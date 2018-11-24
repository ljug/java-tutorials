/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.exeptions;

/**
 *
 * @author ppfar
 */
public class ListeVide extends Exception {

    /**
     * Creates a new instance of <code>ListeVide</code> without detail message.
     */
    public ListeVide() {
    }

    /**
     * Constructs an instance of <code>ListeVide</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ListeVide(String msg) {
        super(msg);
    }
}
