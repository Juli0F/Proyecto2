<%-- 
    Document   : paciente
    Created on : 4 oct. 2020, 16:59:53
    Author     : julio
pacienteSession
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

        <c:if test="${personaSession != null}" >
            <%@include file="html/paciente-html-navbar.html" %>

            <br><br><br><br><br>
            <form action="PacienteController?accion=guardar-cambios" method="post">
                <div class="row">
                    <div class="col">
                        <label for="codigo">Codigo</label>
                        <input type="text"  id="codigo" name="codigo"class="form-control" placeholder="First name"  value="${pacienteSession.getCodigo()}" readonly="">
                    </div>
                    <div class="col">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" placeholder="Nombre" id="nombre" value="${personaSession.getNombre()}" name="nombre" required="">
                    </div>
                    <div class="col">

                        <label for="dpi"></label>
                        <input type="text" class="form-control" placeholder="1333434234" id="dpi" name="dpi" value="${personaSession.getDpi()}" required>
                    </div>

                </div>
                <div class="row">

                    <div class="col">
                        <label for="tipo">Tipo de Sangre</label>
                        <input type="text" class="form-control" id="tipo" name="tipo" placeholder="O+"  value="${pacienteSession.getTipo_de_sangre()}" required="">
                    </div>
                    <div class="col">
                        <label for="peso">Peso</label>
                        <input type="number" step="0.001" class="form-control" id="peso" name="peso" placeholder="66" '  value="${pacienteSession.getPeso()}" required="">
                    </div>
                    <div class="col">
                        <label for="fecha">Fecha de nacimiento</label>
                        <input type="date" class="form-control" id="fecha" name="fecha"  value="${pacienteSession.getFecha()}" required>
                    </div>

                </div>


                <div class="row">

                    <div class="col">
                    </div>

                    <div class="col">
                        
                        <input type="submit"  class="btn btn-outline-primary"  name="peso"  '  value="Enviar" required="">
                    </div>
                    <div class="col">
                        
                    </div>

                </div>


            </form>


            <%@include file="js/js-bootstrap.html" %>
        </c:if>
    </body>
</html>
