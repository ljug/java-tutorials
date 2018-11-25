/**
 * 
 * <a href="http://rosenlaw.com/pdf-files/OSL3.0-comparison.pdf">Open Software License (�OSL�) v. 3.0</a>
*/
package net.cofares.ljug.listecirculaire.ListeCircullaire;

import java.lang.reflect.Array;
import net.cofares.ljug.exeptions.ListePleine;
import net.cofares.ljug.exeptions.ListeVide;

/**
 * @author Pascal Fares pascal.fares@cofares.net
 * @param <D> : Donnée génerique de la liste
 */
public class ListeCirculaire<D> {
    private final D[] liste;
    private final int taille;
    private int nbElem;
    /**
     * debut et fin de la liste
     * debut ou on peut écrire (la case est vide)
     * fin ou on peut lire (FIFO) 
     * 
     */
    private int debut,fin; 
    /**
     * Constructeur
     * @param clazz le type génerique exemple String[].class
     * @param taille la taille de la liste circulaire 
     */
    public ListeCirculaire(Class<D[]> clazz,int taille) {
        liste = clazz.cast(Array.newInstance(clazz.getComponentType(), taille)); 
        this.taille=taille;
        nbElem=debut=fin=0; //la liste est vide
    }
    /**
     * Déposer un élément
     * @param d : élément à produire
     * @throws ListePleine Si liste pleine (pas possible de déposer)
     */
    public void put(D d) throws ListePleine{
        //si liste pas pleine
        if (estPleine()) {
            throw new ListePleine("La liste est pleine");
        }
        liste[debut]=d;
        debut = (debut+1) % taille;
        nbElem++;
    }
    /**
     * Retirer (consomer un élément)
     * @return l'élément consomé (il n'est plus dans la liste)
     * @throws ListeVide Si liste vide (pas possible d'obtenir un élément)
     */
    public D get() throws ListeVide{
        //si liste pas vide
        if (estVide()) {
            throw new ListeVide("La liste est vide");
        }
        D d=liste[fin];
        fin = (fin+1) % taille;
        nbElem--;
        return d;
    }
    /**
     * Liste pleine?
     * @return true si pleine
     */
    public boolean estPleine() {
        
        return (getNbElem()==taille);
    }
    
    public boolean estVide() {
        return (getNbElem()==0);
    }

    /**
     * @return the nbElem
     */
    public int getNbElem() {
        return nbElem;
    }
}
