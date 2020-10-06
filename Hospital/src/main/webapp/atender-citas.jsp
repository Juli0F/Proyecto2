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
        <%@include file="html/navbar-medico-html.html" %>

        <div class="table-wrapper-scroll-y my-custom-scrollbar">

        <table  class="table table-striped table-bordered table-sm " cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th scope="col">Codigo Cita</th>
                    <th scope="col">Tipo  Consulta</th>
                    <th scope="col">Codigo del Paciente</th>
                    <th scope="col">Paciente</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Hora</th>
                    <th scope="col">Costo </th>
                    <th scope="col">Llenar Informe</th>


                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${citasPaciente}">

                    <tr>
                        <th scope="row" >

                            <input type="text"  id="codigo-cita" name="codigo-cita"class="form-control"   value="${item.getCodigoCita()}" readonly="">${item.getCodigoCita()}
                            hola
                        </th>

                        <td>
                            <input type="text"  id="consulta" name="consulta"class="form-control"   value="${item.getConsulta()}" readonly="">

                        </td>      

                        <td>
                            <input type="text"  id="codigoPaciente" name="codigoPaciente" class="form-control"   value="${item.getCodigoPaciente()}" readonly="">
                        </td>
                        <td>
                            <input type="text"  id="nombre" name="nombre" class="form-control"   value="${item.getPaciente()}" readonly="">
                        </td>
                        <td>
                            <input type="text"  id="codigoPaciente" name="codigoPaciente" class="form-control"   value="${item.getFecha()}" readonly="">
                        </td>
                        <td>
                            <input type="text"  id="codigoPaciente" name="codigoPaciente" class="form-control"   value="${item.getHora()}" readonly="">
                        </td>
                        <td>
                            <input type="text"  id="codigoPaciente" name="codigoPaciente" class="form-control"   value="${item.getCosto()}" readonly="">
                        </td>
                        <td>
                            <button type="Button"  id="myBoton" name="btn" class="btn btn-outline-primary" onclick="showDiv('Cancelar',' Llenar Formulario')"  value="${item.getPaciente()}" readonly="">Llenar Formulario</button>
                            <div id="div-oculto" style="display:none">
                                <div class="form-group">
                                   
                                    <form method="post" action="InformeController?accion=guardar-${item.getCodigoCita()}">
                                        
                                        <label for="exampleFormControlTextarea1">Example textarea</label>
                                        <textarea class="form-control" id="exampleFormControlTextarea1" name="txtInforme" rows="3"></textarea>
                                        <input type="submit" value="Guardar">
                                        
                                    </form>
                                        
                                </div>
                            </div>
                        </td>




                    </tr>



                </c:forEach>
            </tbody>
        </table>
            </div>

        <%@include file="js/js-tablas.html" %>
        <%@include file="js/js-ocultar-div-informe.html" %>
        <%@include file="js/js-bootstrap.html" %>
    </body>
</html>
