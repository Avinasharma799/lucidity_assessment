package com.lucidity.assesment;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lucidity.utility.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
public class ApplyCartOfferTest {
	
	private static final String BASE_URL = "http://localhost:9001";
	
	@BeforeClass
    public void setup() throws IOException, InterruptedException {
        RestAssured.baseURI = BASE_URL;
        System.out.println("Hello World!");
        String[] command = {"/bin/bash", "./src/test/java/setup_environment.sh"};

        // Create a ProcessBuilder to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        // Read and log the output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Environment setup completed successfully.");
        } else {
            System.err.println("Environment setup failed with exit code " + exitCode);
        }
    }
    
    
    

    @Test(description="TC01 - Verify apply valid Flat Amount Offer for Segment p1")
    public void testApplyValidFlatAmountOfferForP1() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("7", "FLATX", "10", "p1");
			applyCartOffer = Utils.applyCartOffer("300", "1", "7");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        int expectedCartValue = 300 - 10; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC02 - Verify apply valid Flat Amount Offer for Segment p2")
    public void testApplyValidFlatAmountOfferForP2() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("7", "FLATX", "20", "p2");
			applyCartOffer = Utils.applyCartOffer("300", "2", "7");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        int expectedCartValue = 300 - 20; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC03 - Verify apply valid Flat Amount Offer for Segment p3")
    public void testApplyValidFlatAmountOfferForP3() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("7", "FLATX", "30", "p3");
			applyCartOffer = Utils.applyCartOffer("300", "3", "7");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        int expectedCartValue = 300 - 30; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC04 - Verify apply valid Flat Percent Offer for Segment p1")
    public void testApplyValidFlatPercentOfferForP1() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("8", "FLATX%", "10", "p1");
			applyCartOffer = Utils.applyCartOffer("300", "1", "8");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 300 - (0.1*300); // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC05 - Verify apply valid Flat Percent Offer for Segment p2")
    public void testApplyValidFlatPercentOfferForP2() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("8", "FLATX%", "20", "p2");
			applyCartOffer = Utils.applyCartOffer("300", "2", "8");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 300 - (0.2*300); // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC06 - Verify apply valid Flat Percent Offer for Segment p3")
    public void testApplyValidFlatPercentOfferForP3() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("8", "FLATX%", "30", "p3");
			applyCartOffer = Utils.applyCartOffer("300", "3", "8");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 300 - (0.3*300); // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC07 - Verify Apply Flat Amount Offer for Segment p1 with Minimum Cart Value")
    public void testApplyValidFlatAmountOfferForP1WithMinimumCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("9", "FLATX", "10", "p1");
			applyCartOffer = Utils.applyCartOffer("1", "1", "9");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 0; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC08 - Verify Apply Flat Percentage Offer for Segment p2 with Minimum Cart Value")
    public void testApplyValidFlatPercentOfferForP2WithMinimumCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("9", "FLATX%", "10", "p2");
			applyCartOffer = Utils.applyCartOffer("1", "2", "9");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 1-(0.1*1); // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC09 - Verify Apply Flat Amount Offer for Segment p3 with Maximum Cart Value")
    public void testApplyValidFlatAmountOfferForP3WithMaximumCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("9", "FLATX", "50", "p3");
			applyCartOffer = Utils.applyCartOffer("2147483647", "3", "9");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        long expectedCartValue = 2147483647-50; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
    }
    
    @Test(description="TC10 - Verify Apply Flat Amount Offer for Segment p3 with Maximum Cart Value Plus One")
    public void testApplyValidFlatAmountOfferForP3WithMaximumCartValuePlusOne() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("9", "FLATX", "50", "p3");
			applyCartOffer = Utils.applyCartOffer("2147483648", "3", "9");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertEquals(cartResponse.statusCode(), 400, "Bad Request");
    }
    
    @Test(description="TC11 - Verify Apply Flat Percentage Offer for Segment p1 with Maximum Cart Value")
    public void testApplyValidFlatPercentOfferForP1WithMaximumCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("10", "FLATX%", "50", "p1");
			applyCartOffer = Utils.applyCartOffer("2147483647", "1", "10");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 2147483647-0.5*2147483647; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
        }
    
    @Test(description="TC12 - Verify Apply Flat Percentage Offer for Segment p1 with Maximum Cart Value Plus One")
    public void testApplyValidFlatPercentOfferForP3WithMaximumCartValuePlusOne() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("10", "FLATX%", "25", "p1");
			applyCartOffer = Utils.applyCartOffer("2147483648", "1", "10");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertEquals(cartResponse.statusCode(), 400, "Bad Request");
        }
    
    @Test(description="TC13 - Verify Apply Offer with Zero Cart Value")
    public void testApplyOfferWithZeroCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("9", "FLATX", "10", "p1");
			applyCartOffer = Utils.applyCartOffer("0", "1", "9");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 0; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
        }
    
    @Test(description="TC14 - Verify Apply Offer for Segment p1 with Invalid Offer Type")
    public void testApplyOfferWithInvalidOfferType() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("10", "INVALID", "50", "p2");
			applyCartOffer = Utils.applyCartOffer("1000", "2", "10");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertEquals(cartResponse.statusCode(), 400, "Bad Request");
        }
    
    @Test(description="TC15 - Verify Apply Offer for Invalid Segment")
    public void testApplyOfferForInvalidSegement() {
    	String applyCartOffer = null;
		try {
			applyCartOffer = Utils.applyCartOffer("1000", "4", "10");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertEquals(cartResponse.statusCode(), 400, "Bad Request");
        }
    
    @Test(description="TC16 - Verify Apply Multiple Offers for Segment p1")
    public void testApplyOfferForMultipleSegementP1() {
    	String setOfferRequest1 = null;
    	String setOfferRequest2 = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest1 = Utils.setOfferForTheRestuarant("7", "FLATX", "10", "p1");
			setOfferRequest2 = Utils.setOfferForTheRestuarant("7", "FLATX%", "5", "p1");
			applyCartOffer = Utils.applyCartOffer("300", "1", "7");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest1)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest2)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 300 - 0.5*300; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
        }
    
    @Test(description="TC17 - Verify Apply Offer for Cart with Discount Exceeding Cart Value")
    public void testApplyOfferWithDiscountExceedingCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("10", "FLATX%", "300", "p3");
			applyCartOffer = Utils.applyCartOffer("200", "3", "10");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 0; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
        }
    
    @Test(description="TC18 - Verify Apply Flat Percentage Offer for Cart Value = -100")
    public void testApplyFlatNegativePercentOfferForCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("11", "FLATX%", "-100", "p1");
			applyCartOffer = Utils.applyCartOffer("200", "1", "11");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertEquals(cartResponse.statusCode(), 400, "Bad Request");
        }
    
    @Test(description="TC19 - Verify Apply Offer with Multiple Users in Same Segment")
    public void testApplyOfferWithMultipleUserInSameSegement() {
    	String setOfferRequest1 = null;
    	String applyCartOffer1 = null;
    	String setOfferRequest2 = null;
    	String applyCartOffer2 = null;
    	String setOfferRequest3 = null;
    	String applyCartOffer3 = null;
		try {
			setOfferRequest1 = Utils.setOfferForTheRestuarant("12", "FLATX", "20", "p1");
			applyCartOffer1 = Utils.applyCartOffer("200", "1", "12");
			setOfferRequest2 = Utils.setOfferForTheRestuarant("12", "FLATX", "20", "p2");
			applyCartOffer2 = Utils.applyCartOffer("200", "2", "12");
			setOfferRequest3 = Utils.setOfferForTheRestuarant("12", "FLATX", "20", "p3");
			applyCartOffer3 = Utils.applyCartOffer("200", "3", "12");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest1)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest2)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest3)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer1)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        int updatedCartValue = cartResponse.jsonPath().get("cart_value");
        double expectedCartValue = 200-20; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
        
        cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer2)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        updatedCartValue = cartResponse.jsonPath().get("cart_value");
        expectedCartValue = 200-20; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
        
        cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer3)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        updatedCartValue = cartResponse.jsonPath().get("cart_value");
        expectedCartValue = 200-20; // Flat discount calculation
        Assert.assertEquals(updatedCartValue, expectedCartValue, "Cart value after applying offer is incorrect!");
        System.out.println("Updated Cart Value after Applying Offer: " + updatedCartValue);
        }
    
    @Test(description="TC20 - Verify Apply Flat Amount Offer for Cart with Negative Amount")
    public void testApplyFlatAmountOfferForNegativeCartValue() {
    	String setOfferRequest = null;
    	String applyCartOffer = null;
		try {
			setOfferRequest = Utils.setOfferForTheRestuarant("12", "FLATX", "20", "p1");
			applyCartOffer = Utils.applyCartOffer("-50", "1", "12");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error while generating json template");
		}
    	Response offerResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(setOfferRequest)
                .when()
                .post("/api/v1/offer")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(offerResponse.getStatusCode(), 200);
        System.out.println("Offer Applied Successfully");
        
        Response cartResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(applyCartOffer)
                .when()
                .post("/api/v1/cart/apply_offer")
                .then()
                .statusCode(400)
                .extract()
                .response();
        Assert.assertEquals(cartResponse.statusCode(), 400, "Bad Request");
        }
}
