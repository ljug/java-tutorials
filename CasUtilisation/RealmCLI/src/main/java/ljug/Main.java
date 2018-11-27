/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import ljug.provide.ServiceFactory;
import ljug.provide.Services;
import ljug.util.JDBCUtilFactory;
import ljug.util.JDBCUtil;

/**
 *
 * @author pfares
 */
public class Main {
    public static void main(String[] args) throws Exception {

        //Connection BD standard par jdbc (utilisation de net.cofares.ljug:jdbcmysql-utility artifact maven par la LJUG)
        JDBCUtil jdbcUtil= JDBCUtilFactory.create("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/realm?useSSL=false", "realm-ms", "realm-ms");
        jdbcUtil.startStatement();
        //S'assurer que la BD existe (Les tables seront crée par JPA)
        jdbcUtil.executeSQLCommand("create database if not exists realm");
        //JPA
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("REALMPU");
        
        Services service = ServiceFactory.ServicesBuilder(emf);
        //
        service.addUser("pascal", "pascal", null);
        service.addUser("pascal1", "pascal1", "pfares@cofares.net");
        service.addRole("admin");
        service.addRole("prof");
        service.associate("pascal", "admin");
        service.associate("pascal", "prof");
        service.associate("pascal1", "prof");
        jdbcUtil.checkData("select * from realm.USERS");
        jdbcUtil.checkData("select * from realm.ROLES");
        jdbcUtil.checkData("select * from realm.ROLES_USERS");
        System.out.println("----------");
        service.getRoles("pascal").stream().map(r -> r.getRoleName() + " role of pascal " ).forEach(System.out::print);
        System.out.println("-");
        service.getRoles("pascal1").stream().map(r -> r.getRoleName() + " role of pascal1 " ).forEach(System.out::print);
        System.out.println("-");
        service.getUsers("admin").stream().map(u -> u.getUserName() + " has role admin " ).forEach(System.out::print);
        System.out.println("-");
        service.getUsers("prof").stream().map(u -> u.getUserName() + " has role prof " ).forEach(System.out::print);
        System.out.println("-----------");
        
        service.dessociate("pascal1", "prof");
        jdbcUtil.checkData("select * from realm.USERS");
        jdbcUtil.checkData("select * from realm.ROLES");
        jdbcUtil.checkData("select * from realm.ROLES_USERS");
        
        service.rmUser("pascal");
        jdbcUtil.checkData("select * from realm.USERS");
        jdbcUtil.checkData("select * from realm.ROLES");
        jdbcUtil.checkData("select * from realm.ROLES_USERS");
        jdbcUtil.endStatement();
        
    }
}
