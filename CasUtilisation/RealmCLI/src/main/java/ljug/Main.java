/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import javax.persistence.EntityManagerFactory;
import javax.annotation.Generated;
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
        jdbcUtil.executeSQLCommand("create database if not exist realm");
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
        jdbcUtil.checkData("select * from realm.users");
        jdbcUtil.checkData("select * from realm.roles");
        jdbcUtil.checkData("select * from realm.users_roles");
        System.out.println("----------");
        service.getRoles("pascal").stream().map(r -> r.getRoleName() + " of pascal " ).forEach(System.out::print);
        System.out.println("-");
        service.getRoles("pascal1").stream().map(r -> r.getRoleName() + " of pascal1 " ).forEach(System.out::print);
        System.out.println("-");
        service.getUsers("admin").stream().map(u -> u.getUserName() + " of admin " ).forEach(System.out::print);
        System.out.println("-");
        service.getUsers("prof").stream().map(u -> u.getUserName() + " of admin " ).forEach(System.out::print);
        System.out.println("-----------");
        service.dessociate("pascal1", "prof");
        jdbcUtil.checkData("select * from realm.users");
        jdbcUtil.checkData("select * from realm.roles");
        jdbcUtil.checkData("select * from realm.users_roles");
        service.rmUser("pascal");
        jdbcUtil.checkData("select * from realm.users");
        jdbcUtil.checkData("select * from realm.roles");
        jdbcUtil.checkData("select * from realm.users_roles");
        jdbcUtil.endStatement();
        /*
        EntityManager em = emf.createEntityManager();
        
        RolesJpaController tc = new RolesJpaController(emf);
        UsersJpaController uc = new UsersJpaController(emf);
        
        uc.create(new Users("pascal","pascal"));
        tc.create(new Roles("chef"));
        
        uc.findUsersEntities().stream().forEach(System.out::println);
        tc.findRolesEntities().stream().forEach(System.out::println);
        
        Users pascal = uc.findUsers("pascal");
        Roles chef = tc.findRoles("chef");
        System.out.println("-----------\n\n");
        System.out.printf("%s %d\n",pascal,pascal.getRolesSet().size());
        pascal.getRolesSet().stream().forEach(System.out::println);
        System.out.println("-----------\n\n");
        Set<Users> lr = chef.getUsersSet();
        System.out.printf("LR %s %d\n",chef,lr.size());
        lr.stream().forEach(u -> System.out.println("Heho"+u));
        System.out.println("----------------------------------------------------\n\n");
        pascal.getRolesSet().add(chef);
        //FonctionsDiverses.addIfNot(pascal.getRolesList(), chef);
        //pascal.getRolesList().add(chef);
        //chef.getUsersList().add(pascal);
        chef.getUsersSet().add(pascal);
        //FonctionsDiverses.addIfNot(chef.getUsersList(),pascal);
        //edit du propriétaire de la relation
        //uc.edit(pascal);
        System.out.println("----------------------------------------------------\n\n");
        tc.edit(chef);
        
        uc.create(new Users("pascal1","pascal1"));
        tc.create(new Roles("prof"));
        
        Users pascal1 = uc.findUsers("pascal1");
        Roles prof = tc.findRoles("prof");
        
        pascal.getRolesSet().add(prof);
        prof.getUsersSet().add(pascal);
        uc.edit(pascal);
        pascal1.getRolesSet().add(prof);
        prof.getUsersSet().add(pascal1);
        uc.edit(pascal1);
        */
        
    }
}
