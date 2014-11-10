/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elmi
 */

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
                      
            if(username != null && password != null) {
                if(
                  (username.equals("ali") && password.equals("ali123"))
                  ||
                  (username.equals("vali") && password.equals("vali123"))
                  ||
                  (username.equals("elmi") && password.equals("1234"))
                  ||
                  (username.equals("ahmadov") && password.equals("12368"))
                        ){               
                   HttpSession session = request.getSession();
                   session.setAttribute("username", username);
                   
                   if(username.equals("elmi")) {
                       session.setAttribute("admin", new Boolean(true));
                       response.sendRedirect("admin.jsp");
                       return;
                   }
                   
                   if(username.equals("ahmadov")) {
                       session.setAttribute("vip", new Boolean(true));
                       response.sendRedirect("vip.jsp");
                       return;
                   }
                   
                   response.sendRedirect("index.jsp");
                } else {
                   response.sendRedirect("filter_login.jsp");
                }
            } else {
                response.sendRedirect("filter_login.jsp");
            }
        } finally {
            out.close();
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

