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
/**
 *
 * @author Wervy Ouzades
 */
@WebServlet(name = "GearServlet", urlPatterns = {"/Gear"})
public class GearServlet extends HttpServlet {
        //parameters: qtype, gearIID, 
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
                String iidString = request.getParameter("gearIID");
                int iid = Integer.parseInt(iidString);

                if (iidString.length() > 0) {
                    if (qtype == null || qtype.equals("")) {
                        try {
                                Gear gear = Gear.searchInArray(DT.gear, iid);
                                out.println("<h1>Gear CA" + gear.code + "(" + gear.gear_type.code + ")" + " Display Page</h1>");
                                
                                ArrayList<Gear> tempGearArray = new ArrayList<Gear>();
                                tempGearArray.add(gear);
                                out.println(RT.printGearArray(tempGearArray));

                                out.println("<img src=\"" + gear.picture_url + "\" style=\"max-height:400px; max-width:200px; height:auto; width:auto;\">");
                                
                                out.println("<p></p><br>");
                                out.println("<p>Transactions with gear:");
                                ArrayList<Transaction> tempTransactionArray = new ArrayList<Transaction>();
                                tempTransactionArray = DT.purgeTransactionArrayByGear(DT.transactions, gear);
                                out.println(RT.printContainedTransactionArray(tempTransactionArray));


                                out.println("<p></p>");
                                out.println("<p>Edit gear:</p>");

                                out.println(RT.printGearCodePrefilledInputBox(gear));

                                ArrayList<Gear_Model> modelsOfType = DT.purgeGearModelArrayByType(DT.gear_models, gear.gear_type);
                                out.println(RT.printGearModelDropDown(modelsOfType, gear.gear_model));

                                out.println(RT.printGearNotesPrefilledInputBox(gear));




                        } catch (Exception e) {
                           out.println("<p>ERROR: bad url (debug: ");
                           out.println(e);
                           out.println(")</p>");
                        }
                    } else {
                        if (ST.hasEditPerms(request)) {
                            String nextQType = "";
                            switch(qtype) {
                                case "e1":
                                    nextQType = "E2";
                                    out.println("<button type=\"button\" id=\"submit\">Edit Gear</button>");
                                    out.println("<p>Are you sure you want to edit the gear?</p>");
                                    break;
                                case "e2":
                                    nextQType = "E3";
                                    out.println("<button type=\"button\" id=\"submit\">Edit Gear</button>");
                                    out.println("<p>Are you absolutely sure you want to edit the gear?</p>");
                                    out.println("<p>Edited gear preview:");
                                    try {
                                        String test = request.getParameter("gear_model");
                                            //out.println(test);
                                        int tempGearModelID = Integer.parseInt(request.getParameter("gear_model"));
                                            //out.println(tempGearModelID);
                                        Gear_Model tempGearModel = DT.getGearModelById(tempGearModelID);
                                            //out.println(tempGearModel);
                                        int checkedInID = DT.getPersonByName(OCDB.checked_in).id;
                                        String notes = request.getParameter("notes");
                                        if (notes == null) notes = "";
                                        //out.println(checkedInID);
                                        //out.println(tempGearModel.gear_type.id);
                                        //out.println(tempGearModel.id);
                                        Gear tempGear;
                                        //try {
                                            tempGear = new Gear(-1, "PREVIEW", checkedInID, tempGearModel.gear_type.id, tempGearModel.id, notes);
                                        //} catch (Exception e) {
                                            //out.println(e);
                                        //}
                                        tempGear.updateLocalReferences();
                                        //out.println(tempGear);
                                        ArrayList<Gear> tempGearArray = new ArrayList<Gear>();
                                        tempGearArray.add(tempGear);
                                        out.println(RT.printGearArray(tempGearArray));
                                    } catch (Exception e) {
                                        out.println("<p>ERROR: data inputted is invalid</p>");
                                        out.println("<p>" + e.getMessage() + "</p>");
                                    }
                                    break;
                                case "e3":
                                    try {
                                        String test = request.getParameter("gear_model");
                                            out.println(test);
                                        int tempGearModelID = Integer.parseInt(request.getParameter("gear_model"));
                                            //out.println(tempGearModelID);
                                        Gear_Model tempGearModel = DT.getGearModelById(tempGearModelID);
                                            //out.println(tempGearModel);
                                        int checkedInID = DT.getPersonByName(OCDB.checked_in).id;
                                        String notes = request.getParameter("notes");
                                        if (notes == null) notes = "";
                                        //out.println(checkedInID);
                                        //out.println(tempGearModel.gear_type.id);
                                        //out.println(tempGearModel.id);
                                        out.println("<p>" + IT.processEditGear(iid, tempGearModel.id, notes) + "</p>");
                                    } catch (Exception e) {
                                        out.println("<p>ERROR: data inputted is invalid</p>");
                                        out.println("<p>" + e.getMessage() + "</p>");
                                    }
                                    nextQType = "";
                                    break;
                                default:
                                    out.println("<button type=\"button\" id=\"submit\">Edit Gear</button>");
                                    nextQType = "E1";
                            }
                            
                            out.println(
                            "<script>        $(\"#submit\").click(function() {\n" +
            "            const url = new URL(window.location.toLocaleString());\n" +
            "            var data = {\n" +
            "                qtype: \"" + nextQType + "\",\n" +
            "                gearIID: url.searchParams.get('iid'),\n" +
            "                code: $(\"#code\").val(),\n" +
            "                gear_model: $(\"#gear_models\").val(),\n" +
            "                notes: $(\"#notes\").val()\n" +
            "            };\n" +
            "            $.get(\"Gear\", data, function(data) {\n" +
            "                $(\"#editedData\").html(data);\n" +
            "            });\n" +
            "        });</script>"        
                            );
                        }
                    }
                } else {
                    out.println("ERROR: invalid id");
                }
            } catch (Exception e) {
                out.println("<p>ERROR: invalid id</p>");
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
