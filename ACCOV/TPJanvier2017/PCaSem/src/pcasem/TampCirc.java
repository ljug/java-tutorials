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

package pcasem;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pascalfares
 * @param <T>
 */
public class TampCirc {

    private final Object Tampon[];
    private final int NbCases;
    private int IndProd, IndCons;
    Semaphore plein, vide, mutexp, mutexc;

    //////////////////////////////////////////////////////////	
    public TampCirc(int NbCases) {
        Tampon = new Object[NbCases];
        this.NbCases = NbCases;
        plein = new Semaphore(NbCases);
        vide = new Semaphore(0);
        mutexp = new Semaphore(1);
        mutexc = new Semaphore(1);
        IndProd = 0;
        IndCons = 0;
    }

    ////////////////////////////////////////////////////////// 
    //   Methode Produire :
    //   Si le tampon est plein, attendre.
    //   Sinon, faire un depot dans la premiere case libre,
    //   (indexee par IndProd), mettre a jour
    //   IndProd, incrementer le nbre de cases
    //   non lues et reveiller les eventuels
    //   consommateurs.
    //
    public void Produire(Object obj) {

        System.out.println("Plein= " + plein.availablePermits());
        plein.acquireUninterruptibly();
        mutexp.acquireUninterruptibly();
        //SECTION CRITIQUE
        Tampon[IndProd] = obj;
        IndProd = (IndProd + 1) % NbCases;
        System.out.println("      Produire : IndProd = " + IndProd);
        //FIN DE SECTION CRITIQUE
        mutexp.release();
        vide.release();

    }

    //////////////////////////////////////////////////////////
    //   Methode Consommer :
    //   Si le tampon est vide, attendre
    //   pendant AU PLUS timeout secondes.
    //   Sinon, lire la premiere case libre,
    //   (indexee par IndCons), mettre a jour
    //   IndCons, decrementer le nbre de cases
    //   non lues et reveiller les eventuels
    //   producteurs.
    //	 On indique le tamps d attente.
    public Object Consommer() {
        System.out.println("vide= " + vide.availablePermits());
        vide.acquireUninterruptibly();
        mutexc.acquireUninterruptibly();
        //DEBUT SECTION CRITIQUE
        Object obj = Tampon[IndCons];
        IndCons = (IndCons + 1) % NbCases;
        System.out.println("      Consommer (" + Thread.currentThread().getName() + ") : IndCons =" + IndCons);
        //FIN SECTION CRITIQUE
        mutexc.release();
        plein.release();                // signaler que le tampon est non-plein
        return obj;

    }

}
