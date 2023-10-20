/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Wervy Ouzades
 */
public class FT {

    public static void addTransactionToDB(Gear gear, Person old_person, Person new_person) {//doesn't include notes
        if (gear.person.id != new_person.id) {
            String SQL1 = "INSERT INTO transactions (gear_id, old_person_id, new_person_id) VALUES (" + gear.id + ", " + old_person.id + ", " + new_person.id + ");";
            String SQL2 = "UPDATE gear SET person_id = " + new_person.id + " WHERE id = " + gear.id + ";";
            try {
                     Statement stmt1 = OCDB.conn.createStatement();  
                     stmt1.executeQuery(SQL1);
                //DT.populateLocalDB();
            } catch (SQLException ex) {
                if (!ex.getMessage().equals("No results were returned by the query.")){
                    //System.out.println(ex.getMessage());
                }
            }
            try {
                    Statement stmt2 = OCDB.conn.createStatement();  
                    stmt2.executeQuery(SQL2);
                DT.populateLocalDB();
            } catch (SQLException ex) {
                if (!ex.getMessage().equals("No results were returned by the query.")){
                    //System.out.println(ex.getMessage());
                }
            }
        } else {
            //System.out.println("GEAR ALREADY IN " + new_person.name + "'s POSSESSION");
        }
    }

    public static String checkoutGear(Gear gear, Person person) {
        if (gear.person.name.equals(OCDB.checked_in)) {
            try {
                FT.addTransactionToDB(gear, gear.person, person);
                return "successfully checked out "
                        + gear.gear_type.name + " CA" + gear.code + " to " + person.name;
            } catch (Exception e) {
                return "failure: " + e.getMessage();
            }
        } else {
            return "gear already checked out<br>"
                    + "transfer gear from " + gear.person.name + " to " + person.name + "?<br>"
                    + "<button type=\"button\" id=\"transfer\">Transfer</button>"
                    + "    <div id=\"result2\"></div>"
                    + "    <script>\n" +
"        $(\"#transfer\").click(function() {\n" +
"            var data = {\n" +
"                qtype: \"T\",\n" +
"                name: $(\"#name\").val(),\n" +
"                code: $(\"#code\").val(),\n" +
"                type: $(\"#type\").val()\n" +
"            };\n" +
"            $.get(\"query\", data, function(data) {\n" +
"                $(\"#result2\").html(data);\n" +
"            });\n" +
"        });\n" +
"    </script>";
//            FT.addTransactionToDB(gear, gear.person, person);
                
        }
    }
    
    public static String transferGear(Gear gear, Person person) {
        if (!gear.person.name.equals(OCDB.checked_in)) {
            try {
                FT.addTransactionToDB(gear, gear.person, person);
                return "success";
            }  catch (Exception e) {
                return "failure: " + e.getMessage();
            }
        } else {
            return "ERROR: either something got externally updated or sebastian screwed up";
        }
    }
    
    public static String checkinGear(Gear gear) {
        if (!gear.person.name.equals(OCDB.checked_in)) {
            try {
                FT.addTransactionToDB(gear, gear.person, DT.getPersonByName(OCDB.checked_in));
                return "successfully checked in "
                        + gear.gear_type.name + " CA" + gear.code + " from " + gear.person.name;
            } catch (Exception e) {
                return "failure: " + e.getMessage();
            }
        } else {
            return "ERROR: gear already checked in";
            //System.out.println("Gear already checked in");
        }
    }

    public static String addPersonToDB(String name) {
        if (DT.getPersonByName(name) == null) {
            String SQL = "INSERT INTO people (name) VALUES ('" + name + "');";
            try {
                OCDB.connect();
                Statement stmt = OCDB.conn.createStatement();
                stmt.executeQuery(SQL);
                OCDB.close();
            } catch (SQLException ex) {
                if (!ex.getMessage().equals("No results were returned by the query.")){
                    return ex.getMessage();
                }
            }
            return "successfully added person " + name + " to database";
        } else {
            return "ERROR: person already in database";
        }
    }
    
    public static String removePersonFromDB(String name) {//be sure to double check before doing it
        Person person = DT.getPersonByName(name);
        if (person == null) {
            return "ERROR: person not found";
        } else {
            String SQL = "DELETE FROM people WHERE id = " + person.id + ";";
            try {
                OCDB.connect();
                Statement stmt = OCDB.conn.createStatement();
                stmt.executeQuery(SQL);
                OCDB.close();
            } catch (SQLException ex) {
                if (!ex.getMessage().equals("No results were returned by the query.")){
                    return ex.getMessage();
                }
            }
            return "successfully removed person " + name + " from database";
        }
    }
    
    public static String addGearToDB(String code, String type, String modelDescription) {
        if (DT.getGearByCodeAndType(code, type) != null)
            return "ERROR: gear already exists";
        else {
            Person ptemp = DT.getPersonByName(OCDB.checked_in);
            Gear_Type ttemp = DT.getGearTypeByCode(type);
            Gear_Model mtemp = DT.getGearModelByDescription(modelDescription);
            if (mtemp == null)
                return "ERROR: gear model doesn't exist in database";
            else if (ttemp == null) 
                return "ERROR: gear type doesn't exist in database";
            else {
                String SQL = "INSERT INTO gear (code, person_id, gear_type_id, gear_model_id) VALUES ("
                        + code + ", " + ptemp.id + ", " + ttemp.id + ", " + mtemp.id + ");";
                try {
                    OCDB.connect();
                    Statement stmt = OCDB.conn.createStatement();
                    stmt.executeQuery(SQL);
                    OCDB.close();
                } catch (SQLException ex) {
                    if (!ex.getMessage().equals("No results were returned by the query.")){
                        return ex.getMessage();
                    }
                }
                return "successfully added gear " + ttemp.name + " CA" + code + " to database";
            }   
        }
    }
    
    public static String editGear(int id, int gearModelId, String notes) {
        String SQL = "UPDATE gear SET gear_model_id = " + gearModelId + ", notes = '" + notes + "' where id = " + id + ";";
        try {
            OCDB.connect();
            Statement stmt = OCDB.conn.createStatement();
            stmt.executeQuery(SQL);
            OCDB.close();
        } catch (SQLException ex) {
            if (!ex.getMessage().equals("No results were returned by the query.")){
                return ex.getMessage();
            }
        }
        return "successfully edited gear IID" + id;
    }

    public static void query() {//barebones
        String SQL = "";
        try (//Connection conn = connect();
                 Statement stmt = OCDB.conn.createStatement();  ResultSet rs = stmt.executeQuery(SQL);) {

                OCDB.close();
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
    }
}
