/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author pascalfares
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;
    
    public Person(String name, int year, int month, int dayOfMonth, Sex g, String email){
        this.name=name;
        birthday = LocalDate.of(year, month, dayOfMonth);
        gender=g;
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
        String sb = "{";
        sb += "name : "+name;
        sb += "Birthday : " + birthday;
        sb += "Age : " + getAge();
        sb+= "}";
        
        return sb;
    }
    
    /**
     * main pour test unitaire de la classe
     */
    public static void main (String args[]) {
        Person p = new Person("Pascal Fares", 1962, 9, 25, Person.Sex.MALE, "pfares@cofares.net");
        p.printPerson();
    }
    
}
