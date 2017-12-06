/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.util;

/**
 *
 * @author pfares
 */
public class JDBCUtilFactory {
    // Configuration  de la base de donnée par defaut pour tester (a l'origine par le developpeur)
    // A adapté pour votre besoin en fonction de votre environemnent
    // TODO : Lire ceci dans un fichier de propriété
    public  static final String URL = "jdbc:mysql://localhost/?useSSL=false";
    public  static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public  static final String USERNAME = "tomcat";
    public  static final String PASSWORD = "pourtomcat";
    
    public static JDBCUtil create() {
        JDBCUtil ju = new JDBCUtilImpl(DBDRIVER, URL, USERNAME, PASSWORD);
        return ju;
    }
    
    public static JDBCUtil create(String driver, String url, String username, String password) {
        JDBCUtil ju = new JDBCUtilImpl(driver, url, username, password);
        return ju;
    }
}
