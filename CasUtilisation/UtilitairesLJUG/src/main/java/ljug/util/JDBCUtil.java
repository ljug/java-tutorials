/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.util;

import java.sql.ResultSet;

/**
 *
 * @author pfares
 */
public interface JDBCUtil {

    /**
     * afficher les donnée d'une requête
     *
     * @param sql la requete sql
     */
    void checkData(String sql);

    /**
     * Creation d'une conection et d'un Opbjet Statement pour les requetes (debut)
     * termiber l'ensemble des requete avec
     * endStatement
     */
    void startStatement();
    void endStatement();
    // Drop table if exists
    void droptable(String sql);

    /**
     * Executer une commande sql
     *
     * @param sql
     * @throws Exception erreur dans la requete
     */
    void executeSQLCommand(String sql) throws Exception;

    /**
     * Affichage des resultat d'une requete générique (un prettyprint)
     *
     * @param rs
     * @throws Exception
     */
    void outputResultSet(ResultSet rs) throws Exception;

    /**
     * Requete update
     *
     * @param sql la requete sql
     */
    void setup(String sql);
    
}
