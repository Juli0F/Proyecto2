<%-- 
    Document   : cita-laboratorista
    Created on : 5 oct. 2020, 0:15:22
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
        <%@include file="css/css-radio-button.html" %>
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${personaSession != null}" >
            <%@include file="html/paciente-html-navbar.html" %>
            <br>
            <br>
            <br>
            <br>
            <h3><strong>Examenes Disponibles</strong></h3>


            <!--
                    <label for="inicio"><b>Fecha de la Cita </b></label>
                    <input type="Date" placeholder="12341234121201" name="inicio" id="inicio" required>
                    <br>
            
                    <label for="hora"><b>Hora</b></label>
                    <input type="TIME"  id="hora" name="hora" required >
                    <br>
            -->

            <div class="table-wrapper-scroll-y my-custom-scrollbar">
                <input type="Search" id="buscar" name="buscar" onkeypress="">
                <table id="dtBasicExample"class="table table-bordered table-striped mb-0">
                    <thead>
                        <tr>
                            <th scope="col">Codigo</th>

                            <th scope="col">nombre</th>
                            <th scope="col">Requiere Orden Medica</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Costo </th>
                            <th scope="col">Laboratoristas</th>
                            <th scope="col">         </th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${examenes}">

                            <tr>
                                <th scope="row" ><input type="text" readonly value="${item.getCodigo()}" name="codigo"></th>

                                <td>${item.getNombre()}</td>      

                                <td>
                                    <c:if test="${item.isOrden() == true}">


                                        <label class="container-radio">
                                            <input type="checkbox" name="radio" readonly  value="1" checked>
                                            <span class="checkmark-radio"></span>
                                        </label>



                                    </c:if>
                                    <c:if test="${item.isOrden() == false}"> 

                                        <label class="container-radio">
                                            <input type="checkbox" name="radio" readonly  value="0" >
                                            <span class="checkmark-radio"></span>
                                        </label>
                                    </c:if>
                                </td>



                                <td>${item.getDescripcion()}</td>


                                <td>${item.getCosto()}</td>


                        <center>

                            <label class="container-radio">
                                <form action="AgendaController">
                                    <td><input type="submit" class="btn btn-outline-primary" value="Elegir"></td>
                                    <td>
                                        <div id="div-oculto" style="display: none">
                                    
                                            
                                            
                                            <c:if test="${item.isOrden() == true}">


                                                <label class="container-radio">
                                                    <input type="checkbox" name="orden" readonly  value="1" checked>
                                                    <span class="checkmark-radio"></span>
                                                </label>



                                            </c:if>
                                            <c:if test="${item.isOrden() == false}"> 

                                                <label class="container-radio">
                                                    <input type="checkbox" name="orden" readonly  value="0" >
                                                    <span class="checkmark-radio"></span>
                                                </label>
                                            </c:if>

                                            <input type="text" value="${item.getCodigo()}" name="codigo">

                                            
                                            <input type="text" readonly value="${item.isOrden()}" name="archivo">

                                        </div>
                                    </td>
                                    
                                </form>
                               <!--<a class="btn btn-primary" href="AgendaController?accion=verLab-code=${item.getCodigo()}" >ver laboratorista</a>-->


                                <span class="checkmark-radio"></span>
                            </label>
                        </center>





                        </tr>




                    </c:forEach>
                </table>
            </div>
            
                <%@include  file="js/js-ocultar-div-informe.html" %>
                <%@include  file="js/js-test-buscar.html" %>
                <%@include file="js/js-bootstrap.html" %>
            </c:if>
    </body>
</html>
