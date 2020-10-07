<%-- 
    Document   : index
    Created on : 30 sept. 2020, 20:58:28
    Author     : julio
<!-- <%@include file="html/Login.html" %> fda--!>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/login.html" %>
        <%@include file="css/home-anti-login.html" %>
        <%@include file="css/Registrar.html" %>
        <%@include file="css/navbar-anti-login.html" %>
        
        <title>JSP Page</title>
    </head>
    <body>

        <c:if test="${personaSession != null} ">
       <h3>No Puede Tener Dos sesiones abiertas.</h3>
       
        </c:if>
        <c:if test="${personaSession == null}" >

                <%@include file="html/navbar-anti-login.html" %>
                <%@include file="html/Home-anti-login.html" %>

                

                <%@include file="js/js-login.html" %>
                <%@include file="js/Registrar.html" %>
                <%@include file="js/home-anti-login.html" %>
                <%@include file="js/load-file.html" %>
        </c:if>
    </body>
</html>
