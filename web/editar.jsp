<%@page import="edu.utp.U19221435.clases.*"%>
<%@page import="edu.utp.U19221435.modelo.*"%>
<%@page import="edu.utp.U19221435.servlet.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  producto obj =(producto)request.getAttribute("cod_Articulo");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Producto</title>
    </head>
    <body>
        <form name="form" method="get" action="controladorProductos">
            <input type="hidden" name="instruccion" value="editarBBDD">
            <input type="hidden" name="codigoArt" value="${objProducto.getCodigoArticulo()}"/>
            
            Seccion: <input type="text" name="seccion" value="${objProducto.getSeccion()}" /><br/>
            <br/>
            Nombre: <input type="text" name="nombre" value="${objProducto.getNombreArticulo()}"/><br/>
            <br/>
            Precio: <input type="text" name="precio" value="${objProducto.getPrecio()}"/><br/>
            <br/>
            Fecha: <input type="date" name="fecha" value="${objProducto.getFecha()}"/><br/>
            <br/>
            <c:choose>
                <c:when test="${objProducto.getImportado()=='VERDADERO'}">
                    Importado: <select name="importado" >
                                <option selected="true">VERDADERO</option>
                                <option>FALSO</option>
                            </select><br/>            
                            <br/>            
                </c:when>
                <c:otherwise>
                    Importado: <select name="importado" >
                                <option>VERDADERO</option>
                                <option selected="true">FALSO</option>
                            </select><br/>            
                            <br/>            
                </c:otherwise>
            </c:choose>            
            Pais: <input type="text" name="pais" value="${objProducto.getPaisOrigen()}"/><br/>
            <br/>
            <input type="submit" value="Editar">
        </form>                
    </body>
</html>
