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
public class Gear_Model extends Item {
    public int id;
    public int gear_type_id;
    public String gear_model;
    public String description;
    public Gear_Type gear_type;
    public String price;
    public Gear_Model(int id, int gear_type_id, String gear_model, String description) {
        this.id = id;
        this.gear_type_id = gear_type_id;
        this.gear_model = gear_model;
        this.description = description;
        //updateReferences();
    } 
    
    public Gear_Model(int id, int gear_type_id, String gear_model, String description, String price) {
        this.id = id;
        this.gear_type_id = gear_type_id;
        this.gear_model = gear_model;
        this.description = description;
        this.price = price;
        //updateReferences();
    }
    
    

    public void updateReferences() {
        gear_type = Gear_Type.populate(gear_type_id);
        gear_type.updateReferences();
    }
    
    public void updateLocalReferences() {
        gear_type = Gear_Type.searchInArray(DT.gear_types, gear_type_id);
    }
    
    public static Gear_Model populate(int id) {
        Gear_Model gear_model = new Gear_Model(
                id,
                QT.getGearModelGearTypeIdById(id),
                QT.getGearModelGearModelById(id),
                QT.getGearModelDescriptionById(id)
        );
        return gear_model;
    }
    
    public static Gear_Model searchInArray(ArrayList<Gear_Model> gear_models, int id) {
        for (Gear_Model temp : gear_models) {
            if (temp.id == id) return temp;
        }
        return null;
    }
    
    public String toString() {
        return this.description;
    }
}
