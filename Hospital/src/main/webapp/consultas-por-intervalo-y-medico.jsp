<%-- 
    Document   : historial-de-todos
    Created on : 7 oct. 2020, 8:18:18
<td><a href="archivos/${result.getArchivo()}" target="__blank">ver</a></td>
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

        <br>
        <%@include file="html/paciente-html-navbar.html" %>
        <br><br><br>

        <div class="row col-xs-12"">
            <div class="center-block">


                <form action="PacienteController?accion=porMedico" method="POST">
                    <div class="col">
                        <div class="form-group">
                            <label for="medico">Medico:</label>
                            <input type="text" class="form-control" id="medico"   name="medico" placeholder="se buscara coincidencias" required>
                            <small id="medicoHelp" class="form-text text-muted">buscara coincidencias de carateres</small>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="inicio">Medico:</label>
                                <input type="date" class="form-control" id="inicio"   name="inicio">
                                <small id="medicoHelp" class="form-text text-muted">Donde iniciara el rango</small>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="final">Medico:</label>
                                <input type="date" class="form-control" id="final"  name="final">
                                <small id="medicoHelp" class="form-text text-muted">Fin del rango</small>
                            </div>
                            <input  type="submit"  class="btn btn-outline-primary"  id="opcion2" style="display: block;" value="Buscar" >

                        </div>
                    </div>
            </div>
        </form>
    </div>

    <div class="row"> 
        <div class="container">



            <table class="table">
                <thead class="black white-text" style="background-color: black; font-style: oblique; color:#eee ">



                    <tr style="background-color: gray;  color:#eee">
                        <th scope="col">Consulta </th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Hora</th>
                        <th scope="col">Medico</th>


                    </tr>

                </thead>
                <c:forEach var="item" items="${ultimos}">



                    <tbody>

                        <tr>


                            <td>${item.getConsulta()}</td>
                            <td>${item.getFecha()}</td>
                            <td>${item.getHora()}</td>
                            <td>${item.getMedico()}</td>

                        </tr>



                    </c:forEach>
                </tbody>
                <tfoot style="background-color: gray;  color:#eee">
                    <tr>
                        <th scope="col">Consulta </th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Hora</th>
                        <th scope="col">Medico</th>

                    </tr>

                </tfoot>
            </table>
        </div>
    </div>
    <br><br><br><br>
    <%@include  file="js/btns.html" %>
    <%@include file="js/js-bootstrap.html" %>
</body>
</html>
