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
import java.security.Principal;
import java.util.ArrayList;

/**
 *
 * @author Wervy Ouzades
 */
@WebServlet(name = "TransactionServlet", urlPatterns = {"/transaction/query"})
public class TransactionServlet extends HttpServlet {

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
            Principal userPrincipal = request.getUserPrincipal();
            String username = "";
            if (userPrincipal != null) {
                username = userPrincipal.getName();
            }
            switch (request.getParameter("qtype").toLowerCase()) {
            case "o"://checkout
                    //out.println("THIS IS A CHECKOUT<br>");
                    try {
                        String name = request.getParameter("name");
                        String code = request.getParameter("code");
                        String type = request.getParameter("type");
                        /*out.println("name provided: " + name + "<br>");
                            out.println("code provided: " + code + "<br>");
                            out.println("type provided: " + type + "<br>");*/
                        if (name.length() == 0 || code.length() == 0 || type.length() == 0) {
                            out.println("ERROR: fill in required fields:<br>");
                            if (name.length() == 0) {
                                out.println("name<br>");
                            }
                            if (code.length() == 0) {
                                out.println("code<br>");
                            }
                            if (type.length() == 0) {
                                out.println("type<br>");
                            }
                        } else {

                            out.println("checkout result:<br>" + IT.processCheckout(name, code, type) + "<br>");
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                break;
                
                case "t"://transfer
                    try {
                        String name = request.getParameter("name");
                        String code = request.getParameter("code");
                        String type = request.getParameter("type");
                        if (name.length() == 0 || code.length() == 0 || type.length() == 0) {
                            out.println("ERROR: fill in required fields:<br>");
                            if (name.length() == 0) {
                                out.println("name<br>");
                            }
                            if (code.length() == 0) {
                                out.println("code<br>");
                            }
                            if (type.length() == 0) {
                                out.println("type<br>");
                            }
                        } else {
                            out.println("transfer result:<br>" + IT.processTransfer(name, code, type) + "<br>");
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                    
                case "i"://check in
                    try {
                    String code = request.getParameter("code");
                    String type = request.getParameter("type");
                    if (code.length() == 0 || type.length() == 0) {
                        out.println("ERROR: fill in required fields:<br>");
                        if (code.length() == 0) {
                            out.println("code<br>");
                        }
                        if (type.length() == 0) {
                            out.println("type<br>");
                        }
                    } else {
                        out.println("check in result:<br>" + IT.processCheckin(code, type) + "<br>");
                    }
                } catch (Exception e) {
                    out.println(e.getMessage());
                }
                break;
                
                case "pg"://preview gear
                    try{
                        String code = IT.processGearCode(request.getParameter("code"));
                        String type = IT.processGearTypeCode(request.getParameter("type"));
                        if (code == null) {
                            out.println("VIPnsOIPJFPOIJSDF");
                        }
                        if (code.length() == 0 || type.length() == 0) {
                            out.println("ERROR: fill in required fields:<br>");
                            if (code.length() == 0) {
                                out.println("code<br>");
                            }
                            if (type.length() == 0) {
                                out.println("type<br>");
                            }
                        } else {
                            Gear g = DT.getGearByCodeAndType(IT.processGearCode(code), IT.processGearTypeCode(type));
                            ArrayList<Gear> ga = new ArrayList<Gear>();
                            if (g != null) {
                                ga.add(g);
                                out.println(RT.printGearArray(ga));
                            } else {
                                out.println("<p>ERROR: gear doesn't exist</p>");
                            }
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
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
