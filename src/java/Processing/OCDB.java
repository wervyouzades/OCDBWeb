/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Processing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.*;
import java.util.*;
import java.time.ZonedDateTime;
import java.io.PrintWriter;
/**
 *
 * @author Wervy Ouzades
 */
public class OCDB {
    /*
    private static String url = "jdbc:postgresql://localhost:5432/backuptest3";//was aserfasv
    private static String user = "postgres";
    private static String password = "Ballstate1";
    public static String server = "TEST SERVER (data is NOT accurate)";
    */
    ///*
    private static String url = "jdbc:postgresql://jelani.db.elephantsql.com:5432/gmsebrgp";
    private static String user = "gmsebrgp";
    private static String password = "jNkiSX6nbIJ_X_DgsZteyps4zkSEYOPF";
    public static String server = "Normal server; ElephantSQL";
    //*/
    /*
    private static String url = "jdbc:postgresql://136.56.2.100:36/aserfasv";
    private static String user = "postgres";
    private static String password = "Ballstate1";
    */
    
    
    public static Connection conn;
    public final static String checked_in = "GEAR_CLOSET";
    public final static String missing = "MISSING";
    public final static String unusable = "UNUSABLE";
    public final static String phased_out = "PHASED_OUT";
    public static Scanner diyan = new Scanner(System.in);
    private static long a, b;
    
    
    /*public static void main(String[] args) {
        OCDB.connect();

        setPointA();
        System.out.println("Outdoors Club Gear Program");

        //System.out.println(IT.inputGearCode());

        while(true) {
            if(mainFunction()) break;
        }
        

        
        setPointB();
        System.out.println("TIME: " + deltaTime());
        //System.out.println(ZonedDateTime.now().toInstant().toEpochMilli());
        //System.out.println("ael");
        //QT.printGearByName("GEAR_CLOSET");
    }*/
    
    
    /*public String css = "body {font-family: Verdana; font-size: 10pt; font-style: normal; line-height: normal; font-weight: normal; color:#b2b2b2; text-decoration: none; background-color:#000000}\n" +
"\n" +
"p {font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-style: normal; line-height: normal; font-weight: normal; color: #b2b2b2; text-decoration: none}\n" +
"p.small {font-family: Verdana, MS Sans Serif, Arial; font-size: 10pt; font-style: normal; line-height: normal; font-weight: normal; color: #b2b2b2; text-decoration: none}\n" +
"p.purple {font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-style: normal; line-height: normal; font-weight: normal; color: #BEA8D0; text-decoration: none}\n" +
"\n" +
"table {font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-style: normal; line-height: normal; font-weight: normal; color: #b2b2b2; text-decoration: none}\n" +
"\n" +
"A:link {color: #ffffff; text-decoration: underline; font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-weight: normal}\n" +
"A:active {color: #FF0000; text-decoration: none; font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-weight: normal}\n" +
"A:visited {color: #999999; text-decoration: underline; font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-weight: normal}\n" +
"A:hover {background:#63437C; color: #ffffff; text-decoration: none; font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-weight: normal}\n" +
"\n" +
"A.small:link {color: #b2b2b2; text-decoration: none; font-family: Verdana, MS Sans Serif, Arial; font-size: 9pt; font-weight: normal}\n" +
"A.small:active {color: #FF0000; text-decoration: none; font-family: Verdana, MS Sans Serif, Arial; font-size: 9pt; font-weight: normal}\n" +
"A.small:visited {color: #b2b2b2; text-decoration: none; font-family: Verdana, MS Sans Serif, Arial; font-size: 9pt; font-weight: normal}\n" +
"A.small:hover {background:#63437C; color: #ffffff; text-decoration: none; font-family: Verdana, MS Sans Serif, Arial; font-size: 9pt; font-weight: normal}\n" +
"\n" +
"strong {font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-style: normal; line-height: normal; font-weight: bold; color: #b2b2b2; text-decoration: none}\n" +
"\n" +
"font {font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-style: normal; line-height: normal; color: #b2b2b2; font-weight: normal; text-decoration: none}\n" +
"\n" +
"h1 {background:#63437C; margin: 2; text-align:center; font-family: Arial Black, Arial, Verdana; font-size: 12pt; font-style: normal; line-height: 24pt; font-weight: normal; color:#ffffff; text-decoration: none}\n" +
"h2 {text-align:center; font-family: Verdana, MS Sans Serif, Arial; font-size: 12pt; font-style: normal; line-height: 14pt; font-weight: normal; color: #b2b2b2; text-decoration: none}\n" +
"h3 {background:#63437C; margin: 2; text-align:center; font-family: Arial, Verdana; font-size: 12pt; font-style: normal; line-height: 14pt; font-weight: normal; color:#ffffff; text-decoration: none}\n" +
"h4 {text-align:left; font-family: Verdana, MS Sans Serif, Arial; font-size: 12pt; font-style: normal; line-height: 14pt; font-weight: normal; color: #b2b2b2; text-decoration: none}\n" +
"h6 {background:#63437C; margin: 2; text-align:left; font-family: Verdana, MS Sans Serif, Arial; font-size: 11pt; font-style: normal; line-height: 14pt; font-weight: normal; color:#ffffff; text-decoration: none}\n" +
"\n" +
"ul {color: #b2b2b2; line-height: 16pt; text-color: #b2b2b2; font-weight: normal; font-family: Verdana, MS Sans Serif, Arial;font-size: 11pt;}\n" +
"ul.loose {color: #b2b2b2; line-height: 23pt; text-color: #b2b2b2; font-weight: normal; font-family: Verdana, MS Sans Serif, Arial;}\n" +
"ul.small {color: #b2b2b2; line-height: 11pt; font-size: 10pt; text-color: #b2b2b2; font-weight: normal; font-family: Verdana, MS Sans Serif, Arial;}\n" +
"ol {color: #b2b2b2; line-height: 16pt; text-color: #b2b2b2; font-weight: normal; font-family: Verdana, MS Sans Serif, Arial;}"*/
    
    
    
