package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JPAUtil {

    // Database configuration
    public static final String URL = "jdbc:mysql://localhost/testdb?useSSL=false";
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USERNAME = "tomcat";
    public static final String PASSWORD = "pourtomcat";
    static Statement st;
    static Connection conn;
/*
    static {
        try {
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = conn.createStatement();
        } catch (ClassNotFoundException|SQLException ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        } 
    }
*/
    public static void setup(String sql) {
        try {
            createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void createStatement() {
        try {
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Drop table if exists
    public static void droptable(String sql) {
        try {
            createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
        }
    }

    public void executeSQLCommand(String sql) throws Exception {
        st.executeUpdate(sql);
    }

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
                    overwrite(line, colpos[i] + colwidths[i], " |");
                }
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
