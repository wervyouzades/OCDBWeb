/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Processing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wervy Ouzades
 */
@WebServlet(name = "GearModelServlet", urlPatterns = {"/GearModel"})
public class GearModelServlet extends HttpServlet {

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
            DT.populateLocalDB();
            String qtype = null;
            try {
                qtype = request.getParameter("qtype");
                if (qtype != null) {
                    qtype = qtype.toLowerCase();
                }
                //out.println("<p>" + qtype + "</p>");
                
            } catch(Exception e) {
                if (e.getMessage().equals("java.lang.NullPointerException: Cannot invoke \"String.toLowerCase()\" because the return value of \"javax.servlet.http.HttpServletRequest.getParameter(String)\" is null")) {
                    //out.println("<p>nooo qtype</p>");
                } else {
                    out.println(e.getMessage());
                }
            }
            
            //out.println("<p>what</p>");
            try {
                String iidString = request.getParameter("gearModelIID");
                int iid = Integer.parseInt(iidString);

                if (iidString.length() > 0) {
                    if (qtype == null || qtype.equals("")) {
                        try {
                            out.println("<h1>Gear Model Display Page</h1>");
                                Gear_Model gear_model = Gear_Model.searchInArray(DT.gear_models, iid);
                                ArrayList<Gear_Model> tempGearModelArray = new ArrayList<Gear_Model>();
                                tempGearModelArray.add(gear_model);
                                out.println(RT.printGearModelArray(tempGearModelArray));

                                out.println("<p></p>");
                                out.println("<p>Gear of model:");
                                ArrayList<Gear> tempGearArray = new ArrayList<Gear>();
                                tempGearArray = DT.purgeGearArrayByModel(DT.gear, gear_model);
                                out.println(RT.printGearArray(tempGearArray));
                                
                                
                                out.println("<p></p>");
                                out.println("<p>Edit gear model(to be added):</p>");
                                /*
                                out.println(RT.printNamePrefilledInputBox(person));

                                ArrayList<Gear_Model> modelsOfType = DT.purgeGearModelArrayByType(DT.gear_models, gear.gear_type);
                                out.println(RT.printGearModelDropDown(modelsOfType, gear.gear_model));

                                out.println(RT.printGearNotesPrefilledInputBox(gear));
                                */

                        } catch (Exception e) {
                           out.println("<p>ERROR: bad url (debug: ");
                           out.println(e);
                           out.println(")</p>");
                        }
                    } else {
                        if (ST.hasEditPerms(request)) {
                            
                        }
                    }
                } else {
                    out.println("invalid id");
                }
            } catch (Exception e) {
                out.println("<p>invalid id</p>");
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
