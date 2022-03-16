package edu.utp.U19221435.servlet;

import edu.utp.U19221435.clases.producto;
import edu.utp.U19221435.modelo.modeloProductos;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author hoptc
 */
@WebServlet(name = "controladorProductos", urlPatterns = {"/controladorProductos"})
public class controladorProductos extends HttpServlet {
    private modeloProductos modelo;
    //Definir o establecer el DataSource
    @Resource(name="jdbc")
    //Variable para alcenar el pool
    private DataSource miPool;
    //CREAMOS EL METODO INIT QUE ES COMO EL METODO MAIN DE UN PROGRAMA NORMAL
    @Override
    public void init() throws ServletException{
        super.init();
        //Conectamos al modelo
        try {
            modelo=new modeloProductos(miPool);
        } catch (Exception e) {throw new ServletException(e);}
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controladorProductos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controladorProductos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Leer el parametro del formulario
        String command=request.getParameter("instruccion");
        //Si no se envia el parametro,listar productos
        if(command==null) command="listar";
        //Redirigir el flujo de ejecucion al metodo adecuado
        switch (command) {
            case "listar":
                ObtenerProductos(request,response);                                
                break;
            case "nuevo":
                agregarProductos(request,response);                
                break;
            case "openEdit":            
                try {
                    AbrirEditar(request,response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }            
                break;
            case "editarBBDD":            
                try {
                    actualizarProducto(request,response);
                } catch (Exception ex) {
                    Logger.getLogger(controladorProductos.class.getName()).log(Level.SEVERE, null, ex);
                }            
                break;
            case "borrar":
                    deleteProducto(request,response);
                break;
            default:
                throw new AssertionError();
        }        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    //Obtenemos la lista de productos desde el modelo        
    private void ObtenerProductos(HttpServletRequest request, HttpServletResponse response) {
        List<producto> productos;
        try {
            productos=modelo.getProductos();
            //Agregar lista al request
            request.setAttribute("listaProductos", productos);
            //Enviar request a la pagina JSP
            RequestDispatcher miDispatcher=request.getRequestDispatcher("/listaProductos.jsp");
            miDispatcher.forward(request, response);
        } catch (Exception e) {e.printStackTrace();}
    }
    //Agregamos productos
    private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Leer la informacion del producto que viene del formularo
            String codigo=request.getParameter("codigo");
            String seccion=request.getParameter("seccion");
            String nombre=request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            String fechaRequest=request.getParameter("fecha");            
            Date fecha = formato.parse(fechaRequest);
            String importado=request.getParameter("importado");
            String pais = request.getParameter("pais");
            //Crear objeto
            producto nuevoObj = new producto(codigo, seccion, nombre, pais, precio, fecha, importado);
            //Enviar el objeto al modelo e insertar el objeto en la bbdd
            modelo.insertarProducto(nuevoObj);
            //Volver al listado de productos
            ObtenerProductos(request, response);
        } catch (Exception e) {e.printStackTrace();}        
    }
    //Editamos productos
    private void AbrirEditar(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String codArticulo=request.getParameter("codArticulo");//Leemos el codigo articulo del listado        
        //Almacenamos el producto del articulo
        producto elProducto=modelo.obtenerProducto(codArticulo);
        //Colocamos el atributo correspondiente al C Articulo
        request.setAttribute("objProducto", elProducto);
        //Enviamos el producto al formulario para editar
        RequestDispatcher miDispatcher=request.getRequestDispatcher("/editar.jsp");
        miDispatcher.forward(request, response);
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        //Leer datos del formulario
            String codigo=request.getParameter("codigoArt");
            String seccion=request.getParameter("seccion");
            String nombre=request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            Date fecha = formato.parse(request.getParameter("fecha"));
            String importado=request.getParameter("importado");
            String pais = request.getParameter("pais");
        //Crear Objeto
        producto newObj = new producto(codigo, seccion, nombre, pais, precio, fecha, importado);
        //Mandar Objeto al modelo y actualizar la base de datos
        modelo.editarProducto(newObj);
        //Volver al listado con RequestDispatcher
        ObtenerProductos(request, response);
    }

    private void deleteProducto(HttpServletRequest request, HttpServletResponse response) {
        //Recogemos el codigo enviado
        String cod=request.getParameter("codArticulo");
        try {
            //Enviamos el codigo al modelo para borrarlo de la base de datos
            modelo.deleteProduct(cod);
        } catch (Exception ex) {
            Logger.getLogger(controladorProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Volvemos al listado con RequestDispatcher
        ObtenerProductos(request, response);
    }
}
