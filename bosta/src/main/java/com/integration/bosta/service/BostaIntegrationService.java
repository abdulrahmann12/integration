package com.integration.bosta.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.bosta.dto.CustomUserDataDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BostaIntegrationService {

    private final RestTemplate restTemplate;
    
    private static final String baseurl = "https://app.bosta.co/api/v2";

    public String loginToBosta() {
   	
    	String url = baseurl + "/users/login";
    	
    	Map<String, String> loginPayload = new HashMap<>();
    	loginPayload.put("email", "bodynafea2@gmail.com");
    	loginPayload.put("password", "Boudy_12");
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(loginPayload,headers);
    	
    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
 	   try {
 		   
 		   
   		JsonNode root = mapper.readTree(response.getBody());
   	 
   		JsonNode data = root.get("data");
   		
   		String token = data.get("token").asText();
   		    	
        return token;

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return "Error parsing response";
    	    }
 	   }

    
    public CustomUserDataDTO getAllData() {
    	String url = baseurl + "/users/fullData";
        String token = loginToBosta(); 

        if (token == null) {
            throw new IllegalAccessError( "Failed to get token from login");
        }
        
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.set("Authorization",token);
    	
    	HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    	
    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    	
    	try {
    		ObjectMapper mapper = new ObjectMapper();
    		JsonNode root = mapper.readTree(response.getBody());
 
    		JsonNode data = root.get("data");
    		
    		String firstName = data.get("profile").get("firstName").asText();
    		String lastName = data.get("profile").get("lastName").asText();
    		String phone = data.get("profile").get("phone").asText();
    		String email = data.get("email").get("address").asText();
    		String businessName = data.get("businessAdminInfo").get("businessName").asText();
    		String country = data.get("country").get("name").asText();
    		
            CustomUserDataDTO dto = new CustomUserDataDTO();
            dto.setFullName(firstName + " " + lastName);
            dto.setPhone(phone);
            dto.setBusiness(businessName);
            dto.setCountry(country);
            dto.setEmail(email);

            return dto;
    		
		} catch (Exception e) {
	        e.printStackTrace();
	        return null;
		}
    }
}
