<%-- 
    Document   : UpFIle
    Created on : 6 oct. 2020, 10:34:26
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <FORM class="col-12 caja2" METHOD="POST" ACTION="ControladorCargaArchivo" enctype="multipart/form-data">  
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
    </body>
</html>
