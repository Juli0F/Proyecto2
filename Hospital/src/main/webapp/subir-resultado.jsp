
<%-- 
    Document   : atender-citas
    Created on : 6 oct. 2020, 22:44:57
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/css-tablas.html" %>
        <%@include file="css/css-bootstrap.html" %>

        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="html/navbarLaboratorio.html" %>

        <div class="table-wrapper-scroll-y my-custom-scrollbar">
      
            <table id="dtHorizontalVerticalExample" class="table table-bordered table-striped mb-0" cellspacing="0"
                   width="100%">
                <thead>
                    <tr>
                        <th scope="col">Identificador de Examen</th>
                        <th scope="col">Examen</th>
                        <th scope="col">Codigo Paciente</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Genero</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Subir Informe</th>


                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listado}">
                    <form method="post" action="ResultadoController?accion=resultado" enctype="multipart/form-data">
                        <tr>
                            <th scope="row" >

                                <input type="text"  id="idExPac" name="idExPac"class="form-control"   value="${item.getIdExamenPaciente()}" readonly>

                            </th>

                            <td>
                                <input type="text"  id="examen" name="examen"class="form-control"   value="${item.getExamen()}" readonly="">

                            </td>      

                            <td>
                                <input type="text"  id="codigoPaciente" name="codigoPaciente" class="form-control"   value="${item.getCodigoPaciente()}" readonly="">
                            </td>
                            <td>
                                <input type="text"  id="nombre" name="nombre" class="form-control"   value="${item.getNombre()}" readonly="">
                            </td>
                            <td>
                                <input type="text"  id="genero" name="genero" class="form-control"   value="${item.getGenero()}" readonly="">
                            </td>

                            <td>
                                <input type="text"  id="fecha" name="fecha" class="form-control"   value="${item.getFecha()}" readonly="">
                            </td>



                            <td>


                                <div class="form-group">



                                    <c:if test="${item.getFormato() == 'PDF'}">

                                        <input type="file" name="resultFile" class="form-control-file" id="resultado" accept="application/pdf" required>

                                    </c:if>
                                    <c:if test="${item.getFormato() == 'IMG'}">

                                        <input type="file" name="resultFile" class="form-control-file" id="resultado" accept="image/jpeg,image/gif,image/png" required="">


                                    </c:if>

                                    <input type="submit" value="SUBIR ">





                                </div>

                            </td>




                        </tr>

                    </form>

                </c:forEach>
                </tbody>
            </table>
        </div>

        <%@include file="js/js-tablas.html" %>
        <%@include file="js/js-ocultar-div-informe.html" %>
        <%@include file="js/js-bootstrap.html" %>
    </body>
</html>
