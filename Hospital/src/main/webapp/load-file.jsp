<%-- 
    Document   : load-file
    Created on : 6 oct. 2020, 15:27:52
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/css-bootstrap.html" %>
        <title>Load File</title>
    </head>
    <body>
        <h3>Cargar Archivo</h3>
        
        <c:if test="${temp == 'temp'}">
            
            <FORM class="col-12 caja2" METHOD="POST" ACTION="ReadFileController?accion=cargar" enctype="multipart/form-data">  
                <div class="row">
                    <div class="form-group col-md-4">
                        <label>Elija el Archivo que desea Cargar a la Base de Datos: </label>
                    </div>
                    <div class="form-group col-md-4">
                        
                        <input class="form-control" type="file"  name="dataFile" id="archivoDB" placeholder="Elija el Archivo" accept="application/xml" required>
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
        </c:if>
        carga De archivo
        <%@include file="js/js-bootstrap.html" %>
    </body>
</html>
