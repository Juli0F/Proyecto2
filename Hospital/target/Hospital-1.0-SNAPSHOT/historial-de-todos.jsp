<%-- 
    Document   : historial-de-todos
    Created on : 7 oct. 2020, 8:18:18
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

        <%@include file="html/navbar-medico-html.html" %>

        <c:forEach var="item" items="${historial}">





            <h3 style="color:#338bff">Paciente</h3>
            <h3 style="color:#338bff">${item.getCodigo()}</h3>
            <h3 style="color:#338bff">${item.getNombrePaciente()}</h3>
            <h3 style="color:#338bff">${item.getGenero()}</h3>
            <h3 style="color:#338bff">${item.getNacimiento()}</h3>

            <table class="table">
                <thead class="black white-text" style="background-color: black; font-style: oblique; color:#eee ">



                    <tr style="background-color: gray;  color:#eee">
                        <th scope="col">Historial </th>
                        <th scope="col">De</th>
                        <th scope="col">Consultas</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    <tr>
                        <th scope="col">  </th>
                        <th scope="col">  </th>
                        <th scope="col"> </th>
                        <th scope="col"> </th>
                        <th scope="col"> </th>
                    </tr>


                </thead>
            </table>


            <!--tabla para las Consultas -->
            <table class="table">
                <thead class="grey lighten-2">
                    <tr>
                        <th scope="col">Codigo de Consulta </th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Costo</th>
                        <th scope="col">Informe</th>
                        <th scope="col">fecha</th>
                        <th scope="col">Codigo cita</th>
                        <th scope="col">Colegiado</th>
                        <th scope="col">Medico</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="consultas" items="${item.getConsultas()}">
                        <tr>
                            <th scope="row">${consultas.getCodigoConsulta()}</th>

                            <td>${consultas.getTipo()}</td>
                            <td>${consultas.getCosto()}</td>
                            <td>${consultas.getInforme()}</td>
                            <td>${consultas.getFecha()}</td>
                            <td>${consultas.getCodigoCita()}</td>
                            <td>${consultas.getColegiado()}</td>
                            <td>${consultas.getNombre()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot style="background-color: gray;  color:#eee">
                    <tr>
                        <th scope="col">Historial </th>
                        <th scope="col">De</th>
                        <th scope="col">Examenes</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    <tr>
                        <th scope="col">  </th>
                        <th scope="col">  </th>
                        <th scope="col"> </th>
                        <th scope="col"> </th>
                        <th scope="col"> </th>
                    </tr>
                </tfoot>
            </table>




            <!--tabla para los examenes -->
            <table class="table">
                <thead class="grey lighten-2">
                    <tr>

                        <th scope="col">Codigo Examen</th>
                        <th scope="col">Examen</th>
                        <th scope="col">fecha</th>
                        <th scope="col">Costo</th>
                        <th scope="col">Ver archivo</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${item.getResultados()}"  var="result">
                        <tr>
                            <th scope="row">${result.getCodigoExamen()}</th>
                            <td>${result.getNombreExamen()}</td>
                            <td>${result.getFecha()}</td>
                            <td>${result.getCosto()}</td>
                            <td><a href="archivos/${result.getArchivo()}" target="__blank">ver</a></td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <br><br><br><br>
            
        </c:forEach>
        <%@include file="js/js-bootstrap.html" %>
    </body>
</html>
