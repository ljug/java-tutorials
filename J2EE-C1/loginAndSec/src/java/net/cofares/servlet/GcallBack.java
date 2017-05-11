/*
 * Copyright © <Pascal Fares @ ISSAE - Cnam Liban>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package net.cofares.servlet;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.cofares.domain.User;
import net.cofares.jsf.web.authentication.LoginManager;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author pascalfares
 */
public class GcallBack extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException, ExecutionException, JSONException, SQLException, NamingException, ClassNotFoundException {

        /*
         match the google callback successful to Tomcat credential: get username and password 
        from Gmail:
        
        1- fetch code 
        2- connect to Google and fecth current user Gmail
        3- fetch user/pssd from DB given his Gmail
        4- authenticate to tomcat programmatically - see Login Servlet to Tomcat
         */
        String glgCode = request.getParameter("code");
        //System.out.println(FacesContext.getCurrentInstance().getExternalContext());
        if (glgCode != null) {
            //System.out.println("GoogleCallback glgCode=" + glgCode);
            HttpSession sess = request.getSession();
            OAuth20Service service = (OAuth20Service) sess.getAttribute("oauth2Service");
            if (service != null) {
                //Construct the access token
                OAuth2AccessToken accessToken = service.getAccessToken(glgCode);
                //System.out.println("Got the Access Token!");
                //Save the token for the duration of the session
                sess.setAttribute("token", accessToken);
                //System.out.println("GoogleCallback accessToken=" + accessToken);
                String requestUrl = "https://www.googleapis.com/oauth2/v1/userinfo";
                final OAuthRequest oAuthrequest = new OAuthRequest(Verb.GET, requestUrl);
                service.signRequest(accessToken, oAuthrequest);
                final Response OAuthResponse = service.execute(oAuthrequest);
                //fetch the gmail from google response
                JSONObject bodyJsonObject = new JSONObject(OAuthResponse.getBody());
                //System.out.println(bodyJsonObject.get("email").toString());
                String gmail = bodyJsonObject.get("email").toString();

                request.login("admin", "admin");
                
                request.getSession(true).setAttribute("user", new User("admin"));

                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Sucess login</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Servlet GoogleCallback: " + request.getContextPath() + ".<br> S.R.</h1>");
                    out.println("<h1>" + gmail + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }

            } else {
                printToOutput(request, response);
            }
        } else {
            printToOutput(request, response);
        }
    }

    public static void printToOutput(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getContextPath() + "GoogleCallback : Code is null!");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GoogleCallback : Code is null!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GoogleCallback: code is invalid at " + request.getContextPath() + ".<br> S.R.</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void handleNotPermitted(HttpServletRequest request, HttpServletResponse response, String res_msg, String gmail, HttpSession sess) throws ServletException, IOException, InterruptedException, ExecutionException, JSONException, SQLException, NamingException, ClassNotFoundException {
        String redirectUrl = request.getContextPath() + "/login/login.jsp";
        request.logout();
        sess.setAttribute("message", res_msg);
        response.sendRedirect(redirectUrl);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GcallBack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
