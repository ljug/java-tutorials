/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.model.Members;
import com.google.api.services.admin.directory.model.Groups;
import com.google.api.services.admin.directory.DirectoryScopes;
import com.google.api.services.admin.directory.model.Group;
import com.google.api.services.admin.directory.model.Member;
import com.google.api.services.admin.directory.model.User;
import com.google.api.services.admin.directory.model.UserName;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author Lab
 */
public class OperationBase {
        
    
    
    public OperationBase() {
        
    }
    
    /**
     * @param args the command line arguments
     * @throws java.security.GeneralSecurityException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        //d est le répertoire qui contient le fichier .p12 et que Google doit trouver en
        //utilisant les secrets "Credential"
       
        Directory d=initDirectory();
        //createUser(d);
        //modifyPassword(d);
        //createGroup(d);
        //addMember(d);
        //addMembersList(d);
        //deleteGroup(d);
        listGroups(d);
        
    }
    
   
    
    public static Directory initDirectory() throws GeneralSecurityException, IOException {
        
        HttpTransport httpTransport=new NetHttpTransport();
        JacksonFactory JSON_FACTORY=new JacksonFactory();

        // TODO initialize credentials
        //Credential credential = null; 
        //--------------------------------------------------------------
        List<String> l=new ArrayList<>();
        l.add(DirectoryScopes.ADMIN_DIRECTORY_USER);
        l.add(DirectoryScopes.ADMIN_DIRECTORY_GROUP);
        
        
        
        File f=new java.io.File("~/resources/uneCleProjet.p12");
        
        GoogleCredential credential=new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(JSON_FACTORY)
            .setServiceAccountId("GENEREPARGOOGLE-160810.iam.gserviceaccount.com")
            .setServiceAccountScopes(l)
            .setServiceAccountPrivateKeyFromP12File(f)
            .setServiceAccountUser("email@dman")
            .build();
        
        //fin credential
        //----------------------------------------------------------------------------
        
        Directory directory = new Directory.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName("My_App_Name")
            .build();

        return directory;
        
    }

    public static void createUser(Directory d) throws IOException {
        User user = new User();
       
        UserName name = new UserName();
        name.setGivenName("aa");
        name.setFamilyName("bb");
        user.setName(name);
        
        user.setPrimaryEmail("aa.bb@cnamliban.org");
        user.setPassword("premuierPassachanger"); 
        user.setOrgUnitPath("/Etudiants");

        // requires DirectoryScopes.ADMIN_DIRECTORY_USER scope  
        d.users().insert(user).execute();
       
    }
    
    public static void modifyPassword(Directory d) throws IOException {
        String emailVoulu="aa.bb@cnamliban.org";   //L’email qu’on veut modifier son password
        
        User user=d.users().get(emailVoulu).execute();
        user.setPassword("newPassword"); //minimum 8 caractères pour un mot de passe dans gmail
        d.users().update(emailVoulu, user).execute();
    }
    
    public static void createGroup(Directory d) throws IOException {
        Group group=new Group();
        
        group.setName("testdeleg2");
        group.setEmail("testdeleg2@cnamliban.org");
        group.setDescription("pour test delegation");
        
        d.groups().insert(group).execute();    
    }
    
    public static void addMember(Directory d) throws IOException {
        String groupEmail="testdeleg2@cnamliban.org";
        
        Member member=new Member();
        member.setEmail("sidemo8@cnamliban.org");
        member.setRole("OWNER");
        
        d.members().insert(groupEmail, member).execute();
    }
    
    public static void addMembersList(Directory d) throws IOException {

        String groupEmail="testdeleg2@cnamliban.org";
        
        List<String> l=new LinkedList<>();
        l.add("sidemo2@cnamliban.org");
        l.add("sidemo3@cnamliban.org");
        l.add("sidemo4@cnamliban.org");
        l.add("sidemo5@cnamliban.org");
        l.add("sidemo6@cnamliban.org");
        l.add("sidemo7@cnamliban.org");
        
        for (String l0:l) {
            Member member=new Member();
            member.setEmail(l0);
            
            d.members().insert(groupEmail, member).execute();
        }
        
        
    }
    
    public static void deleteGroup(Directory d) throws IOException {
        
        String groupEmail="testdeleg2@cnamliban.org";
        d.groups().delete(groupEmail).execute();
        
    }
    
    public static void listGroups(Directory d) throws IOException {
       
        //Cette méthode liste tous les groupes du domaine avec le propriétaire de chaque groupe
        
        Groups  r= d.groups().list().setDomain("isae.edu.lb").execute();  
        List<Group> groups=r.getGroups();
        
        for (Group group:groups) {
            
            Members mm=d.members().list(group.getEmail()).execute();
            List<Member> mem=mm.getMembers();
            if (mem!=null) {
                if (!mem.isEmpty()) {
                    for (Member m:mem) {    
                        if (m.getRole().equals("OWNER")) {
                            System.out.println(group.getEmail()+" - "+group.getName()+" - "+m.getEmail());
                            break;
                        }    
                    }
                }
            }
        }    
    }

  
}
    

