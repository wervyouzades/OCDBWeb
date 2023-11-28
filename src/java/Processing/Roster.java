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
public class Roster extends Item {
    public int id;
    public int trip_id;
    public int person_id;
    public Trip trip;
    public Person person;
    public Roster(int id, int trip_id, int person_id) {
        this.id = id;
        this.trip_id = trip_id;
        this.person_id = person_id;
    }
    
    public void updateLocalReferences() {
        this.trip = Trip.searchInArray(DT.trips, trip_id);
        this.person = Person.searchInArray(DT.people, person_id);
    }
    
    public static Roster searchInArray(ArrayList<Roster> rosters, int id) {
        for (Roster r : rosters) {
            if (r.id == id) {
                return r;
            }
        }
        return null;
    }
    
    public String toString() {
        return trip.name + " " + person.name;
    }
}
