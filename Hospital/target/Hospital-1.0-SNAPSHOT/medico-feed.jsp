<%-- 
    Document   : feedback-cita
    Created on : 5 oct. 2020, 17:28:26
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/css-bootstrap.html" %>
        <title>JSP Page</title>
    </head>
    <body>

        <%@include file="html/navbar-medico-html.html" %>
        <br><br><br><br><br>
    <Center>
        <c:if test="${requestScope['registro']}">
            
        
        <div class="alert alert-success" role="alert">
            Se han registrado los datos correctamente
        </div>
        </c:if>
        <c:if test="${!requestScope['registro']}">
        <div class="alert alert-danger" role="alert">
           Ocurrio un error al Ingresar los datos al Sistema, Intentalo mas Tarde
        </div>
        </c:if>
    </Center>
    <%@include file="js/js-bootstrap.html" %>

</body>
</html>
