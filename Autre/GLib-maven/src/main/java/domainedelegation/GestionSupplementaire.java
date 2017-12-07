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

public class GestionSupplementaire {

    //public GestionSupplementaire() {
    //}

    public static void main(String args[]) throws GeneralSecurityException, IOException {
        //System.out.println(ajoutMembreAGroupe("test@isae.edu.lb", "sidemo17@isae.edu.lb", "OWNER"));
        //System.out.println(listeGroupes());
        //System.out.println(testerExistenceMembreGroupe("test@isae.edu.lb", "sidemo17@isae.edu.lb"));
        //System.out.println(testerExistenceMembreGroupe("2017-SMB215-2@isae.edu.lb", "pascal.fares@isae.edu.lb"));
        //System.out.println(testerExistenceMembreGroupe("test@isae.edu.lb", "pascal.fares@isae.edu.lb"));
        //listeGroupesParPersonne("pascal.fares@isae.edu.lb");
        //System.out.println(listeGroupesSansOwner());
        
        //List<String> l=listeGroupesParPersonne("nehmat@isae.edu.lb");
        //for (String l0:l) {
            //System.out.println(l0);
        //}
        
    }

    public static String ajoutMembreAGroupe(String groupEmail, String membreEmail, String membreRole) throws IOException {
        String mess;

        try {
            Directory d = GestionDomaine.initDirectory();

            Member member = new Member();
            member.setEmail(membreEmail);
            member.setRole(membreRole);

            d.members().insert(groupEmail, member).execute();
            mess = "L'ajout du membre au groupe a eu lieu avec succès.";

        } catch (Exception exception) {
            String erreur = exception.getMessage();
            if (erreur.substring(0, 3).equals("404")) {
                mess = "ATTENTION: ECHEC - L'ajout du membre n'a pas eu lieu car ce groupe n'existe pas.";
            } else if (erreur.substring(0, 3).equals("409")) {
                mess = "ATTENTION: ECHEC - Ce membre existe déjà dans ce groupe.";
            } else {
                //Erreur 400: Directory non trouver ou il ya des parametres vides ou non valides ou autre raison d'echec
                mess = "ATTENTION: ECHEC - L'ajout du membre n'a pas eu lieu.";
            }
        }
        return mess;
    }

    public static String listeGroupes() throws IOException, GeneralSecurityException {
        //Cette méthode liste tous les groupes du domaine avec le propriétaire de chaque groupe
        //elle est lente
        //elle n'est pas utilisée dans les applications (le 15 juin 2017)
        String mess = "";

        try {
            Directory d = GestionDomaine.initDirectory();
            Groups r = d.groups().list().setDomain("isae.edu.lb").execute();
            List<Group> groups = r.getGroups();

            for (Group group : groups) {
                Members mm = d.members().list(group.getEmail()).execute();
                List<Member> mem = mm.getMembers();
                if (mem != null) {
                    if (!mem.isEmpty()) {
                        for (Member m : mem) {
                            if (m.getRole().equals("OWNER")) {
                                mess += group.getEmail() + " - " + group.getName() + " - " + m.getEmail() + "\n";
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception exception) {
            mess = "ATTENTION: ECHEC - Impossible d'afficher la liste des groupes.";
        }
        return mess;
    }
    
    public static List<String> listeGroupesParPersonne(String EmailPersonne) throws IOException, GeneralSecurityException {
        //Cette méthode liste tous les groupes qu'une personne possède comme étant propriétaire
        String mess="";
        List<String> l=new LinkedList<>();
        try {
            Directory d = GestionDomaine.initDirectory();
            Groups userGroups = d.groups().list().setUserKey(EmailPersonne).execute();
            
            List<Group> g=userGroups.getGroups();
            
            for (Group obj : g) {
                Member m=d.members().get(obj.getEmail(), EmailPersonne).execute();
                //l.add(obj.getEmail()+" -- "+m.getRole());
                if (m.getRole().equals("OWNER")) {
                    l.add(obj.getEmail());
                }
            }
            
        } catch (Exception exception) {
            mess = "ATTENTION: ECHEC - Impossible d'afficher la liste des groupes pour: "+EmailPersonne;
        }
        return l;
    }

    public static int testerExistenceMembreGroupe(String groupEmail, String membreEmail) throws GeneralSecurityException, IOException {
        int existe = 0;

        try {
            Directory d = GestionDomaine.initDirectory();
            Member m=d.members().get(groupEmail, membreEmail).execute();
            if (m.getRole().equals("OWNER")) {
                existe = 1;
            }
            if (m.getRole().equals("MEMBER")) {
                existe = 2;
            }
        } catch (Exception exception) {
        }
        return existe;
    }
    
    public static String listeGroupesSansOwner() throws IOException, GeneralSecurityException {
        //Cette méthode liste tous les groupes du domaine
        //elle n'est pas utilisée dans les applications (le 15 juin 2017)
        String mess = "";

        try {
            Directory d = GestionDomaine.initDirectory();
            Groups r = d.groups().list().setDomain("isae.edu.lb").execute();
            List<Group> groups = r.getGroups();

            for (Group group : groups) {
                mess += group.getEmail() + " - " + group.getName() + "\n";
            }
        } catch (Exception exception) {
            mess = "ATTENTION: ECHEC - Impossible d'afficher la liste des groupes.";
        }
        return mess;
    }
}
