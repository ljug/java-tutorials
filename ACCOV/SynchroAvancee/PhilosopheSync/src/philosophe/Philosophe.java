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

package philosophe;

public class Philosophe extends Thread {
  /** no du philosophe */
  int no;
  /** nb de bouchees restantes dans l'assiette */
  int nbBouchees;
  /** stoppe le processus s'il a fini son assiette */
  boolean fini;
  /** date de debut du diner */
  long debut;
  /** liste de fourchettes */
  Fourchettes lesFourchettes;
  /**temps min d'une bouchee en milliseconde*/
  static final int TEMPSMINBOUCHEE=100;
  /**temps optionnel en plus pour une bouchee en milliseconde*/
  static final int TEMPSBOUCHEE=2000;
  /**temps min d'une pensee en milliseconde*/
  static final int TEMPSMINPENSEE=100;
  /**temps optionnel en plus pour une pensee en milliseconde*/
  static final int TEMPSPENSEE=2000;
 
/** initialise fini a true*/	
Philosophe() { fini = true; }
 
/** initialise le no et nb de bouchees */
Philosophe(int _no, int _nbBouchees, Fourchettes _lesFourchettes)
{
  no = _no;
  nbBouchees = _nbBouchees;
  lesFourchettes = _lesFourchettes;
  fini = false;
}
 
/** fonction principale du philosophe : cycle sur manger, penser.
 * Pour manger, il prend la fourchette de droite et celle de gauche.
 * Donc il prend la fourchette i et i+1.
 * Le philosophe garde les fourchettes un certains temps et les depose ensuite
 * la boucle se termine lorsque le philosophe a termine ses bouchees.
 * */
public void run()
{
long debutRepas = System.currentTimeMillis();
  while(!fini)
  {
    System.out.println("philo"+no+" : je pense en attendant les fourchettes");
    lesFourchettes.prendre(no);           
    System.out.println("philo"+ no+ " : je mange, il me reste " + nbBouchees + " bouchees.");
    nbBouchees--;
    fini = (nbBouchees<=0); 
    try {  Thread.sleep(Philosophe.TEMPSMINBOUCHEE +    (int)(Math.random()*Philosophe.TEMPSBOUCHEE ));}  
    catch (InterruptedException e) {}
    lesFourchettes.deposer(no);
    System.out.println("philo"+no+" : j'ai depose mes fourchettes et je pense un peu");
    try { Thread.sleep(Philosophe.TEMPSMINPENSEE +   (int)(Math.random()*Philosophe.TEMPSPENSEE ));}  
    catch (InterruptedException e) {}
  }
  long finRepas = System.currentTimeMillis();
  System.out.println("philo"+ no+ " : j'ai fini en " + 
      (float)((float)(finRepas-debutRepas)/1000) + "s...");
 }
}
