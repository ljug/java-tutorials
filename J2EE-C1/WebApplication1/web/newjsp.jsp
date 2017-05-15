<%-- 
    Document   : newjsp
    Created on : May 15, 2017, 4:06:50 PM
    Author     : pascalfares
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Name <%=request.getServerName()%>
        Port <%=request.getServerPort()%>
        Port <%=request.getRequestURL().toString().replace(request.getRequestURI(),"")%>
        Port <%=request.getRequestURI()%>
        
    </body>
</html>
