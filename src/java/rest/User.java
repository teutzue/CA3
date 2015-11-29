package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import deploy.DeploymentConfiguration;
import entity.DailyRate;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
@RolesAllowed("User")
public class User {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}";
    }

    @GET
    @Path("currency/dailyrates")
    public String getJsonFromDailyRate() {

        Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        EntityManager em = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME).createEntityManager();
        List<DailyRate> dailyrates = em.createQuery("select t.id,t.rate,t.currencycode from DailyRate t").getResultList();
//        List<DailyRate> dailyrates = em.createQuery("select t.id,t.rate,t.currencycode from DailyRate t", DailyRate.class).getResultList();
        return gson.toJson(dailyrates);
    }
    
    

}
