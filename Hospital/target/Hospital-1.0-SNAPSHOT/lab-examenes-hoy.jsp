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
            <%@include file="css/css-bootstrap.html" %>
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <%@include file="html/navbarLaboratorio.html" %>
        <div class="container">
            <div class="row">


               

                
                <c:if test="${citasHoy == null}">
                    <h3>
                        No hay citas para hoy
                    </h3>
                </c:if>
                <c:if test="${citasHoy != null}">


                    <table class="table table-striped">
                        <thead>
                            <tr>
                                
                                <th scope="col">Codigo Paciente</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Tipo</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${citasHoy}">
                                <tr>
                                    
                                    <td>${item.getCodigoPaciente()}</td>
                                    <td>${item.getPaciente()}</td>
                                    <td>${item.getExamenNombre()}</td>
                                    
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
