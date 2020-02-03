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
     * afficher les données d'une requête
     *
     * @param sql an sql query
     */
    void checkData(String sql);

    /**
     * Creation d'une connecxion et d'un Objet Statement pour les requetes
     * (debut)
     */
    void startStatement();

    /**
     * terminer l'ensemble des requetes avec endStatement
     */
    void endStatement();

    /**
     * Service drop table
     *
     * @param sql an sql query
     */
    void droptable(String sql);

    /**
     * Executer une commande sql
     *
     * @param sql sql auery
     * @throws Exception erreur dans la requete
     */
    void executeSQLCommand(String sql) throws Exception;

    /**
     * Affichage des resultats d'une requete générique (un pretyprint)
     *
     * @param rs The resultSet
     * @throws Exception
     */
    void outputResultSet(ResultSet rs) throws Exception;

    /**
     * Requête setup
     *
     * @param sql la requete sql
     */
    void setup(String sql);

}
