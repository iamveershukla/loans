package com.devops.coe.creditrating.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.coe.creditrating.models.CreditRating;
import com.devops.coe.creditrating.repositories.CreditRatingRepository;

@RestController
@RequestMapping("/api/v1/creditrating")
public class CreditRatingController {
	
	@Autowired
	private CreditRatingRepository creditRatingRepository;
	
	@GetMapping
	public List<CreditRating> list(){
		return creditRatingRepository.findAll();
	}
	
	@PostMapping
	public void create(@RequestBody CreditRating creditRating) {
		creditRatingRepository.save(creditRating);		
	}
	
	@GetMapping("/{pancard}")
	public CreditRating get(@PathVariable("pancard") String pancard){
		return creditRatingRepository.getOne(pancard);		
	}

}
