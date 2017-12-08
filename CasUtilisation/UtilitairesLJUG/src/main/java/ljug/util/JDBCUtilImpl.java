package ljug.util;

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
class JDBCUtilImpl implements JDBCUtil {

    private static final Logger LOGGER
            = Logger.getLogger(JDBCUtilImpl.class.getName());

    // Configuration  de la base de donnée par defaut pour tester (a l'origine par le developpeur)
    // A adapté pour votre besoin en fonction de votre environemnent
    // TODO : Lire ceci dans un fichier de propriété
    String driver, url, username, password;
    Statement st;
    Connection conn;

    public JDBCUtilImpl(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Requete update
     *
     * @param sql la requete sql
     */
    @Override
    public void setup(String sql) {
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Une exeption pour :" + sql, e);
            System.exit(0);
        }
    }

    /**
     * Creation d'une conection et d'un Opbjet Statement pour les requetes
     *
     * @param driver : le driver JDBC
     * @param url lúrl de la BD
     * @param username l'utilisateur
     * @param password Le password
     */
    @Override
    public void startStatement() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.INFO, "Une exeption pour createStatement", e);
            System.exit(0);
        }
    }

    @Override
    public void endStatement() {
        try {
            conn.close();
        } catch (SQLException ex) {
             LOGGER.log(Level.INFO, "Une exeption pour endStatement", ex);
        }
    }

    // Drop table if exists
    @Override
    public void droptable(String sql) {
        try {
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
    @Override
    public void executeSQLCommand(String sql) throws Exception {
        st.executeUpdate(sql);
    }

    /**
     * afficher les donnée d'une requête
     *
     * @param sql la requete sql
     */
    @Override
    public void checkData(String sql) {
        String[] starray = sql.split(" ");
        System.out.println("\n******** Table: " + starray[starray.length - 1] + " *******");
        try {
            ResultSet rs = st.executeQuery(sql);
            outputResultSet(rs);
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
    @Override
    public void outputResultSet(ResultSet rs) throws Exception {
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
        StringBuilder blankline = new StringBuilder(linewidth);
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

    void overwrite(StringBuffer b, int pos, String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            b.setCharAt(pos + i, s.charAt(i));
        }
    }
}
