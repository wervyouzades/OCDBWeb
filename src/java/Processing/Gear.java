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
public class Gear extends Item{
    public int id;
    public String code;
    public int person_id;
    public int gear_type_id;
    public int gear_model_id;
    public String notes;
    public String picture_url;
    public Person person;
    public Gear_Type gear_type;
    public Gear_Model gear_model;
    public ArrayList<Transaction> transactions;
    public Gear(int id, String code, int person_id, int gear_type_id, int gear_model_id) {
        this.id = id;
        this.code =  code;
        this.person_id = person_id;
        this.gear_type_id = gear_type_id;
        this.gear_model_id = gear_model_id;
        this.notes = "";
        //updateReferences();
    }
    
    public Gear(int id, String code, int person_id, int gear_type_id, int gear_model_id, String notes) {
        this.id = id;
        this.code =  code;
        this.person_id = person_id;
        this.gear_type_id = gear_type_id;
        this.gear_model_id = gear_model_id;
        this.notes = notes;
        //updateReferences();
    }
        
    public Gear(int id, String code, int person_id, int gear_type_id, int gear_model_id, String notes, String picture_url) {
        this.id = id;
        this.code =  code;
        this.person_id = person_id;
        this.gear_type_id = gear_type_id;
        this.gear_model_id = gear_model_id;
        this.notes = notes;
        this.picture_url = picture_url;
        //updateReferences();
    }
    
    public void updateReferences() {
        person = Person.populate(person_id);
        person.updateReferences();
        gear_type = Gear_Type.populate(gear_type_id);
        gear_type.updateReferences();
        gear_model = Gear_Model.populate(gear_model_id);
        gear_model.updateReferences();
    }
    
    public void updateLocalReferences() {
        person = Person.searchInArray(DT.people, person_id);
        gear_type = Gear_Type.searchInArray(DT.gear_types, gear_type_id);
        gear_model = Gear_Model.searchInArray(DT.gear_models, gear_model_id);
    }
    
    public String printLastTransactionDateTime() {
        DT.populateLocalDB();
        OffsetDateTime offsetdatetime = DT.getLastTransactionDateTimeByGear(this);
        if (offsetdatetime != null) {
            return offsetdatetime.toString();
        } else {
            return "no transactions" + DT.transactions.get(0).offsetdatetime.toString();
        }
    }
    
    public static Gear populate(int id) {
        Gear gear = new Gear(
                id,
                QT.getGearCodeById(id),
                QT.getGearPersonIdById(id),
                QT.getGearGearTypeIdById(id),
                QT.getGearGearModelIdById(id)
        );
        return gear;
    }
    
    public static Gear searchInArray(ArrayList<Gear> gear, int id) {
        for (Gear temp : gear) {
            if (temp.id == id) return temp;
        }
        return null;
    }
    
    public String toString() {
        return "CA" + this.code + "\nPossessed by: " + this.person.name + "\n" + this.gear_type.name + "\n" + this.gear_model.description;
    }
    
}
