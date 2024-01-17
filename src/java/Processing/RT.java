/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;
import java.time.OffsetDateTime;
/**
 *
 * @author Wervy Ouzades
 */
import java.util.ArrayList;
public class RT {
    
    public static String printGearModelArray(ArrayList<Gear_Model> gear_models) {
        String result = "<table>";
        result += "<tr>"
                + "<td>IID</td>"
                + "<td>Gear type</td>"
                + "<td>Gear model</td>"
                + "<td>Price</td>"
                + "</tr>";
        for (Gear_Model g : gear_models) {
            result += printGearModelRow(g);
        }
        result += "</table>";
        return result;
    }
    
    public static String printGearModelRow(Gear_Model gear_model) {
        String result = "<tr>"
                + "<td>" + printLinkedGearModelIID(gear_model) + "</td>"
                + "<td>" + gear_model.gear_type.name + "</td>"
                + "<td>" + printLinkedGearModelDescription(gear_model) + "</td>"
                + "<td>$" + gear_model.price + "</td>"
                + "</tr>";
        return result;
    }
    
    public static String printTripArray(ArrayList<Trip> trips) {
        String result = "<table>";
        result += "<tr>"
                + "<td>IID</td>"
                + "<td>Trip Name</td>"
                + "<td>Trip Date</td>"
                + "</tr>";
        for (Trip t : trips) {
            result += printTripRow(t);
        }
        result += "</table>";
        return result;
    }
    
    public static String printTripRow(Trip trip) {
        String result = "<tr>"
                + "<td>" + printLinkedTripIID(trip) + "</td>"
                + "<td>" + trip.name + "</td>"
                + "<td>" + trip.datetime + "</td>"
                + "</tr>";
        return result;
    }
    
    public static String printRosterRow(Roster roster) {
        String result = "<tr>"
                + "<td>" + roster.id + "</td>"
                + "<td>" + roster.trip.name + "</td>"
                + "<td>" + printLinkedPersonName(roster.person) + "</td>"
                + "</tr>";
        return result;
    }
    
    
    public static String printRosterArray(ArrayList<Roster> rosters) {
        String result = "<table>";
        result += "<tr>"
                + "<td>IID</td>"
                + "<td>Trip</td>"
                + "<td>Person</td>"
                + "</tr>";
        for (Roster r : rosters) {
            result += printRosterRow(r);
        }
        result += "</table>";
        return result;
    }
    
    
    public static String printTransactionRow(Transaction transaction) {
        String result = "<tr>"
                + "<td>" + transaction.id + "</td>"
                + "<td>" + printLinkedGearCode(transaction.gear) + "</td>"
                + "<td>" + transaction.gear.gear_type.name + "</td>"
                + "<td>" + printLinkedPersonName(transaction.old_person) + "</td>"
                + "<td>" + printLinkedPersonName(transaction.new_person) + "</td>"
                + "<td>" + transaction.datetime + "</td>"
                + "<td>" + transaction.notes + "</td>"
                + "</tr>";
        return result;
    }
    public static String printTransactionArray(ArrayList<Transaction> transactions) {
        String result = "<table>";
        result += "<tr>"
                + "<td>IID</td>"
                + "<td>Gear code</td>"
                + "<td>Gear type</td>"
                + "<td>Person giving up gear</td>"
                + "<td>Person receiving gear</td>"
                + "<td>Date</td>"
                + "<td>Notes</td>"
                + "</tr>";
        for (Transaction t : transactions) {
            result += printTransactionRow(t);
        }
        result += "</table>";
        return result;
    }
    
    public static String printContainedTransactionArray(ArrayList<Transaction> transactions) {
        String result = "<div style=\"height:200px;overflow:auto;border:1px solid white\">";
        result += printTransactionArray(transactions);
        result += "</div>";
        return result;
    }
    
    public static String printLinkedTripIID(Trip trip) {
        String result = "<a href=../../../OCDBWeb/viewtrip.html?iid=" + trip.id + ">" + trip.id + "</a>";
        return result;
    }
    
