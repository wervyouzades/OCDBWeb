/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Processing;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Wervy Ouzades
 */
@WebServlet(name = "ReceiptServlet", urlPatterns = {"/Receipt"})
public class ReceiptServlet extends HttpServlet {

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
                //out.println("ael");
                //out.println(request.getParameter("personIID"));
                DT.populateLocalDB();
                try {
                int iid = Integer.parseInt(request.getParameter("personIID"));
                Person person = DT.getPersonById(iid);
                if (person != null) {
                    ArrayList<Gear> gear = DT.purgeGearArrayByPerson(DT.gear, person);
                    String datetime = OffsetDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("T", " ").replace("Z", " ");
                    out.println("<p>" + datetime.substring(0, datetime.indexOf(".")) + "</p>");
                    out.println(RT.printGearArray(gear));
                    out.println("<p>I, " + person.name + ", by signing, acknowledge that <br>" +
"•The gear listed above is my responsibility from the time it leaves the gear closet to the time it returns to the gear closet and is checked in. <br>" +
"•Returning different gear than the gear listed above will not relieve me of my obligations towards the gear I have checked out. <br>" +
"•Not returning all the gear listed above by the date December 1, 2023 or returning the gear listed above in a broken condition will result in a fine up to the amount specified per gear item.<br>" +
"<br>" +
"__________________________");
                } else {
                    out.println("person doesn't exist");
                }
            } catch (Exception e) {
                out.println(e);
            }
            
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
