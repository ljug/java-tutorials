package net.cofares.ljug.listecirculaire.ListeCircullaire;

import net.cofares.ljug.exeptions.ListePleine;
import net.cofares.ljug.exeptions.ListeVide;

/**
 *
 * @author Pascal Fares
 * @param <D> : Donnée générique de la liste
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
    public ListeCirculaire(int taille) {
        liste = (D[]) new Object[taille];
        this.taille=taille;
        nbElem=debut=fin=0; //la liste est vide
    }
    public void put(D d) throws ListePleine{
        //si liste pas pleine
        if (estPleine()) {
            throw new ListePleine("La liste est pleine");
        }
        liste[debut]=d;
        debut = (debut+1) % taille;
        nbElem++;
    }
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
