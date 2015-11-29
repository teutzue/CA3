/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testScemaGen;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import deploy.DeploymentConfiguration;
import entity.DailyRate;
import entity.User;
import facades.AdminFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rest.util.JsonConverter;

/**
 *
 * @author bo
 */
public class BoTester {
    
    private static final AdminFacade af = AdminFacade.getInstance();
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    private static EntityManagerFactory emf =  Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    
    
    
    
    public static void DailyRates() {
        
//        EntityManager em = emf.createEntityManager();
//        
//        List<DailyRate> dailyrates = em.createQuery("select t.id,t.rate,t.currencycode from DailyRate t", DailyRate.class).getResultList();
//        
//        
//        System.out.println( gson.toJson(dailyrates) );
        
        
        
    } // End of method
    
    
    public static void test() {
        
        
        
//        List<entity.User> users = new ArrayList<>( af.getAllUsers() );
//        
//        for (User user : users) {
//            System.out.println(user.getUserName() + " " + user.getRoles().get(0).getRoleName() + " " + user.getPassword());
//        }
//        for (User user : users) {
//          
//            System.out.println(JsonConverter.user2Json(user));
//        }
        
//        List<entity.User> users = new ArrayList<>(af.getAllUsers());
//
//        String jUsers = "";
//        for (entity.User user : users) {
//
//            jUsers += JsonConverter.user2JsonObj(user);
//        }
//        
//        System.out.println(jUsers);
        
        
        
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
        
    } // End of method
    
    
    
    
    public static void main(String[] args) {
        
        DailyRates();
       
        
    } // End of main
    
} // End of class
