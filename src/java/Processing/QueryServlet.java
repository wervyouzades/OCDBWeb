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
import javax.*;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import java.io.Writer;

/**
 *
 * @author Wervy Ouzades
 */
@WebServlet(name = "QueryServlet", urlPatterns = {"/query"})
public class QueryServlet extends HttpServlet {

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
            switch (request.getParameter("qtype").toLowerCase()) {
                case "c"://check server status
                    out.println("SERVER STATUS: ");
                    if (OCDB.checkServerStatus()) {
                        out.println("we're chillin");
                    } else {
                        out.println("oh no (spam it a coupla times maybe?)");
                    }
                    break;

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
                
                case "dg":
                    try {
                        //NEED TO FIX BAD INPUTS-----------------------------------------------------------------------------------
                        ArrayList<Gear> gear = new ArrayList<Gear>();
                        for (Gear g : DT.gear) {
                            gear.add(g);
                        }
                        String name = request.getParameter("name");
                        String code = request.getParameter("code");
                        String type = request.getParameter("type");
                        String boxString = request.getParameter("showOnlyCheckedOutGear");
                        boolean showOnlyCheckedOutGear = Boolean.parseBoolean(boxString);
                        if (showOnlyCheckedOutGear) {
                            gear = DT.purgeGearArrayByCheckedOut(gear);
                        }
                        boolean chillin = true;
                        if (name.length() > 0) {
                            Person p = DT.getPersonByName(name);
                            if (p == null) {
                                chillin = false;
                                out.println("ERROR: person doesn't exist or isn't in database");
                            } else {
                                gear = DT.purgeGearArrayByPerson(gear, p);
                            }
                        }
                        if (code.length() > 0 || type.length() > 0) {
                            if (code.length() > 0 && type.length() > 0) {
                                code = IT.processGearCode(code);
                                type = IT.processGearTypeCode(type);
                                gear = DT.purgeGearArrayByCodeAndType(gear, code, type);
                            } else {
                                chillin = false;
                                out.println("ERROR: if you put in gear code you also have to put in gear type (and vice versa)");
                            }
                        }

                        if (chillin == true) {
                            out.println(RT.printGearArray(gear));
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                    
                case "dp":
                    try {
                        ArrayList<Person> people = new ArrayList<Person>();
                        for (Person p : DT.people) {
                            people.add(p);
                        }
                        String pwg = request.getParameter("onlyShowPeopleWithGear");
                        boolean onlyShowPeopleWithGear = Boolean.parseBoolean(pwg);
                        if (onlyShowPeopleWithGear) {
                            people = new ArrayList<Person>();
                            people = DT.getPeopleWithGear();
                        }
                        String returnation = RT.printPersonArray(people);
                        out.println(returnation);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                    
                case "dt":
                    //NEED TO FIX BAD INPUTS-----------------------------------------------------------------------------------
                    try {
                        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
                        for (Transaction t : DT.transactions) {
                            transactions.add(t);
                        }
                        String name = request.getParameter("name");
                        String code = request.getParameter("code");
                        String type = request.getParameter("type");
                        boolean chillin = true;
                        if (name.length() > 0) {
                            Person p = DT.getPersonByName(name);
                            if (p == null) {
                                chillin = false;
                                out.println("ERROR: person doesn't exist or isn't in database");
                            } else {
                                transactions = DT.purgeTransactionArrayByPerson(transactions, p);
                            }
                        }
                        if (code.length() > 0 || type.length() > 0) {
                            if (code.length() > 0 && type.length() > 0) {
                                code = IT.processGearCode(code);
                                type = IT.processGearTypeCode(type);
                                Gear gdueny = DT.getGearByCodeAndType(code, type);
                                if (gdueny != null) {
                                    transactions = DT.purgeTransactionArrayByGear(transactions, gdueny);
                                } else {
                                    chillin = false;
                                    out.println("ERROR: gear not found");
                                }
                            } else {
                                chillin = false;
                                out.println("ERROR: if you put in gear code you also have to put in gear type (and vice versa)");
                            }
                        }
                        if (chillin) {
                            out.println(RT.printTransactionArray(transactions));
                        }
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
                    try {
                        String name = request.getParameter("name");
                        if (name.length() > 0) {
                            out.println("management result:<br>" + FT.removePersonFromDB(name) + "<br>");
                        } else {
                            out.println("ERROR: enter a name");
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                    
                case "mag"://manage add gear
                    try {
                        String code = request.getParameter("code");
                        String type = request.getParameter("type");
                        String modelDescription = request.getParameter("modelDescription");
                        if (code.length() == 0 || type.length() == 0 || modelDescription.length() == 0) {
                            out.println("ERROR: fill in required fields:<br>");
                            if (code.length() == 0) {
                                out.println("code<br>");
                            }
                            if (type.length() == 0) {
                                out.println("type<br>");
                            }
                            if (modelDescription.length() == 0) {
                                out.println("model description<br>");
                            }
                        } else {
                            out.println("management result:<br>" + IT.processNewGear(code, type, modelDescription) + "<br>");
                        }
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
                    break;
                default:
                    out.println("sebastian screwed smth up");
            }
            try {
                OCDB.conn.close();
            } catch (Exception e) {
                out.println(e.getMessage());
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
