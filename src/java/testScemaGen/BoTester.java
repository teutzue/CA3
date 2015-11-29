/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testScemaGen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.User;
import facades.AdminFacade;
import java.util.ArrayList;
import java.util.List;
import rest.util.JsonConverter;

/**
 *
 * @author bo
 */
public class BoTester {
    
    private static final AdminFacade af = AdminFacade.getInstance();
    
    public static void main(String[] args) {
        
//        List<entity.User> users = new ArrayList<>( af.getAllUsers() );
//        
//        for (User user : users) {
//            System.out.println(user.getUserName() + " " + user.getRoles().get(0).getRoleName() + " " + user.getPassword());
//        }
//        for (User user : users) {
//          
//            System.out.println(JsonConverter.user2Json(user));
//        }
        
        List<entity.User> users = new ArrayList<>(af.getAllUsers());

        String jUsers = "";
        for (entity.User user : users) {

            jUsers += JsonConverter.user2JsonObj(user);
        }
        
        System.out.println(jUsers);
        
        
        
//         JsonObject joUser = new JsonObject();
//
//        joUser.addProperty("username", "admin");
//        joUser.addProperty("role", "Admin");
//        joUser.addProperty("password", "1234567892345678923456789");
//        
//        JsonArray ja = new JsonArray();
//        
//        ja.add(joUser);
//        ja.add(joUser);
//        
//        System.out.println(ja.toString());
        
    } // End of main
    
} // End of class
