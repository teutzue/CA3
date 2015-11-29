/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deploy.DeploymentConfiguration;
import entity.DailyRate;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import rest.util.Calculator;
import rest.util.JsonConverter;

/**
 * REST Web Service
 *
 * @author CosticaTeodor
 */
@Path("demoUser")
@RolesAllowed("User")
public class DemoUserResource {

    @Context
    private UriInfo context;


    /**
     * Creates a new instance of DemoUserResource
     */
    public DemoUserResource() {
    }

    /**
     * Retrieves representation of an instance of rest.DemoUserResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("cur")
    public String getJsonFromDailyRate() {
        return JsonConverter.DailyRAteJsonObj();
    }

    @GET
    @Path("currency/calculator/{amount}/{fromCur}/{toCur}")
    public String calculateCurrency(@PathParam("amount") Double amount,
            @PathParam("fromCur") String fromCur,
            @PathParam("toCur") String toCur
    ) {
        String curs = JsonConverter.DailyRAteJsonObj();
        Calculator cal = new Calculator(amount, toCur, fromCur, curs);
        double result = cal.getCalculatedCur();
        return "" + result;
    }

    /**
     * PUT method for updating or creating an instance of DemoUserResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
