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
package compte;

/**
 *
 * @author pascalfares
 */
public class Compte {

    private int solde = 0;

    public void ajouter(int somme) {
        solde += somme;
        System.out.print(" ajoute " + somme);
    }

    public void retirer(int somme) {
        solde -= somme;
        System.out.print(" retire " + somme);
    }

    public void operationNulle(int somme) {
       ajouter(somme);
       retirer (somme);
    }

    synchronized public int getSolde() {
        return solde;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
