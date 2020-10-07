<%-- 
    Document   : Perfil
    Created on : 1 oct. 2020, 10:08:40
    Author     : julio
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page session="true" %>
<jsp:useBean id="persona" scope="session" class="com.hospital.entities.Persona" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/css-bootstrap.html" %>
        <%@include file="css/perfil.html" %>
        <title>Hospital-Usuario ${usuario.getCodigo()}</title>
    </head>
    <body>
        <br><br>
        
        <c:if test="${admin != null}">
        
            <%@include file="css/perfil.html" %>
            <%@include file="css/css-examen.html" %>
            <%@include file="css/css-crear-medico.html" %>
            <%@include file="css/css-crear-lab.html" %>
            <%@include file="css/Registrar.html" %>

            <%@include file="html/navbar-admin.html" %>
            <%@include file="js/js-crear-paciente.html" %>
            <%@include file="js/js-crear-examen.html" %>
            <%@include file="js/js-crear-medico.html" %>
            <%@include file="js/js-crear-lab.html" %>
            <%@include file="js/js-perfil.html" %>
            <%@include file="js/Registrar.html" %>
            
        </c:if>

        <!--  Fin laboratorista -->
        <%@include file="js/js-perfil.html" %>
        <%@include file="js/js-bootstrap.html" %>
    </body>
</html>
