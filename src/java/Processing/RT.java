/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;

/**
 *
 * @author Wervy Ouzades
 */
import java.util.ArrayList;
public class RT {
    public static String printTransaction(Transaction transaction) {
        String result = "<tr>"
                + "<td>CA" + transaction.gear.code + "</td>"
                + "<td>" + transaction.gear.gear_type.name + "</td>"
                + "<td>" + transaction.old_person.name + "</td>"
                + "<td>" + transaction.new_person.name + "</td>"
                + "<td>" + transaction.datetime + "</td>";
        return result;
    }
    public static String printTransactionArray(ArrayList<Transaction> transactions) {
        String result = "<table>";
        result += "<tr>"
                + "<td>Gear code</td>"
                + "<td>Gear type</td>"
                + "<td>Person giving up gear</td>"
                + "<td>Person receiving gear</td>"
                + "<td>Date</td>"
                + "</tr>";
        for (Transaction t : transactions) {
            result += printTransaction(t);
        }
        result += "</table>";
        return result;
    }
    public static String printGear(Gear gear) {
        String result = "<tr>"
                + "<td>CA" + gear.code + "</td>"
                + "<td>" + gear.gear_type.name + "</td>"
                + "<td>" + gear.person.name + "</td>"
                + "<td>" + gear.gear_model.description + "</td>"
                + "</tr>";
        return result;
    }
    public static String printGearArray(ArrayList<Gear> gear) {
        String result = "<table>";
        result += "<tr>"
                + "<td>Gear code</td>"
                + "<td>Gear type</td>"
                + "<td>Person in possession of gear</td>"
                + "<td>Gear model</td>"
                + "</tr>";
        for (Gear g : gear) {
            result += printGear(g);
        } 
        result += "</table>";
        return result;
    }
    
    public static String printPerson(Person person) {
        String result = "<tr>"
                + "<td>" + person.name + "</td>"
                + "</tr>";
        return result;
    }
    
    public static String printPersonArray(ArrayList<Person> people) {
        String result = "<table>";
        result += "<tr>"
                + "<td>Name</td>"
                + "</tr>";
        for (Person p : people) {
            result += printPerson(p);
        }
        result += "</table>";
        return result;
    }
    /*
    public static String printGearModel(Gear_Model gear_model) {
        System.out.println("Gear model: " + gear_model.gear_model);
        System.out.println("Gear model description: " + gear_model.description);
        System.out.println("Gear model type: " + gear_model.gear_type.name);
    }
    
    public static String printGearType(Gear_Type gear_type) {
        System.out.println("Gear type code: " + gear_type.code);
        System.out.println("Gear type name: " + gear_type.name);
    }
    
    public static String printPerson(Person person) {
        System.out.println("Person name: " + person.name);
        if (!person.last_trip.equals("null")) {
            System.out.println("Last trip attended: " + person.last_trip);
        }
    }
    
        public static String printTransaction(Transaction transaction) {
        if (transaction.new_person.name.equals(OCDB.checked_in)) {
            System.out.println("Check-in:");
            System.out.println("Gear: " + transaction.gear.gear_type.name + " CA" + transaction.gear.code);
            System.out.println("Person: " + transaction.old_person.name);
            System.out.println("Notes: " + transaction.notes);
            System.out.println("Date: " + transaction.date);
        } else if (transaction.old_person.name.equals(OCDB.checked_in)) {
            System.out.println("Checkout:");
            System.out.println("Gear: " + transaction.gear.gear_type.name + " CA" + transaction.gear.code);
            System.out.println("Person: " + transaction.new_person.name);
            System.out.println("Notes: " + transaction.notes);
            System.out.println("Date: " + transaction.date);
        } else {
            System.out.println("Transfer:");
            System.out.println("Gear: " + transaction.gear.gear_type.name + " CA" + transaction.gear.code);
            System.out.println("Old person: " + transaction.old_person.name);
            System.out.println("New person: " + transaction.new_person.name);
            System.out.println("Notes: " + transaction.notes);
            System.out.println("Date: " + transaction.date);
        }
    }*/
}
