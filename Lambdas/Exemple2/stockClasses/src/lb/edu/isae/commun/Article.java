/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lb.edu.isae.commun;

/**
 *
 * @author pfares
 */
public class Article {
    Produit p;
    private int quantite;
    
    public Article(Produit p, int qt) {
        this.p=p;
        quantite=qt;
    }
    
    public boolean isEnStock() {
        return (getQuantite() > 0);
    }
    
    public boolean  isDeCategorie(Categories c){
        return (p.getCategorie() == c);
    }
    
    public String getNom() {
        return p.getNom();
    }
    
    public String getCode () {
        return p.getCode();
    }

    /**
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
