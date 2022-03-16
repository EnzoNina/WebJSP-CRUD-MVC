package edu.utp.U19221435.modelo;

import edu.utp.U19221435.clases.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class modeloProductos {

    private DataSource origenDatos;

    public modeloProductos(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<producto> getProductos() throws Exception {
        List<producto> listProductos = new ArrayList<>();
        Connection conexion = null;
        Statement estado = null;
        ResultSet resultado = null;
        try {
            //Establecer conexion    
            conexion = origenDatos.getConnection();
            //Crear sentencia Sql
            String sentencia = "SELECT * FROM productos";
            estado = conexion.createStatement();
            //Ejecutar sql
            resultado = estado.executeQuery(sentencia);
            //Recolectar datos
            while (resultado.next()) {
                listProductos.add(new producto(resultado.getString("codigoArticulo"), resultado.getString("seccion"), resultado.getString("nombreArticulo"), resultado.getString("paisOrigen"), resultado.getDouble("precio"), resultado.getDate("fecha"), resultado.getString("importado")));
            }
        } catch (Exception e) {
        } finally {
            conexion.close();
            estado.close();
            resultado.close();
        }
        return listProductos;
    }

    public void insertarProducto(producto objeto) throws Exception {
        Connection conexion = null;
        Statement estado = null;
        try {
            //Establecer conexion    
            conexion = origenDatos.getConnection();
            //Convertimos la fecha en date.sql
            java.sql.Date fechaSQL = new java.sql.Date(objeto.getFecha().getTime());
            //Convertimos el boolean en bit        
            //Crear sentencia Sql        
            String sentencia = "INSERT INTO productos values('" + objeto.getCodigoArticulo() + "','" + objeto.getSeccion() + "','" + objeto.getNombreArticulo() + "','" + objeto.getPrecio() + "','" + fechaSQL + "','" + objeto.getImportado() + "','" + objeto.getPaisOrigen() + "')";
            estado = conexion.createStatement();
            //Ejecutar sql
            estado.executeUpdate(sentencia);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
            estado.close();
        }
    }

    //Buscamos el producto a editar
    public producto obtenerProducto(String codArticulo) throws Exception {
        producto obj_Producto = null;
        Connection conexion = null;
        PreparedStatement Prepare = null;
        ResultSet resultado = null;
        String cod = codArticulo;
        try {
            //Establecer conexion
            conexion = origenDatos.getConnection();
            //Crear Sql para la busqueda del producto
            String consulta = "SELECT * FROM productos WHERE codigoArticulo=?";
            //Crear consulta preparada
            Prepare = conexion.prepareStatement(consulta);
            //Establecer parametros
            Prepare.setString(1, cod);
            //Ejecutar consulta
            resultado = Prepare.executeQuery();
            //Obtener datos 
            if (resultado.next()) {
                //Creamos el producto
                obj_Producto = new producto(resultado.getString("codigoArticulo"), resultado.getString("seccion"), resultado.getString("nombreArticulo"), resultado.getString("paisOrigen"), resultado.getDouble("precio"), resultado.getDate("fecha"), resultado.getString("importado"));
            } else {
                throw new Exception("No encontramos el producto con el codigo: " + codArticulo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
            Prepare.close();
            resultado.close();
        }
        return obj_Producto;
    }

    public void editarProducto(producto newObj) throws Exception {
        Connection conexion = null;
        PreparedStatement statment = null;
        try {
            //Establecemos conexion
            conexion = origenDatos.getConnection();
            //Preparamos sentencia
            String sentencia = "UPDATE productos set seccion=?,nombreArticulo=?,precio=?,fecha=?,importado=?,paisOrigen=? where codigoArticulo=?";
            //Crear consulta preparada
            statment = conexion.prepareStatement(sentencia);
            //Establecemos parametros
            statment.setString(1, newObj.getSeccion());
            statment.setString(2, newObj.getNombreArticulo());
            statment.setDouble(3, newObj.getPrecio());
            //Convertimos fecha a un tipo de fecha compatible
            java.sql.Date fechaSQL = new java.sql.Date(newObj.getFecha().getTime());
            statment.setDate(4, fechaSQL);
            statment.setString(5, newObj.getImportado());
            statment.setString(6, newObj.getPaisOrigen());
            statment.setString(7, newObj.getCodigoArticulo());
            //Ejecutamos SQL
            statment.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
            statment.close();
        }
    }

    public void deleteProduct(String cod) throws Exception {
        Connection conexion = null;
        PreparedStatement statment = null;
        try {
            //Establecemos la conexion
            conexion = origenDatos.getConnection();
            //Preparamos sentencia
            String sentencia = "DELETE FROM productos WHERE codigoArticulo=?";
            statment = conexion.prepareStatement(sentencia);
            //Establecemos parametro
            statment.setString(1, cod);
            //Ejecutamos SQL            
            statment.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.close();
            statment.close();
        }
    }

}
