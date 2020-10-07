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
        <%@include file="html/paciente-html-navbar.html" %>
        <br><br>
        <div class="container">
            <div class="row">


                <form action="PacienteController?accion=porTipoFecha" method="post">

                    <div class="col">

                        <label for="tipo">Hasta: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                
                            </div>
                            <input type="text" class="form-control" id="tipo" aria-describedby="basic-addon3" name="tipo">
                        </div>

                    </div>
                    <div class="col">
                        
                        <label for="inicio">Buscar Desde: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                
                            </div>
                            <input type="date" class="form-control" id="inicio" aria-describedby="basic-addon3" name="inicio">
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

                <c:if test="${porTipo == null}">
                    <h3>
                        Esperando datos...
                    </h3>
                </c:if>
                <c:if test="${porTipo != null}">
                    


                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Codigo Examen</th>
                                <th scope="col">Tipo</th>
                                <th scope="col">Registro</th>
                                <th scope="col">Laboratorista</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${porTipo}">
                                <tr>
                                    <td>${item.getCodigoExamen()}</td>
                                    <td>${item.getTipo()}</td>
                                    <td>${item.getRegistro()}</td>
                                    <td>${item.getLab()}</td>
                                    <td>${item.getFecha()}</td>
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
