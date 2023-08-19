/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;
import java.util.*;

/**
 *
 * @author Wervy Ouzades
 */
public class PT {
    
    public static void printGear(Gear gear) {
        System.out.println("Gear code: CA" + gear.code);
        System.out.println("Gear possessed by: " + gear.person.name);
        System.out.println("Gear model description: " + gear.gear_model.description);
        System.out.println("Gear type: " + gear.gear_type.name);
    }
    
    public static void printGearModel(Gear_Model gear_model) {
        System.out.println("Gear model: " + gear_model.gear_model);
        System.out.println("Gear model description: " + gear_model.description);
        System.out.println("Gear model type: " + gear_model.gear_type.name);
    }
    
    public static void printGearType(Gear_Type gear_type) {
        System.out.println("Gear type code: " + gear_type.code);
        System.out.println("Gear type name: " + gear_type.name);
    }
    
    public static void printPerson(Person person) {
        System.out.println("Person name: " + person.name);
        if (!person.last_trip.equals("null")) {
            System.out.println("Last trip attended: " + person.last_trip);
        }
    }
    
    public static void printTransaction(Transaction transaction) {
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
    }
    
    public static void printLocalDB() {
        for (Transaction g : DT.transactions) {
            PT.printTransaction(g);
        }
        for (Gear g : DT.gear) {
            PT.printGear(g);
        }
        for (Gear_Model g : DT.gear_models) {
            PT.printGearModel(g);
        }
        for (Gear_Type g : DT.gear_types) {
            PT.printGearType(g);
        }
        for (Person g : DT.people) {
            PT.printPerson(g);
        }
    }
    
    public static void printLocalTransactions() {
        for (Transaction g : DT.transactions) {
            PT.printTransaction(g);
        }
    }
    
    public static void printLocalGear() {
        for (Gear g : DT.gear) {
            PT.printGear(g);
        }
    }
    
    public static void printLocalPeople() {
        for (Person g : DT.people) {
            PT.printPerson(g);
        }
    }
    
    public static void printGearArray(ArrayList<Gear> gear) {
        for (Gear g : gear) {
            PT.printGear(g);
            System.out.println();
        }
    }
    
    public static void printTransactionArray(ArrayList<Transaction> transactions) {
        for(Transaction t : transactions) {
            PT.printTransaction(t);
            System.out.println();
        }
    }
    
    public static void printPersonArray(ArrayList<Person> people) {
        for (Person p : people) {
            PT.printPerson(p);
        }
    }
    
}
