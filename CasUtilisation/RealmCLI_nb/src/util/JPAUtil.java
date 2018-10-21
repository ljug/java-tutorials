package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tester en afichant le contenue des tables d'une base de donnée Mysql
 *
 * @author pfares
 */
public class JPAUtil {

    private static final Logger logger
            = Logger.getLogger(JPAUtil.class.getName());

    // Configuration  de la base de donnée par defaut pour tester (a l'origine par le developpeur)
    // A adapté pour votre besoin en fonction de votre environemnent
    // Lire ceci dans un fichier de propriété
    public static final String URL = "jdbc:mysql://localhost/?useSSL=false";
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USERNAME = "tomcat";
    public static final String PASSWORD = "pourtomcat";
    static Statement st;
    static Connection conn;

    /**
     * Requete update
     *
     * @param sql la requete sql
     */
    public static void setup(String sql) {
        try {
            createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            logger.log(Level.INFO, "Une exeption pour :" + sql, e);
            System.exit(0);
        }
    }

    public static void createStatement(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.INFO, "Une exeption pour createStatement", e);
            System.exit(0);
        }
    }

    public static void createStatement() {
        createStatement(DBDRIVER, URL, USERNAME, PASSWORD);
    }

    // Drop table if exists
    public static void droptable(String sql) {
        try {
            createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    /**
     * Executer une commande sql
     *
     * @param sql
     * @throws Exception erreur dans la requete
     */
    public void executeSQLCommand(String sql) throws Exception {
        st.executeUpdate(sql);
    }

    /**
     * afficher les donnée d'une requête
     *
     * @param sql la requete sql
     */
    public static void checkData(String sql) {
        String[] starray = sql.split(" ");
        System.out.println("\n******** Table: " + starray[starray.length - 4] + " *******");
        try {
            createStatement();
            ResultSet rs = st.executeQuery(sql);
            outputResultSet(rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Affichage des resultat d'une requete générique (un prettyprint)
     *
     * @param rs
     * @throws Exception
     */
    public static void outputResultSet(ResultSet rs) throws Exception {
        ResultSetMetaData metadata = rs.getMetaData();

        int numcols = metadata.getColumnCount();
        String[] labels = new String[numcols];
        int[] colwidths = new int[numcols];
        int[] colpos = new int[numcols];
        int linewidth;

        linewidth = 1;
        for (int i = 0; i < numcols; i++) {
            colpos[i] = linewidth;
            labels[i] = metadata.getColumnLabel(i + 1); // get its label
            int size = metadata.getColumnDisplaySize(i + 1);
            if (size > 30 || size == -1) {
                size = 30;
            }
            int labelsize = labels[i].length();
            if (labelsize > size) {
                size = labelsize;
            }
            colwidths[i] = size + 1; // save the column the size
            linewidth += colwidths[i] + 2; // increment total size
        }

        StringBuffer divider = new StringBuffer(linewidth);
        StringBuffer blankline = new StringBuffer(linewidth);
        for (int i = 0; i < linewidth; i++) {
            divider.insert(i, '-');
            blankline.insert(i, " ");
        }
        // Put special marks in the divider line at the column positions
        for (int i = 0; i < numcols; i++) {
            divider.setCharAt(colpos[i] - 1, '+');
        }
        divider.setCharAt(linewidth - 1, '+');

        // Begin the table output with a divider line
        System.out.println(divider);

        // The next line of the table contains the column labels.
        // Begin with a blank line, and put the column names and column
        // divider characters "|" into it. overwrite() is defined below.
        StringBuffer line = new StringBuffer(blankline.toString());
        line.setCharAt(0, '|');
        for (int i = 0; i < numcols; i++) {
            int pos = colpos[i] + 1 + (colwidths[i] - labels[i].length()) / 2;
            overwrite(line, pos, labels[i]);
            overwrite(line, colpos[i] + colwidths[i], " |");
        }
        System.out.println(line);
        System.out.println(divider);

        while (rs.next()) {
            line = new StringBuffer(blankline.toString());
            line.setCharAt(0, '|');
            for (int i = 0; i < numcols; i++) {
                Object value = rs.getObject(i + 1);
                if (value != null) {
                    overwrite(line, colpos[i] + 1, value.toString().trim());
                }
                overwrite(line, colpos[i] + colwidths[i], " |");
            }
            System.out.println(line);
        }
        System.out.println(divider);

    }

    static void overwrite(StringBuffer b, int pos, String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            b.setCharAt(pos + i, s.charAt(i));
        }
    }
}
