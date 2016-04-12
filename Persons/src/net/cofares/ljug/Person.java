/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author pascalfares
 */
public class Person implements Serializable { 

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

    /**
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the salaire
     */
    public double getSalaire() {
        return salaire;
    }

    /**
     * @param salaire the salaire to set
     */
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public enum Sex {
        MALE, FEMALE
        
    }

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;
    private double salaire;

    public Person(String name, int year, int month, int dayOfMonth, Sex g, String email) {
        this.name = name;
        birthday = LocalDate.of(year, month, dayOfMonth);
        gender = g;
        emailAddress = email;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(getBirthday(), now);
        return (int) years;
    }

    public void printPerson() {
        System.out.println(this);
    }

    
    @Override
    public String toString() {
        String sb = "{ ";
        sb += "\"name\": \"" + getName() + "\",";
        sb += "\"Birthday\": \"" + getBirthday()+ "\",";
        sb += "\"Sex\": \"" + getGender() + "\",";
        sb += "\"Age\": \"" + getAge()+ "\"";
        sb += "\"Salaire\": \"" + getSalaire()+ "\"";
        sb += " }";

        return sb;
    }

    /**
     * main pour test unitaire de la classe
     * @param args
     */
    public static void main(String args[]) {
        Person p = new Person("Pascal Fares", 1962, 9, 25, Person.Sex.MALE, "pfares@cofares.net");
        p.printPerson();
       
    }

}
