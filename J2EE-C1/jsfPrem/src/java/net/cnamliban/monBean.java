/*
 * Copyright © <Pascal Fares @ ISSAE - Cnam Liban>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package net.cnamliban;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author pascalfares
 */
@Named(value = "monBean")
@ApplicationScoped
public class monBean implements Serializable {

    private int conteur;

    /**
     * Get the value of conteur
     *
     * @return the value of conteur
     */
    public int getConteur() {
        conteur++;
        return conteur;
    }

    /**
     * Set the value of conteur
     *
     * @param conteur new value of conteur
     */
    public void setConteur(int conteur) {
        this.conteur = conteur;
    }

    /**
     * Creates a new instance of monBean
     */
    public monBean() {
    }
    
}
