/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Wervy Ouzades
 */
public class QT {
    /*
    private static final String url = "jdbc:postgresql://caocdb.duckdns.org:36/ocdb";
    private static final String user = "postgres";
    private static final String password = "Ballstate1";
    
    
    //format: get [table] [value] by [query field]
    //
    private static final String url = "jdbc:postgresql://jelani.db.elephantsql.com:5432/gmsebrgp";
    private static final String user = "gmsebrgp";
    private static final String password = "jNkiSX6nbIJ_X_DgsZteyps4zkSEYOPF";
    //*/

    
    public static void populateGearArray(ArrayList<Gear> gearArray, String name) {
        String SQL = "SELECT * FROM gear WHERE person_id = (SELECT id FROM people WHERE LOWER(name) = LOWER('" + name + "'));";
        try(//Connection conn = connect();
                Statement stmt = OCDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);)
        {
            while (rs.next()) {
                //System.out.println("hao aer u");
                int id = Integer.parseInt(rs.getString("id"));
                Gear temp = Gear.populate(id);
                temp.updateReferences();//= new Gear(-1, "-1", -1, -1, -1);
                /*System.out.println("Gear Type ID: " + temp.gear_type_id);
                System.out.println(temp.gear_type.name);
                //temp = temp.populate(id);
                System.out.println("ID: " + id);*/
                gearArray.add(temp);
                //if (id == 10) break;
                //System.out.println("no");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*public static void printGearByName(String name) {
        String SQL = "SELECT * FROM gear WHERE person_id = (SELECT id FROM people WHERE LOWER(name) = LOWER('" + name + "'));";
        try(//Connection conn = connect();
                Statement stmt = OCDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);)
        {
            while (rs.next()) {
                //System.out.println("hao aer u");
                int id = Integer.parseInt(rs.getString("id"));
                System.out.println("id: " + id);
                String code = getGearCodeById(id);
                System.out.println("Code: CA" + code);
                String type = getGearTypeNameById(Integer.parseInt(rs.getString("gear_type_id")));
                System.out.println("Gear Type: " + type);
                String model = getGearModelDescriptionById(Integer.parseInt(rs.getString("gear_model_id")));
                System.out.println("Gear Description: " + model);
                
                
                //System.out.println("no");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
    
    public static String getPersonNameById(int id) {
        return getStringFromId(id, "people", "name");
    }
    
    public static String getPersonLastTripById(int id) {
        return getStringFromId(id, "people", "last_trip");
    }
    
    public static String getGearCodeById(int id) {
        return getStringFromId(id, "gear", "code");
    }
    
    public static int getGearPersonIdById(int id) {
        return getIntFromId(id, "gear", "person_id");
    }
    
    public static int getGearGearTypeIdById(int id) {
        return getIntFromId(id, "gear", "gear_type_id");
    }
    
    public static int getGearGearModelIdById(int id) {
        return getIntFromId(id, "gear", "gear_model_id");
    }
    
    public static String getGearTypeCodeById(int id) {
        return getStringFromId(id, "gear_types", "code");
    }

    public static String getGearTypeNameById(int id) {
        return getStringFromId(id, "gear_types", "name");
    }
    
    public static int getGearModelGearTypeIdById(int id) {
        return getIntFromId(id, "gear_models", "gear_type_id");
    }
    
    public static String getGearModelGearModelById(int id) {
        return getStringFromId(id, "gear_models", "gear_model");
    }
    
    public static String getGearModelDescriptionById(int id) {
        return getStringFromId(id, "gear_models", "description");
    }
    
    public static int getTransactionGearIdById(int id) {
        return getIntFromId(id, "transactions", "gear_id");
    }    
    
    public static int getTransactionOldPersonIdById(int id) {
        return getIntFromId(id, "transactions", "old_person_id");
    }
    
    public static int getTransactionNewPersonIdById(int id) {
        return getIntFromId(id, "transactions", "new_person_id");
    }
    
    public static String getTransactionNotesById(int id) {
        return getStringFromId(id, "transactions", "notes");
    }
    
    public static String getTransactionDateById(int id) {
        return getStringFromId(id, "transactions", "date");
    }
    
    public static String getTransactionDatetimeById(int id) {
        return getStringFromId(id, "transactions", "datetime");
    }
    
    private static String getStringFromId(int id, String table, String target) {
        String SQL = "SELECT " + target + " FROM " + table + " WHERE id = " + id + ";";
        try (//Connection conn = connect();
            Statement stmt = OCDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            while (rs.next()) {
                return rs.getString(target);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }
    
    private static int getIntFromId(int id, String table, String target) {
        String SQL = "SELECT " + target + " FROM " + table + " WHERE id = " + id + ";";
        try (//Connection conn = connect();
            Statement stmt = OCDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            while (rs.next()) {
                return Integer.parseInt(rs.getString(target));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
        return -1;
    }
    
    public static void query () {//barebones
        String SQL = "";
        try (//Connection conn = connect();
            Statement stmt = OCDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 

 }
