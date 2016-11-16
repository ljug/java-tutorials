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
package fr.lecnam;

/**
 *
 * @author pascalfares
 */
public class CompteurOrder implements Runnable {
    private String nom;
    private int max;
    // Temps de pause en millisecondes
    private final static int PAUSE = 5000;

    public CompteurOrder(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public CompteurOrder(String nom) {
        this(nom, 10);
    }

    public String getNom() {
        return nom;
    }

    public int getMax() {
        return max;
    }

    public void run() {
        for (int i = 1; i <= max; i++) {
            try {
                Thread.sleep((int) (Math.random() * PAUSE));
            } catch (InterruptedException e) {
                System.err.println(getNom() + " a ete interrompu.");
            }
            System.out.println(getNom() + " : " + i);
        }
        System.out.println("*** " + getNom() + " a termine.");
    }

    public static void main(String[] args) {
        CompteurOrder[] compteurs = {
            new CompteurOrder("Toto"),
            new CompteurOrder("Bibi"),
            new CompteurOrder("Robert"),
            new CompteurOrder("Pierre")
        };
        for (int i = 0; i < compteurs.length; i++) {
            new Thread(compteurs[i],compteurs[i].getNom()).start();
        }
    }
}
