<%-- 
    Document   : Citas-intervalo-tiempo
    Created on : 6 oct. 2020, 15:44:51
    Author     : julio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%@include file="css/css-bootstrap.html" %>\
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="html/navbar-medico-html.html" %>
        <div class="container">
            <div class="row">


                <form action="MedicosInforme?accion=intervalo" method="post">

                    <div class="col">
                        <label for="basic-url">Buscar Desde: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                
                            </div>
                            <input type="date" class="form-control" id="basic-url" aria-describedby="basic-addon3" name="inicio">
                        </div>
                    </div>

                    <div class="col">

                        <label for="basic-url">Hasta: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                
                            </div>
                            <input type="date" class="form-control" id="basic-url" aria-describedby="basic-addon3" name="final">
                        </div>

                    </div>
                    <input type="submit" class="btn btn-outline-primary" value="Mostrar">
                </form>

                <c:if test="${citas == null}">
                    <h3>
                        Esperando datos...
                    </h3>
                </c:if>
                <c:if test="${citas != null}">


                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Codigo De Cita</th>
                                <th scope="col">Codigo Paciente</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Tipo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${citas}">
                                <tr>
                                    <td>${item.getCodigoCita()}</td>
                                    <td>${item.getCodigoPaciente()}</td>
                                    <td>${item.getNombre()}</td>
                                    <td>${item.getFecha()}</td>
                                    <td>${item.getTipo()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>



            </div>
        </div>
         <%@include file="js/js-bootstrap.html" %>

    </body>
</html>
