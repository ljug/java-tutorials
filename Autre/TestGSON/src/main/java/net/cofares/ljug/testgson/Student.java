/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.testgson;

/**
 *
 * @author pfares
 */
class Student {
    private String name; 
   private int age; 
   public Student(){} 
   
   public String getName() { 
      return name; 
   } 
   public void setName(String name) { 
      this.name = name; 
   } 
   public int getAge() { 
      return age; 
   } 
   public void setAge(int age) { 
      this.age = age; 
   } 
   public String toString() { 
      return "Student [ name: "+name+", age: "+ age+ " ]"; 
   }  
}
