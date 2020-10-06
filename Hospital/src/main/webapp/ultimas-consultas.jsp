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
        <div class="container">
            <table class="table">
                <thead class="black white-text" style="background-color: black; font-style: oblique; color:#eee ">



                    <tr style="background-color: gray;  color:#eee">
                        <th scope="col">Codigo Examen </th>
                        <th scope="col">Examen</th>
                        <th scope="col">Fecha</th>

                    </tr>

                </thead>
                <c:forEach var="item" items="${ultimos}">



                    <tbody>

                        <tr>


                            <td>${item.getCodigoExamen()}</td>
                            <td>${item.getFecha()}</td>
                            <td>${item.getExamen()}</td>
                            
                        </tr>



                    </c:forEach>
                </tbody>
                <tfoot style="background-color: gray;  color:#eee">
                    <tr>
                       <th scope="col">Codigo Examen </th>
                        <th scope="col">Examen</th>
                        <th scope="col">Fecha</th>

                    </tr>

                </tfoot>
            </table>
        </div>
        <br><br><br><br>
        <%@include  file="js/btns.html" %>
        <%@include file="js/js-bootstrap.html" %>
    </body>
</html>
