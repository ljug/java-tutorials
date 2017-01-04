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
public class PM {

    static int nbThreads = 0;
  static int nbTravaux = 0;

  public  static void main (String args[]) {
      Parse(args);

      	if (nbThreads <= 0) {
	 System.out.println ( "Il n'y a personne a faire travailler !");
	 System.exit (1);
	}

	if (nbTravaux <= 0) {
	 System.out.println ( "Il n'y a rien a faire  !");
	 System.exit (1);
	}

	if (nbTravaux < nbThreads ) {
	 System.out.println ( "Il faut plus de travaux que de threads !");
	 System.exit (1);
	}


      // Creation des deux  outils de synchronisation
      //
      
      //b est un Barr
      Barr b = new Barr();
      //b2 est un A_Faire
      A_faire b2=new A_faire(); 
      b2.Init(nbTravaux);
    
      // Creation des threads  qui vont effectuer le travail
      //
      for (int i =0; i < nbThreads ; i++) {
		new Travailleur ("Le_thread_"+ Integer.toString(i), b, b2).start();
        	}

      // ... et enfin, le chef
      //
       new Chef (b, b2, nbTravaux).start();
    }


////////////////////////////////////////////////////////////////
  private static  void Parse(String args[]) {
      
      if (args.length == 2) {
	nbThreads = Integer.parseInt(args[0]);
	nbTravaux = Integer.parseInt(args[1]);	
	}
      else {
	  System.out.println("Deux arguments : Nbre_de_threads Nbre_de_Travaux ! ");
	  System.exit(1);
        }
    }
    
}
