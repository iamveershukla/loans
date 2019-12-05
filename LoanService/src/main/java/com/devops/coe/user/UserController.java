package com.devops.coe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.coe.loans.services.CreditRatingService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
	private CreditRatingService creditRatingService;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
	@PostMapping("/signup")
	public void signup(@RequestBody ApplicationUsers user) {
		
		System.out.println("Inside user register");
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setAuthorities("ROLE_USER_2");
		
		applicationUserRepository.save(user);
		
		// Register User to Credit Rating Service
		try {
			creditRatingService.registerCreditRating(user.getPanCard());
		}catch(Exception e) {
			System.out.println(e);
		}

	}
}
