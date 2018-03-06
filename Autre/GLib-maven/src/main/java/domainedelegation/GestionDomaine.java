package domainedelegation;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.DirectoryScopes;
import com.google.api.services.admin.directory.model.Group;
import com.google.api.services.admin.directory.model.Member;
import com.google.api.services.admin.directory.model.Members;
import com.google.api.services.admin.directory.model.User;
import com.google.api.services.admin.directory.model.UserName;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class GestionDomaine {

    static final  boolean DEBUG=false;
    public GestionDomaine() {
    }

    public static Directory initDirectory() throws GeneralSecurityException, IOException {

        
        //String cheminCatalina = System.getProperty("catalina.base");
        String cheminCatalina = "/opt/apache-tomcat-8.0.18";
        String chemin = "/resources/";
        String nomFichierConfig = "config.properties";

        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory JSON_FACTORY = new JacksonFactory();

        //les credentials
        //---------------
        List<String> l = new ArrayList<>();
        l.add(DirectoryScopes.ADMIN_DIRECTORY_USER);
        l.add(DirectoryScopes.ADMIN_DIRECTORY_GROUP);

        Properties prop = new Properties();

        File fichierConfig = new File(cheminCatalina + chemin + nomFichierConfig);
        if (fichierConfig.exists()) {
            InputStream inputStream = new FileInputStream(fichierConfig);
            prop.load(inputStream);
        }

        File f = new java.io.File(cheminCatalina + chemin + prop.getProperty("serviceAccountPKS12Name"));

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(prop.getProperty("serviceAccountEmail"))
                .setServiceAccountScopes(l)
                .setServiceAccountPrivateKeyFromP12File(f)
                .setServiceAccountUser(prop.getProperty("userEmail"))
                .build();

        //fin credential
        //----------------------------------------------------------------------------
        Directory directory = new Directory.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName("My_App_Name")
                .build();

        return directory;

    }

    public static void main(String args[]) throws GeneralSecurityException, IOException {
        //System.out.println(creerUtilisateur("sidemo-ente@isae.edu.lb", "si", "demo16", "demo1616", "/Etudiants"));
        //System.out.println(changerMotDePasse("sidemo17@isae.edu.lb", "demo1717"));
        //System.out.println(creerGroupe("test", "test@isae.edu.lb", "test"));
        //System.out.println(supprimerGroupe("test@isae.edu.lb"));
        //System.out.println(testerExistenceEmail("sidemo17@isae.edu.lb"));
    }

    public static String miseAJourGroupe(String groupEmail, List<String> l) throws IOException, GeneralSecurityException {
        if (DEBUG) System.out.println("MAJ GROUPE");
        List<String> laajoute = l.stream().collect(Collectors.toList());
        Directory d = initDirectory();
        String mess = "";
        //l représente la nouvelle liste des étudiants
        //mem représente la liste courante des étudiants chez Google
        //l.stream().forEach(System.out::println);

        if (DEBUG) System.out.println("Taille de la liste souhété" + l.size());

        Members mm = d.members().list(groupEmail).execute();
        //mm.getNextPageToken();
        List<Member> mem = mm.getMembers();
        if (DEBUG) System.out.println("Taille de la liste dans google" + mem.size() + ":" + mm.getNextPageToken());
        while (mm.getNextPageToken() != null) {
            mm = d.members().list(groupEmail).setPageToken(mm.getNextPageToken()).execute();
            if (DEBUG) System.out.println("Taille de la liste dans google" + mem.size() + ":" + mm.getNextPageToken());
            mem.addAll(mm.getMembers());
        }

        List<String> oldList = mem.stream().map(m -> m.getEmail()).collect(Collectors.toList());
        //mem.stream().map(m -> m.getEmail()).forEach(System.out::println);
        laajoute.removeAll(oldList);
        if (DEBUG) System.out.println("Taille de la liste resultante (a ajouté)" + laajoute.size());

        oldList.removeAll(l);
        if (DEBUG) System.out.println("Taille de la liste resultante (a suprimé)" + oldList.size());

        if (!(laajoute.isEmpty() && oldList.isEmpty())) {
            BatchRequest batch = d.batch(new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                    request.setConnectTimeout(10 * 60000);
                    request.setReadTimeout(10 * 60000);

                }
            });

            /**
             * TODO : Comtabiliser le nombre total d'echec par rapport au nombre
             * de réussi Alrs message oindiquand le non=mbre d'ajout réussi
             */
            JsonBatchCallback<Member> callback = new JsonBatchCallback<Member>() {

                @Override
                public void onSuccess(Member content, HttpHeaders responseHeaders) {
                    //Pour l'instant ceci va dans catalina.out
                    //TODO comtabilier les réussi
                    if (DEBUG) System.out.println(content.getEmail() + ".... is successful!");
                }

                @Override
                public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
                    //TODO comptabiliser les echec
                    if (DEBUG) System.out.println("Error Message: " + e.getMessage());
                }
            };

            JsonBatchCallback<Void> callbackRm = new JsonBatchCallback<Void>() {

                @Override
                public void onSuccess(Void content, HttpHeaders responseHeaders) {
                    //Pour l'instant ceci va dans catalina.out
                    //TODO comtabilier les réussi
                    if (DEBUG) System.out.println(content + ".... is successful!");
                }

                @Override
                public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
                    //TODO comptabiliser les echec
                    if (DEBUG) System.out.println("Error Message: " + e.getMessage());
                }

            };
            if (DEBUG) System.out.print("1 ");
            for (String nm : laajoute) {
                Member member = new Member();
                member.setEmail(nm);
                d.members().insert(groupEmail, member).queue(batch, callback);
            }
            if (DEBUG) System.out.print("2 ");
            for (String md : oldList) {
                d.members().delete(groupEmail, md).queue(batch, callbackRm);
            }
            if (DEBUG)  System.out.print("3 ");
            try {
                //System.out.println(".........BEFORE BATCH EXECUTE ........."+batch.size());
                if (DEBUG) System.out.println(".........BEFORE BATCH EXECUTE ........." + batch.size());
                batch.execute();
                mess = "L'opération de l'insertion a eu lieu avec succès. ";
                if (DEBUG) System.out.println(".........AFTER BATCH EXECUTE ........." + batch.size());
            } catch (IOException ex) {
                //Logger.getLogger(GroupService.class.getName()).log(Level.SEVERE, null, ex);
                //System.out.println(".........ERROR BATCH EXECUTE ........."+batch.size()+"\n"+ex);
                //Bien qu'il y ai un IOExeption c'est OK et créer comme groupe dans google
                //resultCode = pack1.Message.CREATION_ECHEC;
                mess += "ATTENTION, Echec de l'opération de l'insertion. ";
            }
        }

        mess += "Nombre de membres inséré=" + laajoute.size() +  "<br />Nombre de membres supprimé"+oldList.size();

        return mess;
    }

    public static int testExistenceGroupe(String groupEmail) throws GeneralSecurityException, IOException {
        Directory d = initDirectory();
        int existe = 0;

        try {
            d.groups().get(groupEmail).execute();
            existe = 1;
            //System.out.println("groupe existe"+groupEmail);

        } catch (Exception exception) {
//            String errorMsg = exception.getMessage();
//            System.out.println(errorMsg);
//            System.out.println("message erreur"+errorMsg.substring(0, 3));
        }
        return existe;
    }

    public static String creerUtilisateur(String email, String prenom, String nom, String password, String chemin) throws IOException, GeneralSecurityException {
        String mess;

        try {
            Directory d = initDirectory();
            User user = new User();

            UserName name = new UserName();
            name.setGivenName(prenom);
            name.setFamilyName(nom);
            user.setName(name);

            user.setPrimaryEmail(email);
            user.setPassword(password);
            user.setOrgUnitPath(chemin);
            user.setChangePasswordAtNextLogin(true);

            d.users().insert(user).execute();
            mess = "La création de l'email a eu lieu avec succès.";

        } catch (Exception exception) {
            String erreur = exception.getMessage();
            if (erreur.substring(0, 3).equals("409")) {
                mess = "ATTENTION: ECHEC - La création n'a pas eu lieu car cet email existe déjà. Définir un nouveau email en changeant le suffixe.";
            } else if (erreur.substring(0, 3).equals("404")) {
                mess = "ATTENTION: ECHEC - La création n'a pas eu lieu car le domaine n'existe pas.";
            } else {
                //Erreur 400: Directory non trouver ou le mot de passe doit être au moins 8 caractères ou il ya des parametres vides ou autre raison d'echec
                mess = "ATTENTION: ECHEC - La création n'a pas eu lieu.";
            }
        }
        return mess;
    }

    public static String changerMotDePasse(String email, String password) throws IOException, GeneralSecurityException {
        String mess;

        try {
            Directory d = initDirectory();
            User user = d.users().get(email).execute();
            user.setPassword(password); //minimum 8 caractères pour un mot de passe dans gmail

            d.users().update(email, user).execute();
            mess = "La modification du mot de passe a eu lieu avec succès.";

        } catch (Exception exception) {
            String erreur = exception.getMessage();
            if (erreur.substring(0, 3).equals("403")) {
                mess = "ATTENTION: ECHEC - La modification du mot de passe n'a pas eu lieu car le domaine n'existe pas.";
            } else {
                //Erreur 400: Directory non trouver ou le mot de passe doit être au moins 8 caractères ou il ya des parametres vides ou autre raison d'echec
                mess = "ATTENTION: ECHEC - La modification du mot de passe n'a pas eu lieu.";
            }
        }
        return mess;
    }

    public static String creerGroupe(String nom, String email, String description) throws IOException, GeneralSecurityException {
        String mess;

        try {
            Directory d = initDirectory();
            Group group = new Group();

            group.setName(nom);
            group.setEmail(email);
            group.setDescription(description);

            d.groups().insert(group).execute();
            mess = "La création du groupe a eu lieu avec succès.";

        } catch (Exception exception) {
            String erreur = exception.getMessage();
            if (erreur.substring(0, 3).equals("409")) {
                mess = "ATTENTION: ECHEC - La création n'a pas eu lieu car ce groupe existe déjà.";
            } else {
                //Erreur 400: Directory non trouver ou il ya des parametres vides ou non valides ou autre raison d'echec
                mess = "ATTENTION: ECHEC - La création du groupe n'a pas eu lieu.";
            }
        }
        return mess;
    }

    public static String supprimerGroupe(String groupEmail) throws IOException {
        String mess;

        try {
            Directory d = initDirectory();

            d.groups().delete(groupEmail).execute();
            mess = "La suppression du groupe a eu lieu avec succès.";

        } catch (Exception exception) {
            String erreur = exception.getMessage();
            if (erreur.substring(0, 3).equals("404")) {
                mess = "ATTENTION: ECHEC - La suppression n'a pas eu lieu car ce groupe n'existe pas.";
            } else {
                //Erreur 400: Directory non trouver ou autre raison d'echec
                mess = "ATTENTION: ECHEC - La suppression n'a pas eu lieu.";
            }
        }
        return mess;
    }

    public static int testerExistenceEmail(String email) throws GeneralSecurityException, IOException {
        int existe = 0;

        try {
            Directory d = initDirectory();
            d.users().get(email).execute();
            existe = 1;
        } catch (Exception exception) {
        }
        return existe;
    }

    public static String supprimerMembre(String groupEmail, String memberEmail) {
        String mess = "";
        try {
            Directory d = initDirectory();
            d.members().delete(groupEmail, memberEmail).execute();
            mess = "L'opération a eu lieu avec succès.";

        } catch (Exception exception) {
            mess = "ATTENTION: ECHEC - L'opération n'a pas eu lieu.";
        }
        return mess;
    }

    public static int testerEmailLogin(String email) throws GeneralSecurityException, IOException {
        int dejaLogin = 0;

        try {
            Directory d = initDirectory();
            d.users().get(email).execute();
            if (d.users().get(email).execute().getAgreedToTerms() == true) {
                dejaLogin = 1;
            }
        } catch (Exception exception) {
        }
        return dejaLogin;
    }

}
