/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aproche3;

import aproche3.CheckPerson;
import net.cofares.ljug.Person;

/**
 *
 * @author pascalfares
 */
public class CriteriaImpl implements CheckPerson {
    @Override
    /**
     * Un test particulier statique!
     */
    public boolean test(Person p) {
        return p.getGender() == Person.Sex.MALE &&
            p.getAge() >= 51 &&
            p.getAge() <= 53;
    }

   
}
