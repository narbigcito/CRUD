/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.materiam.gibran.beans.UsuarioBeanLocal;
import mx.materiam.gibran.entities.User;

/**
 *
 * @author gibran
 */
@WebServlet(urlPatterns = {"/DowloadServlet"})
public class DowloadServlet extends HttpServlet {

    @EJB
    UsuarioBeanLocal ub;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
            /* TODO output your page here. You may use following sample code. */

            byte[] bytes = null;
            String mime = "";
            String filename = "hola.pdf";
            String etag = request.getHeader("If-None-Match");
            String type = request.getParameter("type");
            String tag = "";
            
            String g="";
            User u = ub.getUser(7);
            g=request.getParameter("name");
            System.out.println(g);

            bytes = u.getArrayPdf();
            mime = "application/pdf";
            response.setContentType(mime);
            response.setContentLength(u.getArrayPdf().length);
            tag = String.valueOf(u.getArrayPdf().length);
            if (tag.equals(etag)) {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return;
            }
            
            OutputStream out = null;
            out = response.getOutputStream();
            out.write(bytes, 0, bytes.length);

            out.close();
            
            
            response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            
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
        processRequest(request, response);
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
