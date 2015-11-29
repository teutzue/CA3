/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import deploy.DeploymentConfiguration;
import entity.DailyRate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import net.minidev.json.JSONArray;

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
    
    public static String DailyRAteJsonObj() {
        
        Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        EntityManager em = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME).createEntityManager();
        List<DailyRate> dailyrates = em.createQuery("select t from DailyRate t", DailyRate.class).getResultList();
        
        JsonArray jsonArray = new JsonArray();
        for (DailyRate p : dailyrates) 
        {
           JsonObject json = new JsonObject();
           
          json.addProperty("date", p.getDate().toString());
          json.addProperty("rate", p.getRate());
          
          if(p.getCurrencycode()!=null )
          {
              
          
          json.addProperty("currencyCode", p.getCurrencycode().getCode());
          json.addProperty("description", p.getCurrencycode().getDescription());

          }
          jsonArray.add(json);
        }
        
        return gson.toJson(jsonArray);
    } // End of method
    
   
} // End of class
