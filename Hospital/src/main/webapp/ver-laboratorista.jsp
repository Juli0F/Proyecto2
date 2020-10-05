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
                    <th scope="col">#</th>

                    <th scope="col">DPI</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Registro</th>
                    <th scope="col">Disponible</th>
                    <th scope="col">Dias que labora</th>
                    <th scope="col">Reservar Fecha</th>
                    <th scope="col">Crear</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${laboratoristas}">



                    <tr>
                        <form name="form" action="CitaController?accion=lab-${item.getRegistro()}" method="POST">
                            <th scope="row" >${item.getDpi()}</th>
                            <td>${item.getNombre()}</td>
                            <td>${item.getRegistro()}</td>
                            <td>

                                <label class="container-radio">
                                    <input type="checkbox" class="btn btn-outline-primary">

                                    <span class="checkmark-radio"></span>
                                </label>


                            </td>
                            <td> 


                                <label for="inicio"><b>Fecha de la Cita </b></label>
                                <input type="Date" placeholder="12341234121201" name="inicio" id="inicio" required>
                                <br>
                            </td>

                            <td>

                                <label class="container-radio">
                                    <input type="submit" class="btn btn-outline-primary">Crear Cita</button>

                                    <span class="checkmark-radio"></span>
                                </label>


                            </td>
                        </form>
            </tr>


        </c:forEach>

        <%@include  file="js/js-bootstrap.html" %>
</body>
</html>
