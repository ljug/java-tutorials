/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainedelegation;

import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.model.Group;
import com.google.api.services.admin.directory.model.Groups;
import com.google.api.services.admin.directory.model.Member;
import com.google.api.services.admin.directory.model.Members;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lab
 */
public class GestionAvancee {
    
    public static void main(String args[]) throws GeneralSecurityException, IOException {
        //List<String> l=listeMembresGroupes("favoris-nehmat@isae.edu.lb");
        //for (String l0:l) {
        //    System.out.println("nom groupe favori: "+l0);
        //}
    }
    
    
    public static List<String> listeMembresGroupes(String groupEmail) throws IOException, GeneralSecurityException {
        //Cette méthode liste tous les membres d'un groupe (sans le propriétaire)
        List<String> l=new LinkedList<>();

        try {
            Directory d = GestionDomaine.initDirectory();
            
            Members mm = d.members().list(groupEmail).execute();
            List<Member> mem = mm.getMembers();
            if (mem != null) {
                if (!mem.isEmpty()) { 
                    for (Member m : mem) {
                        if (m.getRole().equals("MEMBER")) {
                            l.add(m.getEmail()); 
                        }
                    }
                    }
                }
            
        } catch (Exception exception) {
            
        }
        return l;
    }
    
    public static String listeGroupesFavoris() throws IOException, GeneralSecurityException {
        //Cette méthode liste tous les groupes du domaine
        //pas encore utilisée dans les applications (le 15 juin 2017)
        String mess = "";

        try {
            Directory d = GestionDomaine.initDirectory();
            Groups r = d.groups().list().setDomain("isae.edu.lb").execute();
            List<Group> groups = r.getGroups();
           
            for (Group group : groups) {
                if (group.getEmail().indexOf("favoris-", 0)==0) {
                    mess += group.getEmail() + " - " + group.getName() + " - "+group.getDirectMembersCount()+"\n";
                }
            }
        } catch (Exception exception) {
            mess = "ATTENTION: ECHEC - Impossible d'afficher la liste des groupes.";
        }
        return mess;
    }
    
        public static boolean estGroupeUtilise(String nomdomaine,String groupEmail) throws IOException, GeneralSecurityException {
        //Cette méthode teste si le groupe est utilisé par autre que le propriétaire,
        //c'est-à-dire si groupEmail se trouve comme membre dans l'un des groupes qui commencent par le mot favoris-
        
        boolean existe=false;

        try {
            Directory d = GestionDomaine.initDirectory();
            
            //la variable nomd contient le nom du domaine sans le @
            //c'est-à-dire nomd='isae.edu.lb'  car la variable nomdomaine='@isae.edu.lb'
            String nomd=nomdomaine.substring(1, nomdomaine.length());
            
            Groups r = d.groups().list().setDomain(nomd).execute();
            List<Group> groups = r.getGroups();
           
            for (Group group : groups) {
                
                if (group.getEmail().startsWith("favoris-")==true) {
             
                    if (domainedelegation.GestionSupplementaire.testerExistenceMembreGroupe(group.getEmail(), groupEmail)==2) {
                        existe=true;
                        break;
                    }
                }
            }
        } catch (Exception exception) {
            
        }
        return existe;
        
    }
        
        public static String quitterGroupeUtilise(String groupEmail,String memberEmail) {
        String mess="";
        try {
            Directory d=GestionDomaine.initDirectory();
            
            //Supprimer le groupe membre du groupe favoris
            d.members().delete(groupEmail, memberEmail).execute();
            
            //Avoir le groupe favoris
            Group g=d.groups().get(groupEmail).execute();
            
            //tester si le groupe favoris ne contient plus des groupes membres sauf le propriétaire
            if (g.getDirectMembersCount()==1) {
                //effacer le groupe favoris puisqu'il est devenu vide
                d.groups().delete(groupEmail).execute();
            }
            mess="L'opération a eu lieu avec succès.";
            
        } catch (Exception exception) {
            mess="ATTENTION: ECHEC - L'opération n'a pas eu lieu.";
        }
        return mess;
    }
        
        public static String quitterGroupeProprietaire(String groupEmail,String memberEmail,String nomdomaine) {
        String mess="";
        try {
            Directory d=GestionDomaine.initDirectory();
            
            //Supprimer le propriétaire du groupe
            //-------------------------------------
            d.members().delete(groupEmail, memberEmail).execute();
            
            //Voir maintenant au moins une personne qui utilise le groupe
            //------------------------------------------------------------
            String emailTrouve="";
            //la variable nomd contient le nom du domaine sans le @
            //c'est-à-dire nomd='isae.edu.lb'  car la variable nomdomaine='@isae.edu.lb'
            String nomd=nomdomaine.substring(1, nomdomaine.length());
            
            Groups r = d.groups().list().setDomain(nomd).execute();
            List<Group> groups = r.getGroups();
           
            for (Group group : groups) {    
                if (group.getEmail().startsWith("favoris-")==true) {
                    if (domainedelegation.GestionSupplementaire.testerExistenceMembreGroupe(group.getEmail(), groupEmail)==2) {
                        emailTrouve=group.getEmail().substring(8, group.getEmail().length());
                        quitterGroupeUtilise(group.getEmail(), groupEmail);
                        break;   
                    }
                }
            }
            
            
            //Affecter maintenant la propriété du groupe à une personne qui était utilisateur du groupe
            //------------------------------------------------------------------------------------------
           
            domainedelegation.GestionSupplementaire.ajoutMembreAGroupe(groupEmail, emailTrouve, "OWNER");
            
            mess="L'opération a eu lieu avec succès.";
            
        } catch (Exception exception) {
            mess="ATTENTION: ECHEC - L'opération n'a pas eu lieu.";
        }
        return mess;
    }
}
