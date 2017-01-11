<%-- 
    Document   : index
    Created on : Jan 5, 2017, 6:26:57 PM
    Author     : pascalfares
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="mb" class="net.cofares.MonBean" scope="request" >
        </jsp:useBean>
        <jsp:setProperty property="*"  name="mb" />
    </head>
    <body>
        <h1>Hello World! xxx</h1>
      
        <h1> x est <%= mb.getX() %> nom est <%= mb.getName()%> </h1>
        ${mb.name}
    </body>

</html>
