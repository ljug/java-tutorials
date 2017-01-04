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
package pm;

/**
 *
 * @author pascalfares
 */
public class Chef extends Thread {

    Barr b;
    A_faire Travail;
    int nbTravaux;

    Chef(Barr b, A_faire Travail, int nbTravaux) {
        this.b = b;
        this.Travail = Travail;
        this.nbTravaux = nbTravaux;
    }

    @Override
    public void run() {
        while (true) {

            // Initialiser le nombre de travaux a faire
            Travail.Init(nbTravaux);
            System.out.println(" *** Init du travail par chef : " + nbTravaux + " travaux a faire *** ");

            // Lever la barriere pour 
            // indiquer aux travailleurs qu'ils peuvent commencer
            b.DebloquerTous();

            // Ici on pourrait afficher l'etat des donnees
            // avant travaux.
            // attendre la fin des travaux
            Travail.Attendre();

            // Ici on pourrait afficher l'etat des donnees
            // apres travaux.
        }
    }
}
