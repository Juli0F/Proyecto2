<%-- 
    Document   : index
    Created on : 30 sept. 2020, 20:58:28
    Author     : julio
<!-- <%@include file="html/Login.html" %> fda--!>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/login.html" %>
        <%@include file="css/home-anti-login.html" %>
        <%@include file="css/Registrar.html" %>
        <%@include file="css/navbar-anti-login.html" %>
        
        <title>JSP Page</title>
    </head>
    <body>

        <c:if test="${personaSession != null} ">
       <h3>No Puede Tener Dos sesiones abiertas.</h3>
       
        </c:if>
        <c:if test="${personaSession == null}" >
            
            <FORM class="col-12 caja2" METHOD="POST" ACTION="LoginController?accion=cargar" enctype="multipart/form-data">  
                <div class="row">
                    <div class="form-group col-md-4">
                        <label>Elija el Archivo que desea Cargar a la Base de Datos: </label>
                    </div>
                    <div class="form-group col-md-4">
                        
                        <input class="form-control" type="file"  name="archivoDB" id="archivoDB" placeholder="Elija el Archivo" accept="application/xml" required>
                    </div>
                </div>
                <div class="row">
                        <div class="form-group col-md-5">
                            <label>Seleccione los Archivos Complementarios del Archivo XML</label>
                        </div>
                        <div class="form-group col-md-4">
                            <input class="form-control" type="file"  name="archivosComplementariosDB" id="archivosComplementariosDB" placeholder="Elija los archivos" accept=".pdf,image/*" multiple required>
                        </div>
                    </div>


                <br>
                <CENTER>

                    <input class="btn btn-primary" type="submit" value="Cargar">
                    <input class="btn" type="reset" value="Eliminar Datos">
                </CENTER>

            </FORM>
            
            
            <!--- fin formulario->


            <div class="container" style="padding-top: 70px" >

                <FORM class="col-12 caja2" METHOD="POST" ACTION="LoginController?accion=cargar" enctype="multipart/form-data" name="formFile">


                    <h1 class="align-content-lg-center">Carga de Archivo XML</h1>


                    <div class="row">
                        <div class="form-group col-md-4">
                            <label>Elija el Archivo que desea Cargar a la Base de Datos: </label>
                        </div>
                        <div class="form-group col-md-4">
                            <label>Nombre: </label>
                            <input type="file" id="file-input" name="file-input" accept="application/xml" required />

                            <h3>Contenido del archivo:</h3>
                            <pre id="contenido-archivo" name="contenido-archivo"></pre>

                        </div>
                    </div>


                    <br>
                    <CENTER>

                        <input class="btn btn-primary" type="submit" value="Enviar">
                        <input class="btn" type="reset" value="Eliminar Datos">
                    </CENTER>

                </FORM>

            </div>


                <%@include file="html/navbar-anti-login.html" %>
                <%@include file="html/Home-anti-login.html" %>

                

                <%@include file="js/js-login.html" %>
                <%@include file="js/Registrar.html" %>
                <%@include file="js/home-anti-login.html" %>
                <%@include file="js/load-file.html" %>
        </c:if>
    </body>
</html>
