<%-- 
    Document   : newjsp
    Created on : Dec 18, 2016, 2:08:16 PM
    Author     : pascalfares
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Une page JSB</title>
        <jsp:useBean  id="monBean" scope="request" class="" />
        <jsp:setProperty name="id" property="*" />
    </head>
    <body>
        <h1>Hello World!</h1> 
        {monBean} 
        
    </body>
</html>
