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
public class Gear_Type extends Item {
    public int id;
    public String code;
    public String name;
    public Gear_Type(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
        //updateReferences();
    }
    public void updateLocalReferences(){
        
    }
    
    public void updateReferences() {
        
    }
    
    
    public static Gear_Type populate(int id) {
        Gear_Type gear_type = new Gear_Type(
                id,
                QT.getGearTypeCodeById(id),
                QT.getGearTypeNameById(id)
        );
        return gear_type;
    }
    
    public static Gear_Type searchInArray(ArrayList<Gear_Type> gear_types, int id) {
        for (Gear_Type temp : gear_types) {
            if (temp.id == id) return temp;
        }
        return null;
    }
    
    public String toString(){
        return name;
    }
}
