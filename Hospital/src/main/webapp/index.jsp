<%-- 
    Document   : index
    Created on : 30 sept. 2020, 20:58:28
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/login.html" %>
        <%@include file="css/home-anti-login.html" %>
        <title>JSP Page</title>
    </head>
    <body>
        
        <%@include file="html/Login.html" %>
        <%@include file="html/Home-anti-login.html" %>
        <%@include file="js/js-login.html" %>
        <%@include file="js/home-anti-login.html" %>
    </body>
</html>
