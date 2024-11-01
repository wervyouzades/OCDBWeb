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

/**
 *
 * @author Wervy Ouzades
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/query"})
public class AdminServlet extends HttpServlet {

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
            switch(request.getParameter("qtype").toLowerCase()) {
                case "dd":
                    try {
                        out.println(RT.printGearModelDropDown(DT.gear_models));
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                
                case "map"://manage add people
                    try {
                        String name = request.getParameter("name");
                        if (name.length() > 0) {
                            out.println("management result:<br>" + FT.addPersonToDB(name) + "<br>");
                        } else {
                            out.println("ERROR: enter a name");
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                    
                case "mrp"://manage remove people
                    /*try {
                        String name = request.getParameter("name");
                        //out.println(RT.printGearArray(DT.gear));
                        if (name.length() > 0) {
                            out.println("management result:<br>" + FT.removePersonFromDB(name) + "<br>");
                        } else {
                            out.println("ERROR: enter a name");
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }*/
                    out.println("please dont do this");
                    break;
                    
                case "mag"://manage add gear
                    try {
                        String code = request.getParameter("code");
                        String type = request.getParameter("type");
                        String modelIID = request.getParameter("modelIID");
                        if (code.length() == 0 || type.length() == 0 || modelIID.length() == 0) {
                            out.println("ERROR: fill in required fields:<br>");
                            if (code.length() == 0) {
                                out.println("code<br>");
                            }
                            if (type.length() == 0) {
                                out.println("type<br>");
                            }
                            if (modelIID.length() == 0) {
                                out.println("model IID<br>");
                            }
                        } else {
                            code = IT.processGearCode(code);
                            type = IT.processGearTypeCode(type);
                            int intModelIID = Integer.parseInt(modelIID);
                            if (intModelIID >= 0) {
                                out.println("management result:<br>" + IT.processNewGear(code, type, intModelIID) + "<br>");
                            } else {
                                out.println("ERROR: invalid gear model IID");
                            }
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                    
                case "magm"://manage add gear model
                    try {
                        String type = request.getParameter("type");
                        String model = request.getParameter("model");
                        String modelDescription = request.getParameter("modelDescription");
                        String price = request.getParameter("price");
                        if (type.length() == 0 || model.length() == 0 || modelDescription.length() == 0 || price.length() == 0) {
                            out.println("ERROR: fill in requred fields:<br>");
                            if (type.length() == 0 ) {
                                out.println("type<br>");
                            }
                            if (model.length() == 0 ) {
                                out.println("model<br>");
                            }
                            if (modelDescription.length() == 0 ) {
                                out.println("model description<br>");
                            }
                            if (price.length() == 0 ) {
                                out.println("price");
                            }
                        } else {
                            type = IT.processGearTypeCode(type);
                            out.println("management result:<br>" + IT.processNewGearModel(type, model, modelDescription, price));
                        }
                    } catch (Exception e) {
                        out.println(e);
                    }
                    break;
                default:
                    out.println("sebastian screwed smth up");
                    break;
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
