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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pascalfares
 */
public class A_faire {
     int Valeur;
        public A_faire(){
                Valeur = 0;
                //  System.out.println("Afaire.Valeur  : " + Valeur);
        }

        // Init : initialisation du nombre de travaux a effectue
        public void Init(int Nouvelle_Valeur){
                // Initialisation de la valeur a valeur +1
                // Ainsi, si valeur vaut 0 on fait tout de suite notify  
                Valeur = Nouvelle_Valeur +1 ;
                // System.out.println("Afaire.Valeur init. a : " + Valeur);
        }

        // Decrementer : cette methode decremente
        // le nombre de travaux a faire et reveille celui qui attend
        //  quand le dernier travail est pris
        public synchronized int Decrementer( ){
                Valeur = Valeur - 1;
                System.out.println("Afaire.Valeur Decr. -> : " + Valeur);
                if(Valeur == 0){
                        this.notifyAll();
                        return(0);
                }
                return(Valeur);
        }

        // Methode Attendre : cette methode est appelee par celui 
        // qui attend la fin des travaux
        public synchronized void Attendre( ) {
         try {
             this.wait();
         } catch (InterruptedException ex) {
             Logger.getLogger(A_faire.class.getName()).log(Level.SEVERE, null, ex);
         }
	}
}
