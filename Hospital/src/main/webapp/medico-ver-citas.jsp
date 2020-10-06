<%-- 
    Document   : medico-ver-citas
    Created on : 6 oct. 2020, 15:25:06
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <c:if test="${personaSession != null}" >
             <%@include file="html/navbar-medico-html.html" %>
            <br>
            <br>
            <br>
            <br>
            <h3><strong>Citas Pendientes</strong>  ${examen.getNombre()}</h3>
            <h3><strong>Costo</strong>${examen.getCosto()}</h3>
            <h3><strong>Requiere Orden</strong>${examen.isOrden()}</h3>
            <table class="table table-striped">
                <thead>
                    <tr>


                        <th scope="col">DPI</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Tipo Consulta</th>
                        <th scope="col">Codigo Cita</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Hora</th>
                        <th scope="col">Atender</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${citasPac}">



                        <tr>
                    <form name="form" action="CitaController?accion=lab=${item.getPaciente()}" method="POST">

                        <th scope="row" >${item.getDpi()}</th>
                        <td>${item.getNombre()}</td>
                        <td>${item.getConsulta()}</td>
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
                        <td> </td>
                        <td> 


                            <label for="inicio"><b>Fecha de la Cita </b></label>
                            <input type="Date" placeholder="12341234121201" name="fecha" id="inicio" required>
                            <br>
                        </td>

                        <td> 


                            <label for="inicio"><b>Hora de la Cita </b></label>
                            <input type="Date" placeholder="12341234121201" name="fecha" id="inicio" required>
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
        </c:if>
    </body>
</html>
