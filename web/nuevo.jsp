<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Producto</title>
    </head>
    <body>
        <form name="form" method="get" action="controladorProductos">
            <input type="hidden" name="instruccion" value="nuevo"/>
            Codigo: <input type="text" name="codigo"/><br/>
            <br/>
            Seccion: <input type="text" name="seccion"/><br/>
            <br/>
            Nombre: <input type="text" name="nombre"/><br/>
            <br/>
            Precio: <input type="text" name="precio"/><br/>
            <br/>
            Fecha: <input type="date" name="fecha"/><br/>
            <br/>
            Importado: <select name="importado">
                            <option>VERDADERO</option>
                            <option>FALSO</option>
                        </select><br/>            
            <br/>            
            Pais: <input type="text" name="pais"/><br/>
            <br/>
            <input type="submit" value="Registrar">
        </form>
    </body>
</html>