    public static String printLinkedGearIID(Gear gear) {
        String result = "<a href=../../../OCDBWeb/viewgear.html?iid=" + gear.id + ">" + gear.id + "</a>";
        return result;
    }
    
    public static String printLinkedGearCode(Gear gear) {
        String result = "<a href=../../../OCDBWeb/viewgear.html?iid=" + gear.id + ">CA" + gear.code + " (" + gear.gear_type.code + ")" + "</a>";
        return result;
    }
    
    public static String printLinkedGearModelIID(Gear_Model gear_model) {
        String result = "<a href=../../../OCDBWeb/viewgearmodel.html?iid=" + gear_model.id + ">" + gear_model.id + "</a>";
        return result;
    }
    
    public static String printLinkedGearModelDescription(Gear_Model gear_model) {
        String result = "<a href=../../../OCDBWeb/viewgearmodel.html?iid=" + gear_model.id + ">" + gear_model.description + "</a>";
        return result;
    }
    
    public static String printGearRow(Gear gear) {
        String result = "<tr>"
                + "<td>" + printLinkedGearIID(gear) + "</td>"
                + "<td>" + printLinkedGearCode(gear) + "</td>"
                + "<td>" + gear.gear_type.name + "</td>"
                + "<td>" + printLinkedPersonName(gear.person) + "</td>"
                + "<td>" + printLinkedGearModelDescription(gear.gear_model) + "</td>"
                + "<td>" + gear.notes+ "</td>"
                + "<td>$" + gear.gear_model.price + "</td>"
                + "<td>"; 
                if (gear.id >= 0) {
                    OffsetDateTime e = DT.getLastTransactionDateTimeByGear(gear);
                    if (e != null) 
                        result += e.toString().replace('T', ' ').replace('Z', ' ');
                }
                result += "</td>"
                + "</tr>";
        return result;
    }
    public static String printGearArray(ArrayList<Gear> gear) {
        String result = "<table>";
        result += "<tr>"
                + "<td>IID</td>"
                + "<td width=\"6%\">Gear code</td>"
                + "<td width=\"14%\">Gear type</td>"
                + "<td width=\"8%\">Person in possession of gear</td>"
                + "<td width=\"40%\">Gear model</td>"
                + "<td>Notes</td>"
                + "<td>Price</td>"
                + "<td width=\"16%\">Last Transaction Date</td>"
                + "</tr>";
        for (Gear g : gear) {
            result += printGearRow(g);
        } 
        result += "</table>";
        return result;
    }
    
    public static String printLinkedPersonIID(Person person) {
        String result = "<a href=../../../OCDBWeb/viewperson.html?iid=" + person.id + ">" + person.id + "</a>";
        return result;
    }
    
    public static String printLinkedReceiptPersonIID(Person person) {
        String result = "<a href=../../../OCDBWeb/viewreceipt.html?iid=" + person.id + ">" + person.id + "</a>";
        return result;
    }
    
    public static String printLinkedPersonName(Person person) {
        String result = "<a href=../../../OCDBWeb/viewperson.html?iid=" + person.id + ">" + person.name + "</a>";
        return result;
    }
    
    public static String printLinkedReceiptPersonName(Person person) {
        String result = "<a href=../../../OCDBWeb/viewreceipt.html?iid=" + person.id + ">" + person.name + "</a>";
        return result;
    }
    
    public static String printPersonRow(Person person) {
        String result = "<tr>"
                + "<td>" + printLinkedReceiptPersonIID(person) + "</td>"
                + "<td>" + printLinkedPersonName(person) + "</td>"
                + "<td>" + printLinkedReceiptPersonName(person) + "</td>"
                + "</tr>";
        return result;
    }
    
    public static String printPersonArray(ArrayList<Person> people) {
        String result = "<table>";
        result += "<tr>"
                + "<td>IID</td>"
                + "<td>Name</td>"
                + "<td>Receipt</td>"
                + "</tr>";
        for (Person p : people) {
            result += printPersonRow(p);
        }
        result += "</table>";
        return result;
    }
    
