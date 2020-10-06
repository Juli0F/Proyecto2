<%-- 
    Document   : crear-cita
    Created on : 4 oct. 2020, 21:24:43
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
            <%@include file="archivos/" %>
            <br>
            <br>
            <br>
            <br>
            <h3><Strong>Doctor</Strong>${personaM.getNombre()}</h3>
            <h3><Strong>Colegiado</Strong>${medico.getColegiado()}</h3>





            <table class="table table-striped" >
                <thead>
                    <tr>
                        <th scope="col">#</th>

                        <th scope="col">Tipo</th>
                        <th scope="col">Costo</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Hora</th>
                        <th scope="col">Seleccionar</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${consultas}">


                    <form  action="CitaController?accion=crearCita=${item.getIdConsulta()}" method="post">

                        <tr>
                            <td scope="row" >
                                <div class="md-form mt-0">
                                    <input class="form-control" type="text"  aria-label="Search" value="${item.getIdConsulta()}" name="idConsulta" readonly onmousedown="return false;">
                                </div>

                            </td>

                            <td ><div class="md-form mt-0">
                                    <input class="form-control" type="text"  aria-label="Search" value="${item.getTipo()}" name="tipo" readonly onmousedown="return false;">
                                </div> 
                            </td>

                            <td> 
                                <div class="md-form mt-0">
                                    <input class="form-control" type="text"  aria-label="Search" value="${item.getCosto()}" name="costo" readonly onmousedown="return false;">
                                </div>
                            </td>
                            <td> 


                                <label for="inicio"><b>Fecha de la Cita </b></label>
                                <input type="Date" placeholder="12341234121201" name="fecha" id="inicio" required>
                                <br>
                            </td>
                            <td>
                                <label for="hora"><b>Hora</b></label>
                                <input type="TIME"  id="hora" name="hora" required >
                                <br>
                            </td>
                            <td>

                                <label class="container-radio">
                                    <button type="submit" class="btn btn-outline-primary">Crear Cita</button>

                                    <span class="checkmark-radio"></span>
                                </label>


                            </td>
                        </tr>
                    </form>

                </c:forEach>


                <%@include file="js/js-bootstrap.html" %>
            </c:if>
    </body>
</html>
