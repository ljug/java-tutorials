package lb.edu.isae.commun;

import java.io.Serializable;

/**
 * Une bonne habitude extends Serializable et getter Setter quand non manipuilons des entités 
 * @author Pascal Fares pour le Cnam Liban
 */
public class Produit implements Serializable {   
    private Categories categorie;
    private String code;
    private String nom;
    private int prix;
    
    public Produit() {
    }
    
    public Produit(Categories c, String code, String n, int prix) {
        setCategorie(c);
        setCode(code);
        setNom(n);
        setPrix(prix);
    }
    //Les getter setter (une bonne habitude)
    /**
     * Get the value of prix
     *
     * @return the value of prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Set the value of prix
     *
     * @param prix new value of prix
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the value of categorie
     *
     * @return the value of categorie
     */
    public Categories getCategorie() {
        return categorie;
    }

    /**
     * Set the value of categorie
     *
     * @param categorie new value of categorie
     */
    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    
}