    public static String printGearCodePrefilledInputBox(Gear gear) {
        String result = "        <p>\n" +
"            <label for=\"code\">Gear Code: CA</label>\n" +
"            <input type=\"text\" id=\"code\" value=" + gear.code + ">\n" +
"        </p>";
        return result;
    }
    
    public static String printGearNotesPrefilledInputBox(Gear gear) {
        String result = "        <p>\n" +
"            <label for=\"notes\">Gear Notes: </label>\n" +
"            <input type=\"text\" id=\"notes\" value=\"" + gear.notes + "\">\n" +
"        </p>";
        return result;
    }
    
    /*
    public static String printGearModelDropDown(ArrayList<Gear_Model> gear_models) {//https://www.w3schools.com/tags/tag_select.asp
        String result = "<label for \"gear_models\">Gear Model: </label>";
        result += "<select name=\"gear_models\" id=\"gear_models\">";
        for (Gear_Model gear_model : gear_models) {
            result += "<option value=" + gear_model.id + ">" + gear_model.description + "</option>";
        }
        result += "</select>";
        return result;
    }*/
    
    public static String printGearModelDropDown(ArrayList<Gear_Model> gear_models) {
        String result = "<label>Gear Model (IID): ";
        result += "<input id=\"gear_models\" list=\"modelList\"></label>";
        result += "<datalist id=\"modelList\">";
        String afterFirst = "";
        for (Gear_Model gear_model : gear_models) {
            afterFirst += "<option value=\"" + gear_model.id + "\">" + gear_model.description + "</option>";
        }
        result += afterFirst;
        result += "</datalist>";
        return result;
    }
    
    public static String printGearModelDropDown(ArrayList<Gear_Model> gear_models, Gear_Model firstInList) {
        //https://stackoverflow.com/questions/18309059/drop-down-menu-text-field-in-one
        //https://www.w3schools.com/tags/tag_select.asp
        String preResult = "<label>Gear Model (IID): ";
        boolean done = false;
        String result = "<datalist id=\"modelList\">";
        String afterFirst = "";
        for (Gear_Model gear_model : gear_models) {
            if (gear_model.id == firstInList.id) {
                result += "<option value=\"" + gear_model.id + "\">" + gear_model.description + "</option>";
                preResult += "<input id=\"gear_models\" list=\"modelList\" value=\"" + gear_model.id + "\"></label>";
                done = true;
            } else {
                afterFirst += "<option value=\"" + gear_model.id + "\">" + gear_model.description + "</option>";
            }
        }
        if (!done) {
            preResult  += "<input id=\"gear_models\" list=\"modelList\"></label>";
        }
        result += afterFirst;
        result += "</datalist>";
        result = preResult + result;
        return result;
    }
    
    public static String printPersonDropDownDatalist(ArrayList<Person> people) {
        //https://stackoverflow.com/questions/18309059/drop-down-menu-text-field-in-one
        //https://www.w3schools.com/tags/tag_select.asp
        String result = "";
        //String result = "<label>Name ";
        //result += "<input id=\"name\" autocomplete=\"off\" placeholder=\"ex. sebastian desouza\" list=\"personList\"></label>";
        result += "<datalist id=\"personList\">";
        String afterFirst = "";
        for (Person person : people) {
            afterFirst += "<option value=\"" + person.name + "\">" + person.name + "</option>";
        }
        result += afterFirst;
        result += "</datalist>";
        return result;
    }
    /*
    public static String printGearModel(Gear_Model gear_model) {
        System.out.println("Gear model: " + gear_model.gear_model);
        System.out.println("Gear model description: " + gear_model.description);
        System.out.println("Gear model type: " + gear_model.gear_type.name);
    }
    
    public static String printGearType(Gear_Type gear_type) {
        System.out.println("Gear type code: " + gear_type.code);
        System.out.println("Gear type name: " + gear_type.name);
    }
    
    public static String printPersonRow(Person person) {
        System.out.println("Person name: " + person.name);
        if (!person.last_trip.equals("null")) {
            System.out.println("Last trip attended: " + person.last_trip);
        }
    }
    
        public static String printTransactionRow(Transaction transaction) {
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
    }*/
}
