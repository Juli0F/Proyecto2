<%-- 
    Document   : AgendarCita
    Created on : 3 oct. 2020, 7:50:03
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/perfil.html" %>
        <%@include file="css/css-bootstrap.html" %>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="html/paciente-html-navbar.html" %>
        <br>
        <br>
       
        
         <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Codigo</th>
              <th scope="col">Medico</th>
              <th scope="col">Colegiado</th>
              <th scope="col">Correo</th>
              <th scope="col">Hora Entrada</th>
              <th scope="col">Hora Salida</th>
              
              <th scope="col">Especialidades</th>
              <th scope="col">Agendar Cita</th>
            </tr>
          </thead>
          <tbody>
              <c:forEach var="item" items="${medicos}">
                  
                <tr>
                     <th scope="row" >${item.getId()}</th>
                    <td>${item.getCodigo()}</td>
                    <td>${item.getNombre()}</td>
                    <td>${item.getColegiado()}</td>
                    <td>${item.getCorreo()}</td>
                    <td>${item.getHoraEntrada()}</td>
                    <td>${item.getHoraSalida()}</td>
                    <td>${item.getEspecialidades()}</td>
                     
                    <td>
                        
                            <a class="btn btn-primary" href="AgendaController?accion=crearCita=${item.getColegiado()}">Agendar Cita</a>
                        
                       
                    </td>
                </tr>
             
              </c:forEach>
            
          </tbody>
        </table>
        <%@include file="js/js-bootstrap.html" %>
        
        <%@include file="js/js-perfil.html" %>
    </body>
</html>
