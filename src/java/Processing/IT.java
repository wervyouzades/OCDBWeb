/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;

//import static ocdb.OCDB.diyan;
/**
 *
 * @author Wervy Ouzades
 */
public class IT {

    public static void inputNewPerson() {
        System.out.println("Enter person? (y/n)");
        if (IT.checkYN()) {
            System.out.println("New person name: ");
            String name = OCDB.diyan.nextLine();
            FT.addPersonToDB(name);
        }
    }
    
    public static void inputCheckout() {
        System.out.println("Check out gear:");
        System.out.println("Gear code: ");
        String code = inputGearCode();
        System.out.println("Gear type letter: ");
        String geartypeletter = inputGearTypeCode();
        System.out.println("Person name: ");
        String name = OCDB.diyan.nextLine();
        Gear temp = DT.getGearByCodeAndType(code, geartypeletter);
        //PT.printGear(temp);
        Person tempPerson = DT.getPersonByName(name);
        FT.checkoutGear(temp, tempPerson);
    }
    
    public static void inputCheckin(){
        System.out.println("Check in gear:");
        System.out.println("Gear code: ");
        String code = inputGearCode();
        System.out.println("Gear type letter: ");
        String geartypeletter = inputGearTypeCode();
        Gear temp = DT.getGearByCodeAndType(code, geartypeletter);
        FT.checkinGear(temp);
    }
    
    public static String processCheckout(String name, String code, String type) {
        OCDB.connect();
        Gear temp = DT.getGearByCodeAndType(processGearCode(code), processGearTypeCode(type));
        if (temp == null) return "ERROR: gear doesn't exist or isn't in database";
        Person tempPerson = DT.getPersonByName(name);
        if (tempPerson == null) return "ERROR: person doesn't exist or isn't in database";
        return FT.checkoutGear(temp, tempPerson);
        
    }
    
    public static String processCheckout(String name, String code, String type, String notes) {
        OCDB.connect();
        Gear temp = DT.getGearByCodeAndType(processGearCode(code), processGearTypeCode(type));
        if (temp == null) return "ERROR: gear doesn't exist or isn't in database";
        Person tempPerson = DT.getPersonByName(name);
        if (tempPerson == null) return "ERROR: person doesn't exist or isn't in database";
        return FT.checkoutGear(temp, tempPerson, notes);
        
    }  
    
    public static String processTransfer(String name, String code, String type) {
        OCDB.connect();
        Gear temp = DT.getGearByCodeAndType(processGearCode(code), processGearTypeCode(type));
        if (temp == null) return "ERROR: gear doesn't exist or isn't in database";
        Person tempPerson = DT.getPersonByName(name);
        if (tempPerson == null) return "ERROR: person doesn't exist or isn't in database";
        return FT.transferGear(temp, tempPerson);
    }
    
    public static String processTransfer(String name, String code, String type, String notes) {
        OCDB.connect();
        Gear temp = DT.getGearByCodeAndType(processGearCode(code), processGearTypeCode(type));
        if (temp == null) return "ERROR: gear doesn't exist or isn't in database";
        Person tempPerson = DT.getPersonByName(name);
        if (tempPerson == null) return "ERROR: person doesn't exist or isn't in database";
        return FT.transferGear(temp, tempPerson, notes);
    }
    
    public static String processCheckin(String code, String type) {
        OCDB.connect();
        Gear temp = DT.getGearByCodeAndType(processGearCode(code), processGearTypeCode(type));
        if (temp == null) return "ERROR: gear doesn't exist or isn't in database";
        return FT.checkinGear(temp);
    }
    
    public static String processCheckin(String code, String type, String notes) {
        OCDB.connect();
        Gear temp = DT.getGearByCodeAndType(processGearCode(code), processGearTypeCode(type));
        if (temp == null) return "ERROR: gear doesn't exist or isn't in database";
        return FT.checkinGear(temp, notes);
    }
    
    public static String processNewPerson(String name) {
        if (DT.getPersonByName(name) == null) {
            return FT.addPersonToDB(name);
        } else {
            return "ERROR: person is already in database";
        }
    }    
    
    public static String processRemovePerson(String name) {
        if (DT.getPersonByName(name) != null) {
            return FT.removePersonFromDB(name);
        } else {
            return "ERROR: person doesn't exist";
        }
    }
    
    public static String processEditGear(int id, int gearModelId, String notes) {
        if (DT.getGearById(id) != null && DT.getGearModelById(gearModelId) != null) {
            return FT.editGear(id, gearModelId, notes);
        } else {
            return "ERROR: parameters don't match objects in database\n" +
                    id + "\n" +
                    DT.getGearById(id) + "\n" + 
                    DT.getGearModelById(gearModelId);
        }
    }
    
    public static String processNewGear(String code, String type, int modelIID) {
        OCDB.connect();
        String pcode = processGearCode(code);
        String ptype = processGearTypeCode(type);
        if (DT.getGearByCodeAndType(pcode, ptype) != null) return "ERROR: gear already exists";
        return FT.addGearToDB(pcode, ptype, modelIID);
    }
    
    public static String processNewGearModel(String type, String model, String modelDescription, String price) {
        type = IT.processGearTypeCode(type);
        price = IT.processGearPrice(price);
        return FT.addGearModelToDB(type, model, modelDescription, price);
    }
    
    public static String processGearCode(String code) {
        String updatedCode = code.replaceAll("[^0-9]", "");
        return updatedCode;
    }
    
    public static String processGearTypeCode(String code) {
        if (code.length() > 0)
            return code.substring(0, 1).toUpperCase();
        else
            return "";
    }
    
    public static String processGearPrice(String price) {
        return price.replaceAll("[^0-9]", "");
    }
    
    public static String inputGearCode() {
        String code = OCDB.diyan.nextLine();
        String updatedCode = code.replaceAll("[^0-9]", "");
        return updatedCode;
    }
  
    
    public static String inputGearTypeCode() {
        String code = OCDB.diyan.nextLine();
        return code.substring(0, 1).toUpperCase();
    }
    
    public static boolean checkYN() {
        String answer = OCDB.diyan.nextLine();
        try {
            if (answer.toLowerCase().charAt(0) == 'y') {
                return true;
            }
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }
}
