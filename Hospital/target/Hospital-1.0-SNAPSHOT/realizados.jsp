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
        <%@include file="html/navbarLaboratorio.html" %>

        <div class="row">
            <div class="col">
                <input  type="button"  class="btn btn-outline-primary"  id="opcion1" style="display: block;" value="Ver Todos" onclick="ocultar()"><a href="LaboratoristaController?accion=realizados"></a></input>

                <form action="LaboratoristaController?accion=realizados" method="POST">
                    <input  type="submit"  class="btn btn-outline-primary"  id="opcion2" style="display: block;" value="Ver Mios" onclick="mostrar()">
                </form>



            </div>
        </div>
        <div class="container">
            <table class="table">
                <thead class="black white-text" style="background-color: black; font-style: oblique; color:#eee ">



                    <tr style="background-color: gray;  color:#eee">
                        <th scope="col">Codigo Paciente </th>
                        <th scope="col">Paciente</th>
                        <th scope="col">Examen</th>
                        <th scope="col">Resultado</th>

                    </tr>

                </thead>
                <c:forEach var="item" items="${realizados}">



                    <tbody>

                        <tr>


                            <td>${item.getCodigoPaciente()}</td>
                            <td>${item.getNombre()}</td>
                            <td>${item.getExamen()}</td>
                            <td><a href="archivos/${item.getFormato()}" target="__blank">Ver: ${item.getFormato()}</a></td>
                        </tr>



                    </c:forEach>
                </tbody>
                <tfoot style="background-color: gray;  color:#eee">
                    <tr>
                        <th scope="col">Codigo Paciente </th>
                        <th scope="col">Paciente</th>
                        <th scope="col">Examen</th>
                        <th scope="col">Resultado</th>

                    </tr>

                </tfoot>
            </table>
        </div>
        <br><br><br><br>
        <%@include  file="js/btns.html" %>
        <%@include file="js/js-bootstrap.html" %>
    </body>
</html>
