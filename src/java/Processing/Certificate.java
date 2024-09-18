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
public class Certificate {
    public int id;
    public String name;
    public String description;
    
    public Certificate (int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    public static Certificate searchInArray(ArrayList<Certificate> certificates, int id) {
        for (Certificate c : certificates) {
            if (c.id == id) {
                return c;
            }
        }
        return null;
    }
    
}
