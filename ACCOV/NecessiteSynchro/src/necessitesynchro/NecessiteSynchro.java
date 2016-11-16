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
package necessitesynchro;

/**
 *
 * @author pascalfares
 */
public class NecessiteSynchro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // définition d'un thread de comptage
        Runnable compte = new Runnable() {

            public void run() {

                // chaque thread possède son propre compteur
                SimpleCompteur compteur = new SimpleCompteur();
                for (int i = 0; i < 100; i++) {
                    compteur.compte();
                }
            }
        };

        // on lance 5 threads, qui comptent chacun jusqu'à 100
        Thread[] compteurs = new Thread[10];

        // initialisation des threads
        for (int i = 0; i < compteurs.length; i++) {
            compteurs[i] = new Thread(compte);
        }

        // lancement des threads
        for (int i = 0; i < compteurs.length; i++) {
            compteurs[i].start();
        }

        for (int i = 0; i < compteurs.length; i++) {
            // jette une InterruptedException
            compteurs[i].join();
        }

        // vérification de la valeur de compte
        System.out.println("Compteur = " + SimpleCompteur.getCompte());
    }

}
