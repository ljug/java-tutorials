/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aproche2;

import java.util.ArrayList;
import java.util.List;
import net.cofares.ljug.Person;

/**
 *
 * @author pascalfares
 */
public class Aproche2 {

    public static void printPersonsWithinAgeRange(
            List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Person> lp = new ArrayList<>();

        lp.add(new Person("Pascal Fares", 1962, 9, 25, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal1 Fares", 1963, 9, 26, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal2 Fares", 1964, 9, 27, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal3 Fares", 1965, 9, 28, Person.Sex.MALE, "pfares@cofares.net"));
        lp.add(new Person("Pascal4 Fares", 1966, 9, 29, Person.Sex.MALE, "pfares@cofares.net"));

        printPersonsWithinAgeRange(lp, 51, 53);
        //printPersonsOlderThan(lp, 51);

    }

}
