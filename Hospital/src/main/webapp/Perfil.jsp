<%-- 
    Document   : Perfil
    Created on : 1 oct. 2020, 10:08:40
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--  Menu Para el Paciente -->
        <c:if test="${requestScope['paciente'] != null}">
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
            <h2>${admin.getCodigo()}</h2>
            <p>Click on the buttons inside the tabbed menu:</p>

            <div class="tab">
                <button class="tablinks" onclick="openCity(event, 'Crear')">London</button>
                <button class="tablinks" onclick="openCity(event, 'Modificar')">Paris</button>
                <button class="tablinks" onclick="openCity(event, 'REportes')">Tokyo</button>
            </div>

            <div id="Crear" class="tabcontent">
                <h3>London</h3>
                <p>London is the capital city of England.</p>
            </div>

            <div id="Modificar" class="tabcontent">
                <h3>Paris</h3>
                <p>Paris is the capital of France.</p> 
            </div>

            <div id="Reportes" class="tabcontent">
                <h3>Tokyo</h3>
                <p>Tokyo is the capital of Japan.</p>
            </div>
            <%@include file="js/js-perfil.html" %>

        </c:if>
            <!--  Fin laboratorista -->
    </body>
</html>
