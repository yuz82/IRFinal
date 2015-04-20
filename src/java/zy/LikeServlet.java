/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zy;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yuezhao
 */
@WebServlet(name = "LikeServlet", urlPatterns = {"/LikeServlet"})
public class LikeServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            
        String post_id = request.getParameter("post_id");
        String is_liked_str = request.getParameter("is_liked");
        Boolean is_liked = Boolean.valueOf(is_liked_str);
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");
        //test
        post_id = "g-41203-S-5948531387177984001";
        access_token = "AQVicog6-ig1QjPr7HrkyWcW13YXesGbBW86e1TuVw9tZTgwm6uCISNChIQgeFwDraR4_Vkb2ZC0sQEXbgo1aeyRppiptAofMF9ana8z7Ogyts6GNmx6adMe1XjY_ltOnvp31RzgpkpUgNyhAp0uAWayPo4aVim18NPndEtEguPz6T7WcxI";
        is_liked = false;
        
        like http = new like();
        http.sendPut(post_id,access_token,is_liked);
        
        response.sendRedirect("index.html");
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
