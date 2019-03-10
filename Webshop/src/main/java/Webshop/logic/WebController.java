/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Webshop.logic;

import Webshop.entity.Cupcake;
import Webshop.entity.Toppings;
import Webshop.entity.Users;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ibenk
 */
@WebServlet(name = "WebController", urlPatterns = {"/WebController"})
public class WebController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String origin = (String) request.getParameter("origin");
        switch (origin) {
            case "loginpage":
                loginpage(request, response);
                break;
            case "shop":
                shop(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "cupcake":
                cupcake(request, response);
                break;
            default:
                throw new AssertionError();
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

    private void loginpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = (String) request.getParameter("username");
            String password = (String) request.getParameter("password");

            DataMapperUsers db = new DataMapperUsers();
            Users user = db.getUser(username);

            if (password.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("loginpage.jsp").forward(request, response);
    }

    private void shop(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = (String) request.getParameter("username");
            String password = (String) request.getParameter("password");
            String email = (String) request.getParameter("email");

            DataMapperUsers db = new DataMapperUsers();
            db.createUser(username, password, email);
        } catch (SQLException ex) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
        rd.forward(request, response);
    }

    private void cupcake(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topping = (String) request.getParameter("topping");
        String bottom = (String) request.getParameter("bottom");
        
        DataMapperCupcake mapper = new DataMapperCupcake();
        
        Cupcake cupcake = mapper.makeCupcake(topping, bottom);
        
        HttpSession session = request.getSession();
        session.setAttribute("cupcake", cupcake);
        
        RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
        rd.forward(request, response);
    }

}
