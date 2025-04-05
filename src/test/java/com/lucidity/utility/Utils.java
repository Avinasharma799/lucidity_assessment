package com.lucidity.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class Utils {
	

    /**
     * Reads a JSON template from a file and replaces placeholders with actual values.
     *
     * @param templateFilePath Path to the JSON template file
     * @param values           Map containing placeholders and their corresponding replacement values
     * @return The final JSON string with replaced values
     * @throws IOException if there is an error reading the file
     */
    public static String generateJsonFromTemplate(String templateFilePath, Map<String, String> values) throws IOException {
        String template = new String(Files.readAllBytes(new File(templateFilePath).toPath()));
        for (Map.Entry<String, String> entry : values.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        return template;
    }
    
    public static String setOfferForTheRestuarant(String restaurant_id, String offer_type, String offer_value, String segment) throws IOException{
    	Map<String, String> offerRequestValues = new HashMap<>();
        offerRequestValues.put("restaurant_id", String.valueOf(restaurant_id));
        offerRequestValues.put("offer_type", String.valueOf(offer_type));
        offerRequestValues.put("offer_value", String.valueOf(offer_value));
        offerRequestValues.put("segment", segment);  
		return generateJsonFromTemplate("./src/test/java/json-template/set-restaurant-offer-template.json", offerRequestValues);
		
    }
    
    public static String applyCartOffer(String cart_value, String user_id, String restaurant_id) throws IOException{
    	Map<String, String> cartRequestValues = new HashMap<>();
        cartRequestValues.put("cart_value", String.valueOf(cart_value));
        cartRequestValues.put("user_id", String.valueOf(user_id));
        cartRequestValues.put("restaurant_id", String.valueOf(restaurant_id));
        return generateJsonFromTemplate("./src/test/java/json-template/apply-offer-request-template.json", cartRequestValues);
    }

}

