<%-- 
    Document   : ver-laboratorista
    Created on : 5 oct. 2020, 10:00:31
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
        <%@include file="css/css-radio-button.html" %>

        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${personaSession != null}" >
            <%@include file="html/paciente-html-navbar.html" %>
            <br>
            <br>
            <br>
            <br>
            <h3><strong>Nombre</strong>  ${examen.getNombre()}</h3>
            <h3><strong>Costo</strong>${examen.getCosto()}</h3>
            <h3><strong>Requiere Orden</strong>${examen.isOrden()}</h3>
            <table class="table table-striped">
                <thead>
                    <tr>


                        <th scope="col">DPI</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Registro</th>
                        <th scope="col">Disponible</th>
                        <th scope="col">Dias que labora</th>
                        <th scope="col">Reservar Fecha</th>
                        <th scope="col">Requiere Orden</th>
                        <th scope="col">Crear</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${laboratoristas}">



                        <tr>
                    <form name="form" action="CitaController?accion=lab=${item.getRegistro()}" method="POST" enctype="multipart/form-data">

                        <th scope="row" >${item.getDpi()}</th>
                        <td>${item.getNombre()}</td>
                        <td>${item.getRegistro()}</td>
                        <td>
                            <c:if test="${item.isDisponible() == true}">

                                <label class="container-radio">
                                    <input type="checkbox" name="sinName" readonly checked>
                                    <span class="checkmark-radio"></span>
                                </label>
                            </c:if>
                            <c:if test="${item.isDisponible() == false}"> 

                                <label class="container-radio">
                                    <input type="checkbox" name="radio" readonly  >
                                    <span class="checkmark-radio"></span>
                                </label>
                            </c:if>

                        </td>
                        <td ><input type="text" id="dias" value="${item.getDiasDeTrabajo()}" readonly> </td>
                        <td> 


                            <label for="inicio"><b>Fecha de la Cita </b></label>
                            <input type="date" name="fecha" id="mi_calendar" onchange="findDay('${item.getDiasDeTrabajo()}')">
                            
                            <br>
                        </td>

                        <td>
                            <c:if test="${orden == 1}">

                                <!--
                                 <label class="container-radio">
                                     <input type="checkbox" name="radio" readonly  value="1">
                                     <span class="checkmark-radio"></span>
                                 </label>-->
                                <input type="file" name="ordenFile" required> 


                            </c:if>
                            <c:if test="${orden == 0}"> 

                                <label class="container-radio">
                                    <input type="checkbox" name="radio" readonly  value="0">
                                    <span class="checkmark-radio"></span>
                                </label>
                            </c:if>
                        </td>
                        </td>
                        <td>

                            <div id="oculto" style="display: none;">

                                <input type="submit" class="btn btn-outline-primary" value="Reservar Cita" id="btnSubmit" >

                            </div>

                        </td>
                    </form>
                </tr>


                
            </c:forEach>
                <p id="demo"></p>

            <%@include file="js/js-find-day.html" %>   
            <%@include  file="js/js-bootstrap.html" %>
        </c:if>
</body>
</html>
