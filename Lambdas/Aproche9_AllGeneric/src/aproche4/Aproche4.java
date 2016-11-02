/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aproche4;

import java.util.ArrayList;
import java.util.List;
import net.cofares.ljug.Person;

/**
 *
 * @author pascalfares
 */
public class Aproche4 {

   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Initialisation des ensembles de donnée
        List<Person> lp = new ArrayList<>();

         
        lp.add(new Person("Pascal Fares", 1962, 9, 25, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal1 Fares", 1963, 9, 26, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal2 Fares", 1964, 9, 27, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal3 Fares", 1965, 9, 28, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal4 Fares", 1966, 9, 29, Person.Sex.MALE, "pfares@cofares.net"));

        lp.stream().forEach(p -> System.out.println("Qui?"+p));

        //Traitement sur les données
        lp.stream().filter(
                        p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 51
                        && p.getAge() <= 53)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));
        
        lp
                .stream()
                .filter(
                        p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 51
                        && p.getAge() <= 53)
           
                .forEach(p -> System.out.println(p));

    }

}
