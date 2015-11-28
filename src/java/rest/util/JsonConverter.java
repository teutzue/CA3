/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 *
 * @author bo
 */
public class JsonConverter {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    
    
    public static JsonObject user2JsonObj(entity.User user) {
        
        JsonObject joUser = new JsonObject();

        joUser.addProperty(Properties.USER_NAME, user.getUserName());
        joUser.addProperty(Properties.ROLE, user.getRoles().get(0).getRoleName());
        joUser.addProperty(Properties.PASSWORD, user.getPassword());
        
        return joUser;
    } // End of method
    
    public static String user2Json(entity.User user) {
        
        return user2JsonObj(user).toString();
    }
    
} // End of class
