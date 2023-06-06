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
import java.util.List;
import model.Destination;
import model.Stations;
import model.trains;

/**
 *
 * @author phamn
 */
@WebServlet(name = "destinations", urlPatterns = {"/destinations"})
public class destinations extends HttpServlet {

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
            out.println("<title>Servlet destinations</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet destinations at " + request.getContextPath() + "</h1>");
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
        dao d = new dao();
        String message="";
        List<trains> trainlist = d.getAll();
        List<Stations> stationList = d.getAllStations();
        request.setAttribute("trainlist", trainlist);
        request.setAttribute("stationList", stationList);
        request.getRequestDispatcher("Destinations.jsp").forward(request, response);
        if (request.getParameter("delete") != null) {
            String id = (String) request.getParameter("delete");
            d.DeleteDestination(id);
            message = "Destination Deleted";
        }

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
        List<trains> trainlist = d.getAll();
        List<Stations> stationList = d.getAllStations();
        request.setAttribute("trainlist", trainlist);
        request.setAttribute("stationList", stationList);
        String message = "";
        Stations sts = new Stations();
        String tempTime = "2018-09-05 00:00:00";
        Destination desti = new Destination();
        if (request.getParameter("save_all") != null) {
            String[] station_toAr = request.getParameterValues("station_to[]");
            String[] jurny_timeAr = request.getParameterValues("jurny_time[]");
            String[] fareAr = request.getParameterValues("fare[]");
            String[] total_seatAr = request.getParameterValues("total_seat[]");
            String[] seat_rangeAr = request.getParameterValues("seat_range[]");
            for (int j = 0; j < jurny_timeAr.length - 1; j++) {
                Destination tempDesti = new Destination();
                tempDesti.setId(String.valueOf(d.randomID("destinations")));
                tempDesti.setStation_from(request.getParameter("station_from"));
                tempDesti.setTrain_id(request.getParameter("dst_train"));
                tempDesti.setStation_to(station_toAr[j]);
                tempDesti.setTime(jurny_timeAr[j]);
                tempDesti.setStatus("active");
                tempDesti.setFare(fareAr[j]);
                tempDesti.setLast_activity("00");
                tempDesti.setLast_modify_by("0");
                tempDesti.setTotal_seat(total_seatAr[j]);
                tempDesti.setSeat_range(seat_rangeAr[j]);
                tempDesti.setType("up");
                d.InsertNewDestination(tempDesti);
            }

        }
        if (request.getParameter("search") != null) {
            List<Destination> allDestinaions = d.getAll(request.getParameter("station_from"), request.getParameter("dst_train"));
            request.setAttribute("list", allDestinaions);
        }
        request.getRequestDispatcher("Destinations.jsp").forward(request, response);

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
