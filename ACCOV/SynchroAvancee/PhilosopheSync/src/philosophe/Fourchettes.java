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

/** liste des Fourchettes que doivent se partager les philosophes*/
import java.util.Arrays;

public class Fourchettes {
  /** tableau d'occupation des fourchettes false = occupee, true = libre*/
  boolean[] lesFourchettes;
  /** nb de fourchettes*/
  int taille;
 
  /** constructeur initialisant la taille et le tableau des fourchettes a true*/
  public Fourchettes(int _taille) {
    lesFourchettes = new boolean[_taille];
    Arrays.fill(lesFourchettes, true);
    taille = _taille;
  }
 
  /** fonction appelee par un processus philosophe i
  * Si la fourchette de droite (i) et de gauche (i+1) est libre 
  * alors le philosophe les prend, 
  * sinon, il est mis en attente*/
public synchronized void prendre(int no) {
  int droite = no;
  int gauche = (no+1)%taille;    	
  while (!lesFourchettes[gauche] || !lesFourchettes[droite]) {            
    try   {  wait();  }
    catch (InterruptedException e) {}
}
  lesFourchettes[gauche] = false;
  lesFourchettes[droite] = false;
}
 
/** fonction appelee par un processus philosophe i. <br>
* libere la fourchette de droite (i) et de gauche (i+1) <br> 
* et reveille les processus en attente sur les fourchettes*/
public synchronized void deposer(int no) {
  int droite = no;
  int gauche = (no+1)%taille;    	
  lesFourchettes[gauche] = true;
  lesFourchettes[droite] = true;
  notifyAll(); // reveille les processus en attente de fourchettes
}       
 
/** retourne sous forme de chaine l'etat des differentes fourchettes*/
public String toString() {
  String chaine = "";
  for(int i=0; i<taille; i++)
    chaine = chaine + lesFourchettes[i] + " ; ";
  return chaine;
 }    
}
