/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;
import java.util.*;
import java.time.OffsetDateTime;
/**
 *
 * @author Wervy Ouzades
 */
public class Transaction extends Item {
    public int id;
    public int gear_id;
    public int old_person_id;
    public int new_person_id;
    public String notes;
    public String date;
    public String datetime;
    public Gear gear;
    public Person old_person;
    public Person new_person;
    public OffsetDateTime offsetdatetime;
    public Transaction(int id, int gear_id, int old_person_id, int new_person_id, String notes, String date, String datetime){
        this.id = id;
        this.gear_id = gear_id;
        this.old_person_id = old_person_id;
        this.new_person_id = new_person_id;
        this.notes = notes;
        this.date = date;
        this.datetime = datetime;
        //updateReferences();
    }
    public Transaction(int id, int gear_id, int old_person_id, int new_person_id, String notes, String date, String datetime, OffsetDateTime offsetdatetime){
        this.id = id;
        this.gear_id = gear_id;
        this.old_person_id = old_person_id;
        this.new_person_id = new_person_id;
        this.notes = notes;
        this.date = date;
        this.datetime = datetime;
        this.offsetdatetime = offsetdatetime;
        //updateReferences();
    }
    
    public void updateReferences() {
        gear = Gear.populate(gear_id);
        gear.updateReferences();
        old_person = Person.populate(old_person_id);
        old_person.updateReferences();
        new_person = Person.populate(new_person_id);
        new_person.updateReferences();
    }
    
    public void updateLocalReferences() {
        gear = Gear.searchInArray(DT.gear, gear_id);
        old_person = Person.searchInArray(DT.people, old_person_id);
        new_person = Person.searchInArray(DT.people, new_person_id);
    }
    
    public static Transaction populate(int id) {
        Transaction transaction = new Transaction(
                id,
                QT.getTransactionGearIdById(id),
                QT.getTransactionOldPersonIdById(id),
                QT.getTransactionNewPersonIdById(id),
                QT.getTransactionNotesById(id),
                QT.getTransactionDateById(id),
                QT.getTransactionDatetimeById(id)
        );
        return transaction;
    }
    
    public static Transaction searchInArray(ArrayList<Transaction> transactions, int id) {
        for (Transaction temp : transactions) {
            if (temp.id == id) return temp;
        }
        return null;
    }
    
    public String toString() {
        return gear.toString() + "\nFrom " + old_person.name + " to " + new_person.name + " on " + date;
    }
    
}
