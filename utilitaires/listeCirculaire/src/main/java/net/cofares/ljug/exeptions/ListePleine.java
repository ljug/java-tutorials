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
public class ListePleine extends Exception {

    /**
     * Creates a new instance of <code>ListePleine</code> without detail
     * message.
     */
    public ListePleine() {
    }

    /**
     * Constructs an instance of <code>ListePleine</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ListePleine(String msg) {
        super(msg);
    }
}
