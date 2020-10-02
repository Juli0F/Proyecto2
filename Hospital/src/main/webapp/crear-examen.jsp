<%-- 
    Document   : crear-examen
    Created on : 2 oct. 2020, 11:33:53
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/css-bootstrap.html" %>
        <%@include file="css/css-checkbox.html" %>
        <style>
            #formulario{
                margin: 0 auto;
                text-align: center;
                border-radius: 10px;
                border: 1px solid #666666;
                width: 500px;
            }</style>
        <title>Crear Examen</title>

    </head>
    <body>
        <%@include file="html/navbar-admin.html" %>
        <div style="inline-box-align: inherit">
            <form class="form-group" >

                <div class="form-row " >
                    <div class="form-group col-md-4">
                        <label for="codigo">Codigo</label>
                        <input type="text" class="form-control" id="codigo" placeholder="Codigo" name="codigo">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="examen">Examen</label>
                        <input type="text" class="form-control" id="examen" placeholder="Nombre del Examen" name="nombre">
                    </div>

                </div>

                <div class="form-row">


                    <div class="form-group col-md-4">
                        <label for="costo">Costo</label>
                        <input type="number" step="0.001" class="form-control" id="costo" placeholder="100.50" name="costo">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="descripcion">Descripcion</label>
                        <input type="text"  class="form-control" id="descripcion" maxlength="45" name="descripcion">
                    </div>

                    <div class="form-group col-md-4">
                        
                    </div>

                    <div class="form-group col-md-4">
                        <label class="container">Requiere Orden
                            <input type="checkbox" checked="checked">
                            <span class="checkmark"></span>
                        </label>

                        <label for="inputState">Tipo De archivo</label>
                        <select id="inputState" class="form-control">
                            <option value="pdf" selected>pdf</option>
                            <option value="png">Imagen 'png'</option>
                        </select>
                    </div>


                </div>
                <button type="submit" class="btn btn-primary">Crear</button>
            </form>
        </div>
    </body>
</html>
