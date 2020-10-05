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
        <%@include file="css/perfil.html" %>>
        <title>Hospital-Usuario ${usuario.getCodigo()}</title>
    </head>
    <body>
        <!--  Menu Para el Paciente -->
        <c:if test="${requestScope['paciente'] != null}">

            <%@include file="paciente.jsp" %>
<!--
            <p>Cuenta De: </p>


     

            <div class="tab">
                <button class="tablinks" onclick="openCity(event, 'home')">Mi Cuenta</button>
                <a href="PacienteController?citaMedica=cita"><button class="tablinks" onclick="openCity(event, 'citaMedica')">Agendar Cita Medica</button>
                    <button class="tablinks" onclick="openCity(event, 'citaLab')">Agendar Cita en Laboratorios</button>
                    <button class="tablinks" onclick="openCity(event, 'informe')">Informes</button>
            </div>

            <div id="citaMedica" class="tabcontent">
                <h3>Cita Medica</h3>



            </div>

            <div id="citaLab" class="tabcontent">
                <p>Paris is the capital of France.</p> 
            </div>

            <div id="home" class="tabcontent">
                <h3>Mi Cuenta</h3>
                <p>Tokyo is the capital of Japan.</p>
            </div>

            <div id="informe" class="tabcontent">
                <h3>Informes</h3>
                <p>Tokyo is the capital of Japan.</p>
            </div>
              

-->
        </c:if>
        <!--  Fin Pacientes -->

        <!--  Menu Para el Medico -->
        <c:if test="${requestScope['medico'] != null}">
            <h2>Tabs</h2>
            <p>Click on the buttons inside the tabbed menu:</p>

            <div class="tab">
                <button class="tablinks" onclick="openCity(event, 'London')">London</button>
                <button class="tablinks" onclick="openCity(event, 'Paris')">Paris</button>
                <button class="tablinks" onclick="openCity(event, 'Tokyo')">Tokyo</button>
            </div>

            <div id="London" class="tabcontent">
                <h3>London</h3>
                <p>London is the capital city of England.</p>
            </div>

            <div id="Paris" class="tabcontent">
                <h3>Paris</h3>
                <p>Paris is the capital of France.</p> 
            </div>

            <div id="Tokyo" class="tabcontent">
                <h3>Tokyo</h3>
                <p>Tokyo is the capital of Japan.</p>
            </div>

        </c:if>
        <!--  Fin Medico -->

        <!--  Menu Para el laboratorista -->
        <c:if test="${requestScope['laboratorista'] != null}">
            <h2>${laboratorista.getRegistro()}</h2>
            <p>opciones</p>

            <div class="tab">
                <button class="tablinks" onclick="openCity(event, 'London')">London</button>
                <button class="tablinks" onclick="openCity(event, 'Paris')">Paris</button>
                <button class="tablinks" onclick="openCity(event, 'Tokyo')">Tokyo</button>
            </div>

            <div id="London" class="tabcontent">
                <h3>London</h3>
                <p>London is the capital city of England.</p>
            </div>

            <div id="Paris" class="tabcontent">
                <h3>Paris</h3>
                <p>Paris is the capital of France.</p> 
            </div>

            <div id="Tokyo" class="tabcontent">
                <h3>Tokyo</h3>
                <p>Tokyo is the capital of Japan.</p>
            </div>

        </c:if>

        <!--  Menu Para el laboratorista -->
        <c:if test="${requestScope['admin'] != null}">
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
