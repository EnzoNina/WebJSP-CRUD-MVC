/*
 Los errores que me ocurrieron fue por la version del driver de mysql connector
 */
package edu.utp.U19221435.servlet;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
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
@WebServlet(name = "servletPrueba", urlPatterns = {"/servletPrueba"})
public class servletPrueba extends HttpServlet {
    //Definir o establecer el DataSource
    @Resource(name="jdbc")
    //Variable para alcenar el pool    
    private DataSource miPool;    
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
            out.println("<title>Servlet servletPrueba</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletPrueba at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");            
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Creamos el objeto printWrite
        PrintWriter salida = response.getWriter();
        response.setContentType("text/plain");
        //Creamos conexion con BBDD
        Connection miConexion;
        Statement miStatement;
        ResultSet resultado;       
        try {
            miConexion=miPool.getConnection();
            String consulta="SELECT * FROM productos";
            miStatement=miConexion.createStatement();
            resultado=miStatement.executeQuery(consulta);
            while (resultado.next()) {                
                String nombreArticulo=resultado.getString(3);
                salida.println(nombreArticulo);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
