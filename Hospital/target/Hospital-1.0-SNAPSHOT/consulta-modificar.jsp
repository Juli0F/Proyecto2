<%-- 
    Document   : consulta-modificar
    Created on : 7 oct. 2020, 10:00:57
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/css-bootstrap.html" %>
        <title>Modificar</title>
    </head>
    <body>




        <form action="ConsultaController?accion=buscar" method="post">



            <div class="col">

                <label for="basic-url">Hasta: </label>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">

                    </div>
                    <input type="search" class="form-control" id="basic-url" aria-describedby="basic-addon3" name="buscar">
                </div>

            </div>
            <input type="submit" class="btn btn-outline-primary" value="Buscar">
        </form>

        <div class="table-wrapper-scroll-y my-custom-scrollbar">

            <table class="table table-bordered table-striped mb-0">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Costo</th>



                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${consulta}">


                        <tr>
                    <form action="ConsultaController?accion=modificar" method="post">


                        <th scope="row" >
                            <input type="text" name="id" value="${item.getIdConsulta()}" readonly required>
                        </th>

                        <td>
                            <input type="text" name="tipo" value="${item.getTipo()}" required>
                        </td>
                        <td>
                            <input type="number" step="0.001" name="costo" value="${item.getCosto()}" required>
                        </td>

                        <td>

                            <input type="submit" class="btn btn-outline-primary" value="Modificar">

                        </td>

                    </form>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>


        <%@include file="js/js-bootstrap.html" %>

    </body>
</html>
