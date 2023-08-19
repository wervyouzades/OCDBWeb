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
public class Person extends Item{
    public int id;
    public String name;
    public String last_trip;
    public Person (int id, String name, String last_trip) {
        this.id = id;
        this.name = name;
        this.last_trip = last_trip;
        //updateReferences();
    }
    public void updateReferences() {
        
    }
    
    public void updateLocalReferences() {
        
    }
    
    public static Person populate(int id) {
        Person person = new Person(
                id,
                QT.getPersonNameById(id),
                QT.getPersonLastTripById(id)
        );
        return person;
    }
    
    public static Person searchInArray(ArrayList<Person> person, int id) {
        for (Person temp : person) {
            if (temp.id == id) return temp;
        }
        return null;
    }
    
    public String toString() {
        return this.name;
    }
}
