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
public class Trip extends Item{
    
    public int id;
    public String name;
    public String datetime;
    
    public Trip (int id, String name, String datetime) {
        this.id = id;
        this.name = name;
        this.datetime = datetime;
    }
    
    public void updateLocalReferences() {
        
    }
    
    public static Trip searchInArray(ArrayList<Trip> trips, int id) {
        for (Trip t : trips) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }
    
    public String toString() {
        return this.name;
    }
    
    
}
