/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.cofares.ljug.realmcli.control.RolesJpaController;
import net.cofares.ljug.realmcli.control.UsersJpaController;
import net.cofares.ljug.realmcli.entites.Roles;
import net.cofares.ljug.realmcli.entites.Users;
import util.FonctionsDiverses;

/**
 *
 * @author pfares
 */
public class Main {
    public static void main(String[] args) throws Exception {

        // Perform JPA operations
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("RealmCLI_PU");
        
        RolesJpaController tc = new RolesJpaController(emf);
        UsersJpaController uc = new UsersJpaController(emf);
        
        uc.createorUpdate(new Users("pascal1","pascal1"));
        tc.createorUpdate(new Roles("prof"));
        
        uc.findUsersEntities().stream().forEach(System.out::println);
        tc.findRolesEntities().stream().forEach(System.out::println);
        
        Users pascal = uc.findUsers("Pascal");
        Roles chef = tc.findRoles("chef");
        System.out.printf("%s %d\n",pascal,pascal.getRolesList().size());
        pascal.getRolesList().stream().forEach(System.out::println);
        System.out.printf("%s %d\n",chef,chef.getUsersList().size());
        chef.getUsersList().stream().forEach(System.out::println);
        System.out.println("----------------------------------------------------\n\n");
        FonctionsDiverses.addIfNot(pascal.getRolesList(), chef);
        //pascal.getRolesList().add(chef);
        //chef.getUsersList().add(pascal);
        FonctionsDiverses.addIfNot(chef.getUsersList(),pascal);
        //edit du propriétaire de la relation
        //uc.edit(pascal);
        System.out.println("----------------------------------------------------\n\n");
        tc.edit(chef);
    }
}
