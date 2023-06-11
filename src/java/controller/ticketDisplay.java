/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.dao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import model.Stations;
import model.trains;

/**
 *
 * @author phamn
 */
@WebServlet(name = "ticketDisplay", urlPatterns = {"/ticketdisplay"})
public class ticketDisplay extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ticketDisplay</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ticketDisplay at " + request.getContextPath() + "</h1>");
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

        dao d = new dao();
        ArrayList<HashMap<String, String>> trains = new ArrayList<HashMap<String, String>>();
        trains trnObj = new trains();
        String stationFrom = (String) request.getParameter("from");
        String stationTo = (String) request.getParameter("to");
        String journey_date = (String) request.getParameter("journey_date");
        Stations stationFromObj = new Stations();
        Stations stationToObj = new Stations();
        if (stationTo != null || stationFrom != null) {
            trains = d.searchticket(stationFrom, stationTo,journey_date);
            Stations tempStationsObj = new Stations();
            stationFromObj = d.getStation(stationFrom);          
            stationToObj = d.getStation(stationTo);
        }
        request.setAttribute("stationFromObj", stationFromObj);
        request.setAttribute("stationToObj", stationToObj);
        request.setAttribute("journey_date", journey_date);
        request.setAttribute("trains", trains);
        request.getRequestDispatcher("TicketDisplay.jsp").forward(request, response);
        

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

