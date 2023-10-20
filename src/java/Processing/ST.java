/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Processing;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Wervy Ouzades
 */
public class ST {
    public static final String[] edit_perm_roles = {"siteadmin"};
    public static boolean hasEditPerms(HttpServletRequest request) {
        for (String edit_role : edit_perm_roles) {
            if (request.isUserInRole(edit_role)) return true;
        }
        return false;
    }
}
