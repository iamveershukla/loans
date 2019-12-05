package com.devops.coe.loans.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.devops.coe.loans.models.CreditRating;

@Component
public class CreditRatingService {
	
	private enum Rating { A, B, C, D };
	
	@Value("${minMonthlySurplus}")
	BigDecimal minMonthlySurplus;
	
	@Value("${maxMonthlySurplus}")
	BigDecimal maxMonthlySurplus;
	
	@Value("${creditRatingServiceUrl}")
    private String url;
	
	
    public String getRating() {
    	return Rating.values()[new Random().nextInt(Rating.values().length)].toString();
    }
    
    public BigDecimal getMonthlySurplus(){
    	BigDecimal surplus = new BigDecimal(Math.random()).multiply(maxMonthlySurplus.subtract(minMonthlySurplus).add(new BigDecimal("1")));

    	return surplus.add(minMonthlySurplus).setScale(0, BigDecimal.ROUND_HALF_EVEN);
    }
    
    public CreditRating getCreditRating(String panCard)
    {	
    	final String resourceUrl  = url + "/" + panCard;
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	
    	HttpEntity <String> entity = new HttpEntity<String>(headers);
        
    	System.out.println("Resource URL is: " + resourceUrl);
    	
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, CreditRating.class).getBody();
    }
    
    public void registerCreditRating(String panCard)
    {	
    	final String resourceUrl  = url;
    	
    	CreditRating creditRating = new CreditRating(panCard, getRating(), getMonthlySurplus() );
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	
    	HttpEntity <CreditRating> entity = new HttpEntity<CreditRating>(creditRating, headers);
        
    	System.out.println("Resource URL is: " + resourceUrl);
    	
        RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.exchange(resourceUrl, HttpMethod.POST, entity, CreditRating.class);
    }
    
    
}
