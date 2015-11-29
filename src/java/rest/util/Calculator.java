package rest.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;

/**
 *
 * @author CosticaTeodor
 */
public class Calculator {

    public double amount;
    public  String from;
    public  String to;
    
    public String jsonStringArray;

    public Calculator(double amount, String from, String to, String jsonStringArray) {
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.jsonStringArray = jsonStringArray;
    }

    public double getRate(String code) {
        Gson gson = new Gson();
        JsonArray curs = new JsonParser().parse(jsonStringArray).getAsJsonArray();

        for (int i = 0; i < curs.size(); i++) {
            JsonObject obj = curs.get(i).getAsJsonObject();
            System.out.println("the json obj: " + gson.toJson(obj));
            if (obj.has("currencyCode")) {
                String codec = obj.get("currencyCode").getAsString();
                
                if (codec.equals(code)) {
                    return obj.get("rate").getAsDouble();

                }
            }

        }
        return 1.0;
    }
    
    public  double getCalculatedCur(){
        double  targetAmount = 0.1;
        System.out.println("the rate from "+getRate(from));
        System.out.println("the rate from "+getRate(to));
        return targetAmount = (amount / getRate(from)) * getRate(to);
    }

}
