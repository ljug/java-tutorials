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
 * Travailleur est un thread qui a pour rôle d'executer un Travail (tâche)
 * Barr la barrière donnant l'autorisation de lancer un travail
 * A_faire les traveaux qui restent a faire
 * @author pascalfares
 */
public class Travailleur extends Thread {
  Barr b;
  A_faire  Travail;

  public Travailleur (String str, Barr b, A_faire Travail) {
      super(str);
      this.b       = b;
      this.Travail = Travail;
    }

  public void run() {
      while (true) { 
	  int j = 0;
	
	  System.out.println(getName() + " attend du travail");
	  
          
              // Attendre devant la barriere qu'il y ait un travail a faire
              b.Attendre();
          
	  
	  while (true)  {
	// Indiquer qu'on a va prendre un travail :
        // j contient le nombre de travaux qu'il reste a faire.
        // Quand j passe a zero, il n'y a plus de travaux a effectuer,
        // donc, le thread retournera attendre.

	      j = Travail.Decrementer();
	      if (j == 0) break;

	      // Effectuer ce travail : simulation par sleep (!!)
	      System.out.println
		("\t\t" + Thread.currentThread().getName()+ " PREND  un travail" );
	      try {
		  sleep((int)(Math.random() * 1000));  // C'est ca, la travail
		  }
	      catch (InterruptedException e) {}
	  	      // Le travail est termine
	      System.out.println("\t\t" + getName() + " TERMINE un travail");  
              if (Travail.Valeur == 0) break;
	    } 
	
	}
    }
}
