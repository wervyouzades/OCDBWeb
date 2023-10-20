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
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 *
 * @author Wervy Ouzades
 */
public class DT {
        /*
    private static final String url = "jdbc:postgresql://caocdb.duckdns.org:36/ocdb";
    private static final String user = "postgres";
    private static final String password = "Ballstate1";
    

    //format: get [table] [value] by [query field]
    //
    private static final String url = "jdbc:postgresql://jelani.db.elephantsql.com:5432/gmsebrgp";
    private static final String user = "gmsebrgp";
    private static final String password = "jNkiSX6nbIJ_X_DgsZteyps4zkSEYOPF";
    */
    public static ArrayList<Transaction> transactions;
    public static ArrayList<Gear> gear;
    public static ArrayList<Person> people;
    public static ArrayList<Gear_Model> gear_models;
    public static ArrayList<Gear_Type> gear_types;
    
    public static void populateLocalDB() {
        OCDB.connect();
        transactions = new ArrayList<Transaction>();
        gear = new ArrayList<Gear>();
        people = new ArrayList<Person>();
        gear_models = new ArrayList<Gear_Model>();
        gear_types = new ArrayList<Gear_Type>();
        String gearTypesSQL = "SELECT * FROM gear_types;";
        String gearModelsSQL = "SELECT * FROM gear_models;";
        String peopleSQL = "SELECT * FROM people ORDER BY name;";
        String gearSQL = "SELECT * FROM gear ORDER BY gear_type_id, code;";
        String transactionsSQL = "SELECT * FROM transactions ORDER BY datetime DESC;";
        OCDB.connect();
        try (
            Statement gearTypesSTMT = OCDB.conn.createStatement();
            Statement gearModelsSTMT = OCDB.conn.createStatement();
            Statement peopleSTMT = OCDB.conn.createStatement();
            Statement gearSTMT = OCDB.conn.createStatement();
            Statement transactionsSTMT = OCDB.conn.createStatement();
            ResultSet gearTypesRS = gearTypesSTMT.executeQuery(gearTypesSQL);
            ResultSet gearModelsRS = gearModelsSTMT.executeQuery(gearModelsSQL);
            ResultSet peopleRS = peopleSTMT.executeQuery(peopleSQL);
            ResultSet gearRS = gearSTMT.executeQuery(gearSQL);
            ResultSet transactionsRS = transactionsSTMT.executeQuery(transactionsSQL);
                ) {
            while (gearTypesRS.next()) {
                int id = Integer.parseInt(gearTypesRS.getString("id"));
                String code = gearTypesRS.getString("code");
                String name = gearTypesRS.getString("name");
                Gear_Type temp = new Gear_Type(id, code, name);
                temp.updateLocalReferences();
                gear_types.add(temp);
            }
            while (gearModelsRS.next()) {
                int id = Integer.parseInt(gearModelsRS.getString("id"));
                int gear_type_id = Integer.parseInt(gearModelsRS.getString("gear_type_id"));
                String gear_model = gearModelsRS.getString("gear_model");
                String description = gearModelsRS.getString("description");
                String price = gearModelsRS.getString("price");
                Gear_Model temp = new Gear_Model(id, gear_type_id, gear_model, description, price);
                temp.updateLocalReferences();
                gear_models.add(temp);
            }
            while (peopleRS.next()) {
                int id = Integer.parseInt(peopleRS.getString("id"));
                String name = peopleRS.getString("name");
                String last_trip;
                try{last_trip = peopleRS.getString("last_trip");} catch (Exception e) {
                    last_trip = "";
                }
                Person temp = new Person(id, name, last_trip);
                temp.updateLocalReferences();
                people.add(temp);
            }
            while (gearRS.next()) {
                int id = Integer.parseInt(gearRS.getString("id"));
                int person_id = Integer.parseInt(gearRS.getString("person_id"));
                String code = gearRS.getString("code");
                int gear_type_id = Integer.parseInt(gearRS.getString("gear_type_id"));
                int gear_model_id = Integer.parseInt(gearRS.getString("gear_model_id"));
                String notes = gearRS.getString("notes");
                if (notes == null) {
                    notes = "";
                }
                Gear temp = new Gear(id, code, person_id, gear_type_id, gear_model_id, notes);
                temp.updateLocalReferences();
                gear.add(temp);
            }
            while (transactionsRS.next()) {
                int id = Integer.parseInt(transactionsRS.getString("id"));
                int gear_id = Integer.parseInt(transactionsRS.getString("gear_id"));
                int old_person_id = Integer.parseInt(transactionsRS.getString("old_person_id"));
                int new_person_id = Integer.parseInt(transactionsRS.getString("new_person_id"));
                String notes;
                OffsetDateTime offsetdatetime = transactionsRS.getObject("datetime", OffsetDateTime.class);
                try {
                    notes = transactionsRS.getString("notes");
                } catch (Exception e) {
                    notes = "";
                }
                String date = transactionsRS.getString("date");
                String datetime = transactionsRS.getString("datetime");
                Transaction temp = new Transaction(id, gear_id, old_person_id, new_person_id, notes, date, datetime, offsetdatetime);
                temp.updateLocalReferences();
                transactions.add(temp);
            }
            
            OCDB.close();
            populateLocalGearWithTransactions();
        } catch (SQLException ex) {
            OCDB.close();
            System.out.println(ex.getMessage());
        }
    }
    
