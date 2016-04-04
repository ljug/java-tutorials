/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aproche4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.cofares.ljug.Person;

/**
 *
 * @author pascalfares
 */
public class Aproche4 {

    public static void processPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {
        //test x -> boolean
        for (Person p : roster) {
            if (tester.test(p)) {
                //Application  x -> F x
                String data = mapper.apply(p);
                //Consomer x -> void
                block.accept(data);
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

        /* CheckPerson une seule mehode abstraite alors une interface fonctionelle */
        processPersonsWithFunction(lp,
                (Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 51
                && p.getAge() <= 53,
                p -> p.getEmailAddress(),
                email -> System.out.println(email));

    }

}
