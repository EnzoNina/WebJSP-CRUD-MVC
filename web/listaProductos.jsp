<%@page import="edu.utp.U19221435.clases.*"%>
<%@page import="edu.utp.U19221435.modelo.*"%>
<%@page import="edu.utp.U19221435.servlet.*"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CRUD Productos</title>
        <link rel="stylesheet" href="style.css"/>
    </head>
    <body>
        <table border="2">
            <tr>
                <td class="cabecera">Codigo Articulo</td>
                <td class="cabecera">Seccion</td>
                <td class="cabecera">Nombre Articulo</td>
                <td class="cabecera">Fecha</td>
                <td class="cabecera">Precio</td>
                <td class="cabecera">Importado</td>
                <td class="cabecera">País de Origen</td>
                <td>Accion</td>
            </tr>        
            <c:forEach var="temp" items="${listaProductos}">                
                <!<!-- Link para editar cada producto con su campo clave -->                
                <c:url var="linkEditar" value="controladorProductos">
                    <c:param name="instruccion" value="openEdit"></c:param>
                    <c:param name="codArticulo" value="${temp.getCodigoArticulo()}"></c:param>
                </c:url>
                <!<!-- Link para borrar pasando el campo clave -->
                <c:url var="linkBorrar" value="controladorProductos">
                    <c:param name="instruccion" value="borrar"></c:param>
                    <c:param name="codArticulo" value="${temp.getCodigoArticulo()}"></c:param>
                </c:url>                
                  <tr>
                    <td>${temp.getCodigoArticulo()}</td>
                    <td>${temp.getSeccion()}</td>
                    <td>${temp.getNombreArticulo()}</td>
                    <td>${temp.getFecha()}</td>
                    <td>${temp.getPrecio()}</td>
                    <td>${temp.getImportado()}</td>
                    <td>${temp.getPaisOrigen()}</td>
                    <td><a href="${linkEditar}">Editar</a>|<a href="${linkBorrar}">Eliminar</a></td>
                </tr>              
            </c:forEach>
        </table>
        <input type="button" value="Nuevo Producto" name="nuevo" onclick="window.location.href='nuevo.jsp'"/>
    </body>        
</html>
