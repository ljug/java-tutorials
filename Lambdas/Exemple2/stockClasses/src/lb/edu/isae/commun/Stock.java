/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lb.edu.isae.commun;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author pfares
 */
public class Stock {
    HashMap<String,Produit> produits;
    Set<Article> articleEnStock;
    
    public Stock() {
        produits= new HashMap<>();
        articleEnStock = new HashSet<>();
    }
    
    //Créer le produit
    public void add(Produit p){
        produits.put(p.getCode(), p);
   }
   
    //Ajouter un article
    public void add (String code, int qt) {
        //verifié que produit dans la base
        Produit p = produits.get(code);
        Article a = new Article(p, qt);
        articleEnStock.add(a);        
    }
    
    public static Stock initStock() {
        Stock s = new Stock();
        //Créer quelques produites
        s.add(new Produit(Categories.AUTRE, "CProduit1", "Produit1", 1000));
        s.add(new Produit(Categories.CONSOMABLE, "CProduit2", "Produit2", 100));
        s.add(new Produit(Categories.EMPRUNTABLE, "CProduit3", "Produit3", 10000));
        s.add(new Produit(Categories.MEUBLE, "CProduit4", "Produit4", 2000));
        s.add(new Produit(Categories.AUTRE, "CProduit5", "Produit5", 200));
        s.add(new Produit(Categories.AMORTISSABLE, "CProduit6", "Produit6", 3000));
        s.add(new Produit(Categories.AMORTISSABLE, "CProduit7", "Produit7", 300));
        s.add(new Produit(Categories.AMORTISSABLE, "CProduit8", "Produit8", 4000));
        s.add(new Produit(Categories.AMORTISSABLE, "CProduit9", "Produit9", 500));
        s.add(new Produit(Categories.AMORTISSABLE, "CProduit10", "Produit10", 999));
        
        s.add("CProduit1",10);
        s.add("CProduit2",100);
        s.add("CProduit3",9);
        s.add("CProduit4",0);
        s.add("CProduit5",5);
        s.add("CProduit6",10);
        s.add("CProduit7",7);
        s.add("CProduit8",0);
        s.add("CProduit9",0);
        s.add("CProduit10",10);
        
        return s;
      
    }
    
    public int qtEnStock() {
        return articleEnStock.stream().map(Article::getQuantite).reduce(0,(x,y) -> x+y);
    }
    
    //Le produit qui possède le moins d'eleément en stock
    public Article leMoins() {
        Optional<Article> a;
        a= articleEnStock.stream().filter(Article::isEnStock).min((ar1,ar2) -> Integer.compare(ar1.getQuantite(),ar2.getQuantite()));
        //a= articleEnStock.stream().filter(Article::isEnStock).min(Comparator.comparing(Article::getQuantite));
        
       
        return a.get();
        
    }
    
    public Article plus(Article a, Article b) {
        if (a == null) return b;
        else if (b==null) return a;
        else return ((a.getQuantite() > b.getQuantite()) ? a : b);
    }
    public String qtMaxEnStock() {
        Optional<Article> a;
        a=articleEnStock.stream().filter(Article::isEnStock).reduce((ar1,ar2) -> plus(ar1,ar2));
        return a.get().getNom();
        
      
    }
    public static  void main(String args[]) {
        //Créer quelques Produits
        Stock s=initStock();
        
        s.articleEnStock.stream().filter(Article::isEnStock).map(Article::getNom).forEach(System.out::println);
        
        s.articleEnStock.stream().filter(ar -> (ar.isEnStock() && ar.isDeCategorie(Categories.AUTRE))).map(ar -> String.format("%s:%d",ar.getNom(),ar.getQuantite())).forEach(System.out::println);
        
        System.out.printf("La qt totale en stock est %d", s.qtEnStock());
        
        System.out.printf("Celui qui en possède le moins %s", s.leMoins().getNom());
         System.out.printf("Celui qui en possède le plus %s", s.qtMaxEnStock());
    }
}
