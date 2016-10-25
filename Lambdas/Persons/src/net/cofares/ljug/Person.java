
package net.cofares.ljug;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author pascalfares
 */
public class Person {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public Sex getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Sex gender) {
        this.gender = gender;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public enum Sex {
        MALE, FEMALE
        
    }

    private String name;
    LocalDate birthday;
    private Sex gender;
    private String emailAddress;

    public Person(String name, int year, int month, int dayOfMonth, Sex g, String email) {
        this.name = name;
        birthday = LocalDate.of(year, month, dayOfMonth);
        gender = g;
        emailAddress = email;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birthday, now);
        return (int) years;
    }

    public void printPerson() {
        System.out.println(this);
    }

    
    public String toString() {
        String sb = "{ ";
        sb += "\"name\": \"" + getName() + "\",";
        sb += "\"Birthday\": \"" + birthday+ "\",";
        sb += "\"Sex\": \"" + getGender() + "\",";
        sb += "\"Age\": \"" + getAge()+ "\"";
        sb += " }";

        return sb;
    }

    /**
     * main pour test unitaire de la classe
     */
    public static void main(String args[]) {
        Person p = new Person("Pascal Fares", 1962, 9, 25, Person.Sex.MALE, "pfares@cofares.net");
        p.printPerson();
       
    }

}