    public static Person getPersonByName(String name) {
        for (Person p : DT.people) {
            if (p.name.equalsIgnoreCase(name)) return p;
        }
        return null;
    }
    
    public static Person getPersonById(int id) {
        for (Person p : DT.people) {
            if (p.id == id) return p;
        }
        return null;
    }
    
    public static ArrayList<Transaction> getTransactionsByPerson(Person person) {
        ArrayList<Transaction> temp = new ArrayList<Transaction>();
        for (Transaction t : DT.transactions) {
            if(t.old_person.id == person.id || t.new_person.id == person.id) temp.add(t);
        }
        return temp;
    }
    
    public static ArrayList<Transaction> getTransactionsByGear(Gear gear) {
        ArrayList<Transaction> temp = new ArrayList<Transaction>();
        for (Transaction t : DT.transactions) {
            if (t.gear.id == gear.id) temp.add(t);
        }
        return temp;
    }
    
    public static ArrayList<Transaction> purgeTransactionArrayByPerson(ArrayList<Transaction> transactions, Person person) {
        ArrayList<Transaction> temp = new ArrayList<Transaction>();
        for (Transaction t : transactions) {
            if(t.old_person.id == person.id || t.new_person.id == person.id) temp.add(t);
        }
        return temp;
    }
    
    public static ArrayList<Transaction> purgeTransactionArrayByGear(ArrayList<Transaction> transactions, Gear gear) {
        ArrayList<Transaction> temp = new ArrayList<Transaction>();
        for (Transaction t : transactions) {
            if (t.gear.id == gear.id) temp.add(t);
        }
        return temp;
    }
    
    public static ArrayList<Gear> getGearByPerson(Person person) {
        ArrayList<Gear> temp = new ArrayList<Gear>();
        for (Gear g: DT.gear) {
            if(g.person == person) temp.add(g);
        }
        return temp;
    }
    
    public static Gear getGearById(int id) {
        for (Gear g : DT.gear) {
            if (g.id == id) return g;
        }
        return null;
    }
    
    public static Gear getGearByCodeAndType(String gearCode, String gearTypeCode) {
        for (Gear g : DT.gear) {
            if (g.code.equals(gearCode) && g.gear_type.code.toLowerCase().equals(gearTypeCode.toLowerCase())) {
                return g;
            }
        }
        return null;
    }
    
    
    public static Gear_Model getGearModelByDescription(String description) {
        for (Gear_Model gearModel : gear_models) {
            if (gearModel.description.equals(description)) return gearModel;
        }
        return null;
    }
    
    public static Gear_Model getGearModelById(int id) {
        for (Gear_Model gearModel : gear_models) {
            if (gearModel.id == id) return gearModel;
        }
        return null;
    }
    
    public static Gear_Type getGearTypeByCode(String code) {
        for (Gear_Type gearType : gear_types) {
            if (gearType.code.equals(code)) return gearType;
        }
        return null;
    }
    
