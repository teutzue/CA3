package rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import facades.AdminFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rest.util.JsonConverter;

@Path("demoadmin")
@RolesAllowed("Admin")
public class Admin {

//    private static final AdminFacade af = AdminFacade.getInstance();
    private final AdminFacade af = AdminFacade.getInstance();
    
    public Admin() {}

    @DELETE
    @Path("users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePersonJson(@PathParam("id") String userName)  {
        
        entity.User user =  af.deleteUser(userName);
        
        return "{}";
    }

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {

        List<entity.User> users = af.getAllUsers();

        String jUsers = "";
        JsonArray jaUsers = new JsonArray();
        for (entity.User user : users) {

            jaUsers.add( JsonConverter.user2JsonObj(user) );
        }
        
        return jaUsers.toString();
    }

} // End of class