    public static boolean checkServerStatus() {
        connect();
        return !(conn == null);
    }
    
    public static void exist(PrintWriter out) {
        connect(out);
            String SQL = "SELECT * FROM people;";
        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            String result = "";
            while (rs.next()) {
                result += rs.getString("name");
            }
            out.println(result);
        } catch (SQLException ex) {
            out.println(ex.getMessage());
        }
        
    }
    
    private static boolean mainFunction() {
        if (conn != null) {
            try{
                DT.populateLocalDB();
                System.out.println();
                System.out.println("Choose action");
                System.out.println("Check [O]ut gear");
                System.out.println("Check [I]n gear");
                System.out.println("[D]isplay data");
                System.out.println("[E]xit");

                String input = OCDB.diyan.nextLine();
                switch (input.toLowerCase().charAt(0)) {
                    case 'o': 
                        IT.inputCheckout();
                        break;
                    case 'i':
                        IT.inputCheckin();
                        break;
                    case 'd':
                        OCDB.displayFunction();
                        break;
                    case 'e':
                        return true;
                    default:
                        System.out.println("No valid option selected.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Failed to connect to database");
            String trash = diyan.nextLine();
        }
        return false;
    }
    
    private static void displayFunction() {
        System.out.println("Display [G]ear");
        System.out.println("Display [T]ransactions");
        System.out.println("Display [P]eople");
        String input = diyan.nextLine();
        switch (input.toLowerCase().charAt(0)) {
            case 'g':
                OCDB.displayGear();
                break;
            case 't':
                OCDB.displayTransactions();
                break;
            case 'p':
                OCDB.displayPeople();
                break;
            default:
                System.out.println("No valid option selected.");
                break;
        }
        
    }
    
    private static void displayGear() {
        System.out.println("Select by:");
        System.out.println("[A]ll");
        System.out.println("[C]ode and type");
        System.out.println("[P]erson");
        String input = diyan.nextLine();
        switch (input.toLowerCase().charAt(0)) {
            case 'a':
                PT.printGearArray(DT.gear);
                break;
            case 'c':
                System.out.println("Enter code:");
                String code = IT.inputGearCode();
                System.out.println("Enter type letter");
                String type = IT.inputGearTypeCode();
                PT.printGear(DT.getGearByCodeAndType(code, type));
                break;
            case 'p':
                System.out.println("Enter name:");
                String name = OCDB.diyan.nextLine();
                PT.printGearArray(DT.getGearByPerson(DT.getPersonByName(name)));
                break;
            default:
                System.out.println("No valid option selected.");
                break;
        }
    }
    
    private static void displayTransactions() {
        System.out.println("Select by:");
        System.out.println("[A]ll");
        System.out.println("[G]ear");
        System.out.println("[P]erson");
        String input = diyan.nextLine();
        switch (input.toLowerCase().charAt(0)) {
            case 'a':
                PT.printTransactionArray(DT.transactions);
                break;
            case 'g':
                System.out.println("Gear code:");
                String code = IT.inputGearCode();
                System.out.println("Gear type:");
                String type = IT.inputGearTypeCode();
                Gear tempGear = DT.getGearByCodeAndType(code, type);
                PT.printTransactionArray(DT.getTransactionsByGear(tempGear));
                break;
            case 'p':
                System.out.println("Person name:");
                String name = OCDB.diyan.nextLine();
                Person tempPerson = DT.getPersonByName(name);
                PT.printTransactionArray(DT.getTransactionsByPerson(tempPerson));
                break;
            default:
                System.out.println("No valid option selected.");
                break;
        }
        
    }
    
    private static void displayPeople() {
        System.out.println("Select by:");
        System.out.println("[A]ll");
        System.out.println("[T]rip");
        System.out.println("[P]ossesses gear");
        String input = diyan.nextLine();
        switch (input.toLowerCase().charAt(0)) {
            case 'a':
                PT.printLocalPeople();
            case 't':
                System.out.println("Enter trip name:");
                String tripname = diyan.nextLine();
                PT.printPersonArray(DT.getPeopleByTrip(tripname));
            case 'p':
                PT.printPersonArray(DT.getPeopleWithGear());
                break;
            default:
                System.out.println("No valid option selected.");
                break;
        }
        
    }
    
    private static long deltaTime() {
        return b - a;
    }
    private static void setPointA() {
        a = ZonedDateTime.now().toInstant().toEpochMilli();
    }
    private static void setPointB() {
        b = ZonedDateTime.now().toInstant().toEpochMilli();
    }
    
    public static void connect(PrintWriter out) {
        if (conn != null) {
            try {
            conn.close();
            } catch (SQLException e) {
                out.println(e.getMessage());
            }
        }
        conn = null;
        
        //System.out.println("query");
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
    
        public static void connect() {
            if (conn != null) {
            try {
            conn.close();
            } catch (SQLException e) {
            }
        }
        conn = null;
        //System.out.println("query");
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                
            }
        }
    }
    
}








    /*
    public void printGearByName(String name) {
        String SQL = "SELECT * FROM gear WHERE person_id = (SELECT id FROM people WHERE LOWER(name) = LOWER('" + name + "'));";
        try(Connection conn = connect();
                Statement stmt = conn.createStatement();
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
    }
    
    
    public String getGearCodeById(int id) {
        String SQL = "SELECT code FROM gear WHERE id = " + id + ";";
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            while (rs.next()) {
                return rs.getString("code");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "";
    }

    public String getGearTypeNameById(int id) {
        String SQL = "SELECT name FROM gear_types WHERE id = " + id + ";";
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            while (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "";
    }
    
    public String getGearModelDescriptionById(int id) {
        String SQL = "SELECT description FROM gear_models WHERE id = " + id + ";";
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            while (rs.next()) {
                return rs.getString("description");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "";
    }
    
    public String getGearModelCodeById(int id) {
        String SQL = "SELECT description FROM gear_models WHERE id = " + id + ";";
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            while (rs.next()) {
                return rs.getString("code");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "";
    }
    
    public void query () {//barebones
        String SQL = "";
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);) {
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
        
       
     public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    */