    public static ArrayList<Gear> purgeGearArrayByPerson(ArrayList<Gear> gear, Person person) {
        ArrayList<Gear> temp = new ArrayList<Gear>();
        for (Gear g: gear) {
            if(g.person == person) temp.add(g);
        }
        return temp;
    }
    
    public static ArrayList<Gear> purgeGearArrayByCodeAndType(ArrayList<Gear> gear, String gearCode, String gearTypeCode) {
        ArrayList<Gear> temp = new ArrayList<Gear>();
        for (Gear g : gear) {
            if (g.code.equals(gearCode) && g.gear_type.code.toLowerCase().equals(gearTypeCode.toLowerCase())) {
                temp.add(g);
            }
        }
        return temp;
    }
    
    public static ArrayList<Gear> purgeGearArrayByCode(ArrayList<Gear> gear, String gearCode) {
        ArrayList<Gear> temp = new ArrayList<Gear>();
        for (Gear g : gear) {
            if (g.code.equalsIgnoreCase(gearCode)) {
                temp.add(g);
            }
        }
        return temp;
    }
    
    public static ArrayList<Gear> purgeGearArrayByType(ArrayList<Gear> gear, String gearType) {
        ArrayList<Gear> temp = new ArrayList<Gear>();
        for (Gear g : gear) {
            if (g.gear_type.code.equalsIgnoreCase(gearType)) {
                temp.add(g);
            }
        }
        return temp;
    }
    
    public static ArrayList<Gear> purgeGearArrayByCheckedOut(ArrayList<Gear> gear) {
        ArrayList<Gear> temp = new ArrayList<Gear>();
        for (Gear g : gear) {
            if (!g.person.name.equalsIgnoreCase(OCDB.checked_in)) {
                temp.add(g);
            }
        }
        return temp;
    }
    
    public static ArrayList<Gear> purgeGearArrayByUsablePresentGear(ArrayList<Gear> gear) {
        ArrayList<Gear> temp = new ArrayList<Gear>();
        for (Gear g : gear) {
            if (!g.person.name.equalsIgnoreCase(OCDB.missing) && !g.person.name.equalsIgnoreCase(OCDB.unusable)) {
                temp.add(g);
            }
        }
        return temp;
    }
    
    public static ArrayList<Gear_Model> purgeGearModelArrayByType(ArrayList<Gear_Model> gear_models, Gear_Type gear_type) {
        ArrayList<Gear_Model> temp = new ArrayList<Gear_Model>();
        for (Gear_Model gm : gear_models) {
            if (gm.gear_type.id == gear_type.id) {
                temp.add(gm);
            }
        }
        return temp;
    }
    
    public static ArrayList<Person> getPeopleWithGear() {
        ArrayList<Person> temp = new ArrayList<Person>();
        for (Gear g : DT.gear) {
            if (!g.person.name.equals(OCDB.checked_in)) {
                boolean trigger = false;
                for (Person p : temp) {
                    if (p.id == g.person.id) trigger = true;
                }
                if (trigger) continue;
                temp.add(g.person);
            }
        }
        return temp;
    }
    
    public static ArrayList<Person> getPeopleByTrip(String tripname) {
        ArrayList<Person> temp = new ArrayList<Person>();
        for (Person p : DT.people) {
            if (p.last_trip.equals(tripname)) {
                temp.add(p);
            }
        }
        return temp;
    }
    
    public static void populateLocalGearWithTransactions() {
        for (Gear g : gear) {
            g.transactions = new ArrayList<Transaction>();
        }
        for (Transaction t : transactions) {
            try {
            getGearById(t.gear_id).transactions.add(t);
            } catch (Exception e) {
                
            }
        }
    }
    
    public static OffsetDateTime getLastTransactionDateTimeByGear(Gear gear) {
        OffsetDateTime sample = OffsetDateTime.parse("1900-01-01T00:00:00+00:00");
        Transaction temp = new Transaction(-1, -1, -1, -1, "", "", "", sample);
        for (Transaction t : gear.transactions) {
            if (t.offsetdatetime != null && t.offsetdatetime.compareTo(temp.offsetdatetime) > 0) {
                temp = t;
            }
        }
        if (temp.offsetdatetime != null && temp.offsetdatetime.compareTo(sample) > 0) {
            return temp.offsetdatetime;
        } else {
            return null;
        }
    }


    

}
