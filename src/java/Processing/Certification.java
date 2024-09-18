/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;

/**
 *
 * @author Wervy Ouzades
 */
public class Certification {
    public int id;
    public int certificate_id;
    public int person_id;
    public Certificate certificate;
    public Person person;
    
    public Certification (int id, int certificate_id, int person_id) {
        this.id = id;
        this.certificate_id = certificate_id;
        this.person_id = person_id;
    }
    
    public void updateLocalReferences() {
        this.certificate = Certificate.searchInArray(DT.certificates, certificate_id);
        this.person = Person.searchInArray(DT.people, person_id);
    }
}
