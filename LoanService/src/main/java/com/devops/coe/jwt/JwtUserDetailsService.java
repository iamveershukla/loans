package com.devops.coe.jwt;

import com.devops.coe.user.ApplicationUserRepository;
import com.devops.coe.user.ApplicationUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private ApplicationUserRepository userRepository;
	private ApplicationUsers user;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user = userRepository.findByUsername(username);
		
		System.out.println("Inside loadByUsername User Details: " + user.toString());
		
		JwtUserDetails jwtUserDetails = new JwtUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), user.getAuthorities());

		if (user==null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return jwtUserDetails;
	}

}
